//ecd:1353396287H20241223210702_V1.0
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
@RequestMapping("/api/estimate/baseCompanyCarInfo")
@RequiredArgsConstructor
@Tag(name = "BaseCompanyCarInfo (차량 정보 엔티티)", description = "API for managing 차량 정보 엔티티 entities.")
public class BaseCompanyCarInfoController {

    private final BaseCompanyCarInfoCoreService baseCompanyCarInfoService;


    @Operation(summary = "Create a new BaseCompanyCarInfo", description = "Registers a new BaseCompanyCarInfo in the system.")
    @PostMapping
    public BaseCompanyCarInfoDTO create(@Valid @RequestBody BaseCompanyCarInfoDTO inputDto) {
        return baseCompanyCarInfoService.save(inputDto);
    }


    @Operation(summary = "Retrieve a BaseCompanyCarInfo by ID", description = "Fetches a single BaseCompanyCarInfo using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BaseCompanyCarInfo found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseCompanyCarInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "BaseCompanyCarInfo not found", content = @Content)
    })
    @GetMapping("/{carId}")
    public ResponseEntity<BaseCompanyCarInfoDTO> findById(@PathVariable("carId") String carId) {
        Optional<BaseCompanyCarInfoDTO> findOptional = baseCompanyCarInfoService.findById(carId);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search BaseCompanyCarInfo data", description = "Searches for BaseCompanyCarInfo records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<BaseCompanyCarInfoDTO> search(@RequestBody BaseCompanyCarInfoSrchDTO searchDTO) {
        return baseCompanyCarInfoService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing BaseCompanyCarInfo", description = "Updates the details of an existing BaseCompanyCarInfo by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BaseCompanyCarInfo updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseCompanyCarInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "BaseCompanyCarInfo not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<BaseCompanyCarInfoDTO> update(@Valid @RequestBody BaseCompanyCarInfoDTO updateDto) {
        BaseCompanyCarInfoDTO resultDto = baseCompanyCarInfoService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a BaseCompanyCarInfo", description = "Deletes a BaseCompanyCarInfo from the system using its unique identifier.")
    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> delete(@PathVariable("carId") String carId) {
        if (!baseCompanyCarInfoService.findById(carId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        baseCompanyCarInfoService.deleteById(carId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for BaseCompanyCarInfoDTO",
        description = "Fetches metadata for the BaseCompanyCarInfoDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(BaseCompanyCarInfoDTO.class));
    }
}
