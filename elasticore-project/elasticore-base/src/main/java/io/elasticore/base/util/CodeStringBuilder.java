package io.elasticore.base.util;


/**
 * <pre>
 *     CodeStringBulder cb = new CodeStringBulder("{"});
 *     sb.l("class BaseMode ")
 *     .s()
 *     .l("string update")
 *     .l("string update")
 *     .e();
 *
 *
 *
 *
 * </pre>
 *
 *
 */
public class CodeStringBuilder {

    private StringBuilder sb = new StringBuilder();


    private final static String NEWLINE = "\n";

    private int blockDepth = 0;

    private String blockStartText = "";

    private String blockEndText = "";

    private String crntBlockStartText = null;

    private String crntBlockSEndText = null;

    private String indent = "";

    public CodeStringBuilder(){
    }

    public CodeStringBuilder(String blockStartText, String blockEndText) {
        this.blockStartText = blockStartText;
        this.blockEndText = blockEndText;
    }


    private void newline(){
        sb.append(NEWLINE);
    }

    private void indent() {
        sb.append(indent);
    }

    public CodeStringBuilder block() {
        return block(null);
    }

    public CodeStringBuilder end() {
        return end(null);
    }

    public CodeStringBuilder block(String blockMark) {
        if(blockMark==null ) {
            crntBlockStartText = blockStartText;
        }else {
            crntBlockStartText = blockMark;
        }
        sb.append(crntBlockStartText);
        blockDepth++;

        StringBuilder sbb = new StringBuilder();
        for(int i=0;i<blockDepth;i++) {
            sbb.append("  ");
        }
        indent = sbb.toString();
        return this;
    }

    public CodeStringBuilder end(String blockMark) {
        if(blockMark==null ) {
            crntBlockSEndText = blockEndText;
        }else {
            crntBlockSEndText = blockMark;
        }
        blockDepth--;
        if(blockDepth<0)
            throw new IllegalStateException();

        StringBuilder sbb = new StringBuilder();
        for(int i=0;i<blockDepth;i++) {
            sbb.append("  ");
        }
        indent = sbb.toString();

        this.newline();
        this.indent();
        sb.append(crntBlockSEndText);

        return this;
    }



    public CodeStringBuilder line(String code) {
        newline();
        indent();
        sb.append(code);
        return this;
    }


    public CodeStringBuilder line(String template, Object... parameter) {
        String code = String.format(template,parameter);
        line(code);
        return this;
    }




    public String toString(){
        return sb.toString();
    }


    public static void main(String[] args) {
        CodeStringBuilder cb = new CodeStringBuilder("{","}");

        cb
                .line("start")
                .block("")
                .line("class test")
                .block()
                .line("tet 1")
                .line("tet 2")
                .line("%S %S", "123", 2342)
                .end()
                .end("");


        System.out.println(cb.toString());
    }

}
