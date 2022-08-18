package dev.dmchoi.eomisae.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "dev.dmchoi.eomisae.controllers.RootController")
@RequestMapping(value = "/")
public class RootController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex(ModelAndView modelAndView) {
        modelAndView.setViewName("root/index");
        return modelAndView;
    }

    //   쇼핑정보
    @RequestMapping(value = "fs", method = RequestMethod.GET)
    public ModelAndView getFs(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/fs/fs");
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

    @RequestMapping(value = "fe/boardWrite", method = RequestMethod.GET)
    public ModelAndView getFeBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/fe/boardWrite-fe");
        return modelAndView;
    }

    @RequestMapping(value = "fh/boardWrite", method = RequestMethod.GET)
    public ModelAndView getFhBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/fh/boardWrite-fh");
        return modelAndView;
    }

    @RequestMapping(value = "qa/boardWrite", method = RequestMethod.GET)
    public ModelAndView getQaBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/qa/boardWrite-qa");
        return modelAndView;
    }

    @RequestMapping(value = "chc/boardWrite", method = RequestMethod.GET)
    public ModelAndView getChcBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/chc/boardWrite-chc");
        return modelAndView;
    }

    @RequestMapping(value = "ds/boardWrite", method = RequestMethod.GET)
    public ModelAndView getDsBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/ds/boardWrite-ds");
        return modelAndView;
    }

    @RequestMapping(value = "dl/boardWrite", method = RequestMethod.GET)
    public ModelAndView getDlBoardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("boards/dl/boardWrite-dl");
        return modelAndView;
    }



}
