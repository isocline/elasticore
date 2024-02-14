package io.elasticore.base.util;


/**
 *
 */
public class StringList {

    private StringBuilder stringBuilder;



    private String prefix;

    private String delimeter = ",";

    private boolean isStart= false;

    private StringList(String delimeter) {
        this.stringBuilder = new StringBuilder();

        this.delimeter = delimeter;
    }

    public static StringList create() {
        return new StringList(",");
    }

    public static StringList create(String delimeter) {
        return new StringList(delimeter);
    }

    public StringList append(String prefix) {
        this.stringBuilder.append(prefix);
        return this;
    }

    public StringList add(Object obj) {
        if(!isStart) isStart=true;
        else {
            this.stringBuilder.append(delimeter);
        }
        if(obj==null)
            this.stringBuilder.append("null");
        else
            this.stringBuilder.append(obj.toString());

        return this;
    }

    @Override
    public String toString() {
        return this.stringBuilder.toString();
    }
}
