//ecd:-985280933H20240517105348V0.7
package io.elasticore.demo.linkone.enums;


import lombok.Getter;
import javax.persistence.*;


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
}
