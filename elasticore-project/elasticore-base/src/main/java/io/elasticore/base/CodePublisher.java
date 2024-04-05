package io.elasticore.base;

import io.elasticore.base.exeption.ProcessException;
import io.elasticore.base.model.ModelComponent;

/**
 * Defines the operations for publishing code based on model components.
 * This interface provides methods for setting and getting a source code writer factory,
 * retrieving the model context, publishing the model components to source code,
 * and handling errors during the publishing process.
 *
 * @author Richard Kim
 */
public interface CodePublisher {

    /**
     * Sets the factory for creating source code writers.
     *
     * @param srcCodeWriterFactory The factory to set.
     */
    void setSrcCodeWriterFactory(SrcCodeWriterFactory srcCodeWriterFactory);

    /**
     * Gets the factory for creating source code writers.
     *
     * @return The current source code writer factory.
     */
    SrcCodeWriterFactory getSrcCodeWriterFactory();

    /**
     * Retrieves the model context associated with this publisher.
     *
     * @return The current ECoreModelContext.
     */
    ECoreModelContext getECoreModelContext();

    /**
     * Publishes the model components to source code based on the provided context.
     *
     * @param ctx The context containing model information for publishing.
     * @return True if the publishing was successful; False otherwise.
     * @throws ProcessException If an error occurs during the publishing process.
     */
    boolean publish(ECoreModelContext ctx) throws ProcessException;

    /**
     * Handles errors that occur during the publishing process.
     *
     * @param modelComponent The model component that was being published when the error occurred.
     * @param e              The exception that was thrown.
     */
    void errorOnPublish(ModelComponent modelComponent, Throwable e);
}
