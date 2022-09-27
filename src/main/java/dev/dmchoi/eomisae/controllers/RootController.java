package dev.dmchoi.eomisae.controllers;

import dev.dmchoi.eomisae.dtos.bbs.BoardListArticleDto;
import dev.dmchoi.eomisae.entities.bbs.BoardEntity;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.services.SystemService;
import dev.dmchoi.eomisae.services.UserService;
import dev.dmchoi.eomisae.services.bbs.BoardListService;
import dev.dmchoi.eomisae.vos.bbs.BoardListVo;
import dev.dmchoi.eomisae.vos.bbs.BoardListVoForEv;
import dev.dmchoi.eomisae.vos.bbs.BoardListVoForNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "dev.dmchoi.eomisae.controllers.RootController")
@RequestMapping(value = "/")
public class RootController extends StandardController {
    private final UserService userService;
    private final BoardListService boardListService;

    @Autowired
    private RootController(SystemService systemService, UserService userService, BoardListService boardListService) {
        super(systemService);
        this.userService = userService;
        this.boardListService = boardListService;
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
                                 ModelAndView modelAndView,
                                 BoardListVo boardListVo,
                                 BoardListVoForNo boardListVoForNo,
                                 BoardListVoForEv boardListVoForEv) {
        boardListVo.setArticles(this.boardListService.getNewArticlesForAll());
        List<BoardListArticleDto> boardListVoForNoList = this.boardListService.getNewArticlesForNo();
        boardListVoForNo.setArticles(boardListVoForNoList);
        List<BoardListArticleDto> boardListVoForEvList = this.boardListService.getNewArticlesForEv();
        boardListVoForEv.setArticles(boardListVoForEvList);
        modelAndView.addObject("boardListVo", boardListVo);
        modelAndView.addObject("boardListVoForNo", boardListVoForNo);
        modelAndView.addObject("boardListVoForEv", boardListVoForEv);
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
