package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

import java.util.Date;

public class JoinCommentEntity implements IEntity<JoinCommentEntity> {
    public static JoinCommentEntity build() {
        return new JoinCommentEntity();
    }
    private int index;
    private int boardIndex;
    private int userIndex;
    private String content;
    private Date writtenAt;
    private int parentIndex;
    private int depth;
    private int like;
    private boolean isDeleted;

    public int getIndex() {
        return index;
    }

    public JoinCommentEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public JoinCommentEntity setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public JoinCommentEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public String getContent() {
        return content;
    }

    public JoinCommentEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getWrittenAt() {
        return writtenAt;
    }

    public JoinCommentEntity setWrittenAt(Date writtenAt) {
        this.writtenAt = writtenAt;
        return this;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public JoinCommentEntity setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
        return this;
    }

    public int getDepth() {
        return depth;
    }

    public JoinCommentEntity setDepth(int depth) {
        this.depth = depth;
        return this;
    }

    public int getLike() {
        return like;
    }

    public JoinCommentEntity setLike(int like) {
        this.like = like;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public JoinCommentEntity setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    @Override
    public JoinCommentEntity clone() {
        JoinCommentEntity joinCommentEntity = new JoinCommentEntity();
        joinCommentEntity.index = this.index;
        joinCommentEntity.userIndex = this.userIndex;
        joinCommentEntity.content = this.content;
        joinCommentEntity.writtenAt = this.writtenAt;
        joinCommentEntity.parentIndex = this.parentIndex;
        joinCommentEntity.depth = this.depth;
        joinCommentEntity.like = this.like;
        joinCommentEntity.isDeleted = this.isDeleted;
        return joinCommentEntity;
    }

    @Override
    public void copyValuesOf(JoinCommentEntity joinCommentEntity) {
        this.index = joinCommentEntity.index;
        this.userIndex = joinCommentEntity.userIndex;
        this.content = joinCommentEntity.content;
        this.writtenAt = joinCommentEntity.writtenAt;
        this.parentIndex = joinCommentEntity.parentIndex;
        this.depth = joinCommentEntity.depth;
        this.like = joinCommentEntity.like;
        this.isDeleted = joinCommentEntity.isDeleted;
    }
}
