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

//   StandardController를 상속하는 모든 컨트롤러에서 발생하는, 혹은 그 이하 서비스나 모델에서 아니면 매퍼에서 발생하는 모든 예외는 이제 얘가 다 처리할 것
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
