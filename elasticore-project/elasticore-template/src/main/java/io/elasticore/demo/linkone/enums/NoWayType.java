//ecd:530013520H20240517105348V0.7
package io.elasticore.demo.linkone.enums;


import lombok.Getter;
import javax.persistence.*;


/**


 */


@Getter

public enum NoWayType {


    R("수리만진행")
    ,A("지역변경")
    ,E("기타");


    private final String msg;


    NoWayType(String msg) {

        this.msg = msg;

    }
}
