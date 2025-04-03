//ecd:1570561573H20250403200008_V1.0
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


 */


@JsonSerialize(using = MessageStatus.CustomSerializer.class)
@JsonDeserialize(using = MessageStatus.CustomDeserializer.class)
@Getter
public enum MessageStatus {

    PENDING("PD","대기")
    ,SENT("OK","전송완료")
    ,FAILED("FL","전송실패");

    private final String code;
    private final String name;

    MessageStatus(String code,String name) {
        this.code = code;
    this.name = name;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(MessageStatus i:MessageStatus.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("code", i.getCode());
            map.put("name", i.getName());
            list.add(map);
        }
        return list;
    }

    public static MessageStatus fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (MessageStatus type : MessageStatus.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown MessageStatus code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<MessageStatus>{
        @Override
        public void serialize(MessageStatus value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<MessageStatus>{
        @Override
        public MessageStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return MessageStatus.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(MessageStatus.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<MessageStatus, String>{
        @Override
        public String convertToDatabaseColumn(MessageStatus e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public MessageStatus convertToEntityAttribute(String val){
            if (val == null) return null;
            return MessageStatus.fromCode(val);
        }
    }

}
