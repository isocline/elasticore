package io.elasticore.springboot3.http;

import java.util.List;
import java.util.Map;

/**
 * An interface for handling HTTP authentication processing.
 * This interface provides a method to process authentication-related operations
 * based on the given input object and HTTP headers.
 */
public interface HttpAuthProvider {

    /**
     * Processes authentication or authorization logic based on the provided input object
     * and a list of HTTP header maps.
     *
     * @param inputObject The user-defined input object for authentication processing.
     * @param headerMapList A list of maps containing HTTP headers as key-value pairs.
     * @return A user-defined object containing authentication results or related data.
     */
    Object process(Object inputObject, List<Map<String, String>> headerMapList);

}
