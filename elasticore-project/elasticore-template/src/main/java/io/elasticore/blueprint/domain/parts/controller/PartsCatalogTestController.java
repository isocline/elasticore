package io.elasticore.blueprint.domain.parts.controller;


import io.elasticore.blueprint.domain.parts.dto.*;
import io.elasticore.blueprint.domain.parts.entity.*;
import io.elasticore.blueprint.domain.parts.port.CarInfoPortService;
import io.elasticore.blueprint.domain.parts.port.PartsCatalogAdapter;
import io.elasticore.blueprint.domain.parts.service.CarModelService;
import io.elasticore.blueprint.domain.parts.service.PartsCatalogsService;
import io.elasticore.springboot3.dbms.DbmsSqlExecutor;
import io.elasticore.springboot3.dbms.meta.DbmsServiceImpl;
import io.elasticore.springboot3.mapper.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parts/catalog")
@RequiredArgsConstructor
@Tag(name = "Parts Catalog API", description = "API for external parts catalog integration")
public class PartsCatalogTestController {

    private final PartsCatalogsService partsCatalogsService;

    private final PartsCatalogAdapter partsCatalogAdapter;

    private final CarModelService carModelService;

    private final CarInfoPortService carInfoPortService;

    private final DbmsSqlExecutor executor;


    @PostMapping("test/test")

    public ResponseEntity<List> test(@RequestBody Map body) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        DbmsServiceImpl dbmsService = new DbmsServiceImpl("parts.CarInfoPortService", "main", "blueprint/parts/port/dbms.yml");
        List<HashMap> findByBrand = executor.inqueryList(dbmsService, "findByBrand", body, HashMap.class);


