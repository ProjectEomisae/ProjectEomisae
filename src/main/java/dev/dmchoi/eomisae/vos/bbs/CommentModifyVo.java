package dev.dmchoi.eomisae.vos.bbs;

import dev.dmchoi.eomisae.entities.bbs.CommentEntity;
import dev.dmchoi.eomisae.enums.bbs.CommentModifyResult;
import dev.dmchoi.eomisae.interfaces.IResult;

public class CommentModifyVo extends CommentEntity implements IResult<CommentModifyResult> {
    private CommentModifyResult result;

    @Override
    public CommentModifyResult getResult() {
        return result;
    }

    @Override
    public void setResult(CommentModifyResult result) {
        this.result = result;
    }
}
