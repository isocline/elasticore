//ecd:256104669H20241223210702_V1.0
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
@RequestMapping("/api/estimate/transArea")
@RequiredArgsConstructor
@Tag(name = "TransArea (탁송 지역)", description = "API for managing 탁송 지역 entities.")
public class TransAreaController {

    private final TransAreaCoreService transAreaService;


    @Operation(summary = "Create a new TransArea", description = "Registers a new TransArea in the system.")
    @PostMapping
    public TransAreaDTO create(@Valid @RequestBody TransAreaDTO inputDto) {
        return transAreaService.save(inputDto);
    }


    @Operation(summary = "Retrieve a TransArea by ID", description = "Fetches a single TransArea using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TransArea found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TransAreaDTO.class))}),
            @ApiResponse(responseCode = "404", description = "TransArea not found", content = @Content)
    })
    @GetMapping("/{seq}")
    public ResponseEntity<TransAreaDTO> findById(@PathVariable("seq") Long seq) {
        Optional<TransAreaDTO> findOptional = transAreaService.findById(seq);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search TransArea data", description = "Searches for TransArea records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<TransAreaResultDTO> search(@RequestBody TransAreaSrchDTO searchDTO) {
        return transAreaService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing TransArea", description = "Updates the details of an existing TransArea by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TransArea updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TransAreaDTO.class))}),
            @ApiResponse(responseCode = "404", description = "TransArea not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<TransAreaDTO> update(@Valid @RequestBody TransAreaDTO updateDto) {
        TransAreaDTO resultDto = transAreaService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a TransArea", description = "Deletes a TransArea from the system using its unique identifier.")
    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> delete(@PathVariable("seq") Long seq) {
        if (!transAreaService.findById(seq).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        transAreaService.deleteById(seq);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for TransAreaDTO",
        description = "Fetches metadata for the TransAreaDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(TransAreaDTO.class));
    }
}
