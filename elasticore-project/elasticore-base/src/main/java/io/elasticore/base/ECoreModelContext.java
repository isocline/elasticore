package io.elasticore.base;

import io.elasticore.base.exeption.ProcessException;

/**
 * Represents the context of an ECore model, providing access to model domains
 * and facilitating the publication of model components.
 * This interface defines methods to retrieve domain names, access specific model domains,
 * and publish model components using a given code publisher.
 */
public interface ECoreModelContext {

    /**
     * Retrieves the names of all domains within the ECore model context.
     *
     * @return An array of domain names.
     */
    String[] getDomanNames();

    /**
     * Retrieves the default model domain associated with this context.
     * If multiple domains exist, this method may return the primary or default domain.
     *
     * @return The default ModelDomain instance.
     */
    ModelDomain getDomain();

    /**
     * Retrieves a specific model domain by its name.
     *
     * @param name The name of the domain to retrieve.
     * @return The ModelDomain instance with the specified name, or null if no such domain exists.
     */
    ModelDomain getDomain(String name);

    /**
     * Publishes the model components of the current context using the specified code publisher.
     * This method orchestrates the publishing process, delegating the publication of individual
     * model components to the provided publisher.
     *
     * @param publisher The CodePublisher instance to use for publishing model components.
     * @return True if the publication was successful; False otherwise.
     * @throws ProcessException If an error occurs during the publishing process.
     */
    boolean publish(CodePublisher publisher) throws ProcessException;
}
