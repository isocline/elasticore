//ecd:1394052310H20250319092245_V1.0
package com.test.port;

import com.test.dto.*;


import io.elasticore.runtime.port.*;


/**


 */
@ExternalService(protocol="http", id="port.SfaPortService" ,url="http://server.com")
public interface SfaPortService   {

    @HttpEndpoint(url="/gateway/chn-sfa/api/plan/list", method="POST")
    java.util.HashMap listPlan(MsgInput param,MsgInput2 srch);
    

}
