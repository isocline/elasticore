package io.elasticore.base.model.relation;


public enum RelationType {

    SUPER("super"),
    CHILD("child"),
    ROLLUP("rollup"),
    ROLLUP_("rollup"),
    ROLLDOWN("rolldown"),
    TEMPLATE("template"),
    TEMPLATE_TO("templateTo"),
    ONE_TO_ONE("ontToOne"),
    ONE_TO_MANY("oneToMany"),
    MANY_TO_ONE("manyToOne"),
    MANY_TO_MANY("manyToMany"),
    EMBEDDED("embedded"),
    EMBEDDABLE("embeddable");


    private String name;

    private RelationType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
