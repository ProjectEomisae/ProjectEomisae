package dev.dmchoi.eomisae.mappers.bbs;

import dev.dmchoi.eomisae.dtos.bbs.BoardListArticleDto;
import dev.dmchoi.eomisae.dtos.bbs.BoardReadCommentDto;
import dev.dmchoi.eomisae.entities.bbs.*;
import dev.dmchoi.eomisae.vos.bbs.ArticleReadVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IBoardListMapper {
    ArticleReadVo selectPageByBoardIndex(
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "aid") int aid);

    int selectArticleCountTotal();     // 전체 게시판 게시글 갯수 불러오기

    int selectArticleCountTotalByUserIndex(     // 유저 별 작성한 게시글 갯수 불러오기
                                                @Param(value = "userIndex") int userIndex);

    int selectArticleCountTotalByBoardIndex(     // 게시판 별 총 게시글 갯수 불러오기
                                                 @Param(value = "boardIndex") int boardIndex);

    int selectCommentCountTotalByBoardIndex(     // 게시판 댓글 갯수 불러오기
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
    GenderEntity[] selectGenders();
    ProductStatusEntity[] selectProductStatuses();
    TradeMethodEntity[] selectTradeMethods();
    CurrencyEntity[] selectCurrencies();

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

    int getCommentCountByContent(     // 해당 게시판의 검색기준 내용 별 댓글 개수
                                      @Param(value = "boardIndex") int boardIndex,
                                      @Param(value = "keyword") String keyword);

    int getCommentCountByComment(     // 해당 게시판의 검색기준 댓글 별 댓글 개수
                                      @Param(value = "boardIndex") int boardIndex,
                                      @Param(value = "keyword") String keyword);

    int getCommentCountByNickname(     // 해당 게시판의 검색기준 닉네임 별 댓글 개수
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
                                                       @Param(value = "keyword") String keyword,
                                                       @Param(value = "count") int count,
                                                       @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByTitleAll( // 해당 게시판의 검색기준 제목 별 게시글 불러오기
                                                @Param(value = "keyword") String keyword,
                                                @Param(value = "count") int count,
                                                @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByContentAll( // 해당 게시판의 검색기준 내용 별 게시글 불러오기
                                                  @Param(value = "keyword") String keyword,
                                                  @Param(value = "count") int count,
                                                  @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByCommentAll( // 해당 게시판의 검색기준 댓글 별 게시글 불러오기
                                                  @Param(value = "keyword") String keyword,
                                                  @Param(value = "count") int count,
                                                  @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByNicknameAll( // 해당 게시판의 검색기준 닉네임 별 게시글 불러오기
                                                   @Param(value = "keyword") String keyword,
                                                   @Param(value = "count") int count,
                                                   @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByTitleContent( // 해당 게시판의 검색기준 제목+내용 별 게시글 불러오기
                                                    @Param(value = "boardIndex") int boardIndex,
                                                    @Param(value = "keyword") String keyword,
                                                    @Param(value = "count") int count,
                                                    @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByTitle( // 해당 게시판의 검색기준 제목 별 게시글 불러오기
                                             @Param(value = "boardIndex") int boardIndex,
                                             @Param(value = "keyword") String keyword,
                                             @Param(value = "count") int count,
                                             @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByContent( // 해당 게시판의 검색기준 내용 별 게시글 불러오기
                                               @Param(value = "boardIndex") int boardIndex,
                                               @Param(value = "keyword") String keyword,
                                               @Param(value = "count") int count,
                                               @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByComment( // 해당 게시판의 검색기준 댓글 별 게시글 불러오기
                                               @Param(value = "boardIndex") int boardIndex,
                                               @Param(value = "keyword") String keyword,
                                               @Param(value = "count") int count,
                                               @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByNickname( // 해당 게시판의 검색기준 닉네임 별 게시글 불러오기
                                                @Param(value = "boardIndex") int boardIndex,
                                                @Param(value = "keyword") String keyword,
                                                @Param(value = "count") int count,
                                                @Param(value = "offset") int offset);

    List<BoardReadCommentDto> searchCommentByContent( // 해당 게시판의 검색기준 내용 별 댓글 불러오기
                                                      @Param(value = "boardIndex") int boardIndex,
                                                      @Param(value = "keyword") String keyword,
                                                      @Param(value = "count") int count,
                                                      @Param(value = "offset") int offset);

    List<BoardReadCommentDto> searchCommentByComment( // 해당 게시판의 검색기준 댓글 별 댓글 불러오기
                                                      @Param(value = "boardIndex") int boardIndex,
                                                      @Param(value = "keyword") String keyword,
                                                      @Param(value = "count") int count,
                                                      @Param(value = "offset") int offset);

    List<BoardReadCommentDto> searchCommentByNickname( // 해당 게시판의 검색기준 닉네임 별 댓글 불러오기
                                                       @Param(value = "boardIndex") int boardIndex,
                                                       @Param(value = "keyword") String keyword,
                                                       @Param(value = "count") int count,
                                                       @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByTitleContentAndCategoryAll(  // 해당 게시판의 검색기준 제목+내용 별 && 카테고리 별 게시글 개수
                                                                   @Param(value = "categoryIndex") int categoryIndex,
                                                                   @Param(value = "keyword") String keyword,
                                                                   @Param(value = "count") int count,
                                                                   @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByTitleAndCategoryAll( // 해당 게시판의 검색기준 제목 별 && 카테고리 별 게시글 개수
                                                           @Param(value = "categoryIndex") int categoryIndex,
                                                           @Param(value = "keyword") String keyword,
                                                           @Param(value = "count") int count,
                                                           @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByContentAndCategoryAll( // 해당 게시판의 검색기준 내용 별 && 카테고리 별 게시글 개수
                                                             @Param(value = "categoryIndex") int categoryIndex,
                                                             @Param(value = "keyword") String keyword,
                                                             @Param(value = "count") int count,
                                                             @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByCommentAndCategoryAll( // 해당 게시판의 검색기준 댓글 별 && 카테고리 별 게시글 개수
                                                             @Param(value = "categoryIndex") int categoryIndex,
                                                             @Param(value = "keyword") String keyword,
                                                             @Param(value = "count") int count,
                                                             @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByNicknameAndCategoryAll( // 해당 게시판의 검색기준 닉네임 별 && 카테고리 별 게시글 개수
                                                              @Param(value = "categoryIndex") int categoryIndex,
                                                              @Param(value = "keyword") String keyword,
                                                              @Param(value = "count") int count,
                                                              @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByTitleContentAndCategory(  // 해당 게시판의 검색기준 제목+내용 별 && 카테고리 별 게시글 개수
                                                                @Param(value = "boardIndex") int boardIndex,
                                                                @Param(value = "categoryIndex") int categoryIndex,
                                                                @Param(value = "keyword") String keyword,
                                                                @Param(value = "count") int count,
                                                                @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByTitleAndCategory( // 해당 게시판의 검색기준 제목 별 && 카테고리 별 게시글 개수
                                                        @Param(value = "boardIndex") int boardIndex,
                                                        @Param(value = "categoryIndex") int categoryIndex,
                                                        @Param(value = "keyword") String keyword,
                                                        @Param(value = "count") int count,
                                                        @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByContentAndCategory( // 해당 게시판의 검색기준 내용 별 && 카테고리 별 게시글 개수
                                                          @Param(value = "boardIndex") int boardIndex,
                                                          @Param(value = "categoryIndex") int categoryIndex,
                                                          @Param(value = "keyword") String keyword,
                                                          @Param(value = "count") int count,
                                                          @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByCommentAndCategory( // 해당 게시판의 검색기준 댓글 별 && 카테고리 별 게시글 개수
                                                          @Param(value = "boardIndex") int boardIndex,
                                                          @Param(value = "categoryIndex") int categoryIndex,
                                                          @Param(value = "keyword") String keyword,
                                                          @Param(value = "count") int count,
                                                          @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByNicknameAndCategory( // 해당 게시판의 검색기준 닉네임 별 && 카테고리 별 게시글 개수
                                                           @Param(value = "boardIndex") int boardIndex,
                                                           @Param(value = "categoryIndex") int categoryIndex,
                                                           @Param(value = "keyword") String keyword,
                                                           @Param(value = "count") int count,
                                                           @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByTitleContentAndAlignment(  // 해당 게시판의 검색기준 제목+내용 별 && 카테고리 별 게시글 개수
                                                                 @Param(value = "boardIndex") int boardIndex,
                                                                 @Param(value = "keyword") String keyword,
                                                                 @Param(value = "alignment") int alignment,
                                                                 @Param(value = "count") int count,
                                                                 @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByTitleAndAlignment( // 해당 게시판의 검색기준 제목 별 && 카테고리 별 게시글 개수
                                                         @Param(value = "boardIndex") int boardIndex,
                                                         @Param(value = "keyword") String keyword,
                                                         @Param(value = "alignment") int alignment,
                                                         @Param(value = "count") int count,
                                                         @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByContentAndAlignment( // 해당 게시판의 검색기준 내용 별 && 카테고리 별 게시글 개수
                                                           @Param(value = "boardIndex") int boardIndex,
                                                           @Param(value = "keyword") String keyword,
                                                           @Param(value = "alignment") int alignment,
                                                           @Param(value = "count") int count,
                                                           @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByCommentAndAlignment( // 해당 게시판의 검색기준 댓글 별 && 카테고리 별 게시글 개수
                                                           @Param(value = "boardIndex") int boardIndex,
                                                           @Param(value = "keyword") String keyword,
                                                           @Param(value = "alignment") int alignment,
                                                           @Param(value = "count") int count,
                                                           @Param(value = "offset") int offset);

    List<BoardListArticleDto> searchByNicknameAndAlignment( // 해당 게시판의 검색기준 닉네임 별 && 카테고리 별 게시글 개수
                                                            @Param(value = "boardIndex") int boardIndex,
                                                            @Param(value = "keyword") String keyword,
                                                            @Param(value = "alignment") int alignment,
                                                            @Param(value = "count") int count,
                                                            @Param(value = "offset") int offset);

    List<BoardListArticleDto> selectArticlesForAll();// 모든 게시판의 모든 게시글 불러오기

    List<BoardListArticleDto> selectNewArticlesForAll();// 모든 게시판의 최신 게시글 6개 불러오기
    List<BoardListArticleDto> selectArticlesForSale();// 쇼핑 정보 게시판의 최신 게시글 6개 불러오기
    List<BoardListArticleDto> selectArticlesForRealImage();// 실사 후기 게시판의 최신 게시글 6개 불러오기
    List<BoardListArticleDto> selectArticlesForDailyImage();// 데일리룩 게시판의 최신 게시글 6개 불러오기
    List<BoardListArticleDto> selectArticlesForNo(); // 공지사항 게시판 용 게시글 불러오기

    List<BoardListArticleDto> selectNewArticlesForNo(); // 공지사항 게시판 용 최신 게시글 불러오기

    List<BoardListArticleDto> selectNewArticlesForEv(); // 이벤트 게시판 용 최신 게시글 불러오기

    List<BoardListArticleDto> selectNewArticlesForFavorite(); // 인기글 게시판 용 최신 게시글 불러오기

    List<BoardListArticleDto> selectArticlesForUserIndex( // 유저 별 작성한 전체 게시글 불러오기
                                                          @Param(value = "index") int index,
                                                          @Param(value = "count") int count,
                                                          @Param(value = "offset") int offset);

    List<BoardListArticleDto> selectArticlesForBoardList( // 게시판 별 게시글 불러오기
                                                          @Param(value = "boardIndex") int boardIndex,
                                                          @Param(value = "count") int count,
                                                          @Param(value = "offset") int offset);

    List<BoardReadCommentDto> selectJoinComments( // 게시판 별 댓글 불러오기
                                                  @Param(value = "boardIndex") int boardIndex,
                                                  @Param(value = "count") int count,
                                                  @Param(value = "offset") int offset);

    List<BoardListArticleDto> selectArticlesForBoardListAll( // 게시판&&카테고리 별 게시글 불러오기
                                                             @Param(value = "count") int count,
                                                             @Param(value = "offset") int offset);

    List<BoardListArticleDto> selectArticlesForBoardListAllByCriteria( // 게시판&&카테고리 별 게시글 불러오기
                                                                       @Param(value = "keyword") String keyword,
                                                                       @Param(value = "count") int count,
                                                                       @Param(value = "offset") int offset);

    List<BoardListArticleDto> selectArticlesForBoardListByCriteria( // 게시판&&카테고리 별 게시글 불러오기
                                                                    @Param(value = "boardIndex") int boardIndex,
                                                                    @Param(value = "keyword") String keyword,
                                                                    @Param(value = "count") int count,
                                                                    @Param(value = "offset") int offset);

    List<BoardListArticleDto> selectArticlesForBoardListAllByCategory( // 게시판&&카테고리 별 게시글 불러오기
                                                                       @Param(value = "categoryIndex") int categoryIndex,
                                                                       @Param(value = "keyword") String keyword,
                                                                       @Param(value = "count") int count,
                                                                       @Param(value = "offset") int offset);

    List<BoardListArticleDto> selectArticlesForBoardListByCategory( // 게시판&&카테고리 별 게시글 불러오기
                                                                    @Param(value = "boardIndex") int boardIndex,
                                                                    @Param(value = "categoryIndex") int categoryIndex,
                                                                    @Param(value = "keyword") String keyword,
                                                                    @Param(value = "count") int count,
                                                                    @Param(value = "offset") int offset);

    BoardEntity selectBoardByUrlName( // urlName 에 해당하는 게시판 불러오기
                                      @Param(value = "urlName") String urlName);

    List<BoardReadCommentDto> selectJoinComments();

    int deleteJoinComment(JoinCommentEntity joinCommentEntity);

    ArticleEntity selectArticle(
            @Param(value = "bid") int bid,
            @Param(value = "aid") int aid);

    CommentEntity selectComment(
            @Param(value = "aid") int aid,
            @Param(value = "cid") int cid);

    JoinCommentEntity selectJoinComment(
            @Param(value = "cid") int cid);

    ArticleBuyEntity selectArticleBuyByUser(
            @Param(value = "userIndex") int userIndex,
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "articleIndex") int articleIndex);

    ArticleViewEntity selectArticleViewByUser(
            @Param(value = "userIndex") int userIndex,
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "articleIndex") int articleIndex);

    ArticleLikeEntity selectArticleLikeByUser(
            @Param(value = "userIndex") int userIndex,
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "articleIndex") int articleIndex);

    ArticleReportEntity selectArticleReportByUser(
            @Param(value = "userIndex") int userIndex,
            @Param(value = "boardIndex") int boardIndex,
            @Param(value = "articleIndex") int articleIndex);


    CommentLikeEntity selectCommentLikeByUser(
            @Param(value = "userIndex") int userIndex,
            @Param(value = "articleIndex") int articleIndex,
            @Param(value = "commentIndex") int commentIndex);

    CommentReportEntity selectCommentReportByUser(
            @Param(value = "userIndex") int userIndex,
            @Param(value = "articleIndex") int articleIndex,
            @Param(value = "commentIndex") int commentIndex);

    JoinCommentLikeEntity selectJoinCommentLikeByUser(
            @Param(value = "userIndex") int userIndex,
            @Param(value = "commentIndex") int commentIndex);

    int insertArticleBuy(ArticleBuyEntity articleBuy);

    int insertArticleView(ArticleViewEntity articleView);

    int insertArticleLike(ArticleLikeEntity articleLike);

    int insertArticleReport(ArticleReportEntity articleReport);

    int insertCommentLike(CommentLikeEntity commentLike);

    int insertCommentReport(CommentReportEntity commentReport);

    int insertJoinCommentLike(JoinCommentLikeEntity joinCommentLike);

    int updateArticleForBuy(
            @Param(value = "buy") int buy,
            @Param(value = "bid") int bid,
            @Param(value = "aid") int aid);

    int updateArticleForView(
            @Param(value = "view") int view,
            @Param(value = "bid") int bid,
            @Param(value = "aid") int aid);

    int updateArticleForLike(
            @Param(value = "like") int like,
            @Param(value = "bid") int bid,
            @Param(value = "aid") int aid);

    int updateArticleForReport(
            @Param(value = "blindStatus") int blindStatus,
            @Param(value = "bid") int bid,
            @Param(value = "aid") int aid);

    int updateCommentForLike(
            @Param(value = "like") int like,
            @Param(value = "aid") int aid,
            @Param(value = "cid") int cid);

    int updateCommentForReport(
            @Param(value = "blindStatus") int blindStatus,
            @Param(value = "aid") int aid,
            @Param(value = "cid") int cid);

    int updateJoinComment(
            @Param(value = "like") int like,
            @Param(value = "cid") int cid);
}
