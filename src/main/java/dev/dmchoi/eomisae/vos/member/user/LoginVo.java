package dev.dmchoi.eomisae.vos.member.user;

import dev.dmchoi.eomisae.entities.member.SessionEntity;
import dev.dmchoi.eomisae.interfaces.IResult;
import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.member.user.LoginResult;

public class LoginVo extends UserEntity implements IResult<LoginResult> {

    private LoginResult result;
    private boolean autosign;

    private SessionEntity sessionEntity;

    @Override
    public LoginResult getResult() {
        return result;
    }

    @Override
    public void setResult(LoginResult result) {
        this.result = result;
    }

    public boolean isAutosign() {
        return autosign;
    }

    public LoginVo setAutosign(boolean autosign) {
        this.autosign = autosign;
        return this;
    }

    public SessionEntity getSessionEntity() {
        return sessionEntity;
    }

    public LoginVo setSessionEntity(SessionEntity sessionEntity) {
        this.sessionEntity = sessionEntity;
        return this;
    }
}
