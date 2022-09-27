package dev.dmchoi.eomisae.vos.member.user;

import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.interfaces.IResult;
import dev.dmchoi.eomisae.enums.member.user.DeleteUserResult;

public class DeleteUserVo extends UserEntity implements IResult<DeleteUserResult> {
    private DeleteUserResult result;

    public DeleteUserResult getResult() {
        return result;
    }

    public void setResult(DeleteUserResult result) {
        this.result = result;
    }
}
