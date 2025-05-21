//ecd:601116544H20250521114808_V1.0
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
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(Board from, BoardDTO to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("boardType").checkEnable())
        setVal(from.getBoardType(), to::setBoardType, isSkipNull);
        if(c==null || c.fd("articles").checkEnable())
        setVal(from.getArticles(), to::setArticles, isSkipNull, e->toArticleDTOList(e,c));
        if(c==null || c.fd("bid").checkEnable())
        setVal(from.getBid(), to::setBid, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
        if(c==null || c.fd("boardTypeList").checkEnable())
        setVal(from.getBoardTypeList(), to::setBoardTypeList, isSkipNull);
        if(isSkip("Board","AuditEntity")) return;
        if(c==null || c.fd("lastModifiedBy").checkEnable())
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        if(c==null || c.fd("lastModifiedDate").checkEnable())
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        if(c==null || c.fd("createIP").checkEnable())
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        if(c==null || c.fd("lastModifiedIP").checkEnable())
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
        if(isSkip("Board","BaseEntity")) return;
        if(c==null || c.fd("createDate").checkEnable())
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        if(c==null || c.fd("createdBy").checkEnable())
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(Board from, BoardDTO to){
        mapping(from,to,false);
    }
    public static void mapping(Board from, BoardDTO to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static BoardDTO toDTO(Board from){
        return toDTO(from,MappingContext.withGuard(2,null));
    }
    public static BoardDTO toDTO(Board from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        BoardDTO to = new BoardDTO();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<BoardDTO> toBoardDTOList(List<Board> fromList){
        return toBoardDTOList(fromList,(MappingContext) null);
    }
    public static List<BoardDTO> toBoardDTOList(List<Board> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->BbsMapper.toDTO(e,c)).collect(Collectors.toList());
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
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(Article from, ArticleDTO to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(hasValue(from.getBoard()))
            to.setBoardTestId(from.getBoard().getName());
        if(c==null || c.fd("aid").checkEnable())
        setVal(from.getAid(), to::setAid, isSkipNull);
        if(c==null || c.fd("title").checkEnable())
        setVal(from.getTitle(), to::setTitle, isSkipNull);
        if(c==null || c.fd("content").checkEnable())
        setVal(from.getContent(), to::setContent, isSkipNull);
        if(isSkip("Article","AuditEntity")) return;
        if(c==null || c.fd("lastModifiedBy").checkEnable())
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        if(c==null || c.fd("lastModifiedDate").checkEnable())
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        if(c==null || c.fd("createIP").checkEnable())
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        if(c==null || c.fd("lastModifiedIP").checkEnable())
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
        if(isSkip("Article","BaseEntity")) return;
        if(c==null || c.fd("createdBy").checkEnable())
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(Article from, ArticleDTO to){
        mapping(from,to,false);
    }
    public static void mapping(Article from, ArticleDTO to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static ArticleDTO toDTO(Article from){
        return toDTO(from,MappingContext.withGuard(2,null));
    }
    public static ArticleDTO toDTO(Article from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        ArticleDTO to = new ArticleDTO();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<ArticleDTO> toArticleDTOList(List<Article> fromList){
        return toArticleDTOList(fromList,(MappingContext) null);
    }
    public static List<ArticleDTO> toArticleDTOList(List<Article> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->BbsMapper.toDTO(e,c)).collect(Collectors.toList());
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
    
    
    public static void mapping(ArticlePart from, ArticlePartDTO to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(ArticlePart from, ArticlePartDTO to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("article").checkEnable())
        setVal(from.getArticle(), to::setArticle, isSkipNull, e->toDTO(e,c));
        if(c==null || c.fd("partnerArticle").checkEnable())
        setVal(from.getPartnerArticle(), to::setPartnerArticle, isSkipNull, e->toDTO(e,c));
        if(hasValue(from.getArticle()))
            to.setArticleAid(from.getArticle().getAid());
        if(hasValue(from.getPartnerArticle()))
            to.setPartnerArticleAid(from.getPartnerArticle().getAid());
    }
    
    
    public static void mapping(ArticlePart from, ArticlePartDTO to){
        mapping(from,to,false);
    }
    public static void mapping(ArticlePart from, ArticlePartDTO to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static ArticlePartDTO toDTO(ArticlePart from){
        return toDTO(from,MappingContext.withGuard(2,null));
    }
    public static ArticlePartDTO toDTO(ArticlePart from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        ArticlePartDTO to = new ArticlePartDTO();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<ArticlePartDTO> toArticlePartDTOList(List<ArticlePart> fromList){
        return toArticlePartDTOList(fromList,(MappingContext) null);
    }
    public static List<ArticlePartDTO> toArticlePartDTOList(List<ArticlePart> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->BbsMapper.toDTO(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<ArticlePartDTO> toArticlePartDTOList(List<ArticlePart> fromList, BiFunction<ArticlePart, ArticlePartDTO, ArticlePartDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ArticlePartDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ArticleDTO from, Article to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(ArticleDTO from, Article to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("aid").checkEnable())
        setVal(from.getAid(), to::setAid, isSkipNull);
        if(c==null || c.fd("title").checkEnable())
        setVal(from.getTitle(), to::setTitle, isSkipNull);
        if(c==null || c.fd("content").checkEnable())
        setVal(from.getContent(), to::setContent, isSkipNull);
        if(c==null || c.fd("lastModifiedBy").checkEnable())
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        if(c==null || c.fd("lastModifiedDate").checkEnable())
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        if(c==null || c.fd("createIP").checkEnable())
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        if(c==null || c.fd("lastModifiedIP").checkEnable())
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
        if(c==null || c.fd("createdBy").checkEnable())
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(ArticleDTO from, Article to){
        mapping(from,to,false);
    }
    public static void mapping(ArticleDTO from, Article to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static Article toEntity(ArticleDTO from){
        return toEntity(from,MappingContext.withGuard(2,null));
    }
    public static Article toEntity(ArticleDTO from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        Article to = new Article();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<Article> toArticleList(List<ArticleDTO> fromList){
        return toArticleList(fromList,(MappingContext) null);
    }
    public static List<Article> toArticleList(List<ArticleDTO> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->BbsMapper.toEntity(e,c)).collect(Collectors.toList());
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
    
    
    public static void mapping(BoardDTO from, Board to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(BoardDTO from, Board to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(c==null || c.fd("bid").checkEnable())
        setVal(from.getBid(), to::setBid, isSkipNull);
        if(c==null || c.fd("name").checkEnable())
        setVal(from.getName(), to::setName, isSkipNull);
        if(c==null || c.fd("boardType").checkEnable())
        setVal(from.getBoardType(), to::setBoardType, isSkipNull);
        if(c==null || c.fd("boardTypeList").checkEnable())
        setVal(from.getBoardTypeList(), to::setBoardTypeList, isSkipNull);
        if(c==null || c.fd("lastModifiedBy").checkEnable())
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        if(c==null || c.fd("lastModifiedDate").checkEnable())
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        if(c==null || c.fd("createIP").checkEnable())
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        if(c==null || c.fd("lastModifiedIP").checkEnable())
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
        if(c==null || c.fd("createDate").checkEnable())
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        if(c==null || c.fd("createdBy").checkEnable())
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(BoardDTO from, Board to){
        mapping(from,to,false);
    }
    public static void mapping(BoardDTO from, Board to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static Board toEntity(BoardDTO from){
        return toEntity(from,MappingContext.withGuard(2,null));
    }
    public static Board toEntity(BoardDTO from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        Board to = new Board();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<Board> toBoardList(List<BoardDTO> fromList){
        return toBoardList(fromList,(MappingContext) null);
    }
    public static List<Board> toBoardList(List<BoardDTO> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->BbsMapper.toEntity(e,c)).collect(Collectors.toList());
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
    
    
    public static void mapping(ArticlePartDTO from, ArticlePart to, boolean isSkipNull){
        mapping(from,to,isSkipNull,null);
    }
    public static void mapping(ArticlePartDTO from, ArticlePart to, boolean isSkipNull, MappingContext c){
        if(c!=null && !c.checkEnable()) return;
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        
        
        if(hasValue(from.getArticleAid())){
            Article t = new Article();
            t.setAid(from.getArticleAid());
            to.setArticle(t);
        }
        
        
        if(hasValue(from.getPartnerArticleAid())){
            Article t = new Article();
            t.setAid(from.getPartnerArticleAid());
            to.setPartnerArticle(t);
        }
    }
    
    
    public static void mapping(ArticlePartDTO from, ArticlePart to){
        mapping(from,to,false);
    }
    public static void mapping(ArticlePartDTO from, ArticlePart to, MappingContext c){
        mapping(from,to,false,c);
    }
    
    
    public static ArticlePart toEntity(ArticlePartDTO from){
        return toEntity(from,MappingContext.withGuard(2,null));
    }
    public static ArticlePart toEntity(ArticlePartDTO from,MappingContext c1){
        MappingContext c=c1!=null?c1.getChild():null;
        if(c!=null && !c.checkEnable()) return null;
        if(from==null) return null;
        ArticlePart to = new ArticlePart();
        mapping(from,to,c);
        return to;
    }
    
    
    public static List<ArticlePart> toArticlePartList(List<ArticlePartDTO> fromList){
        return toArticlePartList(fromList,(MappingContext) null);
    }
    public static List<ArticlePart> toArticlePartList(List<ArticlePartDTO> fromList, MappingContext c){
        if(c!=null && !c.checkEnable(1)) return null;
        if(fromList==null) return null;
        return fromList.stream().map(e->BbsMapper.toEntity(e,c)).collect(Collectors.toList());
    }
    
    
    public static List<ArticlePart> toArticlePartList(List<ArticlePartDTO> fromList, BiFunction<ArticlePartDTO, ArticlePart, ArticlePart> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ArticlePart to = toEntity(from);
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
    
    
    public static Specification<ArticlePart> toSpec(ArticlePartSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<ArticlePart> toSpec(ArticlePartSrchDTO searchDTO, Specification<ArticlePart> sp){
        String articleAid = searchDTO.getArticleAid();
        if(hasValue(articleAid)){
            sp = sp.and((r,q,c) -> c.equal(r.get("article").get("aid"),articleAid));
        }
        String partnerArticleAid = searchDTO.getPartnerArticleAid();
        if(hasValue(partnerArticleAid)){
            sp = sp.and((r,q,c) -> c.equal(r.get("partnerArticle").get("aid"),partnerArticleAid));
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
