package epam.labs.dzmitry.zorych.dal.urlcreator.impl;

import epam.labs.dzmitry.zorych.dal.urlcreator.BadUrlApiException;
import epam.labs.dzmitry.zorych.entity.Currency;
import epam.labs.dzmitry.zorych.entity.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class JsonRequestUrlCreatorByLocationTest {
    private Location locationFirst;
    private Location locationSecond;
    private Location locationThird;

    private final static String key = "5a17e15699764435a31deeb97a9fd13e";

    private final static String locationUrl = "https://api.opencagedata.com/geocode/v1/json?key=@{key}&q=@{latitude}+@{longitude}&pretty=1";
    private final static String expectedLocationUrl = "https://api.opencagedata.com/geocode/v1/json?key=5a17e15699764435a31deeb97a9fd13e&q=40.2311+-32.12312&pretty=1";

    private final static String weatherUrl = "https://api.darksky.net/forecast/@{key}/@{latitude},@{longitude}";
    private final static String expectedWeatherUrl = "https://api.darksky.net/forecast/5a17e15699764435a31deeb97a9fd13e/40.2311,-32.12312";

    private final static String currencyUrl = "https://free.currencyconverterapi.com/api/v6/convert?q=@{iso_code}_USD,@{iso_code}_EUR,@{iso_code}_GBP,@{iso_code}_CNY&compact=ultra&apiKey=@{key}";
    private final static String expectedCurrencyUrl = "https://free.currencyconverterapi.com/api/v6/convert?q=BYN_USD,BYN_EUR,BYN_GBP,BYN_CNY&compact=ultra&apiKey=5a17e15699764435a31deeb97a9fd13e";

    private final static String badUrl = "koko.by";

    @Before
    public void prepareData() {
        locationFirst = new Location();
        locationFirst.setLatitude(BigDecimal.valueOf(40.2311));
        locationFirst.setLongitude(BigDecimal.valueOf(-32.12312));

        locationSecond = new Location();
        locationSecond.setCurrencyCode(Currency.valueOf("BYN"));

        locationThird = new Location();
        locationThird.setLatitude(BigDecimal.valueOf(40.2311));
        locationThird.setLongitude(BigDecimal.valueOf(-32.12312));
        locationThird.setCurrencyCode(Currency.valueOf("BYN"));
    }

    @Test
    public void testLocationUrl() throws BadUrlApiException {
        JsonRequestUrlCreatorByLocation builder = new JsonRequestUrlCreatorByLocation(locationUrl, key);

        String actual = builder.createURL(locationFirst).toString();

        Assert.assertEquals(actual, expectedLocationUrl);
    }

    @Test
    public void testWeatherUrl() throws BadUrlApiException {
        JsonRequestUrlCreatorByLocation builder = new JsonRequestUrlCreatorByLocation(weatherUrl, key);

        String actual = builder.createURL(locationFirst).toString();

        Assert.assertEquals(actual, expectedWeatherUrl);
    }

    @Test
    public void testCurrencyUrl() throws BadUrlApiException {
        JsonRequestUrlCreatorByLocation builder = new JsonRequestUrlCreatorByLocation(currencyUrl, key);

        String actual = builder.createURL(locationSecond).toString();

        Assert.assertEquals(actual, expectedCurrencyUrl);
    }

    @Test(expected = BadUrlApiException.class)
    public void testBadUrl() throws BadUrlApiException {
        JsonRequestUrlCreatorByLocation builder = new JsonRequestUrlCreatorByLocation(badUrl, key);

        String actual = builder.createURL(locationThird).toString();
    }
}
