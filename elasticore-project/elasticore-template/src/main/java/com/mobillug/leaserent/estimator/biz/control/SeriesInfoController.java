//ecd:1514165226H20241223210702_V1.0
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
@RequestMapping("/api/estimate/seriesInfo")
@RequiredArgsConstructor
@Tag(name = "SeriesInfo (차량 시리즈)", description = "API for managing 차량 시리즈 entities.")
public class SeriesInfoController {

    private final SeriesInfoCoreService seriesInfoService;


    @Operation(summary = "Create a new SeriesInfo", description = "Registers a new SeriesInfo in the system.")
    @PostMapping
    public SeriesInfoDTO create(@Valid @RequestBody SeriesInfoDTO inputDto) {
        return seriesInfoService.save(inputDto);
    }


    @Operation(summary = "Retrieve a SeriesInfo by ID", description = "Fetches a single SeriesInfo using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SeriesInfo found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SeriesInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "SeriesInfo not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<SeriesInfoDTO> findById(@PathVariable("id") String id) {
        Optional<SeriesInfoDTO> findOptional = seriesInfoService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search SeriesInfo data", description = "Searches for SeriesInfo records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public Page<SeriesInfoResultDTO> search(@RequestBody SeriesInfoSrchDTO searchDTO) {
        return seriesInfoService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing SeriesInfo", description = "Updates the details of an existing SeriesInfo by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SeriesInfo updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SeriesInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "SeriesInfo not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<SeriesInfoDTO> update(@Valid @RequestBody SeriesInfoDTO updateDto) {
        SeriesInfoDTO resultDto = seriesInfoService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a SeriesInfo", description = "Deletes a SeriesInfo from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!seriesInfoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        seriesInfoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for SeriesInfoDTO",
        description = "Fetches metadata for the SeriesInfoDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(SeriesInfoDTO.class));
    }
}
