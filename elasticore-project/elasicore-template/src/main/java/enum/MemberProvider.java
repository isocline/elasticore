package com.elasticore.sample.enum;


import lombok.Getter;
import lombok.AllArgsConstructor;
import javax.persistence.*;


/**


 */

@Getter
@AllArgsConstructor
public enum MemberProvider {


    COMPANY(자사)
    ,KAKAO(카카오);


    private final String name


    MemberProvider(String name) {

 this.name = name

 }
}
