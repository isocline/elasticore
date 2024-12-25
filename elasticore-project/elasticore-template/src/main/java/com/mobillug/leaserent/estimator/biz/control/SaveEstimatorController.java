//ecd:-1643318741H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.control;

import com.mobillug.leaserent.estimator.biz.dto.*;
import com.mobillug.leaserent.estimator.biz.service.*;

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
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mobillug.leaserent.estimator.common.utils.DTOUtils;

@RestController
@RequestMapping("/api/estimate/saveEstimator")
@RequiredArgsConstructor
@Tag(name = "SaveEstimator (견적 저장 엔티티)", description = "API for managing 견적 저장 엔티티 entities.")
public class SaveEstimatorController {

    private final SaveEstimatorCoreService saveEstimatorService;


    @Operation(summary = "Create a new SaveEstimator", description = "Registers a new SaveEstimator in the system.")
    @PostMapping
    public SaveEstimatorDTO create(@Valid @RequestBody SaveEstimatorDTO inputDto) {
        return saveEstimatorService.save(inputDto);
    }


    @Operation(summary = "Retrieve a SaveEstimator by ID", description = "Fetches a single SaveEstimator using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SaveEstimator found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SaveEstimatorDTO.class))}),
            @ApiResponse(responseCode = "404", description = "SaveEstimator not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<SaveEstimatorDTO> findById(@PathVariable("id") String id) {
        Optional<SaveEstimatorDTO> findOptional = saveEstimatorService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search SaveEstimator data", description = "Searches for SaveEstimator records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public Page<SaveEstimatorDTO> search(@RequestBody SaveEstimatorSrchDTO searchDTO) {
        return saveEstimatorService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing SaveEstimator", description = "Updates the details of an existing SaveEstimator by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SaveEstimator updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SaveEstimatorDTO.class))}),
            @ApiResponse(responseCode = "404", description = "SaveEstimator not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<SaveEstimatorDTO> update(@Valid @RequestBody SaveEstimatorDTO updateDto) {
        SaveEstimatorDTO resultDto = saveEstimatorService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a SaveEstimator", description = "Deletes a SaveEstimator from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!saveEstimatorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        saveEstimatorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for SaveEstimatorDTO",
        description = "Fetches metadata for the SaveEstimatorDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(SaveEstimatorDTO.class));
    }
}
