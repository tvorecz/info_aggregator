package epam.labs.dzmitry.zorych.urlbuilder.impl;

import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.urlbuilder.BadUrlApiException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

public class JsonRequestUrlBuilderByLocationTest {
    private static Location locationFirst;
    private static Location locationSecond;
    private static Location locationThird;

    private final static String key = "5a17e15699764435a31deeb97a9fd13e";

    private final static String locationUrl = "https://api.opencagedata.com/geocode/v1/json?key=${key}&q=${latitude}+${longitude}&pretty=1";
    private final static String expectedLocationUrl = "https://api.opencagedata.com/geocode/v1/json?key=5a17e15699764435a31deeb97a9fd13e&q=40.2311+-32.12312&pretty=1";

    private final static String weatherUrl = "https://api.darksky.net/forecast/${key}/${latitude},${longitude}";
    private final static String expectedWeatherUrl = "https://api.darksky.net/forecast/5a17e15699764435a31deeb97a9fd13e/40.2311,-32.12312";

    private final static String currencyUrl = "http://data.fixer.io/api/latest?access_key=${key}&format=1";
    private final static String expectedCurrencyUrl = "http://data.fixer.io/api/latest?access_key=5a17e15699764435a31deeb97a9fd13e&format=1";

    private final static String badUrl = "koko.by";

    @BeforeClass
    public static void prepareData() {
        locationFirst = new Location();
        locationFirst.setLatitude(BigDecimal.valueOf(40.2311));
        locationFirst.setLongitude(BigDecimal.valueOf(-32.12312));

        locationSecond = new Location();
        locationSecond.setIsoCode("BYN");

        locationThird = new Location();
        locationThird.setLatitude(BigDecimal.valueOf(40.2311));
        locationThird.setLongitude(BigDecimal.valueOf(-32.12312));
        locationThird.setIsoCode("BYN");
    }

    @Test
    public void testLocationUrl() throws BadUrlApiException {
        JsonRequestUrlBuilderByLocation builder = new JsonRequestUrlBuilderByLocation(locationUrl, key);

        String actual = builder.buildURL(locationFirst).toString();

        Assert.assertEquals(actual, expectedLocationUrl);
    }

    @Test
    public void testWeatherUrl() throws BadUrlApiException {
        JsonRequestUrlBuilderByLocation builder = new JsonRequestUrlBuilderByLocation(weatherUrl, key);

        String actual = builder.buildURL(locationFirst).toString();

        Assert.assertEquals(actual, expectedWeatherUrl);
    }

    @Test
    public void testCurrencyUrl() throws BadUrlApiException {
        JsonRequestUrlBuilderByLocation builder = new JsonRequestUrlBuilderByLocation(currencyUrl, key);

        String actual = builder.buildURL(locationSecond).toString();

        Assert.assertEquals(actual, expectedCurrencyUrl);
    }

    @Test(expected = BadUrlApiException.class)
    public void testBadUrl() throws BadUrlApiException {
        JsonRequestUrlBuilderByLocation builder = new JsonRequestUrlBuilderByLocation(badUrl, key);

        String actual = builder.buildURL(locationThird).toString();
    }
}
