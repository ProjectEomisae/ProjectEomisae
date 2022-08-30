package dev.dmchoi.eomisae.mappers.bbs;

import dev.dmchoi.eomisae.dtos.bbs.ArticleReadCommentDto;
import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IArticleReadMapper {
    ArticleEntity selectArticleByIndex(
            @Param(value = "index") int index);

    List<ArticleReadCommentDto> selectCommentsForArticleRead(
            @Param(value = "aid") int aid);
}
