package dev.dmchoi.eomisae.services.bbs;

import dev.dmchoi.eomisae.dtos.bbs.BoardListArticleDto;
import dev.dmchoi.eomisae.entities.bbs.BoardEntity;
import dev.dmchoi.eomisae.entities.bbs.CategoryEntity;
import dev.dmchoi.eomisae.enums.bbs.BoardListResult;
import dev.dmchoi.eomisae.mappers.bbs.IBoardListMapper;
import dev.dmchoi.eomisae.mappers.IUserMapper;
import dev.dmchoi.eomisae.models.PagingModel;
import dev.dmchoi.eomisae.vos.bbs.BoardListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CategoryEntity[] getCategories() {
        return this.boardListMapper.selectCategories();
    }

    // 모든 게시판 게시글 불러오기
    public List<BoardListArticleDto> getArticlesForAll() {
        return this.boardListMapper.selectArticlesForAll();
    }

    // 공지사항 게시판용
    public List<BoardListArticleDto> getArticlesForNo() {
        return this.boardListMapper.selectArticlesForNo();
    }

    // 게시판 별 게시글 리스트
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
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "title":
                articles = this.boardListMapper.searchByTitleAll(
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "content":
                articles = this.boardListMapper.searchByContentAll(
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "comment":
                articles = this.boardListMapper.searchByCommentAll(
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "nickname":
                articles = this.boardListMapper.searchByNicknameAll(
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            default:
                articles = this.boardListMapper.selectArticlesForBoardListAllByCriteria(
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
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
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "title":
                articles = this.boardListMapper.searchByTitle(
                        boardEntity.getIndex(),
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "content":
                articles = this.boardListMapper.searchByContent(
                        boardEntity.getIndex(),
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "comment":
                articles = this.boardListMapper.searchByComment(
                        boardEntity.getIndex(),
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "nickname":
                articles = this.boardListMapper.searchByNickname(
                        boardEntity.getIndex(),
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            default:
                articles = this.boardListMapper.selectArticlesForBoardListByCriteria(
                        boardEntity.getIndex(),
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
        }
        boardListVo.setArticles(articles);
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
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "title":
                articles = this.boardListMapper.searchByTitleAndCategoryAll(
                        category,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "content":
                articles = this.boardListMapper.searchByContentAndCategoryAll(
                        category,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "comment":
                articles = this.boardListMapper.searchByCommentAndCategoryAll(
                        category,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "nickname":
                articles = this.boardListMapper.searchByNicknameAndCategoryAll(
                        category,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            default:
                articles = this.boardListMapper.selectArticlesForBoardListAllByCategory(
                        category,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
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
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "title":
                articles = this.boardListMapper.searchByTitleAndCategory(
                        boardEntity.getIndex(),
                        category,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "content":
                articles = this.boardListMapper.searchByContentAndCategory(
                        boardEntity.getIndex(),
                        category,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "comment":
                articles = this.boardListMapper.searchByCommentAndCategory(
                        boardEntity.getIndex(),
                        category,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            case "nickname":
                articles = this.boardListMapper.searchByNicknameAndCategory(
                        boardEntity.getIndex(),
                        category,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
            default:
                articles = this.boardListMapper.selectArticlesForBoardListByCategory(
                        boardEntity.getIndex(),
                        category,
                        pagingModel.rowCountPerPage,
                        (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                        keyword);
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
