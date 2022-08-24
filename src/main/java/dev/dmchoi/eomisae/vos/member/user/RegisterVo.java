package dev.dmchoi.eomisae.vos.member.user;

import dev.dmchoi.eomisae.interfaces.IResult;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.member.user.RegisterResult;

public class RegisterVo extends UserEntity implements IResult<RegisterResult> {
    private RegisterResult result;
    @Override
    public RegisterResult getResult() {
        return result;
    }

    @Override
    public void setResult(RegisterResult result) {
        this.result = result;
    }
}
