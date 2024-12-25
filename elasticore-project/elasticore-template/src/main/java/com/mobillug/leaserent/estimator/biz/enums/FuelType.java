//ecd:1013491718H20241223210702_V1.0
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


@JsonSerialize(using = FuelType.CustomSerializer.class)
@JsonDeserialize(using = FuelType.CustomDeserializer.class)
@Getter
public enum FuelType {

    HYBRID("HY","하이브리드")
    ,ELECTRIC("EL","전기")
    ,HYDROGEN("HD","수소")
    ,GASOLINE("GA","가솔린")
    ,LPG("LP","LPG")
    ,DIESEL("DS","디젤");

    private final String code;
    private final String name;

    FuelType(String code,String name) {
        this.code = code;
    this.name = name;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(FuelType i:FuelType.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("code", i.getCode());
            map.put("name", i.getName());
            list.add(map);
        }
        return list;
    }

    public static FuelType fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (FuelType type : FuelType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown FuelType code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<FuelType>{
        @Override
        public void serialize(FuelType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<FuelType>{
        @Override
        public FuelType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return FuelType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(FuelType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<FuelType, String>{
        @Override
        public String convertToDatabaseColumn(FuelType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public FuelType convertToEntityAttribute(String val){
            if (val == null) return null;
            return FuelType.fromCode(val);
        }
    }

}
