package dev.dmchoi.eomisae.services;

import dev.dmchoi.eomisae.mappers.IUserMapper;
import org.springframework.stereotype.Service;

@Service(value = "dev.dmchoi.eomisae.services.UserService")
public class UserService {
    private final IUserMapper userMapper;

    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }


}
