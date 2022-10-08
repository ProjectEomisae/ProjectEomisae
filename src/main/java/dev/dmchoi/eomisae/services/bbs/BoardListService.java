package dev.dmchoi.eomisae.services.bbs;

import dev.dmchoi.eomisae.dtos.bbs.BoardListArticleDto;
import dev.dmchoi.eomisae.dtos.bbs.BoardReadCommentDto;
import dev.dmchoi.eomisae.entities.bbs.*;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.bbs.BoardListResult;
import dev.dmchoi.eomisae.enums.bbs.JoinCommentDeleteResult;
import dev.dmchoi.eomisae.mappers.bbs.IBoardListMapper;
import dev.dmchoi.eomisae.mappers.IUserMapper;
import dev.dmchoi.eomisae.models.PagingModel;
import dev.dmchoi.eomisae.vos.bbs.BoardListVo;
import dev.dmchoi.eomisae.vos.bbs.JoinCommentDeleteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "dev.dmchoi.eomisae.services.bbs.BoardListService")
public class BoardListService {
    private final IBoardListMapper boardListMapper;
    private final IUserMapper userMapper;

    @Autowired
    public BoardListService(IBoardListMapper boardListMapper, IUserMapper userMapper) {
        this.boardListMapper = boardListMapper;
        this.userMapper = userMapper;
    }

