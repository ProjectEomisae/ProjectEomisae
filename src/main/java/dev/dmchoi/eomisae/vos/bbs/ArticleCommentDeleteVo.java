package dev.dmchoi.eomisae.vos.bbs;

import dev.dmchoi.eomisae.entities.bbs.CommentEntity;
import dev.dmchoi.eomisae.enums.bbs.ArticleCommentDeleteResult;
import dev.dmchoi.eomisae.enums.bbs.ArticleCommentWriteResult;
import dev.dmchoi.eomisae.interfaces.IResult;

public class ArticleCommentDeleteVo extends CommentEntity implements IResult<ArticleCommentDeleteResult> {
    private ArticleCommentDeleteResult result;

    @Override
    public ArticleCommentDeleteResult getResult() {
        return result;
    }

    @Override
    public void setResult(ArticleCommentDeleteResult result) {
        this.result = result;
    }
}
