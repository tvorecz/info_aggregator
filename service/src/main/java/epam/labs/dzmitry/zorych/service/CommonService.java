package epam.labs.dzmitry.zorych.service;

import epam.labs.dzmitry.zorych.entity.Location;

public interface CommonService<T> {
    T get(Location location);
}
