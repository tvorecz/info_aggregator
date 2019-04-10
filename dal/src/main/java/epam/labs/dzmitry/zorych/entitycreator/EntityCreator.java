package epam.labs.dzmitry.zorych.entitycreator;

/**
 * Create entity from json-string, receiving from rest-provider
 */
public interface EntityCreator<T> {
    /**
     * Return entity created from json-string, receiving from rest-provider
     * @param json String with json-content
     * @return Current entity
     * @throws DataSourceException Generate when json-data is invalid, contains not enough information or server-error
     */
    T create(String json) throws DataSourceException;
}
