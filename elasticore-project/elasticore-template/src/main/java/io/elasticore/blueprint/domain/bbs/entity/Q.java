//ecd:-1255714431H20250409143835_V1.0
package io.elasticore.blueprint.domain.bbs.entity;

import io.elasticore.blueprint.domain.bbs.enums.*;

import io.elasticore.springboot3.entity.*;
import org.springframework.data.jpa.domain.Specification;
import java.util.*;
import io.elasticore.blueprint.domain.bbs.enums.*;
import io.elasticore.blueprint.domain.bbs.entity.*;


/**
 * This class serves as a domain-specific unified metadata holder,
 * containing field names and type information for entities.
 *
 * Generated and managed by ElastiCORE.
 */

public class Q {

	public static QxBoard Board=new QxBoard();
	public static class QxBoard {
	
	    private FieldInfo p=null;
	    public QxBoard() {}
	    public QxBoard(FieldInfo p) {this.p=p;}
	
	    public final FieldInfo getBid() {return new FieldInfo<Board>(Board.class,"bid",Long.class, null,p);}
	    public final String bid="bid";
	    public Specification<Board> bid(Op op,Object value) {return getBid().where(op,value);}
	    public Specification<Board> bid(Op op,Object value,boolean chkEmpty) {return getBid().where(op,value,chkEmpty);}
	
	    public final FieldInfo getName() {return new FieldInfo<Board>(Board.class,"name",String.class, null,p);}
	    public final String name="name";
	    public Specification<Board> name(Op op,Object value) {return getName().where(op,value);}
	    public Specification<Board> name(Op op,Object value,boolean chkEmpty) {return getName().where(op,value,chkEmpty);}
	
	    public final FieldInfo getBoardType() {return new FieldInfo<Board>(Board.class,"boardType",BoardType.class, null,p);}
	    public final String boardType="boardType";
	    public Specification<Board> boardType(Op op,Object value) {return getBoardType().where(op,value);}
	    public Specification<Board> boardType(Op op,Object value,boolean chkEmpty) {return getBoardType().where(op,value,chkEmpty);}
	
	    public final FieldInfo getArticles() {return new FieldInfo<Board>(Board.class,"articles",Article.class, List.class,p);}
	    public final String articles="articles";
	
	    public final FieldInfo getLastModifiedBy() {return new FieldInfo<Board>(Board.class,"lastModifiedBy",String.class, null,p);}
	    public final String lastModifiedBy="lastModifiedBy";
	    public Specification<Board> lastModifiedBy(Op op,Object value) {return getLastModifiedBy().where(op,value);}
	    public Specification<Board> lastModifiedBy(Op op,Object value,boolean chkEmpty) {return getLastModifiedBy().where(op,value,chkEmpty);}
	
	    public final FieldInfo getLastModifiedDate() {return new FieldInfo<Board>(Board.class,"lastModifiedDate",java.time.LocalDateTime.class, null,p);}
	    public final String lastModifiedDate="lastModifiedDate";
	    public Specification<Board> lastModifiedDate(Op op,Object value) {return getLastModifiedDate().where(op,value);}
	    public Specification<Board> lastModifiedDate(Op op,Object value,boolean chkEmpty) {return getLastModifiedDate().where(op,value,chkEmpty);}
	
	    public final FieldInfo getCreateIP() {return new FieldInfo<Board>(Board.class,"createIP",String.class, null,p);}
	    public final String createIP="createIP";
	    public Specification<Board> createIP(Op op,Object value) {return getCreateIP().where(op,value);}
	    public Specification<Board> createIP(Op op,Object value,boolean chkEmpty) {return getCreateIP().where(op,value,chkEmpty);}
	
	    public final FieldInfo getLastModifiedIP() {return new FieldInfo<Board>(Board.class,"lastModifiedIP",String.class, null,p);}
	    public final String lastModifiedIP="lastModifiedIP";
	    public Specification<Board> lastModifiedIP(Op op,Object value) {return getLastModifiedIP().where(op,value);}
	    public Specification<Board> lastModifiedIP(Op op,Object value,boolean chkEmpty) {return getLastModifiedIP().where(op,value,chkEmpty);}
	
	    public final FieldInfo getCreateDate() {return new FieldInfo<Board>(Board.class,"createDate",java.time.LocalDateTime.class, null,p);}
	    public final String createDate="createDate";
	    public Specification<Board> createDate(Op op,Object value) {return getCreateDate().where(op,value);}
	    public Specification<Board> createDate(Op op,Object value,boolean chkEmpty) {return getCreateDate().where(op,value,chkEmpty);}
	
