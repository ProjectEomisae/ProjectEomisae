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

//   StandardController를 상속하는 모든 컨트롤러에서 발생하는,
//   혹은 그 이하 서비스나 모델에서 아니면 매퍼에서 발생하는 모든 예외는 이제 얘가 다 처리할 것
    @ExceptionHandler(value = Exception.class)
    // value = 이 메서드는 이러한 예외만 처리를 한다라고 정의. type of class 명시
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
//  이 handleException이라는 메서드는 모든 예외에 대한 최후의 보류이기 때문에
//  handleException 안에서는 예외가 밖으로 세어 나가서는 안된다.
//  고로 이 안에서 조치하는 모든 오류는 이 안에서 끝나야 한다.

//  개발자는 오류 발생을 알기 위해 sout하여 getMessage해주고 printStackTrace를 해준다.
}
