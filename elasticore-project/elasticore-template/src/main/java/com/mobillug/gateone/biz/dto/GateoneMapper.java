//ecd:-7896130H20240618012928_V0.8
package com.mobillug.gateone.biz.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import com.mobillug.gateone.biz.entity.*;
import com.mobillug.gateone.biz.dto.*;
import com.mobillug.gateone.biz.enums.*;



/**


 */


public class GateoneMapper {

    
    public static void mapping(LoginUser from, LoginUserDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        to.setAllowServieList(toMappingServiceDTOList(from.getAllowServieList()));
        if(!isSkipNull || from.getPassword()!=null)
            to.setPassword(from.getPassword());
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getUserId()!=null)
            to.setUserId(from.getUserId());
        if(!isSkipNull || from.getName()!=null)
            to.setName(from.getName());
        if(!isSkipNull || from.getPhone()!=null)
            to.setPhone(from.getPhone());
        if(!isSkipNull || from.getEmail()!=null)
            to.setEmail(from.getEmail());
        if(!isSkipNull || from.getStatus()!=null)
            to.setStatus(from.getStatus());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(LoginUser from, LoginUserDTO to){
        mapping(from,to,false);
    }
    
    
    public static LoginUserDTO toDTO(LoginUser from){
        if(from==null) return null;
        LoginUserDTO to = new LoginUserDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LoginUserDTO> toLoginUserDTOList(List<LoginUser> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(GateoneMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<LoginUserDTO> toLoginUserDTOList(List<LoginUser> fromList, BiFunction<LoginUser, LoginUserDTO, LoginUserDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LoginUserDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(LoginUser from, LoginUserSrchResultDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getUserId()!=null)
            to.setUserId(from.getUserId());
        if(!isSkipNull || from.getPassword()!=null)
            to.setPassword(from.getPassword());
        if(!isSkipNull || from.getName()!=null)
            to.setName(from.getName());
        if(!isSkipNull || from.getPhone()!=null)
            to.setPhone(from.getPhone());
        if(!isSkipNull || from.getEmail()!=null)
            to.setEmail(from.getEmail());
        if(!isSkipNull || from.getStatus()!=null)
            to.setStatus(from.getStatus());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(LoginUser from, LoginUserSrchResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static LoginUserSrchResultDTO toLoginUserSrchResultDTO(LoginUser from){
        if(from==null) return null;
        LoginUserSrchResultDTO to = new LoginUserSrchResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LoginUserSrchResultDTO> toLoginUserSrchResultDTOList(List<LoginUser> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(GateoneMapper::toLoginUserSrchResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<LoginUserSrchResultDTO> toLoginUserSrchResultDTOList(List<LoginUser> fromList, BiFunction<LoginUser, LoginUserSrchResultDTO, LoginUserSrchResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LoginUserSrchResultDTO to = toLoginUserSrchResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(LoginHistory from, LoginHistoryDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getSeq()!=null)
            to.setSeq(from.getSeq());
        if(!isSkipNull || from.getUserId()!=null)
            to.setUserId(from.getUserId());
        if(!isSkipNull || from.getSuccessYN()!=null)
            to.setSuccessYN(from.getSuccessYN());
        if(!isSkipNull || from.getToken()!=null)
            to.setToken(from.getToken());
        if(!isSkipNull || from.getAgentInfo()!=null)
            to.setAgentInfo(from.getAgentInfo());
        if(!isSkipNull || from.getClientIp()!=null)
            to.setClientIp(from.getClientIp());
        if(!isSkipNull || from.getExpireDateTime()!=null)
            to.setExpireDateTime(from.getExpireDateTime());
        if(!isSkipNull || from.getCreateDateTime()!=null)
            to.setCreateDateTime(from.getCreateDateTime());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(LoginHistory from, LoginHistoryDTO to){
        mapping(from,to,false);
    }
    
    
    public static LoginHistoryDTO toDTO(LoginHistory from){
        if(from==null) return null;
        LoginHistoryDTO to = new LoginHistoryDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LoginHistoryDTO> toLoginHistoryDTOList(List<LoginHistory> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(GateoneMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<LoginHistoryDTO> toLoginHistoryDTOList(List<LoginHistory> fromList, BiFunction<LoginHistory, LoginHistoryDTO, LoginHistoryDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LoginHistoryDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(LoginHistory from, LoginHistorySrchResultDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getSeq()!=null)
            to.setSeq(from.getSeq());
        if(!isSkipNull || from.getUserId()!=null)
            to.setUserId(from.getUserId());
        if(!isSkipNull || from.getSuccessYN()!=null)
            to.setSuccessYN(from.getSuccessYN());
        if(!isSkipNull || from.getToken()!=null)
            to.setToken(from.getToken());
        if(!isSkipNull || from.getAgentInfo()!=null)
            to.setAgentInfo(from.getAgentInfo());
        if(!isSkipNull || from.getClientIp()!=null)
            to.setClientIp(from.getClientIp());
        if(!isSkipNull || from.getExpireDateTime()!=null)
            to.setExpireDateTime(from.getExpireDateTime());
        if(!isSkipNull || from.getCreateDateTime()!=null)
            to.setCreateDateTime(from.getCreateDateTime());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(LoginHistory from, LoginHistorySrchResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static LoginHistorySrchResultDTO toLoginHistorySrchResultDTO(LoginHistory from){
        if(from==null) return null;
        LoginHistorySrchResultDTO to = new LoginHistorySrchResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LoginHistorySrchResultDTO> toLoginHistorySrchResultDTOList(List<LoginHistory> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(GateoneMapper::toLoginHistorySrchResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<LoginHistorySrchResultDTO> toLoginHistorySrchResultDTOList(List<LoginHistory> fromList, BiFunction<LoginHistory, LoginHistorySrchResultDTO, LoginHistorySrchResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LoginHistorySrchResultDTO to = toLoginHistorySrchResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ServiceInfo from, ServiceInfoDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getName()!=null)
            to.setName(from.getName());
        if(!isSkipNull || from.getKeyName()!=null)
            to.setKeyName(from.getKeyName());
        if(!isSkipNull || from.getMainUrl()!=null)
            to.setMainUrl(from.getMainUrl());
        if(!isSkipNull || from.getLoginUrl()!=null)
            to.setLoginUrl(from.getLoginUrl());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(ServiceInfo from, ServiceInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static ServiceInfoDTO toDTO(ServiceInfo from){
        if(from==null) return null;
        ServiceInfoDTO to = new ServiceInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ServiceInfoDTO> toServiceInfoDTOList(List<ServiceInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(GateoneMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<ServiceInfoDTO> toServiceInfoDTOList(List<ServiceInfo> fromList, BiFunction<ServiceInfo, ServiceInfoDTO, ServiceInfoDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ServiceInfoDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ServiceInfo from, ServiceInfoSrchResultDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getName()!=null)
            to.setName(from.getName());
        if(!isSkipNull || from.getKeyName()!=null)
            to.setKeyName(from.getKeyName());
        if(!isSkipNull || from.getMainUrl()!=null)
            to.setMainUrl(from.getMainUrl());
        if(!isSkipNull || from.getLoginUrl()!=null)
            to.setLoginUrl(from.getLoginUrl());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(ServiceInfo from, ServiceInfoSrchResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static ServiceInfoSrchResultDTO toServiceInfoSrchResultDTO(ServiceInfo from){
        if(from==null) return null;
        ServiceInfoSrchResultDTO to = new ServiceInfoSrchResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ServiceInfoSrchResultDTO> toServiceInfoSrchResultDTOList(List<ServiceInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(GateoneMapper::toServiceInfoSrchResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<ServiceInfoSrchResultDTO> toServiceInfoSrchResultDTOList(List<ServiceInfo> fromList, BiFunction<ServiceInfo, ServiceInfoSrchResultDTO, ServiceInfoSrchResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ServiceInfoSrchResultDTO to = toServiceInfoSrchResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(MappingService from, MappingServiceDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(from.getService()!=null)
            to.setServiceId(from.getService().getId());
        if(from.getService()!=null)
            to.setServiceName(from.getService().getName());
        if(from.getService()!=null)
            to.setServiceKeyName(from.getService().getKeyName());
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getRole()!=null)
            to.setRole(from.getRole());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(MappingService from, MappingServiceDTO to){
        mapping(from,to,false);
    }
    
    
    public static MappingServiceDTO toDTO(MappingService from){
        if(from==null) return null;
        MappingServiceDTO to = new MappingServiceDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<MappingServiceDTO> toMappingServiceDTOList(List<MappingService> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(GateoneMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<MappingServiceDTO> toMappingServiceDTOList(List<MappingService> fromList, BiFunction<MappingService, MappingServiceDTO, MappingServiceDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                MappingServiceDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(LoginUserDTO from, LoginUser to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getUserId()!=null)
            to.setUserId(from.getUserId());
        if(!isSkipNull || from.getPassword()!=null)
            to.setPassword(from.getPassword());
        if(!isSkipNull || from.getName()!=null)
            to.setName(from.getName());
        if(!isSkipNull || from.getPhone()!=null)
            to.setPhone(from.getPhone());
        if(!isSkipNull || from.getEmail()!=null)
            to.setEmail(from.getEmail());
        if(!isSkipNull || from.getStatus()!=null)
            to.setStatus(from.getStatus());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
        to.setAllowServieList(toMappingServiceList(from.getAllowServieList()));
    }
    
    
    public static void mapping(LoginUserDTO from, LoginUser to){
        mapping(from,to,false);
    }
    
    
    public static LoginUser toEntity(LoginUserDTO from){
        if(from==null) return null;
        LoginUser to = new LoginUser();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LoginUser> toLoginUserList(List<LoginUserDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(GateoneMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<LoginUser> toLoginUserList(List<LoginUserDTO> fromList, BiFunction<LoginUserDTO, LoginUser, LoginUser> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LoginUser to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(LoginHistoryDTO from, LoginHistory to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getSeq()!=null)
            to.setSeq(from.getSeq());
        if(!isSkipNull || from.getUserId()!=null)
            to.setUserId(from.getUserId());
        if(!isSkipNull || from.getSuccessYN()!=null)
            to.setSuccessYN(from.getSuccessYN());
        if(!isSkipNull || from.getToken()!=null)
            to.setToken(from.getToken());
        if(!isSkipNull || from.getAgentInfo()!=null)
            to.setAgentInfo(from.getAgentInfo());
        if(!isSkipNull || from.getClientIp()!=null)
            to.setClientIp(from.getClientIp());
        if(!isSkipNull || from.getExpireDateTime()!=null)
            to.setExpireDateTime(from.getExpireDateTime());
        if(!isSkipNull || from.getCreateDateTime()!=null)
            to.setCreateDateTime(from.getCreateDateTime());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(LoginHistoryDTO from, LoginHistory to){
        mapping(from,to,false);
    }
    
    
    public static LoginHistory toEntity(LoginHistoryDTO from){
        if(from==null) return null;
        LoginHistory to = new LoginHistory();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LoginHistory> toLoginHistoryList(List<LoginHistoryDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(GateoneMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<LoginHistory> toLoginHistoryList(List<LoginHistoryDTO> fromList, BiFunction<LoginHistoryDTO, LoginHistory, LoginHistory> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LoginHistory to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ServiceInfoDTO from, ServiceInfo to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getName()!=null)
            to.setName(from.getName());
        if(!isSkipNull || from.getKeyName()!=null)
            to.setKeyName(from.getKeyName());
        if(!isSkipNull || from.getMainUrl()!=null)
            to.setMainUrl(from.getMainUrl());
        if(!isSkipNull || from.getLoginUrl()!=null)
            to.setLoginUrl(from.getLoginUrl());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(ServiceInfoDTO from, ServiceInfo to){
        mapping(from,to,false);
    }
    
    
    public static ServiceInfo toEntity(ServiceInfoDTO from){
        if(from==null) return null;
        ServiceInfo to = new ServiceInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ServiceInfo> toServiceInfoList(List<ServiceInfoDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(GateoneMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<ServiceInfo> toServiceInfoList(List<ServiceInfoDTO> fromList, BiFunction<ServiceInfoDTO, ServiceInfo, ServiceInfo> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ServiceInfo to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(MappingServiceDTO from, MappingService to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getRole()!=null)
            to.setRole(from.getRole());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
        
        {
            ServiceInfo t = new ServiceInfo();
            t.setId(from.getServiceId());
            to.setService(t);
        }
    }
    
    
    public static void mapping(MappingServiceDTO from, MappingService to){
        mapping(from,to,false);
    }
    
    
    public static MappingService toEntity(MappingServiceDTO from){
        if(from==null) return null;
        MappingService to = new MappingService();
        mapping(from, to);
        return to;
    }
    
    
    public static List<MappingService> toMappingServiceList(List<MappingServiceDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(GateoneMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<MappingService> toMappingServiceList(List<MappingServiceDTO> fromList, BiFunction<MappingServiceDTO, MappingService, MappingService> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                MappingService to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<LoginUser> toSpec(LoginUserSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<LoginUser> toSpec(LoginUserSrchDTO searchDTO, Specification<LoginUser> sp){
        String userId = searchDTO.getUserId();
        if(hasValue(userId)){
            sp = sp.and((r,q,c) -> c.like(r.get("userId"),"%" +userId+ "%"));
        }
        String name = searchDTO.getName();
        if(hasValue(name)){
            sp = sp.and((r,q,c) -> c.like(r.get("name"),"%" +name+ "%"));
        }
        String phone = searchDTO.getPhone();
        if(hasValue(phone)){
            sp = sp.and((r,q,c) -> c.like(r.get("phone"),"%" +phone+ "%"));
        }
        String email = searchDTO.getEmail();
        if(hasValue(email)){
            sp = sp.and((r,q,c) -> c.like(r.get("email"),"%" +email+ "%"));
        }
        return sp;
    }
    
    
    public static Specification<LoginHistory> toSpec(LoginHistorySrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<LoginHistory> toSpec(LoginHistorySrchDTO searchDTO, Specification<LoginHistory> sp){
        String userId = searchDTO.getUserId();
        if(hasValue(userId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("userId"),userId));
        }
        Indicator successYN = searchDTO.getSuccessYN();
        if(hasValue(successYN)){
            sp = sp.and((r,q,c) -> c.equal(r.get("successYN"),successYN));
        }
        String agentInfo = searchDTO.getAgentInfo();
        if(hasValue(agentInfo)){
            sp = sp.and((r,q,c) -> c.like(r.get("agentInfo"),"%" +agentInfo+ "%"));
        }
        String clientIp = searchDTO.getClientIp();
        if(hasValue(clientIp)){
            sp = sp.and((r,q,c) -> c.like(r.get("clientIp"),"%" +clientIp+ "%"));
        }
        java.time.LocalDateTime expireDateTimeFrom = searchDTO.getExpireDateTimeFrom();
        java.time.LocalDateTime expireDateTimeTo = searchDTO.getExpireDateTimeTo();
        if(hasValue(expireDateTimeFrom) && hasValue(expireDateTimeTo)){
            sp = sp.and((r,q,c) -> c.between(r.get("expireDateTime"),expireDateTimeFrom,expireDateTimeTo));
        }
        else if(hasValue(expireDateTimeFrom)){
            sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get("expireDateTime"),expireDateTimeFrom));
        }
        else if(hasValue(expireDateTimeTo)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("expireDateTime"),expireDateTimeTo));
        }
        return sp;
    }
    
    
    public static Specification<ServiceInfo> toSpec(ServiceInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<ServiceInfo> toSpec(ServiceInfoSrchDTO searchDTO, Specification<ServiceInfo> sp){
        String name = searchDTO.getName();
        if(hasValue(name)){
            sp = sp.and((r,q,c) -> c.like(r.get("name"),"%" +name+ "%"));
        }
        String keyName = searchDTO.getKeyName();
        if(hasValue(keyName)){
            sp = sp.and((r,q,c) -> c.like(r.get("keyName"),"%" +keyName+ "%"));
        }
        String mainUrl = searchDTO.getMainUrl();
        if(hasValue(mainUrl)){
            sp = sp.and((r,q,c) -> c.like(r.get("mainUrl"),"%" +mainUrl+ "%"));
        }
        String loginUrl = searchDTO.getLoginUrl();
        if(hasValue(loginUrl)){
            sp = sp.and((r,q,c) -> c.like(r.get("loginUrl"),"%" +loginUrl+ "%"));
        }
        return sp;
    }
    



private static boolean hasValue(String val) {
        if(val != null && !val.isEmpty())
            return true;

        return false;
    }

    private static boolean hasValue(java.time.LocalDateTime dateTime) {
        if(dateTime !=null)
            return true;

        return false;
    }

    private static boolean hasValue(Object val) {
        if(val != null )
            return true;

        return false;
    }
}