    public void addArticleBuy(BoardListVo boardListVo, ArticleBuyEntity articleBuy) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        ArticleBuyEntity articleBuyEntity = this.boardListMapper.selectArticleBuyByUser(articleBuy.getUserIndex(), articleBuy.getBoardIndex() , articleBuy.getArticleIndex());
        if (articleBuyEntity != null) {
            boardListVo.setResult(BoardListResult.NOT_ALLOWED);
            return;
        }
        articleBuy.setChecked(true);
        this.boardListMapper.insertArticleBuy(articleBuy);
        boardListVo.setResult(BoardListResult.SUCCESS);
        ArticleEntity articleEntity = this.boardListMapper.selectArticle(articleBuy.getBoardIndex(), articleBuy.getArticleIndex());
        articleEntity.setBuy(articleEntity.getBuy() + 1);
        this.boardListMapper.updateArticleForBuy(articleEntity.getBuy(), articleEntity.getBoardIndex(), articleEntity.getIndex());
    }

    @Transactional
    public void addArticleView(BoardListVo boardListVo, ArticleViewEntity articleView) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        ArticleViewEntity articleViewEntity = this.boardListMapper.selectArticleViewByUser(articleView.getUserIndex(), boardEntity.getIndex() , articleView.getArticleIndex());
        UserEntity user = this.userMapper.selectUserByIndex(articleView.getUserIndex());
        if (articleViewEntity != null || user.getLevel() < boardEntity.getReadLevel()) {
            boardListVo.setResult(BoardListResult.NOT_ALLOWED);
            return;
        }
        if (user == null) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        articleView.setChecked(true);
        articleView.setBoardIndex(boardEntity.getIndex());
        this.boardListMapper.insertArticleView(articleView);
        boardListVo.setResult(BoardListResult.SUCCESS);
        ArticleEntity articleEntity = this.boardListMapper.selectArticle(boardEntity.getIndex(), articleView.getArticleIndex());
        articleEntity.setView(articleEntity.getView() + 1);
        this.boardListMapper.updateArticleForView(articleEntity.getView(), boardEntity.getIndex(), articleEntity.getIndex());
    }
    @Transactional
    public void addArticleLike(BoardListVo boardListVo, ArticleLikeEntity articleLike) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        ArticleLikeEntity articleLikeEntity = this.boardListMapper.selectArticleLikeByUser(articleLike.getUserIndex(), articleLike.getBoardIndex() , articleLike.getArticleIndex());
        if (articleLikeEntity != null) {
            boardListVo.setResult(BoardListResult.NOT_ALLOWED);
            return;
        }
        articleLike.setChecked(true);
        this.boardListMapper.insertArticleLike(articleLike);
        boardListVo.setResult(BoardListResult.SUCCESS);
        ArticleEntity articleEntity = this.boardListMapper.selectArticle(articleLike.getBoardIndex(), articleLike.getArticleIndex());
        articleEntity.setLike(articleEntity.getLike() + 1);
        this.boardListMapper.updateArticleForLike(articleEntity.getLike(), articleEntity.getBoardIndex(), articleEntity.getIndex());
    }

    @Transactional
    public void addArticleReport(BoardListVo boardListVo, ArticleReportEntity articleReport) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        ArticleReportEntity articleReportEntity = this.boardListMapper.selectArticleReportByUser(articleReport.getUserIndex(), articleReport.getBoardIndex() , articleReport.getArticleIndex());
        if (articleReportEntity != null) {
            boardListVo.setResult(BoardListResult.NOT_ALLOWED);
            return;
        }
        articleReport.setChecked(true);
        this.boardListMapper.insertArticleReport(articleReport);
        boardListVo.setResult(BoardListResult.SUCCESS);
        ArticleEntity articleEntity = this.boardListMapper.selectArticle(articleReport.getBoardIndex(), articleReport.getArticleIndex());
        articleEntity.setBlindStatus(articleEntity.getBlindStatus() + 1);
        this.boardListMapper.updateArticleForReport(articleEntity.getBlindStatus(), articleEntity.getBoardIndex(), articleEntity.getIndex());
    }
    @Transactional
    public void addCommentLike(BoardListVo boardListVo, CommentLikeEntity commentLike) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        CommentLikeEntity commentLikeEntity = this.boardListMapper.selectCommentLikeByUser(commentLike.getUserIndex(), commentLike.getArticleIndex() , commentLike.getCommentIndex());
        if (commentLikeEntity != null) {
            boardListVo.setResult(BoardListResult.NOT_ALLOWED);
            return;
        }
        commentLike.setChecked(true);
        this.boardListMapper.insertCommentLike(commentLike);
        boardListVo.setResult(BoardListResult.SUCCESS);
        CommentEntity commentEntity = this.boardListMapper.selectComment(commentLike.getArticleIndex(), commentLike.getCommentIndex());
        commentEntity.setLike(commentEntity.getLike() + 1);
        this.boardListMapper.updateCommentForLike(commentEntity.getLike(), commentEntity.getArticleIndex() , commentEntity.getIndex());
    }

    @Transactional
    public void addCommentReport(BoardListVo boardListVo, CommentReportEntity commentReport) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        CommentReportEntity commentReportEntity = this.boardListMapper.selectCommentReportByUser(commentReport.getUserIndex(), commentReport.getArticleIndex() , commentReport.getCommentIndex());
        if (commentReportEntity != null) {
            boardListVo.setResult(BoardListResult.NOT_ALLOWED);
            return;
        }
        commentReport.setChecked(true);
        this.boardListMapper.insertCommentReport(commentReport);
        boardListVo.setResult(BoardListResult.SUCCESS);
        CommentEntity commentEntity = this.boardListMapper.selectComment(commentReport.getArticleIndex(), commentReport.getCommentIndex());
        commentEntity.setBlindStatus(commentEntity.getBlindStatus() + 1);
        this.boardListMapper.updateCommentForReport(commentEntity.getBlindStatus(), commentEntity.getArticleIndex() , commentEntity.getIndex());
    }

    public void addJoinCommentLike(BoardListVo boardListVo, JoinCommentLikeEntity joinCommentLike) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        JoinCommentLikeEntity joinCommentLikeEntity = this.boardListMapper.selectJoinCommentLikeByUser(joinCommentLike.getUserIndex(), joinCommentLike.getCommentIndex());
        if (joinCommentLikeEntity != null) {
            boardListVo.setResult(BoardListResult.NOT_ALLOWED);
            return;
        }
        joinCommentLike.setChecked(true);
        this.boardListMapper.insertJoinCommentLike(joinCommentLike);
        boardListVo.setResult(BoardListResult.SUCCESS);
        JoinCommentEntity joinCommentEntity = this.boardListMapper.selectJoinComment(joinCommentLike.getCommentIndex());
