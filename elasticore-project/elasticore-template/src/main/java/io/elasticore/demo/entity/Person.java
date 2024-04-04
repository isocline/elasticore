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
