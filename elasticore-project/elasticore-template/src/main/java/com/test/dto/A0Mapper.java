//ecd:-38284293H20250117173851_V1.0
package com.test.dto;

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
import com.test.entity.*;
import com.test.dto.*;



import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class A0Mapper {

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

    
    public static void mapping(Product from, ProductDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getPid(), to::setPid, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getEngName(), to::setEngName, isSkipNull);
        setVal(from.getDesc(), to::setDesc, isSkipNull);
        setVal(from.getPrice(), to::setPrice, isSkipNull);
        setVal(from.getWeight(), to::setWeight, isSkipNull);
    }
    
    
    public static void mapping(Product from, ProductDTO to){
        mapping(from,to,false);
    }
    
    
    public static ProductDTO toDTO(Product from){
        if(from==null) return null;
        ProductDTO to = new ProductDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ProductDTO> toProductDTOList(List<Product> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A0Mapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<ProductDTO> toProductDTOList(List<Product> fromList, BiFunction<Product, ProductDTO, ProductDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ProductDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ProductDTO from, Product to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getPid(), to::setPid, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getEngName(), to::setEngName, isSkipNull);
        setVal(from.getDesc(), to::setDesc, isSkipNull);
        setVal(from.getPrice(), to::setPrice, isSkipNull);
        setVal(from.getWeight(), to::setWeight, isSkipNull);
    }
    
    
    public static void mapping(ProductDTO from, Product to){
        mapping(from,to,false);
    }
    
    
    public static Product toEntity(ProductDTO from){
        if(from==null) return null;
        Product to = new Product();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Product> toProductList(List<ProductDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A0Mapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Product> toProductList(List<ProductDTO> fromList, BiFunction<ProductDTO, Product, Product> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Product to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<Product> toSpec(ProductSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Product> toSpec(ProductSrchDTO searchDTO, Specification<Product> sp){
        sp=setSpec(sp, "pid", searchDTO.getPid());
        sp=setSpec(sp, "name", searchDTO.getName());
        sp=setSpec(sp, "engName", searchDTO.getEngName());
        sp=setSpec(sp, "desc", searchDTO.getDesc());
        Long price = searchDTO.getPrice();
        if(hasValue(price)){
            sp = sp.and((r,q,c) -> c.equal(r.get("price"),price));
        }
        Double weight = searchDTO.getWeight();
        if(hasValue(weight)){
            sp = sp.and((r,q,c) -> c.equal(r.get("weight"),weight));
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
