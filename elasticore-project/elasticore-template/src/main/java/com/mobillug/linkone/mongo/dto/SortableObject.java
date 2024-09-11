//ecd:290324942H20240911095941_V1.0
package com.mobillug.linkone.mongo.dto;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public interface SortableObject {

    String getSortCode();

    String getSortColumn();

    Boolean getSortAscending();

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
