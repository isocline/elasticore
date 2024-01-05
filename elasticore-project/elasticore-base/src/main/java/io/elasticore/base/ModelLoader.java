package io.elasticore.base;

import io.elasticore.base.model.ECoreModel;

import java.util.List;

public interface ModelLoader {

    ECoreModel load(String domainName);


    List<String> getDomainNameList();


}
