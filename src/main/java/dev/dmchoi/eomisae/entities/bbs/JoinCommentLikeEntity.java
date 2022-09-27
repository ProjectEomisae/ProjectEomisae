package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

public class JoinCommentLikeEntity implements IEntity<JoinCommentLikeEntity> {
    public static JoinCommentLikeEntity build() {
        return new JoinCommentLikeEntity();
    }

    private int index;
    private int userIndex;
    private int commentIndex;
    private boolean isChecked;

    public int getIndex() {
        return index;
    }

    public JoinCommentLikeEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public JoinCommentLikeEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public int getCommentIndex() {
        return commentIndex;
    }

    public JoinCommentLikeEntity setCommentIndex(int commentIndex) {
        this.commentIndex = commentIndex;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public JoinCommentLikeEntity setChecked(boolean checked) {
        isChecked = checked;
        return this;
    }

    @Override
    public JoinCommentLikeEntity clone() {
        JoinCommentLikeEntity joinCommentLikeEntity = new JoinCommentLikeEntity();
        joinCommentLikeEntity.index = this.index;
        joinCommentLikeEntity.userIndex = this.userIndex;
        joinCommentLikeEntity.commentIndex = this.commentIndex;
        joinCommentLikeEntity.isChecked = this.isChecked;
        return joinCommentLikeEntity;
    }

    @Override
    public void copyValuesOf(JoinCommentLikeEntity joinCommentLikeEntity) {
        this.index = joinCommentLikeEntity.index;
        this.userIndex = joinCommentLikeEntity.userIndex;
        this.commentIndex = joinCommentLikeEntity.commentIndex;
        this.isChecked = joinCommentLikeEntity.isChecked;
    }
}
