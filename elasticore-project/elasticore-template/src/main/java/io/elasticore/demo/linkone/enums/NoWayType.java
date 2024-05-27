//ecd:-1855007769H20240527134244V0.7
package io.elasticore.demo.linkone.enums;

import lombok.Getter;





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
