//ecd:934227851H20240618012928_V0.8
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
@RequestMapping("/api/linkone/loanCarProcess")
@RequiredArgsConstructor
public class LoanCarProcessController{

    private final LoanCarProcessCoreService loanCarProcessService;

    @GetMapping
    @ResponseBody
    public List<LoanCarProcessDTO> listAll() {
        return loanCarProcessService.findAll();
    }

    @GetMapping("/sample")
    @ResponseBody
    public LoanCarProcessDTO sample() {
        return new LoanCarProcessDTO();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LoanCarProcessDTO> findById(@PathVariable("id") Long id) {
        Optional<LoanCarProcessDTO> LoanCarProcess = loanCarProcessService.findById(id);
        return LoanCarProcess.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public LoanCarProcessDTO create(@RequestBody LoanCarProcessDTO LoanCarProcess) {
        return loanCarProcessService.save(LoanCarProcess);
    }


    @PostMapping("/search")
    @ResponseBody
    public Page<LoanCarProcessDTO> search(@RequestBody LoanCarPrsSrchDTO searchDTO) {
        return loanCarProcessService.findBySearch(searchDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LoanCarProcessDTO> update(@PathVariable("id") Long id, @RequestBody LoanCarProcessDTO loanCarProcessDetails) {
        LoanCarProcessDTO loanCarProcess = loanCarProcessService.update(loanCarProcessDetails);

        return ResponseEntity.ok(loanCarProcess);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!loanCarProcessService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        loanCarProcessService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
