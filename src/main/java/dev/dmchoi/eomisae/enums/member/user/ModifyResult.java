package dev.dmchoi.eomisae.enums.member.user;

public enum ModifyResult {
    FAILURE_DUPLICATE_USER_EMAIL,
    FAILURE_DUPLICATE_USER_ID,
    FAILURE_DUPLICATE_NICKNAME,
    FAILURE_ORIGIN_PASSWORD, // 기존 비밀번호 확인 실패
    FAILURE_DUPLICATE_OLD_PASSWORD, // 기존 비밀번호와 일치함
    FAILURE,
    ILLEGAL_USER_ID,
    ILLEGAL_NICKNAME,
    SUCCESS
}
