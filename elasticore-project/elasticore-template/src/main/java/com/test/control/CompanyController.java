//ecd:-1651652841H20250204014854_V1.0
package com.test.control;

import com.test.dto.*;
import com.test.service.*;

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
@RequestMapping("/api/A0/company")
@RequiredArgsConstructor
@Tag(name = "Company (회사)", description = "API for managing 회사 entities.")
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
    @GetMapping("/{cid}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable("cid") String cid) {
        Optional<CompanyDTO> findOptional = companyService.findById(cid);
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
    public ResponseEntity<CompanyDTO> update(@RequestBody CompanyDTO updateDto) {
        CompanyDTO resultDto = companyService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }

    @Operation(summary = "Delete multiple Company entities", description = "Deletes a list of Company entities based on their IDs provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletion result for each Company entity", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class, description = "List of booleans indicating success (true) or failure (false) for each entity"))}),
            @ApiResponse(responseCode = "404", description = "One or more Company entities not found", content = @Content)
    })
    @PostMapping("/delete")
    public ResponseEntity<List<Boolean>> delete(@RequestBody List<CompanyKeyDTO> delDtoList) {
        List<Boolean> result = companyService.delete(delDtoList);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete a Company", description = "Deletes a Company from the system using its unique identifier.")
    @DeleteMapping("/{cid}")
    public ResponseEntity<Boolean> delete(@PathVariable("cid") String cid) {
        if (!companyService.findById(cid).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boolean result = companyService.deleteById(cid);
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
