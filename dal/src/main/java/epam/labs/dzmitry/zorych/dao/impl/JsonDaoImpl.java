package epam.labs.dzmitry.zorych.dao.impl;

import epam.labs.dzmitry.zorych.apimanager.JsonApiManager;
import epam.labs.dzmitry.zorych.apimanager.JsonApiManagerException;
import epam.labs.dzmitry.zorych.dao.JsonDao;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entitycreator.DataSourceException;
import epam.labs.dzmitry.zorych.entitycreator.EntityCreator;
import epam.labs.dzmitry.zorych.urlcreator.BadUrlApiException;

/**
 * Provide access to rest-providers
 * @param <T> Type of entity
 */
public class JsonDaoImpl<T> implements JsonDao<T> {
    private JsonApiManager jsonApiManager;
    private EntityCreator<T> entityCreator;

    /**
     * Create instance of dao
     * @param jsonApiManager Manager for access to rest-provider
     * @param entityCreator Creator current entity
     */
    public JsonDaoImpl(JsonApiManager jsonApiManager, EntityCreator<T> entityCreator) {
        this.jsonApiManager = jsonApiManager;
        this.entityCreator = entityCreator;
    }

    /**
     * Create entity for current Location
     * @param location Location for search
     * @return Found entity
     * @throws JsonApiManagerException
     * @throws BadUrlApiException
     * @throws DataSourceException
     */
    @Override
    public T getFor(Location location) throws JsonApiManagerException, BadUrlApiException, DataSourceException {
        String request = jsonApiManager.getResponseFor(location);
        T entity = entityCreator.create(request);

        return entity;
    }
}
