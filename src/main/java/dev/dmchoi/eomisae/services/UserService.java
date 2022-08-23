package dev.dmchoi.eomisae.services;

import dev.dmchoi.eomisae.mappers.IUserMapper;
import dev.dmchoi.eomisae.vos.member.user.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "dev.dmchoi.eomisae.services.UserService")
public class UserService {
    private final IUserMapper userMapper;

    @Autowired
    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public void register(RegisterVo registerVo) {

    }

    public void login()
}
