package io.elasticore.blueprint.domain.parts.service;


import io.elasticore.blueprint.domain.parts.dto.*;
import io.elasticore.blueprint.domain.parts.entity.*;
import io.elasticore.blueprint.domain.parts.port.PartsCatalogAdapter;
import io.elasticore.blueprint.domain.parts.repository.PartsRepositoryHelper;
import io.elasticore.springboot3.entity.Op;
import io.elasticore.springboot3.mapper.Mapper;
import io.elasticore.springboot3.mapper.MappingContext;

import io.elasticore.springboot3.util.ReflectUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@AllArgsConstructor
public class PartsCatalogsService {

    private final PartsCatalogAdapter partsCatalogAdapter;

    private final PartsRepositoryHelper helper;

    private final static boolean FORCE_CAL_API = true;


    public List<CatalogDTO> getCatalogs() {
        List<Catalog> catalogList = helper.getCatalog().findAll();
        if (catalogList == null)
            catalogList = new ArrayList<>();
        if (FORCE_CAL_API || catalogList.size() < 10) {
            List<Map> catalogs = partsCatalogAdapter.getCatalogs();
            for (Map item : catalogs) {
                Catalog catalog = new Catalog();
                Mapper.of(item, catalog)
                        .map("id", Q.Catalog.catalogId)
                        .map("name", Q.Catalog.name)
                        .includeUndefinedMap(false)
                        .execute();
                helper.getCatalog().save(catalog);

                catalogList.add(catalog);
            }

        }
        List<CatalogDTO> list = Mapper.of(catalogList, CatalogDTO.class).toList();
        return list;
    }

    /**
     * @param catalogId
     * @return
     */
    public List<CarModelDTO> getCarModels(String catalogId) {

        Specification<CarModel> sp = Q.CarModel.catalog().catalogId(Op.EQ, catalogId);
        Sort sort = Sort.by(Q.CarModel.getName().getAscOrder());

        List<CarModel> carModelList = Optional.ofNullable(helper.getCarModel().findAll(sp, sort))
                .orElseGet(ArrayList::new);

        if (FORCE_CAL_API || carModelList.isEmpty()) {

            Catalog catalog = new Catalog(catalogId);

            if(helper.getCatalog().findById(catalogId).isEmpty()) {
                catalog.setName("undifiend");
                helper.getCatalog().save(catalog);
            }

            List<PartInfo> carModelList2 = partsCatalogAdapter.getCarModelList(catalogId);

            for(PartInfo partInfo : carModelList2) {
                CarModel carModel = Mapper.of(partInfo, CarModel.class)
                        .map(Q.CarModel.id, Q.CarModel.name, Q.CarModel.img)
                        .execute();
                carModel.setCatalog(catalog);
                helper.getCarModel().save(carModel);
                carModelList.add(carModel);
            }


            List<PartInfo> carModelList1 = partsCatalogAdapter.getCarModelList(catalogId);
            for (PartInfo partInfo : carModelList1) {
                CarModel carModel = new CarModel();
                carModel.setCatalog(catalog);
                Mapper.of(partInfo, carModel)
                        .map(Q.CarModel.id, Q.CarModel.name, Q.CarModel.img)
                        .execute();

                helper.getCarModel().save(carModel);
                carModelList.add(carModel);
            }
        }

        List<CarModelDTO> resultList = Mapper.of(carModelList, CarModelDTO.class)
                .map(Q.CarModel.id, Q.CarModel.name, Q.CarModel.img)
                .toList();

        return resultList;
    }


    // 3. 차량 목록 - 저장하고 재활용
    public List<CarInfoDTO> getCarInfoList(String catalogId, String modelId, Integer page) {
        Specification<CarInfo> spec = Q.CarInfo.modelId(Op.EQ, modelId);
        Sort sort = Sort.by(Q.CarInfo.getName().getAscOrder());
        List<CarInfo> list = helper.getCarInfo().findAll(spec, sort);
        if (list == null)
            list = new ArrayList<>();

        if (FORCE_CAL_API || list.isEmpty() || list.size() < 10) {
            List<Map<String, Object>> rawList = partsCatalogAdapter.getCarInfoList(catalogId, modelId, page);
            for (Map<String, Object> item : rawList) {
                CarInfo car = new CarInfo();
                Mapper.of(item, car)
                        .map(Q.CarInfo.id, Q.CarInfo.name
                                , Q.CarInfo.modelId, Q.CarInfo.modelName
                                , Q.CarInfo.description, Q.CarInfo.criteria
                                , Q.CarInfo.brand, Q.CarInfo.groupsTreeAvailable)
                        .execute();

                List<Map> parames = (List) item.get("parameters");
                if (parames != null) {
                    List<ParamInfo> paramInfoList = new ArrayList<>();
                    car.setParameters(paramInfoList);
                    for (Map param : parames) {
                        ParamInfo paramInfo = new ParamInfo();
                        paramInfo.setCarId(car.getId());
                        Mapper.copy(param, paramInfo);

                        paramInfoList.add(paramInfo);
                        helper.getParamInfo().save(paramInfo);
                    }
                }

                helper.getCarInfo().save(car);
                list.add(car);
            }
        }

        return PartsMapper.toCarInfoDTOList(list, MappingContext.withGuard(2, c ->
                "carId".equals(c.getFieldName()) ? false : true));
        //return ReflectUtils.toList(list, CarInfoDTO.class);
    }

