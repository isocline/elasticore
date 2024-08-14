//ecd:-2021857202H20240806171759_V0.8
package com.mobillug.gateone.biz.control;


import com.mobillug.gateone.biz.dto.*;
import com.mobillug.gateone.biz.service.*;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gateone/loginHistory")
@RequiredArgsConstructor
public class LoginHistoryController{

    private final LoginHistoryCoreService loginHistoryService;

    @GetMapping
    @ResponseBody
    public List<LoginHistoryDTO> listAll() {
        return loginHistoryService.findAll();
    }

    @GetMapping("/sample")
    @ResponseBody
    public LoginHistoryDTO sample() {
        return new LoginHistoryDTO();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LoginHistoryDTO> findById(@PathVariable("id") Long id) {
        Optional<LoginHistoryDTO> LoginHistory = loginHistoryService.findById(id);
        return LoginHistory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public LoginHistoryDTO create(@RequestBody LoginHistoryDTO LoginHistory) {
        return loginHistoryService.save(LoginHistory);
    }


    @PostMapping("/search")
    @ResponseBody
    public Page<LoginHistorySrchResultDTO> search(@RequestBody LoginHistorySrchDTO searchDTO) {
        return loginHistoryService.findBySearch(searchDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LoginHistoryDTO> update(@PathVariable("id") Long id, @RequestBody LoginHistoryDTO loginHistoryDetails) {
        LoginHistoryDTO loginHistory = loginHistoryService.update(loginHistoryDetails);

        return ResponseEntity.ok(loginHistory);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!loginHistoryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        loginHistoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
