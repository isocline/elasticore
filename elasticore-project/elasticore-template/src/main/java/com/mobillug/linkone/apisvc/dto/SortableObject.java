//ecd:-437412617H20240805175916_V0.8
package com.mobillug.linkone.apisvc.dto;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public interface SortableObject {

    String getSortCode();

    default Sort getSort() {
        String sortTxt = getSortCode();
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
