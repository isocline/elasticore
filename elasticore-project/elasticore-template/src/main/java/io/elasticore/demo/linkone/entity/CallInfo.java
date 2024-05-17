//ecd:-705505384H20240517105348V0.7
package io.elasticore.demo.linkone.entity;


import io.elasticore.demo.linkone.enums.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
	@Temporal(TemporalType.DATE)
	private java.util.Date callDate;
	

	@Column(name = "call_time")
	@Temporal(TemporalType.TIME)
	private java.util.Date callTime;
	


};
