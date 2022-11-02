const userDeletingForm = window.document.getElementById('userDeletingForm');

const hiddenWarning = {
    getElement: () => window.document.getElementById('hiddenWarning'),
    hide: () => {
        if (hiddenWarning.getElement().classList.contains('failure-text')) {
            hiddenWarning.getElement().classList.remove('failure-text');
        }
    },
    failureShow: (text) => {
        hiddenWarning.getElement().innerText = text;
        hiddenWarning.getElement().classList.add('failure-text');
    }
};

userDeletingForm.onsubmit = e => {
    e.preventDefault();
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append("password", userDeletingForm['password'].value);
    xhr.open('POST', '/user/my-page/memberLeave');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'failure_origin_password' :
                        hiddenWarning.failureShow('비밀번호가 일치하지 않습니다.');
                        userDeletingForm['password'].focus();
                        userDeletingForm['password'].select();
                        break;
                    case 'success' :
                        alert('계정을 정말로 삭제할까요?\n\n삭제 후에는 복구가 불가능합니다.\n\n확인을 누르면 계정 삭제가 완료됩니다.');
                        window.location.href = '/';
                        break;
                    default:
                        alert('알 수 없는 이유로 계정을 삭제하지 못하였습니다.\n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요');
                }
            } else {
                alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요');
            }
        }
    };
    xhr.send(formData);
};