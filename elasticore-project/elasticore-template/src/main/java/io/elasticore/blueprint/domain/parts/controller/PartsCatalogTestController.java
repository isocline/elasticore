package io.elasticore.blueprint.domain.parts.controller;


import io.elasticore.blueprint.domain.parts.dto.*;
import io.elasticore.blueprint.domain.parts.entity.*;
import io.elasticore.blueprint.domain.parts.port.PartsCatalogAdapter;
import io.elasticore.blueprint.domain.parts.service.CarModelService;
import io.elasticore.blueprint.domain.parts.service.PartsCatalogsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Tag(name = "1. Catalogs")
    @GetMapping("/catalogs")
    @Operation(summary = "Get car models by catalog", description = "Retrieve car model list from the specified catalog")
    public ResponseEntity<List<CatalogDTO>> getCatalogs() {
        List<CatalogDTO> resultList = partsCatalogsService.getCatalogs();
        return ResponseEntity.ok(resultList);
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
            @RequestParam(name = "page", defaultValue = "1") Integer page) {

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
    public ResponseEntity<Map<String, Object>> getCarParams(
            @PathVariable("catalogId") String catalogId,
            @RequestParam("modelIf") String modelIf) {
        return ResponseEntity.ok(partsCatalogAdapter.getCarParams(catalogId, modelIf));
    }

    @Tag(name = "2. Cars")
    @GetMapping("/vin")
    @Operation(summary = "Get car info by VIN", description = "Search car info by VIN number")
    public ResponseEntity<List<?>> getCarInfoByVIN(@RequestParam("q") String vin) {

        List<CarProfileInfoDTO> result =partsCatalogsService.findVin(vin);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{catalogId}/groups")
    @Operation(summary = "Get catalog groups", description = "Retrieve groups in a catalog")
    public ResponseEntity<List<?>> getCatalogGroups(@PathVariable("catalogId") String catalogId) {
        return ResponseEntity.ok(partsCatalogAdapter.getCatalogGroupList(catalogId));
    }

    @GetMapping("/{catalogId}/groups/suggest")
    @Operation(summary = "Get suggested catalog groups", description = "Retrieve suggested groups for catalog")
    public ResponseEntity<List<?>> getSuggestedGroups(@PathVariable("catalogId") String catalogId) {
        return ResponseEntity.ok(partsCatalogAdapter.getCatalogGroupSuggestList(catalogId));
    }

    @GetMapping("/{catalogId}/parts")
    @Operation(summary = "Get parts list", description = "Retrieve parts in the catalog")
    public ResponseEntity<List<?>> getParts(@PathVariable("catalogId") String catalogId) {
        return ResponseEntity.ok(partsCatalogAdapter.getParts(catalogId));
    }

    @GetMapping("/{catalogId}/groups/tree")
    @Operation(summary = "Get group tree", description = "Retrieve hierarchical group tree of catalog")
    public ResponseEntity<List<?>> getGroupTree(@PathVariable("catalogId") String catalogId) {
        return ResponseEntity.ok(partsCatalogAdapter.getGroupstree(catalogId));
    }

    @GetMapping("/{catalogId}/schemas")
    @Operation(summary = "Get schema definitions", description = "Retrieve schema info for catalog")
    public ResponseEntity<Map<String, Object>> getSchemas(@PathVariable("catalogId") String catalogId) {
        return ResponseEntity.ok(partsCatalogAdapter.getSchemas(catalogId));
    }

}