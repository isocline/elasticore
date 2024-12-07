//ecd:-1757196096H20241207204630_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.dto;

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Join;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import com.xsolcorpkorea.elasticore.test.rollup.entity.*;
import com.xsolcorpkorea.elasticore.test.rollup.dto.*;
import com.xsolcorpkorea.elasticore.test.rollup.enums.*;


import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class SearchResultMapper {

    private static TransformPermissionChecker permissionChecker;

    public static void setTransformPermissionChecker(TransformPermissionChecker checker) {
        permissionChecker = checker;
    }


    protected static void checkPermission(Object from, Object to) {
        if(permissionChecker !=null) {
            if( !permissionChecker.hasPermission(from, to)) {
                throw new PermissionDeniedDataAccessException(from.getClass().getName()+ " access error" ,new RuntimeException());
            }
        }
    }

    
    public static void mapping(Person from, PersonDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getPersonGrp(), to::setPersonGrp, isSkipNull, SearchResultMapper::toDTO);
        if(hasValue(from.getPersonGrp()))
            to.setPersonGrpId(from.getPersonGrp().getId());
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getAge(), to::setAge, isSkipNull);
        setVal(from.getAddr(), to::setAddr, isSkipNull);
        if(isSkip("Person","AbstractEntity")) return;
        setVal(from.getOwnerId(), to::setOwnerId, isSkipNull);
        setVal(from.getTestId(), to::setTestId, isSkipNull);
    }
    
    
    public static void mapping(Person from, PersonDTO to){
        mapping(from,to,false);
    }
    
    
    public static PersonDTO toDTO(Person from){
        if(from==null) return null;
        PersonDTO to = new PersonDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<PersonDTO> toPersonDTOList(List<Person> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<PersonDTO> toPersonDTOList(List<Person> fromList, BiFunction<Person, PersonDTO, PersonDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                PersonDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(PersonGroup from, PersonGroupResultDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getScope1(), to::setScope1, isSkipNull);
        setVal(from.getScope2(), to::setScope2, isSkipNull);
    }
    
    
    public static void mapping(PersonGroup from, PersonGroupResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static PersonGroupResultDTO toPersonGroupResultDTO(PersonGroup from){
        if(from==null) return null;
        PersonGroupResultDTO to = new PersonGroupResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<PersonGroupResultDTO> toPersonGroupResultDTOList(List<PersonGroup> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toPersonGroupResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<PersonGroupResultDTO> toPersonGroupResultDTOList(List<PersonGroup> fromList, BiFunction<PersonGroup, PersonGroupResultDTO, PersonGroupResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                PersonGroupResultDTO to = toPersonGroupResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(PersonGroup from, PersonGroupDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getScope1(), to::setScope1, isSkipNull);
        setVal(from.getScope2(), to::setScope2, isSkipNull);
    }
    
    
    public static void mapping(PersonGroup from, PersonGroupDTO to){
        mapping(from,to,false);
    }
    
    
    public static PersonGroupDTO toDTO(PersonGroup from){
        if(from==null) return null;
        PersonGroupDTO to = new PersonGroupDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<PersonGroupDTO> toPersonGroupDTOList(List<PersonGroup> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<PersonGroupDTO> toPersonGroupDTOList(List<PersonGroup> fromList, BiFunction<PersonGroup, PersonGroupDTO, PersonGroupDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                PersonGroupDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(Organization from, OrganizationDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getOrgCd(), to::setOrgCd, isSkipNull);
        setVal(from.getOrgNm(), to::setOrgNm, isSkipNull);
        setVal(from.getOrgLevelCd(), to::setOrgLevelCd, isSkipNull);
        setVal(from.getUpOrgCd(), to::setUpOrgCd, isSkipNull);
        setVal(from.getOrderNo(), to::setOrderNo, isSkipNull);
        setVal(from.getOrgBossId(), to::setOrgBossId, isSkipNull);
        setVal(from.getDirectPhone1(), to::setDirectPhone1, isSkipNull);
        setVal(from.getDirectPhone2(), to::setDirectPhone2, isSkipNull);
        setVal(from.getDirectPhone3(), to::setDirectPhone3, isSkipNull);
        setVal(from.getFaxPhone1(), to::setFaxPhone1, isSkipNull);
        setVal(from.getFaxPhone2(), to::setFaxPhone2, isSkipNull);
        setVal(from.getFaxPhone3(), to::setFaxPhone3, isSkipNull);
        setVal(from.getPostNo1(), to::setPostNo1, isSkipNull);
        setVal(from.getPostNo2(), to::setPostNo2, isSkipNull);
        setVal(from.getBaseAddr(), to::setBaseAddr, isSkipNull);
        setVal(from.getDetailAddr(), to::setDetailAddr, isSkipNull);
        setVal(from.getOpenYmd(), to::setOpenYmd, isSkipNull);
        setVal(from.getCloseYmd(), to::setCloseYmd, isSkipNull);
        setVal(from.getOrgTypeCd(), to::setOrgTypeCd, isSkipNull);
        setVal(from.getIncreaseOrgCd(), to::setIncreaseOrgCd, isSkipNull);
        setVal(from.getSearchYN(), to::setSearchYN, isSkipNull);
        if(isSkip("Organization","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(Organization from, OrganizationDTO to){
        mapping(from,to,false);
    }
    
    
    public static OrganizationDTO toDTO(Organization from){
        if(from==null) return null;
        OrganizationDTO to = new OrganizationDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<OrganizationDTO> toOrganizationDTOList(List<Organization> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<OrganizationDTO> toOrganizationDTOList(List<Organization> fromList, BiFunction<Organization, OrganizationDTO, OrganizationDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                OrganizationDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(Employee from, EmployeeDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getJuminNo(), to::setJuminNo, isSkipNull);
        setVal(from.getKorNm(), to::setKorNm, isSkipNull);
        setVal(from.getEngNm(), to::setEngNm, isSkipNull);
        setVal(from.getOrgCd(), to::setOrgCd, isSkipNull);
        setVal(from.getJobPositionCd(), to::setJobPositionCd, isSkipNull);
        setVal(from.getJobGradeCd(), to::setJobGradeCd, isSkipNull);
        setVal(from.getJobTitleCd(), to::setJobTitleCd, isSkipNull);
        setVal(from.getCareerCd(), to::setCareerCd, isSkipNull);
        setVal(from.getInVtmentYn(), to::setInVtmentYn, isSkipNull);
        setVal(from.getEnterYmd(), to::setEnterYmd, isSkipNull);
        setVal(from.getRetireYmd(), to::setRetireYmd, isSkipNull);
        setVal(from.getOrderYmd(), to::setOrderYmd, isSkipNull);
        setVal(from.getRetireReason(), to::setRetireReason, isSkipNull);
        setVal(from.getBirthYmd(), to::setBirthYmd, isSkipNull);
        setVal(from.getMobilePhone1(), to::setMobilePhone1, isSkipNull);
        setVal(from.getMobilePhone2(), to::setMobilePhone2, isSkipNull);
        setVal(from.getMobilePhone3(), to::setMobilePhone3, isSkipNull);
        setVal(from.getDirectPhone1(), to::setDirectPhone1, isSkipNull);
        setVal(from.getDirectPhone2(), to::setDirectPhone2, isSkipNull);
        setVal(from.getDirectPhone3(), to::setDirectPhone3, isSkipNull);
        setVal(from.getEmail(), to::setEmail, isSkipNull);
        setVal(from.getPostNo1(), to::setPostNo1, isSkipNull);
        setVal(from.getPostNo2(), to::setPostNo2, isSkipNull);
        setVal(from.getBaseAddr(), to::setBaseAddr, isSkipNull);
        setVal(from.getDetailAddr(), to::setDetailAddr, isSkipNull);
        setVal(from.getMarryYn(), to::setMarryYn, isSkipNull);
        setVal(from.getBloodCd(), to::setBloodCd, isSkipNull);
        setVal(from.getReligionCd(), to::setReligionCd, isSkipNull);
        setVal(from.getHobbyNm(), to::setHobbyNm, isSkipNull);
        setVal(from.getCtbno(), to::setCtbno, isSkipNull);
        setVal(from.getBioTypeCd(), to::setBioTypeCd, isSkipNull);
        setVal(from.getLifeInsuYn(), to::setLifeInsuYn, isSkipNull);
        setVal(from.getPnpnSvn(), to::setPnpnSvn, isSkipNull);
        setVal(from.getBankCd(), to::setBankCd, isSkipNull);
        setVal(from.getAccountNo(), to::setAccountNo, isSkipNull);
        setVal(from.getPhotoFileNm(), to::setPhotoFileNm, isSkipNull);
        setVal(from.getEmailUseYn(), to::setEmailUseYn, isSkipNull);
        setVal(from.getECommP(), to::setECommP, isSkipNull);
        setVal(from.getIncludeNumber(), to::setIncludeNumber, isSkipNull);
        setVal(from.getAdvancedPayment(), to::setAdvancedPayment, isSkipNull);
        setVal(from.getScholarship(), to::setScholarship, isSkipNull);
        setVal(from.getScholarshipStart(), to::setScholarshipStart, isSkipNull);
        setVal(from.getScholarshipEnd(), to::setScholarshipEnd, isSkipNull);
        setVal(from.getChildCare(), to::setChildCare, isSkipNull);
        setVal(from.getAppoint(), to::setAppoint, isSkipNull);
        setVal(from.getAppointMonth(), to::setAppointMonth, isSkipNull);
        setVal(from.getIntroductionPolicy(), to::setIntroductionPolicy, isSkipNull);
        setVal(from.getRevertMonth(), to::setRevertMonth, isSkipNull);
        if(isSkip("Employee","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(Employee from, EmployeeDTO to){
        mapping(from,to,false);
    }
    
    
    public static EmployeeDTO toDTO(Employee from){
        if(from==null) return null;
        EmployeeDTO to = new EmployeeDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<EmployeeDTO> toEmployeeDTOList(List<Employee> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<EmployeeDTO> toEmployeeDTOList(List<Employee> fromList, BiFunction<Employee, EmployeeDTO, EmployeeDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                EmployeeDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(EmpFamily from, EmpFamilyDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getJuminNo(), to::setJuminNo, isSkipNull);
        setVal(from.getFamilyRelNm(), to::setFamilyRelNm, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getEtc(), to::setEtc, isSkipNull);
        setVal(from.getTogetherYn(), to::setTogetherYn, isSkipNull);
        if(isSkip("EmpFamily","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(EmpFamily from, EmpFamilyDTO to){
        mapping(from,to,false);
    }
    
    
    public static EmpFamilyDTO toDTO(EmpFamily from){
        if(from==null) return null;
        EmpFamilyDTO to = new EmpFamilyDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<EmpFamilyDTO> toEmpFamilyDTOList(List<EmpFamily> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<EmpFamilyDTO> toEmpFamilyDTOList(List<EmpFamily> fromList, BiFunction<EmpFamily, EmpFamilyDTO, EmpFamilyDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                EmpFamilyDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(EmpEducation from, EmpEducationDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getEnterYmd(), to::setEnterYmd, isSkipNull);
        setVal(from.getOutYmd(), to::setOutYmd, isSkipNull);
        setVal(from.getSchoolNm(), to::setSchoolNm, isSkipNull);
        setVal(from.getSchoolCareerCd(), to::setSchoolCareerCd, isSkipNull);
        setVal(from.getSubjectNm(), to::setSubjectNm, isSkipNull);
        if(isSkip("EmpEducation","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(EmpEducation from, EmpEducationDTO to){
        mapping(from,to,false);
    }
    
    
    public static EmpEducationDTO toDTO(EmpEducation from){
        if(from==null) return null;
        EmpEducationDTO to = new EmpEducationDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<EmpEducationDTO> toEmpEducationDTOList(List<EmpEducation> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<EmpEducationDTO> toEmpEducationDTOList(List<EmpEducation> fromList, BiFunction<EmpEducation, EmpEducationDTO, EmpEducationDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                EmpEducationDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(Customer from, CustomerDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCustNo(), to::setCustNo, isSkipNull);
        setVal(from.getCustCd(), to::setCustCd, isSkipNull);
        setVal(from.getOrgEmpNo(), to::setOrgEmpNo, isSkipNull);
        setVal(from.getCustNm(), to::setCustNm, isSkipNull);
        setVal(from.getDirectPhone1(), to::setDirectPhone1, isSkipNull);
        setVal(from.getDirectPhone2(), to::setDirectPhone2, isSkipNull);
        setVal(from.getDirectPhone3(), to::setDirectPhone3, isSkipNull);
        setVal(from.getAcademicCd(), to::setAcademicCd, isSkipNull);
        setVal(from.getJobNm(), to::setJobNm, isSkipNull);
        setVal(from.getMobilePhone1(), to::setMobilePhone1, isSkipNull);
        setVal(from.getMobilePhone2(), to::setMobilePhone2, isSkipNull);
        setVal(from.getMobilePhone3(), to::setMobilePhone3, isSkipNull);
        setVal(from.getGender(), to::setGender, isSkipNull);
        setVal(from.getBirthCd(), to::setBirthCd, isSkipNull);
        setVal(from.getBirthYmd(), to::setBirthYmd, isSkipNull);
        setVal(from.getEmail(), to::setEmail, isSkipNull);
        setVal(from.getHomePostNo1(), to::setHomePostNo1, isSkipNull);
        setVal(from.getHomePostNo2(), to::setHomePostNo2, isSkipNull);
        setVal(from.getHomeBaseAddr(), to::setHomeBaseAddr, isSkipNull);
        setVal(from.getHomeDetailAddr(), to::setHomeDetailAddr, isSkipNull);
        setVal(from.getMemorialCd(), to::setMemorialCd, isSkipNull);
        setVal(from.getMemorialYmd(), to::setMemorialYmd, isSkipNull);
        setVal(from.getGroupCd(), to::setGroupCd, isSkipNull);
        setVal(from.getGroupNm(), to::setGroupNm, isSkipNull);
        setVal(from.getRecommNm(), to::setRecommNm, isSkipNull);
        setVal(from.getChannelCd(), to::setChannelCd, isSkipNull);
        setVal(from.getCompanyNm(), to::setCompanyNm, isSkipNull);
        setVal(from.getJobPhone1(), to::setJobPhone1, isSkipNull);
        setVal(from.getJobPhone2(), to::setJobPhone2, isSkipNull);
        setVal(from.getJobPhone3(), to::setJobPhone3, isSkipNull);
        setVal(from.getJobPostNo1(), to::setJobPostNo1, isSkipNull);
        setVal(from.getJobPostNo2(), to::setJobPostNo2, isSkipNull);
        setVal(from.getJobBaseAddr(), to::setJobBaseAddr, isSkipNull);
        setVal(from.getJobDetailAddr(), to::setJobDetailAddr, isSkipNull);
        setVal(from.getInsuComCd(), to::setInsuComCd, isSkipNull);
        setVal(from.getInsuProdNm(), to::setInsuProdNm, isSkipNull);
        setVal(from.getStartYmd(), to::setStartYmd, isSkipNull);
        setVal(from.getEndYmd(), to::setEndYmd, isSkipNull);
        setVal(from.getContYn(), to::setContYn, isSkipNull);
        setVal(from.getPlateNo(), to::setPlateNo, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        if(isSkip("Customer","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(Customer from, CustomerDTO to){
        mapping(from,to,false);
    }
    
    
    public static CustomerDTO toDTO(Customer from){
        if(from==null) return null;
        CustomerDTO to = new CustomerDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CustomerDTO> toCustomerDTOList(List<Customer> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CustomerDTO> toCustomerDTOList(List<Customer> fromList, BiFunction<Customer, CustomerDTO, CustomerDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CustomerDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CustContactHistory from, CustContactHistoryDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getContactSeq(), to::setContactSeq, isSkipNull);
        setVal(from.getContactYmd(), to::setContactYmd, isSkipNull);
        setVal(from.getContactTime(), to::setContactTime, isSkipNull);
        setVal(from.getContactCd(), to::setContactCd, isSkipNull);
        setVal(from.getCustNm(), to::setCustNm, isSkipNull);
        setVal(from.getCustSeq(), to::setCustSeq, isSkipNull);
        setVal(from.getCustNo(), to::setCustNo, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getOpenYN(), to::setOpenYN, isSkipNull);
        setVal(from.getContactTtl(), to::setContactTtl, isSkipNull);
        setVal(from.getContactPlace(), to::setContactPlace, isSkipNull);
        setVal(from.getContactDesc(), to::setContactDesc, isSkipNull);
        if(isSkip("CustContactHistory","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(CustContactHistory from, CustContactHistoryDTO to){
        mapping(from,to,false);
    }
    
    
    public static CustContactHistoryDTO toDTO(CustContactHistory from){
        if(from==null) return null;
        CustContactHistoryDTO to = new CustContactHistoryDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CustContactHistoryDTO> toCustContactHistoryDTOList(List<CustContactHistory> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CustContactHistoryDTO> toCustContactHistoryDTOList(List<CustContactHistory> fromList, BiFunction<CustContactHistory, CustContactHistoryDTO, CustContactHistoryDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CustContactHistoryDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CustInsuranceStatus from, CustInsuranceStatusDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCustSeq(), to::setCustSeq, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getInsuComNm(), to::setInsuComNm, isSkipNull);
        setVal(from.getInsuItemNm(), to::setInsuItemNm, isSkipNull);
        setVal(from.getInsuProdNm(), to::setInsuProdNm, isSkipNull);
        setVal(from.getStartYmd(), to::setStartYmd, isSkipNull);
        setVal(from.getEndYmd(), to::setEndYmd, isSkipNull);
        if(isSkip("CustInsuranceStatus","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(CustInsuranceStatus from, CustInsuranceStatusDTO to){
        mapping(from,to,false);
    }
    
    
    public static CustInsuranceStatusDTO toDTO(CustInsuranceStatus from){
        if(from==null) return null;
        CustInsuranceStatusDTO to = new CustInsuranceStatusDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CustInsuranceStatusDTO> toCustInsuranceStatusDTOList(List<CustInsuranceStatus> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CustInsuranceStatusDTO> toCustInsuranceStatusDTOList(List<CustInsuranceStatus> fromList, BiFunction<CustInsuranceStatus, CustInsuranceStatusDTO, CustInsuranceStatusDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CustInsuranceStatusDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ContractSpecialClause from, ContractSpecialClauseDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getInsuComCd(), to::setInsuComCd, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getSpeNm(), to::setSpeNm, isSkipNull);
        setVal(from.getStartYmd(), to::setStartYmd, isSkipNull);
        setVal(from.getEndYmd(), to::setEndYmd, isSkipNull);
        setVal(from.getBasePrem(), to::setBasePrem, isSkipNull);
        setVal(from.getPayPrem(), to::setPayPrem, isSkipNull);
        setVal(from.getSpeCd(), to::setSpeCd, isSkipNull);
        if(isSkip("ContractSpecialClause","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(ContractSpecialClause from, ContractSpecialClauseDTO to){
        mapping(from,to,false);
    }
    
    
    public static ContractSpecialClauseDTO toDTO(ContractSpecialClause from){
        if(from==null) return null;
        ContractSpecialClauseDTO to = new ContractSpecialClauseDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ContractSpecialClauseDTO> toContractSpecialClauseDTOList(List<ContractSpecialClause> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<ContractSpecialClauseDTO> toContractSpecialClauseDTOList(List<ContractSpecialClause> fromList, BiFunction<ContractSpecialClause, ContractSpecialClauseDTO, ContractSpecialClauseDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ContractSpecialClauseDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ContractRelated from, ContractRelatedDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getInsuComCd(), to::setInsuComCd, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getInsTypeCd(), to::setInsTypeCd, isSkipNull);
        setVal(from.getInsHolderNm(), to::setInsHolderNm, isSkipNull);
        setVal(from.getInsHolderNo(), to::setInsHolderNo, isSkipNull);
        setVal(from.getInsRelCd(), to::setInsRelCd, isSkipNull);
        setVal(from.getInsAge(), to::setInsAge, isSkipNull);
        if(isSkip("ContractRelated","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(ContractRelated from, ContractRelatedDTO to){
        mapping(from,to,false);
    }
    
    
    public static ContractRelatedDTO toDTO(ContractRelated from){
        if(from==null) return null;
        ContractRelatedDTO to = new ContractRelatedDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ContractRelatedDTO> toContractRelatedDTOList(List<ContractRelated> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<ContractRelatedDTO> toContractRelatedDTOList(List<ContractRelated> fromList, BiFunction<ContractRelated, ContractRelatedDTO, ContractRelatedDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ContractRelatedDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ContractPayment from, ContractPaymentDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getInsuComCd(), to::setInsuComCd, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getDistrCnt(), to::setDistrCnt, isSkipNull);
        setVal(from.getPayYMM(), to::setPayYMM, isSkipNull);
        setVal(from.getPayMethodCd(), to::setPayMethodCd, isSkipNull);
        setVal(from.getPayYmd(), to::setPayYmd, isSkipNull);
        setVal(from.getSumPrem(), to::setSumPrem, isSkipNull);
        setVal(from.getModifyPrem(), to::setModifyPrem, isSkipNull);
        setVal(from.getPayStateCd(), to::setPayStateCd, isSkipNull);
        setVal(from.getRectNo(), to::setRectNo, isSkipNull);
        setVal(from.getRateType(), to::setRateType, isSkipNull);
        setVal(from.getPayAmt(), to::setPayAmt, isSkipNull);
        setVal(from.getJobDate(), to::setJobDate, isSkipNull);
        setVal(from.getClosYn(), to::setClosYn, isSkipNull);
        setVal(from.getPolicyNo_BK(), to::setPolicyNo_BK, isSkipNull);
        setVal(from.getPolicyNo_New(), to::setPolicyNo_New, isSkipNull);
        if(isSkip("ContractPayment","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(ContractPayment from, ContractPaymentDTO to){
        mapping(from,to,false);
    }
    
    
    public static ContractPaymentDTO toDTO(ContractPayment from){
        if(from==null) return null;
        ContractPaymentDTO to = new ContractPaymentDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ContractPaymentDTO> toContractPaymentDTOList(List<ContractPayment> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<ContractPaymentDTO> toContractPaymentDTOList(List<ContractPayment> fromList, BiFunction<ContractPayment, ContractPaymentDTO, ContractPaymentDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ContractPaymentDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(InsuranceCompanyCode from, InsuranceCompanyCodeDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getInsCpCode(), to::setInsCpCode, isSkipNull);
        setVal(from.getInsCpTypeCd(), to::setInsCpTypeCd, isSkipNull);
        setVal(from.getInsCpNm(), to::setInsCpNm, isSkipNull);
        setVal(from.getRegDate(), to::setRegDate, isSkipNull);
        setVal(from.getStartDate(), to::setStartDate, isSkipNull);
        setVal(from.getEndDate(), to::setEndDate, isSkipNull);
        setVal(from.getMgrNm(), to::setMgrNm, isSkipNull);
        setVal(from.getContNum(), to::setContNum, isSkipNull);
        setVal(from.getContYN(), to::setContYN, isSkipNull);
        setVal(from.getMgtCnt(), to::setMgtCnt, isSkipNull);
        setVal(from.getFeeCnt(), to::setFeeCnt, isSkipNull);
        setVal(from.getCompanyFaxNo(), to::setCompanyFaxNo, isSkipNull);
        setVal(from.getStartCnt(), to::setStartCnt, isSkipNull);
        setVal(from.getEndCnt(), to::setEndCnt, isSkipNull);
        setVal(from.getPensionAdjustRate(), to::setPensionAdjustRate, isSkipNull);
        setVal(from.getProtectionAdjustRate(), to::setProtectionAdjustRate, isSkipNull);
        setVal(from.getAdjustRate(), to::setAdjustRate, isSkipNull);
        if(isSkip("InsuranceCompanyCode","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(InsuranceCompanyCode from, InsuranceCompanyCodeDTO to){
        mapping(from,to,false);
    }
    
    
    public static InsuranceCompanyCodeDTO toDTO(InsuranceCompanyCode from){
        if(from==null) return null;
        InsuranceCompanyCodeDTO to = new InsuranceCompanyCodeDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<InsuranceCompanyCodeDTO> toInsuranceCompanyCodeDTOList(List<InsuranceCompanyCode> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<InsuranceCompanyCodeDTO> toInsuranceCompanyCodeDTOList(List<InsuranceCompanyCode> fromList, BiFunction<InsuranceCompanyCode, InsuranceCompanyCodeDTO, InsuranceCompanyCodeDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                InsuranceCompanyCodeDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(InsProductCode from, InsProductCodeDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getInsCpCode(), to::setInsCpCode, isSkipNull);
        setVal(from.getProductCode(), to::setProductCode, isSkipNull);
        setVal(from.getProdNm(), to::setProdNm, isSkipNull);
        setVal(from.getProdTypeCd(), to::setProdTypeCd, isSkipNull);
        setVal(from.getProdGrpCd(), to::setProdGrpCd, isSkipNull);
        setVal(from.getInsProdCd(), to::setInsProdCd, isSkipNull);
        setVal(from.getSaleStartDate(), to::setSaleStartDate, isSkipNull);
        setVal(from.getSaleEndDate(), to::setSaleEndDate, isSkipNull);
        setVal(from.getSaleYN(), to::setSaleYN, isSkipNull);
        setVal(from.getMgtCnt(), to::setMgtCnt, isSkipNull);
        setVal(from.getAdjustRate(), to::setAdjustRate, isSkipNull);
        setVal(from.getNewAdjustRate(), to::setNewAdjustRate, isSkipNull);
        if(isSkip("InsProductCode","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(InsProductCode from, InsProductCodeDTO to){
        mapping(from,to,false);
    }
    
    
    public static InsProductCodeDTO toDTO(InsProductCode from){
        if(from==null) return null;
        InsProductCodeDTO to = new InsProductCodeDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<InsProductCodeDTO> toInsProductCodeDTOList(List<InsProductCode> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<InsProductCodeDTO> toInsProductCodeDTOList(List<InsProductCode> fromList, BiFunction<InsProductCode, InsProductCodeDTO, InsProductCodeDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                InsProductCodeDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CommissionMaster from, CommissionMasterDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getClosYm(), to::setClosYm, isSkipNull);
        setVal(from.getInsCpCode(), to::setInsCpCode, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getEndPayCnt(), to::setEndPayCnt, isSkipNull);
        setVal(from.getPolicStateCd(), to::setPolicStateCd, isSkipNull);
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getEmpName(), to::setEmpName, isSkipNull);
        setVal(from.getOrgEmpNo(), to::setOrgEmpNo, isSkipNull);
        setVal(from.getInsProdCd(), to::setInsProdCd, isSkipNull);
        setVal(from.getProductCode(), to::setProductCode, isSkipNull);
        setVal(from.getPolHolderNm(), to::setPolHolderNm, isSkipNull);
        setVal(from.getContYmd(), to::setContYmd, isSkipNull);
        setVal(from.getPayCyclCd(), to::setPayCyclCd, isSkipNull);
        setVal(from.getPmtyCd(), to::setPmtyCd, isSkipNull);
        setVal(from.getPayMethodCd(), to::setPayMethodCd, isSkipNull);
        setVal(from.getPayYmd(), to::setPayYmd, isSkipNull);
        setVal(from.getInPrem(), to::setInPrem, isSkipNull);
        setVal(from.getDistrCnt(), to::setDistrCnt, isSkipNull);
        setVal(from.getCnvnFre(), to::setCnvnFre, isSkipNull);
        setVal(from.getCnvrtCnt(), to::setCnvrtCnt, isSkipNull);
        setVal(from.getEfficAchi(), to::setEfficAchi, isSkipNull);
        setVal(from.getPayYMM(), to::setPayYMM, isSkipNull);
        setVal(from.getCmiFeeAmt(), to::setCmiFeeAmt, isSkipNull);
        setVal(from.getPayBackFeeAmt(), to::setPayBackFeeAmt, isSkipNull);
        setVal(from.getIncentiveFee(), to::setIncentiveFee, isSkipNull);
        if(isSkip("CommissionMaster","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(CommissionMaster from, CommissionMasterDTO to){
        mapping(from,to,false);
    }
    
    
    public static CommissionMasterDTO toDTO(CommissionMaster from){
        if(from==null) return null;
        CommissionMasterDTO to = new CommissionMasterDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CommissionMasterDTO> toCommissionMasterDTOList(List<CommissionMaster> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CommissionMasterDTO> toCommissionMasterDTOList(List<CommissionMaster> fromList, BiFunction<CommissionMaster, CommissionMasterDTO, CommissionMasterDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CommissionMasterDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CommissionContractDetails from, CommissionContractDetailsDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getClosYm(), to::setClosYm, isSkipNull);
        setVal(from.getInsCpCode(), to::setInsCpCode, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getEndPayCnt(), to::setEndPayCnt, isSkipNull);
        setVal(from.getCommiTypeCd(), to::setCommiTypeCd, isSkipNull);
        setVal(from.getCmiFeeCd(), to::setCmiFeeCd, isSkipNull);
        setVal(from.getFeeStateCd(), to::setFeeStateCd, isSkipNull);
        setVal(from.getCnvFre(), to::setCnvFre, isSkipNull);
        setVal(from.getCmiFeeRate(), to::setCmiFeeRate, isSkipNull);
        setVal(from.getPayBackFeeAmt(), to::setPayBackFeeAmt, isSkipNull);
        setVal(from.getCmiFeeAmt(), to::setCmiFeeAmt, isSkipNull);
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getPayPtrnCode(), to::setPayPtrnCode, isSkipNull);
        setVal(from.getProdGrpCd(), to::setProdGrpCd, isSkipNull);
        if(isSkip("CommissionContractDetails","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(CommissionContractDetails from, CommissionContractDetailsDTO to){
        mapping(from,to,false);
    }
    
    
    public static CommissionContractDetailsDTO toDTO(CommissionContractDetails from){
        if(from==null) return null;
        CommissionContractDetailsDTO to = new CommissionContractDetailsDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CommissionContractDetailsDTO> toCommissionContractDetailsDTOList(List<CommissionContractDetails> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CommissionContractDetailsDTO> toCommissionContractDetailsDTOList(List<CommissionContractDetails> fromList, BiFunction<CommissionContractDetails, CommissionContractDetailsDTO, CommissionContractDetailsDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CommissionContractDetailsDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CommissionDetails from, CommissionDetailsDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getClosYm(), to::setClosYm, isSkipNull);
        setVal(from.getInsCpCode(), to::setInsCpCode, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getEndPayCnt(), to::setEndPayCnt, isSkipNull);
        setVal(from.getCommiTypeCd(), to::setCommiTypeCd, isSkipNull);
        setVal(from.getCmiFeeCd(), to::setCmiFeeCd, isSkipNull);
        setVal(from.getFeeStateCd(), to::setFeeStateCd, isSkipNull);
        setVal(from.getCnvrFe(), to::setCnvrFe, isSkipNull);
        setVal(from.getCmiFeeRate(), to::setCmiFeeRate, isSkipNull);
        setVal(from.getPayBackFeeAmt(), to::setPayBackFeeAmt, isSkipNull);
        setVal(from.getCmiFeeAmt(), to::setCmiFeeAmt, isSkipNull);
        setVal(from.getPolicStateCd(), to::setPolicStateCd, isSkipNull);
        setVal(from.getSysInputDate(), to::setSysInputDate, isSkipNull);
        setVal(from.getSysInputUser(), to::setSysInputUser, isSkipNull);
        setVal(from.getSysInputIP(), to::setSysInputIP, isSkipNull);
        setVal(from.getPayPtrnCode(), to::setPayPtrnCode, isSkipNull);
    }
    
    
    public static void mapping(CommissionDetails from, CommissionDetailsDTO to){
        mapping(from,to,false);
    }
    
    
    public static CommissionDetailsDTO toDTO(CommissionDetails from){
        if(from==null) return null;
        CommissionDetailsDTO to = new CommissionDetailsDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CommissionDetailsDTO> toCommissionDetailsDTOList(List<CommissionDetails> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CommissionDetailsDTO> toCommissionDetailsDTOList(List<CommissionDetails> fromList, BiFunction<CommissionDetails, CommissionDetailsDTO, CommissionDetailsDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CommissionDetailsDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(PersonGroupDTO from, PersonGroup to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getScope1(), to::setScope1, isSkipNull);
        setVal(from.getScope2(), to::setScope2, isSkipNull);
    }
    
    
    public static void mapping(PersonGroupDTO from, PersonGroup to){
        mapping(from,to,false);
    }
    
    
    public static PersonGroup toEntity(PersonGroupDTO from){
        if(from==null) return null;
        PersonGroup to = new PersonGroup();
        mapping(from, to);
        return to;
    }
    
    
    public static List<PersonGroup> toPersonGroupList(List<PersonGroupDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<PersonGroup> toPersonGroupList(List<PersonGroupDTO> fromList, BiFunction<PersonGroupDTO, PersonGroup, PersonGroup> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                PersonGroup to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(PersonDTO from, Person to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getAge(), to::setAge, isSkipNull);
        setVal(from.getAddr(), to::setAddr, isSkipNull);
        setVal(from.getOwnerId(), to::setOwnerId, isSkipNull);
        setVal(from.getTestId(), to::setTestId, isSkipNull);
        
        
        if(hasValue(from.getPersonGrpId())){
            PersonGroup t = new PersonGroup();
            t.setId(from.getPersonGrpId());
            to.setPersonGrp(t);
        }
    }
    
    
    public static void mapping(PersonDTO from, Person to){
        mapping(from,to,false);
    }
    
    
    public static Person toEntity(PersonDTO from){
        if(from==null) return null;
        Person to = new Person();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Person> toPersonList(List<PersonDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Person> toPersonList(List<PersonDTO> fromList, BiFunction<PersonDTO, Person, Person> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Person to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(OrganizationDTO from, Organization to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getOrgCd(), to::setOrgCd, isSkipNull);
        setVal(from.getOrgNm(), to::setOrgNm, isSkipNull);
        setVal(from.getOrgLevelCd(), to::setOrgLevelCd, isSkipNull);
        setVal(from.getUpOrgCd(), to::setUpOrgCd, isSkipNull);
        setVal(from.getOrderNo(), to::setOrderNo, isSkipNull);
        setVal(from.getOrgBossId(), to::setOrgBossId, isSkipNull);
        setVal(from.getDirectPhone1(), to::setDirectPhone1, isSkipNull);
        setVal(from.getDirectPhone2(), to::setDirectPhone2, isSkipNull);
        setVal(from.getDirectPhone3(), to::setDirectPhone3, isSkipNull);
        setVal(from.getFaxPhone1(), to::setFaxPhone1, isSkipNull);
        setVal(from.getFaxPhone2(), to::setFaxPhone2, isSkipNull);
        setVal(from.getFaxPhone3(), to::setFaxPhone3, isSkipNull);
        setVal(from.getPostNo1(), to::setPostNo1, isSkipNull);
        setVal(from.getPostNo2(), to::setPostNo2, isSkipNull);
        setVal(from.getBaseAddr(), to::setBaseAddr, isSkipNull);
        setVal(from.getDetailAddr(), to::setDetailAddr, isSkipNull);
        setVal(from.getOpenYmd(), to::setOpenYmd, isSkipNull);
        setVal(from.getCloseYmd(), to::setCloseYmd, isSkipNull);
        setVal(from.getOrgTypeCd(), to::setOrgTypeCd, isSkipNull);
        setVal(from.getIncreaseOrgCd(), to::setIncreaseOrgCd, isSkipNull);
        setVal(from.getSearchYN(), to::setSearchYN, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(OrganizationDTO from, Organization to){
        mapping(from,to,false);
    }
    
    
    public static Organization toEntity(OrganizationDTO from){
        if(from==null) return null;
        Organization to = new Organization();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Organization> toOrganizationList(List<OrganizationDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Organization> toOrganizationList(List<OrganizationDTO> fromList, BiFunction<OrganizationDTO, Organization, Organization> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Organization to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(EmployeeDTO from, Employee to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getJuminNo(), to::setJuminNo, isSkipNull);
        setVal(from.getKorNm(), to::setKorNm, isSkipNull);
        setVal(from.getEngNm(), to::setEngNm, isSkipNull);
        setVal(from.getOrgCd(), to::setOrgCd, isSkipNull);
        setVal(from.getJobPositionCd(), to::setJobPositionCd, isSkipNull);
        setVal(from.getJobGradeCd(), to::setJobGradeCd, isSkipNull);
        setVal(from.getJobTitleCd(), to::setJobTitleCd, isSkipNull);
        setVal(from.getCareerCd(), to::setCareerCd, isSkipNull);
        setVal(from.getInVtmentYn(), to::setInVtmentYn, isSkipNull);
        setVal(from.getEnterYmd(), to::setEnterYmd, isSkipNull);
        setVal(from.getRetireYmd(), to::setRetireYmd, isSkipNull);
        setVal(from.getOrderYmd(), to::setOrderYmd, isSkipNull);
        setVal(from.getRetireReason(), to::setRetireReason, isSkipNull);
        setVal(from.getBirthYmd(), to::setBirthYmd, isSkipNull);
        setVal(from.getMobilePhone1(), to::setMobilePhone1, isSkipNull);
        setVal(from.getMobilePhone2(), to::setMobilePhone2, isSkipNull);
        setVal(from.getMobilePhone3(), to::setMobilePhone3, isSkipNull);
        setVal(from.getDirectPhone1(), to::setDirectPhone1, isSkipNull);
        setVal(from.getDirectPhone2(), to::setDirectPhone2, isSkipNull);
        setVal(from.getDirectPhone3(), to::setDirectPhone3, isSkipNull);
        setVal(from.getEmail(), to::setEmail, isSkipNull);
        setVal(from.getPostNo1(), to::setPostNo1, isSkipNull);
        setVal(from.getPostNo2(), to::setPostNo2, isSkipNull);
        setVal(from.getBaseAddr(), to::setBaseAddr, isSkipNull);
        setVal(from.getDetailAddr(), to::setDetailAddr, isSkipNull);
        setVal(from.getMarryYn(), to::setMarryYn, isSkipNull);
        setVal(from.getBloodCd(), to::setBloodCd, isSkipNull);
        setVal(from.getReligionCd(), to::setReligionCd, isSkipNull);
        setVal(from.getHobbyNm(), to::setHobbyNm, isSkipNull);
        setVal(from.getCtbno(), to::setCtbno, isSkipNull);
        setVal(from.getBioTypeCd(), to::setBioTypeCd, isSkipNull);
        setVal(from.getLifeInsuYn(), to::setLifeInsuYn, isSkipNull);
        setVal(from.getPnpnSvn(), to::setPnpnSvn, isSkipNull);
        setVal(from.getBankCd(), to::setBankCd, isSkipNull);
        setVal(from.getAccountNo(), to::setAccountNo, isSkipNull);
        setVal(from.getPhotoFileNm(), to::setPhotoFileNm, isSkipNull);
        setVal(from.getEmailUseYn(), to::setEmailUseYn, isSkipNull);
        setVal(from.getECommP(), to::setECommP, isSkipNull);
        setVal(from.getIncludeNumber(), to::setIncludeNumber, isSkipNull);
        setVal(from.getAdvancedPayment(), to::setAdvancedPayment, isSkipNull);
        setVal(from.getScholarship(), to::setScholarship, isSkipNull);
        setVal(from.getScholarshipStart(), to::setScholarshipStart, isSkipNull);
        setVal(from.getScholarshipEnd(), to::setScholarshipEnd, isSkipNull);
        setVal(from.getChildCare(), to::setChildCare, isSkipNull);
        setVal(from.getAppoint(), to::setAppoint, isSkipNull);
        setVal(from.getAppointMonth(), to::setAppointMonth, isSkipNull);
        setVal(from.getIntroductionPolicy(), to::setIntroductionPolicy, isSkipNull);
        setVal(from.getRevertMonth(), to::setRevertMonth, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(EmployeeDTO from, Employee to){
        mapping(from,to,false);
    }
    
    
    public static Employee toEntity(EmployeeDTO from){
        if(from==null) return null;
        Employee to = new Employee();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Employee> toEmployeeList(List<EmployeeDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Employee> toEmployeeList(List<EmployeeDTO> fromList, BiFunction<EmployeeDTO, Employee, Employee> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Employee to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(EmpFamilyDTO from, EmpFamily to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getJuminNo(), to::setJuminNo, isSkipNull);
        setVal(from.getFamilyRelNm(), to::setFamilyRelNm, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getEtc(), to::setEtc, isSkipNull);
        setVal(from.getTogetherYn(), to::setTogetherYn, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(EmpFamilyDTO from, EmpFamily to){
        mapping(from,to,false);
    }
    
    
    public static EmpFamily toEntity(EmpFamilyDTO from){
        if(from==null) return null;
        EmpFamily to = new EmpFamily();
        mapping(from, to);
        return to;
    }
    
    
    public static List<EmpFamily> toEmpFamilyList(List<EmpFamilyDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<EmpFamily> toEmpFamilyList(List<EmpFamilyDTO> fromList, BiFunction<EmpFamilyDTO, EmpFamily, EmpFamily> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                EmpFamily to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(EmpEducationDTO from, EmpEducation to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getEnterYmd(), to::setEnterYmd, isSkipNull);
        setVal(from.getOutYmd(), to::setOutYmd, isSkipNull);
        setVal(from.getSchoolNm(), to::setSchoolNm, isSkipNull);
        setVal(from.getSchoolCareerCd(), to::setSchoolCareerCd, isSkipNull);
        setVal(from.getSubjectNm(), to::setSubjectNm, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(EmpEducationDTO from, EmpEducation to){
        mapping(from,to,false);
    }
    
    
    public static EmpEducation toEntity(EmpEducationDTO from){
        if(from==null) return null;
        EmpEducation to = new EmpEducation();
        mapping(from, to);
        return to;
    }
    
    
    public static List<EmpEducation> toEmpEducationList(List<EmpEducationDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<EmpEducation> toEmpEducationList(List<EmpEducationDTO> fromList, BiFunction<EmpEducationDTO, EmpEducation, EmpEducation> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                EmpEducation to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CustomerDTO from, Customer to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCustNo(), to::setCustNo, isSkipNull);
        setVal(from.getCustCd(), to::setCustCd, isSkipNull);
        setVal(from.getOrgEmpNo(), to::setOrgEmpNo, isSkipNull);
        setVal(from.getCustNm(), to::setCustNm, isSkipNull);
        setVal(from.getDirectPhone1(), to::setDirectPhone1, isSkipNull);
        setVal(from.getDirectPhone2(), to::setDirectPhone2, isSkipNull);
        setVal(from.getDirectPhone3(), to::setDirectPhone3, isSkipNull);
        setVal(from.getAcademicCd(), to::setAcademicCd, isSkipNull);
        setVal(from.getJobNm(), to::setJobNm, isSkipNull);
        setVal(from.getMobilePhone1(), to::setMobilePhone1, isSkipNull);
        setVal(from.getMobilePhone2(), to::setMobilePhone2, isSkipNull);
        setVal(from.getMobilePhone3(), to::setMobilePhone3, isSkipNull);
        setVal(from.getGender(), to::setGender, isSkipNull);
        setVal(from.getBirthCd(), to::setBirthCd, isSkipNull);
        setVal(from.getBirthYmd(), to::setBirthYmd, isSkipNull);
        setVal(from.getEmail(), to::setEmail, isSkipNull);
        setVal(from.getHomePostNo1(), to::setHomePostNo1, isSkipNull);
        setVal(from.getHomePostNo2(), to::setHomePostNo2, isSkipNull);
        setVal(from.getHomeBaseAddr(), to::setHomeBaseAddr, isSkipNull);
        setVal(from.getHomeDetailAddr(), to::setHomeDetailAddr, isSkipNull);
        setVal(from.getMemorialCd(), to::setMemorialCd, isSkipNull);
        setVal(from.getMemorialYmd(), to::setMemorialYmd, isSkipNull);
        setVal(from.getGroupCd(), to::setGroupCd, isSkipNull);
        setVal(from.getGroupNm(), to::setGroupNm, isSkipNull);
        setVal(from.getRecommNm(), to::setRecommNm, isSkipNull);
        setVal(from.getChannelCd(), to::setChannelCd, isSkipNull);
        setVal(from.getCompanyNm(), to::setCompanyNm, isSkipNull);
        setVal(from.getJobPhone1(), to::setJobPhone1, isSkipNull);
        setVal(from.getJobPhone2(), to::setJobPhone2, isSkipNull);
        setVal(from.getJobPhone3(), to::setJobPhone3, isSkipNull);
        setVal(from.getJobPostNo1(), to::setJobPostNo1, isSkipNull);
        setVal(from.getJobPostNo2(), to::setJobPostNo2, isSkipNull);
        setVal(from.getJobBaseAddr(), to::setJobBaseAddr, isSkipNull);
        setVal(from.getJobDetailAddr(), to::setJobDetailAddr, isSkipNull);
        setVal(from.getInsuComCd(), to::setInsuComCd, isSkipNull);
        setVal(from.getInsuProdNm(), to::setInsuProdNm, isSkipNull);
        setVal(from.getStartYmd(), to::setStartYmd, isSkipNull);
        setVal(from.getEndYmd(), to::setEndYmd, isSkipNull);
        setVal(from.getContYn(), to::setContYn, isSkipNull);
        setVal(from.getPlateNo(), to::setPlateNo, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(CustomerDTO from, Customer to){
        mapping(from,to,false);
    }
    
    
    public static Customer toEntity(CustomerDTO from){
        if(from==null) return null;
        Customer to = new Customer();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Customer> toCustomerList(List<CustomerDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Customer> toCustomerList(List<CustomerDTO> fromList, BiFunction<CustomerDTO, Customer, Customer> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Customer to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CustContactHistoryDTO from, CustContactHistory to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getContactSeq(), to::setContactSeq, isSkipNull);
        setVal(from.getContactYmd(), to::setContactYmd, isSkipNull);
        setVal(from.getContactTime(), to::setContactTime, isSkipNull);
        setVal(from.getContactCd(), to::setContactCd, isSkipNull);
        setVal(from.getCustNm(), to::setCustNm, isSkipNull);
        setVal(from.getCustSeq(), to::setCustSeq, isSkipNull);
        setVal(from.getCustNo(), to::setCustNo, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getOpenYN(), to::setOpenYN, isSkipNull);
        setVal(from.getContactTtl(), to::setContactTtl, isSkipNull);
        setVal(from.getContactPlace(), to::setContactPlace, isSkipNull);
        setVal(from.getContactDesc(), to::setContactDesc, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(CustContactHistoryDTO from, CustContactHistory to){
        mapping(from,to,false);
    }
    
    
    public static CustContactHistory toEntity(CustContactHistoryDTO from){
        if(from==null) return null;
        CustContactHistory to = new CustContactHistory();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CustContactHistory> toCustContactHistoryList(List<CustContactHistoryDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<CustContactHistory> toCustContactHistoryList(List<CustContactHistoryDTO> fromList, BiFunction<CustContactHistoryDTO, CustContactHistory, CustContactHistory> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CustContactHistory to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CustInsuranceStatusDTO from, CustInsuranceStatus to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCustSeq(), to::setCustSeq, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getInsuComNm(), to::setInsuComNm, isSkipNull);
        setVal(from.getInsuItemNm(), to::setInsuItemNm, isSkipNull);
        setVal(from.getInsuProdNm(), to::setInsuProdNm, isSkipNull);
        setVal(from.getStartYmd(), to::setStartYmd, isSkipNull);
        setVal(from.getEndYmd(), to::setEndYmd, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(CustInsuranceStatusDTO from, CustInsuranceStatus to){
        mapping(from,to,false);
    }
    
    
    public static CustInsuranceStatus toEntity(CustInsuranceStatusDTO from){
        if(from==null) return null;
        CustInsuranceStatus to = new CustInsuranceStatus();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CustInsuranceStatus> toCustInsuranceStatusList(List<CustInsuranceStatusDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<CustInsuranceStatus> toCustInsuranceStatusList(List<CustInsuranceStatusDTO> fromList, BiFunction<CustInsuranceStatusDTO, CustInsuranceStatus, CustInsuranceStatus> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CustInsuranceStatus to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ContractSpecialClauseDTO from, ContractSpecialClause to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getInsuComCd(), to::setInsuComCd, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getSpeNm(), to::setSpeNm, isSkipNull);
        setVal(from.getStartYmd(), to::setStartYmd, isSkipNull);
        setVal(from.getEndYmd(), to::setEndYmd, isSkipNull);
        setVal(from.getBasePrem(), to::setBasePrem, isSkipNull);
        setVal(from.getPayPrem(), to::setPayPrem, isSkipNull);
        setVal(from.getSpeCd(), to::setSpeCd, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(ContractSpecialClauseDTO from, ContractSpecialClause to){
        mapping(from,to,false);
    }
    
    
    public static ContractSpecialClause toEntity(ContractSpecialClauseDTO from){
        if(from==null) return null;
        ContractSpecialClause to = new ContractSpecialClause();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ContractSpecialClause> toContractSpecialClauseList(List<ContractSpecialClauseDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<ContractSpecialClause> toContractSpecialClauseList(List<ContractSpecialClauseDTO> fromList, BiFunction<ContractSpecialClauseDTO, ContractSpecialClause, ContractSpecialClause> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ContractSpecialClause to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ContractRelatedDTO from, ContractRelated to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getInsuComCd(), to::setInsuComCd, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getInsTypeCd(), to::setInsTypeCd, isSkipNull);
        setVal(from.getInsHolderNm(), to::setInsHolderNm, isSkipNull);
        setVal(from.getInsHolderNo(), to::setInsHolderNo, isSkipNull);
        setVal(from.getInsRelCd(), to::setInsRelCd, isSkipNull);
        setVal(from.getInsAge(), to::setInsAge, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(ContractRelatedDTO from, ContractRelated to){
        mapping(from,to,false);
    }
    
    
    public static ContractRelated toEntity(ContractRelatedDTO from){
        if(from==null) return null;
        ContractRelated to = new ContractRelated();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ContractRelated> toContractRelatedList(List<ContractRelatedDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<ContractRelated> toContractRelatedList(List<ContractRelatedDTO> fromList, BiFunction<ContractRelatedDTO, ContractRelated, ContractRelated> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ContractRelated to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ContractPaymentDTO from, ContractPayment to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getInsuComCd(), to::setInsuComCd, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getDistrCnt(), to::setDistrCnt, isSkipNull);
        setVal(from.getPayYMM(), to::setPayYMM, isSkipNull);
        setVal(from.getPayMethodCd(), to::setPayMethodCd, isSkipNull);
        setVal(from.getPayYmd(), to::setPayYmd, isSkipNull);
        setVal(from.getSumPrem(), to::setSumPrem, isSkipNull);
        setVal(from.getModifyPrem(), to::setModifyPrem, isSkipNull);
        setVal(from.getPayStateCd(), to::setPayStateCd, isSkipNull);
        setVal(from.getRectNo(), to::setRectNo, isSkipNull);
        setVal(from.getRateType(), to::setRateType, isSkipNull);
        setVal(from.getPayAmt(), to::setPayAmt, isSkipNull);
        setVal(from.getJobDate(), to::setJobDate, isSkipNull);
        setVal(from.getClosYn(), to::setClosYn, isSkipNull);
        setVal(from.getPolicyNo_BK(), to::setPolicyNo_BK, isSkipNull);
        setVal(from.getPolicyNo_New(), to::setPolicyNo_New, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(ContractPaymentDTO from, ContractPayment to){
        mapping(from,to,false);
    }
    
    
    public static ContractPayment toEntity(ContractPaymentDTO from){
        if(from==null) return null;
        ContractPayment to = new ContractPayment();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ContractPayment> toContractPaymentList(List<ContractPaymentDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<ContractPayment> toContractPaymentList(List<ContractPaymentDTO> fromList, BiFunction<ContractPaymentDTO, ContractPayment, ContractPayment> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ContractPayment to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(InsuranceCompanyCodeDTO from, InsuranceCompanyCode to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getInsCpCode(), to::setInsCpCode, isSkipNull);
        setVal(from.getInsCpTypeCd(), to::setInsCpTypeCd, isSkipNull);
        setVal(from.getInsCpNm(), to::setInsCpNm, isSkipNull);
        setVal(from.getRegDate(), to::setRegDate, isSkipNull);
        setVal(from.getStartDate(), to::setStartDate, isSkipNull);
        setVal(from.getEndDate(), to::setEndDate, isSkipNull);
        setVal(from.getMgrNm(), to::setMgrNm, isSkipNull);
        setVal(from.getContNum(), to::setContNum, isSkipNull);
        setVal(from.getContYN(), to::setContYN, isSkipNull);
        setVal(from.getMgtCnt(), to::setMgtCnt, isSkipNull);
        setVal(from.getFeeCnt(), to::setFeeCnt, isSkipNull);
        setVal(from.getCompanyFaxNo(), to::setCompanyFaxNo, isSkipNull);
        setVal(from.getStartCnt(), to::setStartCnt, isSkipNull);
        setVal(from.getEndCnt(), to::setEndCnt, isSkipNull);
        setVal(from.getPensionAdjustRate(), to::setPensionAdjustRate, isSkipNull);
        setVal(from.getProtectionAdjustRate(), to::setProtectionAdjustRate, isSkipNull);
        setVal(from.getAdjustRate(), to::setAdjustRate, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(InsuranceCompanyCodeDTO from, InsuranceCompanyCode to){
        mapping(from,to,false);
    }
    
    
    public static InsuranceCompanyCode toEntity(InsuranceCompanyCodeDTO from){
        if(from==null) return null;
        InsuranceCompanyCode to = new InsuranceCompanyCode();
        mapping(from, to);
        return to;
    }
    
    
    public static List<InsuranceCompanyCode> toInsuranceCompanyCodeList(List<InsuranceCompanyCodeDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<InsuranceCompanyCode> toInsuranceCompanyCodeList(List<InsuranceCompanyCodeDTO> fromList, BiFunction<InsuranceCompanyCodeDTO, InsuranceCompanyCode, InsuranceCompanyCode> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                InsuranceCompanyCode to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(InsProductCodeDTO from, InsProductCode to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getInsCpCode(), to::setInsCpCode, isSkipNull);
        setVal(from.getProductCode(), to::setProductCode, isSkipNull);
        setVal(from.getProdNm(), to::setProdNm, isSkipNull);
        setVal(from.getProdTypeCd(), to::setProdTypeCd, isSkipNull);
        setVal(from.getProdGrpCd(), to::setProdGrpCd, isSkipNull);
        setVal(from.getInsProdCd(), to::setInsProdCd, isSkipNull);
        setVal(from.getSaleStartDate(), to::setSaleStartDate, isSkipNull);
        setVal(from.getSaleEndDate(), to::setSaleEndDate, isSkipNull);
        setVal(from.getSaleYN(), to::setSaleYN, isSkipNull);
        setVal(from.getMgtCnt(), to::setMgtCnt, isSkipNull);
        setVal(from.getAdjustRate(), to::setAdjustRate, isSkipNull);
        setVal(from.getNewAdjustRate(), to::setNewAdjustRate, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(InsProductCodeDTO from, InsProductCode to){
        mapping(from,to,false);
    }
    
    
    public static InsProductCode toEntity(InsProductCodeDTO from){
        if(from==null) return null;
        InsProductCode to = new InsProductCode();
        mapping(from, to);
        return to;
    }
    
    
    public static List<InsProductCode> toInsProductCodeList(List<InsProductCodeDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<InsProductCode> toInsProductCodeList(List<InsProductCodeDTO> fromList, BiFunction<InsProductCodeDTO, InsProductCode, InsProductCode> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                InsProductCode to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CommissionMasterDTO from, CommissionMaster to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getClosYm(), to::setClosYm, isSkipNull);
        setVal(from.getInsCpCode(), to::setInsCpCode, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getEndPayCnt(), to::setEndPayCnt, isSkipNull);
        setVal(from.getPolicStateCd(), to::setPolicStateCd, isSkipNull);
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getEmpName(), to::setEmpName, isSkipNull);
        setVal(from.getOrgEmpNo(), to::setOrgEmpNo, isSkipNull);
        setVal(from.getInsProdCd(), to::setInsProdCd, isSkipNull);
        setVal(from.getProductCode(), to::setProductCode, isSkipNull);
        setVal(from.getPolHolderNm(), to::setPolHolderNm, isSkipNull);
        setVal(from.getContYmd(), to::setContYmd, isSkipNull);
        setVal(from.getPayCyclCd(), to::setPayCyclCd, isSkipNull);
        setVal(from.getPmtyCd(), to::setPmtyCd, isSkipNull);
        setVal(from.getPayMethodCd(), to::setPayMethodCd, isSkipNull);
        setVal(from.getPayYmd(), to::setPayYmd, isSkipNull);
        setVal(from.getInPrem(), to::setInPrem, isSkipNull);
        setVal(from.getDistrCnt(), to::setDistrCnt, isSkipNull);
        setVal(from.getCnvnFre(), to::setCnvnFre, isSkipNull);
        setVal(from.getCnvrtCnt(), to::setCnvrtCnt, isSkipNull);
        setVal(from.getEfficAchi(), to::setEfficAchi, isSkipNull);
        setVal(from.getPayYMM(), to::setPayYMM, isSkipNull);
        setVal(from.getCmiFeeAmt(), to::setCmiFeeAmt, isSkipNull);
        setVal(from.getPayBackFeeAmt(), to::setPayBackFeeAmt, isSkipNull);
        setVal(from.getIncentiveFee(), to::setIncentiveFee, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(CommissionMasterDTO from, CommissionMaster to){
        mapping(from,to,false);
    }
    
    
    public static CommissionMaster toEntity(CommissionMasterDTO from){
        if(from==null) return null;
        CommissionMaster to = new CommissionMaster();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CommissionMaster> toCommissionMasterList(List<CommissionMasterDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<CommissionMaster> toCommissionMasterList(List<CommissionMasterDTO> fromList, BiFunction<CommissionMasterDTO, CommissionMaster, CommissionMaster> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CommissionMaster to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CommissionContractDetailsDTO from, CommissionContractDetails to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getClosYm(), to::setClosYm, isSkipNull);
        setVal(from.getInsCpCode(), to::setInsCpCode, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getEndPayCnt(), to::setEndPayCnt, isSkipNull);
        setVal(from.getCommiTypeCd(), to::setCommiTypeCd, isSkipNull);
        setVal(from.getCmiFeeCd(), to::setCmiFeeCd, isSkipNull);
        setVal(from.getFeeStateCd(), to::setFeeStateCd, isSkipNull);
        setVal(from.getCnvFre(), to::setCnvFre, isSkipNull);
        setVal(from.getCmiFeeRate(), to::setCmiFeeRate, isSkipNull);
        setVal(from.getPayBackFeeAmt(), to::setPayBackFeeAmt, isSkipNull);
        setVal(from.getCmiFeeAmt(), to::setCmiFeeAmt, isSkipNull);
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
        setVal(from.getPayPtrnCode(), to::setPayPtrnCode, isSkipNull);
        setVal(from.getProdGrpCd(), to::setProdGrpCd, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
    }
    
    
    public static void mapping(CommissionContractDetailsDTO from, CommissionContractDetails to){
        mapping(from,to,false);
    }
    
    
    public static CommissionContractDetails toEntity(CommissionContractDetailsDTO from){
        if(from==null) return null;
        CommissionContractDetails to = new CommissionContractDetails();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CommissionContractDetails> toCommissionContractDetailsList(List<CommissionContractDetailsDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<CommissionContractDetails> toCommissionContractDetailsList(List<CommissionContractDetailsDTO> fromList, BiFunction<CommissionContractDetailsDTO, CommissionContractDetails, CommissionContractDetails> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CommissionContractDetails to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CommissionDetailsDTO from, CommissionDetails to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getClosYm(), to::setClosYm, isSkipNull);
        setVal(from.getInsCpCode(), to::setInsCpCode, isSkipNull);
        setVal(from.getPolicyNo(), to::setPolicyNo, isSkipNull);
        setVal(from.getEndPayCnt(), to::setEndPayCnt, isSkipNull);
        setVal(from.getCommiTypeCd(), to::setCommiTypeCd, isSkipNull);
        setVal(from.getCmiFeeCd(), to::setCmiFeeCd, isSkipNull);
        setVal(from.getFeeStateCd(), to::setFeeStateCd, isSkipNull);
        setVal(from.getCnvrFe(), to::setCnvrFe, isSkipNull);
        setVal(from.getCmiFeeRate(), to::setCmiFeeRate, isSkipNull);
        setVal(from.getPayBackFeeAmt(), to::setPayBackFeeAmt, isSkipNull);
        setVal(from.getCmiFeeAmt(), to::setCmiFeeAmt, isSkipNull);
        setVal(from.getPolicStateCd(), to::setPolicStateCd, isSkipNull);
        setVal(from.getSysInputDate(), to::setSysInputDate, isSkipNull);
        setVal(from.getSysInputUser(), to::setSysInputUser, isSkipNull);
        setVal(from.getSysInputIP(), to::setSysInputIP, isSkipNull);
        setVal(from.getPayPtrnCode(), to::setPayPtrnCode, isSkipNull);
    }
    
    
    public static void mapping(CommissionDetailsDTO from, CommissionDetails to){
        mapping(from,to,false);
    }
    
    
    public static CommissionDetails toEntity(CommissionDetailsDTO from){
        if(from==null) return null;
        CommissionDetails to = new CommissionDetails();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CommissionDetails> toCommissionDetailsList(List<CommissionDetailsDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<CommissionDetails> toCommissionDetailsList(List<CommissionDetailsDTO> fromList, BiFunction<CommissionDetailsDTO, CommissionDetails, CommissionDetails> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CommissionDetails to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<Person> toSpec(PersonSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Person> toSpec(PersonSrchDTO searchDTO, Specification<Person> sp){
        String personGrpId = searchDTO.getPersonGrpId();
        if(hasValue(personGrpId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("personGrp").get("id"),personGrpId));
        }
        String addr = searchDTO.getAddr();
        if(hasValue(addr)){
            sp = sp.and((r,q,c) -> c.equal(r.get("addr"),addr));
        }
        String testId = searchDTO.getTestId();
        if(hasValue(testId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("testId"),testId));
        }
        return sp;
    }
    
    
    public static Specification<PersonGroup> toSpec(PersonGroupSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<PersonGroup> toSpec(PersonGroupSrchDTO searchDTO, Specification<PersonGroup> sp){
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "name", searchDTO.getName());
        Integer scopeVal = searchDTO.getScopeVal();
        if(hasValue(scopeVal)){
            sp = sp.and((r,q,c) -> c.lessThan(r.get("scopeVal"),scopeVal));
        }
        Integer scopeVal = searchDTO.getScopeVal();
        if(hasValue(scopeVal)){
            sp = sp.and((r,q,c) -> c.greaterThan(r.get("scopeVal"),scopeVal));
        }
        return sp;
    }
    
    
    public static Specification<Organization> toSpec(OrganizationSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Organization> toSpec(OrganizationSrchDTO searchDTO, Specification<Organization> sp){
        sp=setSpec(sp, "orgCd", searchDTO.getOrgCd());
        sp=setSpec(sp, "orgNm", searchDTO.getOrgNm());
        sp=setSpec(sp, "orgLevelCd", searchDTO.getOrgLevelCd());
        sp=setSpec(sp, "upOrgCd", searchDTO.getUpOrgCd());
        Long orderNo = searchDTO.getOrderNo();
        if(hasValue(orderNo)){
            sp = sp.and((r,q,c) -> c.equal(r.get("orderNo"),orderNo));
        }
        sp=setSpec(sp, "orgBossId", searchDTO.getOrgBossId());
        sp=setSpec(sp, "directPhone1", searchDTO.getDirectPhone1());
        sp=setSpec(sp, "directPhone2", searchDTO.getDirectPhone2());
        sp=setSpec(sp, "directPhone3", searchDTO.getDirectPhone3());
        sp=setSpec(sp, "faxPhone1", searchDTO.getFaxPhone1());
        sp=setSpec(sp, "faxPhone2", searchDTO.getFaxPhone2());
        sp=setSpec(sp, "faxPhone3", searchDTO.getFaxPhone3());
        sp=setSpec(sp, "postNo1", searchDTO.getPostNo1());
        sp=setSpec(sp, "postNo2", searchDTO.getPostNo2());
        sp=setSpec(sp, "baseAddr", searchDTO.getBaseAddr());
        sp=setSpec(sp, "detailAddr", searchDTO.getDetailAddr());
        sp=setSpec(sp, "openYmd", searchDTO.getOpenYmd());
        sp=setSpec(sp, "closeYmd", searchDTO.getCloseYmd());
        sp=setSpec(sp, "orgTypeCd", searchDTO.getOrgTypeCd());
        sp=setSpec(sp, "increaseOrgCd", searchDTO.getIncreaseOrgCd());
        Indicator searchYN = searchDTO.getSearchYN();
        if(hasValue(searchYN)){
            sp = sp.and((r,q,c) -> c.equal(r.get("searchYN"),searchYN));
        }
        return sp;
    }
    
    
    public static Specification<Employee> toSpec(EmployeeSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Employee> toSpec(EmployeeSrchDTO searchDTO, Specification<Employee> sp){
        sp=setSpec(sp, "empNo", searchDTO.getEmpNo());
        sp=setSpec(sp, "juminNo", searchDTO.getJuminNo());
        sp=setSpec(sp, "korNm", searchDTO.getKorNm());
        sp=setSpec(sp, "engNm", searchDTO.getEngNm());
        sp=setSpec(sp, "orgCd", searchDTO.getOrgCd());
        sp=setSpec(sp, "jobPositionCd", searchDTO.getJobPositionCd());
        sp=setSpec(sp, "jobGradeCd", searchDTO.getJobGradeCd());
        sp=setSpec(sp, "jobTitleCd", searchDTO.getJobTitleCd());
        sp=setSpec(sp, "careerCd", searchDTO.getCareerCd());
        Indicator inVtmentYn = searchDTO.getInVtmentYn();
        if(hasValue(inVtmentYn)){
            sp = sp.and((r,q,c) -> c.equal(r.get("inVtmentYn"),inVtmentYn));
        }
        sp=setSpec(sp, "enterYmd", searchDTO.getEnterYmd());
        sp=setSpec(sp, "retireYmd", searchDTO.getRetireYmd());
        sp=setSpec(sp, "orderYmd", searchDTO.getOrderYmd());
        sp=setSpec(sp, "retireReason", searchDTO.getRetireReason());
        sp=setSpec(sp, "birthYmd", searchDTO.getBirthYmd());
        sp=setSpec(sp, "mobilePhone1", searchDTO.getMobilePhone1());
        sp=setSpec(sp, "mobilePhone2", searchDTO.getMobilePhone2());
        sp=setSpec(sp, "mobilePhone3", searchDTO.getMobilePhone3());
        sp=setSpec(sp, "directPhone1", searchDTO.getDirectPhone1());
        sp=setSpec(sp, "directPhone2", searchDTO.getDirectPhone2());
        sp=setSpec(sp, "directPhone3", searchDTO.getDirectPhone3());
        sp=setSpec(sp, "email", searchDTO.getEmail());
        sp=setSpec(sp, "postNo1", searchDTO.getPostNo1());
        sp=setSpec(sp, "postNo2", searchDTO.getPostNo2());
        sp=setSpec(sp, "baseAddr", searchDTO.getBaseAddr());
        sp=setSpec(sp, "detailAddr", searchDTO.getDetailAddr());
        Indicator marryYn = searchDTO.getMarryYn();
        if(hasValue(marryYn)){
            sp = sp.and((r,q,c) -> c.equal(r.get("marryYn"),marryYn));
        }
        sp=setSpec(sp, "bloodCd", searchDTO.getBloodCd());
        sp=setSpec(sp, "religionCd", searchDTO.getReligionCd());
        sp=setSpec(sp, "hobbyNm", searchDTO.getHobbyNm());
        sp=setSpec(sp, "ctbno", searchDTO.getCtbno());
        sp=setSpec(sp, "bioTypeCd", searchDTO.getBioTypeCd());
        Indicator lifeInsuYn = searchDTO.getLifeInsuYn();
        if(hasValue(lifeInsuYn)){
            sp = sp.and((r,q,c) -> c.equal(r.get("lifeInsuYn"),lifeInsuYn));
        }
        Indicator pnpnSvn = searchDTO.getPnpnSvn();
        if(hasValue(pnpnSvn)){
            sp = sp.and((r,q,c) -> c.equal(r.get("pnpnSvn"),pnpnSvn));
        }
        sp=setSpec(sp, "bankCd", searchDTO.getBankCd());
        sp=setSpec(sp, "accountNo", searchDTO.getAccountNo());
        sp=setSpec(sp, "photoFileNm", searchDTO.getPhotoFileNm());
        Indicator emailUseYn = searchDTO.getEmailUseYn();
        if(hasValue(emailUseYn)){
            sp = sp.and((r,q,c) -> c.equal(r.get("emailUseYn"),emailUseYn));
        }
        Indicator eCommP = searchDTO.getECommP();
        if(hasValue(eCommP)){
            sp = sp.and((r,q,c) -> c.equal(r.get("eCommP"),eCommP));
        }
        Integer includeNumber = searchDTO.getIncludeNumber();
        if(hasValue(includeNumber)){
            sp = sp.and((r,q,c) -> c.equal(r.get("includeNumber"),includeNumber));
        }
        Indicator advancedPayment = searchDTO.getAdvancedPayment();
        if(hasValue(advancedPayment)){
            sp = sp.and((r,q,c) -> c.equal(r.get("advancedPayment"),advancedPayment));
        }
        Indicator scholarship = searchDTO.getScholarship();
        if(hasValue(scholarship)){
            sp = sp.and((r,q,c) -> c.equal(r.get("scholarship"),scholarship));
        }
        sp=setSpec(sp, "scholarshipStart", searchDTO.getScholarshipStart());
        sp=setSpec(sp, "scholarshipEnd", searchDTO.getScholarshipEnd());
        Indicator childCare = searchDTO.getChildCare();
        if(hasValue(childCare)){
            sp = sp.and((r,q,c) -> c.equal(r.get("childCare"),childCare));
        }
        Indicator appoint = searchDTO.getAppoint();
        if(hasValue(appoint)){
            sp = sp.and((r,q,c) -> c.equal(r.get("appoint"),appoint));
        }
        sp=setSpec(sp, "appointMonth", searchDTO.getAppointMonth());
        sp=setSpec(sp, "introductionPolicy", searchDTO.getIntroductionPolicy());
        sp=setSpec(sp, "revertMonth", searchDTO.getRevertMonth());
        return sp;
    }
    
    
    public static Specification<EmpFamily> toSpec(EmpFamilySrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<EmpFamily> toSpec(EmpFamilySrchDTO searchDTO, Specification<EmpFamily> sp){
        sp=setSpec(sp, "empNo", searchDTO.getEmpNo());
        Long seq = searchDTO.getSeq();
        if(hasValue(seq)){
            sp = sp.and((r,q,c) -> c.equal(r.get("seq"),seq));
        }
        sp=setSpec(sp, "juminNo", searchDTO.getJuminNo());
        sp=setSpec(sp, "familyRelNm", searchDTO.getFamilyRelNm());
        sp=setSpec(sp, "name", searchDTO.getName());
        sp=setSpec(sp, "etc", searchDTO.getEtc());
        Indicator togetherYn = searchDTO.getTogetherYn();
        if(hasValue(togetherYn)){
            sp = sp.and((r,q,c) -> c.equal(r.get("togetherYn"),togetherYn));
        }
        return sp;
    }
    
    
    public static Specification<EmpEducation> toSpec(EmpEducationSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<EmpEducation> toSpec(EmpEducationSrchDTO searchDTO, Specification<EmpEducation> sp){
        sp=setSpec(sp, "empNo", searchDTO.getEmpNo());
        sp=setSpec(sp, "enterYmd", searchDTO.getEnterYmd());
        sp=setSpec(sp, "outYmd", searchDTO.getOutYmd());
        sp=setSpec(sp, "schoolNm", searchDTO.getSchoolNm());
        sp=setSpec(sp, "schoolCareerCd", searchDTO.getSchoolCareerCd());
        sp=setSpec(sp, "subjectNm", searchDTO.getSubjectNm());
        return sp;
    }
    
    
    public static Specification<Customer> toSpec(CustomerSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Customer> toSpec(CustomerSrchDTO searchDTO, Specification<Customer> sp){
        sp=setSpec(sp, "custNo", searchDTO.getCustNo());
        sp=setSpec(sp, "custCd", searchDTO.getCustCd());
        sp=setSpec(sp, "orgEmpNo", searchDTO.getOrgEmpNo());
        sp=setSpec(sp, "custNm", searchDTO.getCustNm());
        sp=setSpec(sp, "directPhone1", searchDTO.getDirectPhone1());
        sp=setSpec(sp, "directPhone2", searchDTO.getDirectPhone2());
        sp=setSpec(sp, "directPhone3", searchDTO.getDirectPhone3());
        sp=setSpec(sp, "academicCd", searchDTO.getAcademicCd());
        sp=setSpec(sp, "jobNm", searchDTO.getJobNm());
        sp=setSpec(sp, "mobilePhone1", searchDTO.getMobilePhone1());
        sp=setSpec(sp, "mobilePhone2", searchDTO.getMobilePhone2());
        sp=setSpec(sp, "mobilePhone3", searchDTO.getMobilePhone3());
        GenderCode gender = searchDTO.getGender();
        if(hasValue(gender)){
            sp = sp.and((r,q,c) -> c.equal(r.get("gender"),gender));
        }
        sp=setSpec(sp, "birthCd", searchDTO.getBirthCd());
        sp=setSpec(sp, "birthYmd", searchDTO.getBirthYmd());
        sp=setSpec(sp, "email", searchDTO.getEmail());
        sp=setSpec(sp, "homePostNo1", searchDTO.getHomePostNo1());
        sp=setSpec(sp, "homePostNo2", searchDTO.getHomePostNo2());
        sp=setSpec(sp, "homeBaseAddr", searchDTO.getHomeBaseAddr());
        sp=setSpec(sp, "homeDetailAddr", searchDTO.getHomeDetailAddr());
        MemorialCode memorialCd = searchDTO.getMemorialCd();
        if(hasValue(memorialCd)){
            sp = sp.and((r,q,c) -> c.equal(r.get("memorialCd"),memorialCd));
        }
        sp=setSpec(sp, "memorialYmd", searchDTO.getMemorialYmd());
        sp=setSpec(sp, "groupCd", searchDTO.getGroupCd());
        sp=setSpec(sp, "groupNm", searchDTO.getGroupNm());
        sp=setSpec(sp, "recommNm", searchDTO.getRecommNm());
        sp=setSpec(sp, "channelCd", searchDTO.getChannelCd());
        sp=setSpec(sp, "companyNm", searchDTO.getCompanyNm());
        sp=setSpec(sp, "jobPhone1", searchDTO.getJobPhone1());
        sp=setSpec(sp, "jobPhone2", searchDTO.getJobPhone2());
        sp=setSpec(sp, "jobPhone3", searchDTO.getJobPhone3());
        sp=setSpec(sp, "jobPostNo1", searchDTO.getJobPostNo1());
        sp=setSpec(sp, "jobPostNo2", searchDTO.getJobPostNo2());
        sp=setSpec(sp, "jobBaseAddr", searchDTO.getJobBaseAddr());
        sp=setSpec(sp, "jobDetailAddr", searchDTO.getJobDetailAddr());
        sp=setSpec(sp, "insuComCd", searchDTO.getInsuComCd());
        sp=setSpec(sp, "insuProdNm", searchDTO.getInsuProdNm());
        sp=setSpec(sp, "startYmd", searchDTO.getStartYmd());
        sp=setSpec(sp, "endYmd", searchDTO.getEndYmd());
        Indicator contYn = searchDTO.getContYn();
        if(hasValue(contYn)){
            sp = sp.and((r,q,c) -> c.equal(r.get("contYn"),contYn));
        }
        sp=setSpec(sp, "plateNo", searchDTO.getPlateNo());
        sp=setSpec(sp, "policyNo", searchDTO.getPolicyNo());
        return sp;
    }
    
    
    public static Specification<CustContactHistory> toSpec(CustContactHistorySrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CustContactHistory> toSpec(CustContactHistorySrchDTO searchDTO, Specification<CustContactHistory> sp){
        sp=setSpec(sp, "empNo", searchDTO.getEmpNo());
        Integer contactSeq = searchDTO.getContactSeq();
        if(hasValue(contactSeq)){
            sp = sp.and((r,q,c) -> c.equal(r.get("contactSeq"),contactSeq));
        }
        sp=setSpec(sp, "contactYmd", searchDTO.getContactYmd());
        sp=setSpec(sp, "contactTime", searchDTO.getContactTime());
        sp=setSpec(sp, "contactCd", searchDTO.getContactCd());
        sp=setSpec(sp, "custNm", searchDTO.getCustNm());
        sp=setSpec(sp, "custSeq", searchDTO.getCustSeq());
        sp=setSpec(sp, "custNo", searchDTO.getCustNo());
        sp=setSpec(sp, "policyNo", searchDTO.getPolicyNo());
        Indicator openYN = searchDTO.getOpenYN();
        if(hasValue(openYN)){
            sp = sp.and((r,q,c) -> c.equal(r.get("openYN"),openYN));
        }
        sp=setSpec(sp, "contactTtl", searchDTO.getContactTtl());
        sp=setSpec(sp, "contactPlace", searchDTO.getContactPlace());
        sp=setSpec(sp, "contactDesc", searchDTO.getContactDesc());
        return sp;
    }
    
    
    public static Specification<CustInsuranceStatus> toSpec(CustInsuranceStatusSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CustInsuranceStatus> toSpec(CustInsuranceStatusSrchDTO searchDTO, Specification<CustInsuranceStatus> sp){
        sp=setSpec(sp, "custSeq", searchDTO.getCustSeq());
        Integer seq = searchDTO.getSeq();
        if(hasValue(seq)){
            sp = sp.and((r,q,c) -> c.equal(r.get("seq"),seq));
        }
        sp=setSpec(sp, "insuComNm", searchDTO.getInsuComNm());
        sp=setSpec(sp, "insuItemNm", searchDTO.getInsuItemNm());
        sp=setSpec(sp, "insuProdNm", searchDTO.getInsuProdNm());
        sp=setSpec(sp, "startYmd", searchDTO.getStartYmd());
        sp=setSpec(sp, "endYmd", searchDTO.getEndYmd());
        return sp;
    }
    
    
    public static Specification<ContractSpecialClause> toSpec(ContractSpecialClauseSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<ContractSpecialClause> toSpec(ContractSpecialClauseSrchDTO searchDTO, Specification<ContractSpecialClause> sp){
        sp=setSpec(sp, "insuComCd", searchDTO.getInsuComCd());
        sp=setSpec(sp, "policyNo", searchDTO.getPolicyNo());
        Integer seq = searchDTO.getSeq();
        if(hasValue(seq)){
            sp = sp.and((r,q,c) -> c.equal(r.get("seq"),seq));
        }
        sp=setSpec(sp, "speNm", searchDTO.getSpeNm());
        sp=setSpec(sp, "startYmd", searchDTO.getStartYmd());
        sp=setSpec(sp, "endYmd", searchDTO.getEndYmd());
        Long basePrem = searchDTO.getBasePrem();
        if(hasValue(basePrem)){
            sp = sp.and((r,q,c) -> c.equal(r.get("basePrem"),basePrem));
        }
        Long payPrem = searchDTO.getPayPrem();
        if(hasValue(payPrem)){
            sp = sp.and((r,q,c) -> c.equal(r.get("payPrem"),payPrem));
        }
        sp=setSpec(sp, "speCd", searchDTO.getSpeCd());
        return sp;
    }
    
    
    public static Specification<ContractRelated> toSpec(ContractRelatedSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<ContractRelated> toSpec(ContractRelatedSrchDTO searchDTO, Specification<ContractRelated> sp){
        sp=setSpec(sp, "insuComCd", searchDTO.getInsuComCd());
        sp=setSpec(sp, "policyNo", searchDTO.getPolicyNo());
        Integer seq = searchDTO.getSeq();
        if(hasValue(seq)){
            sp = sp.and((r,q,c) -> c.equal(r.get("seq"),seq));
        }
        sp=setSpec(sp, "insTypeCd", searchDTO.getInsTypeCd());
        sp=setSpec(sp, "insHolderNm", searchDTO.getInsHolderNm());
        sp=setSpec(sp, "insHolderNo", searchDTO.getInsHolderNo());
        sp=setSpec(sp, "insRelCd", searchDTO.getInsRelCd());
        sp=setSpec(sp, "insAge", searchDTO.getInsAge());
        return sp;
    }
    
    
    public static Specification<ContractPayment> toSpec(ContractPaymentSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<ContractPayment> toSpec(ContractPaymentSrchDTO searchDTO, Specification<ContractPayment> sp){
        sp=setSpec(sp, "policyNo", searchDTO.getPolicyNo());
        sp=setSpec(sp, "insuComCd", searchDTO.getInsuComCd());
        Integer seq = searchDTO.getSeq();
        if(hasValue(seq)){
            sp = sp.and((r,q,c) -> c.equal(r.get("seq"),seq));
        }
        Integer distrCnt = searchDTO.getDistrCnt();
        if(hasValue(distrCnt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("distrCnt"),distrCnt));
        }
        sp=setSpec(sp, "payYMM", searchDTO.getPayYMM());
        sp=setSpec(sp, "payMethodCd", searchDTO.getPayMethodCd());
        sp=setSpec(sp, "payYmd", searchDTO.getPayYmd());
        Long sumPrem = searchDTO.getSumPrem();
        if(hasValue(sumPrem)){
            sp = sp.and((r,q,c) -> c.equal(r.get("sumPrem"),sumPrem));
        }
        Long modifyPrem = searchDTO.getModifyPrem();
        if(hasValue(modifyPrem)){
            sp = sp.and((r,q,c) -> c.equal(r.get("modifyPrem"),modifyPrem));
        }
        sp=setSpec(sp, "payStateCd", searchDTO.getPayStateCd());
        sp=setSpec(sp, "rectNo", searchDTO.getRectNo());
        sp=setSpec(sp, "rateType", searchDTO.getRateType());
        Long payAmt = searchDTO.getPayAmt();
        if(hasValue(payAmt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("payAmt"),payAmt));
        }
        sp=setSpec(sp, "jobDate", searchDTO.getJobDate());
        Indicator closYn = searchDTO.getClosYn();
        if(hasValue(closYn)){
            sp = sp.and((r,q,c) -> c.equal(r.get("closYn"),closYn));
        }
        sp=setSpec(sp, "policyNo_BK", searchDTO.getPolicyNo_BK());
        sp=setSpec(sp, "policyNo_New", searchDTO.getPolicyNo_New());
        return sp;
    }
    
    
    public static Specification<InsuranceCompanyCode> toSpec(InsuranceCompanyCodeSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<InsuranceCompanyCode> toSpec(InsuranceCompanyCodeSrchDTO searchDTO, Specification<InsuranceCompanyCode> sp){
        sp=setSpec(sp, "insCpCode", searchDTO.getInsCpCode());
        sp=setSpec(sp, "insCpTypeCd", searchDTO.getInsCpTypeCd());
        sp=setSpec(sp, "insCpNm", searchDTO.getInsCpNm());
        sp=setSpec(sp, "regDate", searchDTO.getRegDate());
        sp=setSpec(sp, "startDate", searchDTO.getStartDate());
        sp=setSpec(sp, "endDate", searchDTO.getEndDate());
        sp=setSpec(sp, "mgrNm", searchDTO.getMgrNm());
        sp=setSpec(sp, "contNum", searchDTO.getContNum());
        Indicator contYN = searchDTO.getContYN();
        if(hasValue(contYN)){
            sp = sp.and((r,q,c) -> c.equal(r.get("contYN"),contYN));
        }
        Integer mgtCnt = searchDTO.getMgtCnt();
        if(hasValue(mgtCnt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("mgtCnt"),mgtCnt));
        }
        Integer feeCnt = searchDTO.getFeeCnt();
        if(hasValue(feeCnt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("feeCnt"),feeCnt));
        }
        sp=setSpec(sp, "companyFaxNo", searchDTO.getCompanyFaxNo());
        Integer startCnt = searchDTO.getStartCnt();
        if(hasValue(startCnt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("startCnt"),startCnt));
        }
        Integer endCnt = searchDTO.getEndCnt();
        if(hasValue(endCnt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("endCnt"),endCnt));
        }
        Float pensionAdjustRate = searchDTO.getPensionAdjustRate();
        if(hasValue(pensionAdjustRate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("pensionAdjustRate"),pensionAdjustRate));
        }
        Float protectionAdjustRate = searchDTO.getProtectionAdjustRate();
        if(hasValue(protectionAdjustRate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("protectionAdjustRate"),protectionAdjustRate));
        }
        Float adjustRate = searchDTO.getAdjustRate();
        if(hasValue(adjustRate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("adjustRate"),adjustRate));
        }
        return sp;
    }
    
    
    public static Specification<InsProductCode> toSpec(InsProductCodeSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<InsProductCode> toSpec(InsProductCodeSrchDTO searchDTO, Specification<InsProductCode> sp){
        sp=setSpec(sp, "insCpCode", searchDTO.getInsCpCode());
        sp=setSpec(sp, "productCode", searchDTO.getProductCode());
        sp=setSpec(sp, "prodNm", searchDTO.getProdNm());
        sp=setSpec(sp, "prodTypeCd", searchDTO.getProdTypeCd());
        sp=setSpec(sp, "prodGrpCd", searchDTO.getProdGrpCd());
        sp=setSpec(sp, "insProdCd", searchDTO.getInsProdCd());
        sp=setSpec(sp, "saleStartDate", searchDTO.getSaleStartDate());
        sp=setSpec(sp, "saleEndDate", searchDTO.getSaleEndDate());
        Indicator saleYN = searchDTO.getSaleYN();
        if(hasValue(saleYN)){
            sp = sp.and((r,q,c) -> c.equal(r.get("saleYN"),saleYN));
        }
        Integer mgtCnt = searchDTO.getMgtCnt();
        if(hasValue(mgtCnt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("mgtCnt"),mgtCnt));
        }
        Integer adjustRate = searchDTO.getAdjustRate();
        if(hasValue(adjustRate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("adjustRate"),adjustRate));
        }
        Float newAdjustRate = searchDTO.getNewAdjustRate();
        if(hasValue(newAdjustRate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("newAdjustRate"),newAdjustRate));
        }
        return sp;
    }
    
    
    public static Specification<CommissionMaster> toSpec(CommissionMasterSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CommissionMaster> toSpec(CommissionMasterSrchDTO searchDTO, Specification<CommissionMaster> sp){
        sp=setSpec(sp, "closYm", searchDTO.getClosYm());
        sp=setSpec(sp, "insCpCode", searchDTO.getInsCpCode());
        sp=setSpec(sp, "policyNo", searchDTO.getPolicyNo());
        Integer endPayCnt = searchDTO.getEndPayCnt();
        if(hasValue(endPayCnt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("endPayCnt"),endPayCnt));
        }
        sp=setSpec(sp, "policStateCd", searchDTO.getPolicStateCd());
        sp=setSpec(sp, "empNo", searchDTO.getEmpNo());
        sp=setSpec(sp, "empName", searchDTO.getEmpName());
        sp=setSpec(sp, "orgEmpNo", searchDTO.getOrgEmpNo());
        sp=setSpec(sp, "insProdCd", searchDTO.getInsProdCd());
        sp=setSpec(sp, "productCode", searchDTO.getProductCode());
        sp=setSpec(sp, "polHolderNm", searchDTO.getPolHolderNm());
        sp=setSpec(sp, "contYmd", searchDTO.getContYmd());
        sp=setSpec(sp, "payCyclCd", searchDTO.getPayCyclCd());
        sp=setSpec(sp, "pmtyCd", searchDTO.getPmtyCd());
        sp=setSpec(sp, "payMethodCd", searchDTO.getPayMethodCd());
        sp=setSpec(sp, "payYmd", searchDTO.getPayYmd());
        Long inPrem = searchDTO.getInPrem();
        if(hasValue(inPrem)){
            sp = sp.and((r,q,c) -> c.equal(r.get("inPrem"),inPrem));
        }
        Integer distrCnt = searchDTO.getDistrCnt();
        if(hasValue(distrCnt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("distrCnt"),distrCnt));
        }
        Long cnvnFre = searchDTO.getCnvnFre();
        if(hasValue(cnvnFre)){
            sp = sp.and((r,q,c) -> c.equal(r.get("cnvnFre"),cnvnFre));
        }
        Integer cnvrtCnt = searchDTO.getCnvrtCnt();
        if(hasValue(cnvrtCnt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("cnvrtCnt"),cnvrtCnt));
        }
        Integer efficAchi = searchDTO.getEfficAchi();
        if(hasValue(efficAchi)){
            sp = sp.and((r,q,c) -> c.equal(r.get("efficAchi"),efficAchi));
        }
        sp=setSpec(sp, "payYMM", searchDTO.getPayYMM());
        Long cmiFeeAmt = searchDTO.getCmiFeeAmt();
        if(hasValue(cmiFeeAmt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("cmiFeeAmt"),cmiFeeAmt));
        }
        Long payBackFeeAmt = searchDTO.getPayBackFeeAmt();
        if(hasValue(payBackFeeAmt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("payBackFeeAmt"),payBackFeeAmt));
        }
        Long incentiveFee = searchDTO.getIncentiveFee();
        if(hasValue(incentiveFee)){
            sp = sp.and((r,q,c) -> c.equal(r.get("incentiveFee"),incentiveFee));
        }
        return sp;
    }
    
    
    public static Specification<CommissionContractDetails> toSpec(CommissionContractDetailsSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CommissionContractDetails> toSpec(CommissionContractDetailsSrchDTO searchDTO, Specification<CommissionContractDetails> sp){
        sp=setSpec(sp, "closYm", searchDTO.getClosYm());
        sp=setSpec(sp, "insCpCode", searchDTO.getInsCpCode());
        sp=setSpec(sp, "policyNo", searchDTO.getPolicyNo());
        Integer endPayCnt = searchDTO.getEndPayCnt();
        if(hasValue(endPayCnt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("endPayCnt"),endPayCnt));
        }
        sp=setSpec(sp, "commiTypeCd", searchDTO.getCommiTypeCd());
        sp=setSpec(sp, "cmiFeeCd", searchDTO.getCmiFeeCd());
        sp=setSpec(sp, "feeStateCd", searchDTO.getFeeStateCd());
        Long cnvFre = searchDTO.getCnvFre();
        if(hasValue(cnvFre)){
            sp = sp.and((r,q,c) -> c.equal(r.get("cnvFre"),cnvFre));
        }
        Integer cmiFeeRate = searchDTO.getCmiFeeRate();
        if(hasValue(cmiFeeRate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("cmiFeeRate"),cmiFeeRate));
        }
        Long payBackFeeAmt = searchDTO.getPayBackFeeAmt();
        if(hasValue(payBackFeeAmt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("payBackFeeAmt"),payBackFeeAmt));
        }
        Long cmiFeeAmt = searchDTO.getCmiFeeAmt();
        if(hasValue(cmiFeeAmt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("cmiFeeAmt"),cmiFeeAmt));
        }
        sp=setSpec(sp, "empNo", searchDTO.getEmpNo());
        sp=setSpec(sp, "payPtrnCode", searchDTO.getPayPtrnCode());
        sp=setSpec(sp, "prodGrpCd", searchDTO.getProdGrpCd());
        return sp;
    }
    
    
    public static Specification<CommissionDetails> toSpec(CommissionDetailsSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CommissionDetails> toSpec(CommissionDetailsSrchDTO searchDTO, Specification<CommissionDetails> sp){
        sp=setSpec(sp, "closYm", searchDTO.getClosYm());
        sp=setSpec(sp, "insCpCode", searchDTO.getInsCpCode());
        sp=setSpec(sp, "policyNo", searchDTO.getPolicyNo());
        Integer endPayCnt = searchDTO.getEndPayCnt();
        if(hasValue(endPayCnt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("endPayCnt"),endPayCnt));
        }
        sp=setSpec(sp, "commiTypeCd", searchDTO.getCommiTypeCd());
        sp=setSpec(sp, "cmiFeeCd", searchDTO.getCmiFeeCd());
        sp=setSpec(sp, "feeStateCd", searchDTO.getFeeStateCd());
        Long cnvrFe = searchDTO.getCnvrFe();
        if(hasValue(cnvrFe)){
            sp = sp.and((r,q,c) -> c.equal(r.get("cnvrFe"),cnvrFe));
        }
        Float cmiFeeRate = searchDTO.getCmiFeeRate();
        if(hasValue(cmiFeeRate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("cmiFeeRate"),cmiFeeRate));
        }
        Long payBackFeeAmt = searchDTO.getPayBackFeeAmt();
        if(hasValue(payBackFeeAmt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("payBackFeeAmt"),payBackFeeAmt));
        }
        Long cmiFeeAmt = searchDTO.getCmiFeeAmt();
        if(hasValue(cmiFeeAmt)){
            sp = sp.and((r,q,c) -> c.equal(r.get("cmiFeeAmt"),cmiFeeAmt));
        }
        sp=setSpec(sp, "policStateCd", searchDTO.getPolicStateCd());
        java.time.LocalDateTime sysInputDate = searchDTO.getSysInputDate();
        if(hasValue(sysInputDate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("sysInputDate"),sysInputDate));
        }
        sp=setSpec(sp, "sysInputUser", searchDTO.getSysInputUser());
        sp=setSpec(sp, "sysInputIP", searchDTO.getSysInputIP());
        sp=setSpec(sp, "payPtrnCode", searchDTO.getPayPtrnCode());
        return sp;
    }
    

    protected static Specification setSpec(Specification sp, String fieldName, String value) {
        if(hasValue(value)){
            if(value.startsWith("%") || value.endsWith("%"))
                return sp.and((r,q,c) -> c.like(r.get(fieldName),value));
            else
                return sp.and((r,q,c) -> c.equal(r.get(fieldName),value));
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


    private static boolean isSkip(String fromClassName, String refClassName) {
        return false;
    }

    public static void setVal(String value, Consumer<String> setter, boolean isSkipNull) {
        if ((value != null && !value.isEmpty())  || !isSkipNull) {
            Optional.ofNullable(value).ifPresent(setter);
        }
    }

    public static <T> void setVal(T value, Consumer<T> setter, boolean isSkipNull) {
        if (value != null || !isSkipNull) {
            Optional.ofNullable(value).ifPresent(setter);
        }
    }


    public static <T, R> void setVal(T value, Consumer<R> setter, boolean isSkipNull, Function<T, R> mapper) {


        if (value != null || !isSkipNull) {
            Optional.ofNullable(value).map(mapper).ifPresent(setter);
        }
    }
}
