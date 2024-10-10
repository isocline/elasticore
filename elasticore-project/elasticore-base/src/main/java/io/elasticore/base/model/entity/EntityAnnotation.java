package io.elasticore.base.model.entity;

/**
 * This interface defines various annotation names used in elastiCore modeling.
 */
public interface EntityAnnotation {


    String META_TABLE = "table";


    String[] META_DISABLE =  new String[]{"disable", "deferred"};


    String META_DEFERRED = "deferred";

    String META_ID = "id";



    String[] META_MAPPING = new String[]{"mapping", "table"};


    String META_EXTEND = "extend";


    String META_IMMUTABLE = "immutable";


    String META_DYNAMIC_UPDATE = "DynamicUpdate";


    String META_DYNAMIC_INSERT = "DynamicInsert";


    String META_EMBEDDABLE = "Embeddable";



    String META_ABSTRACT = "abstract";


    String META_TYPE = "type";


    String META_DTO = "dto";


    String META_PAGEABLE = "pageable";

    String META_ROLL_UP = "rollup";

    String[] META_ROLL_UP_TARGET = new String[]{"rollup.target", "rollup.entity" ,"rollup"};

    String[] META_ROLL_UP_DISCRIMINATOR = new String[]{"rollup.discriminator", "rollup.value", "rollup.type"};


    String META_ROLL_DOWN = "rolldown";


    String[] META_NOT_ENTITY =  new String[]{META_ABSTRACT};


    String[] DISCRIMINATOR = new String[]{"discriminator", "discrim"};

    /**
     * Annotations for search functionality.
     */
    String[] SEARCH = new String[]{"searchable", "search","s", "srch" ,"filter"};

    /**
     * Annotations for reference functionality.
     */
    String[] REFERENCE = new String[]{"reference", "ref"};

    /**
     * Annotations for specifying length or size.
     */
    String[] LENGTH = new String[]{"length", "len", "size"};

    /**
     * Annotations for specifying the column name in the database.
     */
    String[] COLUMN_NAME = new String[]{"column", "col", "db"};

    /**
     * Annotations for fetch type.
     */
    String[] FETCH = new String[]{"fetch"};

    /**
     * Annotations for cascade type.
     */
    String[] CASCADE = new String[]{"cascade"};

    /**
     * Annotations for adding comments or labels.
     */
    String[] COMMENT = new String[]{"label", "comment", "desc", "description"};

    /**
     * Annotations for adding descriptions or comments.
     */
    String[] DESCRIPTION = new String[]{"description", "desc", "label", "comment"};

    /**
     * Annotations for specifying if a field is updatable.
     */
    String[] UPDATABLE = new String[]{"updatable"};

    /**
     * Annotations for indicating if a calculation is required.
     */
    String[] CALCULATION_REQUIRED = new String[]{"calcRequired"};

    /**
     * Annotations for providing examples.
     */
    String[] EXAMPLE = new String[]{"example"};

    /**
     * Annotations for specifying format or pattern.
     */
    String[] FORMAT = new String[]{"format", "pattern"};

    /**
     * Annotations for specifying minimum size.
     */
    String[] MIN_SIZE = new String[]{"minsize"};

    /**
     * Annotations for specifying JSON property names.
     */
    String[] JSON_NAME = new String[]{"jsonproperty", "json"};

    /**
     * Annotations for specifying JSON serialization using a specific method.
     */
    String[] JSON_SERIALIZE_USE = new String[]{"jsonSerialize.using"};

    /**
     * Annotations for specifying JSON deserialization using a specific method.
     */
    String[] JSON_DESERIALIZE_USE = new String[]{"jsonDeserialize.using"};

    /**
     * Annotations for specifying getter functions.
     */
    String[] FUNCTION_GET = new String[]{"function.get"};

    /**
     * Annotations for specifying setter functions.
     */
    String[] FUNCTION_SET = new String[]{"function.set"};

    /**
     * Annotations for specifying the kind of a field.
     */
    String[] KIND = new String[]{"kind"};



    String[] ONE_TO_ONE = new String[]{"onetoone"};


    String[] NOT_NULL = new String[]{"notnull" ,"notblank" ,"required"};


    String[] JOIN = new String[]{"join"};

    String[] EMBEDDED = new String[] {"embedded"};

    String[] EMBEDDED_PREFIX = new String[] {"embedded.prefix", "embedded"};
}