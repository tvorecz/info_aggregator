package epam.labs.dzmitry.zorych.controller;

import epam.labs.dzmitry.zorych.entity.ErrorInformation;
import epam.labs.dzmitry.zorych.entity.ResponseParam;
import epam.labs.dzmitry.zorych.service.mediator.CommonServiceMediator;
import epam.labs.dzmitry.zorych.service.mediator.InvalidParameterException;
import epam.labs.dzmitry.zorych.service.mediator.ReceivingDataIsFailedException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Properties;

/**
 * Receives requests to rest-service
 */
@RestController
public class InfoAggregatorController {
    private CommonServiceMediator serviceMediator;

    @Autowired
    public InfoAggregatorController(CommonServiceMediator serviceMediator) {
        this.serviceMediator = serviceMediator;
    }


    @ResponseBody
    @RequestMapping(value = {"/info/{type}/{template}", "/info/{type}"}, method = RequestMethod.GET)
    public ResponseEntity<String> handleJsonRequest(@PathVariable String type,
                                                    @PathVariable(required = false) String template,
                                                    @RequestParam(value = "weather", required = false, defaultValue =
                                                            "true") boolean weather,
                                                    @RequestParam(value = "currency", required = false, defaultValue
                                                            = "false") boolean currency,
                                                    @RequestParam(value = "location", required = false, defaultValue
                                                            = "false") boolean location,
                                                    @RequestParam(value = "latitude") BigDecimal latitude,
                                                    @RequestParam(value = "longitude") BigDecimal longitude
    ) {
        if (isValidType(type)) {
            String fileOfTemplate = chooseFileOfTemplate(type, template);

            if (idValidTemplate(fileOfTemplate)) {
                try {
                    ResponseParam responseParam = getResponse(weather, currency, location, latitude, longitude);
                    return formSuccessResponse(fileOfTemplate, type, responseParam);
                } catch (ReceivingDataIsFailedException e) {
                    return createExceptionReport(e.getMessage(), e.getCode(), type);
                } catch (InvalidParameterException e) {
                    return createExceptionReport(e.getMessage(), e.getCode(), type);
                } catch (Exception e) {
                    return createExceptionReport("Unexpected error.", 400, type);
                }
            }
        }

        return createExceptionReport("Invalid format or template", 400, "json");
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

    private MediaType chooseContentType(String type) {
        switch (type) {
            case "json":
                return MediaType.APPLICATION_JSON_UTF8;
            case "html":
                return MediaType.TEXT_HTML;
            case "text":
                return MediaType.TEXT_PLAIN;
            case "xml":
                return MediaType.APPLICATION_XML;

            default:
                return MediaType.APPLICATION_JSON_UTF8;
        }
    }

    private boolean idValidTemplate(String fileOfTemplate) {
        URL url = this.getClass().getResource(fileOfTemplate);

        if (url == null) {
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

        ResponseParam responseParam = serviceMediator.get(requestParam);

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

    private ResponseEntity<String> formSuccessResponse(String fileOfTemplate, String type, Object responseParam) {
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

        return ResponseEntity.ok().contentType(chooseContentType(type)).body(stringWriter.toString());
    }

    private ResponseEntity<String> createExceptionReport(String message, int code, String templateType) {
        String fileOfTemplate = chooseFileOfTemplate(templateType, "error");

        ErrorInformation errorInformation = new ErrorInformation(code, message);

        return formSuccessResponse(fileOfTemplate, templateType, errorInformation);
    }
}
