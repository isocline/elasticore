//ecd:1151548306H20241207204629_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.enums;

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
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

/**


 */


@JsonSerialize(using = GenderCode.CustomSerializer.class)
@JsonDeserialize(using = GenderCode.CustomDeserializer.class)
@Getter
public enum GenderCode {

    MALE("1","남")
    ,FEMALE("2","여");

    private final String code;
    private final String label;

    GenderCode(String code,String label) {
        this.code = code;
    this.label = label;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(GenderCode i:GenderCode.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("code", i.getCode());
            map.put("label", i.getLabel());
            list.add(map);
        }
        return list;
    }

    public static GenderCode fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (GenderCode type : GenderCode.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown GenderCode code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<GenderCode>{
        @Override
        public void serialize(GenderCode value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<GenderCode>{
        @Override
        public GenderCode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return GenderCode.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(GenderCode.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<GenderCode, String>{
        @Override
        public String convertToDatabaseColumn(GenderCode e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public GenderCode convertToEntityAttribute(String val){
            if (val == null) return null;
            return GenderCode.fromCode(val);
        }
    }

}
