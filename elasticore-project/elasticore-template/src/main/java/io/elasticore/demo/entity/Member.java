package io.elasticore.demo.entity;


import io.elasticore.demo.enums.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.*;


/**


 */
@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member  implements java.io.Serializable  {


    @EmbeddedId
    private MemberIdentity id;


}
