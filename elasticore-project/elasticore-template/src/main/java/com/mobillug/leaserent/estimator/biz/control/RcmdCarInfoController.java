//ecd:-1624673683H20241223210702_V1.0
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
@RequestMapping("/api/estimate/rcmdCarInfo")
@RequiredArgsConstructor
@Tag(name = "RcmdCarInfo (추천(즉출) 차량 정보)", description = "API for managing 추천(즉출) 차량 정보 entities.")
public class RcmdCarInfoController {

    private final RcmdCarInfoCoreService rcmdCarInfoService;


    @Operation(summary = "Create a new RcmdCarInfo", description = "Registers a new RcmdCarInfo in the system.")
    @PostMapping
    public RcmdCarInfoDTO create(@Valid @RequestBody RcmdCarInfoDTO inputDto) {
        return rcmdCarInfoService.save(inputDto);
    }


    @Operation(summary = "Retrieve a RcmdCarInfo by ID", description = "Fetches a single RcmdCarInfo using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "RcmdCarInfo found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RcmdCarInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "RcmdCarInfo not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RcmdCarInfoDTO> findById(@PathVariable("id") String id) {
        Optional<RcmdCarInfoDTO> findOptional = rcmdCarInfoService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search RcmdCarInfo data", description = "Searches for RcmdCarInfo records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<RcmdCarInfoDTO> search(@RequestBody RcmdCarInfoSrchDTO searchDTO) {
        return rcmdCarInfoService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing RcmdCarInfo", description = "Updates the details of an existing RcmdCarInfo by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "RcmdCarInfo updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RcmdCarInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "RcmdCarInfo not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<RcmdCarInfoDTO> update(@Valid @RequestBody RcmdCarInfoDTO updateDto) {
        RcmdCarInfoDTO resultDto = rcmdCarInfoService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a RcmdCarInfo", description = "Deletes a RcmdCarInfo from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!rcmdCarInfoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        rcmdCarInfoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for RcmdCarInfoDTO",
        description = "Fetches metadata for the RcmdCarInfoDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(RcmdCarInfoDTO.class));
    }
}
