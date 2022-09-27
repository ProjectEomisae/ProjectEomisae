package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

public class ArticleLikeEntity implements IEntity<ArticleLikeEntity> {
    public static ArticleLikeEntity build() {
        return new ArticleLikeEntity();
    }

    private int index;
    private int boardIndex;
    private int articleIndex;
    private int userIndex;
    private boolean isChecked;

    public int getIndex() {
        return index;
    }

    public ArticleLikeEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public ArticleLikeEntity setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
        return this;
    }

    public int getArticleIndex() {
        return articleIndex;
    }

    public ArticleLikeEntity setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public ArticleLikeEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public ArticleLikeEntity setChecked(boolean checked) {
        isChecked = checked;
        return this;
    }

    @Override
    public ArticleLikeEntity clone() {
        ArticleLikeEntity articleLikeEntity = new ArticleLikeEntity();
        articleLikeEntity.index = this.index;
        articleLikeEntity.boardIndex = this.boardIndex;
        articleLikeEntity.articleIndex = this.articleIndex;
        articleLikeEntity.userIndex = this.userIndex;
        articleLikeEntity.isChecked = this.isChecked;
        return articleLikeEntity;
    }

    @Override
    public void copyValuesOf(ArticleLikeEntity articleLikeEntity) {
        this.index = articleLikeEntity.index;
        this.boardIndex = articleLikeEntity.boardIndex;
        this.articleIndex = articleLikeEntity.articleIndex;
        this.userIndex = articleLikeEntity.userIndex;
        this.isChecked = articleLikeEntity.isChecked;
    }
}
