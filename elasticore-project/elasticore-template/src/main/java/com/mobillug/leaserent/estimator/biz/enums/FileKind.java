//ecd:485308617H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.enums;

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


@JsonSerialize(using = FileKind.CustomSerializer.class)
@JsonDeserialize(using = FileKind.CustomDeserializer.class)
@Getter
public enum FileKind {

    CAR_IMG("CI","차량 이미지")
    ,LOGO("LG","브랜드이미지");

    private final String code;
    private final String name;

    FileKind(String code,String name) {
        this.code = code;
    this.name = name;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(FileKind i:FileKind.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("code", i.getCode());
            map.put("name", i.getName());
            list.add(map);
        }
        return list;
    }

    public static FileKind fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (FileKind type : FileKind.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown FileKind code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<FileKind>{
        @Override
        public void serialize(FileKind value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<FileKind>{
        @Override
        public FileKind deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return FileKind.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(FileKind.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<FileKind, String>{
        @Override
        public String convertToDatabaseColumn(FileKind e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public FileKind convertToEntityAttribute(String val){
            if (val == null) return null;
            return FileKind.fromCode(val);
        }
    }

}
