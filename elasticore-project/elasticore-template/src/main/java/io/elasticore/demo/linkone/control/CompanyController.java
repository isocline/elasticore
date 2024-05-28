//ecd:-1485741453H20240528142512V0.7
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
@RequestMapping("/api/linkone/company")
@RequiredArgsConstructor
public class CompanyController{

    private final CompanyCoreService companyService;

    @GetMapping
    @ResponseBody
    public List<CompanyDTO> getAllcompanys() {
        return companyService.findAll();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CompanyDTO> findById(@PathVariable("id") Long id) {
        Optional<CompanyDTO> Company = companyService.findById(id);
        return Company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public CompanyDTO create(@RequestBody CompanyDTO Company) {
        return companyService.save(Company);
    }


    @PostMapping("/search")
    @ResponseBody
    public Page<CompanyDTO> search(@RequestBody CompanySearchDTO searchDTO) {
        return companyService.findBySearch(searchDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CompanyDTO> update(@PathVariable("id") Long id, @RequestBody CompanyDTO companyDetails) {
        CompanyDTO company = companyService.update(companyDetails);

        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!companyService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
