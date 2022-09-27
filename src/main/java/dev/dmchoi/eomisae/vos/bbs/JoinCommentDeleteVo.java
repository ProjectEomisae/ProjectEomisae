package dev.dmchoi.eomisae.vos.bbs;

import dev.dmchoi.eomisae.dtos.bbs.ArticleReadCommentDto;
import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;
import dev.dmchoi.eomisae.entities.bbs.JoinCommentEntity;
import dev.dmchoi.eomisae.enums.bbs.ArticleDeleteResult;
import dev.dmchoi.eomisae.enums.bbs.JoinCommentDeleteResult;
import dev.dmchoi.eomisae.interfaces.IResult;

import java.util.List;

public class JoinCommentDeleteVo extends JoinCommentEntity implements IResult<JoinCommentDeleteResult> {
    private JoinCommentDeleteResult result;


    @Override
    public JoinCommentDeleteResult getResult() {
        return this.result;
    }

    @Override
    public void setResult(JoinCommentDeleteResult result) {
        this.result = result;
    }
}
