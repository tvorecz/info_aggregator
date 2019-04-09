package epam.labs.dzmitry.zorych.service.impl;

import epam.labs.dzmitry.zorych.apimanager.JsonApiManagerException;
import epam.labs.dzmitry.zorych.dao.JsonDao;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entitycreator.DataSourceException;
import epam.labs.dzmitry.zorych.service.CannotGetDataException;
import epam.labs.dzmitry.zorych.service.CommonService;
import epam.labs.dzmitry.zorych.urlcreator.BadUrlApiException;

public class CommonServiceImpl<T> implements CommonService<T> {
    private JsonDao<T> dao;

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
