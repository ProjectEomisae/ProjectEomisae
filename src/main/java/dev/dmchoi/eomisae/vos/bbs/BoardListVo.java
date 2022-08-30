package dev.dmchoi.eomisae.vos.bbs;

import dev.dmchoi.eomisae.dtos.bbs.BoardListArticleDto;
import dev.dmchoi.eomisae.entities.bbs.BoardEntity;
import dev.dmchoi.eomisae.enums.bbs.BoardListResult;
import dev.dmchoi.eomisae.interfaces.IResult;

import java.util.List;

public class BoardListVo extends BoardEntity implements IResult<BoardListResult> {
    private BoardListResult result;
    private List<BoardListArticleDto> articles;

    public List<BoardListArticleDto> getArticles() {
        return articles;
    }

    public BoardListVo setArticles(List<BoardListArticleDto> articles) {
        this.articles = articles;
        return this;
    }

    @Override
    public BoardListResult getResult() {
        return this.result;
    }

    @Override
    public void setResult(BoardListResult result) {
        this.result = result;
    }
}