	    public final FieldInfo getCreatedBy() {return new FieldInfo<Board>(Board.class,"createdBy",String.class, null,p);}
	    public final String createdBy="createdBy";
	    public Specification<Board> createdBy(Op op,Object value) {return getCreatedBy().where(op,value);}
	    public Specification<Board> createdBy(Op op,Object value,boolean chkEmpty) {return getCreatedBy().where(op,value,chkEmpty);}
	}
	
	public static QxArticle Article=new QxArticle();
	public static class QxArticle {
	
	    private FieldInfo p=null;
	    public QxArticle() {}
	    public QxArticle(FieldInfo p) {this.p=p;}
	
	    public final FieldInfo getAid() {return new FieldInfo<Article>(Article.class,"aid",String.class, null,p);}
	    public final String aid="aid";
	    public Specification<Article> aid(Op op,Object value) {return getAid().where(op,value);}
	    public Specification<Article> aid(Op op,Object value,boolean chkEmpty) {return getAid().where(op,value,chkEmpty);}
	
	    public final FieldInfo getTitle() {return new FieldInfo<Article>(Article.class,"title",String.class, null,p);}
	    public final String title="title";
	    public Specification<Article> title(Op op,Object value) {return getTitle().where(op,value);}
	    public Specification<Article> title(Op op,Object value,boolean chkEmpty) {return getTitle().where(op,value,chkEmpty);}
	
	    public final FieldInfo getContent() {return new FieldInfo<Article>(Article.class,"content",String.class, null,p);}
	    public final String content="content";
	    public Specification<Article> content(Op op,Object value) {return getContent().where(op,value);}
	    public Specification<Article> content(Op op,Object value,boolean chkEmpty) {return getContent().where(op,value,chkEmpty);}
	
	    public final FieldInfo getBoard() {return new FieldInfo<Article>(Article.class,"board",Board.class, null,p);}
	    public final String board="board";
	    public final QxBoard board() {return new QxBoard(getBoard());}
	
	    public final FieldInfo getLastModifiedBy() {return new FieldInfo<Article>(Article.class,"lastModifiedBy",String.class, null,p);}
	    public final String lastModifiedBy="lastModifiedBy";
	    public Specification<Article> lastModifiedBy(Op op,Object value) {return getLastModifiedBy().where(op,value);}
	    public Specification<Article> lastModifiedBy(Op op,Object value,boolean chkEmpty) {return getLastModifiedBy().where(op,value,chkEmpty);}
	
	    public final FieldInfo getLastModifiedDate() {return new FieldInfo<Article>(Article.class,"lastModifiedDate",java.time.LocalDateTime.class, null,p);}
	    public final String lastModifiedDate="lastModifiedDate";
	    public Specification<Article> lastModifiedDate(Op op,Object value) {return getLastModifiedDate().where(op,value);}
	    public Specification<Article> lastModifiedDate(Op op,Object value,boolean chkEmpty) {return getLastModifiedDate().where(op,value,chkEmpty);}
	
	    public final FieldInfo getCreateIP() {return new FieldInfo<Article>(Article.class,"createIP",String.class, null,p);}
	    public final String createIP="createIP";
	    public Specification<Article> createIP(Op op,Object value) {return getCreateIP().where(op,value);}
	    public Specification<Article> createIP(Op op,Object value,boolean chkEmpty) {return getCreateIP().where(op,value,chkEmpty);}
	
	    public final FieldInfo getLastModifiedIP() {return new FieldInfo<Article>(Article.class,"lastModifiedIP",String.class, null,p);}
	    public final String lastModifiedIP="lastModifiedIP";
	    public Specification<Article> lastModifiedIP(Op op,Object value) {return getLastModifiedIP().where(op,value);}
	    public Specification<Article> lastModifiedIP(Op op,Object value,boolean chkEmpty) {return getLastModifiedIP().where(op,value,chkEmpty);}
	
	    public final FieldInfo getCreateDate() {return new FieldInfo<Article>(Article.class,"createDate",java.time.LocalDateTime.class, null,p);}
	    public final String createDate="createDate";
	    public Specification<Article> createDate(Op op,Object value) {return getCreateDate().where(op,value);}
	    public Specification<Article> createDate(Op op,Object value,boolean chkEmpty) {return getCreateDate().where(op,value,chkEmpty);}
	
	    public final FieldInfo getCreatedBy() {return new FieldInfo<Article>(Article.class,"createdBy",String.class, null,p);}
	    public final String createdBy="createdBy";
	    public Specification<Article> createdBy(Op op,Object value) {return getCreatedBy().where(op,value);}
	    public Specification<Article> createdBy(Op op,Object value,boolean chkEmpty) {return getCreatedBy().where(op,value,chkEmpty);}
	}
	
}
