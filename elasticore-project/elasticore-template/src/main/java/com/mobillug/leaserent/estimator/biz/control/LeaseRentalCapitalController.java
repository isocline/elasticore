//ecd:1339626391H20241223210702_V1.0
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
@RequestMapping("/api/estimate/leaseRentalCapital")
@RequiredArgsConstructor
@Tag(name = "LeaseRentalCapital (캐피탈사)", description = "API for managing 캐피탈사 entities.")
public class LeaseRentalCapitalController {

    private final LeaseRentalCapitalCoreService leaseRentalCapitalService;


    @Operation(summary = "Create a new LeaseRentalCapital", description = "Registers a new LeaseRentalCapital in the system.")
    @PostMapping
    public LeaseRentalCapitalDTO create(@Valid @RequestBody LeaseRentalCapitalDTO inputDto) {
        return leaseRentalCapitalService.save(inputDto);
    }


    @Operation(summary = "Retrieve a LeaseRentalCapital by ID", description = "Fetches a single LeaseRentalCapital using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "LeaseRentalCapital found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LeaseRentalCapitalDTO.class))}),
            @ApiResponse(responseCode = "404", description = "LeaseRentalCapital not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<LeaseRentalCapitalDTO> findById(@PathVariable("id") String id) {
        Optional<LeaseRentalCapitalDTO> findOptional = leaseRentalCapitalService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search LeaseRentalCapital data", description = "Searches for LeaseRentalCapital records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<LeaseRentalCapitalDTO> search(@RequestBody LeaseRentalCapitalSrchDTO searchDTO) {
        return leaseRentalCapitalService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing LeaseRentalCapital", description = "Updates the details of an existing LeaseRentalCapital by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "LeaseRentalCapital updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LeaseRentalCapitalDTO.class))}),
            @ApiResponse(responseCode = "404", description = "LeaseRentalCapital not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<LeaseRentalCapitalDTO> update(@Valid @RequestBody LeaseRentalCapitalDTO updateDto) {
        LeaseRentalCapitalDTO resultDto = leaseRentalCapitalService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a LeaseRentalCapital", description = "Deletes a LeaseRentalCapital from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!leaseRentalCapitalService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        leaseRentalCapitalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for LeaseRentalCapitalDTO",
        description = "Fetches metadata for the LeaseRentalCapitalDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(LeaseRentalCapitalDTO.class));
    }
}
