//ecd:-1610137433H20240531102700_V0.8
package io.elasticore.demo.crm.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.domain.Specification;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import io.elasticore.demo.crm.entity.*;
import io.elasticore.demo.crm.dto.*;
import io.elasticore.demo.crm.enums.*;



/**


 */


public class CrmTestMapper {

    
    public static void mapping(ContractGroup from, ContractGroupDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getGrpSeq()!=null)
            to.setGrpSeq(from.getGrpSeq());
        if(!isSkipNull || from.getGroupName()!=null)
            to.setGroupName(from.getGroupName());
    }
    
    
    public static void mapping(ContractGroup from, ContractGroupDTO to){
        mapping(from,to,false);
    }
    
    
    public static ContractGroupDTO toDTO(ContractGroup from){
        if(from==null) return null;
        ContractGroupDTO to = new ContractGroupDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ContractGroupDTO> toContractGroupDTOList(List<ContractGroup> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(CrmTestMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<ContractGroupDTO> toContractGroupDTOList(List<ContractGroup> fromList, BiFunction<ContractGroup, ContractGroupDTO, ContractGroupDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ContractGroupDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ContractGroupDTO from, ContractGroup to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getGrpSeq()!=null)
            to.setGrpSeq(from.getGrpSeq());
        if(!isSkipNull || from.getGroupName()!=null)
            to.setGroupName(from.getGroupName());
    }
    
    
    public static void mapping(ContractGroupDTO from, ContractGroup to){
        mapping(from,to,false);
    }
    
    
    public static ContractGroup toEntity(ContractGroupDTO from){
        if(from==null) return null;
        ContractGroup to = new ContractGroup();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ContractGroup> toContractGroupList(List<ContractGroupDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(CrmTestMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<ContractGroup> toContractGroupList(List<ContractGroupDTO> fromList, BiFunction<ContractGroupDTO, ContractGroup, ContractGroup> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ContractGroup to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    

}
