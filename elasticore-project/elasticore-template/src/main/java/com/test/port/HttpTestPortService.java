//ecd:-101840582H20250321145637_V1.0
package com.test.port;

import java.util.*;

import com.test.dto.*;


import org.springframework.data.domain.Page;
import io.elasticore.runtime.port.*;


/**
 * <pre>안녕</pre>

 */
@ExternalService(protocol="http", id="port.HttpTestPortService" ,url="https://echo.free.beeceptor.com")
public interface HttpTestPortService   {

    /*
    | 안녕하세요 테스트입니다.
    */
    @HttpEndpoint(url="/gateway/chn-sfa/api/plan/list", method="POST")
    java.util.HashMap listPlan(MsgInput2 body);
    

}
