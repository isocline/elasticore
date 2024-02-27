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
public class Member  implements Serializable  {


    @Id
    private Long id;
    
    @Column(nullable = false, length = 20)
    private MemberGroup memberGroup;
    
    @Column(nullable = false, length = 20)
    private MemberProvider memberProvider;
    
    @Column(nullable = false)
    private datetime dateCreate;
    
    @Column(nullable = false, unique = true)
    private string username;
    
    private string password;
    
    private string name;
    
    private string phone;
    
    @Column(nullable = false)
    private Boolean isEnabled;
    


}
