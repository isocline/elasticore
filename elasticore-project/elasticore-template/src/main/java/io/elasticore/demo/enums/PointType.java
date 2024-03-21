package io.elasticore.demo.enums;


import lombok.Getter;
import javax.persistence.*;


/**


 */

@Getter
public enum PointType {


    PLUS("추가","+")
    ,MINUS("차감","-");


    private final String name;
    private final String symbol;


    PointType(String name,String symbol) {

        this.name = name;
    this.symbol = symbol;

 }
}
