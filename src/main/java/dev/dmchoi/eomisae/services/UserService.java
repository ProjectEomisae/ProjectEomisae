package dev.dmchoi.eomisae.services;

import com.sun.xml.internal.bind.v2.TODO;
import dev.dmchoi.eomisae.entities.member.*;
import dev.dmchoi.eomisae.enums.member.user.*;
import dev.dmchoi.eomisae.entities.member.SessionEntity;
import dev.dmchoi.eomisae.entities.member.UserEmailVerificationCodeEntity;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.member.user.LoginResult;
import dev.dmchoi.eomisae.entities.member.*;
import dev.dmchoi.eomisae.enums.member.user.RegisterResult;
import dev.dmchoi.eomisae.enums.member.user.UserEmailVerifyResult;
import dev.dmchoi.eomisae.mappers.IUserMapper;
import dev.dmchoi.eomisae.mappers.IUserMapper;
import dev.dmchoi.eomisae.utils.CryptoUtils;
import dev.dmchoi.eomisae.vos.member.user.*;
import dev.dmchoi.eomisae.vos.member.user.LoginVo;
import dev.dmchoi.eomisae.utils.CryptoUtils;
import dev.dmchoi.eomisae.utils.CryptoUtils;
import dev.dmchoi.eomisae.vos.member.user.EmailVerifyVo;
import dev.dmchoi.eomisae.vos.member.user.LoginVo;
import dev.dmchoi.eomisae.vos.member.user.EmailVerifyVo;
import dev.dmchoi.eomisae.vos.member.user.LoginVo;
import dev.dmchoi.eomisae.vos.member.user.RegisterVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
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
    public static final int EMAIL_VALID_MINUTES = 5;
    public static final int CODE_VALID_MINUTES = 30;
    public static final int CODE_HASH_ITERATION_COUNT = 10;
    public static final int SALT_HASH_ITERATION_COUNT = 20;

    public static boolean checkEmail(String input) {
        return input != null && input.matches("^(?=.{8,50})([0-9a-z][0-9a-z_]*[0-9a-z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$");
    }

    public static boolean checkNickname(String input) {
        return input != null && input.matches("^(.*[가-힣]{2,5})|(.*[a-z]{4,10})|(.*[0-9]{4,10})$");
    }

    public static boolean checkUserId(String input) {
        return input != null && input.matches("^[A-Za-z]{1}[A-Za-z0-9_]{1,}|$");
    }

    public static boolean checkPassword(String input) {
        return input != null && input.matches("^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?]{6,100})$");
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

    public int getUserCountById(UserEntity user) {
        if (!UserService.checkUserId(user.getUserId())) {
            return -1;
        }
        return this.userMapper.selectUserCountById(user.getUserId());
    }

    @Transactional
    public void register(RegisterVo registerVo) throws MessagingException {
        if (!UserService.checkEmail(registerVo.getEmail()) ||
                !UserService.checkNickname(registerVo.getNickname()) ||
                !UserService.checkPassword(registerVo.getPassword()) ||
                registerVo.getLevel() == 1) {
            registerVo.setResult(RegisterResult.ILLEGAL);
            return;
        }
        if (!registerVo.getUserId().equals("")) {
            if (!UserService.checkUserId(registerVo.getUserId())) {
                registerVo.setResult(RegisterResult.ILLEGAL);
                return;
            }
        }
        if (this.userMapper.selectUserCountByEmail(registerVo.getEmail()) > 0) {
            registerVo.setResult(RegisterResult.FAILURE_DUPLICATE_EMAIL);
            return;
        }
        if (this.userMapper.selectUserCountByNickname(registerVo.getNickname()) > 0) {
            registerVo.setResult(RegisterResult.FAILURE_DUPLICATE_NICKNAME);
            return;
        }

        registerVo.setPassword(CryptoUtils.hash(CryptoUtils.Hash.SHA_512, registerVo.getPassword()));

        if (registerVo.isMailReceived()) {
            registerVo.setMailReceivedAt(new Date());
        }

        if (registerVo.isTermsAgreed()) {
            registerVo.setTermsAgreedAt(new Date());
        }

        if (this.userMapper.insertUser(registerVo) == 0) {
            registerVo.setResult(RegisterResult.FAILURE);
        } else {
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

            this.userMapper.insertUserEmailVerificationCode(userEmailVerificationCodeEntity);

            Context context = new Context();
            context.setVariable("email", registerVo.getEmail());
            context.setVariable("name", registerVo.getNickname());
            context.setVariable("userEmailVerificationCodeEntity", userEmailVerificationCodeEntity);

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

    public void emailVerify(EmailVerifyVo emailVerifyVo) {
        if (emailVerifyVo.getCode() == null || emailVerifyVo.getSalt() == null || !emailVerifyVo.getCode().matches("^([0-9a-z]{128})$") || !emailVerifyVo.getSalt().matches("^([0-9a-z]{256})$")) {
            emailVerifyVo.setResult(UserEmailVerifyResult.ILLEGAL);
            return;
        }
        UserEmailVerificationCodeEntity userEmailVerificationCodeEntity = this.userMapper.selectUserEmailVerificationCode(
                emailVerifyVo.getCode(),
                emailVerifyVo.getSalt()
        );
        if (userEmailVerificationCodeEntity == null || userEmailVerificationCodeEntity.getIndex() == 0) {
            emailVerifyVo.setResult(UserEmailVerifyResult.FAILURE);
            return;
        }
        if (userEmailVerificationCodeEntity.isExpired() || userEmailVerificationCodeEntity.getExpiresAt().compareTo(new Date()) < 0) {
            emailVerifyVo.setResult(UserEmailVerifyResult.EXPIRED);
            return;
        }
        UserEntity user = this.userMapper.selectUserByIndex(userEmailVerificationCodeEntity.getUserIndex());
        String saltA = user.getEmail();
        String saltB = user.getPassword();
        for (int i = 0; i < SALT_HASH_ITERATION_COUNT; i++) {
            saltA = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, saltA);
            saltB = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, saltB);
        }
        if (!userEmailVerificationCodeEntity.getSalt().equals(String.format("%s%s", saltA, saltB))) {
            emailVerifyVo.setResult(UserEmailVerifyResult.FAILURE);
            return;
        }
        user.setEmailVerified(true);
        userEmailVerificationCodeEntity.setExpired(true);
        this.userMapper.updateUser(user);
        this.userMapper.updateUserEmailVerificationCode(userEmailVerificationCodeEntity);
        emailVerifyVo.setResult(UserEmailVerifyResult.SUCCESS);
    }

    public void login(LoginVo loginVo, HttpServletRequest request) {
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
        loginVo.setResult(LoginResult.SUCCESS);
    }

    public void putProfileImage(ProfileImageEntity profileImageEntity) {
        this.userMapper.insertProfileImage(profileImageEntity);
    }
    public ProfileImageEntity getProfileImage(String id) {
        return this.userMapper.selectProfileImage(id);
    }

    public void modifyEmail(UserEntity user, UserModifyVo userModifyVo) throws MessagingException {
        UserEntity originUser = this.userMapper.selectUserEmailByIndex(user.getIndex());
        if (user.getIndex() == 0) {
            userModifyVo.setResult(ModifyResult.FAILURE);
            return;
        }
        if (this.userMapper.selectUserCountByEmail(user.getEmail()) > 0 || originUser.getEmail().equals(user.getEmail())) {
            userModifyVo.setResult(ModifyResult.FAILURE_DUPLICATE_USER_EMAIL);
        } else {
            Date currentDate = new Date();
            Date expiredAt = DateUtils.addMinutes(currentDate, EMAIL_VALID_MINUTES);
            String code = String.format("%s%s%s%f%f",
                    user.getEmail(),
                    user.getPassword(),
                    new SimpleDateFormat("yyyyMMddHHmmssSSS").format(currentDate),
                    Math.random(),
                    Math.random());
            String saltA = user.getEmail();
            String saltB = user.getPassword();
            for (int i = 0; i < CODE_HASH_ITERATION_COUNT; i++) {
                code = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, code);
            }
            for (int i = 0; i < SALT_HASH_ITERATION_COUNT; i++) {
                saltA = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, saltA);
                saltB = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, saltB);
            }
            ModifyUserEmailVerificationCodeEntity modifyUserEmailVerificationCodeEntity = new ModifyUserEmailVerificationCodeEntity();
            modifyUserEmailVerificationCodeEntity.setCreatedAt(currentDate)
                    .setExpiresAt(expiredAt)
                    .setExpired(false)
                    .setCode(code)
                    .setSalt(String.format("%s%s", saltA, saltB))
                    .setUserIndex(user.getIndex())
                    .setModifyEmail(user.getEmail());

            this.userMapper.insertUserModifyEmailVerificationCode(modifyUserEmailVerificationCodeEntity);

            Context context = new Context();
            context.setVariable("originUserEmail", originUser.getEmail());
            context.setVariable("email", user.getEmail());
            context.setVariable("name", user.getNickname());
            context.setVariable("modifyUserEmailVerificationCodeEntity", modifyUserEmailVerificationCodeEntity);

            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessage.setSubject("이메일 변경 인증 메일입니다.");
            mimeMessage.setFrom("dmchoi224@gmail.com");
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setText(this.springTemplateEngine.process("user/modifyEmailVerificationTemplate", context), true);
            this.javaMailSender.send(mimeMessage);

            userModifyVo.setResult(ModifyResult.SUCCESS);
            System.out.println(" SERVICE → email modify result : " + userModifyVo.getResult());
            System.out.println(" 변경할 이메일                    : " + user.getEmail());
            System.out.println(" ====== 이메일 변경 인증 메일 전송 완료 ====== ");

        }
    }

    public void modifyEmailVerify(EmailVerifyVo emailVerifyVo) {
        if (emailVerifyVo.getCode() == null || emailVerifyVo.getSalt() == null || !emailVerifyVo.getCode().matches("^([0-9a-z]{128})$") || !emailVerifyVo.getSalt().matches("^([0-9a-z]{256})$")) {
            emailVerifyVo.setResult(UserEmailVerifyResult.ILLEGAL);
            return;
        }
        ModifyUserEmailVerificationCodeEntity modifyUserEmailVerificationCodeEntity = this.userMapper.selectUserModifyEmailVerificationCode(
                emailVerifyVo.getCode(),
                emailVerifyVo.getSalt()
        );
        if (modifyUserEmailVerificationCodeEntity == null || modifyUserEmailVerificationCodeEntity.getIndex() == 0) {
            emailVerifyVo.setResult(UserEmailVerifyResult.FAILURE);
            return;
        }
        UserEntity user = this.userMapper.selectUserByIndex(modifyUserEmailVerificationCodeEntity.getUserIndex());

        String saltA = modifyUserEmailVerificationCodeEntity.getModifyEmail();
        String saltB = user.getPassword();
        for (int i = 0; i < SALT_HASH_ITERATION_COUNT; i++) {
            saltA = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, saltA);
            saltB = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, saltB);
        }
        if (!modifyUserEmailVerificationCodeEntity.getSalt().equals(String.format("%s%s", saltA, saltB))) {
            emailVerifyVo.setResult(UserEmailVerifyResult.FAILURE);
            return;
        }
        if (modifyUserEmailVerificationCodeEntity.isExpired() || modifyUserEmailVerificationCodeEntity.getExpiresAt().getTime() - (new Date()).getTime() < 0L) {
            emailVerifyVo.setResult(UserEmailVerifyResult.EXPIRED);
            return;
        }
        user.setEmail(modifyUserEmailVerificationCodeEntity.getModifyEmail());
        modifyUserEmailVerificationCodeEntity.setExpired(true);
        this.userMapper.updateModifyEmail(user);
        this.userMapper.updateUserModifyEmailVerificationCode(modifyUserEmailVerificationCodeEntity);
        emailVerifyVo.setResult(UserEmailVerifyResult.SUCCESS);
    }

    @Transactional
    public void modifyProfile(UserEntity user, UserModifyVo userModifyVo) {
        UserEntity tempUser = this.userMapper.selectUserByIndex(user.getIndex());
        if (tempUser == null || tempUser.getIndex() == 0) {
            userModifyVo.setResult(ModifyResult.FAILURE);
            return;
        }
        user.setIndex(tempUser.getIndex())
                .setEmail(tempUser.getEmail())
                .setPassword(tempUser.getPassword())
                .setPoint(tempUser.getPoint())
                .setLevel(tempUser.getLevel())
                .setCreatedAt(tempUser.getCreatedAt())
                .setFindPasswordIndex(tempUser.getFindPasswordIndex())
                .setFindPasswordAnswer(tempUser.getFindPasswordAnswer())
                .setTermsAgreedAt(tempUser.getTermsAgreedAt())
                .setTermsAgreed(tempUser.isTermsAgreed())
                .setEmailVerified(tempUser.isEmailVerified())
                .setUserId(userModifyVo.getUserId())
                .setNickname(userModifyVo.getNickname())
                .setMessageReceptionIndex(userModifyVo.getMessageReceptionIndex());

        if (userModifyVo.isMailReceived()) {
            Date currentDate = new Date();
            user.setMailReceivedAt(currentDate);
            user.setMailReceived(userModifyVo.isMailReceived());
        } else {
            user.setMailReceivedAt(null);
            user.setMailReceived(userModifyVo.isMailReceived());
        }
        if (!UserService.checkNickname(user.getNickname())) {
            userModifyVo.setResult(ModifyResult.ILLEGAL_NICKNAME);
            return;
        }
        if (!user.getUserId().equals("")) {
            if (!UserService.checkUserId(user.getUserId())) {
                userModifyVo.setResult(ModifyResult.ILLEGAL_USER_ID);
                return;
            }
        }
        if (this.userMapper.selectUserCountById(user.getUserId()) > 0 && !(tempUser.getUserId().equals(user.getUserId()))) {
            userModifyVo.setResult(ModifyResult.FAILURE_DUPLICATE_USER_ID);
            return;
        }
        if (this.userMapper.selectUserCountByNickname(user.getNickname()) > 0 && !(tempUser.getNickname().equals(user.getNickname()))) {
            userModifyVo.setResult(ModifyResult.FAILURE_DUPLICATE_NICKNAME);
            return;
        }
        if (this.userMapper.updateProfile(user) == 0) {
            userModifyVo.setResult(ModifyResult.FAILURE);
        }
        this.userMapper.updateProfile(user);
        userModifyVo.setResult(ModifyResult.SUCCESS);
    }

    public void modifyPassword(UserEntity user, UserModifyVo userModifyVo, String modifyPassword, String modifyPasswordCheck) {
        UserEntity originPassword = this.userMapper.selectUserPasswordByIndex(user.getIndex());
        if (originPassword == null || originPassword.getIndex() == 0) {
            userModifyVo.setResult(ModifyResult.FAILURE);
            return;
        }
        if (!UserService.checkPassword(modifyPassword)) {
            userModifyVo.setResult(ModifyResult.ILLEGAL_PASSWORD);
            return;
        }
        if (!modifyPassword.equals(modifyPasswordCheck)) {
            userModifyVo.setResult(ModifyResult.FAILURE_NOT_MATCH_PASSWORD);
            return;
        }

        String hashReceivedOriginPassword = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, userModifyVo.getPassword());
        String hashModifyPassword = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, modifyPassword);

        if (!originPassword.getPassword().equals(hashReceivedOriginPassword)) {
            userModifyVo.setResult(ModifyResult.FAILURE_ORIGIN_PASSWORD);
            return;
        }

        if (originPassword.getPassword().equals(hashModifyPassword)) {
            userModifyVo.setResult(ModifyResult.FAILURE_DUPLICATE_OLD_PASSWORD);
            return;
        }

        userModifyVo.setPassword(hashModifyPassword);
        user.setIndex(originPassword.getIndex());
        user.setPassword(userModifyVo.getPassword());

        if (this.userMapper.updateModifyUserPassword(user) == 0) {
            userModifyVo.setResult(ModifyResult.FAILURE);
        }
        this.userMapper.updateModifyUserPassword(user);
        userModifyVo.setResult(ModifyResult.SUCCESS);
    }

    @Transactional
    public void deleteProfileImage(UserEntity user, ProfileImageEntity profileImageEntity) {
        this.userMapper.deleteProfileImage(profileImageEntity);
        user = this.userMapper.selectUserByIndex(user.getIndex());
        user.setProfileId(null);
        this.userMapper.updateProfile(user);
    }

    public void deleteUser(UserEntity user, DeleteUserVo deleteUserVo) {
        UserEntity originPassword = this.userMapper.selectUserPasswordByIndex(user.getIndex());
        String hashCheckPassword = CryptoUtils.hash(CryptoUtils.Hash.SHA_512, deleteUserVo.getPassword());

        if (!originPassword.getPassword().equals(hashCheckPassword)) {
            deleteUserVo.setResult(DeleteUserResult.FAILURE_ORIGIN_PASSWORD);
            return;
        }
        if (this.userMapper.deleteUserByEmail(user) == 0) {
            deleteUserVo.setResult(DeleteUserResult.FAILURE);
            return;
        }
        this.userMapper.deleteUserByEmail(user);
        deleteUserVo.setResult(DeleteUserResult.SUCCESS);
    }

    public void userFindAccountByEmail(String email, FindAccountVo findAccountVo) throws MessagingException {
        UserEntity originUser = this.userMapper.selectUserByEmail(email);
        if (email == null || originUser == null || originUser.getEmail() == null) {
            findAccountVo.setResult(FindAccountResult.FAILURE);
            return;
        }
        String temporaryPassword = RandomStringUtils.randomAscii(8);
        originUser.setPassword(CryptoUtils.hash(CryptoUtils.Hash.SHA_512, temporaryPassword));
        this.userMapper.updateModifyUserPassword(originUser);

        Context context = new Context();
        context.setVariable("email", originUser.getEmail());
        context.setVariable("name", originUser.getNickname());
        context.setVariable("password", temporaryPassword); // 임시 비밀번호 생성

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("아이디/비밀번호 정보입니다.");
        mimeMessageHelper.setFrom("dmchoi224@gmail.com");
        mimeMessageHelper.setTo(originUser.getEmail());
        mimeMessageHelper.setText(this.springTemplateEngine.process("user/findAccountVerificationTemplate", context), true);
        this.javaMailSender.send(mimeMessage);

        findAccountVo.setResult(FindAccountResult.SUCCESS);
    }

    public void userFindAccountByQna(String email, int questionIndex, String answer, FindAccountVo findAccountVo) throws MessagingException {
        UserEntity originUser = this.userMapper.selectUserByEmail(email);
        if (email == null || questionIndex == 0 || originUser == null || originUser.getEmail() == null) {
            findAccountVo.setResult(FindAccountResult.FAILURE);
            return;
        }
        if (!email.equals(originUser.getEmail())) {
            findAccountVo.setResult(FindAccountResult.FAILURE);
            return;
        }
        if (questionIndex != originUser.getFindPasswordIndex()) {
            findAccountVo.setResult(FindAccountResult.NOT_MATCH_QUESTION_INDEX);
            return;
        } else { // 인덱스가 일치할 떄
            if (!answer.equals(originUser.getFindPasswordAnswer())) {
                findAccountVo.setResult(FindAccountResult.NOT_MATCH_ANSWER);
                return;
            }
        }
        String temporaryPassword = RandomStringUtils.randomAscii(8);
        originUser.setPassword(CryptoUtils.hash(CryptoUtils.Hash.SHA_512, temporaryPassword));
        this.userMapper.updateModifyUserPassword(originUser);

        Context context = new Context();
        context.setVariable("email", originUser.getEmail());
        context.setVariable("name", originUser.getNickname());
        context.setVariable("password", temporaryPassword); // 임시 비밀번호 생성

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("임시 비밀번호가 발급 되었습니다.");
        mimeMessageHelper.setFrom("dmchoi224@gmail.com");
        mimeMessageHelper.setTo(originUser.getEmail());
        mimeMessageHelper.setText(this.springTemplateEngine.process("user/findAccountVerificationTemplate", context), true);
        this.javaMailSender.send(mimeMessage);
        findAccountVo.setResult(FindAccountResult.SUCCESS);
    }

    public void resendVerificationEmail(String email, RegisterVo registerVo) throws MessagingException {
        UserEntity pendingUser = this.userMapper.selectUserByEmail(email);
        if (pendingUser == null || email == null) {
            registerVo.setResult(RegisterResult.FAILURE);
            return;
        }
        if (pendingUser.getEmail() != null && !email.equals(pendingUser.getEmail())) {
            registerVo.setResult(RegisterResult.FAILURE);
            return;
        }
        UserEmailVerificationCodeEntity resendCodeSalt = this.userMapper.selectUserEmailVerificationCodeByUserIndex(pendingUser.getIndex());
        if (resendCodeSalt == null || resendCodeSalt.getIndex() == 0) {
            registerVo.setResult(RegisterResult.FAILURE);
            return;
        }
        Date currentDate = new Date();
        Date expiredAt = DateUtils.addMinutes(currentDate, CODE_VALID_MINUTES);
        resendCodeSalt.setCreatedAt(currentDate)
                .setExpiresAt(expiredAt)
                .setExpired(false);
        Context context = new Context();
        context.setVariable("email", pendingUser.getEmail());
        context.setVariable("name", pendingUser.getNickname());
        context.setVariable("resendCodeSalt", resendCodeSalt);

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("가입 인증 재발송 메일입니다.");
        mimeMessageHelper.setFrom("dmchoi224@gmail.com");
        mimeMessageHelper.setTo(pendingUser.getEmail());
        mimeMessageHelper.setText(this.springTemplateEngine.process("user/resendEmailVerificationTemplate", context), true);
        this.javaMailSender.send(mimeMessage);

        registerVo.setResult(RegisterResult.SUCCESS);
    }

}











