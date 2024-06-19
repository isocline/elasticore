//ecd:-1087961395H20240618012928_V0.8
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




@JsonSerialize(using = RentCarProcessType.CustomSerializer.class)
@JsonDeserialize(using = RentCarProcessType.CustomDeserializer.class)
@Getter
public enum RentCarProcessType {

    REQUEST("요청","RQ")
    ,DILIVERY("배송","DL")
    ,CALL("통화","CL")
    ,FAIL("불가","FL")
    ,ETC("기타","ET")
    ,RETURN("반납확인","RT");

    private final String msg;
    private final String code;

    RentCarProcessType(String msg,String code) {
        this.msg = msg;
    this.code = code;
    }

    
    public static RentCarProcessType fromCode(String code){
        for (RentCarProcessType type : RentCarProcessType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<RentCarProcessType>{
        @Override
        public void serialize(RentCarProcessType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<RentCarProcessType>{
        @Override
        public RentCarProcessType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return RentCarProcessType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && txt.length()>0)
              setValue(RentCarProcessType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<RentCarProcessType, String>{
        @Override
        public String convertToDatabaseColumn(RentCarProcessType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public RentCarProcessType convertToEntityAttribute(String val){
            if (val == null) return null;
            return RentCarProcessType.fromCode(val);
        }
    }

}
