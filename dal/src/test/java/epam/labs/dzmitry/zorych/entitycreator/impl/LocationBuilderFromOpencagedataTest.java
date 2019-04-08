package epam.labs.dzmitry.zorych.entitycreator.impl;

import epam.labs.dzmitry.zorych.entity.Currency;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entitycreator.DataSourceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

public class LocationBuilderFromOpencagedataTest {
    private final static String JSON = "{\n" +
                                       "   \"documentation\" : \"https://opencagedata.com/api\",\n" +
                                       "   \"licenses\" : [\n" +
                                       "      {\n" +
                                       "         \"name\" : \"CC-BY-SA\",\n" +
                                       "         \"url\" : \"https://creativecommons.org/licenses/by-sa/3.0/\"\n" +
                                       "      },\n" +
                                       "      {\n" +
                                       "         \"name\" : \"ODbL\",\n" +
                                       "         \"url\" : \"https://opendatacommons.org/licenses/odbl/summary/\"\n" +
                                       "      }\n" +
                                       "   ],\n" +
                                       "   \"rate\" : {\n" +
                                       "      \"limit\" : 2500,\n" +
                                       "      \"remaining\" : 2494,\n" +
                                       "      \"reset\" : 1554422400\n" +
                                       "   },\n" +
                                       "   \"results\" : [\n" +
                                       "      {\n" +
                                       "         \"annotations\" : {\n" +
                                       "            \"DMS\" : {\n" +
                                       "               \"lat\" : \"53\\u00b0 54' 16.95060'' N\",\n" +
                                       "               \"lng\" : \"27\\u00b0 33' 42.62940'' E\"\n" +
                                       "            },\n" +
                                       "            \"MGRS\" : \"35UNV3691273065\",\n" +
                                       "            \"Maidenhead\" : \"KO33sv77kd\",\n" +
                                       "            \"Mercator\" : {\n" +
                                       "               \"x\" : 3068170.162,\n" +
                                       "               \"y\" : 7117578.086\n" +
                                       "            },\n" +
                                       "            \"OSM\" : {\n" +
                                       "               \"edit_url\" : \"https://www.openstreetmap" +
                                       ".org/edit?way=156144832#map=17/53.90471/27.56184\",\n" +
                                       "               \"url\" : \"https://www.openstreetmap.org/?mlat=53" +
                                       ".90471&mlon=27.56184#map=17/53.90471/27.56184\"\n" +
                                       "            },\n" +
                                       "            \"callingcode\" : 375,\n" +
                                       "            \"currency\" : {\n" +
                                       "               \"alternate_symbols\" : [\n" +
                                       "                  \"\\u0431\\u0435\\u043b. \\u0440\\u0443\\u0431.\",\n" +
                                       "                  \"\\u0431.\\u0440.\",\n" +
                                       "                  \"\\u0440\\u0443\\u0431.\",\n" +
                                       "                  \"\\u0440.\"\n" +
                                       "               ],\n" +
                                       "               \"decimal_mark\" : \",\",\n" +
                                       "               \"disambiguate_symbol\" : \"BYN\",\n" +
                                       "               \"html_entity\" : \"\",\n" +
                                       "               \"iso_code\" : \"BYN\",\n" +
                                       "               \"iso_numeric\" : 933,\n" +
                                       "               \"name\" : \"Belarusian Ruble\",\n" +
                                       "               \"smallest_denomination\" : 1,\n" +
                                       "               \"subunit\" : \"Kapeyka\",\n" +
                                       "               \"subunit_to_unit\" : 100,\n" +
                                       "               \"symbol\" : \"Br\",\n" +
                                       "               \"symbol_first\" : 0,\n" +
                                       "               \"thousands_separator\" : \" \"\n" +
                                       "            },\n" +
                                       "            \"flag\" : \"\\ud83c\\udde7\\ud83c\\uddfe\",\n" +
                                       "            \"geohash\" : \"u9edejjctmyx6bre6bjs\",\n" +
                                       "            \"qibla\" : 159.17,\n" +
                                       "            \"sun\" : {\n" +
                                       "               \"rise\" : {\n" +
                                       "                  \"apparent\" : 1554348960,\n" +
                                       "                  \"astronomical\" : 1554341220,\n" +
                                       "                  \"civil\" : 1554346800,\n" +
                                       "                  \"nautical\" : 1554344100\n" +
                                       "               },\n" +
                                       "               \"set\" : {\n" +
                                       "                  \"apparent\" : 1554396660,\n" +
                                       "                  \"astronomical\" : 1554404460,\n" +
                                       "                  \"civil\" : 1554398880,\n" +
                                       "                  \"nautical\" : 1554401520\n" +
                                       "               }\n" +
                                       "            },\n" +
                                       "            \"timezone\" : {\n" +
                                       "               \"name\" : \"Europe/Minsk\",\n" +
                                       "               \"now_in_dst\" : 0,\n" +
                                       "               \"offset_sec\" : 10800,\n" +
                                       "               \"offset_string\" : 300,\n" +
                                       "               \"short_name\" : 3\n" +
                                       "            },\n" +
                                       "            \"what3words\" : {\n" +
                                       "               \"words\" : \"drainage.stops.visit\"\n" +
                                       "            }\n" +
                                       "         },\n" +
                                       "         \"bounds\" : {\n" +
                                       "            \"northeast\" : {\n" +
                                       "               \"lat\" : 53.9048597,\n" +
                                       "               \"lng\" : 27.562369\n" +
                                       "            },\n" +
                                       "            \"southwest\" : {\n" +
                                       "               \"lat\" : 53.9042694,\n" +
                                       "               \"lng\" : 27.5611765\n" +
                                       "            }\n" +
                                       "         },\n" +
                                       "         \"components\" : {\n" +
                                       "            \"ISO_3166-1_alpha-2\" : \"BY\",\n" +
                                       "            \"ISO_3166-1_alpha-3\" : \"BLR\",\n" +
                                       "            \"_type\" : \"building\",\n" +
                                       "            \"building\" : \"\\u0411\\u0438\\u0437\\u043d\\u0435\\u0441" +
                                       "-\\u0446\\u0435\\u043d\\u0442\\u0440  Colliers International\",\n" +
                                       "            \"city\" : \"Minsk\",\n" +
                                       "            \"city_district\" : \"Tsentralny District\",\n" +
                                       "            \"continent\" : \"Europe\",\n" +
                                       "            \"country\" : \"Belarus\",\n" +
                                       "            \"country_code\" : \"by\",\n" +
                                       "            \"house_number\" : \"36\",\n" +
                                       "            \"neighbourhood\" : \"\\u041d\\u0438\\u0437\\u043a\\u0438\\u0439 " +
                                       "\\u0420\\u044b\\u043d\\u043e\\u043a\",\n" +
                                       "            \"postcode\" : \"220030\",\n" +
                                       "            \"road\" : \"\\u0418\\u043d\\u0442\\u0435\\u0440\\u043d\\u0430" +
                                       "\\u0446\\u0438\\u043e\\u043d\\u0430\\u043b\\u044c\\u043d\\u0430\\u044f " +
                                       "\\u0443\\u043b\\u0438\\u0446\\u0430\"\n" +
                                       "         },\n" +
                                       "         \"confidence\" : 10,\n" +
                                       "         \"formatted\" : \"Belarus, 220030 Minsk, " +
                                       "\\u0418\\u043d\\u0442\\u0435\\u0440\\u043d\\u0430\\u0446\\u0438\\u043e\\u043d" +
                                       "\\u0430\\u043b\\u044c\\u043d\\u0430\\u044f " +
                                       "\\u0443\\u043b\\u0438\\u0446\\u0430, 36, " +
                                       "\\u0411\\u0438\\u0437\\u043d\\u0435\\u0441-\\u0446\\u0435\\u043d\\u0442" +
                                       "\\u0440 Colliers International\",\n" +
                                       "         \"geometry\" : {\n" +
                                       "            \"lat\" : 53.9047085,\n" +
                                       "            \"lng\" : 27.5618415\n" +
                                       "         }\n" +
                                       "      }\n" +
                                       "   ],\n" +
                                       "   \"status\" : {\n" +
                                       "      \"code\" : 200,\n" +
                                       "      \"message\" : \"OK\"\n" +
                                       "   },\n" +
                                       "   \"stay_informed\" : {\n" +
                                       "      \"blog\" : \"https://blog.opencagedata.com\",\n" +
                                       "      \"twitter\" : \"https://twitter.com/opencagedata\"\n" +
                                       "   },\n" +
                                       "   \"thanks\" : \"For using an OpenCage Data API\",\n" +
                                       "   \"timestamp\" : {\n" +
                                       "      \"created_http\" : \"Thu, 04 Apr 2019 15:22:15 GMT\",\n" +
                                       "      \"created_unix\" : 1554391335\n" +
                                       "   },\n" +
                                       "   \"total_results\" : 1\n" +
                                       "}";

