//ecd:1396475085H20250324171551_V1.0
package com.test.port;

import java.util.*;

import com.test.dto.*;


import org.springframework.data.domain.Page;
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
