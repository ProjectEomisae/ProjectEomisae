package dev.dmchoi.eomisae.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import dev.dmchoi.eomisae.dtos.bbs.ArticleReadCommentDto;
import dev.dmchoi.eomisae.dtos.bbs.BoardListArticleDto;
import dev.dmchoi.eomisae.dtos.bbs.BoardReadCommentDto;
import dev.dmchoi.eomisae.entities.bbs.*;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.bbs.ArticleWriteResult;
import dev.dmchoi.eomisae.enums.bbs.BoardListResult;
import dev.dmchoi.eomisae.models.PagingModel;
import dev.dmchoi.eomisae.services.bbs.ArticleReadService;
import dev.dmchoi.eomisae.services.bbs.BoardListService;
import dev.dmchoi.eomisae.utils.CryptoUtils;
import dev.dmchoi.eomisae.vos.bbs.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Transactional
    @RequestMapping(value = "{urlName}", method = RequestMethod.GET)
    public ModelAndView getBoardList(ModelAndView modelAndView,
                                     @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
                                     @PathVariable(value = "urlName", required = true) String urlName,
                                     @RequestParam(name = "page") Optional<Integer> optionalPage,
                                     @RequestParam(name = "criteria", required = false) String criteria,
                                     @RequestParam(name = "keyword", required = false) String keyword,
                                     @RequestParam(name = "category", required = false) Optional<Integer> optionalCategory,
                                     @RequestParam(name = "alignment", required = false) Optional<Integer> optionalAlignment,
                                     BoardListVo boardListVo,
                                     BoardListVoForNo boardListVoForNo,
                                     BoardListVoForFavorite boardListVoForFavorite) throws ClassCastException {
        int category = optionalCategory.orElse(0);
        int alignment = optionalAlignment.orElse(0);
        int page = optionalPage.orElse(1);
        int totalRowCount;
        boardListVo.setUrlName(urlName);
        boardListVo.setResult(null);
//        if (user == null) {
//            modelAndView.setViewName("redirect:/");
//        }
        if (criteria == null || keyword == null) {
            totalRowCount = this.boardListService.boardTotalCountByBoardIndex(boardListVo);
            if (urlName.contains("al")) {
                totalRowCount = this.boardListService.boardTotalCount();
//                System.out.println("cri가 null일 때 : urlName.contains(\"al\")" + totalRowCount);
            }
            if (urlName.contains("ui")) {
                totalRowCount = this.boardListService.commentTotalCountByBoardIndex(boardListVo);
            }

        } else {
            totalRowCount = this.boardListService.boardTotalCountByCriteria(boardListVo, criteria, keyword);
            if (urlName.contains("al")) {
                totalRowCount = this.boardListService.boardTotalCountAllByCriteria(criteria, keyword);
//                System.out.println("cri가 null이 아닐 때 : urlName.contains(\"al\")" + totalRowCount);
            }
            if (urlName.contains("ui")) {
                totalRowCount = this.boardListService.CommentTotalCountByCriteria(boardListVo, criteria, keyword);
            }
        }
        PagingModel paging = new PagingModel(totalRowCount, page);
//        System.out.println(paging.totalRowCount);
        if (criteria == null || keyword == null) {
            this.boardListService.listBoard(boardListVo, paging);
            if (urlName.contains("al")) {
                this.boardListService.listBoardAll(boardListVo, paging);
//                System.out.println("cri가 null일 때 : " + boardListVo.getArticles().size());
            }
            if (urlName.contains("ui")) {
                this.boardListService.readComment(boardListVo, paging);
            }

        } else {
            this.boardListService.listBoardByCriteria(boardListVo, paging, criteria, keyword);
            if (urlName.contains("al")) {
                this.boardListService.listBoardAllByCriteria(boardListVo, paging, criteria, keyword);
//                System.out.println("cri가 null이 아닐 때 : " + boardListVo.getArticles().size());
            }
            if (urlName.contains("ui")) {
                this.boardListService.listBoardCommentByCriteria(boardListVo, paging, criteria, keyword);
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
        // 정렬이 설정될 때
        if (alignment > 0) {
            if (criteria != null) {
                totalRowCount = this.boardListService.boardTotalCountByCriteria(boardListVo, criteria, keyword);
                paging = new PagingModel(totalRowCount, page);
                this.boardListService.listBoardByAlignment(boardListVo, paging, alignment, criteria, keyword);
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
        List<BoardListArticleDto> boardListVoForFavoriteList = this.boardListService.getNewArticlesForFavorite();
        boardListVoForFavorite.setArticles(boardListVoForFavoriteList);

//        System.out.println("유저 레벨 : " + user.getLevel());
//        System.out.println("게시판 레벨 : " + boardListVo.getListLevel());
//        if (boardListVo.getIndex() == 0) {
//            boardListVo.setResult(BoardListResult.NOT_FOUND);
//        } else if (boardListVo.getListLevel() > user.getLevel()) {
//            System.out.println("check");
//            boardListVo.setResult(BoardListResult.NOT_ALLOWED);
//            System.out.println(boardListVo.getResult());
//        }
        modelAndView.addObject("paging", paging);
        modelAndView.addObject("boardListVo", boardListVo);
        modelAndView.addObject("boardListVoForNo", boardListVoForNo);
        modelAndView.addObject("boardListVoForFavorite", boardListVoForFavorite);
        modelAndView.addObject("categoryEntities", this.boardListService.getCategories());
        modelAndView.addObject("title", boardListVo.getName());
        modelAndView.addObject("nonImageMain", "bbs/fragments/non-image-main");
        modelAndView.addObject("imageMain", "bbs/fragments/image-main");
//        modelAndView.addObject("al", "bbs/al/al");
//        }
        if (urlName.contains("ui")) {
            modelAndView.setViewName("bbs/ui/ui");
        } else {
            modelAndView.setViewName("bbs/board");
        }
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
                                           BoardListVoForNo boardListVoForNo,
                                           BoardListVoForFavorite boardListVoForFavorite) {
        int category = optionalCategory.orElse(0);
//        System.out.println("category 값 : " + category);
        int page = optionalPage.orElse(1);
        int totalRowCount;
        boardListVo.setUrlName(urlName);
        boardListVo.setResult(null);

        if (criteria == null || keyword == null) {
            totalRowCount = this.boardListService.boardTotalCountByBoardIndex(boardListVo);
            if (urlName.contains("al")) {
                totalRowCount = this.boardListService.boardTotalCount();
//                System.out.println("cri가 null일 때 : urlName.contains(\"al\")" + totalRowCount);
            }
        } else {
            totalRowCount = this.boardListService.boardTotalCountByCriteria(boardListVo, criteria, keyword);
            if (urlName.contains("al")) {
                totalRowCount = this.boardListService.boardTotalCountAllByCriteria(criteria, keyword);
//                System.out.println("cri가 null이 아닐 때 : urlName.contains(\"al\")" + totalRowCount);
            }
        }
        PagingModel paging = new PagingModel(totalRowCount, page);
//        System.out.println(paging.totalRowCount);
        if (criteria == null || keyword == null) {
            this.boardListService.listBoard(boardListVo, paging);
            if (urlName.contains("al")) {
                this.boardListService.listBoardAll(boardListVo, paging);
//                System.out.println("cri가 null일 때 : " + boardListVo.getArticles().size());
            }
        } else {
            this.boardListService.listBoardByCriteria(boardListVo, paging, criteria, keyword);
            if (urlName.contains("al")) {
                this.boardListService.listBoardAllByCriteria(boardListVo, paging, criteria, keyword);
//                System.out.println("cri가 null이 아닐 때 : " + boardListVo.getArticles().size());
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
        List<BoardListArticleDto> boardListVoForFavoriteList = this.boardListService.getNewArticlesForFavorite();
        boardListVoForFavorite.setArticles(boardListVoForFavoriteList);
        articleReadVo.setResult(null);
        articleReadVo.setIndex(aid);
        this.articleReadService.readArticle(articleReadVo);
//        System.out.println(articleReadVo.getComments().size());
//        System.out.println(articleReadVo.getUserNickname());
        modelAndView.addObject("articleReadVo", articleReadVo);
        modelAndView.addObject("title", boardListVo.getName());
        modelAndView.addObject("paging", paging);
        modelAndView.addObject("boardListVo", boardListVo);
        modelAndView.addObject("boardListVoForNo", boardListVoForNo);
        modelAndView.addObject("boardListVoForFavorite", boardListVoForFavorite);
        modelAndView.addObject("categoryEntities", this.boardListService.getCategories());
        modelAndView.setViewName("bbs/detail/detail");
        return modelAndView;
    }

    @RequestMapping(value = "{urlName}", method = RequestMethod.POST)
    public ModelAndView postBoardList(ModelAndView modelAndView,
                                            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
                                            @PathVariable(value = "urlName", required = true) String urlName,
                                            JoinCommentWriteVo joinCommentWriteVo,
                                            HttpServletResponse response) {
        if (user == null) {
            response.setStatus(404);
            return null;
        }
        joinCommentWriteVo.setUserIndex(user.getIndex());
        joinCommentWriteVo.setWrittenAt(new Date());
        joinCommentWriteVo.setResult(null);
        joinCommentWriteVo.setDeleted(false);
        this.articleReadService.writeJoinComment(urlName, user, joinCommentWriteVo);
        modelAndView.setViewName("redirect:/bbs/" + urlName);
        return modelAndView;
    }

    @RequestMapping(value = "{urlName}/{aid}", method = RequestMethod.POST)
    public ModelAndView postBoardListDetail(ModelAndView modelAndView,
                                            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
                                            @PathVariable(value = "urlName", required = true) String urlName,
                                            @PathVariable(value = "aid", required = true) int aid,
                                            ArticleCommentWriteVo articleCommentWriteVo,
                                            HttpServletResponse response) {
        if (user == null) {
            response.setStatus(404);
            return null;
        }
        articleCommentWriteVo.setUserIndex(user.getIndex());
        articleCommentWriteVo.setArticleIndex(aid);
        articleCommentWriteVo.setWrittenAt(new Date());
        articleCommentWriteVo.setResult(null);
        articleCommentWriteVo.setDeleted(false);
        this.articleReadService.writeComment(urlName, user, articleCommentWriteVo);
        modelAndView.setViewName("redirect:/bbs/" + urlName + '/' + aid);
        return modelAndView;
    }

    @RequestMapping(value = "{urlName}/{bid}/{aid}/article-buy", method = RequestMethod.GET)
    @ResponseBody
    public String getArticleBuy(
            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
            @PathVariable(value = "urlName", required = true) String urlName,
            @PathVariable(value = "bid", required = true) int bid,
            @PathVariable(value = "aid", required = true) int aid,
            BoardListVo boardListVo,
            ArticleBuyEntity articleBuy) {
        articleBuy.setUserIndex(user.getIndex());
        articleBuy.setBoardIndex(bid);
        articleBuy.setArticleIndex(aid);
        this.boardListService.addArticleBuy(boardListVo, articleBuy);
        JSONObject responseJson = new JSONObject();
        responseJson.put("result", boardListVo.getResult().name().toLowerCase());
        return responseJson.toString();
    }

    @RequestMapping(value = "{urlName}/{bid}/{aid}/article-like", method = RequestMethod.GET)
    @ResponseBody
    public String getArticleLike(
            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
            @PathVariable(value = "urlName", required = true) String urlName,
            @PathVariable(value = "bid", required = true) int bid,
            @PathVariable(value = "aid", required = true) int aid,
            BoardListVo boardListVo,
            ArticleLikeEntity articleLike) {
        articleLike.setUserIndex(user.getIndex());
        articleLike.setBoardIndex(bid);
        articleLike.setArticleIndex(aid);
        this.boardListService.addArticleLike(boardListVo, articleLike);
        JSONObject responseJson = new JSONObject();
        responseJson.put("result", boardListVo.getResult().name().toLowerCase());
        return responseJson.toString();
    }

    @RequestMapping(value = "{urlName}/{aid}/{cid}/like", method = RequestMethod.GET)
    @ResponseBody
    public String getCommentLike(
            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
            @PathVariable(value = "urlName", required = true) String urlName,
            @PathVariable(value = "aid", required = true) int aid,
            @PathVariable(value = "cid", required = true) int cid,
            BoardListVo boardListVo,
            CommentLikeEntity commentLike) {
        commentLike.setUserIndex(user.getIndex());
        commentLike.setArticleIndex(aid);
        commentLike.setCommentIndex(cid);
        this.boardListService.addCommentLike(boardListVo, commentLike);
        JSONObject responseJson = new JSONObject();
        responseJson.put("result", boardListVo.getResult().name().toLowerCase());
        return responseJson.toString();
    }

    @RequestMapping(value = "{urlName}/add", method = RequestMethod.GET)
    public ModelAndView getAdd(ModelAndView modelAndView,
                               @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
                               @PathVariable(value = "urlName", required = true) String urlName,
                               BoardListVo boardListVo,
                               HttpServletResponse response) {
        boardListVo.setUrlName(urlName);
        boardListVo.setResult(null);
        if (user == null) {
            response.setStatus(404);
//            modelAndView.setViewName("redirect:/bbs/" + boardListVo.getUrlName());
            return null;
        }
        BoardEntity boardEntity = this.boardListService.boardByUrlName(boardListVo);
        boardListVo.setName(boardEntity.getName());
        boardListVo.setId(boardEntity.getId());
        boardListVo.setIndex(boardEntity.getIndex());
        boardListVo.setWriteLevel(boardEntity.getWriteLevel());
//        System.out.println(user.getLevel());
//        System.out.println(boardListVo.getWriteLevel());
        modelAndView.addObject(UserEntity.ATTRIBUTE_NAME, user);
        modelAndView.addObject("title", boardListVo.getName());
        modelAndView.addObject("boardListVo", boardListVo);
        modelAndView.addObject("categoryEntities", this.boardListService.getCategories());
        modelAndView.setViewName("bbs/add");
        return modelAndView;
    }

    @RequestMapping(value = "{urlName}/add", method = RequestMethod.POST)
    public ModelAndView postAdd(
            ModelAndView modelAndView,
            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
            @PathVariable(value = "urlName", required = true) String urlName,
            HttpServletResponse response,
            ArticleWriteVo articleWriteVo) {
        articleWriteVo.setBoardUrlName(urlName);
        if (user == null) {
            response.setStatus(404);
            return null;
        }
        articleWriteVo.setResult(null);
        this.articleReadService.addArticle(user, articleWriteVo);
        System.out.println("작성 결과 : " + articleWriteVo.getResult());
        modelAndView.addObject("articleWriteVo", articleWriteVo);
        modelAndView.addObject(UserEntity.ATTRIBUTE_NAME, user);
        modelAndView.setViewName("redirect:/bbs/" + articleWriteVo.getBoardUrlName());
        return modelAndView;
    }

    @RequestMapping(value = "{urlName}/{aid}/modify", method = RequestMethod.GET)
    public ModelAndView getModify(ModelAndView modelAndView,
                                  @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
                                  @PathVariable(value = "urlName", required = true) String urlName,
                                  @PathVariable(value = "aid", required = true) int aid,
                                  BoardListVo boardListVo,
                                  ArticleReadVo articleReadVo,
                                  HttpServletResponse response) {
        boardListVo.setUrlName(urlName);
        articleReadVo.setIndex(aid);
        boardListVo.setResult(null);
        if (user == null) {
            response.setStatus(404);
//            modelAndView.setViewName("redirect:/bbs/" + boardListVo.getUrlName());
            return null;
        }
        BoardEntity boardEntity = this.boardListService.boardByUrlName(boardListVo);
        boardListVo.setName(boardEntity.getName());
        boardListVo.setId(boardEntity.getId());
        boardListVo.setIndex(boardEntity.getIndex());
        boardListVo.setWriteLevel(boardEntity.getWriteLevel());
//        System.out.println(user.getLevel());
//        System.out.println(boardListVo.getWriteLevel());
        this.articleReadService.readArticle(articleReadVo);
        modelAndView.addObject(UserEntity.ATTRIBUTE_NAME, user);
        modelAndView.addObject("articleReadVo", articleReadVo);
        modelAndView.addObject("categoryEntities", this.boardListService.getCategories());
        modelAndView.addObject("title", boardListVo.getName());
        modelAndView.setViewName("bbs/modify");
        return modelAndView;
    }

    @RequestMapping(value = "{urlName}/{aid}/modify", method = RequestMethod.POST)
    public ModelAndView postModify(ModelAndView modelAndView,
                                   @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
                                   @PathVariable(value = "urlName", required = true) String urlName,
                                   @PathVariable(value = "aid", required = true) int aid,
                                   BoardListVo boardListVo,
                                   ArticleWriteVo articleWriteVo) {
        articleWriteVo.setBoardUrlName(urlName);
        articleWriteVo.setIndex(aid);
        if (user == null) {
            modelAndView.setViewName("redirect:/bbs/" + boardListVo.getUrlName());
            return null;
        }
        this.articleReadService.modifyArticle(articleWriteVo);
        modelAndView.addObject(UserEntity.ATTRIBUTE_NAME, user);
        modelAndView.addObject("categoryEntities", this.boardListService.getCategories());
        modelAndView.addObject("title", boardListVo.getName());
        modelAndView.setViewName("redirect:/bbs/" + articleWriteVo.getBoardUrlName());
        return modelAndView;
    }

    @RequestMapping(value = "{urlName}/{cid}/like", method = RequestMethod.GET)
    @ResponseBody
    public String getJoinCommentLike(
            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
            @PathVariable(value = "urlName", required = true) String urlName,
            @PathVariable(value = "cid", required = true) int cid,
            BoardListVo boardListVo,
            JoinCommentLikeEntity joinCommentLike) {
        joinCommentLike.setUserIndex(user.getIndex());
        joinCommentLike.setCommentIndex(cid);
        this.boardListService.addJoinCommentLike(boardListVo, joinCommentLike);
        JSONObject responseJson = new JSONObject();
        responseJson.put("result", boardListVo.getResult().name().toLowerCase());
        return responseJson.toString();
    }

    @RequestMapping(value = "{urlName}/{aid}/delete", method = RequestMethod.GET)
    public ModelAndView getArticleDelete(
            HttpServletResponse response,
            ModelAndView modelAndView,
            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
            @PathVariable(value = "urlName", required = true) String urlName,
            @PathVariable(value = "aid", required = true) int aid,
            ArticleDeleteVo articleDeleteVo) {
        System.out.println(urlName);
        if (user == null) {
            response.setStatus(404);
            return null;
        }
        articleDeleteVo.setIndex(aid);
        articleDeleteVo.setBoardUrlName(urlName);
        System.out.println(articleDeleteVo.getIndex());
        System.out.println(articleDeleteVo.getBoardUrlName());
        this.articleReadService.deleteArticle(user, articleDeleteVo);
        modelAndView.setViewName("redirect:/bbs/" + urlName);
        return modelAndView;
    }

    @RequestMapping(value = "{urlName}/{cid}/comment-delete", method = RequestMethod.GET)
    public ModelAndView getJoinCommentDelete(
            HttpServletResponse response,
            ModelAndView modelAndView,
            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
            @PathVariable(value = "urlName", required = true) String urlName,
            @PathVariable(value = "cid", required = true) int cid,
            JoinCommentDeleteVo joinCommentDeleteVo) {
        if (user == null) {
            response.setStatus(404);
            return null;
        }
        joinCommentDeleteVo.setIndex(cid);
        joinCommentDeleteVo.setResult(null);
        joinCommentDeleteVo.setUserIndex(user.getIndex());
        this.boardListService.deleteComment(user, joinCommentDeleteVo);
        modelAndView.setViewName("redirect:/bbs/" + urlName);
        return modelAndView;
    }

    @RequestMapping(value = "{urlName}/{aid}/{cid}/delete", method = RequestMethod.GET)
    public ModelAndView getCommentDelete(
            HttpServletResponse response,
            ModelAndView modelAndView,
            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
            @PathVariable(value = "urlName", required = true) String urlName,
            @PathVariable(value = "aid", required = true) int aid,
            @PathVariable(value = "cid", required = true) int cid,
            ArticleCommentDeleteVo articleCommentDeleteVo) {
        System.out.println(urlName);
        if (user == null) {
            response.setStatus(404);
            return null;
        }
        articleCommentDeleteVo.setIndex(cid);
        articleCommentDeleteVo.setArticleIndex(aid);
        articleCommentDeleteVo.setResult(null);
        articleCommentDeleteVo.setUserIndex(user.getIndex());
        System.out.println(articleCommentDeleteVo.getIndex());
        this.articleReadService.updateForDeletingArticleComment(user, articleCommentDeleteVo);
        // 댓글을 삭제하는 것이 아닌, isDeleted를 true로 변경. true인 댓글은 '삭제된 댓글입니다'를 표시하며 남겨두기 위함.
        modelAndView.setViewName("redirect:/bbs/" + urlName + '/' + aid);
        return modelAndView;
    }

    @RequestMapping(value = "download-image", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getDownloadImage(
            @RequestParam(name = "id", required = false) String id) {
        ImageEntity imageEntity = null;
        if (id != null) {
            imageEntity = this.articleReadService.getImage(id);
        }
        byte[] data = null;
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (imageEntity != null && imageEntity.getData() != null) {
            data = imageEntity.getData();
            headers.add("Content-Type", imageEntity.getFileType());
            headers.add("Content-Disposition", String.format("attachment;" +
                    "filename=\"%s\"", imageEntity.getFileName()));
            headers.add("Content-Length", String.valueOf(data.length));
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(data, headers, status);
    }

    @RequestMapping(value = "/add/upload-image", method = RequestMethod.POST)
    @ResponseBody
    public String postAddUploadImage(
            HttpServletResponse response,
            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
            @RequestParam(name = "upload") MultipartFile[] images) throws IOException {
        if (user == null) {
            response.setStatus(404);
            return null;
        }
        ImageEntity[] imageEntities = new ImageEntity[images.length];
        for (int i = 0; i < images.length; i++) {
            MultipartFile image = images[i];
            Date createdAt = new Date();
            String id = String.format("%s%s%f",
                    new SimpleDateFormat("yyyyMMddHHmmssSSS").format(createdAt),
                    image.getOriginalFilename(),
                    Math.random());
            id = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, id);
            imageEntities[i] = new ImageEntity(id, createdAt,
                    image.getOriginalFilename(),
                    image.getContentType(),
                    image.getBytes());
        }
        this.articleReadService.uploadImages(imageEntities);
        JSONObject responseJson = new JSONObject();
        JSONArray urlJson = new JSONArray();
        for (ImageEntity imageEntity : imageEntities) {
            urlJson.put(String.format("http://127.0.0.1:8080/bbs/download-image?id=%s", imageEntity.getId()));
        }
        responseJson.put("url", urlJson);
        return responseJson.toString();
        // TODO: 2022/09/21 업로드시 들어간 이미지 중 하나를 해당 게시글의 썸네일 이미지로 지정하려 한다.
        //  Article 테이블에는 thumbnailId 컬럼을 추가할 예정. 업로드시 이미지 고유의 id를 게시글에 어떻게 전달하면 좋을까?
        //  1. 업로드시 JSON으로 전달된 url에서 id를 가져와 form에 전달하려 하였지만, 에디터 자체의 xhr처리 로직에서 이 부분을 어떻게 다루어야 할 지 몰라서 PASS
        //  2.

    }

//    @RequestMapping(value = "/add/upload-image-button", method = RequestMethod.POST)
//    @ResponseBody
//    public String postAddUploadImageButton(
//            HttpServletResponse response,
//            @RequestAttribute(value = UserEntity.ATTRIBUTE_NAME, required = false) UserEntity user,
//            @RequestParam(value= "file") MultipartFile[] images) throws IOException {
//        if (user == null) {
//            response.setStatus(404);
//            return null;        }
//        ImageEntity[] imageEntities = new ImageEntity[images.length];
//        for (int i = 0; i < images.length; i++) {
//            MultipartFile image = images[i];
//            Date createdAt = new Date();
//            String id = String.format("%s%s%f",
//                    new SimpleDateFormat("yyyyMMddHHmmssSSS").format(createdAt),
//                    image.getOriginalFilename(),
//                    Math.random());
//            id = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, id);
//            imageEntities[i] = new ImageEntity(id, createdAt,
//                    image.getOriginalFilename(),
//                    image.getContentType(),
//                    image.getBytes());
//        }
//        this.articleReadService.uploadImages(imageEntities);
//        JSONObject responseJson = new JSONObject();
//        for (ImageEntity imageEntity : imageEntities) {
//        responseJson.put("id", imageEntity.getId());
//        responseJson.put("fileType", imageEntity.getFileType());
//        responseJson.put("fileName", imageEntity.getFileName());
//        responseJson.put("size", imageEntity.getData().length);
//        }
//        JSONArray urlJson = new JSONArray();
//        for (ImageEntity imageEntity : imageEntities) {
//            urlJson.put(String.format("http://127.0.0.1:8080/bbs/download-image?id=%s", imageEntity.getId()));
//        }
//        responseJson.put("url", urlJson);
//        return responseJson.toString();
//    }

    @RequestMapping(value = "/ms", method = RequestMethod.GET)
    public ModelAndView getMs(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs/ms/ms");
        return modelAndView;
    }

    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public ModelAndView getMail(ModelAndView modelAndView,
                                BoardListVoForFavorite boardListVoForFavorite) {
        List<BoardListArticleDto> boardListVoForFavoriteList = this.boardListService.getNewArticlesForFavorite();
        boardListVoForFavorite.setArticles(boardListVoForFavoriteList);
        modelAndView.addObject("boardListVoForFavorite", boardListVoForFavorite);
        modelAndView.setViewName("bbs/mail/mail");
        return modelAndView;
    }

}
