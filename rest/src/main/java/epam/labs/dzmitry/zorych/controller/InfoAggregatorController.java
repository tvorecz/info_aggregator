package epam.labs.dzmitry.zorych.controller;

import epam.labs.dzmitry.zorych.entity.ErrorInformation;
import epam.labs.dzmitry.zorych.entity.ResponseParam;
import epam.labs.dzmitry.zorych.mediator.InvalidParameterException;
import epam.labs.dzmitry.zorych.mediator.ReceivingDataIsFailedException;
import epam.labs.dzmitry.zorych.mediator.impl.ServiceMediator;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Properties;

@RestController
public class InfoAggregatorController {
    private final static String urlModel = "/info/json/1?weather=true&currency=true&location=true&latitude=90" +
                                           "&longitude=90";

    private ServiceMediator serviceMediator;

    @Autowired
    public InfoAggregatorController(ServiceMediator serviceMediator) {
        this.serviceMediator = serviceMediator;
    }

    @ResponseBody
    @RequestMapping(value = {"/info/json/{template}", "/info/json"}, method = RequestMethod.GET, produces =
            MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String handleJsonRequest(@PathVariable(required = false) String template,
                                    @RequestParam(value = "weather", required = false, defaultValue = "true") boolean weather,
                                    @RequestParam(value = "currency", required = false, defaultValue = "false") boolean currency,
                                    @RequestParam(value = "location", required = false, defaultValue = "false") boolean location,
                                    @RequestParam(value = "latitude") BigDecimal latitude,
                                    @RequestParam(value = "longitude") BigDecimal longitude
    ) {
        return handleRequest("json", template, weather, currency, location, latitude, longitude);
    }

    @ResponseBody
    @RequestMapping(value = {"/info/xml/{template}", "/info/xml"}, method = RequestMethod.GET, produces =
            MediaType.APPLICATION_XML_VALUE)
    public String handleXmlRequest(@PathVariable(required = false) String template,
                                   @RequestParam(value = "weather", required = false, defaultValue = "true") boolean weather,
                                   @RequestParam(value = "currency", required = false, defaultValue = "false") boolean currency,
                                   @RequestParam(value = "location", required = false, defaultValue = "false") boolean location,
                                   @RequestParam(value = "latitude") BigDecimal latitude,
                                   @RequestParam(value = "longitude") BigDecimal longitude
    ) {
        return handleRequest("xml", template, weather, currency, location, latitude, longitude);
    }

    @ResponseBody
    @RequestMapping(value = {"/info/html/{template}", "/info/html"}, method = RequestMethod.GET, produces =
            MediaType.TEXT_HTML_VALUE)
    public String handleHtmlRequest(@PathVariable(required = false) String template,
                                    @RequestParam(value = "weather", required = false, defaultValue = "true") boolean weather,
                                    @RequestParam(value = "currency", required = false, defaultValue = "false") boolean currency,
                                    @RequestParam(value = "location", required = false, defaultValue = "false") boolean location,
                                    @RequestParam(value = "latitude") BigDecimal latitude,
                                    @RequestParam(value = "longitude") BigDecimal longitude
    ) {
        return handleRequest("html", template, weather, currency, location, latitude, longitude);
    }

    @ResponseBody
    @RequestMapping(value = {"/info/text/{template}", "/info/text"}, method = RequestMethod.GET, produces =
            MediaType.TEXT_PLAIN_VALUE)
    public String handleTextRequest(@PathVariable(required = false) String template,
                                    @RequestParam(value = "weather", required = false, defaultValue = "true") boolean weather,
                                    @RequestParam(value = "currency", required = false, defaultValue = "false") boolean currency,
                                    @RequestParam(value = "location", required = false, defaultValue = "false") boolean location,
                                    @RequestParam(value = "latitude") BigDecimal latitude,
                                    @RequestParam(value = "longitude") BigDecimal longitude
    ) {
        return handleRequest("text", template, weather, currency, location, latitude, longitude);
    }

//    @ResponseBody
//    @RequestMapping(value = {"/info/velo/{template}", "/templates/velo"}, method = RequestMethod.GET, produces =
//            MediaType.TEXT_PLAIN_VALUE)
//    public String handleVelocityRequest(@PathVariable(required = false) String template,
//                                        @RequestParam(value = "weather", required = false, defaultValue = "false") boolean weather,
//                                        @RequestParam(value = "currency", required = false, defaultValue = "false") boolean currency,
//                                        @RequestParam(value = "location", required = false, defaultValue = "false") boolean location,
//                                        @RequestParam(value = "latitude") BigDecimal latitude,
//                                        @RequestParam(value = "longitude") BigDecimal longitude
//    ) {
//        VelocityEngine velocityEngine = new VelocityEngine();
//        Properties properties = new Properties();
//        properties.setProperty("resource.loader", "class");
//        properties.setProperty("class.resource.loader.class",
//                               "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//
//        velocityEngine.init(properties);
//
//        Template vTemplate = velocityEngine.getTemplate("/templates/test.vm");
//
//        VelocityContext context = new VelocityContext();
//        context.put("template", template);
//        context.put("weather", weather);
//        context.put("currency", currency);
//        context.put("location", location);
//        context.put("latitude", latitude);
//        context.put("longitude", longitude);
//
//        StringWriter stringWriter = new StringWriter();
//        vTemplate.merge(context, stringWriter);
//
//        return stringWriter.toString();
//    }


    private String handleRequest(String type, String template,
                                boolean weather,
                                boolean currency,
                                boolean location,
                                BigDecimal latitude,
                                BigDecimal longitude
    ) {
        if(isValidType(type)) {
            String fileOfTemplate = chooseFileOfTemplate(type, template);

            if(idValidTemplate(fileOfTemplate)) {
                ResponseParam responseParam = null;
                try {
                    responseParam = getResponse(weather, currency, location, latitude, longitude);
                } catch (ReceivingDataIsFailedException e) {
                    return createExceptionReport(e.getMessage(), e.getCode(), type);
                } catch (InvalidParameterException e) {
                    return createExceptionReport(e.getMessage(), e.getCode(), type);
                }
                return formVelocityResponseString(fileOfTemplate, responseParam);
            }
        }

        return createExceptionReport("Invalid format or template", 400, type);
    }

    private boolean isValidType(String type) {
        switch (type.toLowerCase()) {
            case "json":
            case "html":
            case "text":
            case "xml":
                return true;
        }

        return false;
    }

    private boolean idValidTemplate(String fileOfTemplate) {
        URL url = this.getClass().getResource(fileOfTemplate);

        if(url == null) {
            return false;
        }

        File file = new File(url.toString().substring(6));
        return file.exists();
    }

    private String chooseFileOfTemplate(String type, String name) {
        StringBuilder stringBuilder = new StringBuilder();

        if (name == null) {
            stringBuilder.append(1);
        } else {
            stringBuilder.append(name);
        }

        stringBuilder.append(".vm");

        stringBuilder.insert(0, ("/templates/" + type + "/"));

        return stringBuilder.toString();
    }

    private ResponseParam getResponse(boolean weather,
                                      boolean currency,
                                      boolean location,
                                      BigDecimal latitude,
                                      BigDecimal longitude) throws
                                                            ReceivingDataIsFailedException,
                                                            InvalidParameterException {
        epam.labs.dzmitry.zorych.entity.RequestParam requestParam = createRequestParam(weather,
                                                                                       currency,
                                                                                       location,
                                                                                       latitude,
                                                                                       longitude);

        ResponseParam responseParam = null;
        responseParam = serviceMediator.get(requestParam);

        return responseParam;
    }

    private epam.labs.dzmitry.zorych.entity.RequestParam createRequestParam(boolean weather,
                                                                            boolean currency,
                                                                            boolean location,
                                                                            BigDecimal latitude,
                                                                            BigDecimal longitude) {
        epam.labs.dzmitry.zorych.entity.RequestParam requestParam = new epam.labs.dzmitry.zorych.entity.RequestParam();
        requestParam.setWeather(weather);
        requestParam.setCurrency(currency);
        requestParam.setLocation(location);
        requestParam.setLatitude(latitude);
        requestParam.setLongitude(longitude);

        return requestParam;
    }

    private String formVelocityResponseString(String fileOfTemplate, Object responseParam) {
        VelocityEngine velocityEngine = new VelocityEngine();
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class",
                               "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        velocityEngine.init(properties);

        Template vTemplate = velocityEngine.getTemplate(fileOfTemplate);

        VelocityContext context = new VelocityContext();
        context.put("responseParam", responseParam);

        StringWriter stringWriter = new StringWriter();
        vTemplate.merge(context, stringWriter);

        return stringWriter.toString();
    }

    private String createExceptionReport(String message, int code, String templateType) {
        String fileOfTemplate = chooseFileOfTemplate(templateType, "error");

        ErrorInformation errorInformation = new ErrorInformation(code, message);

        String result = formVelocityResponseString(fileOfTemplate, errorInformation);

        return result;
    }
}
