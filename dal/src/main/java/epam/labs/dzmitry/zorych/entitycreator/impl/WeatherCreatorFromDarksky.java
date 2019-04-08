package epam.labs.dzmitry.zorych.entitycreator.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import epam.labs.dzmitry.zorych.entity.Weather;
import epam.labs.dzmitry.zorych.entitycreator.DataSourceException;
import epam.labs.dzmitry.zorych.entitycreator.EntityCreator;

public class WeatherCreatorFromDarksky implements EntityCreator<Weather> {

    @Override
    public Weather create(String json) throws DataSourceException {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonRoot = jsonParser.parse(json);
        JsonObject jsonObject = jsonRoot.getAsJsonObject();

        JsonElement error = jsonObject.get("error");

        if(error != null) {
            JsonElement status = jsonObject.get("code");
            throw new DataSourceException(error.getAsString(), status.getAsInt());
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
