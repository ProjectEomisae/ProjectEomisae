package dev.dmchoi.eomisae.controllers;

import dev.dmchoi.eomisae.services.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Component
public abstract class StandardController {
    protected final SystemService systemService;

    @Autowired
    protected StandardController(SystemService systemService) {
        this.systemService = systemService;
    }

    @ExceptionHandler(value = Exception.class)
    protected ModelAndView handleException(Exception ex) {
        try {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            this.systemService.putExceptionLog(ex);
            return new ModelAndView("error");
        } catch (Exception ignored) {
            return null;
        }
    }
}
