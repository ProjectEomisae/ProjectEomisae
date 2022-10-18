const modifyProfileForm = window.document.getElementById('modifyProfileForm');

modifyProfileForm.onsubmit = e => {
    e.preventDefault();
    if (modifyProfileForm['nickname'].value === '') {
        alert('닉네임 값은 필수입니다.')
        modifyProfileForm['nickname'].focus();
        return false;
    }
    if (!new RegExp('^[A-Za-z]{1}[A-Za-z0-9_]{1,}$').test(modifyProfileForm['userId'].value)) {
        alert('아아디의 값은 영문,숫자,_만 가능하며 첫 글자는 영문이어야 합니다.');
        modifyProfileForm['userId'].focus();
        modifyProfileForm['userId'].select();
        return false;
    }
    if (!new RegExp('^(.*[가-힣]{2,5})|(.*[a-z]{4,10})|(.*[0-9]{4,10})$').test(modifyProfileForm['nickname'].value)) {
        alert('닉네임의 값이 한글(2-5), 영문 또는 숫자(4-10)에 맞지 않습니다.');
        modifyProfileForm['nickname'].focus();
        modifyProfileForm['nickname'].select();
        return false;
    }
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('userId', modifyProfileForm['userId'].value);
    formData.append('nickname', modifyProfileForm['nickname'].value);
        formData.append('profileImage', modifyProfileForm['profileImage'].files[0]);

    xhr.open('POST', './memberModifyInfo');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'illegal_nickname' :
                        alert('닉네임의 값이 한글(2-5), 영문 또는 숫자(4-10)에 맞지 않습니다.');
                        modifyProfileForm['nickname'].focus();
                        modifyProfileForm['nickname'].select();
                        break;
                    case 'illegal_user_id' :
                        alert('아아디의 값은 영문,숫자,_만 가능하며 첫 글자는 영문이어야 합니다.');
                        modifyProfileForm['userId'].focus();
                        modifyProfileForm['userId'].select();
                        break;
                    case 'failure_duplicate_user_id' :
                        alert('해당 아이디는 이미 사용중입니다.');
                        break;
                    case 'failure_duplicate_user_nickname' :
                        alert('해당 닉네임은 이미 사용중입니다.');
                        break;
                    case 'success' :
                        alert('회원정보 수정을 성공적으로 마쳤습니다.');
                        break;
                    default:
                        alert('알 수 없는 이유로 동행 글을 작성하지 못하였습니다. 잠시 후 다시 시도해 주세요');
                }
            }
        } else {
            alert('서버와 통신하지 못하엿습니다. 잠시 후 다시 시도해 주세요.');
        }
    };
    xhr.send(formData);


}