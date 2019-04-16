package epam.labs.dzmitry.zorych.dal.entitycreator.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import epam.labs.dzmitry.zorych.dal.entitycreator.DataSourceException;
import epam.labs.dzmitry.zorych.dal.entitycreator.EntityCreator;
import epam.labs.dzmitry.zorych.entity.Currency;
import epam.labs.dzmitry.zorych.entity.Location;

import java.time.LocalDate;

/**
 * Create Location from json-string, receiving from OpenCageData
 */
public class LocationCreatorFromOpencagedata implements EntityCreator<Location> {
    private final static String[] typesOfObjects = {"city", "town", "village", "state"};

    @Override
    public Location create(String json) throws DataSourceException {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonRoot = jsonParser.parse(json);
        JsonObject jsonObject = jsonRoot.getAsJsonObject();

        int code = jsonObject.get("status").getAsJsonObject().get("code").getAsInt();

        if (code != 200) {
            String message = jsonObject.get("status").getAsJsonObject().get("message").getAsString();
            throw new DataSourceException(message, code);
        }

        Location location = new Location();

        if (jsonObject.getAsJsonArray("results").size() > 0) {
            location.setLatitude(jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("geometry").getAsJsonObject().get(
                    "lat").getAsBigDecimal());
            location.setLongitude(jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("geometry").getAsJsonObject().get(
                    "lng").getAsBigDecimal());

            JsonElement annotations = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("annotations");

            if (annotations != null) {
                JsonElement currency = annotations.getAsJsonObject().get("currency");
                if(currency != null) {
                    location.setCurrencyCode(Currency.valueOf(currency.getAsJsonObject().get("iso_code").getAsString()));
                } else {
                    location.setCurrencyCode(null);
                }

                location.setTimeZone(annotations.getAsJsonObject().get(
                        "timezone").getAsJsonObject().get("name").getAsString());
                location.setDate(LocalDate.now());
            } else {
                throw new DataSourceException("Indeterminate location", 404);
            }

            JsonElement components = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("components");

            if (components != null) {
                JsonElement city = null;

                for (String typeOfObject : typesOfObjects) {
                    city = components.getAsJsonObject().get(typeOfObject);

                    if (city != null) {
                        break;
                    }
                }

                if (city == null) {
                    location.setCity("");
                } else {
                    location.setCity(city.getAsString());
                }

                if (components.getAsJsonObject().get("country") != null) {
                    location.setCountry(components.getAsJsonObject().get("country").getAsString());
                } else {
                    location.setCountry("");
                }

                if (components.getAsJsonObject().get("continent") != null) {
                    location.setContinent(components.getAsJsonObject().get("continent").getAsString());
                } else {
                    location.setContinent("");
                }
            }
        } else {
            throw new DataSourceException("Indeterminate location", 404);
        }

        return location;
    }
}
