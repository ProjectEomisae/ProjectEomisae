package dev.dmchoi.eomisae.vos.bbs;

import dev.dmchoi.eomisae.dtos.bbs.ArticleReadCommentDto;
import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;
import dev.dmchoi.eomisae.enums.bbs.ArticleReadResult;
import dev.dmchoi.eomisae.interfaces.IResult;

import java.util.List;

public class ArticleReadVo extends ArticleEntity implements IResult<ArticleReadResult> {
    private ArticleReadResult result;
    private String userNickname;
    private String articleProfileId;
    private int level;
    private int point;
    private List<ArticleReadCommentDto> comments;
    private List<String> tags;

    public String getUserNickname() {
        return userNickname;
    }

    public ArticleReadVo setUserNickname(String userNickname) {
        this.userNickname = userNickname;
        return this;
    }

    public String getArticleProfileId() {
        return articleProfileId;
    }

    public ArticleReadVo setArticleProfileId(String articleProfileId) {
        this.articleProfileId = articleProfileId;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public ArticleReadVo setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getPoint() {
        return point;
    }

    public ArticleReadVo setPoint(int point) {
        this.point = point;
        return this;
    }

    public List<ArticleReadCommentDto> getComments() {
        return comments;
    }

    public ArticleReadVo setComments(List<ArticleReadCommentDto> comments) {
        this.comments = comments;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public ArticleReadVo setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public ArticleReadResult getResult() {
        return result;
    }

    @Override
    public void setResult(ArticleReadResult result) {
        this.result = result;

    }
}
