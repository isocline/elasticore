//ecd:1917196915H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.channel.controller;

import com.xyrokorea.xyroplug.domain.channel.dto.*;
import com.xyrokorea.xyroplug.domain.channel.service.*;

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
@RequestMapping("/api/channel/testInChannel")
@RequiredArgsConstructor
@Tag(name = "TestInChannel (단가)", description = "API for managing 단가 entities.")
public class TestInChannelController {

    private final TestInChannelCoreService testInChannelService;


    @Operation(summary = "Create a new TestInChannel", description = "Registers a new TestInChannel in the system.")
    @PostMapping
    public TestInChannelDTO create(@Valid @RequestBody TestInChannelDTO inputDto) {
        return testInChannelService.save(inputDto);
    }


    @Operation(summary = "Retrieve a TestInChannel by ID", description = "Fetches a single TestInChannel using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TestInChannel found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TestInChannelDTO.class))}),
            @ApiResponse(responseCode = "404", description = "TestInChannel not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TestInChannelDTO> findById(@PathVariable("id") String id) {
        Optional<TestInChannelDTO> findOptional = testInChannelService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search TestInChannel data", description = "Searches for TestInChannel records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<TestInChannelDTO> search(@RequestBody TestInChannelSrchDTO searchDTO) {
        return testInChannelService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing TestInChannel", description = "Updates the details of an existing TestInChannel by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TestInChannel updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TestInChannelDTO.class))}),
            @ApiResponse(responseCode = "404", description = "TestInChannel not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<TestInChannelDTO> update(@RequestBody TestInChannelDTO updateDto) {
        TestInChannelDTO resultDto = testInChannelService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }

    @Operation(summary = "Delete multiple TestInChannel entities", description = "Deletes a list of TestInChannel entities based on their IDs provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletion result for each TestInChannel entity", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class, description = "List of booleans indicating success (true) or failure (false) for each entity"))}),
            @ApiResponse(responseCode = "404", description = "One or more TestInChannel entities not found", content = @Content)
    })
    @PostMapping("/delete")
    public ResponseEntity<List<Boolean>> delete(@RequestBody List<TestInChannelKeyDTO> delDtoList) {
        List<Boolean> result = testInChannelService.delete(delDtoList);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete a TestInChannel", description = "Deletes a TestInChannel from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        if (!testInChannelService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boolean result = testInChannelService.deleteById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
        summary = "Retrieve metadata information for TestInChannelDTO",
        description = "Fetches metadata for the TestInChannelDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(TestInChannelDTO.class));
    }
}
