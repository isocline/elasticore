//ecd:349544489H20240618012928_V0.8
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




@JsonSerialize(using = AreaCode.CustomSerializer.class)
@JsonDeserialize(using = AreaCode.CustomDeserializer.class)
@Getter
public enum AreaCode {

    SEOUL("서울","SU")
    ,GYEONGGI("경기","GG")
    ,GANGWON("강원","KW")
    ,CHUNGCHEONG("충청","CC")
    ,JEOLLA("전라","JL")
    ,GYEONGSANG("경상","GS")
    ,JEJU("제주","JJ");

    private final String msg;
    private final String code;

    AreaCode(String msg,String code) {
        this.msg = msg;
    this.code = code;
    }

    
    public static AreaCode fromCode(String code){
        for (AreaCode type : AreaCode.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<AreaCode>{
        @Override
        public void serialize(AreaCode value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<AreaCode>{
        @Override
        public AreaCode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return AreaCode.fromCode(p.getText());
        }
    }
    
    public static class PropertyEditor extends java.beans.PropertyEditorSupport{
        @Override
        public void setAsText(String txt) throws IllegalArgumentException{
            if(txt!=null && txt.length()>0)
              setValue(AreaCode.fromCode(txt));
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<AreaCode, String>{
        @Override
        public String convertToDatabaseColumn(AreaCode e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public AreaCode convertToEntityAttribute(String val){
            if (val == null) return null;
            return AreaCode.fromCode(val);
        }
    }

}
