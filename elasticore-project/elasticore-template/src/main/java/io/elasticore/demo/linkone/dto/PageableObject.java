//ecd:-1467881570H20240530103703_V0.8
package io.elasticore.demo.linkone.dto;

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
