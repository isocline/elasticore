//ecd:-1266990638H20240924235119_V1.0
package com.xsolcorpkorea.elasticore.test.dto.control;

import com.xsolcorpkorea.elasticore.test.dto.dto.*;
import com.xsolcorpkorea.elasticore.test.dto.service.*;

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
@RequestMapping("/api/dto/company")
@RequiredArgsConstructor
@Tag(name = "Company (company)", description = "API for managing company entities.")
public class CompanyController {

    private final CompanyCoreService companyService;


    @Operation(summary = "Create a new Company", description = "Registers a new Company in the system.")
    @PostMapping
    public CompanyDTO create(@RequestBody CompanyDTO inputDto) {
        return companyService.save(inputDto);
    }


    @Operation(summary = "Retrieve a Company by ID", description = "Fetches a single Company using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable("id") Long id) {
        Optional<CompanyDTO> findOptional = companyService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search Company data", description = "Searches for Company records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<CompanyDTO> search(@RequestBody CompanySrchDTO searchDTO) {
        return companyService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing Company", description = "Updates the details of an existing Company by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update(@PathVariable("id") Long id, @RequestBody CompanyDTO updateDto) {
        CompanyDTO resultDto = companyService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a Company", description = "Deletes a Company from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!companyService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
