//ecd:-1494013758H20250409104819_V1.0
package io.elasticore.blueprint.domain.bbs.enums;

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
 * BoardType
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */


@JsonSerialize(using = BoardType.CustomSerializer.class)
@JsonDeserialize(using = BoardType.CustomDeserializer.class)
@Getter
public enum BoardType {

    PUBLIC("PB","공개")
    ,PRIVATE("PV","개인");

    private final String code;
    private final String name;

    BoardType(String code,String name) {
        this.code = code;
    this.name = name;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(BoardType i:BoardType.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("code", i.getCode());
            map.put("name", i.getName());
            list.add(map);
        }
        return list;
    }

    public static BoardType fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (BoardType type : BoardType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown BoardType code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<BoardType>{
        @Override
        public void serialize(BoardType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<BoardType>{
        @Override
        public BoardType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return BoardType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(BoardType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<BoardType, String>{
        @Override
        public String convertToDatabaseColumn(BoardType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public BoardType convertToEntityAttribute(String val){
            if (val == null) return null;
            return BoardType.fromCode(val);
        }
    }

}
