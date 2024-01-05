package io.elasticore.schema.service;

import io.elasticore.schema.core.AbstractReplaceableModel;
import lombok.AllArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;


public final class ServiceModel extends AbstractReplaceableModel<ServiceModel> {


    private final Map<String, Operation> operationMap = new LinkedHashMap();

    ServiceModel(String domain, String name) {
        super(TYPE_OPERTATION, domain, name);
    }


    void addOperation(Operation operation) {
        operationMap.put(operation.getName(), operation);
    }

    public int getOperationSize() {
        return operationMap.size();
    }

    public Operation[] getOperationArray() {
        return operationMap.values().toArray(new Operation[0]);
    }

    public Operation getOpertaionByName(String name) {
        return operationMap.get(name);
    }


}
