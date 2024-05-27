//ecd:2126382749H20240528005422V0.7
package io.elasticore.demo.crm.enums;

import lombok.Getter;





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
