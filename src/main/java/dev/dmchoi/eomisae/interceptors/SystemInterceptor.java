package dev.dmchoi.eomisae.interceptors;

import dev.dmchoi.eomisae.services.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

// controller로 보내기 전에 처리하는 인터셉터 preHandle
// controller의 handler가 끝나면 처리됨 postHandle
// view까지 처리가 끝난 후에 처리됨 afterCompletion
public class SystemInterceptor implements HandlerInterceptor {
    private static final ArrayList<String> BANNED_IPS = new ArrayList<>();

    @Autowired
    private SystemService systemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Object handler : 핸들러 정보를 의미. ( RequestMapping , DefaultServletHandler )
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
