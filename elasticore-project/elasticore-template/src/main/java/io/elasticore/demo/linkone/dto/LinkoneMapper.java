//ecd:279696594H20240528005455V0.7
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

    
    public static void mapping(Company from, CompanyDTO to){
        if(from ==null || to ==null) return;
        to.setComSeq(from.getComSeq());
        to.setComGrpCode(from.getComGrpCode());
        to.setComName(from.getComName());
        to.setRespName(from.getRespName());
        to.setRespTel(from.getRespTel());
        to.setRespZone(from.getRespZone());
        to.setCreateDate(from.getCreateDate());
        to.setCreatedBy(from.getCreatedBy());
        to.setLastModifiedBy(from.getLastModifiedBy());
        to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static CompanyDTO toDTO(Company from){
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
    
    
    public static void mapping(CustUser from, CustUserDTO to){
        if(from ==null || to ==null) return;
        if(from.getCompany()!=null)
            to.setCompanyComSeq(from.getCompany().getComSeq());
        to.setCompany(toDTO(from.getCompany()));
        to.setUsrSeq(from.getUsrSeq());
        to.setUsrId(from.getUsrId());
        to.setPassword(from.getPassword());
        to.setName(from.getName());
        to.setTelNo(from.getTelNo());
        to.setEmail(from.getEmail());
        to.setDeptNm(from.getDeptNm());
        to.setGrade(from.getGrade());
        to.setCreateDate(from.getCreateDate());
        to.setCreatedBy(from.getCreatedBy());
        to.setLastModifiedBy(from.getLastModifiedBy());
        to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static CustUserDTO toDTO(CustUser from){
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
    
    
    public static void mapping(CustUserDTO from, CustUser to){
        if(from ==null || to ==null) return;
        to.setUsrSeq(from.getUsrSeq());
        to.setUsrId(from.getUsrId());
        to.setPassword(from.getPassword());
        to.setName(from.getName());
        to.setTelNo(from.getTelNo());
        to.setEmail(from.getEmail());
        to.setDeptNm(from.getDeptNm());
        to.setGrade(from.getGrade());
        to.setCreateDate(from.getCreateDate());
        to.setCreatedBy(from.getCreatedBy());
        to.setLastModifiedBy(from.getLastModifiedBy());
        to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static CustUser toEntity(CustUserDTO from){
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
    
    
    public static void mapping(CompanyDTO from, Company to){
        if(from ==null || to ==null) return;
        to.setComSeq(from.getComSeq());
        to.setComGrpCode(from.getComGrpCode());
        to.setComName(from.getComName());
        to.setRespName(from.getRespName());
        to.setRespTel(from.getRespTel());
        to.setRespZone(from.getRespZone());
        to.setCreateDate(from.getCreateDate());
        to.setCreatedBy(from.getCreatedBy());
        to.setLastModifiedBy(from.getLastModifiedBy());
        to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static Company toEntity(CompanyDTO from){
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
    
    
    public static Specification<CustUser> toSpec(CustUserSearchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CustUser> toSpec(CustUserSearchDTO searchDTO, Specification<CustUser> sp){
        String name = searchDTO.getName();
        if(name != null){
            sp = sp.and((r,q,c) -> c.like(r.get("name"),"%" +name+ "%"));
        }
        String telNo = searchDTO.getTelNo();
        if(telNo != null){
            sp = sp.and((r,q,c) -> c.like(r.get("telNo"),"%" +telNo+ "%"));
        }
        String email = searchDTO.getEmail();
        if(email != null){
            sp = sp.and((r,q,c) -> c.like(r.get("email"),"%" +email+ "%"));
        }
        String deptNm = searchDTO.getDeptNm();
        if(deptNm != null){
            sp = sp.and((r,q,c) -> c.like(r.get("deptNm"),"%" +deptNm+ "%"));
        }
        String grade = searchDTO.getGrade();
        if(grade != null){
            sp = sp.and((r,q,c) -> c.like(r.get("grade"),"%" +grade+ "%"));
        }
        return sp;
    }
    
    
    public static Specification<Company> toSpec(CompanySearchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Company> toSpec(CompanySearchDTO searchDTO, Specification<Company> sp){
        String comName = searchDTO.getComName();
        if(comName != null){
            sp = sp.and((r,q,c) -> c.equal(r.get("comName"),comName));
        }
        String respName = searchDTO.getRespName();
        if(respName != null){
            sp = sp.and((r,q,c) -> c.like(r.get("respName"),"%" +respName+ "%"));
        }
        String respTel = searchDTO.getRespTel();
        if(respTel != null){
            sp = sp.and((r,q,c) -> c.like(r.get("respTel"),"%" +respTel+ "%"));
        }
        String respZone = searchDTO.getRespZone();
        if(respZone != null){
            sp = sp.and((r,q,c) -> c.like(r.get("respZone"),"%" +respZone));
        }
        return sp;
    }
    

}
