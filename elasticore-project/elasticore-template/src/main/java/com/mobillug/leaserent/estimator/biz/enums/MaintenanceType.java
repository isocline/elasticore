//ecd:-1825062785H20241223210702_V1.0
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


@JsonSerialize(using = MaintenanceType.CustomSerializer.class)
@JsonDeserialize(using = MaintenanceType.CustomDeserializer.class)
@Getter
public enum MaintenanceType {

    MAINTENANCE_EXCLUDED("정비제외","01")
    ,ROUTINE_MAINTENANCE("순회정비","02");

    private final String msg;
    private final String code;

    MaintenanceType(String msg,String code) {
        this.msg = msg;
    this.code = code;
    }

    
    public static List<Map> getAllEnumInfo(){
        List<Map> list = new ArrayList();
        for(MaintenanceType i:MaintenanceType.class.getEnumConstants()){
            HashMap<String,Object> map = new HashMap();
            map.put("_name", i.name());
            map.put("msg", i.getMsg());
            map.put("code", i.getCode());
            list.add(map);
        }
        return list;
    }

    public static MaintenanceType fromCode(String code){
        if(code ==null) return null;
        if(code.isEmpty()) return null;
        for (MaintenanceType type : MaintenanceType.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown MaintenanceType code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<MaintenanceType>{
        @Override
        public void serialize(MaintenanceType value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<MaintenanceType>{
        @Override
        public MaintenanceType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return MaintenanceType.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && !txt.isEmpty())
              setValue(MaintenanceType.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<MaintenanceType, String>{
        @Override
        public String convertToDatabaseColumn(MaintenanceType e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public MaintenanceType convertToEntityAttribute(String val){
            if (val == null) return null;
            return MaintenanceType.fromCode(val);
        }
    }

}
