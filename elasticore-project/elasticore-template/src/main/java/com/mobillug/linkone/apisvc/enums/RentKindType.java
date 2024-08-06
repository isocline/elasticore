//ecd:-202452984H20240805175914_V0.8
package com.mobillug.linkone.apisvc.enums;

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




@JsonSerialize(using = RentKindType.CustomSerializer.class)
@JsonDeserialize(using = RentKindType.CustomDeserializer.class)
@Getter
public enum RentKindType {

    ACCIDENT("사고","01")
    ,REPAIR("정비","02")
    ,ETC("기타","09");

    private final String msg;
    private final String code;

    RentKindType(String msg,String code) {
        this.msg = msg;
    this.code = code;
    }

    
    public static List<Map> getAllEnumInfo(){
        List list = new ArrayList();
        for(RentKindType i:RentKindType.class.getEnumConstants()){
            HashMap map = new HashMap();
            map.put("_name", i.name());
            map.put("msg", i.getMsg());
            map.put("code", i.getCode());
            list.add(map);
        }
        return list;
    }

    public static RentKindType fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (RentKindType type : RentKindType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown RentKindType code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<RentKindType>{
        @Override
        public void serialize(RentKindType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<RentKindType>{
        @Override
        public RentKindType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return RentKindType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && txt.length()>0)
              setValue(RentKindType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<RentKindType, String>{
        @Override
        public String convertToDatabaseColumn(RentKindType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public RentKindType convertToEntityAttribute(String val){
            if (val == null) return null;
            return RentKindType.fromCode(val);
        }
    }

}
