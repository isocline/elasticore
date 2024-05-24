package io.elasticore.base.model.core;

import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;

import java.util.*;
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

    private static Map<String, RelationshipManager> relationshipManagerMap = new HashMap<>();


    public static RelationshipManager getInstance(String domainId) {
        RelationshipManager instance = relationshipManagerMap.get(domainId);
        if(instance==null) {
            instance = new RelationshipManager();
            relationshipManagerMap.put(domainId, instance);
        }
        return instance;
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


    public List<ModelRelationship> getRelationshipList(RelationType relationType) {
        return relationshipList.stream()
                .filter(r -> r.getRelationType()==relationType)
                .collect(Collectors.toList());
    }

}
