package epam.labs.dzmitry.zorych.urlbuilder;

import epam.labs.dzmitry.zorych.entity.Location;

import java.net.URL;

public interface RequestUrlBuilder {
    URL buildURL(Location location) throws BadUrlApiException;
}
