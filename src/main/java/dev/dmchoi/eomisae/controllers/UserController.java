package dev.dmchoi.eomisae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "dev.dmchoi.eomisae.controllers.UserController")
@RequestMapping(value = "/user")
public class UserController {
    @RequestMapping(value = "/memberSignUpForm", method = RequestMethod.GET)
    public ModelAndView getMemberSignUpForm(ModelAndView modelAndView) {
        modelAndView.setViewName("user/memberSignUpForm");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberInfo", method = RequestMethod.GET)
    public ModelAndView getMemberInfo(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberScrappedDocument", method = RequestMethod.GET)
    public ModelAndView getMemberScrappedDocument(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberScrappedDocument");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberSavedDocument", method = RequestMethod.GET)
    public ModelAndView getMemberSavedDocument(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberSavedDocument");
        return modelAndView;
    }
}
