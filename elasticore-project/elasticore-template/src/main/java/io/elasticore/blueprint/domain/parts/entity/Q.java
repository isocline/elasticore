//ecd:510599727H20250416202052_V1.0
package io.elasticore.blueprint.domain.parts.entity;



import io.elasticore.springboot3.entity.*;
import org.springframework.data.jpa.domain.Specification;
import java.util.*;
import io.elasticore.blueprint.domain.parts.entity.*;
import io.elasticore.blueprint.domain.parts.entity.Q.*;


/**
 * This class serves as a domain-specific unified metadata holder,
 * containing field names and type information for entities.
 *
 * Generated and managed by ElastiCORE.
 */

public class Q {

	public static $Catalog<Catalog> Catalog=new $Catalog<>();
	public static class $Catalog<T> {
	
	    private FieldInfo p=null;
	    public $Catalog() {}
	    public $Catalog(FieldInfo p) {this.p=p;}
	
	    public final FieldInfo<T> getCatalogId() {return new FieldInfo(Catalog.class,"catalogId",String.class, null,p);}
	    public final String catalogId="catalogId";
	    public Specification<T> catalogId(Op op,Object value) {return getCatalogId().where(op,value);}
	    public Specification<T> catalogId(Op op,Object value,boolean chkEmpty) {return getCatalogId().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getName() {return new FieldInfo(Catalog.class,"name",String.class, null,p);}
	    public final String name="name";
	    public Specification<T> name(Op op,Object value) {return getName().where(op,value);}
	    public Specification<T> name(Op op,Object value,boolean chkEmpty) {return getName().where(op,value,chkEmpty);}
	}
	
	public static $CarModel<CarModel> CarModel=new $CarModel<>();
	public static class $CarModel<T> {
	
	    private FieldInfo p=null;
	    public $CarModel() {}
	    public $CarModel(FieldInfo p) {this.p=p;}
	
