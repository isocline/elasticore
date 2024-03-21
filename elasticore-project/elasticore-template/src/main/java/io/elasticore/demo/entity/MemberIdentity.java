package io.elasticore.demo.entity;



import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.*;


/**


 */
@Embeddable

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberIdentity  implements java.io.Serializable  {


    private Long id;
    
    private Long id2;


}
