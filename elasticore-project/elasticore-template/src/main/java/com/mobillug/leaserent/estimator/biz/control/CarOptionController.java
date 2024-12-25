//ecd:1723818266H20241223210702_V1.0
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
@RequestMapping("/api/estimate/carOption")
@RequiredArgsConstructor
@Tag(name = "CarOption (차량 옵션)", description = "API for managing 차량 옵션 entities.")
public class CarOptionController {

    private final CarOptionCoreService carOptionService;


    @Operation(summary = "Create a new CarOption", description = "Registers a new CarOption in the system.")
    @PostMapping
    public CarOptionDTO create(@Valid @RequestBody CarOptionDTO inputDto) {
        return carOptionService.save(inputDto);
    }


    @Operation(summary = "Retrieve a CarOption by ID", description = "Fetches a single CarOption using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarOption found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CarOptionDTO.class))}),
            @ApiResponse(responseCode = "404", description = "CarOption not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarOptionDTO> findById(@PathVariable("id") String id) {
        Optional<CarOptionDTO> findOptional = carOptionService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search CarOption data", description = "Searches for CarOption records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<CarOptionDTO> search(@RequestBody CarOptionSrchDTO searchDTO) {
        return carOptionService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing CarOption", description = "Updates the details of an existing CarOption by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarOption updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CarOptionDTO.class))}),
            @ApiResponse(responseCode = "404", description = "CarOption not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<CarOptionDTO> update(@Valid @RequestBody CarOptionDTO updateDto) {
        CarOptionDTO resultDto = carOptionService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a CarOption", description = "Deletes a CarOption from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!carOptionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        carOptionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for CarOptionDTO",
        description = "Fetches metadata for the CarOptionDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(CarOptionDTO.class));
    }
}
