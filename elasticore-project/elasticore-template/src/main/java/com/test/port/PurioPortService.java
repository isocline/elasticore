//ecd:201090989H20250321093916_V1.0
package com.test.port;

import java.util.*;

import com.test.dto.*;


import io.elasticore.runtime.port.*;


/**


 */
@ExternalService(protocol="http", id="port.PurioPortService" ,url="http://server.com")
public interface PurioPortService   {

    @HttpEndpoint(url="/test", method="POST")
    TokenOutput getToken(AuthProvider authProvider);
    
    @HttpEndpoint(url="/v3/message", method="POST")
    MsgOutput sendMessage(AuthProvider authProvider,MsgInput message);
    

}
