//ecd:518229402H20240618013023_V0.8
package com.mobillug.linkone.biz.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import com.mobillug.linkone.biz.entity.*;
import com.mobillug.linkone.biz.dto.*;
import com.mobillug.linkone.biz.enums.*;



/**


 */


public class LinkoneMapper {

    
    public static void mapping(CommonCode from, CommonCodeDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getCodeSn()!=null)
            to.setCodeSn(from.getCodeSn());
        if(!isSkipNull || from.getCodeId()!=null)
            to.setCodeId(from.getCodeId());
        if(!isSkipNull || from.getCodeNm()!=null)
            to.setCodeNm(from.getCodeNm());
        if(!isSkipNull || from.getCodeValue()!=null)
            to.setCodeValue(from.getCodeValue());
        if(!isSkipNull || from.getCodeOptn1()!=null)
            to.setCodeOptn1(from.getCodeOptn1());
        if(!isSkipNull || from.getCodeOptn2()!=null)
            to.setCodeOptn2(from.getCodeOptn2());
        if(!isSkipNull || from.getCodeOptn3()!=null)
            to.setCodeOptn3(from.getCodeOptn3());
        if(!isSkipNull || from.getCodeDepth()!=null)
            to.setCodeDepth(from.getCodeDepth());
        if(!isSkipNull || from.getCodeOrder()!=null)
            to.setCodeOrder(from.getCodeOrder());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(CommonCode from, CommonCodeDTO to){
        mapping(from,to,false);
    }
    
    
    public static CommonCodeDTO toDTO(CommonCode from){
        if(from==null) return null;
        CommonCodeDTO to = new CommonCodeDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CommonCodeDTO> toCommonCodeDTOList(List<CommonCode> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CommonCodeDTO> toCommonCodeDTOList(List<CommonCode> fromList, BiFunction<CommonCode, CommonCodeDTO, CommonCodeDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CommonCodeDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(Company from, CompanyDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        to.setUserList(toCustUserDTOList(from.getUserList()));
        if(from.getPartnerCust()!=null)
            to.setPartCustId(from.getPartnerCust().getCodeId());
        if(from.getPartnerCust()!=null)
            to.setPartCustNm(from.getPartnerCust().getCodeNm());
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getComGrpCode()!=null)
            to.setComGrpCode(from.getComGrpCode());
        if(!isSkipNull || from.getAreaCodeList()!=null)
            to.setAreaCodeList(from.getAreaCodeList());
        if(!isSkipNull || from.getComName()!=null)
            to.setComName(from.getComName());
        if(!isSkipNull || from.getRespName()!=null)
            to.setRespName(from.getRespName());
        if(!isSkipNull || from.getRespTel()!=null)
            to.setRespTel(from.getRespTel());
        if(!isSkipNull || from.getRespZone()!=null)
            to.setRespZone(from.getRespZone());
        if(!isSkipNull || from.getUseYn()!=null)
            to.setUseYn(from.getUseYn());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(Company from, CompanyDTO to){
        mapping(from,to,false);
    }
    
    
    public static CompanyDTO toDTO(Company from){
        if(from==null) return null;
        CompanyDTO to = new CompanyDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CompanyDTO> toCompanyDTOList(List<Company> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CompanyDTO> toCompanyDTOList(List<Company> fromList, BiFunction<Company, CompanyDTO, CompanyDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CompanyDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(Company from, CompanySearchResultDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(from.getPartnerCust()!=null)
            to.setPartCustId(from.getPartnerCust().getCodeId());
        if(from.getPartnerCust()!=null)
            to.setPartCustNm(from.getPartnerCust().getCodeNm());
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getComGrpCode()!=null)
            to.setComGrpCode(from.getComGrpCode());
        if(!isSkipNull || from.getAreaCodeList()!=null)
            to.setAreaCodeList(from.getAreaCodeList());
        if(!isSkipNull || from.getComName()!=null)
            to.setComName(from.getComName());
        if(!isSkipNull || from.getRespName()!=null)
            to.setRespName(from.getRespName());
        if(!isSkipNull || from.getRespTel()!=null)
            to.setRespTel(from.getRespTel());
        if(!isSkipNull || from.getUseYn()!=null)
            to.setUseYn(from.getUseYn());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(Company from, CompanySearchResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static CompanySearchResultDTO toCompanySearchResultDTO(Company from){
        if(from==null) return null;
        CompanySearchResultDTO to = new CompanySearchResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CompanySearchResultDTO> toCompanySearchResultDTOList(List<Company> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toCompanySearchResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<CompanySearchResultDTO> toCompanySearchResultDTOList(List<Company> fromList, BiFunction<Company, CompanySearchResultDTO, CompanySearchResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CompanySearchResultDTO to = toCompanySearchResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CustUser from, CustUserDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(from.getCompany()!=null)
            to.setCompanyId(from.getCompany().getId());
        if(from.getCompany()!=null)
            to.setComRespName(from.getCompany().getRespName());
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getUsrId()!=null)
            to.setUsrId(from.getUsrId());
        if(!isSkipNull || from.getPassword()!=null)
            to.setPassword(from.getPassword());
        if(!isSkipNull || from.getName()!=null)
            to.setName(from.getName());
        if(!isSkipNull || from.getTelNo()!=null)
            to.setTelNo(from.getTelNo());
        if(!isSkipNull || from.getEmail()!=null)
            to.setEmail(from.getEmail());
        if(!isSkipNull || from.getDeptNm()!=null)
            to.setDeptNm(from.getDeptNm());
        if(!isSkipNull || from.getGrade()!=null)
            to.setGrade(from.getGrade());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(CustUser from, CustUserDTO to){
        mapping(from,to,false);
    }
    
    
    public static CustUserDTO toDTO(CustUser from){
        if(from==null) return null;
        CustUserDTO to = new CustUserDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CustUserDTO> toCustUserDTOList(List<CustUser> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CustUserDTO> toCustUserDTOList(List<CustUser> fromList, BiFunction<CustUser, CustUserDTO, CustUserDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CustUserDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(LoanCarProcess from, LoanCarProcessDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(from.getRentCompany()!=null)
            to.setRentComId(from.getRentCompany().getId());
        to.setCallHistory(toDTO(from.getCallHistory()));
        if(!isSkipNull || from.getLcpCode()!=null)
            to.setLcpCode(from.getLcpCode());
        if(!isSkipNull || from.getProcessType()!=null)
            to.setProcessType(from.getProcessType());
        if(!isSkipNull || from.getApplyDate()!=null)
            to.setApplyDate(from.getApplyDate());
        if(!isSkipNull || from.getApplyTime()!=null)
            to.setApplyTime(from.getApplyTime());
        if(!isSkipNull || from.getMemo()!=null)
            to.setMemo(from.getMemo());
        if(!isSkipNull || from.getLoanCarMasterId()!=null)
            to.setLoanCarMasterId(from.getLoanCarMasterId());
        if(!isSkipNull || from.getCustReqMemo()!=null)
            to.setCustReqMemo(from.getCustReqMemo());
        if(!isSkipNull || from.getReqCarName()!=null)
            to.setReqCarName(from.getReqCarName());
        if(!isSkipNull || from.getReqCarNo()!=null)
            to.setReqCarNo(from.getReqCarNo());
        if(!isSkipNull || from.getInsureYN()!=null)
            to.setInsureYN(from.getInsureYN());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(LoanCarProcess from, LoanCarProcessDTO to){
        mapping(from,to,false);
    }
    
    
    public static LoanCarProcessDTO toDTO(LoanCarProcess from){
        if(from==null) return null;
        LoanCarProcessDTO to = new LoanCarProcessDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LoanCarProcessDTO> toLoanCarProcessDTOList(List<LoanCarProcess> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<LoanCarProcessDTO> toLoanCarProcessDTOList(List<LoanCarProcess> fromList, BiFunction<LoanCarProcess, LoanCarProcessDTO, LoanCarProcessDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LoanCarProcessDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CallHistory from, CallHistoryDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getCallCode()!=null)
            to.setCallCode(from.getCallCode());
        if(!isSkipNull || from.getCallContent()!=null)
            to.setCallContent(from.getCallContent());
        if(!isSkipNull || from.getCallDate()!=null)
            to.setCallDate(from.getCallDate());
        if(!isSkipNull || from.getCallTime()!=null)
            to.setCallTime(from.getCallTime());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(CallHistory from, CallHistoryDTO to){
        mapping(from,to,false);
    }
    
    
    public static CallHistoryDTO toDTO(CallHistory from){
        if(from==null) return null;
        CallHistoryDTO to = new CallHistoryDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CallHistoryDTO> toCallHistoryDTOList(List<CallHistory> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CallHistoryDTO> toCallHistoryDTOList(List<CallHistory> fromList, BiFunction<CallHistory, CallHistoryDTO, CallHistoryDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CallHistoryDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(LoanCar from, LoanCarDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(from.getRentCompany()!=null)
            to.setRentComId(from.getRentCompany().getId());
        if(from.getRentCompany()!=null)
            to.setRentComName(from.getRentCompany().getComName());
        to.setProcessHistory(toLoanCarProcessDTOList(from.getProcessHistory()));
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getLcCode()!=null)
            to.setLcCode(from.getLcCode());
        if(!isSkipNull || from.getStatusType()!=null)
            to.setStatusType(from.getStatusType());
        if(!isSkipNull || from.getEmpNo()!=null)
            to.setEmpNo(from.getEmpNo());
        if(!isSkipNull || from.getRentKind()!=null)
            to.setRentKind(from.getRentKind());
        if(!isSkipNull || from.getRentCarCode()!=null)
            to.setRentCarCode(from.getRentCarCode());
        if(!isSkipNull || from.getRentCarNm()!=null)
            to.setRentCarNm(from.getRentCarNm());
        if(!isSkipNull || from.getRentCost()!=null)
            to.setRentCost(from.getRentCost());
        if(!isSkipNull || from.getInsurNm()!=null)
            to.setInsurNm(from.getInsurNm());
        if(!isSkipNull || from.getIndeMoney()!=null)
            to.setIndeMoney(from.getIndeMoney());
        if(!isSkipNull || from.getRegDate()!=null)
            to.setRegDate(from.getRegDate());
        if(!isSkipNull || from.getRegTime()!=null)
            to.setRegTime(from.getRegTime());
        if(!isSkipNull || from.getJoinCode()!=null)
            to.setJoinCode(from.getJoinCode());
        if(!isSkipNull || from.getCustNm()!=null)
            to.setCustNm(from.getCustNm());
        if(!isSkipNull || from.getCustTel()!=null)
            to.setCustTel(from.getCustTel());
        if(!isSkipNull || from.getVipYn()!=null)
            to.setVipYn(from.getVipYn());
        if(!isSkipNull || from.getEtcDesc()!=null)
            to.setEtcDesc(from.getEtcDesc());
        if(!isSkipNull || from.getReqDate()!=null)
            to.setReqDate(from.getReqDate());
        if(!isSkipNull || from.getReqTime()!=null)
            to.setReqTime(from.getReqTime());
        if(!isSkipNull || from.getReqZip()!=null)
            to.setReqZip(from.getReqZip());
        if(!isSkipNull || from.getReqAddr()!=null)
            to.setReqAddr(from.getReqAddr());
        if(!isSkipNull || from.getReqDaddr()!=null)
            to.setReqDaddr(from.getReqDaddr());
        if(!isSkipNull || from.getConsignYn()!=null)
            to.setConsignYn(from.getConsignYn());
        if(!isSkipNull || from.getConsignOfiiceNm()!=null)
            to.setConsignOfiiceNm(from.getConsignOfiiceNm());
        if(!isSkipNull || from.getConsignZip()!=null)
            to.setConsignZip(from.getConsignZip());
        if(!isSkipNull || from.getConsignAddr()!=null)
            to.setConsignAddr(from.getConsignAddr());
        if(!isSkipNull || from.getConsignDaddr()!=null)
            to.setConsignDaddr(from.getConsignDaddr());
        if(!isSkipNull || from.getConsignTel()!=null)
            to.setConsignTel(from.getConsignTel());
        if(!isSkipNull || from.getLcReason()!=null)
            to.setLcReason(from.getLcReason());
        if(!isSkipNull || from.getSmsParseType()!=null)
            to.setSmsParseType(from.getSmsParseType());
        if(!isSkipNull || from.getContent()!=null)
            to.setContent(from.getContent());
        if(!isSkipNull || from.getPartnerEtcNm()!=null)
            to.setPartnerEtcNm(from.getPartnerEtcNm());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(LoanCar from, LoanCarDTO to){
        mapping(from,to,false);
    }
    
    
    public static LoanCarDTO toDTO(LoanCar from){
        if(from==null) return null;
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
    
    
    public static void mapping(LoanCar from, LoanCarSearchResultDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(from.getRentCompany()!=null)
            to.setRentComId(from.getRentCompany().getId());
        if(from.getRentCompany()!=null)
            to.setRentCompanyName(from.getRentCompany().getComName());
        if(from.getPartnerCust()!=null)
            to.setPartCustCd(from.getPartnerCust().getCodeId());
        to.setProcessHistory(toLoanCarProcessDTOList(from.getProcessHistory()));
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getLcCode()!=null)
            to.setLcCode(from.getLcCode());
        if(!isSkipNull || from.getStatusType()!=null)
            to.setStatusType(from.getStatusType());
        if(!isSkipNull || from.getEmpNo()!=null)
            to.setEmpNo(from.getEmpNo());
        if(!isSkipNull || from.getRentKind()!=null)
            to.setRentKind(from.getRentKind());
        if(!isSkipNull || from.getRentCarCode()!=null)
            to.setRentCarCode(from.getRentCarCode());
        if(!isSkipNull || from.getRentCarNm()!=null)
            to.setRentCarNm(from.getRentCarNm());
        if(!isSkipNull || from.getRentCost()!=null)
            to.setRentCost(from.getRentCost());
        if(!isSkipNull || from.getInsurNm()!=null)
            to.setInsurNm(from.getInsurNm());
        if(!isSkipNull || from.getIndeMoney()!=null)
            to.setIndeMoney(from.getIndeMoney());
        if(!isSkipNull || from.getRegDate()!=null)
            to.setRegDate(from.getRegDate());
        if(!isSkipNull || from.getRegTime()!=null)
            to.setRegTime(from.getRegTime());
        if(!isSkipNull || from.getJoinCode()!=null)
            to.setJoinCode(from.getJoinCode());
        if(!isSkipNull || from.getCustNm()!=null)
            to.setCustNm(from.getCustNm());
        if(!isSkipNull || from.getCustTel()!=null)
            to.setCustTel(from.getCustTel());
        if(!isSkipNull || from.getVipYn()!=null)
            to.setVipYn(from.getVipYn());
        if(!isSkipNull || from.getEtcDesc()!=null)
            to.setEtcDesc(from.getEtcDesc());
        if(!isSkipNull || from.getReqDate()!=null)
            to.setReqDate(from.getReqDate());
        if(!isSkipNull || from.getReqTime()!=null)
            to.setReqTime(from.getReqTime());
        if(!isSkipNull || from.getReqZip()!=null)
            to.setReqZip(from.getReqZip());
        if(!isSkipNull || from.getReqAddr()!=null)
            to.setReqAddr(from.getReqAddr());
        if(!isSkipNull || from.getReqDaddr()!=null)
            to.setReqDaddr(from.getReqDaddr());
        if(!isSkipNull || from.getConsignYn()!=null)
            to.setConsignYn(from.getConsignYn());
        if(!isSkipNull || from.getConsignOfiiceNm()!=null)
            to.setConsignOfiiceNm(from.getConsignOfiiceNm());
        if(!isSkipNull || from.getConsignZip()!=null)
            to.setConsignZip(from.getConsignZip());
        if(!isSkipNull || from.getConsignAddr()!=null)
            to.setConsignAddr(from.getConsignAddr());
        if(!isSkipNull || from.getConsignDaddr()!=null)
            to.setConsignDaddr(from.getConsignDaddr());
        if(!isSkipNull || from.getConsignTel()!=null)
            to.setConsignTel(from.getConsignTel());
        if(!isSkipNull || from.getSmsParseType()!=null)
            to.setSmsParseType(from.getSmsParseType());
        if(!isSkipNull || from.getPartnerEtcNm()!=null)
            to.setPartnerEtcNm(from.getPartnerEtcNm());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(LoanCar from, LoanCarSearchResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static LoanCarSearchResultDTO toLoanCarSearchResultDTO(LoanCar from){
        if(from==null) return null;
        LoanCarSearchResultDTO to = new LoanCarSearchResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LoanCarSearchResultDTO> toLoanCarSearchResultDTOList(List<LoanCar> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toLoanCarSearchResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<LoanCarSearchResultDTO> toLoanCarSearchResultDTOList(List<LoanCar> fromList, BiFunction<LoanCar, LoanCarSearchResultDTO, LoanCarSearchResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LoanCarSearchResultDTO to = toLoanCarSearchResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CommonCodeDTO from, CommonCode to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getCodeSn()!=null)
            to.setCodeSn(from.getCodeSn());
        if(!isSkipNull || from.getCodeId()!=null)
            to.setCodeId(from.getCodeId());
        if(!isSkipNull || from.getCodeNm()!=null)
            to.setCodeNm(from.getCodeNm());
        if(!isSkipNull || from.getCodeValue()!=null)
            to.setCodeValue(from.getCodeValue());
        if(!isSkipNull || from.getCodeOptn1()!=null)
            to.setCodeOptn1(from.getCodeOptn1());
        if(!isSkipNull || from.getCodeOptn2()!=null)
            to.setCodeOptn2(from.getCodeOptn2());
        if(!isSkipNull || from.getCodeOptn3()!=null)
            to.setCodeOptn3(from.getCodeOptn3());
        if(!isSkipNull || from.getCodeDepth()!=null)
            to.setCodeDepth(from.getCodeDepth());
        if(!isSkipNull || from.getCodeOrder()!=null)
            to.setCodeOrder(from.getCodeOrder());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(CommonCodeDTO from, CommonCode to){
        mapping(from,to,false);
    }
    
    
    public static CommonCode toEntity(CommonCodeDTO from){
        if(from==null) return null;
        CommonCode to = new CommonCode();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CommonCode> toCommonCodeList(List<CommonCodeDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<CommonCode> toCommonCodeList(List<CommonCodeDTO> fromList, BiFunction<CommonCodeDTO, CommonCode, CommonCode> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CommonCode to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CustUserDTO from, CustUser to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getUsrId()!=null)
            to.setUsrId(from.getUsrId());
        if(!isSkipNull || from.getPassword()!=null)
            to.setPassword(from.getPassword());
        if(!isSkipNull || from.getName()!=null)
            to.setName(from.getName());
        if(!isSkipNull || from.getTelNo()!=null)
            to.setTelNo(from.getTelNo());
        if(!isSkipNull || from.getEmail()!=null)
            to.setEmail(from.getEmail());
        if(!isSkipNull || from.getDeptNm()!=null)
            to.setDeptNm(from.getDeptNm());
        if(!isSkipNull || from.getGrade()!=null)
            to.setGrade(from.getGrade());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
        
        {
            Company t = new Company();
            t.setId(from.getCompanyId());
            to.setCompany(t);
        }
    }
    
    
    public static void mapping(CustUserDTO from, CustUser to){
        mapping(from,to,false);
    }
    
    
    public static CustUser toEntity(CustUserDTO from){
        if(from==null) return null;
        CustUser to = new CustUser();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CustUser> toCustUserList(List<CustUserDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<CustUser> toCustUserList(List<CustUserDTO> fromList, BiFunction<CustUserDTO, CustUser, CustUser> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CustUser to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CompanyDTO from, Company to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getComGrpCode()!=null)
            to.setComGrpCode(from.getComGrpCode());
        if(!isSkipNull || from.getAreaCodeList()!=null)
            to.setAreaCodeList(from.getAreaCodeList());
        if(!isSkipNull || from.getComName()!=null)
            to.setComName(from.getComName());
        if(!isSkipNull || from.getRespName()!=null)
            to.setRespName(from.getRespName());
        if(!isSkipNull || from.getRespTel()!=null)
            to.setRespTel(from.getRespTel());
        if(!isSkipNull || from.getRespZone()!=null)
            to.setRespZone(from.getRespZone());
        if(!isSkipNull || from.getUseYn()!=null)
            to.setUseYn(from.getUseYn());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
        to.setUserList(toCustUserList(from.getUserList()));
    }
    
    
    public static void mapping(CompanyDTO from, Company to){
        mapping(from,to,false);
    }
    
    
    public static Company toEntity(CompanyDTO from){
        if(from==null) return null;
        Company to = new Company();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Company> toCompanyList(List<CompanyDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Company> toCompanyList(List<CompanyDTO> fromList, BiFunction<CompanyDTO, Company, Company> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Company to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(LoanCarDTO from, LoanCar to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getId()!=null)
            to.setId(from.getId());
        if(!isSkipNull || from.getLcCode()!=null)
            to.setLcCode(from.getLcCode());
        if(!isSkipNull || from.getStatusType()!=null)
            to.setStatusType(from.getStatusType());
        if(!isSkipNull || from.getEmpNo()!=null)
            to.setEmpNo(from.getEmpNo());
        if(!isSkipNull || from.getRentKind()!=null)
            to.setRentKind(from.getRentKind());
        if(!isSkipNull || from.getRentCarCode()!=null)
            to.setRentCarCode(from.getRentCarCode());
        if(!isSkipNull || from.getRentCarNm()!=null)
            to.setRentCarNm(from.getRentCarNm());
        if(!isSkipNull || from.getRentCost()!=null)
            to.setRentCost(from.getRentCost());
        if(!isSkipNull || from.getInsurNm()!=null)
            to.setInsurNm(from.getInsurNm());
        if(!isSkipNull || from.getIndeMoney()!=null)
            to.setIndeMoney(from.getIndeMoney());
        if(!isSkipNull || from.getRegDate()!=null)
            to.setRegDate(from.getRegDate());
        if(!isSkipNull || from.getRegTime()!=null)
            to.setRegTime(from.getRegTime());
        if(!isSkipNull || from.getJoinCode()!=null)
            to.setJoinCode(from.getJoinCode());
        if(!isSkipNull || from.getCustNm()!=null)
            to.setCustNm(from.getCustNm());
        if(!isSkipNull || from.getCustTel()!=null)
            to.setCustTel(from.getCustTel());
        if(!isSkipNull || from.getVipYn()!=null)
            to.setVipYn(from.getVipYn());
        if(!isSkipNull || from.getEtcDesc()!=null)
            to.setEtcDesc(from.getEtcDesc());
        if(!isSkipNull || from.getReqDate()!=null)
            to.setReqDate(from.getReqDate());
        if(!isSkipNull || from.getReqTime()!=null)
            to.setReqTime(from.getReqTime());
        if(!isSkipNull || from.getReqZip()!=null)
            to.setReqZip(from.getReqZip());
        if(!isSkipNull || from.getReqAddr()!=null)
            to.setReqAddr(from.getReqAddr());
        if(!isSkipNull || from.getReqDaddr()!=null)
            to.setReqDaddr(from.getReqDaddr());
        if(!isSkipNull || from.getConsignYn()!=null)
            to.setConsignYn(from.getConsignYn());
        if(!isSkipNull || from.getConsignOfiiceNm()!=null)
            to.setConsignOfiiceNm(from.getConsignOfiiceNm());
        if(!isSkipNull || from.getConsignZip()!=null)
            to.setConsignZip(from.getConsignZip());
        if(!isSkipNull || from.getConsignAddr()!=null)
            to.setConsignAddr(from.getConsignAddr());
        if(!isSkipNull || from.getConsignDaddr()!=null)
            to.setConsignDaddr(from.getConsignDaddr());
        if(!isSkipNull || from.getConsignTel()!=null)
            to.setConsignTel(from.getConsignTel());
        if(!isSkipNull || from.getLcReason()!=null)
            to.setLcReason(from.getLcReason());
        if(!isSkipNull || from.getSmsParseType()!=null)
            to.setSmsParseType(from.getSmsParseType());
        if(!isSkipNull || from.getContent()!=null)
            to.setContent(from.getContent());
        if(!isSkipNull || from.getPartnerEtcNm()!=null)
            to.setPartnerEtcNm(from.getPartnerEtcNm());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
        
        {
            Company t = new Company();
            t.setId(from.getRentComId());
            to.setRentCompany(t);
        }
        to.setProcessHistory(toLoanCarProcessList(from.getProcessHistory()));
    }
    
    
    public static void mapping(LoanCarDTO from, LoanCar to){
        mapping(from,to,false);
    }
    
    
    public static LoanCar toEntity(LoanCarDTO from){
        if(from==null) return null;
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
    
    
    public static void mapping(LoanCarProcessDTO from, LoanCarProcess to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getLcpCode()!=null)
            to.setLcpCode(from.getLcpCode());
        if(!isSkipNull || from.getProcessType()!=null)
            to.setProcessType(from.getProcessType());
        if(!isSkipNull || from.getApplyDate()!=null)
            to.setApplyDate(from.getApplyDate());
        if(!isSkipNull || from.getApplyTime()!=null)
            to.setApplyTime(from.getApplyTime());
        if(!isSkipNull || from.getMemo()!=null)
            to.setMemo(from.getMemo());
        if(!isSkipNull || from.getLoanCarMasterId()!=null)
            to.setLoanCarMasterId(from.getLoanCarMasterId());
        if(!isSkipNull || from.getCustReqMemo()!=null)
            to.setCustReqMemo(from.getCustReqMemo());
        if(!isSkipNull || from.getReqCarName()!=null)
            to.setReqCarName(from.getReqCarName());
        if(!isSkipNull || from.getReqCarNo()!=null)
            to.setReqCarNo(from.getReqCarNo());
        if(!isSkipNull || from.getInsureYN()!=null)
            to.setInsureYN(from.getInsureYN());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
        
        {
            Company t = new Company();
            t.setId(from.getRentComId());
            to.setRentCompany(t);
        }
        to.setCallHistory(toEntity(from.getCallHistory()));
    }
    
    
    public static void mapping(LoanCarProcessDTO from, LoanCarProcess to){
        mapping(from,to,false);
    }
    
    
    public static LoanCarProcess toEntity(LoanCarProcessDTO from){
        if(from==null) return null;
        LoanCarProcess to = new LoanCarProcess();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LoanCarProcess> toLoanCarProcessList(List<LoanCarProcessDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<LoanCarProcess> toLoanCarProcessList(List<LoanCarProcessDTO> fromList, BiFunction<LoanCarProcessDTO, LoanCarProcess, LoanCarProcess> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LoanCarProcess to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CallHistoryDTO from, CallHistory to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getCallCode()!=null)
            to.setCallCode(from.getCallCode());
        if(!isSkipNull || from.getCallContent()!=null)
            to.setCallContent(from.getCallContent());
        if(!isSkipNull || from.getCallDate()!=null)
            to.setCallDate(from.getCallDate());
        if(!isSkipNull || from.getCallTime()!=null)
            to.setCallTime(from.getCallTime());
        if(!isSkipNull || from.getCreateDate()!=null)
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || from.getCreatedBy()!=null)
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || from.getLastModifiedBy()!=null)
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || from.getLastModifiedDate()!=null)
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(CallHistoryDTO from, CallHistory to){
        mapping(from,to,false);
    }
    
    
    public static CallHistory toEntity(CallHistoryDTO from){
        if(from==null) return null;
        CallHistory to = new CallHistory();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CallHistory> toCallHistoryList(List<CallHistoryDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(LinkoneMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<CallHistory> toCallHistoryList(List<CallHistoryDTO> fromList, BiFunction<CallHistoryDTO, CallHistory, CallHistory> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CallHistory to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<CommonCode> toSpec(CommonCodeSearchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CommonCode> toSpec(CommonCodeSearchDTO searchDTO, Specification<CommonCode> sp){
        String codeId = searchDTO.getCodeId();
        if(hasValue(codeId)){
            sp = sp.and((r,q,c) -> c.like(r.get("codeId"),codeId+ "%"));
        }
        String codeNm = searchDTO.getCodeNm();
        if(hasValue(codeNm)){
            sp = sp.and((r,q,c) -> c.like(r.get("codeNm"),"%" +codeNm+ "%"));
        }
        return sp;
    }
    
    
    public static Specification<CustUser> toSpec(CustUserSearchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CustUser> toSpec(CustUserSearchDTO searchDTO, Specification<CustUser> sp){
        Long companyId = searchDTO.getCompanyId();
        if(hasValue(companyId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("company").get("id"),companyId));
        }
        String name = searchDTO.getName();
        if(hasValue(name)){
            sp = sp.and((r,q,c) -> c.like(r.get("name"),"%" +name+ "%"));
        }
        String telNo = searchDTO.getTelNo();
        if(hasValue(telNo)){
            sp = sp.and((r,q,c) -> c.like(r.get("telNo"),"%" +telNo+ "%"));
        }
        String email = searchDTO.getEmail();
        if(hasValue(email)){
            sp = sp.and((r,q,c) -> c.like(r.get("email"),"%" +email+ "%"));
        }
        String deptNm = searchDTO.getDeptNm();
        if(hasValue(deptNm)){
            sp = sp.and((r,q,c) -> c.like(r.get("deptNm"),"%" +deptNm+ "%"));
        }
        String grade = searchDTO.getGrade();
        if(hasValue(grade)){
            sp = sp.and((r,q,c) -> c.like(r.get("grade"),"%" +grade+ "%"));
        }
        return sp;
    }
    
    
    public static Specification<Company> toSpec(CompanySearchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Company> toSpec(CompanySearchDTO searchDTO, Specification<Company> sp){
        CompanyGroupCode comGrpCode = searchDTO.getComGrpCode();
        if(hasValue(comGrpCode)){
            sp = sp.and((r,q,c) -> c.equal(r.get("comGrpCode"),comGrpCode));
        }
        AreaCode areaCodeListItem = searchDTO.getAreaCodeListItem();
        if(hasValue(areaCodeListItem)){
            sp = sp.and((root, query, criteriaBuilder) -> {
              Join<Company, AreaCode> join = root.join("areaCodeList");
              return criteriaBuilder.equal(join, areaCodeListItem);
            });
        }
        String comName = searchDTO.getComName();
        if(hasValue(comName)){
            sp = sp.and((r,q,c) -> c.like(r.get("comName"),"%" +comName+ "%"));
        }
        String respName = searchDTO.getRespName();
        if(hasValue(respName)){
            sp = sp.and((r,q,c) -> c.like(r.get("respName"),"%" +respName+ "%"));
        }
        String respTel = searchDTO.getRespTel();
        if(hasValue(respTel)){
            sp = sp.and((r,q,c) -> c.like(r.get("respTel"),"%" +respTel+ "%"));
        }
        String respZone = searchDTO.getRespZone();
        if(hasValue(respZone)){
            sp = sp.and((r,q,c) -> c.like(r.get("respZone"),"%" +respZone+ "%"));
        }
        Indicator useYn = searchDTO.getUseYn();
        if(hasValue(useYn)){
            sp = sp.and((r,q,c) -> c.equal(r.get("useYn"),useYn));
        }
        return sp;
    }
    
    
    public static Specification<LoanCar> toSpec(LoanCarSearchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<LoanCar> toSpec(LoanCarSearchDTO searchDTO, Specification<LoanCar> sp){
        String rentComId = searchDTO.getRentComId();
        if(hasValue(rentComId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("rentCompany").get("id"),rentComId));
        }
        String partCustCd = searchDTO.getPartCustCd();
        if(hasValue(partCustCd)){
            sp = sp.and((r,q,c) -> c.equal(r.get("partnerCust").get("codeId"),partCustCd));
        }
        java.time.LocalDateTime createDateFrom = searchDTO.getCreateDateFrom();
        java.time.LocalDateTime createDateTo = searchDTO.getCreateDateTo();
        if(hasValue(createDateFrom) && hasValue(createDateTo)){
            sp = sp.and((r,q,c) -> c.between(r.get("createDate"),createDateFrom,createDateTo));
        }
        else if(hasValue(createDateFrom)){
            sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get("createDate"),createDateFrom));
        }
        else if(hasValue(createDateTo)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("createDate"),createDateTo));
        }
        String rentCompanyName = searchDTO.getRentCompanyName();
        if(hasValue(rentCompanyName)){
            sp = sp.and((r,q,c) -> c.like(r.get("rentCompany").get("comName"),"%" +rentCompanyName+ "%"));
        }
        String lcCode = searchDTO.getLcCode();
        if(hasValue(lcCode)){
            sp = sp.and((r,q,c) -> c.like(r.get("lcCode"),lcCode+ "%"));
        }
        StatusType statusType = searchDTO.getStatusType();
        if(hasValue(statusType)){
            sp = sp.and((r,q,c) -> c.equal(r.get("statusType"),statusType));
        }
        RentKindType rentKind = searchDTO.getRentKind();
        if(hasValue(rentKind)){
            sp = sp.and((r,q,c) -> c.equal(r.get("rentKind"),rentKind));
        }
        String rentCarCode = searchDTO.getRentCarCode();
        if(hasValue(rentCarCode)){
            sp = sp.and((r,q,c) -> c.like(r.get("rentCarCode"),"%" +rentCarCode+ "%"));
        }
        String rentCarNm = searchDTO.getRentCarNm();
        if(hasValue(rentCarNm)){
            sp = sp.and((r,q,c) -> c.like(r.get("rentCarNm"),"%" +rentCarNm+ "%"));
        }
        String custNm = searchDTO.getCustNm();
        if(hasValue(custNm)){
            sp = sp.and((r,q,c) -> c.like(r.get("custNm"),"%" +custNm+ "%"));
        }
        String custTel = searchDTO.getCustTel();
        if(hasValue(custTel)){
            sp = sp.and((r,q,c) -> c.like(r.get("custTel"),"%" +custTel+ "%"));
        }
        return sp;
    }
    
    
    public static Specification<LoanCarProcess> toSpec(LoanCarPrsSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<LoanCarProcess> toSpec(LoanCarPrsSrchDTO searchDTO, Specification<LoanCarProcess> sp){
        String rentComId = searchDTO.getRentComId();
        if(hasValue(rentComId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("rentCompany").get("id"),rentComId));
        }
        RentCarProcessType processType = searchDTO.getProcessType();
        if(hasValue(processType)){
            sp = sp.and((r,q,c) -> c.equal(r.get("processType"),processType));
        }
        String memo = searchDTO.getMemo();
        if(hasValue(memo)){
            sp = sp.and((r,q,c) -> c.like(r.get("memo"),"%" +memo+ "%"));
        }
        String loanCarMasterId = searchDTO.getLoanCarMasterId();
        if(hasValue(loanCarMasterId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("loanCarMasterId"),loanCarMasterId));
        }
        String custReqMemo = searchDTO.getCustReqMemo();
        if(hasValue(custReqMemo)){
            sp = sp.and((r,q,c) -> c.like(r.get("custReqMemo"),"%" +custReqMemo+ "%"));
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
