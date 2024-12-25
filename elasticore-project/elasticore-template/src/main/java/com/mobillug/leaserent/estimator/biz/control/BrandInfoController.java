//ecd:-1033798584H20241223210702_V1.0
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
@RequestMapping("/api/estimate/brandInfo")
@RequiredArgsConstructor
@Tag(name = "BrandInfo (차량 브랜드 정보)", description = "API for managing 차량 브랜드 정보 entities.")
public class BrandInfoController {

    private final BrandInfoCoreService brandInfoService;


    @Operation(summary = "Create a new BrandInfo", description = "Registers a new BrandInfo in the system.")
    @PostMapping
    public BrandInfoDTO create(@Valid @RequestBody BrandInfoDTO inputDto) {
        return brandInfoService.save(inputDto);
    }


    @Operation(summary = "Retrieve a BrandInfo by ID", description = "Fetches a single BrandInfo using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BrandInfo found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BrandInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "BrandInfo not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BrandInfoDTO> findById(@PathVariable("id") String id) {
        Optional<BrandInfoDTO> findOptional = brandInfoService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search BrandInfo data", description = "Searches for BrandInfo records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public Page<BrandInfoResultDTO> search(@RequestBody BrandInfoSrchDTO searchDTO) {
        return brandInfoService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing BrandInfo", description = "Updates the details of an existing BrandInfo by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BrandInfo updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BrandInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "BrandInfo not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<BrandInfoDTO> update(@Valid @RequestBody BrandInfoDTO updateDto) {
        BrandInfoDTO resultDto = brandInfoService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a BrandInfo", description = "Deletes a BrandInfo from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!brandInfoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        brandInfoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for BrandInfoDTO",
        description = "Fetches metadata for the BrandInfoDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(BrandInfoDTO.class));
    }
}
