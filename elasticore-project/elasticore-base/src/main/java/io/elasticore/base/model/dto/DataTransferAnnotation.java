package io.elasticore.base.model.dto;

import io.elasticore.base.model.entity.EntityAnnotation;

/**
 * This interface defines various annotation names used in elastiCore modeling.
 */
public interface DataTransferAnnotation extends EntityAnnotation {



    String META_SEARCHABLE = "searchable";


    String META_SEARCH_RESULT = "searchResult";


    String META_SEARCHABLE_BYPASS = "searchBypass";



    String META_SEARCHABLE_PAGESIZE = "searchable.pageSize";

    String META_EXTEND = "extend";

    String META_TEMPLATE = "template";


    String[] META_SEARCHABLE_NAME = new String[]{"searchable.entity", "searchable"};


    String[] META_SEARCH_RESULT_NAME = new String[]{"searchResult.entity", "searchResult"};


}