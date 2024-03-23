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
public class Post  implements java.io.Serializable  {


    @Id
    private long id;
    

    private String title;
    

    private String content;
    

    private boolean published = false;
    

    @ManyToOne
    @JoinColumn(columnDefinition = "author_id")
    private User author;
    



}
