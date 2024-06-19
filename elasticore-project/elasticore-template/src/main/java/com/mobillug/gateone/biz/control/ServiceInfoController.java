//ecd:275379211H20240618012928_V0.8
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
@RequestMapping("/api/gateone/serviceInfo")
@RequiredArgsConstructor
public class ServiceInfoController{

    private final ServiceInfoCoreService serviceInfoService;

    @GetMapping
    @ResponseBody
    public List<ServiceInfoDTO> listAll() {
        return serviceInfoService.findAll();
    }

    @GetMapping("/sample")
    @ResponseBody
    public ServiceInfoDTO sample() {
        return new ServiceInfoDTO();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ServiceInfoDTO> findById(@PathVariable("id") String id) {
        Optional<ServiceInfoDTO> ServiceInfo = serviceInfoService.findById(id);
        return ServiceInfo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public ServiceInfoDTO create(@RequestBody ServiceInfoDTO ServiceInfo) {
        return serviceInfoService.save(ServiceInfo);
    }


    @PostMapping("/search")
    @ResponseBody
    public Page<ServiceInfoSrchResultDTO> search(@RequestBody ServiceInfoSrchDTO searchDTO) {
        return serviceInfoService.findBySearch(searchDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ServiceInfoDTO> update(@PathVariable("id") String id, @RequestBody ServiceInfoDTO serviceInfoDetails) {
        ServiceInfoDTO serviceInfo = serviceInfoService.update(serviceInfoDetails);

        return ResponseEntity.ok(serviceInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!serviceInfoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        serviceInfoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
