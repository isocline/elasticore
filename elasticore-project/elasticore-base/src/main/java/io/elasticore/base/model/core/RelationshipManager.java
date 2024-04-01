package io.elasticore.base.model.core;

import io.elasticore.base.model.relation.ModelRelationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RelationshipManager {

    private List<ModelRelationship> relationshipList;
    private Set<ModelRelationship> relationshipSet;

    private RelationshipManager() {
        this.relationshipList = new ArrayList<>();
        this.relationshipSet = new HashSet<>();
    }

    synchronized void reset() {
        this.relationshipList.clear();
        this.relationshipSet.clear();
    }

    private static class Holder {
        private static final RelationshipManager INSTANCE = new RelationshipManager();
    }

    public static RelationshipManager getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void addRelationship(ModelRelationship relationship) {
        if (!relationshipSet.contains(relationship)) {
            this.relationshipList.add(relationship);
            this.relationshipSet.add(relationship);
        }
    }

    public List<ModelRelationship> getRelationshipsForToObj(String name) {
        return relationshipList.stream()
                .filter(r -> r.getToName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<ModelRelationship> getRelationshipsForFromObj(String name) {
        return relationshipList.stream()
                .filter(r -> r.getFromName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}
