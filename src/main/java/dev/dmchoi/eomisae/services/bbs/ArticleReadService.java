package dev.dmchoi.eomisae.services.bbs;

import dev.dmchoi.eomisae.dtos.bbs.ArticleReadCommentDto;
import dev.dmchoi.eomisae.dtos.bbs.BoardReadCommentDto;
import dev.dmchoi.eomisae.entities.bbs.*;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.bbs.*;
import dev.dmchoi.eomisae.mappers.IUserMapper;
import dev.dmchoi.eomisae.mappers.bbs.IArticleReadMapper;
import dev.dmchoi.eomisae.mappers.bbs.IBoardListMapper;
import dev.dmchoi.eomisae.models.PagingModel;
import dev.dmchoi.eomisae.vos.bbs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "dev.dmchoi.eomisae.services.bbs.ArticleReadService")
public class ArticleReadService {
    private final IArticleReadMapper articleReadMapper;
    private final IUserMapper userMapper;
    private final IBoardListMapper boardListMapper;

    @Autowired
    public ArticleReadService(IArticleReadMapper articleReadMapper, IUserMapper userMapper, IBoardListMapper boardListMapper) {
        this.articleReadMapper = articleReadMapper;
        this.userMapper = userMapper;
        this.boardListMapper = boardListMapper;
    }

    public void addArticle(UserEntity user, ArticleWriteVo articleWriteVo) {
        articleWriteVo.setUserIndex(user.getIndex());
        articleWriteVo.setWrittenAt(new Date());
        if (articleWriteVo.getUrl() == null) {
            articleWriteVo.setUrl(null);
        }
        if (articleWriteVo.getTag() == null) {
            articleWriteVo.setTag(null);
        }
        articleWriteVo.setView(0);
        articleWriteVo.setLike(0);
        articleWriteVo.setBuy(0);
        articleWriteVo.setBlindStatus(0);

        if (articleWriteVo.getBoardIndex() == 0) {
            articleWriteVo.setResult(ArticleWriteResult.NOT_FOUND);
            return;
        }
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(articleWriteVo.getBoardUrlName());
        user.setIndex(articleWriteVo.getUserIndex());

        int userLevel = user == null ? 0 : user.getLevel();

        if (boardEntity.getWriteLevel() > userLevel) {
            articleWriteVo.setResult(ArticleWriteResult.NOT_ALLOWED);
            return;
        }
        System.out.println("게시판 레벨 : " + boardEntity.getWriteLevel());
        articleWriteVo.setResult(ArticleWriteResult.SUCCESS);
        this.articleReadMapper.insertArticle(articleWriteVo);
    }

    public void deleteArticle(UserEntity user, ArticleDeleteVo articleDeleteVo) {
        ArticleEntity articleEntity = this.articleReadMapper.selectArticleByIndex(articleDeleteVo.getIndex());
        if (user.getIndex() != articleEntity.getUserIndex()) {
            System.out.println("삭제 불가");
            articleDeleteVo.setResult(ArticleDeleteResult.FAILURE);
            return;
        }
        articleDeleteVo.setResult(ArticleDeleteResult.SUCCESS);
        this.articleReadMapper.deleteArticle(articleEntity);
    }

    public void updateForDeletingArticleComment(UserEntity user, ArticleCommentDeleteVo articleCommentDeleteVo) {
        CommentEntity commentEntity = this.articleReadMapper.selectArticleCommentByIndex(articleCommentDeleteVo.getIndex());
        if (user.getIndex() != commentEntity.getUserIndex()) {
            System.out.println("삭제 불가");
            articleCommentDeleteVo.setResult(ArticleCommentDeleteResult.FAILURE);
            return;
        }
        articleCommentDeleteVo.setResult(ArticleCommentDeleteResult.SUCCESS);
        this.articleReadMapper.updateForDeletingArticleComment(commentEntity);
    }

    public void readArticle(ArticleReadVo articleReadVo) {
        this.readArticle(articleReadVo, null);
    }

