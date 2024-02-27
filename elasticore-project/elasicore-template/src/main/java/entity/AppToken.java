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
public class AppToken  implements Serializable  {


    @Id
    private Long id;
    
    private Member member;
    
    @Column(nullable = false)
    private String appToken;
    
    @Column(nullable = false)
    private datetime dateCreate;
    


}
