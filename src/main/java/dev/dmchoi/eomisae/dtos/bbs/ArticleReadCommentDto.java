package dev.dmchoi.eomisae.dtos.bbs;

import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;

public class ArticleReadCommentDto extends ArticleEntity {
    private String userNickname;
    private int level;
    private int point;
    private String profileId;
    private int commentCount;
    private int depth;
    private int parentIndex;
    private int parentUserIndex;
    private String parentUserNickname;
    private boolean isDeleted;

    public String getUserNickname() {
        return userNickname;
    }

    public ArticleReadCommentDto setUserNickname(String userNickname) {
        this.userNickname = userNickname;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public ArticleReadCommentDto setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getPoint() {
        return point;
    }

    public ArticleReadCommentDto setPoint(int point) {
        this.point = point;
        return this;
    }

    public String getProfileId() {
        return profileId;
    }

    public ArticleReadCommentDto setProfileId(String profileId) {
        this.profileId = profileId;
        return this;
    }

        public int getCommentCount() {
        return commentCount;
    }

    public ArticleReadCommentDto setCommentCount(int commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public int getDepth() {
        return depth;
    }

    public ArticleReadCommentDto setDepth(int depth) {
        this.depth = depth;
        return this;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public ArticleReadCommentDto setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
        return this;
    }

    public int getParentUserIndex() {
        return parentUserIndex;
    }

    public ArticleReadCommentDto setParentUserIndex(int parentUserIndex) {
        this.parentUserIndex = parentUserIndex;
        return this;
    }

    public String getParentUserNickname() {
        return parentUserNickname;
    }

    public ArticleReadCommentDto setParentUserNickname(String parentUserNickname) {
        this.parentUserNickname = parentUserNickname;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public ArticleReadCommentDto setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }
}
