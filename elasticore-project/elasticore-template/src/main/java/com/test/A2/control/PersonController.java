//ecd:-1117088476H20241213011350_V1.0
package com.test.A2.control;

import com.test.A2.dto.*;
import com.test.A2.service.*;

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
@RequestMapping("/api/A2/person")
@RequiredArgsConstructor
@Tag(name = "Person (person)", description = "API for managing person entities.")
public class PersonController {

    private final PersonCoreService personService;


    @Operation(summary = "Create a new Person", description = "Registers a new Person in the system.")
    @PostMapping
    public PersonDTO create(@Valid @RequestBody PersonDTO inputDto) {
        return personService.save(inputDto);
    }


    @Operation(summary = "Retrieve a Person by ID", description = "Fetches a single Person using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") String id) {
        Optional<PersonDTO> findOptional = personService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search Person data", description = "Searches for Person records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<PersonDTO> search(@RequestBody PersonSrchDTO searchDTO) {
        return personService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing Person", description = "Updates the details of an existing Person by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<PersonDTO> update(@Valid @RequestBody PersonDTO updateDto) {
        PersonDTO resultDto = personService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }

    @Operation(summary = "Delete multiple Person entities", description = "Deletes a list of Person entities based on their IDs provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletion result for each Person entity", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class, description = "List of booleans indicating success (true) or failure (false) for each entity"))}),
            @ApiResponse(responseCode = "404", description = "One or more Person entities not found", content = @Content)
    })
    @PostMapping("/delete")
    public ResponseEntity<List<Boolean>> delete(@RequestBody List<PersonDTO> delDtoList) {
        List<Boolean> result = personService.delete(delDtoList);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete a Person", description = "Deletes a Person from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        if (!personService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boolean result = personService.deleteById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
        summary = "Retrieve metadata information for PersonDTO",
        description = "Fetches metadata for the PersonDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(PersonDTO.class));
    }
}
