package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

import java.util.Date;

public class ArticleReportEntity implements IEntity<ArticleReportEntity> {
    public static ArticleReportEntity build() {
        return new ArticleReportEntity();
    }

    private int index;
    private int boardIndex;
    private int articleIndex;
    private int userIndex;
    private Date createdAt = new Date();
    private boolean isChecked;

    public int getIndex() {
        return index;
    }

    public ArticleReportEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public ArticleReportEntity setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
        return this;
    }

    public int getArticleIndex() {
        return articleIndex;
    }

    public ArticleReportEntity setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public ArticleReportEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ArticleReportEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public ArticleReportEntity setChecked(boolean checked) {
        isChecked = checked;
        return this;
    }

    @Override
    public ArticleReportEntity clone() {
        ArticleReportEntity articleReportEntity = new ArticleReportEntity();
        articleReportEntity.index = this.index;
        articleReportEntity.boardIndex = this.boardIndex;
        articleReportEntity.articleIndex = this.articleIndex;
        articleReportEntity.userIndex = this.userIndex;
        articleReportEntity.createdAt = this.createdAt;
        articleReportEntity.isChecked = this.isChecked;
        return articleReportEntity;
    }

    @Override
    public void copyValuesOf(ArticleReportEntity articleReportEntity) {
        this.index = articleReportEntity.index;
        this.boardIndex = articleReportEntity.boardIndex;
        this.articleIndex = articleReportEntity.articleIndex;
        this.userIndex = articleReportEntity.userIndex;
        this.createdAt = articleReportEntity.createdAt;
        this.isChecked = articleReportEntity.isChecked;
    }
}
