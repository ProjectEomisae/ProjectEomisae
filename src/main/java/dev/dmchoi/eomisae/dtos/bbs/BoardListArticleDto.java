package dev.dmchoi.eomisae.dtos.bbs;

import dev.dmchoi.eomisae.entities.bbs.ArticleEntity;

public class BoardListArticleDto extends ArticleEntity {
    private String userNickname;
    private int level;
    private int point;
    private int commentCount;
    private String profileId;

    public String getUserNickname() {
        return userNickname;
    }

    public BoardListArticleDto setUserNickname(String userNickname) {
        this.userNickname = userNickname;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public BoardListArticleDto setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getPoint() {
        return point;
    }

    public BoardListArticleDto setPoint(int point) {
        this.point = point;
        return this;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public BoardListArticleDto setCommentCount(int commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public String getProfileId() {
        return profileId;
    }

    public BoardListArticleDto setProfileId(String profileId) {
        this.profileId = profileId;
        return this;
    }
}