	    public final FieldInfo<T> getId() {return new FieldInfo(CarModel.class,"id",String.class, null,p);}
	    public final String id="id";
	    public Specification<T> id(Op op,Object value) {return getId().where(op,value);}
	    public Specification<T> id(Op op,Object value,boolean chkEmpty) {return getId().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getName() {return new FieldInfo(CarModel.class,"name",String.class, null,p);}
	    public final String name="name";
	    public Specification<T> name(Op op,Object value) {return getName().where(op,value);}
	    public Specification<T> name(Op op,Object value,boolean chkEmpty) {return getName().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getImg() {return new FieldInfo(CarModel.class,"img",String.class, null,p);}
	    public final String img="img";
	    public Specification<T> img(Op op,Object value) {return getImg().where(op,value);}
	    public Specification<T> img(Op op,Object value,boolean chkEmpty) {return getImg().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getCatalog() {return new FieldInfo(CarModel.class,"catalog",Catalog.class, null,p);}
	    public final String catalog="catalog";
	    public final $Catalog<T> catalog() {return new $Catalog(getCatalog());}
	}
	
	public static $CarInfo<CarInfo> CarInfo=new $CarInfo<>();
	public static class $CarInfo<T> {
	
	    private FieldInfo p=null;
	    public $CarInfo() {}
	    public $CarInfo(FieldInfo p) {this.p=p;}
	
	    public final FieldInfo<T> getId() {return new FieldInfo(CarInfo.class,"id",String.class, null,p);}
	    public final String id="id";
	    public Specification<T> id(Op op,Object value) {return getId().where(op,value);}
	    public Specification<T> id(Op op,Object value,boolean chkEmpty) {return getId().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getName() {return new FieldInfo(CarInfo.class,"name",String.class, null,p);}
	    public final String name="name";
	    public Specification<T> name(Op op,Object value) {return getName().where(op,value);}
	    public Specification<T> name(Op op,Object value,boolean chkEmpty) {return getName().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getDescription() {return new FieldInfo(CarInfo.class,"description",String.class, null,p);}
	    public final String description="description";
	    public Specification<T> description(Op op,Object value) {return getDescription().where(op,value);}
	    public Specification<T> description(Op op,Object value,boolean chkEmpty) {return getDescription().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getModelId() {return new FieldInfo(CarInfo.class,"modelId",String.class, null,p);}
	    public final String modelId="modelId";
	    public Specification<T> modelId(Op op,Object value) {return getModelId().where(op,value);}
	    public Specification<T> modelId(Op op,Object value,boolean chkEmpty) {return getModelId().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getModelName() {return new FieldInfo(CarInfo.class,"modelName",String.class, null,p);}
	    public final String modelName="modelName";
	    public Specification<T> modelName(Op op,Object value) {return getModelName().where(op,value);}
	    public Specification<T> modelName(Op op,Object value,boolean chkEmpty) {return getModelName().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getCriteria() {return new FieldInfo(CarInfo.class,"criteria",String.class, null,p);}
	    public final String criteria="criteria";
	    public Specification<T> criteria(Op op,Object value) {return getCriteria().where(op,value);}
	    public Specification<T> criteria(Op op,Object value,boolean chkEmpty) {return getCriteria().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getBrand() {return new FieldInfo(CarInfo.class,"brand",String.class, null,p);}
	    public final String brand="brand";
	    public Specification<T> brand(Op op,Object value) {return getBrand().where(op,value);}
	    public Specification<T> brand(Op op,Object value,boolean chkEmpty) {return getBrand().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getGroupTreeAvailables() {return new FieldInfo(CarInfo.class,"groupTreeAvailables",Boolean.class, null,p);}
	    public final String groupTreeAvailables="groupTreeAvailables";
	    public Specification<T> groupTreeAvailables(Op op,Object value) {return getGroupTreeAvailables().where(op,value);}
	    public Specification<T> groupTreeAvailables(Op op,Object value,boolean chkEmpty) {return getGroupTreeAvailables().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getParameters() {return new FieldInfo(CarInfo.class,"parameters",ParamInfo.class, List.class,p);}
	    public final String parameters="parameters";
	}
	
	public static $ParamInfo<ParamInfo> ParamInfo=new $ParamInfo<>();
	public static class $ParamInfo<T> {
	
	    private FieldInfo p=null;
	    public $ParamInfo() {}
	    public $ParamInfo(FieldInfo p) {this.p=p;}
	
	    public final FieldInfo<T> getIdx() {return new FieldInfo(ParamInfo.class,"idx",String.class, null,p);}
	    public final String idx="idx";
	    public Specification<T> idx(Op op,Object value) {return getIdx().where(op,value);}
	    public Specification<T> idx(Op op,Object value,boolean chkEmpty) {return getIdx().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getKey() {return new FieldInfo(ParamInfo.class,"key",String.class, null,p);}
	    public final String key="key";
	    public Specification<T> key(Op op,Object value) {return getKey().where(op,value);}
	    public Specification<T> key(Op op,Object value,boolean chkEmpty) {return getKey().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getName() {return new FieldInfo(ParamInfo.class,"name",String.class, null,p);}
	    public final String name="name";
	    public Specification<T> name(Op op,Object value) {return getName().where(op,value);}
	    public Specification<T> name(Op op,Object value,boolean chkEmpty) {return getName().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getValue() {return new FieldInfo(ParamInfo.class,"value",String.class, null,p);}
	    public final String value="value";
	    public Specification<T> value(Op op,Object value) {return getValue().where(op,value);}
	    public Specification<T> value(Op op,Object value,boolean chkEmpty) {return getValue().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getCarId() {return new FieldInfo(ParamInfo.class,"carId",String.class, null,p);}
	    public final String carId="carId";
	    public Specification<T> carId(Op op,Object value) {return getCarId().where(op,value);}
	    public Specification<T> carId(Op op,Object value,boolean chkEmpty) {return getCarId().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getSortOrder() {return new FieldInfo(ParamInfo.class,"sortOrder",Integer.class, null,p);}
	    public final String sortOrder="sortOrder";
	    public Specification<T> sortOrder(Op op,Object value) {return getSortOrder().where(op,value);}
	    public Specification<T> sortOrder(Op op,Object value,boolean chkEmpty) {return getSortOrder().where(op,value,chkEmpty);}
	}
	
	public static $PartGroup<PartGroup> PartGroup=new $PartGroup<>();
	public static class $PartGroup<T> {
	
	    private FieldInfo p=null;
	    public $PartGroup() {}
	    public $PartGroup(FieldInfo p) {this.p=p;}
	
	    public final FieldInfo<T> getId() {return new FieldInfo(PartGroup.class,"id",String.class, null,p);}
	    public final String id="id";
	    public Specification<T> id(Op op,Object value) {return getId().where(op,value);}
	    public Specification<T> id(Op op,Object value,boolean chkEmpty) {return getId().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getName() {return new FieldInfo(PartGroup.class,"name",String.class, null,p);}
	    public final String name="name";
	    public Specification<T> name(Op op,Object value) {return getName().where(op,value);}
	    public Specification<T> name(Op op,Object value,boolean chkEmpty) {return getName().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getHasSubgroups() {return new FieldInfo(PartGroup.class,"hasSubgroups",Boolean.class, null,p);}
	    public final String hasSubgroups="hasSubgroups";
	    public Specification<T> hasSubgroups(Op op,Object value) {return getHasSubgroups().where(op,value);}
	    public Specification<T> hasSubgroups(Op op,Object value,boolean chkEmpty) {return getHasSubgroups().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getImg() {return new FieldInfo(PartGroup.class,"img",String.class, null,p);}
	    public final String img="img";
	    public Specification<T> img(Op op,Object value) {return getImg().where(op,value);}
	    public Specification<T> img(Op op,Object value,boolean chkEmpty) {return getImg().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getDescription() {return new FieldInfo(PartGroup.class,"description",String.class, null,p);}
	    public final String description="description";
	    public Specification<T> description(Op op,Object value) {return getDescription().where(op,value);}
	    public Specification<T> description(Op op,Object value,boolean chkEmpty) {return getDescription().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getParentId() {return new FieldInfo(PartGroup.class,"parentId",String.class, null,p);}
	    public final String parentId="parentId";
	    public Specification<T> parentId(Op op,Object value) {return getParentId().where(op,value);}
	    public Specification<T> parentId(Op op,Object value,boolean chkEmpty) {return getParentId().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getCarId() {return new FieldInfo(PartGroup.class,"carId",String.class, null,p);}
	    public final String carId="carId";
	    public Specification<T> carId(Op op,Object value) {return getCarId().where(op,value);}
	    public Specification<T> carId(Op op,Object value,boolean chkEmpty) {return getCarId().where(op,value,chkEmpty);}
	}
	
	public static $CarProfile<CarProfile> CarProfile=new $CarProfile<>();
	public static class $CarProfile<T> {
	
	    private FieldInfo p=null;
	    public $CarProfile() {}
	    public $CarProfile(FieldInfo p) {this.p=p;}
	
	    public final FieldInfo<T> getVin() {return new FieldInfo(CarProfile.class,"vin",String.class, null,p);}
	    public final String vin="vin";
	    public Specification<T> vin(Op op,Object value) {return getVin().where(op,value);}
	    public Specification<T> vin(Op op,Object value,boolean chkEmpty) {return getVin().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getFrame() {return new FieldInfo(CarProfile.class,"frame",String.class, null,p);}
	    public final String frame="frame";
	    public Specification<T> frame(Op op,Object value) {return getFrame().where(op,value);}
	    public Specification<T> frame(Op op,Object value,boolean chkEmpty) {return getFrame().where(op,value,chkEmpty);}
	
	    public final FieldInfo<T> getCarInfo() {return new FieldInfo(CarProfile.class,"carInfo",CarInfo.class, null,p);}
	    public final String carInfo="carInfo";
	    public final $CarInfo<T> carInfo() {return new $CarInfo(getCarInfo());}
	}
	
}
