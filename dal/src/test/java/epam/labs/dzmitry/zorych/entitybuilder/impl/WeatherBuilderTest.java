package epam.labs.dzmitry.zorych.entitybuilder.impl;

import epam.labs.dzmitry.zorych.entity.Weather;
import epam.labs.dzmitry.zorych.entitybuilder.DataSourceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherBuilderTest {
    private final static String JSON = "{\"latitude\":53.9045,\"longitude\":27.5615,\"timezone\":\"Europe/Minsk\"," +
                                       "\"currently\":{\"time\":1554583788,\"summary\":\"Mostly Cloudy\"," +
                                       "\"icon\":\"partly-cloudy-night\",\"precipIntensity\":0.0003," +
                                       "\"precipProbability\":0.01,\"precipType\":\"rain\",\"temperature\":41.29," +
                                       "\"apparentTemperature\":33.77,\"dewPoint\":28.72,\"humidity\":0.61," +
                                       "\"pressure\":1018.28,\"windSpeed\":14.08,\"windGust\":27.96," +
                                       "\"windBearing\":115,\"cloudCover\":0.67,\"uvIndex\":0,\"visibility\":6.22," +
                                       "\"ozone\":378.28},\"offset\":3}";

    private final static String JSON_ERROR = "{\"code\":400,\"error\":\"The given location is invalid.\"}";

    private static Weather exceptedWeather;

    @BeforeClass
    public static void fillData() {
        exceptedWeather = new Weather();

        exceptedWeather.setSummary("Mostly Cloudy");
        exceptedWeather.setIcon("partly-cloudy-night");
        exceptedWeather.setTemperature(41.29);
        exceptedWeather.setApparentTemperature(33.77);
        exceptedWeather.setPressure(1018.28);
        exceptedWeather.setWindSpeed(14.08);
        exceptedWeather.setWindGust(27.96);
    }

    @Test
    public void testWeatherBuilder() throws DataSourceException {
        WeatherBuilder weatherBuilder = new WeatherBuilder();
        Weather actualWeather = weatherBuilder.build(JSON);

        Assert.assertEquals(actualWeather, exceptedWeather);
    }

    @Test(expected = DataSourceException.class)
    public void testWeatherBuilderException() throws DataSourceException {
        WeatherBuilder weatherBuilder = new WeatherBuilder();
        weatherBuilder.build(JSON_ERROR);
    }

}
