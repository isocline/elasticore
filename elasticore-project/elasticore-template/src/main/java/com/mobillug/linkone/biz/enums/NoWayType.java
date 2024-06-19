//ecd:810104839H20240618012928_V0.8
package com.mobillug.linkone.biz.enums;

import lombok.Getter;




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




@JsonSerialize(using = NoWayType.CustomSerializer.class)
@JsonDeserialize(using = NoWayType.CustomDeserializer.class)
@Getter
public enum NoWayType {

    REPAIR("수리만진행","R")
    ,CHG("지역변경","C")
    ,ETC("기타","E");

    private final String msg;
    private final String code;

    NoWayType(String msg,String code) {
        this.msg = msg;
    this.code = code;
    }

    
    public static NoWayType fromCode(String code){
        for (NoWayType type : NoWayType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<NoWayType>{
        @Override
        public void serialize(NoWayType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<NoWayType>{
        @Override
        public NoWayType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return NoWayType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && txt.length()>0)
              setValue(NoWayType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<NoWayType, String>{
        @Override
        public String convertToDatabaseColumn(NoWayType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public NoWayType convertToEntityAttribute(String val){
            if (val == null) return null;
            return NoWayType.fromCode(val);
        }
    }

}
