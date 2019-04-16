package epam.labs.dzmitry.zorych.dal.apimanager;

import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.dal.urlcreator.BadUrlApiException;

public interface JsonApiManager {
    String getResponseFor(Location location) throws BadUrlApiException, JsonApiManagerException;
}
