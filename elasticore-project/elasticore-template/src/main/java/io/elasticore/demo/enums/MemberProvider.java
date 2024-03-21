package io.elasticore.demo.enums;


import lombok.Getter;
import javax.persistence.*;


/**


 */

@Getter
public enum MemberProvider {


    COMPANY("자사")
    ,KAKAO("카카오");


    private final String name;


    MemberProvider(String name) {

        this.name = name;

 }
}
