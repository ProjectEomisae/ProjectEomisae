package dev.dmchoi.eomisae.mappers;

import dev.dmchoi.eomisae.entities.member.UserEmailVerificationCodeEntity;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserMapper {
    int selectUserCountByEmail(String email); // 이메일 존재 여부

    int selectUserCountByNickname(String nickname);

    int insertUser(UserEntity user);
    int insertUserEmailVerificationCode(UserEmailVerificationCodeEntity userEmailVerificationCodeEntity);

//    UserEmailVerificationCodeEntity selectUserEmailVerificationCode(
//            @Param(value = "code") String code,
//            @Param(value = "salt") String salt);

//    UserEmailVerificationCodeEntity selectUserByVerificationCode(
//            @Param(value = "verificationCode") String verificationCode);
}
