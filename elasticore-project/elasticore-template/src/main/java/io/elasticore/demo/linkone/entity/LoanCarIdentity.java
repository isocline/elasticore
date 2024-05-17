//ecd:197761382H20240516173754V0.7
package io.elasticore.demo.linkone.entity;



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

@Embeddable

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class LoanCarIdentity  implements java.io.Serializable  {

	@Comment("아이디")
	@Column(name = "lc_code")
	private Long lcCode;
	
	@Comment("요청상담사")
	@Column(name = "emp_no")
	private String empNo;
	

};
