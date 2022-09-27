package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

import java.util.Date;

public class CommentEntity implements IEntity<CommentEntity> {
    public static CommentEntity build() {
        return new CommentEntity();
    }
    private int index;
    private int articleIndex;
    private int userIndex;
    private String content;
    private Date writtenAt;
    private int parentIndex;
    private int parentUserIndex;
    private int depth;

    private int like;
    private boolean isDeleted;

    public int getIndex() {
        return index;
    }

    public CommentEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getArticleIndex() {
        return articleIndex;
    }

    public CommentEntity setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public CommentEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getWrittenAt() {
        return writtenAt;
    }

    public CommentEntity setWrittenAt(Date writtenAt) {
        this.writtenAt = writtenAt;
        return this;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public CommentEntity setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
        return this;
    }

    public int getParentUserIndex() {
        return parentUserIndex;
    }

    public CommentEntity setParentUserIndex(int parentUserIndex) {
        this.parentUserIndex = parentUserIndex;
        return this;
    }

    public int getDepth() {
        return depth;
    }

    public CommentEntity setDepth(int depth) {
        this.depth = depth;
        return this;
    }

    public int getLike() {
        return like;
    }

    public CommentEntity setLike(int like) {
        this.like = like;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public CommentEntity setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    @Override
    public CommentEntity clone() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.index = this.index;
        commentEntity.articleIndex = this.articleIndex;
        commentEntity.userIndex = this.userIndex;
        commentEntity.content = this.content;
        commentEntity.writtenAt = this.writtenAt;
        commentEntity.parentIndex = this.parentIndex;
        commentEntity.parentUserIndex = this.parentUserIndex;
        commentEntity.depth = this.depth;
        commentEntity.like = this.like;
        commentEntity.isDeleted = this.isDeleted;
        return commentEntity;
    }

    @Override
    public void copyValuesOf(CommentEntity commentEntity) {
        this.index = commentEntity.index;
        this.articleIndex = commentEntity.articleIndex;
        this.userIndex = commentEntity.userIndex;
        this.content = commentEntity.content;
        this.writtenAt = commentEntity.writtenAt;
        this.parentIndex = commentEntity.parentIndex;
        this.parentUserIndex = commentEntity.parentUserIndex;
        this.depth = commentEntity.depth;
        this.like = commentEntity.like;
        this.isDeleted = commentEntity.isDeleted;
    }
}
