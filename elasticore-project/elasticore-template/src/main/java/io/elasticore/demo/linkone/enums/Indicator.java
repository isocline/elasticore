//ecd:-295439736H20240524175232V0.7
package io.elasticore.demo.linkone.enums;


import lombok.Getter;




import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;


/**


 */





@Getter
public enum Indicator {


    Y("YES")
    ,N("NO");


    private final String msg;


    Indicator(String msg) {

        this.msg = msg;

    }

    
    public static Indicator fromMsg(String msg){
        for (Indicator type : Indicator.values()){
            if (type.msg.equals(msg)) return type;
        }
        throw new IllegalArgumentException("Unknown msg: " + msg);
    }
    
    @Converter(autoApply = true)
    public static class EntityConverter implements AttributeConverter<Indicator, String>{
        @Override
        public String convertToDatabaseColumn(Indicator e){
            if (e == null) return null;
            return e.getMsg();
        }
        
        @Override
        public Indicator convertToEntityAttribute(String val){
            if (val == null) return null;
            return Indicator.fromMsg(val);
        }
    }


}
