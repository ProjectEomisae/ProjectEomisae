package dev.dmchoi.eomisae.services;

import dev.dmchoi.eomisae.dtos.bbs.BoardListArticleDto;
import dev.dmchoi.eomisae.entities.bbs.BoardEntity;
import dev.dmchoi.eomisae.entities.bbs.CategoryEntity;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.bbs.BoardListResult;
import dev.dmchoi.eomisae.mappers.IBbsMapper;
import dev.dmchoi.eomisae.mappers.IUserMapper;
import dev.dmchoi.eomisae.models.PagingModel;
import dev.dmchoi.eomisae.vos.bbs.BoardListVo;
import dev.dmchoi.eomisae.vos.bbs.BoardListVoForNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "dev.dmchoi.eomisae.services.BbsService")
public class BbsService {
    private final IBbsMapper bbsMapper;
    private final IUserMapper userMapper;

    @Autowired
    public BbsService(IBbsMapper bbsMapper, IUserMapper userMapper) {
        this.bbsMapper = bbsMapper;
        this.userMapper = userMapper;
    }

    public CategoryEntity[] getCategories() {
        return this.bbsMapper.selectCategories();
    }

    public List<BoardListArticleDto> getArticlesForAll() {
        return this.bbsMapper.selectArticlesForAll();
    }

    public List<BoardListArticleDto> getArticlesForNo() {
        return this.bbsMapper.selectArticlesForNo();
    }
    public void listBoard(BoardListVo boardListVo, PagingModel pagingModel) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardListArticleDto> articles = this.bbsMapper.selectArticlesForBoardList(
                boardEntity.getIndex(), pagingModel.rowCountPerPage, (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    public List<BoardListArticleDto> listBoardByUserIndex(int index , PagingModel pagingModel) {
        return this.bbsMapper.selectArticlesForUserIndex(
                index,
                pagingModel.rowCountPerPage,
                (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
    }

    public void listBoardByCategory(BoardListVo boardListVo, PagingModel pagingModel, int category) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardListArticleDto> articles = this.bbsMapper.selectArticlesForBoardListByCategory(
                boardEntity.getIndex(),
                category,
                pagingModel.rowCountPerPage,
                (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    public int boardTotalCountByUserIndex(int index) {
        return this.bbsMapper.selectArticleCountTotalByUserIndex(index);
    }

    public int boardTotalCount(BoardListVo boardListVo) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        return this.bbsMapper.selectArticleCountTotalByBoardIndex(boardEntity.getIndex());
    }

    public int boardTotalCountByCategory(BoardListVo boardListVo, int category) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        return this.bbsMapper.selectArticleCountTotalByBoardIndexAndCategory(boardEntity.getIndex(), category);
    }

    public int getCountByTitleContent(BoardListVo boardListVo, String keyword) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        return this.bbsMapper.getCountByTitleContent(boardEntity.getIndex(), keyword);
    }

    public void searchByTitleContent(BoardListVo boardListVo, String keyword, PagingModel pagingModel) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardListArticleDto> articles = this.bbsMapper.searchByTitleContent(
                boardEntity.getIndex(),
                pagingModel.rowCountPerPage,
                (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                keyword);
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    public int getCountByTitle(BoardListVo boardListVo, String keyword) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        return this.bbsMapper.getCountByTitle(boardEntity.getIndex(), keyword);
    }

    public void searchByTitle(BoardListVo boardListVo, String keyword, PagingModel pagingModel) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardListArticleDto> articles = this.bbsMapper.searchByTitle(
                boardEntity.getIndex(),
                pagingModel.rowCountPerPage,
                (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                keyword);
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    public int getCountByContent(BoardListVo boardListVo, String keyword) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        return this.bbsMapper.getCountByContent(boardEntity.getIndex(), keyword);
    }

    public void searchByContent(BoardListVo boardListVo, String keyword, PagingModel pagingModel) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardListArticleDto> articles = this.bbsMapper.searchByContent(
                boardEntity.getIndex(),
                pagingModel.rowCountPerPage,
                (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                keyword);
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    public int getCountByComment(BoardListVo boardListVo, String keyword) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        return this.bbsMapper.getCountByComment(boardEntity.getIndex(), keyword);
    }

    public void searchByComment(BoardListVo boardListVo, String keyword, PagingModel pagingModel) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardListArticleDto> articles = this.bbsMapper.searchByComment(
                boardEntity.getIndex(),
                pagingModel.rowCountPerPage,
                (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                keyword);
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

    public int getCountByNickname(BoardListVo boardListVo, String keyword) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        return this.bbsMapper.getCountByNickname(boardEntity.getIndex(), keyword);
    }

    public void searchByNickname(BoardListVo boardListVo, String keyword, PagingModel pagingModel) {
        BoardEntity boardEntity = this.bbsMapper.selectBoardByUrlName(boardListVo.getUrlName());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            boardListVo.setResult(BoardListResult.NOT_FOUND);
            return;
        }
        boardListVo.copyValuesOf(boardEntity);
        List<BoardListArticleDto> articles = this.bbsMapper.searchByNickname(
                boardEntity.getIndex(),
                pagingModel.rowCountPerPage,
                (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage,
                keyword);
        boardListVo.setArticles(articles);
        boardListVo.setResult(BoardListResult.SUCCESS);
    }

}
