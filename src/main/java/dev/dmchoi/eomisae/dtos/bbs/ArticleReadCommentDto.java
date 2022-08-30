package dev.dmchoi.eomisae.dtos.bbs;

import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;

public class ArticleReadCommentDto extends ArticleEntity {
    private String userNickname;
    private int level;
    private int point;
    private String profileId;
    private int commentCount;

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
}
