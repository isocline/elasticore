//ecd:-531854061H20241223210702_V1.0
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
@RequestMapping("/api/estimate/transCity")
@RequiredArgsConstructor
@Tag(name = "TransCity (탁송 도시)", description = "API for managing 탁송 도시 entities.")
public class TransCityController {

    private final TransCityCoreService transCityService;


    @Operation(summary = "Create a new TransCity", description = "Registers a new TransCity in the system.")
    @PostMapping
    public TransCityDTO create(@Valid @RequestBody TransCityDTO inputDto) {
        return transCityService.save(inputDto);
    }


    @Operation(summary = "Retrieve a TransCity by ID", description = "Fetches a single TransCity using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TransCity found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TransCityDTO.class))}),
            @ApiResponse(responseCode = "404", description = "TransCity not found", content = @Content)
    })
    @GetMapping("/{seq}")
    public ResponseEntity<TransCityDTO> findById(@PathVariable("seq") Long seq) {
        Optional<TransCityDTO> findOptional = transCityService.findById(seq);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search TransCity data", description = "Searches for TransCity records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<TransCityResultDTO> search(@RequestBody TransCitySrchDTO searchDTO) {
        return transCityService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing TransCity", description = "Updates the details of an existing TransCity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TransCity updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TransCityDTO.class))}),
            @ApiResponse(responseCode = "404", description = "TransCity not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<TransCityDTO> update(@Valid @RequestBody TransCityDTO updateDto) {
        TransCityDTO resultDto = transCityService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a TransCity", description = "Deletes a TransCity from the system using its unique identifier.")
    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> delete(@PathVariable("seq") Long seq) {
        if (!transCityService.findById(seq).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        transCityService.deleteById(seq);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for TransCityDTO",
        description = "Fetches metadata for the TransCityDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(TransCityDTO.class));
    }
}
