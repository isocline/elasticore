//ecd:1943849722H20240618012928_V0.8
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




@JsonSerialize(using = CompanyGroupCode.CustomSerializer.class)
@JsonDeserialize(using = CompanyGroupCode.CustomDeserializer.class)
@Getter
public enum CompanyGroupCode {

    CALLCENTER("콜센터","CC")
    ,GARAGE("공업사","GR")
    ,RENTCAR("렌트카","RC")
    ,HEAD("본사","HD");

    private final String msg;
    private final String code;

    CompanyGroupCode(String msg,String code) {
        this.msg = msg;
    this.code = code;
    }

    
    public static CompanyGroupCode fromCode(String code){
        for (CompanyGroupCode type : CompanyGroupCode.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<CompanyGroupCode>{
        @Override
        public void serialize(CompanyGroupCode value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<CompanyGroupCode>{
        @Override
        public CompanyGroupCode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return CompanyGroupCode.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && txt.length()>0)
              setValue(CompanyGroupCode.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<CompanyGroupCode, String>{
        @Override
        public String convertToDatabaseColumn(CompanyGroupCode e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public CompanyGroupCode convertToEntityAttribute(String val){
            if (val == null) return null;
            return CompanyGroupCode.fromCode(val);
        }
    }

}
