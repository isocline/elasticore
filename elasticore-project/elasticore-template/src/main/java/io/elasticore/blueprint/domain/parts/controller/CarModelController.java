//ecd:-915653338H20250425111000_V1.0
// Initially generated by ElastiCORE; no longer managed—do not remove this line.
package io.elasticore.blueprint.domain.parts.controller;

import io.elasticore.blueprint.domain.parts.dto.*;
import io.elasticore.blueprint.domain.parts.service.*;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.elasticore.runtime.utils.DTOUtils;

/**
 * REST API controller for managing CarModel entities.
 *
 * Initially generated by ElastiCORE — a model-driven platform for
 * rapid Spring Boot service scaffolding.
 *
 * No longer managed by ElastiCORE. You may modify as needed,
 * but retain the marker for traceability.
 */
@RestController
@RequestMapping("/api/parts/carModel")
@RequiredArgsConstructor
@Tag(name = "CarModel (차량 모델 정보)", description = "API for managing 차량 모델 정보 entities.")
public class CarModelController {

    private final CarModelService carModelService;


    @Operation(summary = "Create a new CarModel", description = "Registers a new CarModel in the system.")
    @PostMapping
    public CarModelDTO create(@Valid @RequestBody CarModelDTO inputDto) {
        return carModelService.save(inputDto);
    }


    @Operation(summary = "Retrieve a CarModel by ID", description = "Fetches a single CarModel using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarModel found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CarModelDTO.class))}),
            @ApiResponse(responseCode = "404", description = "CarModel not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarModelDTO> findById(@PathVariable("id") String id) {
        Optional<CarModelDTO> findOptional = carModelService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search CarModel data", description = "Searches for CarModel records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public Page<CarModelDTO> search(@RequestBody CarModelSrchDTO searchDTO) {
        return carModelService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing CarModel", description = "Updates the details of an existing CarModel by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarModel updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CarModelDTO.class))}),
            @ApiResponse(responseCode = "404", description = "CarModel not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<CarModelDTO> update(@RequestBody CarModelDTO updateDto) {
        CarModelDTO resultDto = carModelService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }

    @Operation(summary = "Delete multiple CarModel entities", description = "Deletes a list of CarModel entities based on their IDs provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletion result for each CarModel entity", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class, description = "List of booleans indicating success (true) or failure (false) for each entity"))}),
            @ApiResponse(responseCode = "404", description = "One or more CarModel entities not found", content = @Content)
    })
    @PostMapping("/delete")
    public ResponseEntity<List<Boolean>> delete(@RequestBody List<CarModelKeyDTO> delDtoList) {
        List<Boolean> result = carModelService.delete(delDtoList);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete a CarModel", description = "Deletes a CarModel from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        if (!carModelService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boolean result = carModelService.deleteById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
        summary = "Retrieve metadata information for CarModelDTO",
        description = "Fetches metadata for the CarModelDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(CarModelDTO.class));
    }
}
