package epam.labs.dzmitry.zorych.dao.impl;

import epam.labs.dzmitry.zorych.apimanager.JsonApiManager;
import epam.labs.dzmitry.zorych.apimanager.JsonApiManagerException;
import epam.labs.dzmitry.zorych.dao.JsonDao;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entitycreator.DataSourceException;
import epam.labs.dzmitry.zorych.entitycreator.EntityCreator;
import epam.labs.dzmitry.zorych.urlcreator.BadUrlApiException;

public class JsonDaoImpl<T> implements JsonDao<T> {
    private JsonApiManager jsonApiManager;
    private EntityCreator<T> entityCreator;

    public JsonDaoImpl(JsonApiManager jsonApiManager, EntityCreator<T> entityCreator) {
        this.jsonApiManager = jsonApiManager;
        this.entityCreator = entityCreator;
    }

    @Override
    public T getFor(Location location) throws JsonApiManagerException, BadUrlApiException, DataSourceException {
        String request = jsonApiManager.getRequestFor(location);
        T entity = entityCreator.create(request);

        return entity;
    }
}