    public void readArticle(ArticleReadVo articleReadVo, PagingModel pagingModel) {
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
        List<String> tags = new ArrayList<>();
        if (articleEntity.getTag() != null) {
            String[] tagName = articleEntity.getTag().split(",");
            for (String result : tagName) {
                tags.add(result);
                articleReadVo.setTags(tags);
            }
        }
        ArticleReadVo temp = boardListMapper.selectPageByBoardIndex(articleReadVo.getBoardIndex(), articleReadVo.getIndex());
        articleReadVo.setPrev(temp.getPrev())
                .setNext(temp.getNext());
        articleReadVo.setUrl(articleEntity.getUrl());
        articleReadVo.setDiscountCode(articleEntity.getDiscountCode());
        articleReadVo.setShippingInfo(articleEntity.getShippingInfo());
        articleReadVo.setOrderDate(articleEntity.getOrderDate());
        articleReadVo.setShoppingMall(articleEntity.getShoppingMall());
        articleReadVo.setContact(articleEntity.getContact());
        articleReadVo.setBrand(articleEntity.getBrand());
        articleReadVo.setProductName(articleEntity.getProductName());
        articleReadVo.setProductSize(articleEntity.getProductSize());
        articleReadVo.setMySize(articleEntity.getMySize());
        articleReadVo.setExchangeSize(articleEntity.getExchangeSize());
        articleReadVo.setCurrencyForProduct(articleEntity.getCurrencyForProduct());
        articleReadVo.setProductPrice(articleEntity.getProductPrice());
        articleReadVo.setCurrencyForPurchasing(articleEntity.getCurrencyForPurchasing());
        articleReadVo.setPurchaseProductPrice(articleEntity.getPurchaseProductPrice());
        articleReadVo.setCurrencyForSailing(articleEntity.getCurrencyForSailing());
        articleReadVo.setSaleProductPrice(articleEntity.getSaleProductPrice());
        articleReadVo.setOuter(articleEntity.getOuter());
        articleReadVo.setTop(articleEntity.getTop());
        articleReadVo.setBottom(articleEntity.getBottom());
        articleReadVo.setShoes(articleEntity.getShoes());
        articleReadVo.setAcc(articleEntity.getAcc());
        articleReadVo.setContent(articleEntity.getContent());
        articleReadVo.setView(articleEntity.getView());
        articleReadVo.setLike(articleEntity.getLike());
        articleReadVo.setBuy(articleEntity.getBuy());
        articleReadVo.setCategoryIndex(articleEntity.getCategoryIndex());
        articleReadVo.setGender(articleEntity.getGender());
        articleReadVo.setProductStatus(articleEntity.getProductStatus());
        articleReadVo.setTradeMethod(articleEntity.getTradeMethod());
        articleReadVo.setThumbnailId(articleEntity.getThumbnailId());
        articleReadVo.setBlindStatus(articleEntity.getBlindStatus());
        articleReadVo.setUserNickname(writerEntity.getNickname());
        if (writerEntity.getProfileId() != null) {
            articleReadVo.setArticleProfileId(writerEntity.getProfileId());
        } else {
            articleReadVo.setArticleProfileId("");
        }
        articleReadVo.setPoint(writerEntity.getPoint());
        articleReadVo.setLevel(writerEntity.getLevel());
        articleReadVo.setResult(ArticleReadResult.SUCCESS);
        if (pagingModel != null) {
            List<ArticleReadCommentDto> articleReadCommentDtos = this.articleReadMapper.selectCommentsForArticleRead(articleReadVo.getIndex(), 20, (pagingModel.requestPage - 1) * 20);
            for (ArticleReadCommentDto articleReadCommentDto : articleReadCommentDtos) {
                UserEntity tempUser = this.userMapper.selectUserByIndex(articleReadCommentDto.getUserIndex());
                if (articleReadCommentDto.getParentUserIndex() != 0) {
                    UserEntity parentUser = this.userMapper.selectUserByIndex(articleReadCommentDto.getParentUserIndex());
                    articleReadCommentDto.setParentUserNickname(parentUser.getNickname());
                }
                articleReadCommentDto.setUserNickname(tempUser.getNickname());
                articleReadCommentDto.setProfileId(tempUser.getProfileId());
                articleReadCommentDto.setLevel(tempUser.getLevel());
                articleReadCommentDto.setPoint(tempUser.getPoint());

//            System.out.println("닉네임 : " + articleReadCommentDto.getUserNickname() + " ");
//            System.out.println("인덱스 : " + articleReadCommentDto.getIndex() + " ");
//            System.out.println("프로필 : " + articleReadCommentDto.getProfileId() + " ");
            }
            articleReadVo.setComments(articleReadCommentDtos);
        }
    }