        return ResponseEntity.ok(findByBrand);
    }


    @Tag(name = "1. Catalogs")
    @GetMapping("/catalogs")
    @Operation(summary = "Get car models by catalog", description = "Retrieve car model list from the specified catalog")
    public ResponseEntity<List<CatalogDTO>> getCatalogs() {
        List<CatalogDTO> resultList = partsCatalogsService.getCatalogs();
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/TESTTEST33/123")
    public void test() {
        String carId = "3e042c85141bdc7ef1d83556e58e749f";
        String grpId = "MCPwn5qANTFfQTc4NvCfkIlKUEcvNTA0ODIzLnBuZw";
        PartGroupInfoDTO resp = partsCatalogsService.getParts("bmw", carId, grpId, null);
        System.out.println(resp);
    }


    @Tag(name = "2. Cars")
    @GetMapping("/TESTTEST3/{catalogId}/parts")
    @Operation(summary = "Get car models by catalog", description = "Retrieve car model list from the specified catalog")
    public ResponseEntity<Integer> getCarParts(@PathVariable("catalogId") String catalogId) {
        List<CarModelDTO> resultList = partsCatalogsService.getCarModels(catalogId);

        List<CarPartDTO> result = new ArrayList<>();

        List<CarInfoDTO> respList = new ArrayList<>();;
        for(CarModelDTO carModelDTO : resultList) {
            String modelId = carModelDTO.getId();

            List<CarInfoDTO> list2 = partsCatalogsService.getCarInfoList(catalogId, modelId, 0);

            for(CarInfoDTO carInfoDTO : list2) {
                String cardId = carInfoDTO.getId();

                CarInfoDTO item = partsCatalogsService.getCarInfo(catalogId, cardId);

                System.err.println(item);

                respList.add(item);



                findParts(catalogId, cardId, null,null, result);
            }


        }
        return ResponseEntity.ok(result.size());
    }



    protected void findParts(String catalogId , String carId, String groupId, String criteria, List<CarPartDTO> result) {
        List<PartGroupDTO> catalogGroups = partsCatalogsService.getCatalogGroups(catalogId, carId, groupId, criteria);
        for(PartGroupDTO partGroupDTO : catalogGroups) {
            boolean hasSubGrp = partGroupDTO.getHasSubgroups();
            String grpId = partGroupDTO.getId();
            if(!hasSubGrp) {



                PartGroupInfoDTO resp = partsCatalogsService.getParts(catalogId, carId, grpId, criteria);
                List<PartGroupDTO> partGroups = resp.getPartGroups();

                try {
                    for (PartGroupDTO pGrp : partGroups) {
                        List<CarPartDTO> parts = pGrp.getParts();
                        for (CarPartDTO carPart : parts) {
                            result.add(carPart);

                            if (result.size() > 20) {
                                return;
                            }
                        }
                    }
                }catch(ClassCastException e) {
                    e.printStackTrace();
                    System.err.println(e);
                }


            }
            else {
                findParts(catalogId,carId,grpId, criteria, result);
            }
        }

    }




    @Tag(name = "2. Cars")
    @GetMapping("/{catalogId}/models")
    @Operation(summary = "Get car models by catalog", description = "Retrieve car model list from the specified catalog")
    public ResponseEntity<List<CarModelDTO>> getCarModels(@PathVariable("catalogId") String catalogId) {
        List<CarModelDTO> resultList = partsCatalogsService.getCarModels(catalogId);
        return ResponseEntity.ok(resultList);
    }

    @Tag(name = "2. Cars")
    @GetMapping("/{catalogId}/models/{modelId}/cars")
    @Operation(summary = "Get cars by catalog and model", description = "Retrieve cars under specific catalog and model")
    public ResponseEntity<List<?>> getCarInfoList(
            @PathVariable("catalogId") String catalogId,
            @PathVariable("modelId") String modelId,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {

        List<CarInfoDTO> resultList = partsCatalogsService.getCarInfoList(catalogId, modelId, page);
        return ResponseEntity.ok(resultList);
    }


    @Tag(name = "2. Cars")
    @GetMapping("/{catalogId}/cars/{carId}")
    @Operation(summary = "Get car detail", description = "Retrieve car info by catalog and car ID")
    public ResponseEntity<CarInfoDTO> getCarInfo(
            @PathVariable("catalogId") String catalogId,
            @PathVariable("carId") String carId) {
        CarInfoDTO item = partsCatalogsService.getCarInfo(catalogId, carId);
        return ResponseEntity.ok(item);
    }

    @Tag(name = "2. Cars")
    @GetMapping("/{catalogId}/parameters")
    @Operation(summary = "Get car parameters", description = "Retrieve parameters for a given catalog and modelIf")
    public ResponseEntity<List> getCarParams(
            @PathVariable("catalogId") String catalogId,
            @RequestParam("modelIf") String modelIf) {
        List<ParamInfoDTO> resp = partsCatalogsService.getCarParams(catalogId, modelIf ,null);

        return ResponseEntity.ok(resp);
    }

    @Tag(name = "2. Cars")
    @GetMapping("/vin")
    @Operation(summary = "Get car info by VIN", description = "Search car info by VIN number")
    public ResponseEntity<List<?>> getCarInfoByVIN(@RequestParam("q") String vin) {

        List<CarProfileInfoDTO> result = partsCatalogsService.findVin(vin);
        return ResponseEntity.ok(result);
    }

    @Tag(name = "3. Groups")
    @GetMapping("/{catalogId}/groups")
    @Operation(summary = "Get catalog groups", description = "Retrieve groups in a catalog")
    public ResponseEntity<List<?>> getCatalogGroups(@PathVariable("catalogId") String catalogId
            , @RequestParam("carId") String carId
            , @RequestParam(name = "groupId", required = false) String groupId
            , @RequestParam(name = "criteria", required = false) String criteria
    ) {
        List resp = partsCatalogsService.getCatalogGroups(catalogId, carId, groupId, criteria);

        return ResponseEntity.ok(resp);
    }

    @Tag(name = "3. Groups")
    @GetMapping("/{catalogId}/groups/suggest")
    @Operation(summary = "Get suggested catalog groups", description = "Retrieve suggested groups for catalog")
    public ResponseEntity<List<?>> getSuggestedGroups(@PathVariable("catalogId") String catalogId
            , @RequestParam("q") String q
    ) {
        List resp = partsCatalogsService.getSuggestedGroupsString(catalogId, q);
        return ResponseEntity.ok(resp);
    }

    @Tag(name = "4. Parts")
    @GetMapping("/{catalogId}/parts")
    @Operation(summary = "Get parts list", description = "Retrieve parts in the catalog")
    public ResponseEntity<PartGroupInfoDTO> getParts(@PathVariable("catalogId") String catalogId
            , @RequestParam("carId") String carId
            , @RequestParam("groupId") String groupId
            , @RequestParam(name = "criteria", required = false) String criteria

    ) {
        PartGroupInfoDTO resp = partsCatalogsService.getParts(catalogId, carId, groupId, criteria);
        return ResponseEntity.ok(resp);
    }

    @Tag(name = "4. Parts")
    @GetMapping("/{catalogId}/groups/tree")
    @Operation(summary = "Get group tree", description = "Retrieve hierarchical group tree of catalog")
    public ResponseEntity<List<?>> getGroupTree(@PathVariable("catalogId") String catalogId
            , @RequestParam("carId") String carId
            , @RequestParam("criteria") String criteria
            , @RequestParam("cached") Boolean cached
    ) {
        List resp = partsCatalogsService.getGroupTree(catalogId, carId, criteria, cached);
        return ResponseEntity.ok(resp);
    }

    //java.util.Map getSchemas(String carId,String catalogId,String branchId,String criteria,Integer page,String partNameIds,String partName);

    @Tag(name = "5. Groups tree")
    @GetMapping("/{catalogId}/schemas")
    @Operation(summary = "Get schema definitions", description = "Retrieve schema info for catalog")
    public ResponseEntity<Object> getSchemas(@PathVariable("catalogId") String catalogId
            , @RequestParam(name = "carId") String carId
            , @RequestParam(name = "branchId", required = false) String branchId
            , @RequestParam(name = "criteria", required = false) String criteria
            , @RequestParam(name = "page", defaultValue = "0") int page
            , @RequestParam(name = "partNameIds", required = false) String partNameIds
            , @RequestParam(name = "partName", required = false) String partName

    ) {
        CatalogSchemaDTO resp = partsCatalogsService.getSchemas(catalogId, carId, branchId, criteria, page, partNameIds, partName);
        return ResponseEntity.ok(resp);

        //Object object = Mapper.of(resp, CatalogSchemaDTO.class).toObject();
        //return ResponseEntity.ok(object);
    }

}