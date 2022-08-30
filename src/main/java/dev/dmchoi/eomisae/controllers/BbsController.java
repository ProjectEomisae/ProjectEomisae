package dev.dmchoi.eomisae.controllers;

import dev.dmchoi.eomisae.dtos.bbs.BoardListArticleDto;
import dev.dmchoi.eomisae.models.PagingModel;
import dev.dmchoi.eomisae.services.bbs.ArticleReadService;
import dev.dmchoi.eomisae.services.bbs.BoardListService;
import dev.dmchoi.eomisae.vos.bbs.ArticleReadVo;
import dev.dmchoi.eomisae.vos.bbs.BoardListVo;
import dev.dmchoi.eomisae.vos.bbs.BoardListVoForNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller(value = "dev.dmchoi.eomisae.controllers.BbsController")
@RequestMapping(value = "/bbs")
public class BbsController {
    private final BoardListService boardListService;
    private final ArticleReadService articleReadService;

    @Autowired
    public BbsController(BoardListService boardListService, ArticleReadService articleReadService) {
        this.boardListService = boardListService;
        this.articleReadService = articleReadService;
    }

    @RequestMapping(value = "{urlName}", method = RequestMethod.GET)
    public ModelAndView getBoardList(ModelAndView modelAndView,
                                     @PathVariable(value = "urlName", required = true) String urlName,
                                     @RequestParam(name = "page") Optional<Integer> optionalPage,
                                     @RequestParam(name = "criteria", required = false) String criteria,
                                     @RequestParam(name = "keyword", required = false) String keyword,
                                     @RequestParam(name = "category", required = false) Optional<Integer> optionalCategory,
                                     BoardListVo boardListVo,
                                     BoardListVoForNo boardListVoForNo) {
        int category = optionalCategory.orElse(0);
        System.out.println("category 값 : " + category);
        int page = optionalPage.orElse(1);
        int totalRowCount;
        boardListVo.setUrlName(urlName);
        boardListVo.setResult(null);

        if (criteria == null || keyword == null) {
            totalRowCount = this.boardListService.boardTotalCountByBoardIndex(boardListVo);
            if (urlName.contains("al")) {
                totalRowCount = this.boardListService.boardTotalCount();
                System.out.println("cri가 null일 때 : urlName.contains(\"al\")" + totalRowCount);
            }
        } else {
            totalRowCount = this.boardListService.boardTotalCountByCriteria(boardListVo, criteria, keyword);
            if (urlName.contains("al")) {
                totalRowCount = this.boardListService.boardTotalCountAllByCriteria(criteria, keyword);
                System.out.println("cri가 null이 아닐 때 : urlName.contains(\"al\")" + totalRowCount);
            }
        }
        PagingModel paging = new PagingModel(totalRowCount, page);
        System.out.println(paging.totalRowCount);
        if (criteria == null || keyword == null) {
            this.boardListService.listBoard(boardListVo, paging);
            if (urlName.contains("al")) {
                this.boardListService.listBoardAll(boardListVo, paging);
                System.out.println("cri가 null일 때 : " + boardListVo.getArticles().size());
            }
        } else {
            this.boardListService.listBoardByCriteria(boardListVo, paging, criteria, keyword);
            if (urlName.contains("al")) {
                this.boardListService.listBoardAllByCriteria(boardListVo, paging, criteria, keyword);
                System.out.println("cri가 null이 아닐 때 : " + boardListVo.getArticles().size());
            }
        }

        // 카테고리가 설정될 때
        if (category > 0) {
            if (criteria != null) {
                totalRowCount = this.boardListService.boardTotalCountByCategory(boardListVo, category, criteria, keyword);
                paging = new PagingModel(totalRowCount, page);
                this.boardListService.listBoardByCategory(boardListVo, paging, category, criteria, keyword);
            }
            if (urlName.contains("al")) {
                if (criteria != null) {
                    totalRowCount = this.boardListService.boardTotalCountAllByCategory(category, criteria, keyword);
                    paging = new PagingModel(totalRowCount, page);
                    this.boardListService.listBoardAllByCategory(boardListVo, paging, category, criteria, keyword);
                }
            }
        }

        List<BoardListArticleDto> boardListVoForNoList = this.boardListService.getArticlesForNo();
        boardListVoForNo.setArticles(boardListVoForNoList);
        modelAndView.addObject("paging", paging);
        modelAndView.addObject("boardListVo", boardListVo);
        modelAndView.addObject("boardListVoForNo", boardListVoForNo);
        modelAndView.addObject("categoryEntities", this.boardListService.getCategories());
        modelAndView.addObject("title", boardListVo.getName());
        modelAndView.addObject("nonImageMain", "bbs/fragments/non-image-main");
//        modelAndView.addObject("al", "bbs/al/al");
        modelAndView.setViewName("bbs/board");
        return modelAndView;
    }

