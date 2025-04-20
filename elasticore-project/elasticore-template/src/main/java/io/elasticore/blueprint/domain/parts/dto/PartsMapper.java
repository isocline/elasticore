//ecd:861957415H20250416202052_V1.0
package io.elasticore.blueprint.domain.parts.dto;

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Join;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import io.elasticore.blueprint.domain.parts.entity.*;
import io.elasticore.blueprint.domain.parts.dto.*;


import io.elasticore.blueprint.domain.parts.entity.*;
import io.elasticore.blueprint.domain.parts.dto.*;
import io.elasticore.springboot3.mapper.MappingContext;
import io.elasticore.runtime.security.TransformPermissionChecker;

/**
 * Provides transformation utilities between DTOs and entities,
 * including conditional mapping, permission validation, and dynamic query support.
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */


public class PartsMapper {

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

    
    public static void mapping(Catalog from, CatalogDTO to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(Catalog from, CatalogDTO to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("catalogId").checkEnable())
        setVal(from.getCatalogId(), to::setCatalogId, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(Catalog from, CatalogDTO to){
        mapping(from,to,false);
    }
    public static void mapping(Catalog from, CatalogDTO to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static CatalogDTO toDTO(Catalog from){
        return toDTO(from,MappingContext.withGuard(2,null));
    }
    public static CatalogDTO toDTO(Catalog from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        CatalogDTO to = new CatalogDTO();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<CatalogDTO> toCatalogDTOList(List<Catalog> fromList){
        return toCatalogDTOList(fromList,(MappingContext) null);
    }
    public static List<CatalogDTO> toCatalogDTOList(List<Catalog> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toDTO(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<CatalogDTO> toCatalogDTOList(List<Catalog> fromList, BiFunction<Catalog, CatalogDTO, CatalogDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CatalogDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CarModel from, CarModelDTO to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(CarModel from, CarModelDTO to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("catalog").checkEnable())
        setVal(from.getCatalog(), to::setCatalog, isSkipNull, e->toDTO(e,c));
        if(hasValue(from.getCatalog()))
            to.setCatalogCatalogId(from.getCatalog().getCatalogId());
        if(c==null || c.fd("id").checkEnable())
        setVal(from.getId(), to::setId, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
        if(c==null || c.fd("img").checkEnable())
        setVal(from.getImg(), to::setImg, isSkipNull);
    }
    
    
    public static void mapping(CarModel from, CarModelDTO to){
        mapping(from,to,false);
    }
    public static void mapping(CarModel from, CarModelDTO to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static CarModelDTO toDTO(CarModel from){
        return toDTO(from,MappingContext.withGuard(2,null));
    }
    public static CarModelDTO toDTO(CarModel from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        CarModelDTO to = new CarModelDTO();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<CarModelDTO> toCarModelDTOList(List<CarModel> fromList){
        return toCarModelDTOList(fromList,(MappingContext) null);
    }
    public static List<CarModelDTO> toCarModelDTOList(List<CarModel> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toDTO(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<CarModelDTO> toCarModelDTOList(List<CarModel> fromList, BiFunction<CarModel, CarModelDTO, CarModelDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CarModelDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CarInfo from, CarInfoDTO to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(CarInfo from, CarInfoDTO to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("parameters").checkEnable())
        setVal(from.getParameters(), to::setParameters, isSkipNull, e->toParamInfoDTOList(e,c));
        if(c==null || c.fd("id").checkEnable())
        setVal(from.getId(), to::setId, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
        if(c==null || c.fd("description").checkEnable())
        setVal(from.getDescription(), to::setDescription, isSkipNull);
        if(c==null || c.fd("modelName").checkEnable())
        setVal(from.getModelName(), to::setModelName, isSkipNull);
        if(c==null || c.fd("criteria").checkEnable())
        setVal(from.getCriteria(), to::setCriteria, isSkipNull);
        if(c==null || c.fd("brand").checkEnable())
        setVal(from.getBrand(), to::setBrand, isSkipNull);
        if(c==null || c.fd("groupTreeAvailables").checkEnable())
        setVal(from.getGroupTreeAvailables(), to::setGroupTreeAvailables, isSkipNull);
    }
    
    
    public static void mapping(CarInfo from, CarInfoDTO to){
        mapping(from,to,false);
    }
    public static void mapping(CarInfo from, CarInfoDTO to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static CarInfoDTO toDTO(CarInfo from){
        return toDTO(from,MappingContext.withGuard(2,null));
    }
    public static CarInfoDTO toDTO(CarInfo from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        CarInfoDTO to = new CarInfoDTO();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<CarInfoDTO> toCarInfoDTOList(List<CarInfo> fromList){
        return toCarInfoDTOList(fromList,(MappingContext) null);
    }
    public static List<CarInfoDTO> toCarInfoDTOList(List<CarInfo> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toDTO(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<CarInfoDTO> toCarInfoDTOList(List<CarInfo> fromList, BiFunction<CarInfo, CarInfoDTO, CarInfoDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CarInfoDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ParamInfo from, ParamInfoDTO to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(ParamInfo from, ParamInfoDTO to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("idx").checkEnable())
        setVal(from.getIdx(), to::setIdx, isSkipNull);
        if(c==null || c.fd("key").checkEnable())
        setVal(from.getKey(), to::setKey, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
        if(c==null || c.fd("value").checkEnable())
        setVal(from.getValue(), to::setValue, isSkipNull);
        if(c==null || c.fd("carId").checkEnable())
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        if(c==null || c.fd("sortOrder").checkEnable())
        setVal(from.getSortOrder(), to::setSortOrder, isSkipNull);
    }
    
    
    public static void mapping(ParamInfo from, ParamInfoDTO to){
        mapping(from,to,false);
    }
    public static void mapping(ParamInfo from, ParamInfoDTO to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static ParamInfoDTO toDTO(ParamInfo from){
        return toDTO(from,MappingContext.withGuard(2,null));
    }
    public static ParamInfoDTO toDTO(ParamInfo from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        ParamInfoDTO to = new ParamInfoDTO();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<ParamInfoDTO> toParamInfoDTOList(List<ParamInfo> fromList){
        return toParamInfoDTOList(fromList,(MappingContext) null);
    }
    public static List<ParamInfoDTO> toParamInfoDTOList(List<ParamInfo> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toDTO(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<ParamInfoDTO> toParamInfoDTOList(List<ParamInfo> fromList, BiFunction<ParamInfo, ParamInfoDTO, ParamInfoDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ParamInfoDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(PartGroup from, PartGroupDTO to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(PartGroup from, PartGroupDTO to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("id").checkEnable())
        setVal(from.getId(), to::setId, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
        if(c==null || c.fd("hasSubgroups").checkEnable())
        setVal(from.getHasSubgroups(), to::setHasSubgroups, isSkipNull);
        if(c==null || c.fd("img").checkEnable())
        setVal(from.getImg(), to::setImg, isSkipNull);
        if(c==null || c.fd("description").checkEnable())
        setVal(from.getDescription(), to::setDescription, isSkipNull);
        if(c==null || c.fd("parentId").checkEnable())
        setVal(from.getParentId(), to::setParentId, isSkipNull);
        if(c==null || c.fd("carId").checkEnable())
        setVal(from.getCarId(), to::setCarId, isSkipNull);
    }
    
    
    public static void mapping(PartGroup from, PartGroupDTO to){
        mapping(from,to,false);
    }
    public static void mapping(PartGroup from, PartGroupDTO to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static PartGroupDTO toDTO(PartGroup from){
        return toDTO(from,MappingContext.withGuard(2,null));
    }
    public static PartGroupDTO toDTO(PartGroup from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        PartGroupDTO to = new PartGroupDTO();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<PartGroupDTO> toPartGroupDTOList(List<PartGroup> fromList){
        return toPartGroupDTOList(fromList,(MappingContext) null);
    }
    public static List<PartGroupDTO> toPartGroupDTOList(List<PartGroup> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toDTO(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<PartGroupDTO> toPartGroupDTOList(List<PartGroup> fromList, BiFunction<PartGroup, PartGroupDTO, PartGroupDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                PartGroupDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CarProfile from, CarProfileDTO to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(CarProfile from, CarProfileDTO to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("carInfo").checkEnable())
        setVal(from.getCarInfo(), to::setCarInfo, isSkipNull, e->toDTO(e,c));
        if(hasValue(from.getCarInfo()))
            to.setCarInfoId(from.getCarInfo().getId());
        if(c==null || c.fd("vin").checkEnable())
        setVal(from.getVin(), to::setVin, isSkipNull);
        if(c==null || c.fd("frame").checkEnable())
        setVal(from.getFrame(), to::setFrame, isSkipNull);
    }
    
    
    public static void mapping(CarProfile from, CarProfileDTO to){
        mapping(from,to,false);
    }
    public static void mapping(CarProfile from, CarProfileDTO to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static CarProfileDTO toDTO(CarProfile from){
        return toDTO(from,MappingContext.withGuard(2,null));
    }
    public static CarProfileDTO toDTO(CarProfile from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        CarProfileDTO to = new CarProfileDTO();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<CarProfileDTO> toCarProfileDTOList(List<CarProfile> fromList){
        return toCarProfileDTOList(fromList,(MappingContext) null);
    }
    public static List<CarProfileDTO> toCarProfileDTOList(List<CarProfile> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toDTO(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<CarProfileDTO> toCarProfileDTOList(List<CarProfile> fromList, BiFunction<CarProfile, CarProfileDTO, CarProfileDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CarProfileDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CatalogDTO from, Catalog to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(CatalogDTO from, Catalog to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("catalogId").checkEnable())
        setVal(from.getCatalogId(), to::setCatalogId, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(CatalogDTO from, Catalog to){
        mapping(from,to,false);
    }
    public static void mapping(CatalogDTO from, Catalog to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static Catalog toEntity(CatalogDTO from){
        return toEntity(from,MappingContext.withGuard(2,null));
    }
    public static Catalog toEntity(CatalogDTO from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        Catalog to = new Catalog();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<Catalog> toCatalogList(List<CatalogDTO> fromList){
        return toCatalogList(fromList,(MappingContext) null);
    }
    public static List<Catalog> toCatalogList(List<CatalogDTO> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toEntity(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<Catalog> toCatalogList(List<CatalogDTO> fromList, BiFunction<CatalogDTO, Catalog, Catalog> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Catalog to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CarModelDTO from, CarModel to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(CarModelDTO from, CarModel to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("id").checkEnable())
        setVal(from.getId(), to::setId, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
        if(c==null || c.fd("img").checkEnable())
        setVal(from.getImg(), to::setImg, isSkipNull);
        
        
        if(hasValue(from.getCatalogCatalogId())){
            Catalog t = new Catalog();
            t.setCatalogId(from.getCatalogCatalogId());
            to.setCatalog(t);
        }
    }
    
    
    public static void mapping(CarModelDTO from, CarModel to){
        mapping(from,to,false);
    }
    public static void mapping(CarModelDTO from, CarModel to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static CarModel toEntity(CarModelDTO from){
        return toEntity(from,MappingContext.withGuard(2,null));
    }
    public static CarModel toEntity(CarModelDTO from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        CarModel to = new CarModel();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<CarModel> toCarModelList(List<CarModelDTO> fromList){
        return toCarModelList(fromList,(MappingContext) null);
    }
    public static List<CarModel> toCarModelList(List<CarModelDTO> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toEntity(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<CarModel> toCarModelList(List<CarModelDTO> fromList, BiFunction<CarModelDTO, CarModel, CarModel> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CarModel to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CarInfoDTO from, CarInfo to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(CarInfoDTO from, CarInfo to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("id").checkEnable())
        setVal(from.getId(), to::setId, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
        if(c==null || c.fd("description").checkEnable())
        setVal(from.getDescription(), to::setDescription, isSkipNull);
        if(c==null || c.fd("modelName").checkEnable())
        setVal(from.getModelName(), to::setModelName, isSkipNull);
        if(c==null || c.fd("criteria").checkEnable())
        setVal(from.getCriteria(), to::setCriteria, isSkipNull);
        if(c==null || c.fd("brand").checkEnable())
        setVal(from.getBrand(), to::setBrand, isSkipNull);
        if(c==null || c.fd("groupTreeAvailables").checkEnable())
        setVal(from.getGroupTreeAvailables(), to::setGroupTreeAvailables, isSkipNull);
    }
    
    
    public static void mapping(CarInfoDTO from, CarInfo to){
        mapping(from,to,false);
    }
    public static void mapping(CarInfoDTO from, CarInfo to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static CarInfo toEntity(CarInfoDTO from){
        return toEntity(from,MappingContext.withGuard(2,null));
    }
    public static CarInfo toEntity(CarInfoDTO from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        CarInfo to = new CarInfo();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<CarInfo> toCarInfoList(List<CarInfoDTO> fromList){
        return toCarInfoList(fromList,(MappingContext) null);
    }
    public static List<CarInfo> toCarInfoList(List<CarInfoDTO> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toEntity(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<CarInfo> toCarInfoList(List<CarInfoDTO> fromList, BiFunction<CarInfoDTO, CarInfo, CarInfo> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CarInfo to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ParamInfoDTO from, ParamInfo to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(ParamInfoDTO from, ParamInfo to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("idx").checkEnable())
        setVal(from.getIdx(), to::setIdx, isSkipNull);
        if(c==null || c.fd("key").checkEnable())
        setVal(from.getKey(), to::setKey, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
        if(c==null || c.fd("value").checkEnable())
        setVal(from.getValue(), to::setValue, isSkipNull);
        if(c==null || c.fd("carId").checkEnable())
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        if(c==null || c.fd("sortOrder").checkEnable())
        setVal(from.getSortOrder(), to::setSortOrder, isSkipNull);
    }
    
    
    public static void mapping(ParamInfoDTO from, ParamInfo to){
        mapping(from,to,false);
    }
    public static void mapping(ParamInfoDTO from, ParamInfo to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static ParamInfo toEntity(ParamInfoDTO from){
        return toEntity(from,MappingContext.withGuard(2,null));
    }
    public static ParamInfo toEntity(ParamInfoDTO from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        ParamInfo to = new ParamInfo();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<ParamInfo> toParamInfoList(List<ParamInfoDTO> fromList){
        return toParamInfoList(fromList,(MappingContext) null);
    }
    public static List<ParamInfo> toParamInfoList(List<ParamInfoDTO> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toEntity(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<ParamInfo> toParamInfoList(List<ParamInfoDTO> fromList, BiFunction<ParamInfoDTO, ParamInfo, ParamInfo> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ParamInfo to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(PartGroupDTO from, PartGroup to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(PartGroupDTO from, PartGroup to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("id").checkEnable())
        setVal(from.getId(), to::setId, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
        if(c==null || c.fd("hasSubgroups").checkEnable())
        setVal(from.getHasSubgroups(), to::setHasSubgroups, isSkipNull);
        if(c==null || c.fd("img").checkEnable())
        setVal(from.getImg(), to::setImg, isSkipNull);
        if(c==null || c.fd("description").checkEnable())
        setVal(from.getDescription(), to::setDescription, isSkipNull);
        if(c==null || c.fd("parentId").checkEnable())
        setVal(from.getParentId(), to::setParentId, isSkipNull);
        if(c==null || c.fd("carId").checkEnable())
        setVal(from.getCarId(), to::setCarId, isSkipNull);
    }
    
    
    public static void mapping(PartGroupDTO from, PartGroup to){
        mapping(from,to,false);
    }
    public static void mapping(PartGroupDTO from, PartGroup to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static PartGroup toEntity(PartGroupDTO from){
        return toEntity(from,MappingContext.withGuard(2,null));
    }
    public static PartGroup toEntity(PartGroupDTO from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        PartGroup to = new PartGroup();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<PartGroup> toPartGroupList(List<PartGroupDTO> fromList){
        return toPartGroupList(fromList,(MappingContext) null);
    }
    public static List<PartGroup> toPartGroupList(List<PartGroupDTO> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toEntity(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<PartGroup> toPartGroupList(List<PartGroupDTO> fromList, BiFunction<PartGroupDTO, PartGroup, PartGroup> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                PartGroup to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CarProfileDTO from, CarProfile to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(CarProfileDTO from, CarProfile to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("vin").checkEnable())
        setVal(from.getVin(), to::setVin, isSkipNull);
        if(c==null || c.fd("frame").checkEnable())
        setVal(from.getFrame(), to::setFrame, isSkipNull);
        
        
        if(hasValue(from.getCarInfoId())){
            CarInfo t = new CarInfo();
            t.setId(from.getCarInfoId());
            to.setCarInfo(t);
        }
    }
    
    
    public static void mapping(CarProfileDTO from, CarProfile to){
        mapping(from,to,false);
    }
    public static void mapping(CarProfileDTO from, CarProfile to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static CarProfile toEntity(CarProfileDTO from){
        return toEntity(from,MappingContext.withGuard(2,null));
    }
    public static CarProfile toEntity(CarProfileDTO from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        CarProfile to = new CarProfile();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<CarProfile> toCarProfileList(List<CarProfileDTO> fromList){
        return toCarProfileList(fromList,(MappingContext) null);
    }
    public static List<CarProfile> toCarProfileList(List<CarProfileDTO> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->PartsMapper.toEntity(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<CarProfile> toCarProfileList(List<CarProfileDTO> fromList, BiFunction<CarProfileDTO, CarProfile, CarProfile> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CarProfile to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<Catalog> toSpec(CatalogSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Catalog> toSpec(CatalogSrchDTO searchDTO, Specification<Catalog> sp){
        sp=setSpec(sp, "catalogId", searchDTO.getCatalogId());
        sp=setSpec(sp, "name", searchDTO.getName());
        return sp;
    }
    
    
    public static Specification<CarModel> toSpec(CarModelSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CarModel> toSpec(CarModelSrchDTO searchDTO, Specification<CarModel> sp){
        String catalogCatalogId = searchDTO.getCatalogCatalogId();
        if(hasValue(catalogCatalogId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("catalog").get("catalogId"),catalogCatalogId));
        }
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "name", searchDTO.getName());
        sp=setSpec(sp, "img", searchDTO.getImg());
        return sp;
    }
    
    
    public static Specification<CarInfo> toSpec(CarInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CarInfo> toSpec(CarInfoSrchDTO searchDTO, Specification<CarInfo> sp){
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "name", searchDTO.getName());
        sp=setSpec(sp, "description", searchDTO.getDescription());
        sp=setSpec(sp, "CarModel", searchDTO.getModelId());
        sp=setSpec(sp, "modelName", searchDTO.getModelName());
        sp=setSpec(sp, "criteria", searchDTO.getCriteria());
        sp=setSpec(sp, "brand", searchDTO.getBrand());
        Boolean groupTreeAvailables = searchDTO.getGroupTreeAvailables();
        if(hasValue(groupTreeAvailables)){
            sp = sp.and((r,q,c) -> c.equal(r.get("groupTreeAvailables"),groupTreeAvailables));
        }
        return sp;
    }
    
    
    public static Specification<ParamInfo> toSpec(ParamInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<ParamInfo> toSpec(ParamInfoSrchDTO searchDTO, Specification<ParamInfo> sp){
        sp=setSpec(sp, "idx", searchDTO.getIdx());
        sp=setSpec(sp, "key", searchDTO.getKey());
        sp=setSpec(sp, "name", searchDTO.getName());
        sp=setSpec(sp, "value", searchDTO.getValue());
        sp=setSpec(sp, "carId", searchDTO.getCarId());
        Integer sortOrder = searchDTO.getSortOrder();
        if(hasValue(sortOrder)){
            sp = sp.and((r,q,c) -> c.equal(r.get("sortOrder"),sortOrder));
        }
        return sp;
    }
    
    
    public static Specification<PartGroup> toSpec(PartGroupSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<PartGroup> toSpec(PartGroupSrchDTO searchDTO, Specification<PartGroup> sp){
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "name", searchDTO.getName());
        Boolean hasSubgroups = searchDTO.getHasSubgroups();
        if(hasValue(hasSubgroups)){
            sp = sp.and((r,q,c) -> c.equal(r.get("hasSubgroups"),hasSubgroups));
        }
        sp=setSpec(sp, "img", searchDTO.getImg());
        sp=setSpec(sp, "description", searchDTO.getDescription());
        sp=setSpec(sp, "parentId", searchDTO.getParentId());
        sp=setSpec(sp, "carId", searchDTO.getCarId());
        return sp;
    }
    
    
    public static Specification<CarProfile> toSpec(CarProfileSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CarProfile> toSpec(CarProfileSrchDTO searchDTO, Specification<CarProfile> sp){
        String carInfoId = searchDTO.getCarInfoId();
        if(hasValue(carInfoId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("carInfo").get("id"),carInfoId));
        }
        sp=setSpec(sp, "vin", searchDTO.getVin());
        sp=setSpec(sp, "frame", searchDTO.getFrame());
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
