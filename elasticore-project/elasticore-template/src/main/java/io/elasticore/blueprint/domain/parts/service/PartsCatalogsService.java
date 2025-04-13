package io.elasticore.blueprint.domain.parts.service;


import io.elasticore.blueprint.domain.parts.dto.*;
import io.elasticore.blueprint.domain.parts.entity.*;
import io.elasticore.blueprint.domain.parts.port.PartsCatalogAdapter;
import io.elasticore.blueprint.domain.parts.repository.PartsRepositoryHelper;
import io.elasticore.springboot3.entity.Op;
import io.elasticore.springboot3.mapper.MappingUtils;
import io.elasticore.springboot3.util.ReflectUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PartsCatalogsService {

    private final PartsCatalogAdapter partsCatalogAdapter;

    private final PartsRepositoryHelper helper;


    public List<CatalogDTO> getCatalogs() {
        List<Catalog> catalogList = helper.getCatalog().findAll();
        if (catalogList == null)
            catalogList = new ArrayList<>();
        if (catalogList.size() < 10) {
            List<Map> catalogs = partsCatalogAdapter.getCatalogs();
            for (Map item : catalogs) {
                Catalog catalog = new Catalog();
                MappingUtils.copy(item, catalog
                        , List.of("id", "name")
                        , List.of(Q.Catalog.catalogId, Q.Catalog.name));
                helper.getCatalog().save(catalog);

                catalogList.add(catalog);
            }

        }

        List<CatalogDTO> list = MappingUtils.toList(catalogList, CatalogDTO.class);
        return list;
    }

    /**
     * @param catalogId
     * @return
     */
    public List<CarModelDTO> getCarModels(String catalogId) {

        Specification<CarModel> sp = Q.CarModel.catalog().catalogId(Op.EQ, catalogId);
        Sort sort = Sort.by(Q.CarModel.getName().getAscOrder());

        List<CarModel> carModelList = helper.getCarModel().findAll(sp, sort);

        if (carModelList == null)
            carModelList = new ArrayList<>();

        if (carModelList.size() == 0) {

            Catalog catalog = new Catalog();
            catalog.setCatalogId(catalogId);

            List<PartInfo> carModelList1 = partsCatalogAdapter.getCarModelList(catalogId);
            for (PartInfo partInfo : carModelList1) {
                CarModel carModel = new CarModel();
                carModel.setCatalog(catalog);
                carModel.setId(partInfo.getId());
                carModel.setName(partInfo.getName());
                carModel.setImg(partInfo.getImg());

                helper.getCarModel().save(carModel);
                carModelList.add(carModel);
            }
        }

        List<CarModelDTO> resultList = MappingUtils.toList(carModelList, CarModelDTO.class, List.of(
                Q.CarModel.id, Q.CarModel.name, Q.CarModel.img
        ));
        return resultList;
    }


    // 3. 차량 목록 - 저장하고 재활용
    public List<CarInfoDTO> getCarInfoList(String catalogId, String modelId, Integer page) {
        Specification<CarInfo> spec = Q.CarInfo.modelId(Op.EQ, modelId);
        Sort sort = Sort.by(Q.CarInfo.getName().getAscOrder());
        List<CarInfo> list = helper.getCarInfo().findAll(spec, sort);
        if (list == null)
            list = new ArrayList<>();

        if (list.isEmpty() || list.size()<100) {
            List<Map<String, Object>> rawList = partsCatalogAdapter.getCarInfoList(catalogId, modelId, page);
            for (Map<String, Object> item : rawList) {
                CarInfo car = new CarInfo();
                MappingUtils.copy(item, car,
                        List.of("id", "name", "modelId", "modelName", "vin", "frame", "description", "criteria", "brand", "groupTreeAvailables"),
                        List.of(Q.CarInfo.id, Q.CarInfo.name, Q.CarInfo.modelId, Q.CarInfo.modelName, Q.CarInfo.vin,
                                Q.CarInfo.frame, Q.CarInfo.description, Q.CarInfo.criteria, Q.CarInfo.brand, Q.CarInfo.groupTreeAvailables));

                List<Map> parames = (List) item.get("parameters");
                if(parames!=null) {
                    List<ParamInfo> paramInfoList = new ArrayList<>();
                    car.setParameters(paramInfoList);
                    for(Map param : parames) {
                        ParamInfo paramInfo = new ParamInfo();
                        paramInfo.setCarId(car.getId());
                        MappingUtils.copy(param, paramInfo);

                        paramInfoList.add(paramInfo);
                        helper.getParamInfo().save(paramInfo);
                    }
                }

                helper.getCarInfo().save(car);
                list.add(car);
            }
        }

        return PartsMapper.toCarInfoDTOList(list);
        //return ReflectUtils.toList(list, CarInfoDTO.class);
    }

    // 4. 차량 상세 정보
    public CarInfoDTO getCarInfo(String catalogId, String carId) {
        Optional<CarInfo> optional = helper.getCarInfo().findById(carId);
        CarInfo car = null;
        if (!optional.isPresent()) {


            Map<String, Object> raw = partsCatalogAdapter.getCarInfo(catalogId, carId);
            car = new CarInfo();
            MappingUtils.copy(raw, car,
                    List.of("id", "name", "modelId", "modelName", "vin", "frame", "description", "criteria", "brand", "groupTreeAvailables"),
                    List.of(Q.CarInfo.id, Q.CarInfo.name, Q.CarInfo.modelId, Q.CarInfo.modelName, Q.CarInfo.vin,
                            Q.CarInfo.frame, Q.CarInfo.description, Q.CarInfo.criteria, Q.CarInfo.brand, Q.CarInfo.groupTreeAvailables));

            List<Map> parames = (List) raw.get("parameters");
            if(parames!=null) {
                List<ParamInfo> paramInfoList = new ArrayList<>();
                car.setParameters(paramInfoList);
                for(Map param : parames) {
                    ParamInfo paramInfo = new ParamInfo();
                    paramInfo.setCarId(car.getId());
                    MappingUtils.copy(param, paramInfo);
                    helper.getParamInfo().save(paramInfo);
                    paramInfoList.add(paramInfo);
                }
            }

            helper.getCarInfo().save(car);
        }else {
            car = optional.get();
        }

        CarInfoDTO carInfoDTO = new CarInfoDTO();
        MappingUtils.copy(car, carInfoDTO);

        return carInfoDTO;
    }

    // 5. 파라미터 정보 저장
    public List<ParamInfoDTO> getCarParams(String catalogId, String modelIf) {
        Specification<ParamInfo> spec = Q.ParamInfo.carId(Op.EQ, modelIf);
        List<ParamInfo> paramList = helper.getParamInfo().findAll(spec);

        if (paramList.isEmpty()) {
            Map<String, Object> raw = partsCatalogAdapter.getCarParams(catalogId, modelIf);
            List<Map<String, Object>> params = (List<Map<String, Object>>) raw.get("params");
            for (Map<String, Object> item : params) {
                ParamInfo param = new ParamInfo();
                MappingUtils.copy(item, param,
                        List.of("idx", "key", "name", "value", "carId", "sortOrder"),
                        List.of(Q.ParamInfo.idx, Q.ParamInfo.key, Q.ParamInfo.name, Q.ParamInfo.value,
                                Q.ParamInfo.carId, Q.ParamInfo.sortOrder));
                helper.getParamInfo().save(param);
                paramList.add(param);
            }
        }

        return MappingUtils.toList(paramList, ParamInfoDTO.class);
    }


}
