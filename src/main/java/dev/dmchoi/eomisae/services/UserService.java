package dev.dmchoi.eomisae.services;

import dev.dmchoi.eomisae.entities.member.SessionEntity;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.member.user.LoginResult;
import dev.dmchoi.eomisae.mappers.IUserMapper;
import dev.dmchoi.eomisae.utils.CryptoUtils;
import dev.dmchoi.eomisae.vos.member.user.LoginVo;
import dev.dmchoi.eomisae.vos.member.user.RegisterVo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service(value = "dev.dmchoi.eomisae.services.UserService")
public class UserService {
    public static final int SESSION_LIFE_TIME = 60;
    public static final int CODE_VALID_MINUTES = 30;
    public static final int CODE_HASH_ITERATION_COUNT = 10;
    public static final int SALT_HASH_ITERATION_COUNT = 20;
    private final IUserMapper userMapper;

    @Autowired
    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 세션 만료(로그아웃)
    public void expireSession(SessionEntity sessionEntity) {
        sessionEntity.setExpired(true);
        this.userMapper.updateSession(sessionEntity);
    }
    // 세션 연장
    public void extendSession(SessionEntity sessionEntity) {
        sessionEntity.setUpdatedAt(new Date());
        sessionEntity.setExpiresAt(DateUtils.addMinutes(sessionEntity.getUpdatedAt(), SESSION_LIFE_TIME));
        this.userMapper.updateSession(sessionEntity);
    }
    // 만료일자가 지금보다 미래이고 만료되지 않은 세션만. SELECT
    public SessionEntity getSession(String key) {
        return this.userMapper.selectSessionByKey(key);
    }
    // 세션에 유저 전달하기 위함
    public UserEntity getUser(int index) {
        return this.userMapper.selectUserByIndex(index);
    }
    public void register(RegisterVo registerVo) {

    }

    public void login(LoginVo loginVo, HttpServletRequest request) {
        loginVo.setPassword(CryptoUtils.hash(CryptoUtils.Hash.SHA_512,loginVo.getPassword()));
        UserEntity userEntity = this.userMapper.selectUser(loginVo.getEmail(), loginVo.getPassword());

        if (userEntity == null) {
            loginVo.setResult(LoginResult.FAILURE);
            System.out.println(loginVo.getResult());
            return;
        }
        if (!userEntity.isEmailVerified()) {
            loginVo.setResult(LoginResult.EMAIL_NOT_VERIFIED);
            System.out.println(loginVo.getResult());
            return;
        }
        loginVo.copyValuesOf(userEntity);

        this.userMapper.updateSessionExpiredByUserIndex(loginVo.getIndex());

        String sessionKey = String.format("%s%s%s%f%f",
                new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()),
                loginVo.getEmail(),
                loginVo.getPassword(),
                Math.random(),
                Math.random());

        String userAgent = request.getHeader("User-Agent");
        Date currentDate = new Date();
        // 사용자를 별도로 인식하기 위한 키
        sessionKey = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, sessionKey); // 해싱까지 완료
        SessionEntity sessionEntity = SessionEntity.build()
                .setCreatedAt(currentDate)
                .setUpdatedAt(currentDate)
                .setExpiresAt(DateUtils.addMinutes(currentDate, SESSION_LIFE_TIME))
                .setExpired(false)
                .setUserIndex(loginVo.getIndex())
                .setKey(sessionKey)
                .setUa(userAgent);
        System.out.println("세션키의 만료시간 : " + sessionEntity.getExpiresAt());
        this.userMapper.insertSession(sessionEntity);
        loginVo.setSessionEntity(sessionEntity);
        System.out.println(loginVo.getSessionEntity());;
        loginVo.setResult(LoginResult.SUCCESS);
    }
}
