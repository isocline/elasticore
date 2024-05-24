//ecd:-224606906H20240524175232V0.7
package io.elasticore.demo.linkone.dto;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import io.elasticore.demo.linkone.entity.*;

import io.elasticore.demo.linkone.dto.*;




/**


 */


public class LinkoneMapper {


    
    public static void mapping(LoanCar from, LoanCarDTO to){
        to.setLcCode(from.getLcCode());
        to.setCustomName(from.getCustomName());
        to.setCustomPh(from.getCustomPh());
        to.setAlarmStatus(from.getAlarmStatus());
        to.setLcReceiveLocation(from.getLcReceiveLocation());
        to.setContent(from.getContent());
        to.setCarModel(from.getCarModel());
        to.setCarNumber(from.getCarNumber());
        to.setMemo(from.getMemo());
        to.setInsuranceCode(from.getInsuranceCode());
        to.setLcRepairShopName(from.getLcRepairShopName());
        to.setLcRepairShopReason(from.getLcRepairShopReason());
        to.setAccidentType(from.getAccidentType());
        to.setLcReason(from.getLcReason());
        to.setRentAlarmStatus(from.getRentAlarmStatus());
        to.setLcView(from.getLcView());
        to.setNoWayType(from.getNoWayType());
        to.setAgentName(from.getAgentName());
        to.setAgentPicName(from.getAgentPicName());
        to.setAreaName(from.getAreaName());
        to.setCapitalName(from.getCapitalName());
        to.setSmsParseType(from.getSmsParseType());
        to.setLcFixCode(from.getLcFixCode());
        to.setCreateDate(from.getCreateDate());
        to.setCreatedBy(from.getCreatedBy());
        to.setLastModifiedBy(from.getLastModifiedBy());
        to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static LoanCarDTO toDTO(LoanCar from){
        LoanCarDTO to = new LoanCarDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LoanCarDTO> toLoanCarDTOList(List<LoanCar> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<LoanCarDTO> toLoanCarDTOList(List<LoanCar> fromList, BiFunction<LoanCar, LoanCarDTO, LoanCarDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LoanCarDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(LoanCarDTO from, LoanCar to){
        to.setLcCode(from.getLcCode());
        to.setCustomName(from.getCustomName());
        to.setCustomPh(from.getCustomPh());
        to.setAlarmStatus(from.getAlarmStatus());
        to.setLcReceiveLocation(from.getLcReceiveLocation());
        to.setContent(from.getContent());
        to.setCarModel(from.getCarModel());
        to.setCarNumber(from.getCarNumber());
        to.setMemo(from.getMemo());
        to.setInsuranceCode(from.getInsuranceCode());
        to.setLcRepairShopName(from.getLcRepairShopName());
        to.setLcRepairShopReason(from.getLcRepairShopReason());
        to.setAccidentType(from.getAccidentType());
        to.setLcReason(from.getLcReason());
        to.setRentAlarmStatus(from.getRentAlarmStatus());
        to.setLcView(from.getLcView());
        to.setNoWayType(from.getNoWayType());
        to.setAgentName(from.getAgentName());
        to.setAgentPicName(from.getAgentPicName());
        to.setAreaName(from.getAreaName());
        to.setCapitalName(from.getCapitalName());
        to.setSmsParseType(from.getSmsParseType());
        to.setLcFixCode(from.getLcFixCode());
        to.setCreateDate(from.getCreateDate());
        to.setCreatedBy(from.getCreatedBy());
        to.setLastModifiedBy(from.getLastModifiedBy());
        to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static LoanCar toEntity(LoanCarDTO from){
        LoanCar to = new LoanCar();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LoanCar> toLoanCarList(List<LoanCarDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<LoanCar> toLoanCarList(List<LoanCarDTO> fromList, BiFunction<LoanCarDTO, LoanCar, LoanCar> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LoanCar to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    public static Specification<LoanCar> toSpec(LoanCarSearchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }

    public static Specification<LoanCar> toSpec(LoanCarSearchDTO searchDTO, Specification<LoanCar> sp){
        String customName = searchDTO.getCustomName();
        if(customName != null){
            sp = sp.and((r,q,c) -> c.equal(r.get("customName"),customName));
        }
        String customPh = searchDTO.getCustomPh();
        if(customPh != null){
            sp = sp.and((r,q,c) -> c.like(r.get("customPh"),"%" +customPh+ "%"));
        }
        java.time.LocalDateTime createDateFrom = searchDTO.getCreateDateFrom();
        java.time.LocalDateTime createDateTo = searchDTO.getCreateDateTo();
        if(createDateFrom !=null && createDateTo !=null){
            sp = sp.and((r,q,c) -> c.between(r.get("createDate"),createDateFrom,createDateTo));
        }
        else if(createDateFrom !=null){
            sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get("createDate"),createDateFrom));
        }
        else if(createDateTo !=null){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("createDate"),createDateTo));
        }
        return sp;
    }
    


}
