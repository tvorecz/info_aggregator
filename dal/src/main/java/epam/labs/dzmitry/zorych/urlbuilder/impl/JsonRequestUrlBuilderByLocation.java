package epam.labs.dzmitry.zorych.urlbuilder.impl;

import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.urlbuilder.BadUrlApiException;
import epam.labs.dzmitry.zorych.urlbuilder.RequestUrlBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class JsonRequestUrlBuilderByLocation implements RequestUrlBuilder {
    private final static String LATITUDE = "${latitude}";
    private final static String LONGITUDE = "${longitude}";
    private final static String ISO_CODE = "${iso_code}";
    private final static String KEY = "${key}";
    private final static String[] PARAMS = {KEY, LATITUDE, LONGITUDE, ISO_CODE};
    private final static String[] EXCHANGE_PARAMS = {"${***}", "${******}", "${*********}", "${********}"};

    private final String urlTemplate;
    private final String key;
    private List<String> actualParamsForCurrentRequest;
    private String urlTemplateExample =
            "https://api.opencagedata.com/geocode/v1/json?key=${key}&q=${latitude}+${longitude}&pretty=1";

    String url =
    "https://free.currencyconverterapi.com/api/v6/convert?q=${iso_code}_USD,${iso_code}_EUR,${iso_code}_GBP,${iso_code}_CNY&compact=ultra&apiKey=${key}";

    public JsonRequestUrlBuilderByLocation(String urlTemplate, String key) {
        this.urlTemplate = prepareUrlTemplate(urlTemplate);
        this.key = key;
    }

    @Override
    public URL buildURL(Location location) throws BadUrlApiException {
        URL url = null;

        try {
            url = new URL(fillUrlTemplate(location));
        } catch (MalformedURLException e) {
            throw new BadUrlApiException("Bad url to api!", e);
        }

        return url;
    }

    private String fillUrlTemplate(Location location) {
        String result = String.format(urlTemplate, prepareArrayParams(location));

        return result;
    }

    private String prepareUrlTemplate(String urlTemplate) {
        Map<Integer, String> indexesAndParamsInRequestString = new HashMap<>();
        actualParamsForCurrentRequest = new ArrayList<>();

        boolean continueCycle;
        String template = urlTemplate;

        for (int i = 0; i < PARAMS.length; i++) {
            continueCycle = true;

            while (continueCycle) {
                int index = template.indexOf(PARAMS[i]);

                if(index > -1) {
                    indexesAndParamsInRequestString.put(index, PARAMS[i]);
                    template = template.replace(PARAMS[i], EXCHANGE_PARAMS[i]);
                } else {
                    continueCycle = false;
                }
            }
        }

        SortedSet<Integer> sortedKeySet = new TreeSet<>(indexesAndParamsInRequestString.keySet());

        int positionOfParam = 1;

        for (Integer indexOfSubstring : sortedKeySet) {
            urlTemplate = urlTemplate.replace(indexesAndParamsInRequestString.get(indexOfSubstring), ("%" + positionOfParam + "$s"));
            actualParamsForCurrentRequest.add(indexesAndParamsInRequestString.get(indexOfSubstring));

            positionOfParam++;
        }

        return urlTemplate;
    }

    private Object[] prepareArrayParams(Location location) {
        Object[] params = new Object[actualParamsForCurrentRequest.size()];

        int index = 0;

        for (String actualParam : actualParamsForCurrentRequest) {
            if(actualParam.equals(KEY)) {
                params[index] = key;
            } if (actualParam.equals(LATITUDE)) {
                params[index] = location.getLatitude();
            } if(actualParam.equals(LONGITUDE)) {
                params[index] = location.getLongitude();
            } if(actualParam.equals(ISO_CODE)) {
                params[index] = location.getIsoCode();
            }

            index++;
        }

        return params;
    }
}
