//ecd:816055198H20240618012928_V0.8
package com.mobillug.gateone.biz.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface PageableObject extends SortableObject {

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
