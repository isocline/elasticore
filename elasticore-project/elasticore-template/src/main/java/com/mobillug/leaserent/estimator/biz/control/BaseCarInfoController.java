//ecd:-1643022486H20241223210702_V1.0
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
@RequestMapping("/api/estimate/baseCarInfo")
@RequiredArgsConstructor
@Tag(name = "BaseCarInfo (차량 정보 엔티티)", description = "API for managing 차량 정보 엔티티 entities.")
public class BaseCarInfoController {

    private final BaseCarInfoCoreService baseCarInfoService;


    @Operation(summary = "Create a new BaseCarInfo", description = "Registers a new BaseCarInfo in the system.")
    @PostMapping
    public BaseCarInfoDTO create(@Valid @RequestBody BaseCarInfoDTO inputDto) {
        return baseCarInfoService.save(inputDto);
    }


    @Operation(summary = "Retrieve a BaseCarInfo by ID", description = "Fetches a single BaseCarInfo using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BaseCarInfo found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseCarInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "BaseCarInfo not found", content = @Content)
    })
    @GetMapping("/{carId}")
    public ResponseEntity<BaseCarInfoDTO> findById(@PathVariable("carId") String carId) {
        Optional<BaseCarInfoDTO> findOptional = baseCarInfoService.findById(carId);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search BaseCarInfo data", description = "Searches for BaseCarInfo records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public Page<BaseCarInfoResultDTO> search(@RequestBody BaseCarInfoSrchDTO searchDTO) {
        return baseCarInfoService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing BaseCarInfo", description = "Updates the details of an existing BaseCarInfo by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BaseCarInfo updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseCarInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "BaseCarInfo not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<BaseCarInfoDTO> update(@Valid @RequestBody BaseCarInfoDTO updateDto) {
        BaseCarInfoDTO resultDto = baseCarInfoService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a BaseCarInfo", description = "Deletes a BaseCarInfo from the system using its unique identifier.")
    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> delete(@PathVariable("carId") String carId) {
        if (!baseCarInfoService.findById(carId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        baseCarInfoService.deleteById(carId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for BaseCarInfoDTO",
        description = "Fetches metadata for the BaseCarInfoDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(BaseCarInfoDTO.class));
    }
}
