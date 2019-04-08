package epam.labs.dzmitry.zorych.apimanager;

import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.urlcreator.BadUrlApiException;

public interface JsonApiManager {
    String getRequestFor(Location location) throws BadUrlApiException, JsonApiManagerException;
}
