//ecd:1589780270H20240805175925_V0.8
package io.elasticore.demo.crm.enums;

import lombok.Getter;




import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**


 */




@Getter
public enum MemberGroup {

    ROL_ASSOCIATE("T1")
    ,ROLE_REGULAR("T2");

    private final String name;

    MemberGroup(String name) {
        this.name = name;
    }

    
    public static List<Map> getAllEnumInfo(){
        List list = new ArrayList();
        for(MemberGroup i:MemberGroup.class.getEnumConstants()){
            HashMap map = new HashMap();
            map.put("_name", i.name());
            map.put("name", i.getName());
            list.add(map);
        }
        return list;
    }

}