    public int getCommentsCountForArticleRead(ArticleReadVo articleReadVo) {
        return this.articleReadMapper.selectCommentsCountForArticleRead(articleReadVo.getIndex());
    }

    public void readComment(CommentReadVo commentReadVo) {
        CommentEntity commentEntity = this.articleReadMapper.selectArticleCommentByIndex(commentReadVo.getIndex());
        if (commentEntity == null || commentEntity.getIndex() == 0) {
            commentReadVo.setResult(CommentReadResult.NOT_FOUND);
            return;
        }
        commentReadVo.copyValuesOf(commentEntity);
        commentReadVo.setResult(CommentReadResult.SUCCESS);
    }

    public void uploadImages(ImageEntity... imageEntities) {
        for (ImageEntity imageEntity : imageEntities) {
            this.articleReadMapper.insertImage(imageEntity);
        }
    }

    public ImageEntity getImage(String id) {
        return this.articleReadMapper.selectImage(id);
    }

    public void modifyArticle(ArticleWriteVo articleWriteVo) {
        ArticleEntity articleEntity = this.articleReadMapper.selectArticleByIndex(articleWriteVo.getIndex());
        if (articleEntity == null || articleEntity.getIndex() == 0) {
            articleWriteVo.setResult(ArticleWriteResult.NOT_ALLOWED);
            return;
        }
        articleWriteVo.setUserIndex(articleEntity.getUserIndex());
        articleWriteVo.setBoardIndex(articleEntity.getBoardIndex());
        articleWriteVo.setResult(ArticleWriteResult.SUCCESS);
        this.articleReadMapper.updateArticle(articleWriteVo);
    }

    public void modifyArticleComment(CommentModifyVo commentModifyVo) {
        CommentEntity commentEntity = this.articleReadMapper.selectArticleCommentByIndex(commentModifyVo.getIndex());
        if (commentEntity == null || commentEntity.getIndex() == 0) {
            commentModifyVo.setResult(CommentModifyResult.NOT_FOUND);
            return;
        }
        commentModifyVo.setUserIndex(commentEntity.getUserIndex());
        commentModifyVo.setResult(CommentModifyResult.SUCCESS);
        this.articleReadMapper.updateArticleComment(commentModifyVo);
    }

    public void writeComment(String urlName, UserEntity user, ArticleCommentWriteVo articleCommentWriteVo) {
        if (articleCommentWriteVo.getArticleIndex() == 0) {
            articleCommentWriteVo.setResult(ArticleCommentWriteResult.NOT_FOUND);
            return;
        }
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(urlName);

        int userLevel = user == null ? 0 : user.getLevel();

        if (boardEntity.getCommentLevel() > userLevel) {
            articleCommentWriteVo.setResult(ArticleCommentWriteResult.NOT_ALLOWED);
            return;
        }
        articleCommentWriteVo.setResult(ArticleCommentWriteResult.SUCCESS);
        this.articleReadMapper.insertComment(articleCommentWriteVo);
    }

    public void writeJoinComment(String urlName, UserEntity user, JoinCommentWriteVo joinCommentWriteVo) {
        BoardEntity boardEntity = this.boardListMapper.selectBoardByUrlName(urlName);

        int userLevel = user == null ? 0 : user.getLevel();

        if (boardEntity.getCommentLevel() > userLevel) {
            joinCommentWriteVo.setResult(ArticleCommentWriteResult.NOT_ALLOWED);
            return;
        }
        joinCommentWriteVo.setResult(ArticleCommentWriteResult.SUCCESS);
        this.articleReadMapper.insertJoinComment(joinCommentWriteVo);
    }
}
