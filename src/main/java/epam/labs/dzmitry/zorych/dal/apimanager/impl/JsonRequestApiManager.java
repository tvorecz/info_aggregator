package epam.labs.dzmitry.zorych.dal.apimanager.impl;

import epam.labs.dzmitry.zorych.dal.apimanager.JsonApiManager;
import epam.labs.dzmitry.zorych.dal.apimanager.JsonApiManagerException;
import epam.labs.dzmitry.zorych.dal.urlcreator.BadUrlApiException;
import epam.labs.dzmitry.zorych.dal.urlcreator.RequestUrlCreator;
import epam.labs.dzmitry.zorych.entity.Location;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Used for access for different api-providers (weather, currency, location)
 */
public class JsonRequestApiManager implements JsonApiManager {
    /**
     * Used for creating unique url for every query
     */
    private RequestUrlCreator urlBuilder;

    public JsonRequestApiManager(RequestUrlCreator urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    /**
     * Return json-response as a string for current location
     * @param location Current search location
     * @return Json-response as a string
     * @throws BadUrlApiException Generate when url received from property is bad
     * @throws JsonApiManagerException Generate when appear stream problems
     */
    @Override
    public String getResponseFor(Location location) throws BadUrlApiException, JsonApiManagerException {
        String resultRequest = null;
        InputStream stream = null;
        String charset = null;
        URLConnection urlConnection = null;

        URL url = urlBuilder.createURL(location);

        JsonApiManagerException jsonApiManagerException = null;

        try {
            try {
                urlConnection = url.openConnection();

                String contentType = urlConnection.getContentType();
                charset = contentType.replaceAll(".+=", "");

                if(charset == null) {
                    throw new JsonApiManagerException("Cannot to receive data!", 400);
                }

                stream = urlConnection.getInputStream();
                resultRequest = createJsonString(stream, charset);
            } catch (IOException e) {
                jsonApiManagerException = new JsonApiManagerException("Cannot to receive data!", e, 404);
                throw jsonApiManagerException;
            } finally {
                if(stream != null) {
                    stream.close();
                }
            }
        } catch (IOException e) {
            if(jsonApiManagerException == null) {
                throw new JsonApiManagerException("Cannot close data source!", e);
            } else {
                throw new JsonApiManagerException("Cannot close data source!", jsonApiManagerException);
            }
        }

        return resultRequest;
    }

    private String createJsonString(InputStream stream, String charset) throws IOException {
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        StringBuilder stringBuilder = new StringBuilder();

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

        int lengthOfReaded;

        while ((lengthOfReaded = stream.read(buffer)) != - 1) {
            arrayOutputStream.write(buffer, 0, lengthOfReaded);
        }

        stringBuilder.append(arrayOutputStream.toString(charset));

        arrayOutputStream.close();

        return stringBuilder.toString();
    }
}
