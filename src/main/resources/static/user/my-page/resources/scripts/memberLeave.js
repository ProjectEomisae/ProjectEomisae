const userDeletingForm = window.document.getElementById('userDeletingForm');
const hiddenWarning = window.document.getElementById('hiddenWarning');
const warningText = window.document.getElementById('warning');


userDeletingForm.onsubmit = e => {
    e.preventDefault();
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append("password", userDeletingForm['password'].value);
    xhr.open('POST', "/user/my-page/memberLeave" );
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'failure_origin_password' :
                        warningText.innerText = '비밀번호 재확인에 실패하였습니다.';
                        hiddenWarning.classList.add('failure-text');
                        userDeletingForm['password'].focus();
                        userDeletingForm['password'].select();
                        e.preventDefault();
                        break;
                    case 'failure' :
                        alert('알 수 없는 이유로 프로필을 추가하지 못헀습니다.\n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요');
                        window.history.back();
                        e.preventDefault();
                        break;
                    case 'success' :
                        alert('계정을 정말로 삭제할까요?\n\n삭제 후에는 복구가 불가능합니다.\n\n확인을 누르면 계정이 삭제됩니다.')
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