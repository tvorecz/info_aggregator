package epam.labs.dzmitry.zorych.dal.dao;

import epam.labs.dzmitry.zorych.dal.apimanager.JsonApiManagerException;
import epam.labs.dzmitry.zorych.dal.entitycreator.DataSourceException;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.dal.urlcreator.BadUrlApiException;

public interface JsonDao<T> {
    T getFor(Location location) throws JsonApiManagerException, BadUrlApiException, DataSourceException;
}
