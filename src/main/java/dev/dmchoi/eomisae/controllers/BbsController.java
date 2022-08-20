package dev.dmchoi.eomisae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "dev.dmchoi.eomisae.controllers.BbsController")
@RequestMapping(value = "/bbs")
public class BbsController {


    //   쇼핑정보
    @RequestMapping(value = "fs", method = RequestMethod.GET)
    public ModelAndView getFs(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/fs/fs");
        return modelAndView;
    }

    @RequestMapping(value = "fs/boardWrite", method = RequestMethod.GET)
    public ModelAndView getFsBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/fs/boardWrite-fs");
        return modelAndView;
    }

    @RequestMapping(value = "os", method = RequestMethod.GET)
    public ModelAndView getOs(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/os/os");
        return modelAndView;
    }

    @RequestMapping(value = "os/boardWrite", method = RequestMethod.GET)
    public ModelAndView getOsBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/os/boardWrite-os");
        return modelAndView;
    }

    @RequestMapping(value = "rt", method = RequestMethod.GET)
    public ModelAndView getRt(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/rt/rt");
        return modelAndView;
    }

    @RequestMapping(value = "rt/boardWrite", method = RequestMethod.GET)
    public ModelAndView getRtBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/rt/boardWrite-rt");
        return modelAndView;
    }

    @RequestMapping(value = "dr", method = RequestMethod.GET)
    public ModelAndView getDr(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/dr/dr");
        return modelAndView;
    }

    @RequestMapping(value = "dr/boardWrite", method = RequestMethod.GET)
    public ModelAndView getDrBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/dr/boardWrite-dr");
        return modelAndView;
    }

    //   커뮤니티
    @RequestMapping(value = "fe", method = RequestMethod.GET)
    public ModelAndView getFe(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/fe/fe");
        return modelAndView;
    }

    @RequestMapping(value = "fe/boardWrite", method = RequestMethod.GET)
    public ModelAndView getFeBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/fe/boardWrite-fe");
        return modelAndView;
    }
    @RequestMapping(value = "fh", method = RequestMethod.GET)
    public ModelAndView getFh(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/fh/fh");
        return modelAndView;
    }

    @RequestMapping(value = "fh/boardWrite", method = RequestMethod.GET)
    public ModelAndView getFhBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/fh/boardWrite-fh");
        return modelAndView;
    }
    @RequestMapping(value = "qa", method = RequestMethod.GET)
    public ModelAndView getQa(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/qa/qa");
        return modelAndView;
    }

    @RequestMapping(value = "qa/boardWrite", method = RequestMethod.GET)
    public ModelAndView getQaBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/qa/boardWrite-qa");
        return modelAndView;
    }
    @RequestMapping(value = "chc", method = RequestMethod.GET)
    public ModelAndView getChc(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/chc/chc");
        return modelAndView;
    }

    @RequestMapping(value = "chc/boardWrite", method = RequestMethod.GET)
    public ModelAndView getChcBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/chc/boardWrite-chc");
        return modelAndView;
    }
    @RequestMapping(value = "jp", method = RequestMethod.GET)
    public ModelAndView getJp(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/jp/jp");
        return modelAndView;
    }

    @RequestMapping(value = "jp/boardWrite", method = RequestMethod.GET)
    public ModelAndView getJpBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/jp/boardWrite-jp");
        return modelAndView;
    }

    //   구매인증
    @RequestMapping(value = "gl", method = RequestMethod.GET)
    public ModelAndView getGl(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/gl/gl");
        return modelAndView;
    }

    @RequestMapping(value = "gl/boardWrite", method = RequestMethod.GET)
    public ModelAndView getGlBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/gl/boardWrite-gl");
        return modelAndView;
    }

    @RequestMapping(value = "ha", method = RequestMethod.GET)
    public ModelAndView getHa(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/ha/ha");
        return modelAndView;
    }

    @RequestMapping(value = "ha/boardWrite", method = RequestMethod.GET)
    public ModelAndView getHaBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/ha/boardWrite-ha");
        return modelAndView;
    }

    //  실사후기
    @RequestMapping(value = "pot", method = RequestMethod.GET)
    public ModelAndView getPot(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/pot/pot");
        return modelAndView;
    }

    @RequestMapping(value = "pot/boardWrite", method = RequestMethod.GET)
    public ModelAndView getPotBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/pot/boardWrite-pot");
        return modelAndView;
    }

    @RequestMapping(value = "pop", method = RequestMethod.GET)
    public ModelAndView getPop(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/pop/pop");
        return modelAndView;
    }

    @RequestMapping(value = "pop/boardWrite", method = RequestMethod.GET)
    public ModelAndView getPopBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/pop/boardWrite-pop");
        return modelAndView;
    }

    @RequestMapping(value = "gm", method = RequestMethod.GET)
    public ModelAndView getGm(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/gm/gm");
        return modelAndView;
    }

    @RequestMapping(value = "gm/boardWrite", method = RequestMethod.GET)
    public ModelAndView getGmBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/gm/boardWrite-gm");
        return modelAndView;
    }

    //  데일리룩
    @RequestMapping(value = "dl", method = RequestMethod.GET)
    public ModelAndView getDl(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/dl/dl");
        return modelAndView;
    }

    @RequestMapping(value = "dl/boardWrite", method = RequestMethod.GET)
    public ModelAndView getDlBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/dl/boardWrite-dl");
        return modelAndView;
    }

    @RequestMapping(value = "ds", method = RequestMethod.GET)
    public ModelAndView getDs(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/ds/ds");
        return modelAndView;
    }

    @RequestMapping(value = "ds/boardWrite", method = RequestMethod.GET)
    public ModelAndView getDsBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/ds/boardWrite-ds");
        return modelAndView;
    }

    //  플리마켓
    @RequestMapping(value = "jo", method = RequestMethod.GET)
    public ModelAndView getJo(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/jo/jo");
        return modelAndView;
    }

    @RequestMapping(value = "jo/boardWrite", method = RequestMethod.GET)
    public ModelAndView getJoBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/jo/boardWrite-jo");
        return modelAndView;
    }

    @RequestMapping(value = "jo2", method = RequestMethod.GET)
    public ModelAndView getJo2(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/jo2/jo2");
        return modelAndView;
    }

    @RequestMapping(value = "jo2/boardWrite", method = RequestMethod.GET)
    public ModelAndView getJo2BoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/jo2/boardWrite-jo2");
        return modelAndView;
    }

    @RequestMapping(value = "jo3", method = RequestMethod.GET)
    public ModelAndView getJo3(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/jo3/jo3");
        return modelAndView;
    }

    @RequestMapping(value = "jo3/boardWrite", method = RequestMethod.GET)
    public ModelAndView getJo3BoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/jo3/boardWrite-jo3");
        return modelAndView;
    }

//  운영관리

    @RequestMapping(value = "no", method = RequestMethod.GET)
    public ModelAndView getNo(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/no/no");
        return modelAndView;
    }

    @RequestMapping(value = "no/boardWrite", method = RequestMethod.GET)
    public ModelAndView getNoBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/no/boardWrite-no");
        return modelAndView;
    }
    @RequestMapping(value = "ev", method = RequestMethod.GET)
    public ModelAndView getEv(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/ev/ev");
        return modelAndView;
    }

    @RequestMapping(value = "ev/boardWrite", method = RequestMethod.GET)
    public ModelAndView getEvBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/ev/boardWrite-ev");
        return modelAndView;
    }

}
