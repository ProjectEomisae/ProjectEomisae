package dev.dmchoi.eomisae.interceptors;

import dev.dmchoi.eomisae.services.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class SystemInterceptor implements HandlerInterceptor {
    private static final ArrayList<String> BANNED_IPS = new ArrayList<>();

    @Autowired
    private SystemService systemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        if (BANNED_IPS.contains(ip) || this.systemService.isIpBanned(request)) {
            if(!BANNED_IPS.contains(ip)) {
                BANNED_IPS.add(ip);
            }
            ModelAndView bannedModelAndView = new ModelAndView("/banned");
            throw new ModelAndViewDefiningException(bannedModelAndView);
        }
        return true;
    }
}
