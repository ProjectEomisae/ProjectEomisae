package dev.dmchoi.eomisae.mappers.bbs;

import dev.dmchoi.eomisae.dtos.bbs.BoardListArticleDto;
import dev.dmchoi.eomisae.entities.bbs.BoardEntity;
import dev.dmchoi.eomisae.entities.bbs.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IBoardListMapper {
    int selectArticleCountTotal();     // 전체 게시판 게시글 갯수 불러오기
    int selectArticleCountTotalByUserIndex(     // 유저 별 작성한 게시글 갯수 불러오기
            @Param(value = "userIndex") int userIndex);
    int selectArticleCountTotalByBoardIndex(     // 게시판 별 총 게시글 갯수 불러오기
            @Param(value = "boardIndex") int boardIndex);
    int selectArticleCountTotalByCriteria(    //  모든 게시판 총 게시글 갯수 불러오기
           @Param(value = "keyword") String keyword);
    int selectArticleCountTotalByBoardIndexAndCriteria(    // 게시판 별 총 게시글 갯수 불러오기
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "keyword") String keyword);
    int selectArticleCountTotalByCategory(  // 모든 게시판 카테고리&&검색기준 별 총 게시글 갯수 불러오기 / default 용
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);
    int selectArticleCountTotalByBoardIndexAndCategory(  // 카테고리&&검색기준 별 총 게시글 갯수 불러오기 / default 용
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);
    CategoryEntity[] selectCategories(); // 카테고리 불러오기

    int getCountByTitleContentAll(     // 모든 게시판의 검색기준 제목+내용 별 게시글 개수
            @Param(value = "keyword") String keyword);

    int getCountByTitleAll(     // 모든 게시판의 검색기준 제목 별 게시글 개수
           @Param(value = "keyword") String keyword);

    int getCountByContentAll(     // 모든 게시판의 검색기준 내용 별 게시글 개수
           @Param(value = "keyword") String keyword);

    int getCountByCommentAll(     // 모든 게시판의 검색기준 댓글 별 게시글 개수
           @Param(value = "keyword") String keyword);

    int getCountByNicknameAll(     // 모든 게시판의 검색기준 닉네임 별 게시글 개수
            @Param(value = "keyword") String keyword);

    int getCountByTitleContent(     // 해당 게시판의 검색기준 제목+내용 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "keyword") String keyword);

    int getCountByTitle(     // 해당 게시판의 검색기준 제목 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "keyword") String keyword);

    int getCountByContent(     // 해당 게시판의 검색기준 내용 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "keyword") String keyword);

    int getCountByComment(     // 해당 게시판의 검색기준 댓글 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "keyword") String keyword);

    int getCountByNickname(     // 해당 게시판의 검색기준 닉네임 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "keyword") String keyword);


    int getCountByTitleContentAndCategoryAll(  // 모든 게시판의 검색기준 제목+내용 별 && 카테고리 별 게시글 개수
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);

    int getCountByTitleAndCategoryAll(  // 모든 게시판의 검색기준 제목 별 && 카테고리 별 게시글 개수
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);

    int getCountByContentAndCategoryAll(  // 모든 게시판의 검색기준 내용 별 && 카테고리 별 게시글 개수
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);

    int getCountByCommentAndCategoryAll(  // 모든 게시판의 검색기준 댓글 별 && 카테고리 별 게시글 개수
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);

    int getCountByNicknameAndCategoryAll(  // 모든 게시판의 검색기준 닉네임 별 && 카테고리 별 게시글 개수
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);

    int getCountByTitleContentAndCategory(  // 해당 게시판의 검색기준 제목+내용 별 && 카테고리 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);

    int getCountByTitleAndCategory(  // 해당 게시판의 검색기준 제목 별 && 카테고리 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);

    int getCountByContentAndCategory(  // 해당 게시판의 검색기준 내용 별 && 카테고리 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);

    int getCountByCommentAndCategory(  // 해당 게시판의 검색기준 댓글 별 && 카테고리 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);

    int getCountByNicknameAndCategory(  // 해당 게시판의 검색기준 닉네임 별 && 카테고리 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByTitleContentAll( // 해당 게시판의 검색기준 제목+내용 별 게시글 불러오기
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByTitleAll( // 해당 게시판의 검색기준 제목 별 게시글 불러오기
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByContentAll( // 해당 게시판의 검색기준 내용 별 게시글 불러오기
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByCommentAll( // 해당 게시판의 검색기준 댓글 별 게시글 불러오기
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByNicknameAll( // 해당 게시판의 검색기준 닉네임 별 게시글 불러오기
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByTitleContent( // 해당 게시판의 검색기준 제목+내용 별 게시글 불러오기
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByTitle( // 해당 게시판의 검색기준 제목 별 게시글 불러오기
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByContent( // 해당 게시판의 검색기준 내용 별 게시글 불러오기
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByComment( // 해당 게시판의 검색기준 댓글 별 게시글 불러오기
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByNickname( // 해당 게시판의 검색기준 닉네임 별 게시글 불러오기
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByTitleContentAndCategoryAll(  // 해당 게시판의 검색기준 제목+내용 별 && 카테고리 별 게시글 개수
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByTitleAndCategoryAll( // 해당 게시판의 검색기준 제목 별 && 카테고리 별 게시글 개수
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByContentAndCategoryAll( // 해당 게시판의 검색기준 내용 별 && 카테고리 별 게시글 개수
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByCommentAndCategoryAll( // 해당 게시판의 검색기준 댓글 별 && 카테고리 별 게시글 개수
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByNicknameAndCategoryAll( // 해당 게시판의 검색기준 닉네임 별 && 카테고리 별 게시글 개수
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByTitleContentAndCategory(  // 해당 게시판의 검색기준 제목+내용 별 && 카테고리 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByTitleAndCategory( // 해당 게시판의 검색기준 제목 별 && 카테고리 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByContentAndCategory( // 해당 게시판의 검색기준 내용 별 && 카테고리 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByCommentAndCategory( // 해당 게시판의 검색기준 댓글 별 && 카테고리 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> searchByNicknameAndCategory( // 해당 게시판의 검색기준 닉네임 별 && 카테고리 별 게시글 개수
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> selectArticlesForAll();// 모든 게시판의 모든 게시글 불러오기
    List<BoardListArticleDto> selectArticlesForNo(); // 공지사항 게시판 용 게시글 불러오기

    List<BoardListArticleDto> selectArticlesForUserIndex( // 유저 별 작성한 전체 게시글 불러오기
            @Param(value = "index") int index,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset);

    List<BoardListArticleDto> selectArticlesForBoardList( // 게시판 별 게시글 불러오기
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset);

    List<BoardListArticleDto> selectArticlesForBoardListAll( // 게시판&&카테고리 별 게시글 불러오기
            @Param(value = "count") int count,
            @Param(value = "offset") int offset);
    List<BoardListArticleDto> selectArticlesForBoardListAllByCriteria( // 게시판&&카테고리 별 게시글 불러오기
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);
    List<BoardListArticleDto> selectArticlesForBoardListByCriteria( // 게시판&&카테고리 별 게시글 불러오기
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);

    List<BoardListArticleDto> selectArticlesForBoardListAllByCategory( // 게시판&&카테고리 별 게시글 불러오기
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);
    List<BoardListArticleDto> selectArticlesForBoardListByCategory( // 게시판&&카테고리 별 게시글 불러오기
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "categoryIndex") int categoryIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset,
            @Param(value = "keyword") String keyword);
    BoardEntity selectBoardByUrlName( // urlName 에 해당하는 게시판 불러오기
                                      @Param(value = "urlName") String urlName);

}
