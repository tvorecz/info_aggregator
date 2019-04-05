package epam.labs.dzmitry.zorych.mainapp;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import epam.labs.dzmitry.zorych.entity.Location;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    private final static String locationUrl =
            "https://api.opencagedata.com/geocode/v1/json?key=5a17e15699764435a31deeb97a9fd13e&q=53.9045+27" +
            ".5615&pretty=1";
    private final static String weatherUrl =
            "https://api.darksky.net/forecast/f096461ff61c1e66669b3cf4ca10f28f/53.9045,27.5615";
    private final static String currensyUrl =
            "http://data.fixer.io/api/latest?access_key=4078f5c77901a5b2e6fe0b87ab5247a4&format=1";

    public static void main(String[] args) {
        readJsonByLibrary();
    }


//    private static void getInfoFromUrl() {
//        try {
//            URL url = new URL(weatherUrl);
//            InputStream stream = url.openStream();
//            //charset
//            URLConnection urlConnection = url.openConnection();
//            String contentType = urlConnection.getContentType();
//            System.out.println(contentType);
//            String charset = contentType.replaceAll(".+=", "");
//            System.out.println(charset);
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }


    private static String getJsonString() {
        InputStream stream = null;
        String charset = null;
        try {
            URL url = new URL(locationUrl);
//            stream = url.openStream();
            //charset
            URLConnection urlConnection = url.openConnection();
            String contentType = urlConnection.getContentType();
            stream = urlConnection.getInputStream();
            System.out.println(contentType);
            charset = contentType.replaceAll(".+=", "");
            System.out.println(charset);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        StringBuilder stringBuilder = new StringBuilder();

        try (ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream()) {
            int lengthOfReaded;

            while ((lengthOfReaded = stream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, lengthOfReaded);
            }

            stringBuilder.append(arrayOutputStream.toString(charset));

        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (BufferedReader in = new BufferedReader(new InputStreamReader(stream, charset))){
//            while (true) {
//                int read = in.read(buffer, 0, buffer.length);
//                if(read < 0) {
//                    break;
//                }
//
//                stringBuilder.append(buffer);
//            }
//
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println(stringBuilder.toString());

        return stringBuilder.toString();
    }

    private static void readJsonByLibrary() {
        JsonParser jsonParser = new JsonParser();
//        jsonParser.parse(new InputStreamReader())
        JsonElement json = jsonParser.parse(getJsonString());

        JsonObject jsonObject = json.getAsJsonObject();

        JsonElement iso_code =
                jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("annotations").getAsJsonObject().get(
                        "currency").getAsJsonObject().get("iso_code");

        Gson gson = new Gson();

        Location location = gson.fromJson(json, Location.class);

        System.out.println(location);

    }
}
