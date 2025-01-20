//ecd:1922521704H20250117173851_V1.0
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
@RequestMapping("/api/A0/product")
@RequiredArgsConstructor
@Tag(name = "Product (product)", description = "API for managing product entities.")
public class ProductController {

    private final ProductCoreService productService;


    @Operation(summary = "Create a new Product", description = "Registers a new Product in the system.")
    @PostMapping
    public ProductDTO create(@Valid @RequestBody ProductDTO inputDto) {
        return productService.save(inputDto);
    }


    @Operation(summary = "Retrieve a Product by ID", description = "Fetches a single Product using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    @GetMapping("/{pid}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("pid") String pid) {
        Optional<ProductDTO> findOptional = productService.findById(pid);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search Product data", description = "Searches for Product records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<ProductDTO> search(@RequestBody ProductSrchDTO searchDTO) {
        return productService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing Product", description = "Updates the details of an existing Product by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO updateDto) {
        ProductDTO resultDto = productService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }

    @Operation(summary = "Delete multiple Product entities", description = "Deletes a list of Product entities based on their IDs provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletion result for each Product entity", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class, description = "List of booleans indicating success (true) or failure (false) for each entity"))}),
            @ApiResponse(responseCode = "404", description = "One or more Product entities not found", content = @Content)
    })
    @PostMapping("/delete")
    public ResponseEntity<List<Boolean>> delete(@RequestBody List<ProductKeyDTO> delDtoList) {
        List<Boolean> result = productService.delete(delDtoList);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete a Product", description = "Deletes a Product from the system using its unique identifier.")
    @DeleteMapping("/{pid}")
    public ResponseEntity<Boolean> delete(@PathVariable("pid") String pid) {
        if (!productService.findById(pid).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boolean result = productService.deleteById(pid);
        return ResponseEntity.ok(result);
    }

    @Operation(
        summary = "Retrieve metadata information for ProductDTO",
        description = "Fetches metadata for the ProductDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(ProductDTO.class));
    }
}
