package dev.dmchoi.eomisae.controllers;

import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.member.user.LoginResult;
import dev.dmchoi.eomisae.services.SystemService;
import dev.dmchoi.eomisae.services.UserService;
import dev.dmchoi.eomisae.vos.member.user.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "dev.dmchoi.eomisae.controllers.RootController")
@RequestMapping(value = "/")
public class RootController extends StandardController {
    private final UserService userService;

    @Autowired
    protected RootController(SystemService systemService, UserService userService) {
        super(systemService);
        this.userService = userService;
    }

    //    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView getLogin(
//            ModelAndView modelAndView) {
//        modelAndView.setViewName("fragments/header");
//        System.out.println("로그인 성공");
//        return modelAndView;
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex(@RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
                                 ModelAndView modelAndView) {
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
