//ecd:2051390068H20240618012928_V0.8
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
@RequestMapping("/api/linkone/loanCar")
@RequiredArgsConstructor
public class LoanCarController{

    private final LoanCarCoreService loanCarService;

    @GetMapping
    @ResponseBody
    public List<LoanCarDTO> listAll() {
        return loanCarService.findAll();
    }

    @GetMapping("/sample")
    @ResponseBody
    public LoanCarDTO sample() {
        return new LoanCarDTO();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LoanCarDTO> findById(@PathVariable("id") String id) {
        Optional<LoanCarDTO> LoanCar = loanCarService.findById(id);
        return LoanCar.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public LoanCarDTO create(@RequestBody LoanCarDTO LoanCar) {
        return loanCarService.save(LoanCar);
    }


    @PostMapping("/search")
    @ResponseBody
    public Page<LoanCarSearchResultDTO> search(@RequestBody LoanCarSearchDTO searchDTO) {
        return loanCarService.findBySearch(searchDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LoanCarDTO> update(@PathVariable("id") String id, @RequestBody LoanCarDTO loanCarDetails) {
        LoanCarDTO loanCar = loanCarService.update(loanCarDetails);

        return ResponseEntity.ok(loanCar);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!loanCarService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        loanCarService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
