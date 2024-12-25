//ecd:-946888296H20241224183121_V1.0
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

    
    public static void mapping(Article from, ArticleDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(Article from, ArticleDTO to){
        mapping(from,to,false);
    }
    
    
    public static ArticleDTO toDTO(Article from){
        if(from==null) return null;
        ArticleDTO to = new ArticleDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ArticleDTO> toArticleDTOList(List<Article> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A0Mapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<ArticleDTO> toArticleDTOList(List<Article> fromList, BiFunction<Article, ArticleDTO, ArticleDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ArticleDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ArticleDTO from, Article to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(ArticleDTO from, Article to){
        mapping(from,to,false);
    }
    
    
    public static Article toEntity(ArticleDTO from){
        if(from==null) return null;
        Article to = new Article();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Article> toArticleList(List<ArticleDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A0Mapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Article> toArticleList(List<ArticleDTO> fromList, BiFunction<ArticleDTO, Article, Article> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Article to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<Article> toSpec(ArticleSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Article> toSpec(ArticleSrchDTO searchDTO, Specification<Article> sp){
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "name", searchDTO.getName());
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
