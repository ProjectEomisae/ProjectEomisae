package dev.dmchoi.eomisae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "dev.dmchoi.eomisae.controllers.RootController")
@RequestMapping(value = "/")
public class RootController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex(ModelAndView modelAndView) {
        modelAndView.setViewName("root/index");
        return modelAndView;
    }

    @RequestMapping(value = "fs", method = RequestMethod.GET)
    public ModelAndView getFs(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/fs/fs");
        return modelAndView;
    }

    @RequestMapping(value = "fs/boardWrite", method = RequestMethod.GET)
    public ModelAndView getFsBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/fs/boardWrite-fs");
        return modelAndView;
    }
}
