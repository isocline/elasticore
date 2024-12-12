package io.elasticore.base.model.relation;


public enum RelationType {


    CHILD("child"),
    ROLLUP("rollup"),

    ROLLDOWN("rolldown"),
    TEMPLATE("template"),
    TEMPLATE_TO("templateTo"),
    ONE_TO_ONE("ontToOne"),
    ONE_TO_MANY("oneToMany"),
    MANY_TO_ONE("manyToOne"),
    MANY_TO_MANY("manyToMany"),
    SEARCHED("searched"),
    SEARCHABLE("searchable"),
    EMBEDDED("embedded"),
    EMBEDDABLE("embeddable"),

    AOSSOCIATION("association"),

    SEARCH_RESULT("searchResult"),

    MAPPER("mapper"),
    SUPER("super");


    private String name;

    private RelationType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
