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

    @RequestMapping(value = "/my-page/memberOwnDocument", method = RequestMethod.GET)
    public ModelAndView getMemberOwnDocument(ModelAndView modelAndView) {
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

    @RequestMapping(value = "/my-page/memberLoginlogHistories", method = RequestMethod.GET)
    public ModelAndView getMemberLoginlogHistories(ModelAndView modelAndView) {
        modelAndView.setViewName("user/my-page/memberLoginlogHistories");
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

}
