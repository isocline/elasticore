//ecd:1984547323H20240618012928_V0.8
package com.mobillug.gateone.biz.enums;

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
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;

/**


 */




@JsonSerialize(using = UserRole.CustomSerializer.class)
@JsonDeserialize(using = UserRole.CustomDeserializer.class)
@Getter
public enum UserRole {

    ADMIN("관리자","A")
    ,RENTAL_MEMBER("렌트카회원","M")
    ,GENERAL_USER("일반 사용자","C");

    private final String msg;
    private final String code;

    UserRole(String msg,String code) {
        this.msg = msg;
    this.code = code;
    }

    
    public static UserRole fromCode(String code){
        for (UserRole type : UserRole.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<UserRole>{
        @Override
        public void serialize(UserRole value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<UserRole>{
        @Override
        public UserRole deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return UserRole.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && txt.length()>0)
              setValue(UserRole.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<UserRole, String>{
        @Override
        public String convertToDatabaseColumn(UserRole e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public UserRole convertToEntityAttribute(String val){
            if (val == null) return null;
            return UserRole.fromCode(val);
        }
    }

}
