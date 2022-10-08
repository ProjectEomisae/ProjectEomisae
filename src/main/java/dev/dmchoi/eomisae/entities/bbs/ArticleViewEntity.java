package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

public class ArticleViewEntity implements IEntity<ArticleViewEntity> {
    public static ArticleViewEntity build() {
        return new ArticleViewEntity();
    }

    private int index;
    private int boardIndex;
    private int articleIndex;
    private int userIndex;
    private boolean isChecked;

    public int getIndex() {
        return index;
    }

    public ArticleViewEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public ArticleViewEntity setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
        return this;
    }

    public int getArticleIndex() {
        return articleIndex;
    }

    public ArticleViewEntity setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public ArticleViewEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public ArticleViewEntity setChecked(boolean checked) {
        isChecked = checked;
        return this;
    }

    @Override
    public ArticleViewEntity clone() {
        ArticleViewEntity articleViewEntity = new ArticleViewEntity();
        articleViewEntity.index = this.index;
        articleViewEntity.boardIndex = this.boardIndex;
        articleViewEntity.articleIndex = this.articleIndex;
        articleViewEntity.userIndex = this.userIndex;
        articleViewEntity.isChecked = this.isChecked;
        return articleViewEntity;
    }

    @Override
    public void copyValuesOf(ArticleViewEntity articleViewEntity) {
        this.index = articleViewEntity.index;
        this.boardIndex = articleViewEntity.boardIndex;
        this.articleIndex = articleViewEntity.articleIndex;
        this.userIndex = articleViewEntity.userIndex;
        this.isChecked = articleViewEntity.isChecked;
    }
}
