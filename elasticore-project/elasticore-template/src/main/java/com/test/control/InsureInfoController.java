//ecd:76452547H20250313130133_V1.0
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
@RequestMapping("/api/chk_spec/insureInfo")
@RequiredArgsConstructor
@Tag(name = "InsureInfo (보험 정보 엔티티)", description = "API for managing 보험 정보 엔티티 entities.")
public class InsureInfoController {

    private final InsureInfoCoreService insureInfoService;


    @Operation(summary = "Create a new InsureInfo", description = "Registers a new InsureInfo in the system.")
    @PostMapping
    public InsureInfoDTO create(@Valid @RequestBody InsureInfoDTO inputDto) {
        return insureInfoService.save(inputDto);
    }


    @Operation(summary = "Retrieve a InsureInfo by ID", description = "Fetches a single InsureInfo using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "InsureInfo found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = InsureInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "InsureInfo not found", content = @Content)
    })
    @GetMapping("/{id}/{id2}")
    public ResponseEntity<InsureInfoDTO> findById(@PathVariable("id") String id ,@PathVariable("id2") Long id2) {
        Optional<InsureInfoDTO> findOptional = insureInfoService.findById(id ,id2);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search InsureInfo data", description = "Searches for InsureInfo records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<InsureInfoDTO> search(@RequestBody InsureInfoSrchDTO searchDTO) {
        return insureInfoService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing InsureInfo", description = "Updates the details of an existing InsureInfo by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "InsureInfo updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = InsureInfoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "InsureInfo not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<InsureInfoDTO> update(@RequestBody InsureInfoDTO updateDto) {
        InsureInfoDTO resultDto = insureInfoService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }

    @Operation(summary = "Delete multiple InsureInfo entities", description = "Deletes a list of InsureInfo entities based on their IDs provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletion result for each InsureInfo entity", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class, description = "List of booleans indicating success (true) or failure (false) for each entity"))}),
            @ApiResponse(responseCode = "404", description = "One or more InsureInfo entities not found", content = @Content)
    })
    @PostMapping("/delete")
    public ResponseEntity<List<Boolean>> delete(@RequestBody List<InsureInfoKeyDTO> delDtoList) {
        List<Boolean> result = insureInfoService.delete(delDtoList);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Delete a InsureInfo", description = "Deletes a InsureInfo from the system using its unique identifier.")
    @DeleteMapping("/{id}/{id2}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id ,@PathVariable("id2") Long id2) {
        if (!insureInfoService.findById(id ,id2).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boolean result = insureInfoService.deleteById(id ,id2);
        return ResponseEntity.ok(result);
    }

    @Operation(
        summary = "Retrieve metadata information for InsureInfoDTO",
        description = "Fetches metadata for the InsureInfoDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(InsureInfoDTO.class));
    }
}
