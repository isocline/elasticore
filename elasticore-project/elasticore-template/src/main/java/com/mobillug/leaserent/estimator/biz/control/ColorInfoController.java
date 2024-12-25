//ecd:-2050282782H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.control;

import com.mobillug.leaserent.estimator.biz.dto.*;
import com.mobillug.leaserent.estimator.biz.service.*;

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
@RequestMapping("/api/estimate/colorInfo")
@RequiredArgsConstructor
@Tag(name = "ColorInfo (colorInfo)", description = "API for managing colorInfo entities.")
public class ColorInfoController {

    private final ColorInfoCoreService colorInfoService;


    @Operation(summary = "Create a new ColorInfo", description = "Registers a new ColorInfo in the system.")
    @PostMapping
    public ColorInfoDTO create(@Valid @RequestBody ColorInfoDTO inputDto) {
        return colorInfoService.save(inputDto);
    }


    @Operation(summary = "Retrieve a ColorInfo by ID", description = "Fetches a single ColorInfo using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ColorInfo found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ColorInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "ColorInfo not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ColorInfoDTO> findById(@PathVariable("id") String id) {
        Optional<ColorInfoDTO> findOptional = colorInfoService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search ColorInfo data", description = "Searches for ColorInfo records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<ColorInfoDTO> search(@RequestBody ColorInfoSrchDTO searchDTO) {
        return colorInfoService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing ColorInfo", description = "Updates the details of an existing ColorInfo by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ColorInfo updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ColorInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "ColorInfo not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<ColorInfoDTO> update(@Valid @RequestBody ColorInfoDTO updateDto) {
        ColorInfoDTO resultDto = colorInfoService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a ColorInfo", description = "Deletes a ColorInfo from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!colorInfoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        colorInfoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for ColorInfoDTO",
        description = "Fetches metadata for the ColorInfoDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(ColorInfoDTO.class));
    }
}
