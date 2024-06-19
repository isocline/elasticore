//ecd:-182658448H20240618012928_V0.8
package com.mobillug.linkone.biz.control;


import com.mobillug.linkone.biz.dto.*;
import com.mobillug.linkone.biz.service.*;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/linkone/commonCode")
@RequiredArgsConstructor
public class CommonCodeController{

    private final CommonCodeCoreService commonCodeService;

    @GetMapping
    @ResponseBody
    public List<CommonCodeDTO> listAll() {
        return commonCodeService.findAll();
    }

    @GetMapping("/sample")
    @ResponseBody
    public CommonCodeDTO sample() {
        return new CommonCodeDTO();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CommonCodeDTO> findById(@PathVariable("id") Long id) {
        Optional<CommonCodeDTO> CommonCode = commonCodeService.findById(id);
        return CommonCode.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public CommonCodeDTO create(@RequestBody CommonCodeDTO CommonCode) {
        return commonCodeService.save(CommonCode);
    }


    @PostMapping("/search")
    @ResponseBody
    public Page<CommonCodeDTO> search(@RequestBody CommonCodeSearchDTO searchDTO) {
        return commonCodeService.findBySearch(searchDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CommonCodeDTO> update(@PathVariable("id") Long id, @RequestBody CommonCodeDTO commonCodeDetails) {
        CommonCodeDTO commonCode = commonCodeService.update(commonCodeDetails);

        return ResponseEntity.ok(commonCode);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!commonCodeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        commonCodeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
