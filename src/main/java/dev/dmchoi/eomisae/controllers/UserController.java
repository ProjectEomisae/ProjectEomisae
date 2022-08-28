package dev.dmchoi.eomisae.controllers;

import dev.dmchoi.eomisae.entities.member.SessionEntity;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.entities.system.ActivityLogEntity;
import dev.dmchoi.eomisae.enums.member.user.LoginResult;
import dev.dmchoi.eomisae.models.PagingModel;
import dev.dmchoi.eomisae.services.BbsService;
import dev.dmchoi.eomisae.services.SystemService;
import dev.dmchoi.eomisae.services.UserService;
import dev.dmchoi.eomisae.vos.bbs.BoardListVo;
import dev.dmchoi.eomisae.vos.member.user.LoginVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import dev.dmchoi.eomisae.vos.member.user.RegisterVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.MessagingException;
import java.util.Optional;

@Controller(value = "dev.dmchoi.eomisae.controllers.UserController")
@RequestMapping(value = "/user")
public class UserController extends StandardController {

    private final UserService userService;
    private final BbsService bbsService;

    @Autowired
    protected UserController(SystemService systemService, UserService userService, BbsService bbsService) {
        super(systemService);
        this.userService = userService;
        this.bbsService = bbsService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String postLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            LoginVo loginVo) {
        loginVo.setResult(null);
        this.userService.login(loginVo, request);
        this.systemService.putActivityLog(loginVo.getIndex(), request, loginVo);
        if (loginVo.getResult() == LoginResult.SUCCESS) {
            if (!loginVo.isAutosign()) {
                Cookie sessionKeyCookie = new Cookie("loginCookie", loginVo.getSessionEntity().getKey());
                sessionKeyCookie.setPath("/");
                response.addCookie(sessionKeyCookie);
            } else {
                Cookie sessionKeyCookie = new Cookie("loginCookie", loginVo.getSessionEntity().getKey());
                sessionKeyCookie.setPath("/");
                response.addCookie(sessionKeyCookie);
                Cookie autoLoginCookie = new Cookie("autoLoginCookie", loginVo.getSessionEntity().getKey());
                autoLoginCookie.setPath("/");
                response.addCookie(autoLoginCookie);
            }
        }
        JSONObject responseJson = new JSONObject();
        responseJson.put("result", loginVo.getResult().name().toLowerCase());
        return responseJson.toString();
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView getLogout(
            HttpServletRequest request,
            ModelAndView modelAndView) {
        if (request.getAttribute("sessionEntity") instanceof SessionEntity) {
            SessionEntity sessionEntity = (SessionEntity) request.getAttribute("sessionEntity");
            this.userService.expireSession(sessionEntity);
        }
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "/memberSignUpForm", method = RequestMethod.GET)
    public ModelAndView getMemberSignUpForm(ModelAndView modelAndView,
                                            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user) {
        if (user != null) {
            modelAndView.setViewName("redirect:/");
        }
        modelAndView.setViewName("user/memberSignUpForm");
        return modelAndView;
    }

    @RequestMapping(value = "/memberSignUpForm", method = RequestMethod.POST)
    public ModelAndView postMemberSignUpForm(RegisterVo registerVo, ModelAndView modelAndView) throws MessagingException {
        registerVo.setEmailVerified(false);
        registerVo.setResult(null);
        this.userService.register(registerVo);
        modelAndView.addObject("registerVo", registerVo);
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

    @RequestMapping(value = "/my-page/memberOwnDocument", method = RequestMethod.GET)
    public ModelAndView getMemberOwnDocument(ModelAndView modelAndView,
                                             @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
                                             @RequestParam(name = "page") Optional<Integer> optionalPage,
                                             BoardListVo boardListVo) {
        if (user == null) {
            modelAndView.setViewName("redirect:/user/login");
        }
        int page = optionalPage.orElse(1);
        int totalRowCount;
        totalRowCount = this.bbsService.boardTotalCountByUserIndex(user.getIndex());
        PagingModel paging = new PagingModel(totalRowCount, page);
        boardListVo.setArticles(this.bbsService.listBoardByUserIndex(user.getIndex(), paging));
        modelAndView.addObject("paging", paging);
        modelAndView.addObject("boardListVo", boardListVo);
        modelAndView.setViewName("user/my-page/memberOwnDocument");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberOwnVote", method = RequestMethod.GET)
    public ModelAndView getMemberOwnVote(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberOwnVote");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberCommunicationMessages", method = RequestMethod.GET)
    public ModelAndView getMemberCommunicationMessages(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberCommunicationMessages");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberLoginLogHistories", method = RequestMethod.GET)
    public ModelAndView getMemberLoginLogHistories(ModelAndView modelAndView,
                                                   @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
                                                   @RequestParam(name = "page") Optional<Integer> optionalPage) {
        if (user == null) {
            modelAndView.setViewName("redirect:/user/login");
        }
        int page = optionalPage.orElse(1);
        int totalRowCount;
        totalRowCount = this.systemService.activityLogTotalCountByUserIndex(user.getIndex());
        PagingModel paging = new PagingModel(totalRowCount, page);
        ActivityLogEntity[] activityLogEntities = this.systemService.getActivityLog(user.getIndex(), paging);
        System.out.println(activityLogEntities.length);
        modelAndView.addObject("paging", paging);
        modelAndView.addObject("activityLogEntities", activityLogEntities);
        modelAndView.setViewName("user/my-page/memberLoginLogHistories");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberPointHistoryList", method = RequestMethod.GET)
    public ModelAndView getMemberPointHistoryList(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberPointHistoryList");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberCashSendLog", method = RequestMethod.GET)
    public ModelAndView getMemberCashSendLog(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberCashSendLog");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberCashHistoryList", method = RequestMethod.GET)
    public ModelAndView getMemberCashHistoryList(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberCashHistoryList");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberOwnComment", method = RequestMethod.GET)
    public ModelAndView getMemberOwnComment(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberOwnComment");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberOwnVotePosting", method = RequestMethod.GET)
    public ModelAndView getMemberOwnVotePosting(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberOwnVotePosting");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberOwnBlockList", method = RequestMethod.GET)
    public ModelAndView getMemberOwnBlockList(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberOwnBlockList");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberSnsManage", method = RequestMethod.GET)
    public ModelAndView getMemberSnsManage(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberSnsManage");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberAutoLoginManager", method = RequestMethod.GET)
    public ModelAndView getMemberAutoLoginManager(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberAutoLoginManager");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberModifyEmailAddress", method = RequestMethod.GET)
    public ModelAndView getMemberModifyEmailAddressr(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberModifyEmailAddress");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberModifyInfo", method = RequestMethod.GET)
    public ModelAndView getMemberModifyInfo(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberModifyInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberModifyPassword", method = RequestMethod.GET)
    public ModelAndView getMemberModifyPassword(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberModifyPassword");
        return modelAndView;
    }

    @RequestMapping(value = "/my-page/memberLeave", method = RequestMethod.GET)
    public ModelAndView getMemberLeave(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberLeave");
        return modelAndView;
    }


}
