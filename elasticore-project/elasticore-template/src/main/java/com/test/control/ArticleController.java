//ecd:-571811049H20241224183121_V1.0
package com.test.control;

import com.test.dto.*;
import com.test.service.*;

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
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mobillug.leaserent.estimator.common.utils.DTOUtils;

@RestController
@RequestMapping("/api/A0/article")
@RequiredArgsConstructor
@Tag(name = "Article (article)", description = "API for managing article entities.")
public class ArticleController {

    private final ArticleCoreService articleService;


    @Operation(summary = "Create a new Article", description = "Registers a new Article in the system.")
    @PostMapping
    public ArticleDTO create(@Valid @RequestBody ArticleDTO inputDto) {
        return articleService.save(inputDto);
    }


    @Operation(summary = "Retrieve a Article by ID", description = "Fetches a single Article using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ArticleDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> findById(@PathVariable("id") String id) {
        Optional<ArticleDTO> findOptional = articleService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search Article data", description = "Searches for Article records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<ArticleDTO> search(@RequestBody ArticleSrchDTO searchDTO) {
        return articleService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing Article", description = "Updates the details of an existing Article by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ArticleDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<ArticleDTO> update(@Valid @RequestBody ArticleDTO updateDto) {
        ArticleDTO resultDto = articleService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a Article", description = "Deletes a Article from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!articleService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        articleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for ArticleDTO",
        description = "Fetches metadata for the ArticleDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(ArticleDTO.class));
    }
}
