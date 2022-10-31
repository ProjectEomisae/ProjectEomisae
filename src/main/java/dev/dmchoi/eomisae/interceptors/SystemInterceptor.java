package dev.dmchoi.eomisae.interceptors;

import dev.dmchoi.eomisae.services.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
// 인터셉터는 컨트롤러의 계속된 진행을 허가하거나 거절 할 수 있고, 세션 처리나 보안 처리 등에 이용한다
// controller로 보내기 전에 처리하는 인터셉터 preHandle
// controller의 handler가 끝나면 처리됨 postHandle / 컨트롤러에서 필요한 조치를 다 취하고 빠져나갈 때 실행하는 건 post.
// view까지 처리가 끝난 후에 처리됨 afterCompletion
public class SystemInterceptor implements HandlerInterceptor {
    private static final ArrayList<String> BANNED_IPS = new ArrayList<>();
    // 정적이기 때문에 SystemInterceptor가 객체화 될 때마다 BANNED_IPS도 객체화 되지 않는다.

    @Autowired // 생성자가 아닌 멤버변수 자체에 @Autowired를 붙여 객체회 된다.
    private SystemService systemService;

    @Override // boolean타입을 반환. 즉 true면 컨트롤러로 진행
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Object handler : 핸들러 정보를 의미. ( RequestMapping , DefaultServletHandler )
        String ip = request.getRemoteAddr();
        if (BANNED_IPS.contains(ip) || this.systemService.isIpBanned(request)) {
            if(!BANNED_IPS.contains(ip)) {
                BANNED_IPS.add(ip);
            }
            ModelAndView bannedModelAndView = new ModelAndView("/banned");
            // 모든 경로에 대해 주소가 차단 당했다면 banned로 이동.
            throw new ModelAndViewDefiningException(bannedModelAndView);
            // throw는 그 자리에서 바로 종료이다. throw 하면 return true 가 작동하지 않음.
        }
        return true;
    }
}
