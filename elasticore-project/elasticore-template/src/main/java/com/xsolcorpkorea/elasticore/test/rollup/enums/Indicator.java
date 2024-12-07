//ecd:-810762598H20241207204629_V1.0
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


@JsonSerialize(using = Indicator.CustomSerializer.class)
@JsonDeserialize(using = Indicator.CustomDeserializer.class)
@Getter
public enum Indicator {

    YES("Y")
    ,NO("N");

    private final String code;

    Indicator(String code) {
        this.code = code;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(Indicator i:Indicator.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("code", i.getCode());
            list.add(map);
        }
        return list;
    }

    public static Indicator fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (Indicator type : Indicator.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown Indicator code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<Indicator>{
        @Override
        public void serialize(Indicator value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<Indicator>{
        @Override
        public Indicator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return Indicator.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(Indicator.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<Indicator, String>{
        @Override
        public String convertToDatabaseColumn(Indicator e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public Indicator convertToEntityAttribute(String val){
            if (val == null) return null;
            return Indicator.fromCode(val);
        }
    }

}
