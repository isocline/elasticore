package com.elasticore.sample.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;


/**


 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneCertification  implements Serializable  {


    @Id
    private Long id;
    
    @Column(nullable = false, length = 13)
    private String phone;
    
    @Column(nullable = false, length = 6)
    private String certificationNumber;
    
    @Column(nullable = false)
    private long sendTime;
    
    @Column(nullable = false)
    private datetime dateLastEnd;
    


}
