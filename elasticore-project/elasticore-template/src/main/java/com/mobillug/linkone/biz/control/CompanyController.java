//ecd:280178829H20240618012928_V0.8
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
@RequestMapping("/api/linkone/company")
@RequiredArgsConstructor
public class CompanyController{

    private final CompanyCoreService companyService;

    @GetMapping
    @ResponseBody
    public List<CompanyDTO> listAll() {
        return companyService.findAll();
    }

    @GetMapping("/sample")
    @ResponseBody
    public CompanyDTO sample() {
        return new CompanyDTO();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CompanyDTO> findById(@PathVariable("id") String id) {
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
    public Page<CompanySearchResultDTO> search(@RequestBody CompanySearchDTO searchDTO) {
        return companyService.findBySearch(searchDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CompanyDTO> update(@PathVariable("id") String id, @RequestBody CompanyDTO companyDetails) {
        CompanyDTO company = companyService.update(companyDetails);

        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!companyService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
