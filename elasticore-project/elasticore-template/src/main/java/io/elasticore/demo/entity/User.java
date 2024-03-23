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
public class User  implements java.io.Serializable  {


    @Id
    private long id;
    

    @Column(nullable = false, length = 50)
    private String email;
    

    @Column(nullable = false)
    private String name;
    

    private int grpCd;
    

    @Column(columnDefinition = "TEXT")
    private String desc;
    

    @OneToMany
    private List<Post> posts;
    



}
