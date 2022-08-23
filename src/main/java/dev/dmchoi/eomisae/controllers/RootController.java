package dev.dmchoi.eomisae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "dev.dmchoi.eomisae.controllers.RootController")
@RequestMapping(value = "/")
public class RootController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex(ModelAndView modelAndView) {
        modelAndView.setViewName("root/index");
        return modelAndView;
    }

    @RequestMapping(value = "banned", method = RequestMethod.GET)
    public ModelAndView getBanned(
            ModelAndView modelAndView) {
        modelAndView.setViewName("banned");
        return modelAndView;
    }

    @RequestMapping(value = "raise-error", method = RequestMethod.GET)
    @ResponseBody
    public String getRaiseError() throws Exception {
        throw new Exception();
    }
}
