//ecd:-613420767H20240805175916_V0.8
package com.mobillug.linkone.apisvc.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Join;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import com.mobillug.linkone.apisvc.entity.*;
import com.mobillug.linkone.apisvc.dto.*;
import com.mobillug.linkone.apisvc.enums.*;



/**


 */


public class ApisvcMapper {

    
    public static void mapping(ReData100 from, Re100 to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getEmpNo()))
            to.setEmpNo(from.getEmpNo());
        if(!isSkipNull || hasValue(from.getRentKind()))
            to.setRentKind(from.getRentKind());
        if(!isSkipNull || hasValue(from.getRentCarCode()))
            to.setRentCarCode(from.getRentCarCode());
        if(!isSkipNull || hasValue(from.getRentCarNm()))
            to.setRentCarNm(from.getRentCarNm());
        if(!isSkipNull || hasValue(from.getRentCost()))
            to.setRentCost(from.getRentCost());
        if(!isSkipNull || hasValue(from.getInsurNm()))
            to.setInsurNm(from.getInsurNm());
        if(!isSkipNull || hasValue(from.getIndeMoney()))
            to.setIndeMoney(from.getIndeMoney());
        if(!isSkipNull || hasValue(from.getRegDate()))
            to.setRegDate(from.getRegDate());
        if(!isSkipNull || hasValue(from.getRegTime()))
            to.setRegTime(from.getRegTime());
        if(!isSkipNull || hasValue(from.getJoincode()))
            to.setJoincode(from.getJoincode());
        if(!isSkipNull || hasValue(from.getCustNm()))
            to.setCustNm(from.getCustNm());
        if(!isSkipNull || hasValue(from.getCustTel()))
            to.setCustTel(from.getCustTel());
        if(!isSkipNull || hasValue(from.getVipYn()))
            to.setVipYn(from.getVipYn());
        if(!isSkipNull || hasValue(from.getBigo()))
            to.setBigo(from.getBigo());
        if(!isSkipNull || hasValue(from.getReqDate()))
            to.setReqDate(from.getReqDate());
        if(!isSkipNull || hasValue(from.getReqTime()))
            to.setReqTime(from.getReqTime());
        if(!isSkipNull || hasValue(from.getReqZip()))
            to.setReqZip(from.getReqZip());
        if(!isSkipNull || hasValue(from.getReqAddr()))
            to.setReqAddr(from.getReqAddr());
        if(!isSkipNull || hasValue(from.getReqDaddr()))
            to.setReqDaddr(from.getReqDaddr());
        if(!isSkipNull || hasValue(from.getConsignYn()))
            to.setConsignYn(from.getConsignYn());
        if(!isSkipNull || hasValue(from.getConsignOfiiceNm()))
            to.setConsignOfiiceNm(from.getConsignOfiiceNm());
        if(!isSkipNull || hasValue(from.getConsignZip()))
            to.setConsignZip(from.getConsignZip());
        if(!isSkipNull || hasValue(from.getConsignAddr()))
            to.setConsignAddr(from.getConsignAddr());
        if(!isSkipNull || hasValue(from.getConsignDaddr()))
            to.setConsignDaddr(from.getConsignDaddr());
        if(!isSkipNull || hasValue(from.getConsignTel()))
            to.setConsignTel(from.getConsignTel());
        if(!isSkipNull || hasValue(from.getPacketId()))
            to.setPacketId(from.getPacketId());
        if(!isSkipNull || hasValue(from.getMasterKey()))
            to.setMasterKey(from.getMasterKey());
        if(!isSkipNull || hasValue(from.getSendYmd()))
            to.setSendYmd(from.getSendYmd());
        if(!isSkipNull || hasValue(from.getSendHms()))
            to.setSendHms(from.getSendHms());
        if(!isSkipNull || hasValue(from.getReceptNo()))
            to.setReceptNo(from.getReceptNo());
        if(!isSkipNull || hasValue(from.getErrorCd()))
            to.setErrorCd(from.getErrorCd());
        if(!isSkipNull || hasValue(from.getErrorText()))
            to.setErrorText(from.getErrorText());
    }
    
    
    public static void mapping(ReData100 from, Re100 to){
        mapping(from,to,false);
    }
    
    
    public static Re100 toDTO(ReData100 from){
        if(from==null) return null;
        Re100 to = new Re100();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Re100> toRe100List(List<ReData100> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(ApisvcMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<Re100> toRe100List(List<ReData100> fromList, BiFunction<ReData100, Re100, Re100> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Re100 to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(Re100 from, ReData100 to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getEmpNo()))
            to.setEmpNo(from.getEmpNo());
        if(!isSkipNull || hasValue(from.getRentKind()))
            to.setRentKind(from.getRentKind());
        if(!isSkipNull || hasValue(from.getRentCarCode()))
            to.setRentCarCode(from.getRentCarCode());
        if(!isSkipNull || hasValue(from.getRentCarNm()))
            to.setRentCarNm(from.getRentCarNm());
        if(!isSkipNull || hasValue(from.getRentCost()))
            to.setRentCost(from.getRentCost());
        if(!isSkipNull || hasValue(from.getInsurNm()))
            to.setInsurNm(from.getInsurNm());
        if(!isSkipNull || hasValue(from.getIndeMoney()))
            to.setIndeMoney(from.getIndeMoney());
        if(!isSkipNull || hasValue(from.getRegDate()))
            to.setRegDate(from.getRegDate());
        if(!isSkipNull || hasValue(from.getRegTime()))
            to.setRegTime(from.getRegTime());
        if(!isSkipNull || hasValue(from.getJoincode()))
            to.setJoincode(from.getJoincode());
        if(!isSkipNull || hasValue(from.getCustNm()))
            to.setCustNm(from.getCustNm());
        if(!isSkipNull || hasValue(from.getCustTel()))
            to.setCustTel(from.getCustTel());
        if(!isSkipNull || hasValue(from.getVipYn()))
            to.setVipYn(from.getVipYn());
        if(!isSkipNull || hasValue(from.getBigo()))
            to.setBigo(from.getBigo());
        if(!isSkipNull || hasValue(from.getReqDate()))
            to.setReqDate(from.getReqDate());
        if(!isSkipNull || hasValue(from.getReqTime()))
            to.setReqTime(from.getReqTime());
        if(!isSkipNull || hasValue(from.getReqZip()))
            to.setReqZip(from.getReqZip());
        if(!isSkipNull || hasValue(from.getReqAddr()))
            to.setReqAddr(from.getReqAddr());
        if(!isSkipNull || hasValue(from.getReqDaddr()))
            to.setReqDaddr(from.getReqDaddr());
        if(!isSkipNull || hasValue(from.getConsignYn()))
            to.setConsignYn(from.getConsignYn());
        if(!isSkipNull || hasValue(from.getConsignOfiiceNm()))
            to.setConsignOfiiceNm(from.getConsignOfiiceNm());
        if(!isSkipNull || hasValue(from.getConsignZip()))
            to.setConsignZip(from.getConsignZip());
        if(!isSkipNull || hasValue(from.getConsignAddr()))
            to.setConsignAddr(from.getConsignAddr());
        if(!isSkipNull || hasValue(from.getConsignDaddr()))
            to.setConsignDaddr(from.getConsignDaddr());
        if(!isSkipNull || hasValue(from.getConsignTel()))
            to.setConsignTel(from.getConsignTel());
        if(!isSkipNull || hasValue(from.getPacketId()))
            to.setPacketId(from.getPacketId());
        if(!isSkipNull || hasValue(from.getMasterKey()))
            to.setMasterKey(from.getMasterKey());
        if(!isSkipNull || hasValue(from.getSendYmd()))
            to.setSendYmd(from.getSendYmd());
        if(!isSkipNull || hasValue(from.getSendHms()))
            to.setSendHms(from.getSendHms());
        if(!isSkipNull || hasValue(from.getReceptNo()))
            to.setReceptNo(from.getReceptNo());
        if(!isSkipNull || hasValue(from.getErrorCd()))
            to.setErrorCd(from.getErrorCd());
        if(!isSkipNull || hasValue(from.getErrorText()))
            to.setErrorText(from.getErrorText());
    }
    
    
    public static void mapping(Re100 from, ReData100 to){
        mapping(from,to,false);
    }
    
    
    public static ReData100 toEntity(Re100 from){
        if(from==null) return null;
        ReData100 to = new ReData100();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ReData100> toReData100List(List<Re100> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(ApisvcMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<ReData100> toReData100List(List<Re100> fromList, BiFunction<Re100, ReData100, ReData100> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ReData100 to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<ReData100> toSpec(Re100SrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<ReData100> toSpec(Re100SrchDTO searchDTO, Specification<ReData100> sp){
        String reqDate = searchDTO.getReqDate();
        if(hasValue(reqDate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("reqDate"),reqDate));
        }
        String reqTime = searchDTO.getReqTime();
        if(hasValue(reqTime)){
            sp = sp.and((r,q,c) -> c.equal(r.get("reqTime"),reqTime));
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

    private static boolean hasValue(List val) {
            if(val != null && val.size()>0)
                return true;

            return false;
    }

    private static boolean hasValue(Object val) {
        if(val != null )
            return true;

        return false;
    }
}
