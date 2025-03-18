//ecd:-124088364H20250318201732_V1.0
package com.test.port;

import java.util.*;

import com.test.dto.*;


import io.elasticore.runtime.annotation.*;


/**


 */
@DbmsService(datasource="test")
public interface DataQueryPortService   {

    MsgOutput countAllMessagesByMonths(MsgInput input);
    

}
