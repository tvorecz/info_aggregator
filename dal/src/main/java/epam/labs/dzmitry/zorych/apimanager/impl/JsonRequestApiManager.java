package epam.labs.dzmitry.zorych.apimanager.impl;

import epam.labs.dzmitry.zorych.apimanager.JsonApiManager;
import epam.labs.dzmitry.zorych.apimanager.JsonApiManagerException;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.urlbuilder.BadUrlApiException;
import epam.labs.dzmitry.zorych.urlbuilder.RequestUrlBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class JsonRequestApiManager implements JsonApiManager {
    private RequestUrlBuilder urlBuilder;

    public JsonRequestApiManager(RequestUrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    @Override
    public String getRequestFor(Location location) throws BadUrlApiException, JsonApiManagerException {
        String resultRequest = null;
        InputStream stream = null;
        String charset = null;
        URLConnection urlConnection = null;

        URL url = urlBuilder.buildURL(location);

        JsonApiManagerException jsonApiManagerException = null;

        try {
            try {
                urlConnection = url.openConnection();

                String contentType = urlConnection.getContentType();
                charset = contentType.replaceAll(".+=", "");

                stream = urlConnection.getInputStream();
                resultRequest = createJsonString(stream, charset);
            } catch (IOException e) {
                jsonApiManagerException = new JsonApiManagerException("Cannot to receive data!", e);
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
