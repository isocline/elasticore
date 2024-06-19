//ecd:1629615105H20240618012928_V0.8
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
@RequestMapping("/api/linkone/custUser")
@RequiredArgsConstructor
public class CustUserController{

    private final CustUserCoreService custUserService;

    @GetMapping
    @ResponseBody
    public List<CustUserDTO> listAll() {
        return custUserService.findAll();
    }

    @GetMapping("/sample")
    @ResponseBody
    public CustUserDTO sample() {
        return new CustUserDTO();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CustUserDTO> findById(@PathVariable("id") String id) {
        Optional<CustUserDTO> CustUser = custUserService.findById(id);
        return CustUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public CustUserDTO create(@RequestBody CustUserDTO CustUser) {
        return custUserService.save(CustUser);
    }


    @PostMapping("/search")
    @ResponseBody
    public Page<CustUserDTO> search(@RequestBody CustUserSearchDTO searchDTO) {
        return custUserService.findBySearch(searchDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CustUserDTO> update(@PathVariable("id") String id, @RequestBody CustUserDTO custUserDetails) {
        CustUserDTO custUser = custUserService.update(custUserDetails);

        return ResponseEntity.ok(custUser);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!custUserService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        custUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
