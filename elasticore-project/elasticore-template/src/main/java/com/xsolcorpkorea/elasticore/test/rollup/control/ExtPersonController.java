//ecd:1074033523H20241014191354_V1.0
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
@RequestMapping("/api/rollup2/extPerson")
@RequiredArgsConstructor
@Tag(name = "ExtPerson (extPerson)", description = "API for managing extPerson entities.")
public class ExtPersonController {

    private final ExtPersonCoreService extPersonService;


    @Operation(summary = "Create a new ExtPerson", description = "Registers a new ExtPerson in the system.")
    @PostMapping
    public ExtPersonDTO create(@RequestBody ExtPersonDTO inputDto) {
        return extPersonService.save(inputDto);
    }


    @Operation(summary = "Retrieve a ExtPerson by ID", description = "Fetches a single ExtPerson using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ExtPerson found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExtPersonDTO.class))}),
            @ApiResponse(responseCode = "404", description = "ExtPerson not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExtPersonDTO> findById(@PathVariable("id") String id) {
        Optional<ExtPersonDTO> findOptional = extPersonService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search ExtPerson data", description = "Searches for ExtPerson records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<ExtPersonDTO> search(@RequestBody ExtPersonSrchDTO searchDTO) {
        return extPersonService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing ExtPerson", description = "Updates the details of an existing ExtPerson by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ExtPerson updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExtPersonDTO.class))}),
            @ApiResponse(responseCode = "404", description = "ExtPerson not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<ExtPersonDTO> update(@RequestBody ExtPersonDTO updateDto) {
        ExtPersonDTO resultDto = extPersonService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a ExtPerson", description = "Deletes a ExtPerson from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!extPersonService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        extPersonService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
