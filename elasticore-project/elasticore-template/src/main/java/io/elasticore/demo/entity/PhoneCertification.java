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
public class PhoneCertification  implements java.io.Serializable  {


    @Id
    private Long id;
    

    @Column(nullable = false, length = 13)
    private String phone;
    

    @Column(nullable = false, length = 6)
    private String certificationNumber;
    

    @Column(nullable = false)
    private long sendTime;
    

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(columnDefinition = "dateLastEnd_id")
    private Date dateLastEnd;
    



}
