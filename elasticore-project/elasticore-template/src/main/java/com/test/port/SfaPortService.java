//ecd:1847598017H20250318201341_V1.0
package com.test.port;

import java.util.*;

import com.test.dto.*;


import io.elasticore.runtime.annotation.*;


/**


 */
@ExternalService(type="http", id="SfaPortService")
public interface SfaPortService   {

    @HttpEndpoint(url="/gateway/chn-sfa/api/plan/list", method="POST")
    java.util.HashMap listPlan(MsgInput param,MsgInput2 srch);
    

}
