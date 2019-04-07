package epam.labs.dzmitry.zorych.mainapp;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entity.Weather;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class Main {
    private final static String locationUrl =
            "https://api.opencagedata.com/geocode/v1/json?key=5a17e15699764435a31deeb97a9fd13e&q=53.9045+27" +
            ".5615&pretty=1";
    private final static String weatherUrl =
            "https://api.darksky.net/forecast/f096461ff61c1e66669b3cf4ca10f28f/53.9045,27.5615?exclude=minutely,hourly,daily,alerts,flags";
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
            URL url = new URL(weatherUrl);
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

//        String iso_code =
//                jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("annotations").getAsJsonObject().get(
//                        "currency").getAsJsonObject().get("iso_code").getAsString();
//
//        BigDecimal latitude =
//                jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("lat").getAsBigDecimal();
//
//        BigDecimal longitude =
//                jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("lng").getAsBigDecimal();
//
//        String timeZone =
//                jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("annotations").getAsJsonObject().get(
//                        "timezone").getAsJsonObject().get("name").getAsString();
//
//        String continent =
//                jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("components").getAsJsonObject().get("continent").getAsString();
//
//        String country =
//                jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("components").getAsJsonObject().get("country").getAsString();
//
//        String city =
//                jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("components").getAsJsonObject().get("city").getAsString();

//        Gson gson = new Gson();
//
//        Location location = gson.fromJson(json, Location.class);
//        System.out.println(location);

        Weather weather = new Weather();

//        weather.setLocation(location);
        weather.setSummary(jsonObject.get("currently").getAsJsonObject().get("summary").getAsString());
        weather.setIcon(jsonObject.get("currently").getAsJsonObject().get("icon").getAsString());
        weather.setTemperature(jsonObject.get("currently").getAsJsonObject().get("temperature").getAsInt());
        weather.setApparentTemperature(jsonObject.get("currently").getAsJsonObject().get("apparentTemperature").getAsInt());
        weather.setPressure(jsonObject.get("currently").getAsJsonObject().get("pressure").getAsDouble());
        weather.setWindSpeed(jsonObject.get("currently").getAsJsonObject().get("windSpeed").getAsInt());
        weather.setWindGust(jsonObject.get("currently").getAsJsonObject().get("windGust").getAsInt());

        System.out.println(weather);

        String format = "%2$s, %3$s, %2$s, %1$s, %2$s, %2$s";
        System.out.println(String.format(format, "1", "2", "3"));
        JsonElement error = jsonObject.get("error");
        System.out.println(error);
    }
}
