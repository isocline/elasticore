//ecd:755538376H20250319211002_V1.0
package com.test.port;

import java.util.*;

import com.test.dto.*;


import io.elasticore.runtime.port.*;


/**


 */
@DbmsService(id="port.DataQueryPortService" ,datasource="test" ,sqlSource="blueprint/port/port.yml")
public interface DataQueryPortService   {

    MsgOutput countAllMessagesByMonths(MsgInput input);
    

}
