//ecd:99820713H20250405141628_V1.0
package com.xyrokorea.xyroplug.domain.channel.enums;

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
 * MessageType
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */


@JsonSerialize(using = MessageType.CustomSerializer.class)
@JsonDeserialize(using = MessageType.CustomDeserializer.class)
@Getter
public enum MessageType {

    SMS("SMS","단문문자")
    ,LMS("LMS","장문문자")
    ,CALL("CALL","전화발신")
    ,KAKAO("KAKAO","카카오톡")
    ,OUTBOUND_CALL("OBC","전화발신")
    ,FAX("FAX","팩스");

    private final String code;
    private final String name;

    MessageType(String code,String name) {
        this.code = code;
    this.name = name;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(MessageType i:MessageType.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("code", i.getCode());
            map.put("name", i.getName());
            list.add(map);
        }
        return list;
    }

    public static MessageType fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (MessageType type : MessageType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown MessageType code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<MessageType>{
        @Override
        public void serialize(MessageType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<MessageType>{
        @Override
        public MessageType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return MessageType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(MessageType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<MessageType, String>{
        @Override
        public String convertToDatabaseColumn(MessageType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public MessageType convertToEntityAttribute(String val){
            if (val == null) return null;
            return MessageType.fromCode(val);
        }
    }

}
