//ecd:1953312190H20241223210702_V1.0
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
@RequestMapping("/api/estimate/smartSearchLog")
@RequiredArgsConstructor
@Tag(name = "SmartSearchLog (스마트 검색 로그)", description = "API for managing 스마트 검색 로그 entities.")
public class SmartSearchLogController {

    private final SmartSearchLogCoreService smartSearchLogService;


    @Operation(summary = "Create a new SmartSearchLog", description = "Registers a new SmartSearchLog in the system.")
    @PostMapping
    public SmartSearchLogDTO create(@Valid @RequestBody SmartSearchLogDTO inputDto) {
        return smartSearchLogService.save(inputDto);
    }


    @Operation(summary = "Retrieve a SmartSearchLog by ID", description = "Fetches a single SmartSearchLog using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SmartSearchLog found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SmartSearchLogDTO.class))}),
            @ApiResponse(responseCode = "404", description = "SmartSearchLog not found", content = @Content)
    })
    @GetMapping("/{logId}")
    public ResponseEntity<SmartSearchLogDTO> findById(@PathVariable("logId") Long logId) {
        Optional<SmartSearchLogDTO> findOptional = smartSearchLogService.findById(logId);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search SmartSearchLog data", description = "Searches for SmartSearchLog records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<SmartSearchLogDTO> search(@RequestBody SmartSearchLogSrchDTO searchDTO) {
        return smartSearchLogService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing SmartSearchLog", description = "Updates the details of an existing SmartSearchLog by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SmartSearchLog updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SmartSearchLogDTO.class))}),
            @ApiResponse(responseCode = "404", description = "SmartSearchLog not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<SmartSearchLogDTO> update(@Valid @RequestBody SmartSearchLogDTO updateDto) {
        SmartSearchLogDTO resultDto = smartSearchLogService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a SmartSearchLog", description = "Deletes a SmartSearchLog from the system using its unique identifier.")
    @DeleteMapping("/{logId}")
    public ResponseEntity<Void> delete(@PathVariable("logId") Long logId) {
        if (!smartSearchLogService.findById(logId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        smartSearchLogService.deleteById(logId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for SmartSearchLogDTO",
        description = "Fetches metadata for the SmartSearchLogDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(SmartSearchLogDTO.class));
    }
}
