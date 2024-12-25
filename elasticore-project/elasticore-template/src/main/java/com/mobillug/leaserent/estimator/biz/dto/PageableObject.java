//ecd:-417933494H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface PageableObject extends SortableObject {

    @Schema(hidden = true)
    default Pageable getPageable() {
        Sort sort = getSort();
        if(sort!=null)
            return PageRequest.of(getPageNumber(), getPageSize(), getSort());
        else
            return PageRequest.of(getPageNumber(), getPageSize());
    }

    int getPageNumber();

    int getPageSize();


}
