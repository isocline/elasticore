//ecd:1009135823H20241223210702_V1.0
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


@JsonSerialize(using = RecommendType.CustomSerializer.class)
@JsonDeserialize(using = RecommendType.CustomDeserializer.class)
@Getter
public enum RecommendType {

    RECOMMEND("RM","추천차량")
    ,IN_STOCK("IS","즉시출고");

    private final String code;
    private final String name;

    RecommendType(String code,String name) {
        this.code = code;
    this.name = name;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(RecommendType i:RecommendType.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("code", i.getCode());
            map.put("name", i.getName());
            list.add(map);
        }
        return list;
    }

    public static RecommendType fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (RecommendType type : RecommendType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown RecommendType code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<RecommendType>{
        @Override
        public void serialize(RecommendType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<RecommendType>{
        @Override
        public RecommendType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return RecommendType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(RecommendType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<RecommendType, String>{
        @Override
        public String convertToDatabaseColumn(RecommendType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public RecommendType convertToEntityAttribute(String val){
            if (val == null) return null;
            return RecommendType.fromCode(val);
        }
    }

}
