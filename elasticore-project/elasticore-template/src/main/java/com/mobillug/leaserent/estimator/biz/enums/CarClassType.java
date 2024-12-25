//ecd:573553300H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.enums;

import lombok.Getter;


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;

/**


 */


@JsonSerialize(using = CarClassType.CustomSerializer.class)
@JsonDeserialize(using = CarClassType.CustomDeserializer.class)
@Getter
public enum CarClassType {

    TRUCK_PICKUP_VAN("TPV","화물 (트럭/픽업+밴)")
    ,SEMI_MEDIUM("SMD","준중형")
    ,LARGE_SUV("LSV","대형SUV")
    ,SPORTS("SPT","스포츠카/슈퍼카")
    ,VAN("VAN","승합 (박스카/승합/버스)")
    ,LARGE("LRG","대형")
    ,SEMI_LARGE("SLG","준대형")
    ,SMALL_SUV("SSV","소형SUV")
    ,MEDIUM("MED","중형")
    ,MEDIUM_SUV("MSV","중형SUV")
    ,SMALL("SML","소형")
    ,COMPACT("CMP","경차/경박스카")
    ,MPV("MPV","MPV");

    private final String code;
    private final String name;

    CarClassType(String code,String name) {
        this.code = code;
    this.name = name;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(CarClassType i:CarClassType.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("code", i.getCode());
            map.put("name", i.getName());
            list.add(map);
        }
        return list;
    }

    public static CarClassType fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (CarClassType type : CarClassType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown CarClassType code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<CarClassType>{
        @Override
        public void serialize(CarClassType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<CarClassType>{
        @Override
        public CarClassType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return CarClassType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(CarClassType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<CarClassType, String>{
        @Override
        public String convertToDatabaseColumn(CarClassType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public CarClassType convertToEntityAttribute(String val){
            if (val == null) return null;
            return CarClassType.fromCode(val);
        }
    }

}
