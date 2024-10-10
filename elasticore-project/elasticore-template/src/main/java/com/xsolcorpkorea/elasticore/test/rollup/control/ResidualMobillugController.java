//ecd:-441126547H20241010182543_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.control;

import com.xsolcorpkorea.elasticore.test.rollup.dto.*;
import com.xsolcorpkorea.elasticore.test.rollup.service.*;

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
import java.util.Optional;

@RestController
@RequestMapping("/api/rollup2/residualMobillug")
@RequiredArgsConstructor
@Tag(name = "ResidualMobillug (모빌러그 잔가)", description = "API for managing 모빌러그 잔가 entities.")
public class ResidualMobillugController {

    private final ResidualMobillugCoreService residualMobillugService;


    @Operation(summary = "Create a new ResidualMobillug", description = "Registers a new ResidualMobillug in the system.")
    @PostMapping
    public ResidualMobillugDTO create(@RequestBody ResidualMobillugDTO inputDto) {
        return residualMobillugService.save(inputDto);
    }


    @Operation(summary = "Retrieve a ResidualMobillug by ID", description = "Fetches a single ResidualMobillug using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ResidualMobillug found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResidualMobillugDTO.class))}),
            @ApiResponse(responseCode = "404", description = "ResidualMobillug not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResidualMobillugDTO> findById(@PathVariable("id") String id) {
        Optional<ResidualMobillugDTO> findOptional = residualMobillugService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search ResidualMobillug data", description = "Searches for ResidualMobillug records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<ResidualMobillugDTO> search(@RequestBody ResidualMobillugSrchDTO searchDTO) {
        return residualMobillugService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing ResidualMobillug", description = "Updates the details of an existing ResidualMobillug by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ResidualMobillug updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResidualMobillugDTO.class))}),
            @ApiResponse(responseCode = "404", description = "ResidualMobillug not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<ResidualMobillugDTO> update(@RequestBody ResidualMobillugDTO updateDto) {
        ResidualMobillugDTO resultDto = residualMobillugService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a ResidualMobillug", description = "Deletes a ResidualMobillug from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!residualMobillugService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        residualMobillugService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
