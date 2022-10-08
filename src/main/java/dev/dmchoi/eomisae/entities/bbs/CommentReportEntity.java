package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

import java.util.Date;

public class CommentReportEntity implements IEntity<CommentReportEntity> {
    public static CommentReportEntity build() {
        return new CommentReportEntity();
    }

    private int index;
    private int articleIndex;
    private int userIndex;
    private int commentIndex;
    private Date createdAt = new Date();
    private boolean isChecked;

    public int getIndex() {
        return index;
    }

    public CommentReportEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getArticleIndex() {
        return articleIndex;
    }

    public CommentReportEntity setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public CommentReportEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public int getCommentIndex() {
        return commentIndex;
    }

    public CommentReportEntity setCommentIndex(int commentIndex) {
        this.commentIndex = commentIndex;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public CommentReportEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public CommentReportEntity setChecked(boolean checked) {
        isChecked = checked;
        return this;
    }

    @Override
    public CommentReportEntity clone() {
        CommentReportEntity commentReportEntity = new CommentReportEntity();
        commentReportEntity.index = this.index;
        commentReportEntity.articleIndex = this.articleIndex;
        commentReportEntity.userIndex = this.userIndex;
        commentReportEntity.commentIndex = this.commentIndex;
        commentReportEntity.createdAt = this.createdAt;
        commentReportEntity.isChecked = this.isChecked;
        return commentReportEntity;
    }

    @Override
    public void copyValuesOf(CommentReportEntity commentReportEntity) {
        this.index = commentReportEntity.index;
        this.articleIndex = commentReportEntity.articleIndex;
        this.userIndex = commentReportEntity.userIndex;
        this.commentIndex = commentReportEntity.commentIndex;
        this.createdAt = commentReportEntity.createdAt;
        this.isChecked = commentReportEntity.isChecked;
    }
}
