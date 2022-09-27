package dev.dmchoi.eomisae.vos.bbs;

import dev.dmchoi.eomisae.entities.bbs.CommentEntity;
import dev.dmchoi.eomisae.entities.bbs.JoinCommentEntity;
import dev.dmchoi.eomisae.enums.bbs.ArticleCommentWriteResult;
import dev.dmchoi.eomisae.interfaces.IResult;

public class JoinCommentWriteVo extends JoinCommentEntity implements IResult<ArticleCommentWriteResult> {
    private ArticleCommentWriteResult result;

    @Override
    public ArticleCommentWriteResult getResult() {
        return result;
    }

    @Override
    public void setResult(ArticleCommentWriteResult result) {
        this.result = result;
    }
}
