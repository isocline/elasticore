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
public class MemberPointHistory  implements java.io.Serializable  {


    @Id
    private Long id;
    

    @ManyToOne
    @JoinColumn(columnDefinition = "member_id")
    private Member member;
    

    @ManyToOne
    @JoinColumn(columnDefinition = "pointType_id")
    private PointType pointType;
    

    @Column(nullable = false)
    private Integer point;
    

    @Column(nullable = false, length = 100)
    private String memo;
    

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(columnDefinition = "dateCreate_id")
    private Date dateCreate;
    

    @ManyToOne
    @JoinColumn(columnDefinition = "dateExpiration_id")
    private Date dateExpiration;
    



}