    // 4. 차량 상세 정보
    public CarInfoDTO getCarInfo(String catalogId, String carId) {
        Optional<CarInfo> optional = helper.getCarInfo().findById(carId);
        CarInfo car = null;
        if (FORCE_CAL_API || !optional.isPresent()) {


            Map<String, Object> raw = partsCatalogAdapter.getCarInfo(catalogId, carId);
            car = new CarInfo();


            Mapper.of(raw, car)
                    .map("id", Q.CarInfo.id)
                    .map("name", Q.CarInfo.name)
                    .map("modelId", Q.CarInfo.modelId)
                    .map("description", Q.CarInfo.description)
                    .map("brand", Q.CarInfo.brand)
                    .map("groupTreeAvailables", Q.CarInfo.groupsTreeAvailable)
                    .execute();


            List<Map> parames = (List) raw.get("parameters");
            if (parames != null) {
                List<ParamInfo> paramInfoList = new ArrayList<>();
                car.setParameters(paramInfoList);
                for (Map param : parames) {
                    ParamInfo paramInfo = new ParamInfo();
                    paramInfo.setCarId(car.getId());
                    Mapper.copy(param, paramInfo);
                    helper.getParamInfo().save(paramInfo);
                    paramInfoList.add(paramInfo);
                }
            }

            helper.getCarInfo().save(car);
        } else {
            car = optional.get();
        }

        CarInfoDTO carInfoDTO = new CarInfoDTO();
        Mapper.copy(car, carInfoDTO);

        return carInfoDTO;
    }

    // 5. 파라미터 정보 저장
    public List<ParamInfoDTO> getCarParams(String catalogId, String modelIf, List<String> parameter) {

        /*
        Specification<ParamInfo> spec = Q.ParamInfo.carId(Op.EQ, modelIf);

        List<ParamInfo> paramList = helper.getParamInfo().findAll(spec);

        if (FORCE_CAL_API || paramList.isEmpty()) {
            Map<String, Object> raw = partsCatalogAdapter.getCarParams(catalogId, modelIf ,parameter);
            List<Map<String, Object>> params = (List<Map<String, Object>>) raw.get("params");
            for (Map<String, Object> item : params) {
                ParamInfo param = new ParamInfo();
                Mapper.of(item, param)
                        .map(Q.ParamInfo.idx, Q.ParamInfo.key, Q.ParamInfo.name
                                , Q.ParamInfo.value, Q.ParamInfo.carId, Q.ParamInfo.sortOrder)
                        .execute();
                helper.getParamInfo().save(param);
                paramList.add(param);
            }
        }

        return Mapper.of(paramList, ParamInfoDTO.class).toList();
        */

        return null;

    }


    /**
     * @param vinOrFrame
     * @return
     */
    public List<CarProfileInfoDTO> findVin(String vinOrFrame) {

        Specification<CarProfile> sp = Q.CarProfile.vin(Op.LIKE, vinOrFrame)
                .or(Q.CarProfile.frame(Op.LIKE, vinOrFrame));

        List<CarProfile> list = helper.getCarProfile().findAll(sp);

        List<CarProfileInfoDTO> resultList = new ArrayList<>();
        if (FORCE_CAL_API || list == null || list.isEmpty()) {
            List<Map> carInfoByVIN = partsCatalogAdapter.getCarInfoByVIN(vinOrFrame);
            for (Map map : carInfoByVIN) {

                CarInfo info = new CarInfo();
                Mapper.of(map, info)
                        .map("title", Q.CarInfo.name)
                        .map("carId", Q.CarInfo.id)
                        .execute();


                Optional.ofNullable(info.getParameters())
                        .ifPresent(params -> params.forEach(p -> p.setCarId(info.getId())));

                helper.getCarInfo().save(info);

                CarProfile carProfile = new CarProfile();
                Mapper.copy(map, carProfile);
                carProfile.setCarInfo(info);


                helper.getCarProfile().save(carProfile);


                CarProfileInfoDTO item = new CarProfileInfoDTO();
                Mapper.copy(map, item);

                resultList.add(item);
            }
        } else {
            for (CarProfile carProfile : list) {

                CarProfileInfoDTO item = new CarProfileInfoDTO();
                Mapper.copy(carProfile, item);

                Mapper.copy(carProfile.getCarInfo(), item);

                item.setCarId(carProfile.getCarInfo().getId());
                resultList.add(item);
            }

        }

        return resultList;

    }


