package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

import java.util.Date;

public class ArticleEntity implements IEntity<ArticleEntity> {
    public static ArticleEntity build() {
        return new ArticleEntity();
    }

    private int index;
    private int userIndex;
    private int boardIndex;
    private String boardUrlName;
    private Date writtenAt;
    private String title;
    private String tag;
    private String url;
    private String content;
    private int view;
    private int like;
    private int buy;
    private int categoryIndex;
    private int blindStatus;

    public int getIndex() {
        return index;
    }

    public ArticleEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public ArticleEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public ArticleEntity setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
        return this;
    }

    public String getBoardUrlName() {
        return boardUrlName;
    }

    public ArticleEntity setBoardUrlName(String boardUrlName) {
        this.boardUrlName = boardUrlName;
        return this;
    }

    public Date getWrittenAt() {
        return writtenAt;
    }

    public ArticleEntity setWrittenAt(Date writtenAt) {
        this.writtenAt = writtenAt;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public ArticleEntity setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ArticleEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public int getView() {
        return view;
    }

    public ArticleEntity setView(int view) {
        this.view = view;
        return this;
    }

    public int getLike() {
        return like;
    }

    public ArticleEntity setLike(int like) {
        this.like = like;
        return this;
    }

    public int getBuy() {
        return buy;
    }

    public ArticleEntity setBuy(int buy) {
        this.buy = buy;
        return this;
    }

    public int getCategoryIndex() {
        return categoryIndex;
    }

    public ArticleEntity setCategoryIndex(int categoryIndex) {
        this.categoryIndex = categoryIndex;
        return this;
    }

    public int getBlindStatus() {
        return blindStatus;
    }

    public ArticleEntity setBlindStatus(int blindStatus) {
        this.blindStatus = blindStatus;
        return this;
    }

    @Override
    public ArticleEntity clone() {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.index = this.index;
        articleEntity.userIndex = this.userIndex;
        articleEntity.boardIndex = this.boardIndex;
        articleEntity.boardUrlName = this.boardUrlName;
        articleEntity.writtenAt = this.writtenAt;
        articleEntity.title = this.title;
        articleEntity.tag = this.tag;
        articleEntity.url = this.url;
        articleEntity.content = this.content;
        articleEntity.view = this.view;
        articleEntity.like = this.like;
        articleEntity.buy = this.buy;
        articleEntity.categoryIndex = this.categoryIndex;
        articleEntity.blindStatus = this.blindStatus;
        return articleEntity;
    }

    @Override
    public void copyValuesOf(ArticleEntity articleEntity) {
        this.index = articleEntity.index;
        this.userIndex = articleEntity.userIndex;
        this.boardIndex = articleEntity.boardIndex;
        this.boardUrlName = articleEntity.boardUrlName;
        this.writtenAt = articleEntity.writtenAt;
        this.title = articleEntity.title;
        this.tag = articleEntity.tag;
        this.url = articleEntity.url;
        this.content = articleEntity.content;
        this.view = articleEntity.view;
        this.like = articleEntity.like;
        this.buy = articleEntity.buy;
        this.categoryIndex = articleEntity.categoryIndex;
        this.blindStatus = articleEntity.blindStatus;
    }
}
