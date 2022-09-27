package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

public class CommentLikeEntity implements IEntity<CommentLikeEntity> {
    public static CommentLikeEntity build() {
        return new CommentLikeEntity();
    }

    private int index;
    private int articleIndex;
    private int userIndex;
    private int commentIndex;
    private boolean isChecked;

    public int getIndex() {
        return index;
    }

    public CommentLikeEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getArticleIndex() {
        return articleIndex;
    }

    public CommentLikeEntity setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public CommentLikeEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public int getCommentIndex() {
        return commentIndex;
    }

    public CommentLikeEntity setCommentIndex(int commentIndex) {
        this.commentIndex = commentIndex;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public CommentLikeEntity setChecked(boolean checked) {
        isChecked = checked;
        return this;
    }

    @Override
    public CommentLikeEntity clone() {
        CommentLikeEntity commentLikeEntity = new CommentLikeEntity();
        commentLikeEntity.index = this.index;
        commentLikeEntity.articleIndex = this.articleIndex;
        commentLikeEntity.userIndex = this.userIndex;
        commentLikeEntity.commentIndex = this.commentIndex;
        commentLikeEntity.isChecked = this.isChecked;
        return commentLikeEntity;
    }

    @Override
    public void copyValuesOf(CommentLikeEntity commentLikeEntity) {
        this.index = commentLikeEntity.index;
        this.articleIndex = commentLikeEntity.articleIndex;
        this.userIndex = commentLikeEntity.userIndex;
        this.commentIndex = commentLikeEntity.commentIndex;
        this.isChecked = commentLikeEntity.isChecked;
    }
}
