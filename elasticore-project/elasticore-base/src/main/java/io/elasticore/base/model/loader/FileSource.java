package io.elasticore.base.model.loader;


import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class FileSource {

    private String filepath;

    private Map infoMap;

    private Throwable error;
}
