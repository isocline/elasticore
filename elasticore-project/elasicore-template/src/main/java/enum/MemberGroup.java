package com.elasticore.sample.enum;


import lombok.Getter;
import lombok.AllArgsConstructor;
import javax.persistence.*;


/**


 */

@Getter
@AllArgsConstructor
public enum MemberGroup {


    ROL_ASSOCIATE(준회원)
    ,ROLE_REGULAR(정회원);


    private final String name


    MemberGroup(String name) {

 this.name = name

 }
}