    @RequestMapping(value = "{urlName}/{aid}", method = RequestMethod.GET)
    public ModelAndView getBoardListDetail(ModelAndView modelAndView,
                                           @PathVariable(value = "urlName", required = true) String urlName,
                                           @PathVariable(value = "aid", required = true) int aid,
                                           ArticleReadVo articleReadVo,
                                           BoardListVo boardListVo,
                                           @RequestParam(name = "page") Optional<Integer> optionalPage,
                                           @RequestParam(name = "criteria", required = false) String criteria,
                                           @RequestParam(name = "keyword", required = false) String keyword,
                                           @RequestParam(name = "category", required = false) Optional<Integer> optionalCategory,
                                           BoardListVoForNo boardListVoForNo){
        int category = optionalCategory.orElse(0);
        System.out.println("category 값 : " + category);
        int page = optionalPage.orElse(1);
        int totalRowCount;
        boardListVo.setUrlName(urlName);
        boardListVo.setResult(null);

        if (criteria == null || keyword == null) {
            totalRowCount = this.boardListService.boardTotalCountByBoardIndex(boardListVo);
            if (urlName.contains("al")) {
                totalRowCount = this.boardListService.boardTotalCount();
                System.out.println("cri가 null일 때 : urlName.contains(\"al\")" + totalRowCount);
            }
        } else {
            totalRowCount = this.boardListService.boardTotalCountByCriteria(boardListVo, criteria, keyword);
            if (urlName.contains("al")) {
                totalRowCount = this.boardListService.boardTotalCountAllByCriteria(criteria, keyword);
                System.out.println("cri가 null이 아닐 때 : urlName.contains(\"al\")" + totalRowCount);
            }
        }
        PagingModel paging = new PagingModel(totalRowCount, page);
        System.out.println(paging.totalRowCount);
        if (criteria == null || keyword == null) {
            this.boardListService.listBoard(boardListVo, paging);
            if (urlName.contains("al")) {
                this.boardListService.listBoardAll(boardListVo, paging);
                System.out.println("cri가 null일 때 : " + boardListVo.getArticles().size());
            }
        } else {
            this.boardListService.listBoardByCriteria(boardListVo, paging, criteria, keyword);
            if (urlName.contains("al")) {
                this.boardListService.listBoardAllByCriteria(boardListVo, paging, criteria, keyword);
                System.out.println("cri가 null이 아닐 때 : " + boardListVo.getArticles().size());
            }
        }

        // 카테고리가 설정될 때
        if (category > 0) {
            if (criteria != null) {
                totalRowCount = this.boardListService.boardTotalCountByCategory(boardListVo, category, criteria, keyword);
                paging = new PagingModel(totalRowCount, page);
                this.boardListService.listBoardByCategory(boardListVo, paging, category, criteria, keyword);
            }
            if (urlName.contains("al")) {
                if (criteria != null) {
                    totalRowCount = this.boardListService.boardTotalCountAllByCategory(category, criteria, keyword);
                    paging = new PagingModel(totalRowCount, page);
                    this.boardListService.listBoardAllByCategory(boardListVo, paging, category, criteria, keyword);
                }
            }
        }

        List<BoardListArticleDto> boardListVoForNoList = this.boardListService.getArticlesForNo();
        boardListVoForNo.setArticles(boardListVoForNoList);


        boardListVo.setUrlName(urlName);
        articleReadVo.setResult(null);
        articleReadVo.setIndex(aid);
        this.articleReadService.readArticle(articleReadVo);
        System.out.println(articleReadVo.getComments().size());
        System.out.println(articleReadVo.getUserNickname());
        modelAndView.addObject("articleReadVo", articleReadVo);
        modelAndView.addObject("title", boardListVo.getName());
        modelAndView.addObject("paging", paging);
        modelAndView.addObject("boardListVo", boardListVo);
        modelAndView.addObject("boardListVoForNo", boardListVoForNo);
        modelAndView.addObject("categoryEntities", this.boardListService.getCategories());
        modelAndView.addObject("title", boardListVo.getName());
        modelAndView.setViewName("bbs/detail/detail");
        return modelAndView;
    }
        @RequestMapping(value = "al/boardWrite", method = RequestMethod.GET)
        public ModelAndView getAlBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/al/boardWrite-al");
            return modelAndView;
        }

        //   쇼핑정보
        @RequestMapping(value = "fs", method = RequestMethod.GET)
        public ModelAndView getFs (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/fs/fs");
            return modelAndView;
        }

        @RequestMapping(value = "ec/boardWrite", method = RequestMethod.GET)
        public ModelAndView getEcBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/ec/boardWrite-ec");
            return modelAndView;
        }

        @RequestMapping(value = "fs/boardWrite", method = RequestMethod.GET)
        public ModelAndView getFsBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/fs/boardWrite-fs");
            return modelAndView;
        }

        @RequestMapping(value = "os", method = RequestMethod.GET)
        public ModelAndView getOs (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/os/os");
            return modelAndView;
        }

        @RequestMapping(value = "os/boardWrite", method = RequestMethod.GET)
        public ModelAndView getOsBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/os/boardWrite-os");
            return modelAndView;
        }

        @RequestMapping(value = "rt", method = RequestMethod.GET)
        public ModelAndView getRt (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/rt/rt");
            return modelAndView;
        }

        @RequestMapping(value = "rt/boardWrite", method = RequestMethod.GET)
        public ModelAndView getRtBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/rt/boardWrite-rt");
            return modelAndView;
        }

        @RequestMapping(value = "dr", method = RequestMethod.GET)
        public ModelAndView getDr (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/dr/dr");
            return modelAndView;
        }

        @RequestMapping(value = "dr/boardWrite", method = RequestMethod.GET)
        public ModelAndView getDrBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/dr/boardWrite-dr");
            return modelAndView;
        }

        @RequestMapping(value = "fe/boardWrite", method = RequestMethod.GET)
        public ModelAndView getFeBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/fe/boardWrite-fe");
            return modelAndView;
        }

        @RequestMapping(value = "fh/boardWrite", method = RequestMethod.GET)
        public ModelAndView getFhBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/fh/boardWrite-fh");
            return modelAndView;
        }

        @RequestMapping(value = "qa/boardWrite", method = RequestMethod.GET)
        public ModelAndView getQaBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/qa/boardWrite-qa");
            return modelAndView;
        }

        @RequestMapping(value = "chc/boardWrite", method = RequestMethod.GET)
        public ModelAndView getChcBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/chc/boardWrite-chc");
            return modelAndView;
        }

        @RequestMapping(value = "jp/boardWrite", method = RequestMethod.GET)
        public ModelAndView getJpBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/jp/boardWrite-jp");
            return modelAndView;
        }

        @RequestMapping(value = "ui", method = RequestMethod.GET)
        public ModelAndView getUi (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/ui/ui");
            return modelAndView;
        }

        @RequestMapping(value = "ui/boardWrite", method = RequestMethod.GET)
        public ModelAndView getUiBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/ui/boardWrite-ui");
            return modelAndView;
        }

        //   구매인증
        @RequestMapping(value = "gl", method = RequestMethod.GET)
        public ModelAndView getGl (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/gl/gl");
            return modelAndView;
        }

        @RequestMapping(value = "gl/boardWrite", method = RequestMethod.GET)
        public ModelAndView getGlBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/gl/boardWrite-gl");
            return modelAndView;
        }

        @RequestMapping(value = "ha", method = RequestMethod.GET)
        public ModelAndView getHa (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/ha/ha");
            return modelAndView;
        }

        @RequestMapping(value = "ha/boardWrite", method = RequestMethod.GET)
        public ModelAndView getHaBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/ha/boardWrite-ha");
            return modelAndView;
        }

        //  실사후기
        @RequestMapping(value = "pot", method = RequestMethod.GET)
        public ModelAndView getPot (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/pot/pot");
            return modelAndView;
        }

        @RequestMapping(value = "pot/boardWrite", method = RequestMethod.GET)
        public ModelAndView getPotBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/pot/boardWrite-pot");
            return modelAndView;
        }

        @RequestMapping(value = "pop", method = RequestMethod.GET)
        public ModelAndView getPop (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/pop/pop");
            return modelAndView;
        }

        @RequestMapping(value = "pop/boardWrite", method = RequestMethod.GET)
        public ModelAndView getPopBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/pop/boardWrite-pop");
            return modelAndView;
        }

        @RequestMapping(value = "gm", method = RequestMethod.GET)
        public ModelAndView getGm (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/gm/gm");
            return modelAndView;
        }

        @RequestMapping(value = "gm/boardWrite", method = RequestMethod.GET)
        public ModelAndView getGmBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/gm/boardWrite-gm");
            return modelAndView;
        }

        //  데일리룩
        @RequestMapping(value = "dl", method = RequestMethod.GET)
        public ModelAndView getDl (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/dl/dl");
            return modelAndView;
        }

        @RequestMapping(value = "dl/boardWrite", method = RequestMethod.GET)
        public ModelAndView getDlBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/dl/boardWrite-dl");
            return modelAndView;
        }

        @RequestMapping(value = "ds", method = RequestMethod.GET)
        public ModelAndView getDs (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/ds/ds");
            return modelAndView;
        }

        @RequestMapping(value = "ds/boardWrite", method = RequestMethod.GET)
        public ModelAndView getDsBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/ds/boardWrite-ds");
            return modelAndView;
        }

        //  플리마켓
        @RequestMapping(value = "jo", method = RequestMethod.GET)
        public ModelAndView getJo (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/jo/jo");
            return modelAndView;
        }

        @RequestMapping(value = "jo/boardWrite", method = RequestMethod.GET)
        public ModelAndView getJoBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/jo/boardWrite-jo");
            return modelAndView;
        }

        @RequestMapping(value = "jo2", method = RequestMethod.GET)
        public ModelAndView getJo2 (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/jo2/jo2");
            return modelAndView;
        }

        @RequestMapping(value = "jo2/boardWrite", method = RequestMethod.GET)
        public ModelAndView getJo2BoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/jo2/boardWrite-jo2");
            return modelAndView;
        }

        @RequestMapping(value = "jo3", method = RequestMethod.GET)
        public ModelAndView getJo3 (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/jo3/jo3");
            return modelAndView;
        }

        @RequestMapping(value = "jo3/boardWrite", method = RequestMethod.GET)
        public ModelAndView getJo3BoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/jo3/boardWrite-jo3");
            return modelAndView;
        }

        @RequestMapping(value = "no/boardWrite", method = RequestMethod.GET)
        public ModelAndView getNoBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/no/boardWrite-no");
            return modelAndView;
        }

        @RequestMapping(value = "ev/boardWrite", method = RequestMethod.GET)
        public ModelAndView getEvBoardWrite (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/ev/boardWrite-ev");
            return modelAndView;
        }

        @RequestMapping(value = "/ms", method = RequestMethod.GET)
        public ModelAndView getMs (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/ms/ms");
            return modelAndView;
        }

        @RequestMapping(value = "/mail", method = RequestMethod.GET)
        public ModelAndView getMail (ModelAndView modelAndView){
            modelAndView.setViewName("bbs/mail/mail");
            return modelAndView;
        }

    }
