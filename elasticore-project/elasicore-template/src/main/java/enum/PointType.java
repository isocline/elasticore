package com.elasticore.sample.enum;


import lombok.Getter;
import lombok.AllArgsConstructor;
import javax.persistence.*;


/**


 */

@Getter
@AllArgsConstructor
public enum PointType {


    PLUS(추가,+)
    ,MINUS(차감,-);


    private final String name;
    private final string symbol


    PointType(String name,string symbol) {

 this.name = name;
    this.symbol = symbol

 }
}
