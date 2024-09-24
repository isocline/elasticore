//ecd:-8824112H20240924235119_V1.0
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
@RequestMapping("/api/dto/customer")
@RequiredArgsConstructor
@Tag(name = "Customer (customer)", description = "API for managing customer entities.")
public class CustomerController {

    private final CustomerCoreService customerService;


    @Operation(summary = "Create a new Customer", description = "Registers a new Customer in the system.")
    @PostMapping
    public CustomerDTO create(@RequestBody CustomerDTO inputDto) {
        return customerService.save(inputDto);
    }


    @Operation(summary = "Retrieve a Customer by ID", description = "Fetches a single Customer using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable("id") Long id) {
        Optional<CustomerDTO> findOptional = customerService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search Customer data", description = "Searches for Customer records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<CustomerDTO> search(@RequestBody CustomerSrchDTO searchDTO) {
        return customerService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing Customer", description = "Updates the details of an existing Customer by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable("id") Long id, @RequestBody CustomerDTO updateDto) {
        CustomerDTO resultDto = customerService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a Customer", description = "Deletes a Customer from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!customerService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
