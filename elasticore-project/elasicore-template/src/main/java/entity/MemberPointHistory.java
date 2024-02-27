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
public class MemberPointHistory  implements Serializable  {


    @Id
    private Long id;
    
    private Member member;
    
    private PointType pointType;
    
    @Column(nullable = false)
    private Integer point;
    
    @Column(nullable = false, length = 100)
    private string memo;
    
    @Column(nullable = false)
    private date dateCreate;
    
    private date dateExpiration;
    


}
