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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class CheckDTO  implements java.io.Serializable  {

	@Column(name = "update_date")
	private String updateDate;
	

	@Column(name = "created_by")
	private String createdBy;
	


};
