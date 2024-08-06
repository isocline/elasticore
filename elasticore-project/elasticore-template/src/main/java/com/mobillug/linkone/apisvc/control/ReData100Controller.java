//ecd:932525366H20240805175916_V0.8
package com.mobillug.linkone.apisvc.control;


import com.mobillug.linkone.apisvc.dto.*;
import com.mobillug.linkone.apisvc.service.*;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.mobillug.linkone.common.utils.TuiGridUtils;
import com.mobillug.linkone.common.vo.TuiGridResponseVO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/apisvc/reData100")
@RequiredArgsConstructor
@Tag(name = "ReData100 (대차요청)", description = "대차 요청 정보를 관리")
public class ReData100Controller{

    private final ReData100CoreService reData100Service;


    @Operation(summary = "reData100 전체조회", description = "reData100 데이터에 대한 전체조회. 테스트 목적으로 추후 해당메소드 삭제 예정.")
    @GetMapping
    @ResponseBody
    public List<Re100> listAll() {
        return reData100Service.findAll();
    }

    @Operation(summary = "ReData100 샘플 구조체 보기", description = "ReData100 의 샘플 구조체 보기, 삭제 예정")
    @GetMapping("/sample")
    @ResponseBody
    public Re100 sample() {
        return new Re100();
    }

    @Operation(summary = "ReData100 의 아이디로 단건조회", description = "ReData100 아이디로 단건 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ReData100", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Re100.class))}),
            @ApiResponse(responseCode = "404", description = "Loan car not found", content = @Content)
    })
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Re100> findById(@PathVariable("id") Long id) {
        Optional<ReData100DTO> ReData100 = reData100Service.findById(id);
        return ReData100.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "ReData100 데이터 단건 등록", description = "ReData100 데이터 단건 등록")
    @PostMapping
    @ResponseBody
    public Re100 create(@RequestBody Re100 ReData100) {
        return reData100Service.save(ReData100);
    }


    @Operation(summary = "ReData100 데이터 검색", description = " ReData100 필드항목 조건에 따라 데이터 검색, 각 조건은 AND 조건이 된다.")
    @PostMapping("/search")
    @ResponseBody
    public Page<Re100> search(@RequestBody Re100SrchDTO searchDTO) {
        return reData100Service.findBySearch(searchDTO);
    }


    @Operation(summary = "ReData100 그리드용 데이터 검색", description = " ReData100 필드항목 조건에 따라 데이터 검색, 각 조건은 AND 조건이 된다.")
    @PostMapping(value = "/grid/search", consumes = "application/x-www-form-urlencoded")
    @ResponseBody
    public TuiGridResponseVO searchGrid(@ModelAttribute Re100SrchDTO searchDTO) {
        return TuiGridUtils.getTuiPageGridResponse( reData100Service.findBySearch(searchDTO) );
    }



    @Operation(summary = "ReData100 데이터 수정", description = "ReData100 데이터 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the ReData100", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Re100.class))}),
            @ApiResponse(responseCode = "404", description = "Loan car not found", content = @Content)
    })
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Re100> update(@PathVariable("id") Long id, @RequestBody Re100 reData100Details) {
        Re100 reData100 = reData100Service.update(reData100Details);

        return ResponseEntity.ok(reData100);
    }

    @Operation(summary = " ReData100 삭제", description = " ReData100 데이터 삭제")
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!reData100Service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        reData100Service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
