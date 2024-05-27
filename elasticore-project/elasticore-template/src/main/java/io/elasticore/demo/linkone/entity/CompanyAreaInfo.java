//ecd:-1924802983H20240528005422V0.7
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

public  class CompanyAreaInfo  implements java.io.Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;
	
	
	@Column(length = 2)
	@Convert(converter = AreaCode.EntityConverter.class)
	private AreaCode areaCode;
	
	
};
