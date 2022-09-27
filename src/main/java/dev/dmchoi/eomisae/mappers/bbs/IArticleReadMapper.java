package dev.dmchoi.eomisae.mappers.bbs;

import dev.dmchoi.eomisae.dtos.bbs.ArticleReadCommentDto;
import dev.dmchoi.eomisae.dtos.bbs.BoardReadCommentDto;
import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;
import dev.dmchoi.eomisae.entities.bbs.CommentEntity;
import dev.dmchoi.eomisae.entities.bbs.ImageEntity;
import dev.dmchoi.eomisae.vos.bbs.ArticleCommentWriteVo;
import dev.dmchoi.eomisae.vos.bbs.ArticleWriteVo;
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

    List<ArticleReadCommentDto> selectCommentsForArticleRead(
            @Param(value = "aid") int aid);

    int insertImage(ImageEntity imageEntity);

    int insertArticle(ArticleEntity articleEntity);

    int insertComment(ArticleCommentWriteVo articleCommentWriteVo);
    int insertJoinComment(JoinCommentWriteVo joinCommentWriteVo);

    ImageEntity selectImage(
            @Param(value = "id") String id);

    int updateArticle(ArticleWriteVo articleWriteVo);

    int updateForDeletingArticleComment(CommentEntity commentEntity);
}
