package dev.dmchoi.eomisae.services.bbs;

import dev.dmchoi.eomisae.dtos.bbs.ArticleReadCommentDto;
import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.bbs.ArticleReadResult;
import dev.dmchoi.eomisae.mappers.IUserMapper;
import dev.dmchoi.eomisae.mappers.bbs.IArticleReadMapper;
import dev.dmchoi.eomisae.vos.bbs.ArticleReadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "dev.dmchoi.eomisae.services.bbs.ArticleReadService")
public class ArticleReadService {
    private final IArticleReadMapper articleReadMapper;
    private final IUserMapper userMapper;

    @Autowired
    public ArticleReadService(IArticleReadMapper articleReadMapper, IUserMapper userMapper) {
        this.articleReadMapper = articleReadMapper;
        this.userMapper = userMapper;
    }

    public void readArticle(ArticleReadVo articleReadVo) {
        ArticleEntity articleEntity = this.articleReadMapper.selectArticleByIndex(articleReadVo.getIndex());
        if (articleEntity == null || articleEntity.getIndex() == 0) {
            articleReadVo.setResult(ArticleReadResult.NOT_FOUND);
            return;
        }
        UserEntity writerEntity = this.userMapper.selectUserByIndex(articleEntity.getUserIndex());
        articleReadVo.setIndex(articleEntity.getIndex());
        articleReadVo.setUserIndex(articleEntity.getUserIndex());
        articleReadVo.setBoardIndex(articleEntity.getBoardIndex());
        articleReadVo.setWrittenAt(articleEntity.getWrittenAt());
        articleReadVo.setTitle(articleEntity.getTitle());
        articleReadVo.setUrl(articleEntity.getUrl());
        articleReadVo.setContent(articleEntity.getContent());
        articleReadVo.setView(articleEntity.getView());
        articleReadVo.setLike(articleEntity.getLike());
        articleReadVo.setBuy(articleEntity.getBuy());
        articleReadVo.setCategoryIndex(articleEntity.getCategoryIndex());
        articleReadVo.setBlindStatus(articleEntity.getBlindStatus());
        articleReadVo.setUserNickname(writerEntity.getNickname());
        articleReadVo.setPoint(writerEntity.getPoint());
        articleReadVo.setLevel(writerEntity.getLevel());
        articleReadVo.setResult(ArticleReadResult.SUCCESS);
        List<ArticleReadCommentDto> articleReadCommentDtos = this.articleReadMapper.selectCommentsForArticleRead(articleReadVo.getIndex());
        for (ArticleReadCommentDto articleReadCommentDto : articleReadCommentDtos) {
        UserEntity tempUser = this.userMapper.selectUserByIndex(articleReadCommentDto.getUserIndex());
        articleReadCommentDto.setUserNickname(tempUser.getNickname());
        articleReadCommentDto.setProfileId(tempUser.getProfileId());
        articleReadCommentDto.setLevel(tempUser.getLevel());
        articleReadCommentDto.setPoint(tempUser.getPoint());
            System.out.println("닉네임" + articleReadCommentDto.getUserNickname() + " ");
            System.out.println("인덱스" + articleReadCommentDto.getIndex() + " ");
            System.out.println("프로필" + articleReadCommentDto.getProfileId() + " ");
        }
        articleReadVo.setComments(articleReadCommentDtos);

    }
}
