//ecd:111512463H20240527134244V0.7
package io.elasticore.demo.linkone.entity;

import io.elasticore.demo.linkone.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.hibernate.annotations.Comment;
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

public  class CallInfo  implements java.io.Serializable  {
	@Id
	@Column(name = "seq")
	private Long seq;
	
	
	@Column(name = "call_date")
	private java.time.LocalDate callDate;
	
	
	@Column(name = "call_time")
	private java.time.LocalTime callTime;
	
	
};
