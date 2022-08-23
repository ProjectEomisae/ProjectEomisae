package dev.dmchoi.eomisae.mappers;

import dev.dmchoi.eomisae.entities.member.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper {
    int selectUserCountByEmail(String email); // 이메일 존재 여부

    int selectUserCountByNickname(String nickname);

    int insertUser(UserEntity user);
}
