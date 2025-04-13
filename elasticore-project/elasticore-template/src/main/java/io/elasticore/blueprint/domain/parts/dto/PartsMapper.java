//ecd:-640466591H20250412200533_V1.0
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
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCatalogId(), to::setCatalogId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(Catalog from, CatalogDTO to){
        mapping(from,to,false);
    }
    
    
    public static CatalogDTO toDTO(Catalog from){
        if(from==null) return null;
        CatalogDTO to = new CatalogDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CatalogDTO> toCatalogDTOList(List<Catalog> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(PartsMapper::toDTO).collect(Collectors.toList());
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
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCatalog(), to::setCatalog, isSkipNull, PartsMapper::toDTO);
        if(hasValue(from.getCatalog()))
            to.setCatalogCatalogId(from.getCatalog().getCatalogId());
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getImg(), to::setImg, isSkipNull);
    }
    
    
    public static void mapping(CarModel from, CarModelDTO to){
        mapping(from,to,false);
    }
    
    
    public static CarModelDTO toDTO(CarModel from){
        if(from==null) return null;
        CarModelDTO to = new CarModelDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CarModelDTO> toCarModelDTOList(List<CarModel> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(PartsMapper::toDTO).collect(Collectors.toList());
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
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getDescription(), to::setDescription, isSkipNull);
        setVal(from.getModelName(), to::setModelName, isSkipNull);
        setVal(from.getVin(), to::setVin, isSkipNull);
        setVal(from.getFrame(), to::setFrame, isSkipNull);
        setVal(from.getCriteria(), to::setCriteria, isSkipNull);
        setVal(from.getBrand(), to::setBrand, isSkipNull);
        setVal(from.getGroupTreeAvailables(), to::setGroupTreeAvailables, isSkipNull);
    }
    
    
    public static void mapping(CarInfo from, CarInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static CarInfoDTO toDTO(CarInfo from){
        if(from==null) return null;
        CarInfoDTO to = new CarInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CarInfoDTO> toCarInfoDTOList(List<CarInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(PartsMapper::toDTO).collect(Collectors.toList());
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
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getIdx(), to::setIdx, isSkipNull);
        setVal(from.getKey(), to::setKey, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getValue(), to::setValue, isSkipNull);
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        setVal(from.getSortOrder(), to::setSortOrder, isSkipNull);
    }
    
    
    public static void mapping(ParamInfo from, ParamInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static ParamInfoDTO toDTO(ParamInfo from){
        if(from==null) return null;
        ParamInfoDTO to = new ParamInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ParamInfoDTO> toParamInfoDTOList(List<ParamInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(PartsMapper::toDTO).collect(Collectors.toList());
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
    
    
    public static void mapping(CatalogDTO from, Catalog to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCatalogId(), to::setCatalogId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(CatalogDTO from, Catalog to){
        mapping(from,to,false);
    }
    
    
    public static Catalog toEntity(CatalogDTO from){
        if(from==null) return null;
        Catalog to = new Catalog();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Catalog> toCatalogList(List<CatalogDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(PartsMapper::toEntity).collect(Collectors.toList());
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
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
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
    
    
    public static CarModel toEntity(CarModelDTO from){
        if(from==null) return null;
        CarModel to = new CarModel();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CarModel> toCarModelList(List<CarModelDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(PartsMapper::toEntity).collect(Collectors.toList());
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
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getDescription(), to::setDescription, isSkipNull);
        setVal(from.getModelName(), to::setModelName, isSkipNull);
        setVal(from.getVin(), to::setVin, isSkipNull);
        setVal(from.getFrame(), to::setFrame, isSkipNull);
        setVal(from.getCriteria(), to::setCriteria, isSkipNull);
        setVal(from.getBrand(), to::setBrand, isSkipNull);
        setVal(from.getGroupTreeAvailables(), to::setGroupTreeAvailables, isSkipNull);
    }
    
    
    public static void mapping(CarInfoDTO from, CarInfo to){
        mapping(from,to,false);
    }
    
    
    public static CarInfo toEntity(CarInfoDTO from){
        if(from==null) return null;
        CarInfo to = new CarInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CarInfo> toCarInfoList(List<CarInfoDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(PartsMapper::toEntity).collect(Collectors.toList());
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
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getIdx(), to::setIdx, isSkipNull);
        setVal(from.getKey(), to::setKey, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getValue(), to::setValue, isSkipNull);
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        setVal(from.getSortOrder(), to::setSortOrder, isSkipNull);
    }
    
    
    public static void mapping(ParamInfoDTO from, ParamInfo to){
        mapping(from,to,false);
    }
    
    
    public static ParamInfo toEntity(ParamInfoDTO from){
        if(from==null) return null;
        ParamInfo to = new ParamInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ParamInfo> toParamInfoList(List<ParamInfoDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(PartsMapper::toEntity).collect(Collectors.toList());
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
        sp=setSpec(sp, "vin", searchDTO.getVin());
        sp=setSpec(sp, "frame", searchDTO.getFrame());
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
