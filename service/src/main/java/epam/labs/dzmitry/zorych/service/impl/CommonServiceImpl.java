package epam.labs.dzmitry.zorych.service.impl;

import epam.labs.dzmitry.zorych.apimanager.JsonApiManagerException;
import epam.labs.dzmitry.zorych.dao.JsonDao;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entitycreator.DataSourceException;
import epam.labs.dzmitry.zorych.service.CannotGetDataException;
import epam.labs.dzmitry.zorych.service.CommonService;
import epam.labs.dzmitry.zorych.urlcreator.BadUrlApiException;

/**
 * Specific service for accessing to data layer
 * @param <T> Entity for receiving
 */
public class CommonServiceImpl<T> implements CommonService<T> {
    private JsonDao<T> dao;

    public CommonServiceImpl(JsonDao<T> dao) {
        this.dao = dao;
    }

    /**
     * Get entity according current location
     * @param location Location for searching
     * @return
     * @throws CannotGetDataException Generates when data receiving is failed
     */
    @Override
    public T get(Location location) throws CannotGetDataException {
        T result = null;

        try {
            result = dao.getFor(location);
        } catch (JsonApiManagerException e) {
            createException(e, e.getCode());
        } catch (BadUrlApiException e) {
            createException(e, e.getCode());
        } catch (DataSourceException e) {
            createException(e, e.getCode());
        }

        return result;
    }

    private void createException(Exception e, int code) throws CannotGetDataException {
        if(code != 0) {
            throw new CannotGetDataException("Cannot receive data", e, code);
        } else {
            throw new CannotGetDataException("Cannot receive data", e, 400);
        }
    }
}
