const passwordModifyForm = window.document.getElementById('passwordModifyForm');

passwordModifyForm.onsubmit = e => {
    e.preventDefault();
    if (passwordModifyForm['password'].value === '') {
        hiddenWarning.failureShow('현재 비밀번호를 입력해 주세요.');
        passwordModifyForm['password'].focus();
        return false;
    }
    if (passwordModifyForm['modifyPassword'].value === '') {
        hiddenWarning.failureShow('새 비밀번호를 입력해 주세요.');
        passwordModifyForm['modifyPassword'].focus();
        return false;
    }
    if (!new RegExp('^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:\'\",<.>/?]{6,100})$').test(passwordModifyForm['modifyPassword'].value)) {
        hiddenWarning.failureShow('새 비밀번호의 글자 수를 맞추어 주세요. 비밀번호는 6자리 이상이어야 하며 영문과 숫자를 반드시 포함해야 합니다.');
        passwordModifyForm['modifyPassword'].focus();
        passwordModifyForm['modifyPassword'].select();
        return false;
    }
    if (passwordModifyForm['modifyPasswordCheck'].value === '') {
        hiddenWarning.failureShow('새 비밀번호 확인을 위해 한번 더 입력해 주세요.');
        passwordModifyForm['modifyPasswordCheck'].focus();
         return false;
    }
    if (passwordModifyForm['modifyPassword'].value !== passwordModifyForm['modifyPasswordCheck'].value) {
        hiddenWarning.failureShow('입력하신 두 비밀번호가 일치하지 않습니다. 정확히 입력해 주세요.');
        passwordModifyForm['modifyPasswordCheck'].focus();
        passwordModifyForm['modifyPasswordCheck'].select();
        return false;
    }
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append("password", passwordModifyForm['password'].value);
    formData.append('modifyPassword', passwordModifyForm['modifyPassword'].value);
    formData.append('modifyPasswordCheck', passwordModifyForm['modifyPasswordCheck'].value);
    xhr.open('POST', '/user/my-page/memberModifyPassword');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'failure_duplicate_old_password':
                        hiddenWarning.failureShow('기존 비밀번호와 일치합니다. 다른 비밀번호를 입력해주세요.');
                        passwordModifyForm['modifyPassword'].focus();
                        passwordModifyForm['modifyPassword'].select();
                        break;
                    case 'failure_origin_password':
                        hiddenWarning.failureShow('기존 비밀번호가 일치하지 않습니다.');
                        passwordModifyForm['password'].focus();
                        passwordModifyForm['password'].select();
                        break;
                    case 'success':
                        alert('비밀번호 변경을 완료하였습니다\n\n변경된 비밀번호로 다시 로그인 해주세요.');
                        window.location.href = '/user/logout';
                        break;
                    default:
                        alert('알 수 없는 이유로 비밀번호를 수정하지 못하였습니다. \n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요.');
                }
            } else {
                alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요');
            }
        }
    };
    xhr.send(formData);
};
