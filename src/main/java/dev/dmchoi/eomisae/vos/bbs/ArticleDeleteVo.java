package dev.dmchoi.eomisae.vos.bbs;

import dev.dmchoi.eomisae.dtos.bbs.ArticleReadCommentDto;
import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;
import dev.dmchoi.eomisae.enums.bbs.ArticleDeleteResult;
import dev.dmchoi.eomisae.enums.bbs.ArticleReadResult;
import dev.dmchoi.eomisae.interfaces.IResult;

import java.util.List;

public class ArticleDeleteVo extends ArticleEntity implements IResult<ArticleDeleteResult> {
    private ArticleDeleteResult result;
    private String userNickname;
    private int level;
    private int point;
    private List<ArticleReadCommentDto> comments;

    public String getUserNickname() {
        return userNickname;
    }

    public ArticleDeleteVo setUserNickname(String userNickname) {
        this.userNickname = userNickname;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public ArticleDeleteVo setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getPoint() {
        return point;
    }

    public ArticleDeleteVo setPoint(int point) {
        this.point = point;
        return this;
    }

    public List<ArticleReadCommentDto> getComments() {
        return comments;
    }

    public ArticleDeleteVo setComments(List<ArticleReadCommentDto> comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public ArticleDeleteResult getResult() {
        return this.result;
    }

    @Override
    public void setResult(ArticleDeleteResult result) {
        this.result = result;
    }
}
