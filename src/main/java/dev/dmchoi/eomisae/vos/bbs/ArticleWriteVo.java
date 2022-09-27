package dev.dmchoi.eomisae.vos.bbs;

import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;
import dev.dmchoi.eomisae.enums.bbs.ArticleWriteResult;
import dev.dmchoi.eomisae.interfaces.IResult;

public class ArticleWriteVo extends ArticleEntity implements IResult<ArticleWriteResult> {
    private ArticleWriteResult result;

    private String userNickname;
    private int level;
    private int point;

    public String getUserNickname() {
        return userNickname;
    }

    public ArticleWriteVo setUserNickname(String userNickname) {
        this.userNickname = userNickname;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public ArticleWriteVo setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getPoint() {
        return point;
    }

    public ArticleWriteVo setPoint(int point) {
        this.point = point;
        return this;
    }

    @Override
    public ArticleWriteResult getResult() {
        return result;
    }

    @Override
    public void setResult(ArticleWriteResult result) {
        this.result = result;
    }
}
