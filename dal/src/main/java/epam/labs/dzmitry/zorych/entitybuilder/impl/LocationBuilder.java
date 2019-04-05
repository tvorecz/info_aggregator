package epam.labs.dzmitry.zorych.entitybuilder.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entitybuilder.EntityBuilder;

public class LocationBuilder implements EntityBuilder<Location> {

    @Override
    public Location build(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonRoot = jsonParser.parse(json);



        return null;
    }
}
