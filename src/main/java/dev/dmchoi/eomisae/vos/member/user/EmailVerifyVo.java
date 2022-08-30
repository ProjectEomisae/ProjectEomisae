package dev.dmchoi.eomisae.vos.member.user;

import dev.dmchoi.eomisae.entities.member.UserEmailVerificationCodeEntity;
import dev.dmchoi.eomisae.enums.member.user.UserEmailVerifyResult;

public class EmailVerifyVo extends UserEmailVerificationCodeEntity {
    private UserEmailVerifyResult result;

    public UserEmailVerifyResult getResult() {
        return result;
    }

    public void setResult(UserEmailVerifyResult result) {
        this.result = result;
    }
}
