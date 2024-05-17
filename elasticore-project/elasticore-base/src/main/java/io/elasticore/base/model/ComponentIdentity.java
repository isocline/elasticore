package io.elasticore.base.model;

public interface ComponentIdentity {

    String getDomainId();

    String getHashId();

    String getId();

    String getName();

    ComponentDesc getInfo();
}
