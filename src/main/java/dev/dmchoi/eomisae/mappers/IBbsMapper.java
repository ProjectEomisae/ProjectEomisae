package dev.dmchoi.eomisae.mappers;

import dev.dmchoi.eomisae.dtos.bbs.BoardListArticleDto;
import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;
import dev.dmchoi.eomisae.entities.bbs.BoardEntity;
import dev.dmchoi.eomisae.entities.bbs.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IBbsMapper {

    int selectArticleCountTotalByUserIndex(
            @Param(value = "userIndex") int userIndex);
    int selectArticleCountTotalByBoardIndex(
            @Param(value = "boardIndex") int boardIndex); // 해당 게시판의 전체 게시글 개수
    int selectArticleCountTotalByBoardIndexAndCategory(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex);
    CategoryEntity[] selectCategories();

    int getCountByTitleContent(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "keyword") String keyword);

    int getCountByTitle(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "keyword") String keyword);

    int getCountByContent(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "keyword") String keyword);

    int getCountByComment(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "keyword") String keyword);

    int getCountByNickname(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByTitleContent(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByTitle(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByContent(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByComment(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByNickname(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> selectArticlesForAll();
    List<BoardListArticleDto> selectArticlesForNo();

    List<BoardListArticleDto> selectArticlesForUserIndex(
            @Param(value = "index") int index,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset);

    List<BoardListArticleDto> selectArticlesForBoardList(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset);

    List<BoardListArticleDto> selectArticlesForBoardListByCategory(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset);
    BoardEntity selectBoardByUrlName( // urlName 에 해당하는 게시판
                                      @Param(value = "urlName") String urlName);

}
