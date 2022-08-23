package dev.dmchoi.eomisae.mappers;

import dev.dmchoi.eomisae.entities.member.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper {
    int insertUser(UserEntity user);
}
