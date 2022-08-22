package dev.dmchoi.eomisae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "dev.dmchoi.eomisae.controllers.BbsController")
@RequestMapping(value = "/bbs")
public class BbsController {

//    @RequestMapping(value = "{bid}/detail/{aid}", method = RequestMethod.GET)
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView getArticleDetail(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/detail/detail");
        return modelAndView;
    }

    //   쇼핑정보
    @RequestMapping(value = "fs", method = RequestMethod.GET)
    public ModelAndView getFs(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/fs/fs");
        return modelAndView;
    }

    @RequestMapping(value = "ec/boardWrite", method = RequestMethod.GET)
    public ModelAndView getEcBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/ec/boardWrite-ec");
        return modelAndView;
    }

    @RequestMapping(value = "fs/boardWrite", method = RequestMethod.GET)
    public ModelAndView getFsBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/fs/boardWrite-fs");
        return modelAndView;
    }

    @RequestMapping(value = "os", method = RequestMethod.GET)
    public ModelAndView getOs(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/os/os");
        return modelAndView;
    }

    @RequestMapping(value = "os/boardWrite", method = RequestMethod.GET)
    public ModelAndView getOsBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/os/boardWrite-os");
        return modelAndView;
    }

    @RequestMapping(value = "rt", method = RequestMethod.GET)
    public ModelAndView getRt(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/rt/rt");
        return modelAndView;
    }

    @RequestMapping(value = "rt/boardWrite", method = RequestMethod.GET)
    public ModelAndView getRtBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/rt/boardWrite-rt");
        return modelAndView;
    }

    @RequestMapping(value = "dr", method = RequestMethod.GET)
    public ModelAndView getDr(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/dr/dr");
        return modelAndView;
    }

    @RequestMapping(value = "dr/boardWrite", method = RequestMethod.GET)
    public ModelAndView getDrBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/dr/boardWrite-dr");
        return modelAndView;
    }

    //   커뮤니티
    @RequestMapping(value = "fe", method = RequestMethod.GET)
    public ModelAndView getFe(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/fe/fe");
        return modelAndView;
    }

    @RequestMapping(value = "fe/boardWrite", method = RequestMethod.GET)
    public ModelAndView getFeBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/fe/boardWrite-fe");
        return modelAndView;
    }
    @RequestMapping(value = "fh", method = RequestMethod.GET)
    public ModelAndView getFh(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/fh/fh");
        return modelAndView;
    }

    @RequestMapping(value = "fh/boardWrite", method = RequestMethod.GET)
    public ModelAndView getFhBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/fh/boardWrite-fh");
        return modelAndView;
    }
    @RequestMapping(value = "qa", method = RequestMethod.GET)
    public ModelAndView getQa(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/qa/qa");
        return modelAndView;
    }

    @RequestMapping(value = "qa/boardWrite", method = RequestMethod.GET)
    public ModelAndView getQaBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/qa/boardWrite-qa");
        return modelAndView;
    }
    @RequestMapping(value = "chc", method = RequestMethod.GET)
    public ModelAndView getChc(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/chc/chc");
        return modelAndView;
    }

    @RequestMapping(value = "chc/boardWrite", method = RequestMethod.GET)
    public ModelAndView getChcBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/chc/boardWrite-chc");
        return modelAndView;
    }
    @RequestMapping(value = "jp", method = RequestMethod.GET)
    public ModelAndView getJp(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/jp/jp");
        return modelAndView;
    }

    @RequestMapping(value = "jp/boardWrite", method = RequestMethod.GET)
    public ModelAndView getJpBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/jp/boardWrite-jp");
        return modelAndView;
    }

    //   구매인증
    @RequestMapping(value = "gl", method = RequestMethod.GET)
    public ModelAndView getGl(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/gl/gl");
        return modelAndView;
    }

    @RequestMapping(value = "gl/boardWrite", method = RequestMethod.GET)
    public ModelAndView getGlBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/gl/boardWrite-gl");
        return modelAndView;
    }

    @RequestMapping(value = "ha", method = RequestMethod.GET)
    public ModelAndView getHa(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/ha/ha");
        return modelAndView;
    }

    @RequestMapping(value = "ha/boardWrite", method = RequestMethod.GET)
    public ModelAndView getHaBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/ha/boardWrite-ha");
        return modelAndView;
    }

    //  실사후기
    @RequestMapping(value = "pot", method = RequestMethod.GET)
    public ModelAndView getPot(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/pot/pot");
        return modelAndView;
    }

    @RequestMapping(value = "pot/boardWrite", method = RequestMethod.GET)
    public ModelAndView getPotBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/pot/boardWrite-pot");
        return modelAndView;
    }

    @RequestMapping(value = "pop", method = RequestMethod.GET)
    public ModelAndView getPop(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/pop/pop");
        return modelAndView;
    }

    @RequestMapping(value = "pop/boardWrite", method = RequestMethod.GET)
    public ModelAndView getPopBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/pop/boardWrite-pop");
        return modelAndView;
    }

    @RequestMapping(value = "gm", method = RequestMethod.GET)
    public ModelAndView getGm(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/gm/gm");
        return modelAndView;
    }

    @RequestMapping(value = "gm/boardWrite", method = RequestMethod.GET)
    public ModelAndView getGmBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/gm/boardWrite-gm");
        return modelAndView;
    }

    //  데일리룩
    @RequestMapping(value = "dl", method = RequestMethod.GET)
    public ModelAndView getDl(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/dl/dl");
        return modelAndView;
    }

    @RequestMapping(value = "dl/boardWrite", method = RequestMethod.GET)
    public ModelAndView getDlBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/dl/boardWrite-dl");
        return modelAndView;
    }

    @RequestMapping(value = "ds", method = RequestMethod.GET)
    public ModelAndView getDs(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/ds/ds");
        return modelAndView;
    }

    @RequestMapping(value = "ds/boardWrite", method = RequestMethod.GET)
    public ModelAndView getDsBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/ds/boardWrite-ds");
        return modelAndView;
    }

    //  플리마켓
    @RequestMapping(value = "jo", method = RequestMethod.GET)
    public ModelAndView getJo(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/jo/jo");
        return modelAndView;
    }

    @RequestMapping(value = "jo/boardWrite", method = RequestMethod.GET)
    public ModelAndView getJoBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/jo/boardWrite-jo");
        return modelAndView;
    }

    @RequestMapping(value = "jo2", method = RequestMethod.GET)
    public ModelAndView getJo2(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/jo2/jo2");
        return modelAndView;
    }

    @RequestMapping(value = "jo2/boardWrite", method = RequestMethod.GET)
    public ModelAndView getJo2BoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/jo2/boardWrite-jo2");
        return modelAndView;
    }

    @RequestMapping(value = "jo3", method = RequestMethod.GET)
    public ModelAndView getJo3(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/jo3/jo3");
        return modelAndView;
    }

    @RequestMapping(value = "jo3/boardWrite", method = RequestMethod.GET)
    public ModelAndView getJo3BoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/jo3/boardWrite-jo3");
        return modelAndView;
    }

//  운영관리

    @RequestMapping(value = "no", method = RequestMethod.GET)
    public ModelAndView getNo(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/no/no");
        return modelAndView;
    }

    @RequestMapping(value = "no/boardWrite", method = RequestMethod.GET)
    public ModelAndView getNoBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/no/boardWrite-no");
        return modelAndView;
    }
    @RequestMapping(value = "ev", method = RequestMethod.GET)
    public ModelAndView getEv(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/ev/ev");
        return modelAndView;
    }

    @RequestMapping(value = "ev/boardWrite", method = RequestMethod.GET)
    public ModelAndView getEvBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/ev/boardWrite-ev");
        return modelAndView;
    }

    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public ModelAndView getMail(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/mail/mail");
        return modelAndView;
    }

}
