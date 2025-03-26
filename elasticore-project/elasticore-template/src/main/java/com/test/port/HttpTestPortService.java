//ecd:-498105596H20250324171551_V1.0
package com.test.port;

import java.util.*;

import com.test.dto.*;


import org.springframework.data.domain.Page;
import io.elasticore.runtime.port.*;


/**


 */
@ExternalService(protocol="http", id="port.HttpTestPortService" ,url="https://echo.free.beeceptor.com")
public interface HttpTestPortService   {

    @HttpEndpoint(url="/gateway/chn-sfa/api/plan/list", method="POST")
    java.util.HashMap listPlan(MsgInput2 body);
    

}
