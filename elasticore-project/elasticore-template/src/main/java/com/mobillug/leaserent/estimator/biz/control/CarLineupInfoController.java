//ecd:7559252H20241223210702_V1.0
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
@RequestMapping("/api/estimate/carLineupInfo")
@RequiredArgsConstructor
@Tag(name = "CarLineupInfo (차량 라인업 정보)", description = "API for managing 차량 라인업 정보 entities.")
public class CarLineupInfoController {

    private final CarLineupInfoCoreService carLineupInfoService;


    @Operation(summary = "Create a new CarLineupInfo", description = "Registers a new CarLineupInfo in the system.")
    @PostMapping
    public CarLineupInfoDTO create(@Valid @RequestBody CarLineupInfoDTO inputDto) {
        return carLineupInfoService.save(inputDto);
    }


    @Operation(summary = "Retrieve a CarLineupInfo by ID", description = "Fetches a single CarLineupInfo using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarLineupInfo found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CarLineupInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "CarLineupInfo not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarLineupInfoDTO> findById(@PathVariable("id") String id) {
        Optional<CarLineupInfoDTO> findOptional = carLineupInfoService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search CarLineupInfo data", description = "Searches for CarLineupInfo records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public Page<CarLineupInfoResultDTO> search(@RequestBody CarLineupInfoSrchDTO searchDTO) {
        return carLineupInfoService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing CarLineupInfo", description = "Updates the details of an existing CarLineupInfo by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarLineupInfo updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CarLineupInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "CarLineupInfo not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<CarLineupInfoDTO> update(@Valid @RequestBody CarLineupInfoDTO updateDto) {
        CarLineupInfoDTO resultDto = carLineupInfoService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a CarLineupInfo", description = "Deletes a CarLineupInfo from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!carLineupInfoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        carLineupInfoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for CarLineupInfoDTO",
        description = "Fetches metadata for the CarLineupInfoDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(CarLineupInfoDTO.class));
    }
}
