const registerForm = window.document.getElementById('registerForm');

const hiddenWarning = {
    getElement: () => window.document.getElementById('hiddenWarning'),
    hide: () => {
        if (hiddenWarning.getElement().classList.contains('failure-text')) {
            hiddenWarning.getElement().classList.remove('failure-text');
        } else if (hiddenWarning.getElement().classList.contains('success-text')) {
            hiddenWarning.getElement().classList.remove('success-text');
        }
    },
    successShow: (text) => {
        hiddenWarning.hide();
        hiddenWarning.getElement().innerText = text;
        hiddenWarning.getElement().classList.add('success-text');
    },
    failureShow: (text) => {
        hiddenWarning.hide();
        hiddenWarning.getElement().innerText = text;
        hiddenWarning.getElement().classList.add('failure-text');
    }
};

const emailWarning = window.document.querySelector('.email-warning');
const idWarning = window.document.querySelector('.id-warning');
const nicknameWarning = window.document.querySelector('.nickname-warning');

registerForm['email'].addEventListener('focusout', e => {
    e.preventDefault();
    if (!new RegExp('^(?=.{10,100}$)([0-9a-z][0-9a-z_]*[0-9a-z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$').test(registerForm['email'].value)) {
        emailWarning.style.display = 'block';
        emailWarning.innerText = '올바른 이메일을 입력해주세요.';
        registerForm['email'].focus();
        registerForm['email'].select();
    } else {
        const xhr = new XMLHttpRequest();
        const formData = new FormData();
        formData.append('email', registerForm['email'].value);
        xhr.open('POST', 'check-email');
        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const response = parseInt(xhr.responseText)
                    switch (response) {
                        case 0:
                            emailWarning.style.display = 'none';
                            hiddenWarning.classList.remove('show');
                            break;
                        case 1:
                            emailWarning.style.display = 'block';
                            emailWarning.innerText = '이미 존재하는 메일 주소입니다. 다른 메일 주소를 입력해주세요.';
                            break;
                        default:
                            emailWarning.style.display = 'block';
                            emailWarning.innerText = '올바른 이메일을 입력해주세요.';
                            break;
                    }
                } else {
                    alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
                }
            }
        };
        xhr.send(formData);
    }
});

registerForm['userId'].addEventListener('focusout', e => {
    e.preventDefault();
    if (!new RegExp('^[A-Za-z]{1}[A-Za-z0-9_]{1,}|$').test(registerForm['userId'].value)) {
        idWarning.style.display = 'block';
        idWarning.innerText = '아아디의 값은 영문,숫자,_만 가능하며 첫 글자는 영문이어야 합니다.';
        registerForm['userId'].focus();
        registerForm['userId'].select();
    } else {
        const xhr = new XMLHttpRequest();
        const formData = new FormData();
        formData.append('userId', registerForm['userId'].value);
        xhr.open('POST', 'check-user-id');
        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const response = parseInt(xhr.responseText)
                    switch (response) {
                        case 0:
                            idWarning.style.display = 'none';
                            hiddenWarning.classList.remove('show');
                            break;
                        case 1:
                            idWarning.style.display = 'block';
                            hiddenWarning.classList.remove('show');
                            idWarning.innerText = '이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.';
                            break;
                        default:
                            idWarning.style.display = 'block';
                            hiddenWarning.classList.remove('show');
                            idWarning.innerText = '아아디의 값은 영문,숫자,_만 가능하며 첫 글자는 영문이어야 합니다.';
                            break;
                    }
                } else {
                    alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
                }
            }
        };
        xhr.send(formData);
    }
});

registerForm['nickname'].addEventListener('focusout', e => {
    e.preventDefault();
    if (!new RegExp('^(.*[가-힣]{2,5})|(.*[a-z]{4,10})|(.*[0-9]{4,10})$').test(registerForm['nickname'].value)) {
        document.documentElement.scrollTop = 0;
        hiddenWarning.failureShow('닉네임의 값이 한글(2-5), 영문 또는 숫자(4-10)에 맞지 않습니다.');
        nicknameWarning.style.display = 'none';
        registerForm['nickname'].focus();
        registerForm['nickname'].select();
    } else {
        const xhr = new XMLHttpRequest();
        const formData = new FormData;
        formData.append('nickname', registerForm['nickname'].value);
        xhr.open('POST', 'check-nickname');
        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const response = parseInt(xhr.responseText);
                    switch (response) {
                        case 0:
                            nicknameWarning.style.display = 'none';
                            hiddenWarning.hide();
                            break;
                        case 1:
                            nicknameWarning.style.display = 'block';
                            hiddenWarning.hide();
                            nicknameWarning.innerText = '이미 존재하는 닉네임입니다. 다른 닉네임을 입력해주세요.';
                            break;
                        default :
                            document.documentElement.scrollTop = 0;
                            nicknameWarning.style.display = 'none';
                            hiddenWarning.failureShow('닉네임의 값이 한글(2-5), 영문 또는 숫자(4-10)에 맞지 않습니다.');
                            break;
                    }
                } else {
                    alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
                }
            }
        };
        xhr.send(formData);
    }
});

