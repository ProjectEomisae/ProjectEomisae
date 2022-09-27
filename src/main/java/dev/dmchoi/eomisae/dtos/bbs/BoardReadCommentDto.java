package dev.dmchoi.eomisae.dtos.bbs;

import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;
import dev.dmchoi.eomisae.entities.bbs.BoardEntity;

import java.util.Date;

public class BoardReadCommentDto extends BoardEntity {
    private int userIndex;
    private String userNickname;
    private int level;
    private int point;
    private String profileId;
    private int commentCount;
    private String content;
    private int depth;
    private int like;
    private int parentIndex;
    private Date writtenAt;
    private boolean isDeleted;

    public int getUserIndex() {
        return userIndex;
    }

    public BoardReadCommentDto setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public BoardReadCommentDto setUserNickname(String userNickname) {
        this.userNickname = userNickname;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public BoardReadCommentDto setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getPoint() {
        return point;
    }

    public BoardReadCommentDto setPoint(int point) {
        this.point = point;
        return this;
    }

    public String getProfileId() {
        return profileId;
    }

    public BoardReadCommentDto setProfileId(String profileId) {
        this.profileId = profileId;
        return this;
    }

        public int getCommentCount() {
        return commentCount;
    }

    public BoardReadCommentDto setCommentCount(int commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public String getContent() {
        return content;
    }

    public BoardReadCommentDto setContent(String content) {
        this.content = content;
        return this;
    }

    public int getDepth() {
        return depth;
    }

    public BoardReadCommentDto setDepth(int depth) {
        this.depth = depth;
        return this;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public BoardReadCommentDto setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
        return this;
    }

    public Date getWrittenAt() {
        return writtenAt;
    }

    public BoardReadCommentDto setWrittenAt(Date writtenAt) {
        this.writtenAt = writtenAt;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public BoardReadCommentDto setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public int getLike() {
        return like;
    }

    public BoardReadCommentDto setLike(int like) {
        this.like = like;
        return this;
    }
}
