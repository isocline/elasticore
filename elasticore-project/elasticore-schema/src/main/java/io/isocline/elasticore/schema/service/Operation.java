package io.isocline.elasticore.schema.service;

import io.isocline.elasticore.schema.repository.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public final class Operation {



    private final List<Script> scriptList = new ArrayList<>();

    private final List<Query> queryList = new ArrayList<>();


    private


    void addScript(Script script) {
        this.scriptList.add(script);
    }

    public int getScriptSize() {
        return this.scriptList.size();
    }

    public Script getScriptByIndex(int idx) {
        return this.scriptList.get(idx);
    }


    void addQuery(Query query) {
        this.queryList.add(query);
    }
}
