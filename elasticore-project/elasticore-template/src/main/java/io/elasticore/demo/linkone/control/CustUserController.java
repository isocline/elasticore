//ecd:1774313741H20240529100717V0.7
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
@RequestMapping("/api/linkone/custUser")
@RequiredArgsConstructor
public class CustUserController{

    private final CustUserCoreService custUserService;

    @GetMapping
    @ResponseBody
    public List<CustUserDTO> getAllcustUsers() {
        return custUserService.findAll();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CustUserDTO> findById(@PathVariable("id") Long id) {
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
    public ResponseEntity<CustUserDTO> update(@PathVariable("id") Long id, @RequestBody CustUserDTO custUserDetails) {
        CustUserDTO custUser = custUserService.update(custUserDetails);

        return ResponseEntity.ok(custUser);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!custUserService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        custUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
