//ecd:1296554231H20250410094432_V1.0
package io.elasticore.blueprint.domain.parts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Provides pagination and sorting support for search-related DTOs.
 *
 * Implementing classes must supply page number, page size,
 * and optionally sorting information to enable pageable queries.
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */
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
