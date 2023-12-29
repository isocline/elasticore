package io.elasticore.schema.service;


import io.elasticore.schema.ExecutableCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class Script implements ExecutableCode {

    private final ScriptType scriptType;

    private final String scriptCode;


    @Override
    public String getId() {
        return null;
    }
}
