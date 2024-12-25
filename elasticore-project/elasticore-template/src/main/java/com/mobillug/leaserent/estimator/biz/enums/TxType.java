//ecd:2030859775H20241223210702_V1.0
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


@JsonSerialize(using = TxType.CustomSerializer.class)
@JsonDeserialize(using = TxType.CustomDeserializer.class)
@Getter
public enum TxType {

    CAR_INFO("CI","차량정보 동기화");

    private final String code;
    private final String name;

    TxType(String code,String name) {
        this.code = code;
    this.name = name;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(TxType i:TxType.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("code", i.getCode());
            map.put("name", i.getName());
            list.add(map);
        }
        return list;
    }

    public static TxType fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (TxType type : TxType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown TxType code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<TxType>{
        @Override
        public void serialize(TxType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<TxType>{
        @Override
        public TxType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return TxType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(TxType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<TxType, String>{
        @Override
        public String convertToDatabaseColumn(TxType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public TxType convertToEntityAttribute(String val){
            if (val == null) return null;
            return TxType.fromCode(val);
        }
    }

}
