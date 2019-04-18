package epam.labs.dzmitry.zorych.entitybuilder;

import epam.labs.dzmitry.zorych.entity.Currency;
import epam.labs.dzmitry.zorych.entity.Location;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LocationBuilder {
    private LocalDate date = LocalDate.of(2019, 04, 04);
    private BigDecimal latitude = BigDecimal.valueOf(50.0);
    private BigDecimal longitude = BigDecimal.valueOf(50.0);
    private String timeZone = "Minsk/Belarus";
    private Currency currencyCode = Currency.BYN;
    private String continent = "Europe";
    private String country = "Belarus";
    private String city = "Minsk";

    public LocationBuilder withDate(int year, int month, int day) {
        date = LocalDate.of(year, month, day);

        return this;
    }

    public LocationBuilder withLatitude(double value) {
        latitude = BigDecimal.valueOf(value);

        return this;
    }

    public LocationBuilder withLongitude(double value) {
        longitude = BigDecimal.valueOf(value);

        return this;
    }

    public LocationBuilder withLatitudeAndLongitude(double value) {
        latitude = BigDecimal.valueOf(value);
        longitude = BigDecimal.valueOf(value);

        return this;
    }

    public LocationBuilder withTimeZone(String value) {
        timeZone = value;

        return this;
    }

    public LocationBuilder withCurrency(Currency value) {
        currencyCode = value;

        return this;
    }

    public LocationBuilder withContinent(String value) {
        continent = value;

        return this;
    }

    public LocationBuilder withCountry(String value) {
        country = value;

        return this;
    }

    public LocationBuilder withCity(String value) {
        city = value;

        return this;
    }

    public Location build() {
        Location location = new Location();

        location.setDate(date);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setTimeZone(timeZone);
        location.setCurrencyCode(currencyCode);
        location.setContinent(continent);
        location.setCountry(country);
        location.setCity(city);

        return  location;
    }
}
