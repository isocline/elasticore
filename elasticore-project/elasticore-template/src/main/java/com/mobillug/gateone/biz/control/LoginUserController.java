//ecd:-1250406148H20240806171759_V0.8
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
@RequestMapping("/api/gateone/loginUser")
@RequiredArgsConstructor
public class LoginUserController{

    private final LoginUserCoreService loginUserService;

    @GetMapping
    @ResponseBody
    public List<LoginUserDTO> listAll() {
        return loginUserService.findAll();
    }

    @GetMapping("/sample")
    @ResponseBody
    public LoginUserDTO sample() {
        return new LoginUserDTO();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LoginUserDTO> findById(@PathVariable("id") String id) {
        Optional<LoginUserDTO> LoginUser = loginUserService.findById(id);
        return LoginUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public LoginUserDTO create(@RequestBody LoginUserDTO LoginUser) {
        return loginUserService.save(LoginUser);
    }


    @PostMapping("/search")
    @ResponseBody
    public Page<LoginUserSrchResultDTO> search(@RequestBody LoginUserSrchDTO searchDTO) {
        return loginUserService.findBySearch(searchDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LoginUserDTO> update(@PathVariable("id") String id, @RequestBody LoginUserDTO loginUserDetails) {
        LoginUserDTO loginUser = loginUserService.update(loginUserDetails);

        return ResponseEntity.ok(loginUser);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!loginUserService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        loginUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
