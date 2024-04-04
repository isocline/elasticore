package io.elasticore.demo.enums;


import lombok.Getter;
import javax.persistence.*;


/**


 */


@Getter

public enum CurrencyCode {


    KRW("410","won")
    ,USD("840","dollar")
    ,CAD("124","dollar");


    private final String code;
    private final String unit;


    CurrencyCode(String code,String unit) {

        this.code = code;
    this.unit = unit;

    }
}
