package epam.labs.dzmitry.zorych.entitybuilder.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import epam.labs.dzmitry.zorych.entity.Weather;
import epam.labs.dzmitry.zorych.entitybuilder.DataSourceException;
import epam.labs.dzmitry.zorych.entitybuilder.EntityBuilder;

public class WeatherBuilder implements EntityBuilder<Weather> {

    @Override
    public Weather build(String json) throws DataSourceException {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonRoot = jsonParser.parse(json);
        JsonObject jsonObject = jsonRoot.getAsJsonObject();

        JsonElement error = jsonObject.get("error");

        if(error != null) {
            JsonElement status = jsonObject.get("code");
            throw new DataSourceException("Code: " + status.getAsInt() + "\nMessage: " + error.getAsString());
        }

        Weather weather = new Weather();

        weather.setSummary(jsonObject.get("currently").getAsJsonObject().get("summary").getAsString());
        weather.setIcon(jsonObject.get("currently").getAsJsonObject().get("icon").getAsString());
        weather.setTemperature(jsonObject.get("currently").getAsJsonObject().get("temperature").getAsDouble());
        weather.setApparentTemperature(jsonObject.get("currently").getAsJsonObject().get("apparentTemperature").getAsDouble());
        weather.setPressure(jsonObject.get("currently").getAsJsonObject().get("pressure").getAsDouble());
        weather.setWindSpeed(jsonObject.get("currently").getAsJsonObject().get("windSpeed").getAsDouble());
        weather.setWindGust(jsonObject.get("currently").getAsJsonObject().get("windGust").getAsDouble());

        return weather;
    }
}
