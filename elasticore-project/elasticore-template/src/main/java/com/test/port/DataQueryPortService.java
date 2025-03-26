//ecd:-1012656063H20250324172611_V1.0
package com.test.port;

import java.util.*;

import com.test.dto.*;


import org.springframework.data.domain.Page;
import io.elasticore.runtime.port.*;


/**


 */
@DbmsService(id="port.DataQueryPortService" ,datasource="test" ,sqlSource="blueprint/port/port.yml")
public interface DataQueryPortService   {

    MsgOutput countAllMessagesByMonths2(MsgInput input);
    
    MsgOutput countAllMessagesByMonths(MsgInput input);
    

}
