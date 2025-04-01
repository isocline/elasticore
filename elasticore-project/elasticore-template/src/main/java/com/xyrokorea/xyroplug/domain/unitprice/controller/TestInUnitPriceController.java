//ecd:-1380899695H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.unitprice.controller;

import com.xyrokorea.xyroplug.domain.unitprice.dto.*;
import com.xyrokorea.xyroplug.domain.unitprice.service.*;

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
@RequestMapping("/api/unitprice/testInUnitPrice")
@RequiredArgsConstructor
@Tag(name = "TestInUnitPrice (단가)", description = "API for managing 단가 entities.")
public class TestInUnitPriceController {

    private final TestInUnitPriceCoreService testInUnitPriceService;


    @Operation(summary = "Create a new TestInUnitPrice", description = "Registers a new TestInUnitPrice in the system.")
    @PostMapping
    public TestInUnitPriceDTO create(@Valid @RequestBody TestInUnitPriceDTO inputDto) {
        return testInUnitPriceService.save(inputDto);
    }


    @Operation(summary = "Retrieve a TestInUnitPrice by ID", description = "Fetches a single TestInUnitPrice using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TestInUnitPrice found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TestInUnitPriceDTO.class))}),
            @ApiResponse(responseCode = "404", description = "TestInUnitPrice not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TestInUnitPriceDTO> findById(@PathVariable("id") String id) {
        Optional<TestInUnitPriceDTO> findOptional = testInUnitPriceService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search TestInUnitPrice data", description = "Searches for TestInUnitPrice records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<TestInUnitPriceDTO> search(@RequestBody TestInUnitPriceSrchDTO searchDTO) {
        return testInUnitPriceService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing TestInUnitPrice", description = "Updates the details of an existing TestInUnitPrice by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TestInUnitPrice updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TestInUnitPriceDTO.class))}),
            @ApiResponse(responseCode = "404", description = "TestInUnitPrice not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<TestInUnitPriceDTO> update(@RequestBody TestInUnitPriceDTO updateDto) {
        TestInUnitPriceDTO resultDto = testInUnitPriceService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }

    @Operation(summary = "Delete multiple TestInUnitPrice entities", description = "Deletes a list of TestInUnitPrice entities based on their IDs provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletion result for each TestInUnitPrice entity", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class, description = "List of booleans indicating success (true) or failure (false) for each entity"))}),
            @ApiResponse(responseCode = "404", description = "One or more TestInUnitPrice entities not found", content = @Content)
    })
    @PostMapping("/delete")
    public ResponseEntity<List<Boolean>> delete(@RequestBody List<TestInUnitPriceKeyDTO> delDtoList) {
        List<Boolean> result = testInUnitPriceService.delete(delDtoList);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete a TestInUnitPrice", description = "Deletes a TestInUnitPrice from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        if (!testInUnitPriceService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boolean result = testInUnitPriceService.deleteById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
        summary = "Retrieve metadata information for TestInUnitPriceDTO",
        description = "Fetches metadata for the TestInUnitPriceDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(TestInUnitPriceDTO.class));
    }
}
