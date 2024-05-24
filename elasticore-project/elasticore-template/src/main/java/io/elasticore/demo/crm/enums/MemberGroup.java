//ecd:199806955H20240524175232V0.7
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
