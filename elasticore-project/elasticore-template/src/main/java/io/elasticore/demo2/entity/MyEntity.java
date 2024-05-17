//ecd:-626397558H20240517105348V0.7
package io.elasticore.demo2.entity;


import io.elasticore.demo2.enums.*;

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

public  class MyEntity  implements java.io.Serializable  {

	@Column(name = "data")
	private String data;
	

	@Column(name = "end_data")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date endData;
	


};
