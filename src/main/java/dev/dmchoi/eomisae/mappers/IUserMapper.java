package dev.dmchoi.eomisae.mappers;

import dev.dmchoi.eomisae.entities.member.SessionEntity;
import dev.dmchoi.eomisae.entities.member.UserEmailVerificationCodeEntity;
import dev.dmchoi.eomisae.entities.member.UserEntity;
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

    int insertUser(UserEntity user);

    SessionEntity selectSessionByKey(
            @Param(value = "key") String key); // 유효한 세션을 가져올 것. SELECT

    int updateSession(SessionEntity sessionEntity);
    // 인터셉터에서 UserEntity가 null이 아님을 확인할 때마다 UPDATE

    int updateSessionExpiredByUserIndex(
            @Param(value = "userIndex") int userIndex); // 인덱스로 사용자의 세션을 만료하기 위함.
    // 로그인을 시도하는, 얘가 가진 인덱스 기준으로 세션테이블에 그 인덱스와 동일한 모든 세션키의 expired를 true(만료)로 만드는게 목적. UPDATE

    int insertUserEmailVerificationCode(UserEmailVerificationCodeEntity userEmailVerificationCodeEntity);

//    UserEmailVerificationCodeEntity selectUserEmailVerificationCode(
//            @Param(value = "code") String code,
//            @Param(value = "salt") String salt);

//    UserEmailVerificationCodeEntity selectUserByVerificationCode(
//            @Param(value = "verificationCode") String verificationCode);
}
