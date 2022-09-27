package dev.dmchoi.eomisae.vos.member.user;

import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.enums.member.user.ModifyResult;
import dev.dmchoi.eomisae.interfaces.IResult;

public class UserModifyVo extends UserEntity implements IResult<ModifyResult> {
    private ModifyResult result;

    @Override
    public ModifyResult getResult() {
        return result;
    }

    @Override
    public void setResult(ModifyResult result) {
        this.result = result;
    }
}
