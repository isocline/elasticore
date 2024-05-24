//ecd:-894934441H20240524175232V0.7
package io.elasticore.demo.linkone.enums;


import lombok.Getter;




import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;


/**


 */





@Getter
public enum AaccidentType {


    D("피해")
    ,I("가해")
    ,S("단독")
    ,R("정비")
    ,E("기타")
    ,U("미정");


    private final String msg;


    AaccidentType(String msg) {

        this.msg = msg;

    }

    
    public static AaccidentType fromMsg(String msg){
        for (AaccidentType type : AaccidentType.values()){
            if (type.msg.equals(msg)) return type;
        }
        throw new IllegalArgumentException("Unknown msg: " + msg);
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<AaccidentType, String>{
        @Override
        public String convertToDatabaseColumn(AaccidentType e){
            if (e == null) return null;
            return e.getMsg();
        }
        
        @Override
        public AaccidentType convertToEntityAttribute(String val){
            if (val == null) return null;
            return AaccidentType.fromMsg(val);
        }
    }


}
