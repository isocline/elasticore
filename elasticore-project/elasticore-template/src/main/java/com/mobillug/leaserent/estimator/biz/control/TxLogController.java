//ecd:69888643H20241223210702_V1.0
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
@RequestMapping("/api/estimate/txLog")
@RequiredArgsConstructor
@Tag(name = "TxLog (Transaction 로그)", description = "API for managing Transaction 로그 entities.")
public class TxLogController {

    private final TxLogCoreService txLogService;


    @Operation(summary = "Create a new TxLog", description = "Registers a new TxLog in the system.")
    @PostMapping
    public TxLogDTO create(@Valid @RequestBody TxLogDTO inputDto) {
        return txLogService.save(inputDto);
    }


    @Operation(summary = "Retrieve a TxLog by ID", description = "Fetches a single TxLog using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TxLog found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TxLogDTO.class))}),
            @ApiResponse(responseCode = "404", description = "TxLog not found", content = @Content)
    })
    @GetMapping("/{txId}")
    public ResponseEntity<TxLogDTO> findById(@PathVariable("txId") String txId) {
        Optional<TxLogDTO> findOptional = txLogService.findById(txId);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search TxLog data", description = "Searches for TxLog records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<TxLogDTO> search(@RequestBody TxLogSrchDTO searchDTO) {
        return txLogService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing TxLog", description = "Updates the details of an existing TxLog by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TxLog updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TxLogDTO.class))}),
            @ApiResponse(responseCode = "404", description = "TxLog not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<TxLogDTO> update(@Valid @RequestBody TxLogDTO updateDto) {
        TxLogDTO resultDto = txLogService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a TxLog", description = "Deletes a TxLog from the system using its unique identifier.")
    @DeleteMapping("/{txId}")
    public ResponseEntity<Void> delete(@PathVariable("txId") String txId) {
        if (!txLogService.findById(txId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        txLogService.deleteById(txId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for TxLogDTO",
        description = "Fetches metadata for the TxLogDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(TxLogDTO.class));
    }
}
