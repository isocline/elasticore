//ecd:414969608H20240528005422V0.7
package io.elasticore.demo.linkone.dto;

import io.elasticore.demo.linkone.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class PagingInfo  implements java.io.Serializable  {
	// 현재 페이지 번호
	private Integer page = 0;
	// 페이지당 로우수
	private Integer perPage = 10;
	// 전체 로우 수
	private Integer rowCount;
	// 전체 페이지 수
	private Integer pageCount;
};
