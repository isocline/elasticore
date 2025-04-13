package io.elasticore.springboot3.mapper;

public class MappingContext {

    private String fieldName;

    private Object from;

    private Object to;

    private int remainDepth = 1;


    private MappingGuard checker;


    public MappingContext(int remainDepth, Object from, Object to, String fieldName, MappingGuard checker) {
        this.remainDepth = remainDepth;
        this.from = from;;
        this.to = to;
        this.fieldName = fieldName;
        this.checker = checker;
    }

    public MappingContext from(Object from) {
        this.from = from;
        return this;
    }

    public MappingContext to(Object to) {
        this.to = to;
        return this;
    }

    public MappingContext fd(String fieldName) {
        this.fieldName = fieldName;
        return this;

    }

    public static MappingContext withGuard(int remainDepth, MappingGuard checker) {
        return new MappingContext(remainDepth, null, null, null,checker);
    }

    public static MappingContext withGuard(int remainDepth) {
        return withGuard(remainDepth, null);
    }


    public boolean checkEnable() {
        if(this.remainDepth<0) return false;

        if(checker!=null) {
            return checker.check(this);
        }

        return true;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public Object getFrom() {
        return this.from;
    }

    public Object getTo() {
        return this.to;
    }

    public int getRemainDepth() {
        return this.remainDepth;
    }




    public MappingContext getChild() {
        return new MappingContext(this.remainDepth-1, from,to,fieldName,checker);
    }


}
