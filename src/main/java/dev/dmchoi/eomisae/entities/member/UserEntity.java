package dev.dmchoi.eomisae.entities.member;

import java.util.Date;

public class UserEntity {
    public static UserEntity build() {
        return new UserEntity();
    }

    private int index;
    private String email;
    private String password;
    private String userId;
    private String nickname;
    private int point;
    private int level;
    private Date createdAt;
    private int findPasswordIndex;
    private String findPasswordAnswer;
    private Date termsAgreedAt;
    private Date mailReceivedAt;
    private int messageReceptionIndex;
    private boolean emailVerifiedFlag;
    private String profileId;

    public int getIndex() {
        return index;
    }

    public UserEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserEntity setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public int getPoint() {
        return point;
    }

    public UserEntity setPoint(int point) {
        this.point = point;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public UserEntity setLevel(int level) {
        this.level = level;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public UserEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public int getFindPasswordIndex() {
        return findPasswordIndex;
    }

    public UserEntity setFindPasswordIndex(int findPasswordIndex) {
        this.findPasswordIndex = findPasswordIndex;
        return this;
    }

    public String getFindPasswordAnswer() {
        return findPasswordAnswer;
    }

    public UserEntity setFindPasswordAnswer(String findPasswordAnswer) {
        this.findPasswordAnswer = findPasswordAnswer;
        return this;
    }

    public Date getTermsAgreedAt() {
        return termsAgreedAt;
    }

    public UserEntity setTermsAgreedAt(Date termsAgreedAt) {
        this.termsAgreedAt = termsAgreedAt;
        return this;
    }

    public Date getMailReceivedAt() {
        return mailReceivedAt;
    }

    public UserEntity setMailReceivedAt(Date mailReceivedAt) {
        this.mailReceivedAt = mailReceivedAt;
        return this;
    }

    public int getMessageReceptionIndex() {
        return messageReceptionIndex;
    }

    public UserEntity setMessageReceptionIndex(int messageReceptionIndex) {
        this.messageReceptionIndex = messageReceptionIndex;
        return this;
    }

    public boolean isEmailVerifiedFlag() {
        return emailVerifiedFlag;
    }

    public UserEntity setEmailVerifiedFlag(boolean emailVerifiedFlag) {
        this.emailVerifiedFlag = emailVerifiedFlag;
        return this;
    }

    public String getProfileId() {
        return profileId;
    }

    public UserEntity setProfileId(String profileId) {
        this.profileId = profileId;
        return this;
    }
}
