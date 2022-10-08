package dev.dmchoi.eomisae.vos.bbs;

import dev.dmchoi.eomisae.entities.bbs.CommentEntity;
import dev.dmchoi.eomisae.enums.bbs.CommentModifyResult;
import dev.dmchoi.eomisae.enums.bbs.CommentReadResult;
import dev.dmchoi.eomisae.interfaces.IResult;

public class CommentReadVo extends CommentEntity implements IResult<CommentReadResult> {
    private CommentReadResult result;

    @Override
    public CommentReadResult getResult() {
        return result;
    }

    @Override
    public void setResult(CommentReadResult result) {
        this.result = result;
    }
}
