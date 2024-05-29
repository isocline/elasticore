//ecd:493740062H20240529100717V0.7
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

    
    public static void mapping(Company from, CompanyDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        to.setUserList(toCustUserDTOList(from.getUserList()));
        if(!isSkipNull || from.getComSeq()!=null)
            to.setComSeq(from.getComSeq());
        if(!isSkipNull || from.getComGrpCode()!=null)
            to.setComGrpCode(from.getComGrpCode());
        if(!isSkipNull || from.getComName()!=null)
            to.setComName(from.getComName());
        if(!isSkipNull || from.getRespName()!=null)
            to.setRespName(from.getRespName());
        if(!isSkipNull || from.getRespTel()!=null)
            to.setRespTel(from.getRespTel());
        if(!isSkipNull || from.getRespZone()!=null)
            to.setRespZone(from.getRespZone());
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
    
    
    public static void mapping(CustUser from, CustUserDTO to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(from.getCompany()!=null)
            to.setCompanyComSeq(from.getCompany().getComSeq());
        to.setCompany(toDTO(from.getCompany()));
        if(!isSkipNull || from.getUsrSeq()!=null)
            to.setUsrSeq(from.getUsrSeq());
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
    
    
    public static void mapping(CustUserDTO from, CustUser to, boolean isSkipNull){
        if(from ==null || to ==null) return;
        if(!isSkipNull || from.getUsrSeq()!=null)
            to.setUsrSeq(from.getUsrSeq());
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
        to.setCompany(toEntity(from.getCompany()));
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
        if(!isSkipNull || from.getComSeq()!=null)
            to.setComSeq(from.getComSeq());
        if(!isSkipNull || from.getComGrpCode()!=null)
            to.setComGrpCode(from.getComGrpCode());
        if(!isSkipNull || from.getComName()!=null)
            to.setComName(from.getComName());
        if(!isSkipNull || from.getRespName()!=null)
            to.setRespName(from.getRespName());
        if(!isSkipNull || from.getRespTel()!=null)
            to.setRespTel(from.getRespTel());
        if(!isSkipNull || from.getRespZone()!=null)
            to.setRespZone(from.getRespZone());
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
    
    
    public static Specification<CustUser> toSpec(CustUserSearchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CustUser> toSpec(CustUserSearchDTO searchDTO, Specification<CustUser> sp){
        Long comSeq = searchDTO.getComSeq();
        if(comSeq != null){
            sp = sp.and((r,q,c) -> c.equal(r.get("company").get("comSeq"),comSeq));
        }
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
