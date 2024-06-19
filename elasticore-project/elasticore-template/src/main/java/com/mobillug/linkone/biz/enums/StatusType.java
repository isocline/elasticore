//ecd:-899664530H20240618012928_V0.8
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




@JsonSerialize(using = StatusType.CustomSerializer.class)
@JsonDeserialize(using = StatusType.CustomDeserializer.class)
@Getter
public enum StatusType {

    REQUEST("요청","RQ")
    ,CONFIRM("확정","CF")
    ,DILIVERY("배송","DL")
    ,RETURN("반납","RT")
    ,CHANGE("변경요청","CG")
    ,FAIL("불가","FL");

    private final String msg;
    private final String code;

    StatusType(String msg,String code) {
        this.msg = msg;
    this.code = code;
    }

    
    public static StatusType fromCode(String code){
        for (StatusType type : StatusType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<StatusType>{
        @Override
        public void serialize(StatusType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<StatusType>{
        @Override
        public StatusType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return StatusType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && txt.length()>0)
              setValue(StatusType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<StatusType, String>{
        @Override
        public String convertToDatabaseColumn(StatusType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public StatusType convertToEntityAttribute(String val){
            if (val == null) return null;
            return StatusType.fromCode(val);
        }
    }

}