registerForm.onsubmit = e => {
    e.preventDefault();
    if (registerForm['termsAgreed'].checked === false) {
        document.documentElement.scrollTop = 0;
        hiddenWarning.failureShow('약관동의 값은 필수입니다.');
        return false;
    }
    if (!new RegExp('^(?=.{10,100}$)([0-9a-z][0-9a-z_]*[0-9a-z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$').test(registerForm['email'].value)) {
        document.documentElement.scrollTop = 0;
        hiddenWarning.failureShow('올바른 메일 주소를 작성해 주세요.');
        registerForm['email'].focus();
        registerForm['email'].select();
        return false;
    }
    if (!new RegExp('^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:\'",<.>/?]{6,100})$').test(registerForm['password'].value)) {
        document.documentElement.scrollTop = 0;
        hiddenWarning.failureShow('비밀번호의 글자 수를 맞추어 주세요.');
        registerForm['password'].focus();
        registerForm['password'].select();
        return false;
    }
    if (registerForm['passwordCheck'].value !== registerForm['password'].value) {
        document.documentElement.scrollTop = 0;
        hiddenWarning.failureShow('두 비밀번호가 일치하지 않습니다. 정확히 입력해 주세요.');
        registerForm['passwordCheck'].focus();
        registerForm['passwordCheck'].select();
        return false;
    }
    if (!new RegExp('^[A-Za-z]{1}[A-Za-z0-9_]{1,}|$').test(registerForm['userId'].value)) {
        idWarning.style.display = 'block';
        idWarning.innerText = '아아디의 값은 영문,숫자,_만 가능하며 첫 글자는 영문이어야 합니다.';
        registerForm['userId'].focus();
        registerForm['userId'].select();
        return false;
    }
    if (registerForm['nickname'].value === '') {
        document.documentElement.scrollTop = 0;
        hiddenWarning.failureShow('닉네임 값은 필수입니다.');
        registerForm['nickname'].focus();
        return false;
    }
    if (!new RegExp('^(.*[가-힣]{2,5})|(.*[a-z]{4,10})|(.*[0-9]{4,10})$').test(registerForm['nickname'].value)) {
        document.documentElement.scrollTop = 0;
        hiddenWarning.failureShow('닉네임의 값이 한글(2-5), 영문 또는 숫자(4-10)에 맞지 않습니다.');
        registerForm['nickname'].focus();
        registerForm['nickname'].select();
        return false;
    }
    if (registerForm['findPasswordAnswer'].value === '') {
        document.documentElement.scrollTop = 0;
        hiddenWarning.failureShow('계정찾기에 이용되는 비밀번호 찾기 답변 값은 필수입니다.');
        registerForm['findPasswordAnswer'].focus();
        return false;
    }
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('email', registerForm['email'].value);
    formData.append('password', registerForm['password'].value);
    formData.append('passwordCheck', registerForm['passwordCheck'].value);
    formData.append('userId', registerForm['userId'].value);
    formData.append('nickname', registerForm['nickname'].value);
    formData.append('findPasswordIndex', registerForm['findPasswordIndex'].value);
    formData.append('findPasswordAnswer', registerForm['findPasswordAnswer'].value);
    if ((registerForm['profileImage'].files?.length ?? 0) > 0) {
        formData.append('profileImage', registerForm['profileImage'].files[0]);
    }
    formData.append('mailReceived', registerForm['mailReceived'].value);
    formData.append('messageReceptionIndex', registerForm['messageReceptionIndex'].value);

    xhr.open('POST', './memberSignUpForm');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'illegal' :
                        document.documentElement.scrollTop = 0;
                        alert('비정상적인 접근입니다.');
                        break;
                    case 'failure_duplicate_email' :
                        document.documentElement.scrollTop = 0;
                        hiddenWarning.failureShow('이미 사용중인 이메일 입니다.');
                        break;
                    case 'failure_duplicate_id' :
                        document.documentElemen밋t.scrollTop = 0;
                        hiddenWarning.failureShow('이미 사용중인 아이디 입니다.');
                        break;
                    case 'failure_duplicate_nickname' :
                        document.documentElement.scrollTop = 0;
                        hiddenWarning.failureShow('이미 사용중인 닉네임 입니다.');
                        break;
                    case 'success' :
                        alert('입력하신 이메일로 회원가입 인증과 관련된 내용이 전송되었습니다.\n\n해당 메일을 통해 회원가입을 완료해주세요.');
                        document.documentElement.scrollTop = 0;
                        hiddenWarning.successShow(`[ ${registerForm['email'].value} ] 로 회원가입 인증과 관련된 내용이 전송되었습니다. 해당 메일을 통해 회원가입을 완료해주세요.`);
                        break;
                    default :
                        alert('알 수 없는 이유로 회원가입에 실패하였습니다. \n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요.');
                }
            } else {
                alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해주시거나 고객센터를 통해 문의해주세요');
            }
        }
    };
    xhr.send(formData);
};
