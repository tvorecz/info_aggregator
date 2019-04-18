package epam.labs.dzmitry.zorych.entitybuilder;

import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entity.Weather;

public class WeatherBuilder {
    
    private Location location = new LocationBuilder().build();
    private String summary = "Sunny";
    private String icon = "sun";
    private double temperature = 23.;
    private double apparentTemperature= 25.;
    private double pressure = 133.55;
    private double windSpeed = 5.5;
    private double windGust = 5.5;

    public WeatherBuilder withLocation(Location value) {
        location = value;

        return this;
    }

    public WeatherBuilder withSummary(String value) {
        summary = value;

        return this;
    }

    public WeatherBuilder withIcon(String value) {
        icon = value;

        return this;
    }

    public WeatherBuilder withTemperature(double value) {
        temperature = value;

        return this;
    }

    public WeatherBuilder withApparentTemperature(double value) {
        apparentTemperature = value;

        return this;
    }

    public WeatherBuilder withpPressure(double value) {
        pressure = value;

        return this;
    }

    public WeatherBuilder withWindSpeed(double value) {
        windSpeed = value;

        return this;
    }

    public WeatherBuilder withWindGust(double value) {
        windGust = value;

        return this;
    }


    public Weather build() {
        Weather weather = new Weather();

        weather.setLocation(location);
        weather.setSummary(summary);
        weather.setIcon(icon);
        weather.setTemperature(temperature);
        weather.setApparentTemperature(apparentTemperature);
        weather.setPressure(pressure);
        weather.setWindSpeed(windSpeed);
        weather.setWindGust(windGust);

        return weather;
    }
}
