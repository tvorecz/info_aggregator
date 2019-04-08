package epam.labs.dzmitry.zorych.service.impl;

import epam.labs.dzmitry.zorych.dao.JsonDao;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.service.CommonService;

public class CommonServiceImpl<T> implements CommonService<T> {
    private JsonDao<T> dao;

    @Override
    public T get(Location location) {
        return dao.getFor(location);
    }
}
