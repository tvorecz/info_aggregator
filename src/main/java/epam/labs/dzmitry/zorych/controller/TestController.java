package epam.labs.dzmitry.zorych.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String viewHello() {
        return "Hello, world";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewRoot(ModelMap modelMap) {
        modelMap.addAttribute("greeting", "Hello, it's spring mvc!");
        return "hello";
    }
}
