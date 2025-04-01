//ecd:-659344216H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.unitprice.controller;

import com.xyrokorea.xyroplug.domain.unitprice.dto.*;
import com.xyrokorea.xyroplug.domain.unitprice.service.*;

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

@RestController
@RequestMapping("/api/unitprice/unitPrice")
@RequiredArgsConstructor
@Tag(name = "UnitPrice (단가)", description = "API for managing 단가 entities.")
public class UnitPriceController {

    private final UnitPriceCoreService unitPriceService;


    @Operation(summary = "Create a new UnitPrice", description = "Registers a new UnitPrice in the system.")
    @PostMapping
    public UnitPriceDTO create(@Valid @RequestBody UnitPriceDTO inputDto) {
        return unitPriceService.save(inputDto);
    }


    @Operation(summary = "Retrieve a UnitPrice by ID", description = "Fetches a single UnitPrice using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UnitPrice found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UnitPriceDTO.class))}),
            @ApiResponse(responseCode = "404", description = "UnitPrice not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UnitPriceDTO> findById(@PathVariable("id") String id) {
        Optional<UnitPriceDTO> findOptional = unitPriceService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search UnitPrice data", description = "Searches for UnitPrice records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<UnitPriceDTO> search(@RequestBody UnitPriceSrchDTO searchDTO) {
        return unitPriceService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing UnitPrice", description = "Updates the details of an existing UnitPrice by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UnitPrice updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UnitPriceDTO.class))}),
            @ApiResponse(responseCode = "404", description = "UnitPrice not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<UnitPriceDTO> update(@RequestBody UnitPriceDTO updateDto) {
        UnitPriceDTO resultDto = unitPriceService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }

    @Operation(summary = "Delete multiple UnitPrice entities", description = "Deletes a list of UnitPrice entities based on their IDs provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletion result for each UnitPrice entity", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class, description = "List of booleans indicating success (true) or failure (false) for each entity"))}),
            @ApiResponse(responseCode = "404", description = "One or more UnitPrice entities not found", content = @Content)
    })
    @PostMapping("/delete")
    public ResponseEntity<List<Boolean>> delete(@RequestBody List<UnitPriceKeyDTO> delDtoList) {
        List<Boolean> result = unitPriceService.delete(delDtoList);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete a UnitPrice", description = "Deletes a UnitPrice from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        if (!unitPriceService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boolean result = unitPriceService.deleteById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
        summary = "Retrieve metadata information for UnitPriceDTO",
        description = "Fetches metadata for the UnitPriceDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(UnitPriceDTO.class));
    }
}
