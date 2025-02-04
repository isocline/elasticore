//ecd:365759673H20250204014854_V1.0
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
@RequestMapping("/api/A0/employee")
@RequiredArgsConstructor
@Tag(name = "Employee (직원)", description = "API for managing 직원 entities.")
public class EmployeeController {

    private final EmployeeCoreService employeeService;


    @Operation(summary = "Create a new Employee", description = "Registers a new Employee in the system.")
    @PostMapping
    public EmployeeDTO create(@Valid @RequestBody EmployeeDTO inputDto) {
        return employeeService.save(inputDto);
    }


    @Operation(summary = "Retrieve a Employee by ID", description = "Fetches a single Employee using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable("id") String id) {
        Optional<EmployeeDTO> findOptional = employeeService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search Employee data", description = "Searches for Employee records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<EmployeeDTO> search(@RequestBody EmployeeSrchDTO searchDTO) {
        return employeeService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing Employee", description = "Updates the details of an existing Employee by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<EmployeeDTO> update(@RequestBody EmployeeDTO updateDto) {
        EmployeeDTO resultDto = employeeService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }

    @Operation(summary = "Delete multiple Employee entities", description = "Deletes a list of Employee entities based on their IDs provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletion result for each Employee entity", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class, description = "List of booleans indicating success (true) or failure (false) for each entity"))}),
            @ApiResponse(responseCode = "404", description = "One or more Employee entities not found", content = @Content)
    })
    @PostMapping("/delete")
    public ResponseEntity<List<Boolean>> delete(@RequestBody List<EmployeeKeyDTO> delDtoList) {
        List<Boolean> result = employeeService.delete(delDtoList);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete a Employee", description = "Deletes a Employee from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        if (!employeeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boolean result = employeeService.deleteById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
        summary = "Retrieve metadata information for EmployeeDTO",
        description = "Fetches metadata for the EmployeeDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(EmployeeDTO.class));
    }
}
