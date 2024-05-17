//ecd:-1105559692H20240517105348V0.7
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
@DiscriminatorValue("Person")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class Person extends Party implements java.io.Serializable  {

	@Column(name = "attained_age")
	private Integer attainedAge;
	

	@Column(name = "date_of_birth")
	private String dateOfBirth;
	

	@Column(name = "birth_location")
	private String birthLocation;
	

	@Column(name = "death_registration_user_id")
	private String deathRegistrationUserId;
	


};
