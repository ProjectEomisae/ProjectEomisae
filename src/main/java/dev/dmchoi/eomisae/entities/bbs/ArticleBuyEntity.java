package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

public class ArticleBuyEntity implements IEntity<ArticleBuyEntity> {
    public static ArticleBuyEntity build() {
        return new ArticleBuyEntity();
    }

    private int index;
    private int boardIndex;
    private int articleIndex;
    private int userIndex;
    private boolean isChecked;

    public int getIndex() {
        return index;
    }

    public ArticleBuyEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public ArticleBuyEntity setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
        return this;
    }

    public int getArticleIndex() {
        return articleIndex;
    }

    public ArticleBuyEntity setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public ArticleBuyEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public ArticleBuyEntity setChecked(boolean checked) {
        isChecked = checked;
        return this;
    }

    @Override
    public ArticleBuyEntity clone() {
        ArticleBuyEntity articleBuyEntity = new ArticleBuyEntity();
        articleBuyEntity.index = this.index;
        articleBuyEntity.boardIndex = this.boardIndex;
        articleBuyEntity.articleIndex = this.articleIndex;
        articleBuyEntity.userIndex = this.userIndex;
        articleBuyEntity.isChecked = this.isChecked;
        return articleBuyEntity;
    }

    @Override
    public void copyValuesOf(ArticleBuyEntity articleBuyEntity) {
        this.index = articleBuyEntity.index;
        this.boardIndex = articleBuyEntity.boardIndex;
        this.articleIndex = articleBuyEntity.articleIndex;
        this.userIndex = articleBuyEntity.userIndex;
        this.isChecked = articleBuyEntity.isChecked;
    }
}
