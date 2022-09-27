const passwordModifyForm = window.document.getElementById('passwordModifyForm');
const hiddenWarning = window.document.getElementById('hiddenWarning');
const warningText = window.document.getElementById('warning');

passwordModifyForm.onsubmit = e => {
    e.preventDefault();
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
                    case 'illegal_password' :
                        alert('새 비밀번호의 글자 수를 맞추어 주세요.\n\n비밀번호는 6자리 이상이어야 하며 영문과 숫자를 반드시 포함해야 합니다.');
                        passwordModifyForm['modifyPassword'].focus();
                        passwordModifyForm['modifyPassword'].select();
                        e.preventDefault();
                        break;
                    case 'failure_not_match_password':
                        warningText.innerText = '비밀번호 재확인에 실패하였습니다.';
                        hiddenWarning.classList.add('failure-text');
                        passwordModifyForm['modifyPasswordCheck'].focus();
                        passwordModifyForm['modifyPasswordCheck'].select();
                        e.preventDefault();
                        break;
                    case 'failure_duplicate_old_password':
                        warningText.innerText = '기존 비밀번호와 일치합니다. 다른 비밀번호를 입력해주세요.';
                        hiddenWarning.classList.add('failure-text');
                        passwordModifyForm['modifyPassword'].focus();
                        passwordModifyForm['modifyPassword'].select();
                        e.preventDefault();
                        break;
                    case 'failure_origin_password':
                        warningText.innerText = '기존 비밀번호가 일치하지 않습니다.';
                        hiddenWarning.classList.add('failure-text');
                        passwordModifyForm['password'].focus();
                        passwordModifyForm['password'].select();
                        e.preventDefault();
                        break;
                    case 'failure':
                        alert('알 수 없는 이유로 프로필을 추가하지 못헀습니다.\n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요');
                        window.history.back();
                        e.preventDefault();
                        break;
                    case 'success':
                        hiddenWarning.classList.remove('failure-text');
                        alert('비밀번호 수정을 완료하였습니다.');
                        window.location.href = '/';
                        e.preventDefault();
                        break;
                    default:
                        alert('알 수 없는 이유로 프로필을 추가하지 못헀습니다.\n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요');
                }
            } else {
                alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요');
            }
        }
    };
    xhr.send(formData);
};
