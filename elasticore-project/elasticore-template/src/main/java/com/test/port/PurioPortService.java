//ecd:641366811H20250319092245_V1.0
package com.test.port;

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
