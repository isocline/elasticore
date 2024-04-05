package io.elasticore.base;

import io.elasticore.base.model.ECoreModel;
import java.util.List;

/**
 * Provides the functionality to load ECore models and retrieve a list of available domain names.
 * This interface is responsible for abstracting the mechanism by which models are loaded,
 * allowing for flexibility in how models are defined and accessed.
 */
public interface ModelLoader {

    /**
     * Loads the ECore model associated with a specific domain name.
     * This method is responsible for initializing and returning the model based on the given domain.
     * It should handle any necessary parsing or processing to construct the model from its definition.
     *
     * @param domainName The name of the domain for which the model should be loaded.
     * @return An instance of {@link ECoreModel} that represents the loaded model for the specified domain.
     */
    ECoreModel load(String domainName);

    /**
     * Retrieves a list of domain names for which models can be loaded.
     * This method provides insight into the different domains available within the ECore model context,
     * allowing clients to query and load models as needed.
     *
     * @return A list of strings, each representing the name of a domain available for loading.
     */
    List<String> getDomainNameList();
}
