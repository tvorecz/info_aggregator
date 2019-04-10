package epam.labs.dzmitry.zorych.apimanager.impl;

import epam.labs.dzmitry.zorych.apimanager.JsonApiManagerException;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.urlcreator.BadUrlApiException;
import epam.labs.dzmitry.zorych.urlcreator.impl.JsonRequestUrlCreatorByLocation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class JsonRequestApiManagerTest {
    private static URL mockUrl;
    private static JsonRequestUrlCreatorByLocation mockUrlBuilder;
    private static URLConnection mockUrlConnection;
    private static Location location;

    @BeforeClass
    public static void fillData() throws IOException, BadUrlApiException {
        location = new Location();

        mockUrl = Mockito.mock(URL.class);
        mockUrlBuilder = Mockito.mock(JsonRequestUrlCreatorByLocation.class);
        mockUrlConnection = Mockito.mock(URLConnection.class);

        Mockito.when(mockUrl.openConnection()).thenReturn(mockUrlConnection);

        InputStream stream = new ByteArrayInputStream("return stream".getBytes(StandardCharsets.UTF_8));

        Mockito.when(mockUrlConnection.getInputStream()).thenReturn(stream);
        Mockito.when(mockUrlConnection.getContentType()).thenReturn("charset=utf-8");

        Mockito.when(mockUrlBuilder.createURL(location)).thenReturn(mockUrl);
    }

    @Test
    public void testJsonRequestApiManager() throws JsonApiManagerException, BadUrlApiException {
        JsonRequestApiManager jsonRequestApiManager = new JsonRequestApiManager(mockUrlBuilder);

        String actual = jsonRequestApiManager.getResponseFor(location);

        String expected = "return stream";

        Assert.assertEquals(actual, expected);
    }

}
