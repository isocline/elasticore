//ecd:165777751H20240527134244V0.7
package io.elasticore.demo.linkone.enums;

import lombok.Getter;





/**


 */




@Getter
public enum StatusType {

    CONFIRM("확정")
    ,CHANGE("변경")
    ,FAIL("불가");

    private final String msg;

    StatusType(String msg) {
        this.msg = msg;
    }

    
}
