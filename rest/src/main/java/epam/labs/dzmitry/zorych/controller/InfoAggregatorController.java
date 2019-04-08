package epam.labs.dzmitry.zorych.controller;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Properties;

@RestController
public class InfoAggregatorController {
    private final static String urlModel = "/info/json/1?weather=true&currency=true&location=true&latitude=90&longitude=90";


    @ResponseBody
    @RequestMapping(value = {"/info/json/{template}", "/info/json"}, method = RequestMethod.GET, produces =
            MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String handleJsonRequest(@PathVariable(required = false) String template,
                                @RequestParam(value = "weather", required = false, defaultValue = "false") boolean weather,
                                @RequestParam(value = "currency", required = false, defaultValue = "false") boolean currency,
                                @RequestParam(value = "location", required = false, defaultValue = "false") boolean location,
                                @RequestParam(value = "latitude") BigDecimal latitude,
                                @RequestParam(value = "longitude") BigDecimal longitude
                                ) {
        String output = "{\"status\":\"%1$s\", \"message\":%2$f}";

        return String.format(Locale.US, output, weather, latitude);
    }

    @ResponseBody
    @RequestMapping(value = {"/info/xml/{template}", "/info/xml"}, method = RequestMethod.GET, produces =
            MediaType.APPLICATION_XML_VALUE)
    public String handleXmlRequest(@PathVariable(required = false) String template,
                                   @RequestParam(value = "weather", required = false, defaultValue = "false") boolean weather,
                                   @RequestParam(value = "currency", required = false, defaultValue = "false") boolean currency,
                                   @RequestParam(value = "location", required = false, defaultValue = "false") boolean location,
                                   @RequestParam(value = "latitude") BigDecimal latitude,
                                   @RequestParam(value = "longitude") BigDecimal longitude
    ) {
        String output = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<response>\n" +
                        "  <weather>%1$s</weather>\n" +
                        "  <latitude>%2$f</latitude>\n" +
                        "</response>";

        return String.format(Locale.US, output, weather, latitude);
    }

    @ResponseBody
    @RequestMapping(value = {"/info/html/{template}", "/info/html"}, method = RequestMethod.GET, produces =
            MediaType.TEXT_HTML_VALUE)
    public String handleHtmlRequest(@PathVariable(required = false) String template,
                                    @RequestParam(value = "weather", required = false, defaultValue = "false") boolean weather,
                                    @RequestParam(value = "currency", required = false, defaultValue = "false") boolean currency,
                                    @RequestParam(value = "location", required = false, defaultValue = "false") boolean location,
                                    @RequestParam(value = "latitude") BigDecimal latitude,
                                    @RequestParam(value = "longitude") BigDecimal longitude
    ) {
        String output = "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<body>\n" +
                        "\n" +
                        "<h1><b>Weather:</b> %1$s</h1>\n" +
                        "\n" +
                        "<p><b>Latitude:</b> %2$s</p>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>";

        return String.format(Locale.US, output, weather, latitude);
    }

    @ResponseBody
    @RequestMapping(value = {"/info/text/{template}", "/templates/text/html"}, method = RequestMethod.GET, produces =
            MediaType.TEXT_PLAIN_VALUE)
    public String handleTextRequest(@PathVariable(required = false) String template,
                                    @RequestParam(value = "weather", required = false, defaultValue = "false") boolean weather,
                                    @RequestParam(value = "currency", required = false, defaultValue = "false") boolean currency,
                                    @RequestParam(value = "location", required = false, defaultValue = "false") boolean location,
                                    @RequestParam(value = "latitude") BigDecimal latitude,
                                    @RequestParam(value = "longitude") BigDecimal longitude
    ) {
        String output = "template = %1$s,\nweather = %2$b,\ncurrency = %3$b,\nlatitude = %4$f,\nlongitude = %5$f";

        return String.format(Locale.US, output, template, weather, currency, latitude, longitude);
    }

    @ResponseBody
    @RequestMapping(value = {"/info/velo/{template}", "/templates/velo"}, method = RequestMethod.GET, produces =
            MediaType.TEXT_PLAIN_VALUE)
    public String handleVelocityRequest(@PathVariable(required = false) String template,
                                    @RequestParam(value = "weather", required = false, defaultValue = "false") boolean weather,
                                    @RequestParam(value = "currency", required = false, defaultValue = "false") boolean currency,
                                    @RequestParam(value = "location", required = false, defaultValue = "false") boolean location,
                                    @RequestParam(value = "latitude") BigDecimal latitude,
                                    @RequestParam(value = "longitude") BigDecimal longitude
    ) {
        VelocityEngine velocityEngine = new VelocityEngine();
        Properties properties = new Properties();
        properties.setProperty("resource.loader","class");
        properties.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        velocityEngine.init(properties);

        Template vTemplate = velocityEngine.getTemplate("/templates/test.vm");

        VelocityContext context = new VelocityContext();
        context.put("template", template);
        context.put("weather", weather);
        context.put("currency", currency);
        context.put("location", location);
        context.put("latitude", latitude);
        context.put("longitude", longitude);

        StringWriter stringWriter = new StringWriter();
        vTemplate.merge(context, stringWriter);

        return stringWriter.toString();
    }
}
