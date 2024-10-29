//ecd:1428475464H20241028203810_V1.0
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
import java.util.Map;
import java.util.Optional;

import io.elasticore.runtime.utils.DTOUtils;

@RestController
@RequestMapping("/api/searchResult/personGroup")
@RequiredArgsConstructor
@Tag(name = "PersonGroup (personGroup)", description = "API for managing personGroup entities.")
public class PersonGroupController {

    private final PersonGroupCoreService personGroupService;


    @Operation(summary = "Create a new PersonGroup", description = "Registers a new PersonGroup in the system.")
    @PostMapping
    public PersonGroupDTO create(@RequestBody PersonGroupDTO inputDto) {
        return personGroupService.save(inputDto);
    }


    @Operation(summary = "Retrieve a PersonGroup by ID", description = "Fetches a single PersonGroup using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PersonGroup found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PersonGroupDTO.class))}),
            @ApiResponse(responseCode = "404", description = "PersonGroup not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PersonGroupDTO> findById(@PathVariable("id") String id) {
        Optional<PersonGroupDTO> findOptional = personGroupService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search PersonGroup data", description = "Searches for PersonGroup records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<PersonGroupDTO> search(@RequestBody PersonGroupSrchDTO searchDTO) {
        return personGroupService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing PersonGroup", description = "Updates the details of an existing PersonGroup by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PersonGroup updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PersonGroupDTO.class))}),
            @ApiResponse(responseCode = "404", description = "PersonGroup not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<PersonGroupDTO> update(@RequestBody PersonGroupDTO updateDto) {
        PersonGroupDTO resultDto = personGroupService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }

    @Operation(summary = "Delete multiple PersonGroup entities", description = "Deletes a list of PersonGroup entities based on their IDs provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletion result for each PersonGroup entity", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class, description = "List of booleans indicating success (true) or failure (false) for each entity"))}),
            @ApiResponse(responseCode = "404", description = "One or more PersonGroup entities not found", content = @Content)
    })
    @PostMapping("/delete")
    public ResponseEntity<List<Boolean>> delete(@RequestBody List<PersonGroupDTO> delDtoList) {
        List<Boolean> result = personGroupService.delete(delDtoList);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete a PersonGroup", description = "Deletes a PersonGroup from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        if (!personGroupService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boolean result = personGroupService.deleteById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
        summary = "Retrieve metadata information for PersonGroupDTO",
        description = "Fetches metadata for the PersonGroupDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(PersonGroupDTO.class));
    }
}
