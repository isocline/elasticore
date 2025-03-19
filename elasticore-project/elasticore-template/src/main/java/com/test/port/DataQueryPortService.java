//ecd:872585127H20250319092245_V1.0
package com.test.port;

import com.test.dto.*;


import io.elasticore.runtime.port.*;


/**


 */
@DbmsService(datasource="test", id="port.DataQueryPortService")
public interface DataQueryPortService   {

    MsgOutput countAllMessagesByMonths(MsgInput input);
    

}
