//ecd:-1630610409H20240517105348V0.7
package io.elasticore.demo.linkone.enums;


import lombok.Getter;
import javax.persistence.*;


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
}
