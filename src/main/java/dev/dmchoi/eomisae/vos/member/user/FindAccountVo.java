package dev.dmchoi.eomisae.vos.member.user;

import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.member.user.FindAccountResult;

public class FindAccountVo extends UserEntity {
    private FindAccountResult result;

    public FindAccountResult getResult() {
        return result;
    }

    public FindAccountVo setResult(FindAccountResult result) {
        this.result = result;
        return this;
    }
}
