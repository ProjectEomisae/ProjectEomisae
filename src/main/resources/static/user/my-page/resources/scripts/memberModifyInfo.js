const modifyProfileForm = window.document.getElementById('modifyProfileForm');
const nickNameInput = window.document.getElementById('nickNameInput');
const idInput = window.document.getElementById('idInput');
const nickNameRegex = new RegExp('^(.*[가-힣]{2,5})|(.*[a-z]{4,10})|(.*[0-9]{4,10})$');
const idRegex = new RegExp('^[A-Za-z]{1}[A-Za-z0-9_]{1,}$');

modifyProfileForm.onsubmit = e => {
    if(modifyProfileForm['nickname'].value === '') {
        alert('닉네임 값은 필수입니다.')
        modifyProfileForm['nickname'].focus();
        e.preventDefault();
        return false;
    }
    if(!idRegex.test(idInput.value)) {
        alert('아아디의 값은 영문,숫자,_만 가능하며 첫 글자는 영문이어야 합니다.');
        idInput.focus();
        idInput.select();
        e.preventDefault();
        return false;
    }
    if(!nickNameRegex.test(nickNameInput.value)) {
        alert('닉네임의 값이 한글(2-5), 영문 또는 숫자(4-10)에 맞지 않습니다.');
        nickNameInput.focus();
        nickNameInput.select();
        e.preventDefault();
        return false;
    }
}