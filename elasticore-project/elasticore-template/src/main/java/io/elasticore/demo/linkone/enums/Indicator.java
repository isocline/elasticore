//ecd:1306764910H20240529100717V0.7
package io.elasticore.demo.linkone.enums;

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




@JsonSerialize(using = Indicator.CustomSerializer.class)
@JsonDeserialize(using = Indicator.CustomDeserializer.class)
@Getter
public enum Indicator {

    YES("Y")
    ,NO("N");

    private final String code;

    Indicator(String code) {
        this.code = code;
    }

    
    public static Indicator fromCode(String code){
        for (Indicator type : Indicator.values()){
            if (type.code.equals(code)) return type;
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
    
    public static class CustomSerializer extends JsonSerializer<Indicator>{
        @Override
        public void serialize(Indicator value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
            gen.writeString(value.getCode());
        }
    }
    
    public static class CustomDeserializer extends JsonDeserializer<Indicator>{
        @Override
        public Indicator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
            return Indicator.fromCode(p.getText());
        }
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<Indicator, String>{
        @Override
        public String convertToDatabaseColumn(Indicator e){
            if (e == null) return null;
            return e.getCode();
        }
        
        @Override
        public Indicator convertToEntityAttribute(String val){
            if (val == null) return null;
            return Indicator.fromCode(val);
        }
    }

}
