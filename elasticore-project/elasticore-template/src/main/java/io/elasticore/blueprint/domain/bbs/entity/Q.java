//ecd:-1608808725H20250417102657_V1.0
package io.elasticore.blueprint.domain.bbs.entity;

import io.elasticore.blueprint.domain.bbs.enums.*;

import io.elasticore.springboot3.entity.*;
import org.springframework.data.jpa.domain.Specification;
import java.util.*;
import io.elasticore.blueprint.domain.bbs.enums.*;
import io.elasticore.blueprint.domain.parts.entity.*;
import io.elasticore.blueprint.domain.parts.entity.Q.*;
import io.elasticore.blueprint.domain.bbs.entity.*;
import io.elasticore.blueprint.domain.bbs.entity.Q.*;


/**
 * This class serves as a domain-specific unified metadata holder,
 * containing field names and type information for entities.
 *
 * Generated and managed by ElastiCORE.
 */

public class Q {

	public static $Board<Board> Board=new $Board<>();
	public static class $Board<T> {
	
	    private FieldInfo p=null;
	    public $Board() {}
	    public $Board(FieldInfo p) {this.p=p;}
	
	    public final FieldInfo<T> getBid() {return new FieldInfo(Board.class,"bid",Long.class, null,p);}
	    public final String bid="bid";
	    public Specification<T> bid(Op op,Object value) {return getBid().where(op,value);}
	    public Specification<T> bid(Op op,Object value,boolean chkEmpty) {return getBid().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getName() {return new FieldInfo(Board.class,"name",String.class, null,p);}
	    public final String name="name";
	    public Specification<T> name(Op op,Object value) {return getName().where(op,value);}
	    public Specification<T> name(Op op,Object value,boolean chkEmpty) {return getName().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getBoardType() {return new FieldInfo(Board.class,"boardType",BoardType.class, null,p);}
	    public final String boardType="boardType";
	    public Specification<T> boardType(Op op,Object value) {return getBoardType().where(op,value);}
	    public Specification<T> boardType(Op op,Object value,boolean chkEmpty) {return getBoardType().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getArticles() {return new FieldInfo(Board.class,"articles",Article.class, List.class,p);}
	    public final String articles="articles";
	
	    public final FieldInfo<T> getLastModifiedBy() {return new FieldInfo(Board.class,"lastModifiedBy",String.class, null,p);}
	    public final String lastModifiedBy="lastModifiedBy";
	    public Specification<T> lastModifiedBy(Op op,Object value) {return getLastModifiedBy().where(op,value);}
	    public Specification<T> lastModifiedBy(Op op,Object value,boolean chkEmpty) {return getLastModifiedBy().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getLastModifiedDate() {return new FieldInfo(Board.class,"lastModifiedDate",java.time.LocalDateTime.class, null,p);}
	    public final String lastModifiedDate="lastModifiedDate";
	    public Specification<T> lastModifiedDate(Op op,Object value) {return getLastModifiedDate().where(op,value);}
	    public Specification<T> lastModifiedDate(Op op,Object value,boolean chkEmpty) {return getLastModifiedDate().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getCreateIP() {return new FieldInfo(Board.class,"createIP",String.class, null,p);}
	    public final String createIP="createIP";
	    public Specification<T> createIP(Op op,Object value) {return getCreateIP().where(op,value);}
	    public Specification<T> createIP(Op op,Object value,boolean chkEmpty) {return getCreateIP().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getLastModifiedIP() {return new FieldInfo(Board.class,"lastModifiedIP",String.class, null,p);}
	    public final String lastModifiedIP="lastModifiedIP";
	    public Specification<T> lastModifiedIP(Op op,Object value) {return getLastModifiedIP().where(op,value);}
	    public Specification<T> lastModifiedIP(Op op,Object value,boolean chkEmpty) {return getLastModifiedIP().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getCreateDate() {return new FieldInfo(Board.class,"createDate",java.time.LocalDateTime.class, null,p);}
	    public final String createDate="createDate";
	    public Specification<T> createDate(Op op,Object value) {return getCreateDate().where(op,value);}
	    public Specification<T> createDate(Op op,Object value,boolean chkEmpty) {return getCreateDate().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getCreatedBy() {return new FieldInfo(Board.class,"createdBy",String.class, null,p);}
	    public final String createdBy="createdBy";
	    public Specification<T> createdBy(Op op,Object value) {return getCreatedBy().where(op,value);}
	    public Specification<T> createdBy(Op op,Object value,boolean chkEmpty) {return getCreatedBy().where(op,value,chkEmpty);}
	}
	
	public static $TypeInfo<TypeInfo> TypeInfo=new $TypeInfo<>();
	public static class $TypeInfo<T> {
	
	    private FieldInfo p=null;
	    public $TypeInfo() {}
	    public $TypeInfo(FieldInfo p) {this.p=p;}
	
	    public final FieldInfo<T> getTid() {return new FieldInfo(TypeInfo.class,"tid",Long.class, null,p);}
	    public final String tid="tid";
	    public Specification<T> tid(Op op,Object value) {return getTid().where(op,value);}
	    public Specification<T> tid(Op op,Object value,boolean chkEmpty) {return getTid().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getName() {return new FieldInfo(TypeInfo.class,"name",String.class, null,p);}
	    public final String name="name";
	    public Specification<T> name(Op op,Object value) {return getName().where(op,value);}
	    public Specification<T> name(Op op,Object value,boolean chkEmpty) {return getName().where(op,value,chkEmpty);}
	}
	
	public static $Article<Article> Article=new $Article<>();
	public static class $Article<T> {
	
	    private FieldInfo p=null;
	    public $Article() {}
	    public $Article(FieldInfo p) {this.p=p;}
	
	    public final FieldInfo<T> getAid() {return new FieldInfo(Article.class,"aid",String.class, null,p);}
	    public final String aid="aid";
	    public Specification<T> aid(Op op,Object value) {return getAid().where(op,value);}
	    public Specification<T> aid(Op op,Object value,boolean chkEmpty) {return getAid().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getTitle() {return new FieldInfo(Article.class,"title",String.class, null,p);}
	    public final String title="title";
	    public Specification<T> title(Op op,Object value) {return getTitle().where(op,value);}
	    public Specification<T> title(Op op,Object value,boolean chkEmpty) {return getTitle().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getContent() {return new FieldInfo(Article.class,"content",String.class, null,p);}
	    public final String content="content";
	    public Specification<T> content(Op op,Object value) {return getContent().where(op,value);}
	    public Specification<T> content(Op op,Object value,boolean chkEmpty) {return getContent().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getBoard() {return new FieldInfo(Article.class,"board",Board.class, null,p);}
	    public final String board="board";
	    public final $Board<T> board() {return new $Board(getBoard());}
	
	    public final FieldInfo<T> getTypeInfo() {return new FieldInfo(Article.class,"typeInfo",TypeInfo.class, null,p);}
	    public final String typeInfo="typeInfo";
	    public final $TypeInfo<T> typeInfo() {return new $TypeInfo(getTypeInfo());}
	
	    public final FieldInfo<T> getCarInfo() {return new FieldInfo(Article.class,"carInfo",CarInfo.class, null,p);}
	    public final String carInfo="carInfo";
	    public final $CarInfo<T> carInfo() {return new $CarInfo(getCarInfo());}
	
	    public final FieldInfo<T> getLastModifiedBy() {return new FieldInfo(Article.class,"lastModifiedBy",String.class, null,p);}
	    public final String lastModifiedBy="lastModifiedBy";
	    public Specification<T> lastModifiedBy(Op op,Object value) {return getLastModifiedBy().where(op,value);}
	    public Specification<T> lastModifiedBy(Op op,Object value,boolean chkEmpty) {return getLastModifiedBy().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getLastModifiedDate() {return new FieldInfo(Article.class,"lastModifiedDate",java.time.LocalDateTime.class, null,p);}
	    public final String lastModifiedDate="lastModifiedDate";
	    public Specification<T> lastModifiedDate(Op op,Object value) {return getLastModifiedDate().where(op,value);}
	    public Specification<T> lastModifiedDate(Op op,Object value,boolean chkEmpty) {return getLastModifiedDate().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getCreateIP() {return new FieldInfo(Article.class,"createIP",String.class, null,p);}
	    public final String createIP="createIP";
	    public Specification<T> createIP(Op op,Object value) {return getCreateIP().where(op,value);}
	    public Specification<T> createIP(Op op,Object value,boolean chkEmpty) {return getCreateIP().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getLastModifiedIP() {return new FieldInfo(Article.class,"lastModifiedIP",String.class, null,p);}
	    public final String lastModifiedIP="lastModifiedIP";
	    public Specification<T> lastModifiedIP(Op op,Object value) {return getLastModifiedIP().where(op,value);}
	    public Specification<T> lastModifiedIP(Op op,Object value,boolean chkEmpty) {return getLastModifiedIP().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getCreateDate() {return new FieldInfo(Article.class,"createDate",java.time.LocalDateTime.class, null,p);}
	    public final String createDate="createDate";
	    public Specification<T> createDate(Op op,Object value) {return getCreateDate().where(op,value);}
	    public Specification<T> createDate(Op op,Object value,boolean chkEmpty) {return getCreateDate().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getCreatedBy() {return new FieldInfo(Article.class,"createdBy",String.class, null,p);}
	    public final String createdBy="createdBy";
	    public Specification<T> createdBy(Op op,Object value) {return getCreatedBy().where(op,value);}
	    public Specification<T> createdBy(Op op,Object value,boolean chkEmpty) {return getCreatedBy().where(op,value,chkEmpty);}
	}
	
}
