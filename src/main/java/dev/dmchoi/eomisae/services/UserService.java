package dev.dmchoi.eomisae.services;

import dev.dmchoi.eomisae.entities.member.SessionEntity;
import dev.dmchoi.eomisae.entities.member.UserEmailVerificationCodeEntity;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.member.user.LoginResult;
import dev.dmchoi.eomisae.enums.member.user.RegisterResult;
import dev.dmchoi.eomisae.mappers.IUserMapper;
import dev.dmchoi.eomisae.utils.CryptoUtils;
import dev.dmchoi.eomisae.vos.member.user.LoginVo;
import dev.dmchoi.eomisae.utils.CryptoUtils;
import dev.dmchoi.eomisae.vos.member.user.RegisterVo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service(value = "dev.dmchoi.eomisae.services.UserService")
public class UserService {
    public static final int SESSION_LIFE_TIME = 60;
    public static final int CODE_VALID_MINUTES = 30;
    public static final int CODE_HASH_ITERATION_COUNT = 10;
    public static final int SALT_HASH_ITERATION_COUNT = 20;

    public static boolean checkEmail(String input) {
        return input != null && input.matches("^(?=.{8,50})([0-9a-z][0-9a-z_]*[0-9a-z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$");
    }

    public static boolean checkNickname(String input) {
        return input != null && input.matches("^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,20}$");
    }

    public static boolean checkPassword(String input) {
        return input != null && input.matches("^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?]{8,100})$");
    }

    private final IUserMapper userMapper;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Autowired
    public UserService(IUserMapper userMapper, JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine) {
        this.userMapper = userMapper;
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
    }

    public int getUserCountByEmail(UserEntity user) {
        if (!UserService.checkEmail(user.getEmail())) {
            return -1;
        }
        return this.userMapper.selectUserCountByEmail(user.getEmail());
    }

    public int getUserCountByNickname(UserEntity user) {
        if (!UserService.checkNickname(user.getNickname())) {
            return -1;
        }
        return this.userMapper.selectUserCountByNickname(user.getNickname());
    }

    public void register(RegisterVo registerVo) throws MessagingException {
        if (!UserService.checkEmail(registerVo.getEmail()) ||
                !UserService.checkNickname(registerVo.getNickname()) ||
                !UserService.checkPassword(registerVo.getPassword()) ||
                registerVo.getLevel() == 1) {
            registerVo.setResult(RegisterResult.ILLEGAL);
            System.out.println(registerVo.getResult());
            return;
        }
        if (this.userMapper.selectUserCountByEmail(registerVo.getEmail()) > 0) {
            registerVo.setResult(RegisterResult.FAILURE_DUPLICATE_EMAIL);
            System.out.println(registerVo.getResult());
            return;
        }
        if (this.userMapper.selectUserCountByNickname(registerVo.getNickname()) > 0) {
            registerVo.setResult(RegisterResult.FAILURE_DUPLICATE_NICKNAME);
            System.out.println(registerVo.getResult());
            return;
        }

        registerVo.setPassword(CryptoUtils.hash(CryptoUtils.Hash.SHA_512, registerVo.getPassword()));

        if (this.userMapper.insertUser(registerVo) == 0) {
            registerVo.setResult(RegisterResult.FAILURE);
            System.out.println(registerVo.getResult());
        } else {
            System.out.println(registerVo.getIndex());
            Date currentDate = new Date();
            Date expiredAt = DateUtils.addMinutes(currentDate, CODE_VALID_MINUTES);
            String code = String.format("%s%s%s%f%f",
                    registerVo.getEmail(),
                    registerVo.getPassword(),
                    new SimpleDateFormat("yyyyMMddHHmmssSSS").format(currentDate),
                    Math.random(),
                    Math.random());
            String saltA = registerVo.getEmail();
            String saltB = registerVo.getPassword();
            for (int i = 0; i < CODE_HASH_ITERATION_COUNT; i++) {
                code = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, code);
            }
            for (int i = 0; i < SALT_HASH_ITERATION_COUNT; i++) {
                saltA = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, saltA);
                saltB = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, saltB);
            }

            UserEmailVerificationCodeEntity userEmailVerificationCodeEntity = UserEmailVerificationCodeEntity.build()
                    .setCreatedAt(currentDate)
                    .setExpiresAt(expiredAt)
                    .setExpired(false)
                    .setCode(code)
                    .setSalt(String.format("%s%s", saltA, saltB))
                    .setUserIndex(registerVo.getIndex());
            System.out.println(registerVo.getResult());

            //if ( this.userMapper.insertUserEmailVerificationCode(userEmailVerificationCodeEntity) == 0 ) {
            //    registerVo.setResult(RegisterResult.FAILURE);
            // }
            this.userMapper.insertUserEmailVerificationCode(userEmailVerificationCodeEntity);

            Context context = new Context();
            context.setVariable("email", registerVo.getEmail());
            context.setVariable("name", registerVo.getNickname());
            context.setVariable("code", userEmailVerificationCodeEntity.getCode());
            context.setVariable("salt", userEmailVerificationCodeEntity.getSalt());

            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("가입 인증 메일입니다.");
            mimeMessageHelper.setFrom("dmchoi224@gmail.com");
            mimeMessageHelper.setTo(registerVo.getEmail());
            mimeMessageHelper.setText(this.springTemplateEngine.process("user/emailVerificationTemplate", context), true);
            this.javaMailSender.send(mimeMessage);
            registerVo.setResult(RegisterResult.SUCCESS);
        }
    }
        // 세션 만료(로그아웃)
        public void expireSession (SessionEntity sessionEntity){
            sessionEntity.setExpired(true);
            this.userMapper.updateSession(sessionEntity);
        }
        // 세션 연장
        public void extendSession (SessionEntity sessionEntity){
            sessionEntity.setUpdatedAt(new Date());
            sessionEntity.setExpiresAt(DateUtils.addMinutes(sessionEntity.getUpdatedAt(), SESSION_LIFE_TIME));
            this.userMapper.updateSession(sessionEntity);
        }
        // 만료일자가 지금보다 미래이고 만료되지 않은 세션만. SELECT
        public SessionEntity getSession (String key){
            return this.userMapper.selectSessionByKey(key);
        }
        // 세션에 유저 전달하기 위함
        public UserEntity getUser ( int index){
            return this.userMapper.selectUserByIndex(index);
        }

        public void login (LoginVo loginVo, HttpServletRequest request){
            loginVo.setPassword(CryptoUtils.hash(CryptoUtils.Hash.SHA_512, loginVo.getPassword()));
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
            System.out.println(loginVo.getSessionEntity());
            ;
            loginVo.setResult(LoginResult.SUCCESS);
        }
    }








