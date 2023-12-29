package io.isocline.elasticore.schema.service;

import java.util.ArrayList;
import java.util.List;

public final class ServiceModel {

    private final List<Operation> operationList = new ArrayList<>();


    void addOperation(Operation Operation) {
        this.operationList.add(Operation);
    }

    public int getOperationSize() {
        return this.operationList.size();
    }

    public Operation getOperationByIdx(int idx) {
        return this.operationList.get(idx);
    }


}
