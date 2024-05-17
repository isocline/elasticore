//ecd:-255619321H20240517105348V0.7
package io.elasticore.demo2.enums;


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
