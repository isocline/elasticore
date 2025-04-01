//ecd:719612224H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.unitprice.dto;

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
