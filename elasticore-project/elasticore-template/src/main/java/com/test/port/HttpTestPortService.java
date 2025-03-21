//ecd:1417260068H20250321093916_V1.0
package com.test.port;

import java.util.*;

import com.test.dto.*;


import io.elasticore.runtime.port.*;


/**


 */
@ExternalService(protocol="http", id="port.HttpTestPortService" ,url="https://echo.free.beeceptor.com")
public interface HttpTestPortService   {

    @HttpEndpoint(url="/gateway/chn-sfa/api/plan/list", method="POST")
    java.util.HashMap listPlan(MsgInput2 body);
    

}
