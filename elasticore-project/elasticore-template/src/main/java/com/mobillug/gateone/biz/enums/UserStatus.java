//ecd:-1161222447H20240806171759_V0.8
package com.mobillug.gateone.biz.enums;

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




@JsonSerialize(using = UserStatus.CustomSerializer.class)
@JsonDeserialize(using = UserStatus.CustomDeserializer.class)
@Getter
public enum UserStatus {

    ACTIVE("정상","A")
    ,PASSWORD_FAILED("패스워드 5회 실패","F")
    ,SUSPENDED("정지","S");

    private final String msg;
    private final String code;

    UserStatus(String msg,String code) {
        this.msg = msg;
    this.code = code;
    }

    
    public static List<Map> getAllEnumInfo(){
        List list = new ArrayList();
        for(UserStatus i:UserStatus.class.getEnumConstants()){
            HashMap map = new HashMap();
            map.put("_name", i.name());
            map.put("msg", i.getMsg());
            map.put("code", i.getCode());
            list.add(map);
        }
        return list;
    }

    public static UserStatus fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (UserStatus type : UserStatus.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown UserStatus code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<UserStatus>{
        @Override
        public void serialize(UserStatus value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<UserStatus>{
        @Override
        public UserStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return UserStatus.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && txt.length()>0)
              setValue(UserStatus.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<UserStatus, String>{
        @Override
        public String convertToDatabaseColumn(UserStatus e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public UserStatus convertToEntityAttribute(String val){
            if (val == null) return null;
            return UserStatus.fromCode(val);
        }
    }

}
