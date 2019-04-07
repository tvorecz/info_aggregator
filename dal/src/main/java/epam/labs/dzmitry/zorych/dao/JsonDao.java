package epam.labs.dzmitry.zorych.dao;

import epam.labs.dzmitry.zorych.apimanager.JsonApiManagerException;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entitybuilder.DataSourceException;
import epam.labs.dzmitry.zorych.urlbuilder.BadUrlApiException;

public interface JsonDao<T> {
    T getFor(Location location) throws JsonApiManagerException, BadUrlApiException, DataSourceException;
}
