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
@DiscriminatorValue("Compay")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class Company extends Organization implements java.io.Serializable  {


    @Column(name = "online_processing_date")
    private String onlineProcessingDate;
    

    @Column(name = "contract_issue_start_day")
    private String contractIssueStartDay;
    

    @Column(name = "number_of_hold_days")
    private Integer numberOfHoldDays;
    



}
