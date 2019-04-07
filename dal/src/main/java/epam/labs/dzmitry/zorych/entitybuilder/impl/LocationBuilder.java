package epam.labs.dzmitry.zorych.entitybuilder.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entitybuilder.DataSourceException;
import epam.labs.dzmitry.zorych.entitybuilder.EntityBuilder;

public class LocationBuilder implements EntityBuilder<Location> {

    @Override
    public Location build(String json) throws DataSourceException {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonRoot = jsonParser.parse(json);
        JsonObject jsonObject = jsonRoot.getAsJsonObject();

        int code = jsonObject.get("status").getAsJsonObject().get("code").getAsInt();

        if(code != 200) {
            String message = jsonObject.get("status").getAsJsonObject().get("message").getAsString();
            throw new DataSourceException("Status: " + code + "\nMessage: " + message);
        }

        Location location = new Location();

        location.setLatitude(jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("lat").getAsBigDecimal());
        location.setLongitude(jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("lng").getAsBigDecimal());
        location.setCity(jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("components").getAsJsonObject().get("city").getAsString());
        location.setCountry(jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("components").getAsJsonObject().get("country").getAsString());
        location.setContinent(jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("components").getAsJsonObject().get("continent").getAsString());
        location.setIsoCode(jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("annotations").getAsJsonObject().get(
                "currency").getAsJsonObject().get("iso_code").getAsString());
        location.setTimeZone(jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("annotations").getAsJsonObject().get(
                "timezone").getAsJsonObject().get("name").getAsString());

        return location;
    }
}
