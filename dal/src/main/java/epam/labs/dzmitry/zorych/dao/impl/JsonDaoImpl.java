package epam.labs.dzmitry.zorych.dao.impl;

import epam.labs.dzmitry.zorych.apimanager.JsonApiManager;
import epam.labs.dzmitry.zorych.apimanager.JsonApiManagerException;
import epam.labs.dzmitry.zorych.dao.JsonDao;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entitybuilder.EntityBuilder;
import epam.labs.dzmitry.zorych.urlbuilder.BadUrlApiException;

public class JsonDaoImpl<T> implements JsonDao<T> {
    private JsonApiManager jsonApiManager;
    private EntityBuilder<T> entityBuilder;

    public JsonDaoImpl(JsonApiManager jsonApiManager, EntityBuilder<T> entityBuilder) {
        this.jsonApiManager = jsonApiManager;
        this.entityBuilder = entityBuilder;
    }

    @Override
    public T getFor(Location location) throws JsonApiManagerException, BadUrlApiException {
        String request = jsonApiManager.getRequestFor(location);
        T entity = entityBuilder.build(request);
        return entity;
    }
}
