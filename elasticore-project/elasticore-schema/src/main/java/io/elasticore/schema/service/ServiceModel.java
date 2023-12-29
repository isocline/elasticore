package io.elasticore.schema.service;

import lombok.AllArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
public final class ServiceModel {

    private final String domain;
    private final String name;

    private final Map<String, Operation> operationMap = new LinkedHashMap();


    void addOperation(Operation operation) {
        operationMap.put(operation.getName(), operation);
    }

    public int getOperationSize() {
        return operationMap.size();
    }

    public Operation[] getOperationArray() {
        return (Operation[]) operationMap.values().toArray();
    }

    public Operation getOpertaionByName(String name) {
        return operationMap.get(name);
    }


}
