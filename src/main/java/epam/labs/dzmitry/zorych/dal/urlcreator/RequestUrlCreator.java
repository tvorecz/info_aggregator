package epam.labs.dzmitry.zorych.dal.urlcreator;

import epam.labs.dzmitry.zorych.entity.Location;

import java.net.URL;

public interface RequestUrlCreator {
    URL createURL(Location location) throws BadUrlApiException;
}
