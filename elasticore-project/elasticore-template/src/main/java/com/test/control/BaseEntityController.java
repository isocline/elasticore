//ecd:2006072865H20241219144053_V1.0
package com.test.control;

import com.test.dto.*;
import com.test.service.*;

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
@RequestMapping("/api/A0/baseEntity")
@RequiredArgsConstructor
@Tag(name = "BaseEntity (baseEntity)", description = "API for managing baseEntity entities.")
public class BaseEntityController {

    private final BaseEntityCoreService baseEntityService;


    @Operation(summary = "Create a new BaseEntity", description = "Registers a new BaseEntity in the system.")
    @PostMapping
    public BaseEntityDTO create(@Valid @RequestBody BaseEntityDTO inputDto) {
        return baseEntityService.save(inputDto);
    }


    @Operation(summary = "Retrieve a BaseEntity by ID", description = "Fetches a single BaseEntity using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BaseEntity found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseEntityDTO.class))}),
            @ApiResponse(responseCode = "404", description = "BaseEntity not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseEntityDTO> findById(@PathVariable("id") String id) {
        Optional<BaseEntityDTO> findOptional = baseEntityService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search BaseEntity data", description = "Searches for BaseEntity records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<BaseEntityDTO> search(@RequestBody BaseEntitySrchDTO searchDTO) {
        return baseEntityService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing BaseEntity", description = "Updates the details of an existing BaseEntity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BaseEntity updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseEntityDTO.class))}),
            @ApiResponse(responseCode = "404", description = "BaseEntity not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<BaseEntityDTO> update(@Valid @RequestBody BaseEntityDTO updateDto) {
        BaseEntityDTO resultDto = baseEntityService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a BaseEntity", description = "Deletes a BaseEntity from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!baseEntityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        baseEntityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for BaseEntityDTO",
        description = "Fetches metadata for the BaseEntityDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(BaseEntityDTO.class));
    }
}
