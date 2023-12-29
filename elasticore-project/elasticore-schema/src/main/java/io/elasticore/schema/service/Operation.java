package io.elasticore.schema.service;

import io.elasticore.schema.ExecutableCode;
import io.elasticore.schema.Replaceable;
import io.elasticore.schema.core.BaseInfo;
import io.elasticore.schema.io.IoModel;
import lombok.AllArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;


@AllArgsConstructor
public final class Operation {

    private final String name;

    private final BaseInfo info;

    private final OperationConfig config;

    private final Replaceable<IoModel> input;
    private final Replaceable<IoModel> output;

    private final Map<String, ExecutableCode> executableMap = new LinkedHashMap<>();


    private void addScript(ExecutableCode executableCode) {
        executableMap.put(executableCode.getId(), executableCode);
    }

    public int getScriptSize() {
        return executableMap.size();
    }

    public ExecutableCode[] getExecutableArray() {
        return (ExecutableCode[]) executableMap.values().toArray();
    }


    public String getName() {
        return this.name;
    }

    public IoModel getInputModel() {
        return this.input.getObject();
    }

    public IoModel getOutputModel() {
        return this.output.getObject();
    }
}
