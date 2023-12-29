package io.isocline.elasticore.schema.entity;


import lombok.Getter;

import java.util.Properties;

@Getter
public final class ColumnConfig {

    ColumnConfig(Properties properties) {

        if(properties.contains("size")) {
            String val = properties.getProperty("size");
            this.size = Integer.parseInt(val);
        }

        if(properties.contains("minLength")) {
            String val = properties.getProperty("minLength");
            this.minLength = Integer.parseInt(val);
        }

        if(properties.contains("maxLength")) {
            String val = properties.getProperty("maxLength");
            this.maxLength = Integer.parseInt(val);
        }


        if(properties.contains("min")) {
            String val = properties.getProperty("min");
            this.min = Double.parseDouble(val);
        }

        if(properties.contains("max")) {
            String val = properties.getProperty("max");
            this.max = Double.parseDouble(val);
        }
    }

    private boolean isPrimary;

    private int size;

    private int minLength;

    private int maxLength;


    private double min;

    private double max;

    private String keyGenerator;

}
