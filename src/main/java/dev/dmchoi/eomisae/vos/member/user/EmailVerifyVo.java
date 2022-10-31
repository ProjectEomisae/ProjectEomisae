package dev.dmchoi.eomisae.vos.member.user;

import dev.dmchoi.eomisae.entities.member.UserEmailVerificationCodeEntity;
import dev.dmchoi.eomisae.enums.member.user.LoginResult;
import dev.dmchoi.eomisae.enums.member.user.UserEmailVerifyResult;
import dev.dmchoi.eomisae.interfaces.IResult;

public class EmailVerifyVo extends UserEmailVerificationCodeEntity implements IResult<UserEmailVerifyResult> {
    private UserEmailVerifyResult result;

    @Override
    public UserEmailVerifyResult getResult() {
        return result;
    }

    @Override
    public void setResult(UserEmailVerifyResult result) {
        this.result = result;
    }

//    public UserEmailVerifyResult getResult() {
//        return result;
//    }
//
//    public void setResult(UserEmailVerifyResult result) {
//        this.result = result;
//    }
}
