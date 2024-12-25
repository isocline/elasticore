//ecd:1181589981H20241223210702_V1.0
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


@JsonSerialize(using = InclusionTexType.CustomSerializer.class)
@JsonDeserialize(using = InclusionTexType.CustomDeserializer.class)
@Getter
public enum InclusionTexType {

    INCLUDED("포함","Y")
    ,EXCLUDED("제외","N");

    private final String msg;
    private final String code;

    InclusionTexType(String msg,String code) {
        this.msg = msg;
    this.code = code;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(InclusionTexType i:InclusionTexType.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("msg", i.getMsg());
            map.put("code", i.getCode());
            list.add(map);
        }
        return list;
    }

    public static InclusionTexType fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (InclusionTexType type : InclusionTexType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown InclusionTexType code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<InclusionTexType>{
        @Override
        public void serialize(InclusionTexType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<InclusionTexType>{
        @Override
        public InclusionTexType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return InclusionTexType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(InclusionTexType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<InclusionTexType, String>{
        @Override
        public String convertToDatabaseColumn(InclusionTexType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public InclusionTexType convertToEntityAttribute(String val){
            if (val == null) return null;
            return InclusionTexType.fromCode(val);
        }
    }

}
