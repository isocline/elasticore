//ecd:-1097574608H20241213011350_V1.0
package com.test.A1.control;

import com.test.A1.dto.*;
import com.test.A1.service.*;

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
@RequestMapping("/api/A1/company")
@RequiredArgsConstructor
@Tag(name = "Company (company)", description = "API for managing company entities.")
public class CompanyController {

    private final CompanyCoreService companyService;


    @Operation(summary = "Create a new Company", description = "Registers a new Company in the system.")
    @PostMapping
    public CompanyDTO create(@Valid @RequestBody CompanyDTO inputDto) {
        return companyService.save(inputDto);
    }


    @Operation(summary = "Retrieve a Company by ID", description = "Fetches a single Company using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable("id") String id) {
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
    @PutMapping
    public ResponseEntity<CompanyDTO> update(@Valid @RequestBody CompanyDTO updateDto) {
        CompanyDTO resultDto = companyService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }

    @Operation(summary = "Delete multiple Company entities", description = "Deletes a list of Company entities based on their IDs provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletion result for each Company entity", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class, description = "List of booleans indicating success (true) or failure (false) for each entity"))}),
            @ApiResponse(responseCode = "404", description = "One or more Company entities not found", content = @Content)
    })
    @PostMapping("/delete")
    public ResponseEntity<List<Boolean>> delete(@RequestBody List<CompanyDTO> delDtoList) {
        List<Boolean> result = companyService.delete(delDtoList);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete a Company", description = "Deletes a Company from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        if (!companyService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boolean result = companyService.deleteById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
        summary = "Retrieve metadata information for CompanyDTO",
        description = "Fetches metadata for the CompanyDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(CompanyDTO.class));
    }
}
