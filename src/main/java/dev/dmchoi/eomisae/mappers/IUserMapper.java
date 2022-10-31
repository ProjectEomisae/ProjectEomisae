package dev.dmchoi.eomisae.mappers;

import dev.dmchoi.eomisae.entities.member.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserMapper {
    int insertSession(SessionEntity sessionEntity); // 세션 INSERT

    UserEntity selectUserByIndex(
            @Param(value = "index") int index); // index로 유저 SELECT
    UserEntity selectUser(
            @Param(value = "email") String email,
            @Param(value = "password") String password);

    int selectUserCountByEmail(String email); // 이메일 존재 여부

    int selectUserCountByNickname(String nickname);

    int selectUserCountById(String userId);

    UserEntity selectUserEmailByIndex(@Param(value = "index") int index);
    UserEntity selectUserIndexByEmail(@Param(value = "email") String email);
    UserEntity selectUserPasswordByIndex(@Param(value="index") int index);
    UserEmailVerificationCodeEntity selectUserEmailVerificationCode(
            @Param(value = "code") String code,
            @Param(value = "salt") String salt);

    ModifyUserEmailVerificationCodeEntity selectUserModifyEmailVerificationCode(
            @Param(value = "code") String code,
            @Param(value = "salt") String salt);

    ProfileImageEntity selectProfileImage(@Param(value = "id") String id);
    SessionEntity selectSessionByKey(
            @Param(value = "key") String key); // 유효한 세션을 가져올 것. SELECT
    int deleteProfileImage(ProfileImageEntity profileImageEntity);

    int insertUser(UserEntity user);
    int insertUserEmailVerificationCode(UserEmailVerificationCodeEntity userEmailVerificationCodeEntity);

    int insertProfileImage(ProfileImageEntity profileImageEntity);

    int updateUser(UserEntity user);

    int updateUserEmailVerificationCode(UserEmailVerificationCodeEntity userEmailVerificationCodeEntity);

    int updateUserModifyEmailVerificationCode(ModifyUserEmailVerificationCodeEntity modifyUserEmailVerificationCodeEntity);

    int insertUserModifyEmailVerificationCode(ModifyUserEmailVerificationCodeEntity modifyUserEmailVerificationCodeEntity);

    int updateModifyUserPassword(UserEntity user);

    int updateSession(SessionEntity sessionEntity);
    // 인터셉터에서 UserEntity가 null이 아님을 확인할 때마다 UPDATE

    int updateSessionExpiredByUserIndex(
            @Param(value = "userIndex") int userIndex); // 인덱스로 사용자의 세션을 만료하기 위함.
    // 로그인을 시도하는, 얘가 가진 인덱스 기준으로 세션테이블에 그 인덱스와 동일한 모든 세션키의 expired를 true(만료)로 만드는게 목적. UPDATE

    int updateProfile(UserEntity user);

    int updateModifyEmail(UserEntity user);

    int deleteUserByEmail(UserEntity user);

}
