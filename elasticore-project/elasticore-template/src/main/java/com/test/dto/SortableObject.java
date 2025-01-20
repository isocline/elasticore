//ecd:893615018H20250117173851_V1.0
package com.test.dto;

import org.springframework.data.domain.Sort;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

public interface SortableObject {

    @Schema(description = "Field used to specify the sorting criteria. Use '+' for ascending and '-' for descending. Multiple fields can be specified, separated by commas.",
            example = "name+,age-")
    String getSortCode();

    @Schema(hidden = true)
    String getSortColumn();

    @Schema(hidden = true)
    Boolean getSortAscending();

    @Schema(hidden = true)
    default Sort getSort() {
        String sortTxt = getSortCode();

        String sortCol = getSortColumn();
        if(sortCol !=null && !sortCol.isEmpty()) {
           sortTxt = sortCol;
           if(getSortAscending())
               sortTxt=sortTxt+"+";
           else
               sortTxt=sortTxt+"-";
        }

        if (sortTxt == null || sortTxt.isEmpty()) return null;

        List<Sort.Order> orders = new ArrayList<>();
        String[] sortParams = sortTxt.split(",");

        for (String param : sortParams) {
            if (param.endsWith("+")) {
                orders.add(Sort.Order.asc(param.substring(0, param.length() - 1)));
            } else if (param.endsWith("-")) {
                orders.add(Sort.Order.desc(param.substring(0, param.length() - 1)));
            }
        }
        return Sort.by(orders);
    }
}