    private final static String JSON_ERROR = "{\n" +
                                             "   \"documentation\" : \"https://opencagedata.com/api\",\n" +
                                             "   \"licenses\" : [\n" +
                                             "      {\n" +
                                             "         \"name\" : \"CC-BY-SA\",\n" +
                                             "         \"url\" : \"https://creativecommons.org/licenses/by-sa/3" +
                                             ".0/\"\n" +
                                             "      },\n" +
                                             "      {\n" +
                                             "         \"name\" : \"ODbL\",\n" +
                                             "         \"url\" : \"https://opendatacommons" +
                                             ".org/licenses/odbl/summary/\"\n" +
                                             "      }\n" +
                                             "   ],\n" +
                                             "   \"rate\" : {\n" +
                                             "      \"limit\" : 2500,\n" +
                                             "      \"remaining\" : 2498,\n" +
                                             "      \"reset\" : 1554681600\n" +
                                             "   },\n" +
                                             "   \"results\" : [],\n" +
                                             "   \"status\" : {\n" +
                                             "      \"code\" : 400,\n" +
                                             "      \"message\" : \"invalid coordinates\"\n" +
                                             "   },\n" +
                                             "   \"stay_informed\" : {\n" +
                                             "      \"blog\" : \"https://blog.opencagedata.com\",\n" +
                                             "      \"twitter\" : \"https://twitter.com/opencagedata\"\n" +
                                             "   },\n" +
                                             "   \"thanks\" : \"For using an OpenCage Data API\",\n" +
                                             "   \"timestamp\" : {\n" +
                                             "      \"created_http\" : \"Sun, 07 Apr 2019 10:14:57 GMT\",\n" +
                                             "      \"created_unix\" : 1554632097\n" +
                                             "   },\n" +
                                             "   \"total_results\" : 0\n" +
                                             "}\n";

    private static Location expectedLocation;

    @BeforeClass
    public static void fillData() {
        expectedLocation = new Location();

        expectedLocation.setLatitude(BigDecimal.valueOf(53.9047085));
        expectedLocation.setLongitude(BigDecimal.valueOf(27.5618415));
        expectedLocation.setCity("Minsk");
        expectedLocation.setCountry("Belarus");
        expectedLocation.setContinent("Europe");
        expectedLocation.setCurrencyCode(Currency.valueOf("BYN"));
        expectedLocation.setTimeZone("Europe/Minsk");
    }

    @Test
    public void testLocationBuilder() throws DataSourceException {
        LocationCreatorFromOpencagedata locationBuilderFromOpencagedata = new LocationCreatorFromOpencagedata();
        Location actualLocation = locationBuilderFromOpencagedata.create(JSON);

        Assert.assertEquals(actualLocation, expectedLocation);
    }

    @Test(expected = DataSourceException.class)
    public void testLocationBuilderException() throws DataSourceException {
        LocationCreatorFromOpencagedata locationBuilderFromOpencagedata = new LocationCreatorFromOpencagedata();
        locationBuilderFromOpencagedata.create(JSON_ERROR);
    }
}
