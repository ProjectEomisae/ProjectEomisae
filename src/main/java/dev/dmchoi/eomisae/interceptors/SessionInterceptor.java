package dev.dmchoi.eomisae.interceptors;

import dev.dmchoi.eomisae.entities.member.SessionEntity;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie autoLoginCookie = null;
        Cookie sessionKeyCookie = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("loginCookie")) {
                    sessionKeyCookie = cookie;
                }
                if (cookie.getName().equals("autoLoginCookie")) {
                    autoLoginCookie = cookie;
                }
            }
        }
        if (sessionKeyCookie != null && sessionKeyCookie.getValue() != null) {
            String sessionKey = sessionKeyCookie.getValue();
            SessionEntity sessionEntity = this.userService.getSession(sessionKey);

            if (sessionEntity != null && sessionEntity.getUserIndex() != 0) {
                UserEntity userEntity = this.userService.getUser(sessionEntity.getUserIndex());

                if (userEntity != null && userEntity.getIndex() != 0) {
                    this.userService.extendSession(sessionEntity);
                    request.setAttribute("sessionEntity", sessionEntity);
                    request.setAttribute(UserEntity.ATTRIBUTE_NAME, userEntity);
                    System.out.println("로그인이 되어있는 상태 -> sessionKeyCookie의 MaxAge : " + sessionKeyCookie.getMaxAge());
                }
            }
        }
        if (autoLoginCookie != null && autoLoginCookie.getValue() != null) {
            String sessionKey = autoLoginCookie.getValue();
            SessionEntity sessionEntity = this.userService.getSession(sessionKey);

            if (sessionEntity != null && sessionEntity.getUserIndex() != 0) {
                UserEntity userEntity = this.userService.getUser(sessionEntity.getUserIndex());

                if (userEntity != null && userEntity.getIndex() != 0) {
                    this.userService.extendSession(sessionEntity);
                    request.setAttribute("sessionEntity", sessionEntity);
                    request.setAttribute(UserEntity.ATTRIBUTE_NAME, userEntity);
                    int limitTime = 60 * 60 * 24 * 90; // 7776000 90일
                    autoLoginCookie.setMaxAge( limitTime);
                    response.addCookie(autoLoginCookie);
                    System.out.println("로그인이 되어있는 상태 -> autoLoginCookie MaxAge : "  + autoLoginCookie.getMaxAge());
                }
            }
        }
        // √ 값이 0이나 음수면 쿠키가 삭제됨.
        if (request.getAttribute(UserEntity.ATTRIBUTE_NAME) == null && sessionKeyCookie != null) {
            sessionKeyCookie.setMaxAge(0);
// 최종적으로 request.getAttribute가 null이면 쿠키 삭제
            response.addCookie(sessionKeyCookie);
                System.out.println("유저가 null이고 sessionKeyCookie가 null이 아닐 때 (로그아웃 시) MaxAge : " + sessionKeyCookie.getMaxAge());
        }
        if (request.getAttribute(UserEntity.ATTRIBUTE_NAME) == null && autoLoginCookie != null) {
            autoLoginCookie.setMaxAge(0);
// 최종적으로 request.getAttribute가 null이면 쿠키 삭제
            response.addCookie(autoLoginCookie);
            System.out.println("유저가 null이고 autoLoginCookie가 null이 아닐 때 (로그아웃 시) MaxAge : " + autoLoginCookie.getMaxAge());
        }
        return true;
    }
}
