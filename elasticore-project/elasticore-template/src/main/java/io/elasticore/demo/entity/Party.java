package io.elasticore.demo.entity;


import io.elasticore.demo.enums.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.*;
import java.time.*;


/**


 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "party_type", discriminatorType = DiscriminatorType.STRING)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Party  implements java.io.Serializable  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_key")
    private Long partyKey;
    

    @Column(name = "party_type", insertable = false, updatable = false)
    private String partyType;
    

    private CurrencyCode preferCuurencyCode;
    



}
