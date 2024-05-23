//ecd:-1836282140H20240523142719V0.7
package io.elasticore.demo.crm.dto;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import io.elasticore.demo.crm.entity.*;

import io.elasticore.demo.crm.dto.*;




/**


 */


public class CrmTestMapper {


    
    public static void mapping(ContractGroup from, ContractGroupDTO to){
        to.setGrpSeq(from.getGrpSeq());
        to.setGroupName(from.getGroupName());
    }
    
    
    public static ContractGroupDTO toDTO(ContractGroup from){
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
    
    
    public static void mapping(ContractGroupDTO from, ContractGroup to){
        to.setGrpSeq(from.getGrpSeq());
        to.setGroupName(from.getGroupName());
    }
    
    
    public static ContractGroup toEntity(ContractGroupDTO from){
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
