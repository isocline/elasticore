package io.elasticore.blueprint.domain.parts.port;


import io.elasticore.runtime.port.ExternalService;
import io.elasticore.runtime.port.HttpEndpoint;

import java.util.Map;


@ExternalService(protocol = "http", id = "aligo.apiserver", url = "https://kakaoapi.aligo.in")
public interface AligoHttpPortAdapter {

    /*
     * testEcho
     */
    @HttpEndpoint(url = "/akv10/token/create/60/s/", method = "POST", contentType = "application/json", paramNames = "apikey,userid")
    java.util.HashMap createToken(String apikey,  String userid);



    @HttpEndpoint(url = "/akv10/alimtalk/send/", method = "POST", contentType = "application/json", paramNames = "body")
    java.util.HashMap sendAlimTalk(Map body);


}
