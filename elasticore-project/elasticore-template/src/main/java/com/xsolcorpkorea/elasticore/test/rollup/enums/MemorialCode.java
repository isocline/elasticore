//ecd:-2073979189H20241207204629_V1.0
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


@JsonSerialize(using = MemorialCode.CustomSerializer.class)
@JsonDeserialize(using = MemorialCode.CustomDeserializer.class)
@Getter
public enum MemorialCode {

    BIRTH("BT","생일")
    ,WEDDING("WD","결홈기념일");

    private final String code;
    private final String label;

    MemorialCode(String code,String label) {
        this.code = code;
    this.label = label;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(MemorialCode i:MemorialCode.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("code", i.getCode());
            map.put("label", i.getLabel());
            list.add(map);
        }
        return list;
    }

    public static MemorialCode fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (MemorialCode type : MemorialCode.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown MemorialCode code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<MemorialCode>{
        @Override
        public void serialize(MemorialCode value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<MemorialCode>{
        @Override
        public MemorialCode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return MemorialCode.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(MemorialCode.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<MemorialCode, String>{
        @Override
        public String convertToDatabaseColumn(MemorialCode e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public MemorialCode convertToEntityAttribute(String val){
            if (val == null) return null;
            return MemorialCode.fromCode(val);
        }
    }

}
