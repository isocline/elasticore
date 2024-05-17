//ecd:-2131938162H20240517105348V0.7
package io.elasticore.demo.crm.enums;


import lombok.Getter;
import javax.persistence.*;


/**


 */


@Getter

public enum MemberGroup {


    ROL_ASSOCIATE("T1")
    ,ROLE_REGULAR("T2");


    private final String name;


    MemberGroup(String name) {

        this.name = name;

    }
}
