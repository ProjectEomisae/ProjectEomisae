package dev.dmchoi.eomisae.mappers.bbs;

import dev.dmchoi.eomisae.dtos.bbs.ArticleReadCommentDto;
import dev.dmchoi.eomisae.dtos.bbs.BoardReadCommentDto;
import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;
import dev.dmchoi.eomisae.entities.bbs.CommentEntity;
import dev.dmchoi.eomisae.entities.bbs.ImageEntity;
import dev.dmchoi.eomisae.vos.bbs.ArticleCommentWriteVo;
import dev.dmchoi.eomisae.vos.bbs.ArticleWriteVo;
import dev.dmchoi.eomisae.vos.bbs.CommentModifyVo;
import dev.dmchoi.eomisae.vos.bbs.JoinCommentWriteVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IArticleReadMapper {
    int deleteArticle(ArticleEntity articleEntity);
    ArticleEntity selectArticleByIndex(
            @Param(value = "index") int index);
    CommentEntity selectArticleCommentByIndex(
            @Param(value = "index") int index);

    int selectCommentsCountForArticleRead(
            @Param(value = "aid") int aid);

    List<ArticleReadCommentDto> selectCommentsForArticleRead(
            @Param(value = "aid") int aid,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset);

    int insertImage(ImageEntity imageEntity);

    int insertArticle(ArticleEntity articleEntity);

    int insertComment(ArticleCommentWriteVo articleCommentWriteVo);
    int insertJoinComment(JoinCommentWriteVo joinCommentWriteVo);

    ImageEntity selectImage(
            @Param(value = "id") String id);

    int updateArticle(ArticleWriteVo articleWriteVo);

    int updateArticleComment(CommentModifyVo commentModifyVo);

    int updateForDeletingArticleComment(CommentEntity commentEntity);
}
