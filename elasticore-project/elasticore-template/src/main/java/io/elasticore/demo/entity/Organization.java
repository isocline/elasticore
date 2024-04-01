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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Organization extends Party implements java.io.Serializable  {


    @Column(name = "organization_status_key")
    private String organizationStatusKey;
    

    @Column(name = "estab_date")
    private String estabDate;
    

    @Column(name = "num_owners")
    private Integer numOwners;
    



}
