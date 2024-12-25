//ecd:-245926064H20241223210702_V1.0
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
@RequestMapping("/api/estimate/uploadFile")
@RequiredArgsConstructor
@Tag(name = "UploadFile (업로드 파일 처리용)", description = "API for managing 업로드 파일 처리용 entities.")
public class UploadFileController {

    private final UploadFileCoreService uploadFileService;


    @Operation(summary = "Create a new UploadFile", description = "Registers a new UploadFile in the system.")
    @PostMapping
    public UploadFileDTO create(@Valid @RequestBody UploadFileDTO inputDto) {
        return uploadFileService.save(inputDto);
    }


    @Operation(summary = "Retrieve a UploadFile by ID", description = "Fetches a single UploadFile using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UploadFile found successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UploadFileDTO.class))}),
            @ApiResponse(responseCode = "404", description = "UploadFile not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UploadFileDTO> findById(@PathVariable("id") String id) {
        Optional<UploadFileDTO> findOptional = uploadFileService.findById(id);
        return findOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Search UploadFile data", description = "Searches for UploadFile records based on provided field criteria. All criteria are combined using logical AND.")
    @PostMapping("/search")
    public List<UploadFileDTO> search(@RequestBody UploadFileSrchDTO searchDTO) {
        return uploadFileService.findBySearch(searchDTO);
    }


    @Operation(summary = "Update an existing UploadFile", description = "Updates the details of an existing UploadFile by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UploadFile updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UploadFileDTO.class))}),
            @ApiResponse(responseCode = "404", description = "UploadFile not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<UploadFileDTO> update(@Valid @RequestBody UploadFileDTO updateDto) {
        UploadFileDTO resultDto = uploadFileService.update(updateDto);
        return ResponseEntity.ok(resultDto);
    }


    @Operation(summary = "Delete a UploadFile", description = "Deletes a UploadFile from the system using its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!uploadFileService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        uploadFileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieve metadata information for UploadFileDTO",
        description = "Fetches metadata for the UploadFileDTO, including field types, size limitations, and possible enumeration values, if applicable."
    )
    @GetMapping("/schema/info")
    public ResponseEntity<Map<String, Object>> getMetaInfo() {
        return ResponseEntity.ok(DTOUtils.getMetaInfo(UploadFileDTO.class));
    }
}
