//ecd:-1880838032H20250318201341_V1.0
package com.test.port;

import java.util.*;

import com.test.dto.*;


import io.elasticore.runtime.annotation.*;


/**


 */
@ExternalService(type="http", id="PurioPortService")
public interface PurioPortService   {

    @HttpEndpoint(url="/test", method="POST")
    TokenOutput getToken(AuthProvider authProvider);
    
    @HttpEndpoint(url="/v3/message", method="POST")
    MsgOutput sendMessage(AuthProvider authProvider,MsgInput message);
    

}
