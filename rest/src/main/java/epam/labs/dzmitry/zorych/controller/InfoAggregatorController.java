package epam.labs.dzmitry.zorych.controller;

import epam.labs.dzmitry.zorych.entity.RestRequestParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
public class InfoAggregatorController {
    private final static String urlModel = "/info?format=json&weather=true&currency=true&baseCurrency=BYN";


    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String handle(@RequestParam RestRequestParam requestParam) {
        return requestParam.toString();
    }
}
