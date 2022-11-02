const emailModifyForm = window.document.getElementById('emailModifyForm');

emailModifyForm.onsubmit = e => {
    e.preventDefault();
    if (emailModifyForm['email'].value === '') {
        hiddenWarning.failureShow('이메일을 입력해 주세요.');
        emailModifyForm['email'].focus();
    }
    if (!new RegExp('^(?=.{10,100}$)([0-9a-z][0-9a-z_]*[0-9a-z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$').test(emailModifyForm['email'].value)) {
        hiddenWarning.failureShow('올바른 이메일을 입력해 주세요.');
        emailModifyForm['email'].focus();
        emailModifyForm['email'].select();
    }
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('email', emailModifyForm['email'].value);
    xhr.open('POST', './memberModifyEmailAddress');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'failure_duplicate_user_email' :
                        hiddenWarning.failureShow('이미 사용중인 이메일입니다. 다른 이메일을 입력해주세요.')
                        break;
                    case 'success' :
                        hiddenWarning.successShow(`[${emailModifyForm['email'].value}] 으로 이메일 변경 인증과 관련된 내용이 전송되었습니다. 해당 메일을 통해 이메일 변경을 완료해주세요`);
                        break;
                    default :
                        alert('알 수 없는 이유로 이메일 변경에 실패하였습니다. \n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요.');
                }
            } else {
                alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요');
            }
        }
    };
    xhr.send(formData);
};
