//ecd:1974349515H20240527134253V0.7
package io.elasticore.demo.linkone.control;


import io.elasticore.demo.linkone.dto.*;
import io.elasticore.demo.linkone.service.*;


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
    public List<LoanCarDTO> getAllloanCars() {
        return loanCarService.findAll();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LoanCarDTO> findById(@PathVariable Long id) {
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
    public Page<LoanCarDTO> search(@RequestBody LoanCarSearchDTO searchDTO) {
        return loanCarService.findBySearch(searchDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LoanCarDTO> update(@PathVariable Long id, @RequestBody LoanCarDTO loanCarDetails) {
        Optional<LoanCarDTO> loanCar = loanCarService.findById(id);

        if (!loanCar.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        LoanCarDTO updatedloanCar = loanCarService.save(loanCarDetails);
        return ResponseEntity.ok(updatedloanCar);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!loanCarService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        loanCarService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
