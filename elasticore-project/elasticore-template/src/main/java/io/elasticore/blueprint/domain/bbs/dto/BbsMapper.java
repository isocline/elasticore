//ecd:-1046195600H20250409130647_V1.0
package io.elasticore.blueprint.domain.bbs.dto;

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
import io.elasticore.blueprint.domain.bbs.entity.*;
import io.elasticore.blueprint.domain.bbs.dto.*;
import io.elasticore.blueprint.domain.bbs.enums.*;

import io.elasticore.blueprint.domain.bbs.enums.*;
import io.elasticore.blueprint.domain.bbs.entity.*;
import io.elasticore.blueprint.domain.bbs.dto.*;
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


public class BbsMapper {

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

    
    public static void mapping(Board from, BoardDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getBoardType(), to::setBoardType, isSkipNull);
        setVal(from.getBid(), to::setBid, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        if(isSkip("Board","AuditEntity")) return;
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
        if(isSkip("Board","BaseEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(Board from, BoardDTO to){
        mapping(from,to,false);
    }
    
    
    public static BoardDTO toDTO(Board from){
        if(from==null) return null;
        BoardDTO to = new BoardDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<BoardDTO> toBoardDTOList(List<Board> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(BbsMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<BoardDTO> toBoardDTOList(List<Board> fromList, BiFunction<Board, BoardDTO, BoardDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                BoardDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(Article from, ArticleDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getBoard(), to::setBoard, isSkipNull, BbsMapper::toDTO);
        if(hasValue(from.getBoard()))
            to.setBoardBid(from.getBoard().getBid());
        setVal(from.getAid(), to::setAid, isSkipNull);
        setVal(from.getTitle(), to::setTitle, isSkipNull);
        setVal(from.getContent(), to::setContent, isSkipNull);
        if(isSkip("Article","AuditEntity")) return;
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
        if(isSkip("Article","BaseEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
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
        return fromList.stream().map(BbsMapper::toDTO).collect(Collectors.toList());
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
    
    
    public static void mapping(BoardDTO from, Board to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getBid(), to::setBid, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getBoardType(), to::setBoardType, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(BoardDTO from, Board to){
        mapping(from,to,false);
    }
    
    
    public static Board toEntity(BoardDTO from){
        if(from==null) return null;
        Board to = new Board();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Board> toBoardList(List<BoardDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(BbsMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Board> toBoardList(List<BoardDTO> fromList, BiFunction<BoardDTO, Board, Board> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Board to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ArticleDTO from, Article to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getAid(), to::setAid, isSkipNull);
        setVal(from.getTitle(), to::setTitle, isSkipNull);
        setVal(from.getContent(), to::setContent, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
        
        
        if(hasValue(from.getBoardBid())){
            Board t = new Board();
            t.setBid(from.getBoardBid());
            to.setBoard(t);
        }
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
        return fromList.stream().map(BbsMapper::toEntity).collect(Collectors.toList());
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
        List<BoardType> boardType = searchDTO.getBoardType();
        if(hasValue(boardType)){
            sp = sp.and((r,q,c) -> r.get("board").get("boardType").in(boardType));
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
        return sp;
    }
    
    
    public static Specification<Board> toSpec(BoardSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Board> toSpec(BoardSrchDTO searchDTO, Specification<Board> sp){
        BoardType boardType = searchDTO.getBoardType();
        if(hasValue(boardType)){
            sp = sp.and((r,q,c) -> c.equal(r.get("boardType"),boardType));
        }
        Long bid = searchDTO.getBid();
        if(hasValue(bid)){
            sp = sp.and((r,q,c) -> c.equal(r.get("bid"),bid));
        }
        sp=setSpec(sp, "name", searchDTO.getName());
        sp=setSpec(sp, "lastModifiedBy", searchDTO.getLastModifiedBy());
        java.time.LocalDateTime lastModifiedDateFrom = searchDTO.getLastModifiedDateFrom();
        java.time.LocalDateTime lastModifiedDateTo = searchDTO.getLastModifiedDateTo();
        if(hasValue(lastModifiedDateFrom) && hasValue(lastModifiedDateTo)){
            sp = sp.and((r,q,c) -> c.between(r.get("lastModifiedDate"),lastModifiedDateFrom,lastModifiedDateTo));
        }
        else if(hasValue(lastModifiedDateFrom)){
            sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get("lastModifiedDate"),lastModifiedDateFrom));
        }
        else if(hasValue(lastModifiedDateTo)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("lastModifiedDate"),lastModifiedDateTo));
        }
        sp=setSpec(sp, "createIP", searchDTO.getCreateIP());
        sp=setSpec(sp, "lastModifiedIP", searchDTO.getLastModifiedIP());
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
        sp=setSpec(sp, "createdBy", searchDTO.getCreatedBy());
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
