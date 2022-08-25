package dev.dmchoi.eomisae.entities.member;

import dev.dmchoi.eomisae.interfaces.IEntity;

import java.util.Date;
import java.util.Objects;

public class UserEntity implements IEntity<UserEntity> {
    public static final String ATTRIBUTE_NAME = "user";

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
    private Date createdAt = new Date();
    private int findPasswordIndex;
    private String findPasswordAnswer;
    private Date termsAgreedAt;
    private Date mailReceivedAt;

    private boolean isTermsAgreed;
    private boolean isMailReceived;
    private int messageReceptionIndex;
    private boolean isEmailVerified;
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

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public UserEntity setEmailVerified(boolean emailVerified) {
        this.isEmailVerified = emailVerified;
        return this;
    }

    public String getProfileId() {
        return profileId;
    }

    public UserEntity setProfileId(String profileId) {
        this.profileId = profileId;
        return this;
    }

    public boolean isTermsAgreed() {
        return isTermsAgreed;
    }

    public UserEntity setTermsAgreed(boolean termsAgreed) {
        isTermsAgreed = termsAgreed;
        return this;
    }

    public boolean isMailReceived() {
        return isMailReceived;
    }

    public UserEntity setMailReceived(boolean mailReceived) {
        isMailReceived = mailReceived;
        return this;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof UserEntity)) {
            return false;
        }
        UserEntity userEntity = (UserEntity) obj;
        return this.email.equals(userEntity.email);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public UserEntity clone() {
        UserEntity userEntity = new UserEntity();
        userEntity.index = this.index;
        userEntity.email = this.email;
        userEntity.password = this.password;
        userEntity.userId = this.userId;
        userEntity.nickname = this.nickname;
        userEntity.point = this.point;
        userEntity.level = this.level;
        userEntity.createdAt = (Date) this.createdAt.clone();
        userEntity.findPasswordIndex = this.findPasswordIndex;
        userEntity.findPasswordAnswer = this.findPasswordAnswer;
        userEntity.termsAgreedAt = (Date) this.termsAgreedAt.clone();
        userEntity.mailReceivedAt = (Date) this.mailReceivedAt.clone();
        userEntity.isTermsAgreed = this.isTermsAgreed;
        userEntity.isMailReceived = this.isMailReceived;
        userEntity.messageReceptionIndex = this.messageReceptionIndex;
        userEntity.isEmailVerified = this.isEmailVerified;
        userEntity.profileId = this.profileId;
        return userEntity;
    }

    @Override
    public void copyValuesOf(UserEntity userEntity) {
        this.index = userEntity.index;
        this.email = userEntity.email;
        this.password = userEntity.password;
        this.userId = userEntity.userId;
        this.nickname = userEntity.nickname;
        this.point = userEntity.point;
        this.level = userEntity.level;
        this.createdAt = userEntity.createdAt;
        this.findPasswordIndex = userEntity.findPasswordIndex;
        this.findPasswordAnswer = userEntity.findPasswordAnswer;
        this.termsAgreedAt = userEntity.termsAgreedAt;
        this.mailReceivedAt = userEntity.mailReceivedAt;
        this.isTermsAgreed = userEntity.isTermsAgreed;
        this.isMailReceived = userEntity.isMailReceived;
        this.messageReceptionIndex = userEntity.messageReceptionIndex;
        this.isEmailVerified = userEntity.isEmailVerified;
        this.profileId = userEntity.profileId;
    }
}