    public List<PartGroupDTO> getCatalogGroups(String catalogId, String carId, String groupId, String criteria) {

        Specification<PartGroup> sp = Q.PartGroup.carId(Op.EQ, carId, true)
                .and(Q.PartGroup.id(Op.EQ, groupId, true))
                .and(Q.PartGroup.criteria(Op.LIKE, criteria, true));


        List<PartGroup> dbList = helper.getPartGroup().findAll(sp);
        if (FORCE_CAL_API || dbList == null || dbList.size() < 2 ) {
            dbList = new ArrayList<>();
            List<Map> catalogGroupList = partsCatalogAdapter.getCatalogGroupList(catalogId, carId, groupId, criteria);

            for (Map map : catalogGroupList) {

                PartGroup item = new PartGroup();
                Mapper.of(map, item).execute();
                item.setCarId(carId);

                helper.getPartGroup().save(item);
                dbList.add(item);
            }
        }

        List<PartGroupDTO> resp = Mapper.of(dbList, PartGroupDTO.class).toList();
        return resp;

    }


    public List getSuggestedGroupsString(String catalogId, String q) {
        Specification<SuggestGroup> sp = Q.SuggestGroup.catalogId(Op.EQ, catalogId);

        List<SuggestGroup> respList = helper.getSuggestGroup().findAll(sp);
        if (FORCE_CAL_API || respList == null || respList.isEmpty()) {
            List<Map> apiList = partsCatalogAdapter.getCatalogGroupSuggestList(catalogId, q);

            for (Map item : apiList) {
                SuggestGroup entity = new SuggestGroup();
                Mapper.of(item, entity).execute();


                helper.getSuggestGroup().save(entity);
                respList.add(entity);
            }
        }

        List<SuggestGroupDTO> response = Mapper.of(respList, SuggestGroupDTO.class).toList();
        return response;
    }


    public PartGroupInfoDTO getParts(String catalogId
            , String carId
            , String groupId
            , String criteria

    ) {

        Specification<PartGroupInfo> sp = Q.PartGroupInfo.carId(Op.EQ, groupId)
                .and(Q.PartGroupInfo.groupId(Op.EQ, groupId));

        List<PartGroupInfo> respList = null; //helper.getPartGroupInfo().findAll(sp );

        PartGroupInfo respObj = new PartGroupInfo(carId, groupId);

        PartGroup entity = null;
        if (FORCE_CAL_API || respList == null || respList.isEmpty())
        {
            Map apiResp = partsCatalogAdapter.getParts(catalogId, carId, groupId, criteria);

            Mapper.of(apiResp, respObj).skipNull(true).execute();

            if(respObj.getPartGroups()!=null) {
                for(PartGroup pargGroup : respObj.getPartGroups()) {
                    pargGroup.setId(groupId);
                    pargGroup.setCarId(carId);
                }
            }

            if(respObj.getPositions()!=null) {
                for(PartPosition pp : respObj.getPositions()) {
                    pp.setId(groupId);
                }
            }
            //helper.getPartGroupInfo().save(respObj);
        }
        else {

        }

        PartGroupInfoDTO resp = Mapper.of(respObj, PartGroupInfoDTO.class).toObject();

        return resp;
    }


    public List getGroupTree(String catalogId
            , String carId
            , String criteria
            , Boolean cached
    ) {
        List resp = partsCatalogAdapter.getGroupsTree(catalogId, carId, criteria, cached);

        return resp;
    }


    public CatalogSchemaDTO getSchemas(String catalogId
            , String carId
            , String branchId
            , String criteria
            , int page
            , String partNameIds
            , String partName

    ) {

        CatalogSchemaDTO resp = partsCatalogAdapter.getSchemas(carId, catalogId, branchId, criteria, page, partNameIds, partName);
        return resp;
    }

}
