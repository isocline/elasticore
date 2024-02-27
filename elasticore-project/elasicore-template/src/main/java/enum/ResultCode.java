package com.elasticore.sample.enum;


import lombok.Getter;
import lombok.AllArgsConstructor;
import javax.persistence.*;


/**


 */

@Getter
@AllArgsConstructor
public enum ResultCode {


    SUCCESS(0,"성공하였습니다.")
    ,FAILED(-1,"실패하였습니다.")
    ,USERNAME_SIGN_IN_FAILED(-10001,"계정이 존재하지 않거나 이메일 또는 비밀번호가 정확하지 않습니다.")
    ,AUTHENTICATION_ENTRY_POINT(-10002,"해당 리소스에 접근하기 위한 권한이 없습니다.")
    ,ACCESS_DENIED(-10003,"보유한 권한으로 접근 할 수 없는 리소스 입니다.")
    ,PASSWORD_FAILED(-10004,"잘못된 비밀번호 입니다.")
    ,PHONE_FAILED(-10005,"잘못된 연락처 형식입니다.")
    ,USERNAME_FAILED(-10006,"잘못된 이메일 형식입니다.")
    ,USERNAME_DUPLICATE(-10007,"이미 등록된 이메일 입니다.")
    ,PHONE_CERTIFICATION_FAILED(-10008,"휴대폰 인증이 유효하지 않습니다.")
    ,MISTAKE_PARAM(-20000,"유효하지 않은 매개변수입니다.");


    private final int code;
    private final string msg


    ResultCode(int code,string msg) {

 this.code = code;
    this.msg = msg

 }
}
