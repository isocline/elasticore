//ecd:-1483381650H20240521223026V0.7
package io.elasticore.demo.linkone.enums;


import lombok.Getter;



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
