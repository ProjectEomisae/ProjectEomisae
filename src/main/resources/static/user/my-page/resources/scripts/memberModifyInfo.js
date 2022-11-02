const modifyProfileForm = window.document.getElementById('modifyProfileForm');

modifyProfileForm.onsubmit = e => {
    e.preventDefault();
    if (modifyProfileForm['nickname'].value === '') {
        hiddenWarning.failureShow('닉네임 값은 필수입니다.');
        modifyProfileForm['nickname'].focus();
        return false;
    }
    if (!new RegExp('^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$').test(modifyProfileForm['userId'].value)) {
        hiddenWarning.failureShow('아이디의 값은 2자 이상 20자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다.');
        modifyProfileForm['userId'].focus();
        modifyProfileForm['userId'].select();
        return false;
    }
    if (!new RegExp('^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$').test(modifyProfileForm['nickname'].value)) {
        hiddenWarning.failureShow('닉네임의 값은 2자 이상 10자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다.');
        modifyProfileForm['nickname'].focus();
        modifyProfileForm['nickname'].select();
        return false;
    }
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('userId', modifyProfileForm['userId'].value);
    formData.append('nickname', modifyProfileForm['nickname'].value);
    if ((modifyProfileForm['profileImage'].files?.length ?? 0) > 0) {
        formData.append('profileImage', modifyProfileForm['profileImage'].files[0]);
    }
    formData.append('mailReceived', modifyProfileForm['mailReceived'].value);
    formData.append('messageReceptionIndex', modifyProfileForm['messageReceptionIndex'].value);
    xhr.open('POST', '/user/my-page/memberModifyInfo');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'illegal_nickname' :
                        hiddenWarning.failureShow('닉네임의 값은 2자 이상 10자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다.');
                        modifyProfileForm['nickname'].focus();
                        modifyProfileForm['nickname'].select();
                        break;
                    case 'illegal_user_id' :
                        hiddenWarning.failureShow('아이디의 값은 2자 이상 20자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다.');
                        modifyProfileForm['userId'].focus();
                        modifyProfileForm['userId'].select();
                        break;
                    case 'failure_duplicate_user_id' :
                        hiddenWarning.failureShow('해당 아이디는 이미 사용중입니다.');
                        break;
                    case 'failure_duplicate_user_nickname' :
                        hiddenWarning.failureShow('해당 닉네임은 이미 사용중입니다.');
                        alert('해당 닉네임은 이미 사용중입니다.');
                        break;
                    case 'success' :
                        alert('회원정보 수정을 성공적으로 마쳤습니다.');
                        window.location.href = '/user/my-page/memberInfo';
                        break;
                    default:
                        alert('알 수 없는 이유로 회원정보를 수정하지 못하였습니다. \n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요.');
                }
            } else {
                alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요');
            }
        }
    };
    xhr.send(formData);
};