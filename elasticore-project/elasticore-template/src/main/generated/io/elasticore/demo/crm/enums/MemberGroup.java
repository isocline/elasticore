package io.elasticore.demo.crm.enums;


import lombok.Getter;
import javax.persistence.*;


/**


 */


@Getter

public enum MemberGroup {


    ROL_ASSOCIATE("준회원")
    ,ROLE_REGULAR("정회원");


    private final String name;


    MemberGroup(String name) {

        this.name = name;

    }
}