//        System.out.println("joinCommentEntity 전 : " + joinCommentEntity.getLike());
        joinCommentEntity.setLike(joinCommentEntity.getLike() + 1);
//        System.out.println("joinCommentEntity 후 : " + joinCommentEntity.getLike());
        this.boardListMapper.updateJoinComment(joinCommentEntity.getLike(), joinCommentEntity.getIndex());
    }

    public void deleteComment(UserEntity user, JoinCommentDeleteVo joinCommentDeleteVo) {
        JoinCommentEntity joinCommentEntity = this.boardListMapper.selectJoinComment(joinCommentDeleteVo.getIndex());
        if (user.getIndex() != joinCommentEntity.getUserIndex()) {
            System.out.println("삭제 불가");
            joinCommentDeleteVo.setResult(JoinCommentDeleteResult.FAILURE);
            return;
        }
        joinCommentDeleteVo.setResult(JoinCommentDeleteResult.SUCCESS);
        this.boardListMapper.deleteJoinComment(joinCommentEntity);
    }

    public CategoryEntity[] getCategories() {
        return this.boardListMapper.selectCategories();
    }

    // 모든 게시판 게시글 불러오기
    public List<BoardListArticleDto> getArticlesForAll() {
        return this.boardListMapper.selectArticlesForAll();
    }

    // 모든 게시판 최신 게시글 6개 불러오기
    public List<BoardListArticleDto> getNewArticlesForAll() {
        return this.boardListMapper.selectNewArticlesForAll();
    }

    // 공지사항 게시판용
    public List<BoardListArticleDto> getArticlesForNo() {
        return this.boardListMapper.selectArticlesForNo();
    }

    // 공지사항 게시판 최신글 용
    public List<BoardListArticleDto> getNewArticlesForNo() {
        return this.boardListMapper.selectNewArticlesForNo();
    }

    // 이벤트 게시판 최신글 용
    public List<BoardListArticleDto> getNewArticlesForEv() {
        return this.boardListMapper.selectNewArticlesForEv();
    }

    // 인기글 게시판 최신글 용
    public List<BoardListArticleDto> getNewArticlesForFavorite() {
        return this.boardListMapper.selectNewArticlesForFavorite();
    }

    // 모든 게시글 리스트
    public void listBoardAll(BoardListVo boardListVo, PagingModel pagingModel) {
        List<BoardListArticleDto> boardListArticleDtoList = this.boardListMapper.selectArticlesForAll();
        if (boardListArticleDtoList == null || boardListArticleDtoList.size() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.setArticles(boardListArticleDtoList);
        List<BoardListArticleDto> articles = this.boardListMapper.selectArticlesForBoardListAll(
                pagingModel.rowCountPerPage,
                (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    // 게시판 별 게시글 리스트
    public void listBoard(BoardListVo boardListVo, PagingModel pagingModel) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardListArticleDto> articles = this.boardListMapper.selectArticlesForBoardList(
                boardEntity.getIndex(), pagingModel.rowCountPerPage, (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    public void readComment(BoardListVo boardListVo, PagingModel pagingModel) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        boardListVo.setResult(BoardListResult.SUCCESS);
        List<BoardReadCommentDto> boardReadCommentDtos = this.boardListMapper.selectJoinComments(
                boardEntity.getIndex(), pagingModel.rowCountPerPage, (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
        for (BoardReadCommentDto boardReadCommentDto : boardReadCommentDtos) {
            UserEntity tempUser = this.userMapper.selectUserByIndex(boardReadCommentDto.getUserIndex());
            boardReadCommentDto.setProfileId(tempUser.getProfileId());
            boardReadCommentDto.setLevel(tempUser.getLevel());
            boardReadCommentDto.setPoint(tempUser.getPoint());
        }
        boardListVo.setJoinComments(boardReadCommentDtos);
    }

    // 유저의 게시글 전체 불러오기
    public List<BoardListArticleDto> listBoardByUserIndex(int index, PagingModel pagingModel) {
        return this.boardListMapper.selectArticlesForUserIndex(
                index,
                pagingModel.rowCountPerPage,
                (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
    }

    // 모든 게시판 검색기준 별 게시글 불러오기. default에 전체도 포함.
    public void listBoardAllByCriteria(BoardListVo boardListVo, PagingModel pagingModel, String criteria, String keyword) {
        List<BoardListArticleDto> boardListArticleDtoList = this.boardListMapper.selectArticlesForAll();
        if (boardListArticleDtoList == null || boardListArticleDtoList.size() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.setArticles(boardListArticleDtoList);
        List<BoardListArticleDto> articles;
        switch (criteria) {
            case "title-content":
                articles = this.boardListMapper.searchByTitleContentAll(
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "title":
                articles = this.boardListMapper.searchByTitleAll(
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "content":
                articles = this.boardListMapper.searchByContentAll(
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "comment":
                articles = this.boardListMapper.searchByCommentAll(
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "nickname":
                articles = this.boardListMapper.searchByNicknameAll(
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            default:
                articles = this.boardListMapper.selectArticlesForBoardListAllByCriteria(
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
        }
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    // 검색기준 별 게시글 불러오기. default에 전체도 포함.
    public void listBoardByCriteria(BoardListVo boardListVo, PagingModel pagingModel, String criteria, String keyword) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardListArticleDto> articles;
        switch (criteria) {
            case "title-content":
                articles = this.boardListMapper.searchByTitleContent(
                        boardEntity.getIndex(),
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "title":
                articles = this.boardListMapper.searchByTitle(
                        boardEntity.getIndex(),
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "content":
                articles = this.boardListMapper.searchByContent(
                        boardEntity.getIndex(),
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "comment":
                articles = this.boardListMapper.searchByComment(
                        boardEntity.getIndex(),
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "nickname":
                articles = this.boardListMapper.searchByNickname(
                        boardEntity.getIndex(),
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            default:
                articles = this.boardListMapper.selectArticlesForBoardListByCriteria(
                        boardEntity.getIndex(),
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
        }
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    // 검색기준 별 댓글 불러오기. default에 전체도 포함.
    public void listBoardCommentByCriteria(BoardListVo boardListVo, PagingModel pagingModel, String criteria, String keyword) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardReadCommentDto> joinComments;
        switch (criteria) {
            case "content":
                joinComments = this.boardListMapper.searchCommentByContent(
                        boardEntity.getIndex(),
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "comment":
                joinComments = this.boardListMapper.searchCommentByComment(
                        boardEntity.getIndex(),
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "nickname":
                joinComments = this.boardListMapper.searchCommentByNickname(
                        boardEntity.getIndex(),
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            default:
                joinComments = this.boardListMapper.searchCommentByContent(
                        boardEntity.getIndex(),
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
        }
        boardListVo.setJoinComments(joinComments);
        for (BoardReadCommentDto joinComment : boardListVo.getJoinComments()) {
            System.out.println(joinComment.getUserNickname());
        }
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    // 모든 게시판 카테고리 별 게시글 불러오기. default에 전체도 포함.
    public void listBoardAllByCategory(BoardListVo boardListVo, PagingModel pagingModel, int category, String criteria, String keyword) {
        List<BoardListArticleDto> boardListArticleDtoList = this.boardListMapper.selectArticlesForAll();
        if (boardListArticleDtoList == null || boardListArticleDtoList.size() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.setArticles(boardListArticleDtoList);
        List<BoardListArticleDto> articles;
        switch (criteria) {
            case "title-content":
                articles = this.boardListMapper.searchByTitleContentAndCategoryAll(
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "title":
                articles = this.boardListMapper.searchByTitleAndCategoryAll(
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "content":
                articles = this.boardListMapper.searchByContentAndCategoryAll(
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "comment":
                articles = this.boardListMapper.searchByCommentAndCategoryAll(
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "nickname":
                articles = this.boardListMapper.searchByNicknameAndCategoryAll(
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            default:
                articles = this.boardListMapper.selectArticlesForBoardListAllByCategory(
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
        }
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    // 카테고리&&검색기준 별 게시글 불러오기. default에 전체도 포함.
    public void listBoardByCategory(BoardListVo boardListVo, PagingModel pagingModel, int category, String criteria, String keyword) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardListArticleDto> articles;
        switch (criteria) {
            case "title-content":
                articles = this.boardListMapper.searchByTitleContentAndCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "title":
                articles = this.boardListMapper.searchByTitleAndCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "content":
                articles = this.boardListMapper.searchByContentAndCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "comment":
                articles = this.boardListMapper.searchByCommentAndCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "nickname":
                articles = this.boardListMapper.searchByNicknameAndCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            default:
                articles = this.boardListMapper.selectArticlesForBoardListByCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
        }
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    // 정렬&&검색기준 별 게시글 불러오기. default에 전체도 포함.
    public void listBoardByAlignment(BoardListVo boardListVo, PagingModel pagingModel, int alignment, String criteria, String keyword) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardListArticleDto> articles;
        switch (criteria) {
            case "title-content":
                articles = this.boardListMapper.searchByTitleContentAndAlignment(
                        boardEntity.getIndex(),
                        keyword,
                        alignment,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "title":
                articles = this.boardListMapper.searchByTitleAndAlignment(
                        boardEntity.getIndex(),
                        keyword,
                        alignment,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "content":
                articles = this.boardListMapper.searchByContentAndAlignment(
                        boardEntity.getIndex(),
                        keyword,
                        alignment,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "comment":
                articles = this.boardListMapper.searchByCommentAndAlignment(
                        boardEntity.getIndex(),
                        keyword,
                        alignment,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            case "nickname":
                articles = this.boardListMapper.searchByNicknameAndAlignment(
                        boardEntity.getIndex(),
                        keyword,
                        alignment,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
                break;
            default:
                articles = this.boardListMapper.searchByTitleContentAndAlignment(
                        boardEntity.getIndex(),
                        keyword,
                        alignment,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
        }
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    // 모든 게시판 게시글 불러오기
    public int boardTotalCount() {
        return this.boardListMapper.selectArticleCountTotal();
    }

    // 유저 별 작성한 게시글 갯수 불러오기
    public int boardTotalCountByUserIndex(int index) {
        return this.boardListMapper.selectArticleCountTotalByUserIndex(index);
    }

    // 게시판 별 총 게시글 갯수 불러오기
    public int boardTotalCountByBoardIndex(BoardListVo boardListVo) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        return this.boardListMapper.selectArticleCountTotalByBoardIndex(boardEntity.getIndex());
    }

    // 게시판의 댓글 총 갯수 불러오기
    public int commentTotalCountByBoardIndex(BoardListVo boardListVo) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        return this.boardListMapper.selectCommentCountTotalByBoardIndex(boardEntity.getIndex());
    }

    // 게시판 별 게시글 불러오기
    public BoardEntity boardByUrlName(BoardListVo boardListVo) {
        return this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
    }


    // 모든 게시판 검색기준 별 게시글 갯수 불러오기. default에 전체도 포함.
    public int boardTotalCountAllByCriteria(String criteria, String keyword) {
        switch (criteria) {
            case "title-content":
                return this.boardListMapper.getCountByTitleContentAll(
                        keyword);
            case "title":
                return this.boardListMapper.getCountByTitleAll(
                        keyword);
            case "content":
                return this.boardListMapper.getCountByContentAll(
                        keyword);
            case "comment":
                return this.boardListMapper.getCountByCommentAll(
                        keyword);
            case "nickname":
                return this.boardListMapper.getCountByNicknameAll(
                        keyword);
            default:
                return this.boardListMapper.selectArticleCountTotalByCriteria(
                        keyword);
        }
    }

    // 모든 게시판 카테고리&&검색기준 별 게시글 갯수 불러오기. default에 전체도 포함.
    public int boardTotalCountAllByCategory(int category, String criteria, String keyword) {
        switch (criteria) {
            case "title-content":
                return this.boardListMapper.getCountByTitleContentAndCategoryAll(
                        category,
                        keyword);
            case "title":
                return this.boardListMapper.getCountByTitleAndCategoryAll(
                        category,
                        keyword);
            case "content":
                return this.boardListMapper.getCountByContentAndCategoryAll(
                        category,
                        keyword);
            case "comment":
                return this.boardListMapper.getCountByCommentAndCategoryAll(
                        category,
                        keyword);
            case "nickname":
                return this.boardListMapper.getCountByNicknameAndCategoryAll(
                        category,
                        keyword);
            default:
                return this.boardListMapper.selectArticleCountTotalByCategory(
                        category,
                        keyword);
        }
    }

    // 검색기준 별 게시글 갯수 불러오기. default에 전체도 포함.
    public int boardTotalCountByCriteria(BoardListVo boardListVo, String criteria, String keyword) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        switch (criteria) {
            case "title-content":
                return this.boardListMapper.getCountByTitleContent(
                        boardEntity.getIndex(),
                        keyword);
            case "title":
                return this.boardListMapper.getCountByTitle(
                        boardEntity.getIndex(),
                        keyword);
            case "content":
                return this.boardListMapper.getCountByContent(
                        boardEntity.getIndex(),
                        keyword);
            case "comment":
                return this.boardListMapper.getCountByComment(
                        boardEntity.getIndex(),
                        keyword);
            case "nickname":
                return this.boardListMapper.getCountByNickname(
                        boardEntity.getIndex(),
                        keyword);
            default:
                return this.boardListMapper.selectArticleCountTotalByBoardIndexAndCriteria(
                        boardEntity.getIndex(),
                        keyword);
        }
    }

    // 검색기준 별 댓글 갯수 불러오기. default에 전체도 포함.
    public int CommentTotalCountByCriteria(BoardListVo boardListVo, String criteria, String keyword) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        switch (criteria) {
            case "content":
                return this.boardListMapper.getCommentCountByContent(
                        boardEntity.getIndex(),
                        keyword);
            case "comment":
                return this.boardListMapper.getCommentCountByComment(
                        boardEntity.getIndex(),
                        keyword);
            case "nickname":
                return this.boardListMapper.getCommentCountByNickname(
                        boardEntity.getIndex(),
                        keyword);
            default:
                return this.boardListMapper.getCommentCountByContent(
                        boardEntity.getIndex(),
                        keyword);
        }
    }

    // 카테고리&&검색기준 별 게시글 갯수 불러오기. default에 전체도 포함.
    public int boardTotalCountByCategory(BoardListVo boardListVo, int category, String criteria, String keyword) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(boardListVo.getUrlName());
        switch (criteria) {
            case "title-content":
                return this.boardListMapper.getCountByTitleContentAndCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword);
            case "title":
                return this.boardListMapper.getCountByTitleAndCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword);
            case "content":
                return this.boardListMapper.getCountByContentAndCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword);
            case "comment":
                return this.boardListMapper.getCountByCommentAndCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword);
            case "nickname":
                return this.boardListMapper.getCountByNicknameAndCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword);
            default:
                return this.boardListMapper.selectArticleCountTotalByBoardIndexAndCategory(
                        boardEntity.getIndex(),
                        category,
                        keyword);
        }
    }
}
