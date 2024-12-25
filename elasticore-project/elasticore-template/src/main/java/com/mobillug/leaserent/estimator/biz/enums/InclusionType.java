//ecd:500064095H20241223210702_V1.0
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


@JsonSerialize(using = InclusionType.CustomSerializer.class)
@JsonDeserialize(using = InclusionType.CustomDeserializer.class)
@Getter
public enum InclusionType {

    INCLUDED("포함","Y")
    ,NOT_INCLUDED("미포함","N");

    private final String msg;
    private final String code;

    InclusionType(String msg,String code) {
        this.msg = msg;
    this.code = code;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(InclusionType i:InclusionType.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("msg", i.getMsg());
            map.put("code", i.getCode());
            list.add(map);
        }
        return list;
    }

    public static InclusionType fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (InclusionType type : InclusionType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown InclusionType code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<InclusionType>{
        @Override
        public void serialize(InclusionType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<InclusionType>{
        @Override
        public InclusionType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return InclusionType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(InclusionType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<InclusionType, String>{
        @Override
        public String convertToDatabaseColumn(InclusionType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public InclusionType convertToEntityAttribute(String val){
            if (val == null) return null;
            return InclusionType.fromCode(val);
        }
    }

}
