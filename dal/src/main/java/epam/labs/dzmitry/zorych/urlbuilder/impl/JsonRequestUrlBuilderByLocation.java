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
    private final static String[] PARAMS = {KEY, LATITUDE, LONGITUDE, KEY};

    private final String urlTemplate;
    private final String key;
    private List<String> actualParams;
    private String urlTemplateExample =
            "https://api.opencagedata.com/geocode/v1/json?key=${key}&q=${latitude}+${longitude}&pretty=1";

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
        Map<Integer, String> indexesAndParams = new HashMap<>();
        actualParams = new ArrayList<>();

        for (String param : PARAMS) {
            int index = urlTemplate.indexOf(param);

            if(index > -1) {
                indexesAndParams.put(index, param);
            }
        }

        SortedSet<Integer> sortedKeySet = new TreeSet<>(indexesAndParams.keySet());

        int positionOfParam = 1;

        for (Integer indexOfSubstring : sortedKeySet) {
            urlTemplate = urlTemplate.replace(indexesAndParams.get(indexOfSubstring), ("%" + positionOfParam + "$s"));
            actualParams.add(indexesAndParams.get(indexOfSubstring));

            positionOfParam++;
        }

        return urlTemplate;
    }

    private Object[] prepareArrayParams(Location location) {
        Object[] params = new Object[actualParams.size()];

        int index = 0;

        for (String actualParam : actualParams) {
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
