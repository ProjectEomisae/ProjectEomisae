const registerForm = window.document.getElementById('registerForm');
const hiddenWarning = window.document.querySelector('.hidden-warning');
const warning = window.document.querySelector('.warning');
const emailWarning = window.document.querySelector('.email-warning');
const idWarning = window.document.querySelector('.id-warning');
const nicknameWarning = window.document.querySelector('.nickname-warning');

const emailInput = window.document.getElementById('emailInput');
const passwordInput = window.document.getElementById('passwordInput');
const passwordCheckInput = window.document.getElementById('passwordCheckInput');
const idInput = window.document.getElementById('idInput');
const nickNameInput = window.document.getElementById('nickNameInput');

const isTermsAgreed = window.document.getElementById('isTermsAgreed');

const emailRegex = new RegExp('^(?=.{10,100}$)([0-9a-z][0-9a-z_]*[0-9a-z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$');
const passwordRegex = new RegExp('^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:\'",<.>/?]{6,100})$');
const idRegex = new RegExp('^[A-Za-z]{1}[A-Za-z0-9_]{1,}|$');
const nickNameRegex = new RegExp('^(.*[가-힣]{2,5})|(.*[a-z]{4,10})|(.*[0-9]{4,10})$');


emailInput.addEventListener('focusout', e => {
    if (!emailRegex.test(emailInput.value)) {
        emailWarning.style.display = 'block';
        emailWarning.innerText = '올바른 이메일을 입력해주세요.';
        emailInput.focus();
        emailInput.select();
    } else {
        const xhr = new XMLHttpRequest();
        const formData = new FormData();
        formData.append('email', emailInput.value);
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
                }
                else {
                    alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
                }
            }
        };
        xhr.send(formData);
    }
});

idInput.addEventListener('focusout', e => {
    if (!idRegex.test(idInput.value)) {
        idWarning.style.display = 'block';
        idWarning.innerText = '아아디의 값은 영문,숫자,_만 가능하며 첫 글자는 영문이어야 합니다.';
        idInput.focus();
        idInput.select();
    } else {
        const xhr = new XMLHttpRequest();
        const formData = new FormData();
        formData.append('userId', idInput.value);
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
                }
                else {
                    alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
                }
            }
        };
        xhr.send(formData);
    }
});

nickNameInput.addEventListener('focusout', e => {
   if(!nickNameRegex.test(nickNameInput.value)) {
       document.documentElement.scrollTop = 0;
       hiddenWarning.classList.add('show');
       nicknameWarning.style.display = 'none';
       warning.innerText = '닉네임의 값이 한글(2-5), 영문 또는 숫자(4-10)에 맞지 않습니다.';
       nickNameInput.focus();
       nickNameInput.select();
   } else {
       const xhr = new XMLHttpRequest();
       const formData = new FormData;
       formData.append('nickname', nickNameInput.value);
       xhr.open('POST', 'check-nickname');
       xhr.onreadystatechange = () => {
           if(xhr.readyState === XMLHttpRequest.DONE) {
               if (xhr.status >= 200 && xhr.status < 300) {
                   const response = parseInt(xhr.responseText);
                   switch (response) {
                       case 0:
                           nicknameWarning.style.display = 'none';
                           hiddenWarning.classList.remove('show');
                           break;
                       case 1:
                           nicknameWarning.style.display = 'block';
                           hiddenWarning.classList.remove('show');
                           nicknameWarning.innerText = '이미 존재하는 닉네임입니다. 다른 닉네임을 입력해주세요.';
                           break;
                       default :
                           document.documentElement.scrollTop = 0;
                           hiddenWarning.classList.add('show');
                           nicknameWarning.style.display = 'none';
                           warning.innerText = '닉네임의 값이 한글(2-5), 영문 또는 숫자(4-10)에 맞지 않습니다.';
                           break;
                   }
               }
               else {
                   alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
               }
           }
       };
       xhr.send(formData);
   }
});


registerForm.onsubmit = e => {
    if (isTermsAgreed.checked === false) {
        document.documentElement.scrollTop = 0;
        hiddenWarning.classList.add('show');
        warning.innerText = '약관동의 값은 필수입니다.';
        e.preventDefault();
        return false;
    }
    if (!emailRegex.test(emailInput.value)) {
        alert('이메일 주소의 값은 올바른 메일 주소가 아닙니다.');
        emailInput.focus();
        emailInput.select();
        e.preventDefault();
        return false;
    }
    if (!passwordRegex.test(passwordInput.value)) {
        document.documentElement.scrollTop = 0;
        hiddenWarning.classList.add('show');
        warning.innerText = '비밀번호의 글자 수를 맞추어 주세요.';
        passwordInput.focus();
        passwordInput.select();
        e.preventDefault();
        return false;
    }
    if(passwordCheckInput.value !== passwordInput.value) {
        document.documentElement.scrollTop = 0;
        hiddenWarning.classList.add('show');
        warning.innerText = '새 비밀번호 확인이(가) 잘못되었습니다.';
        passwordCheckInput.focus();
        passwordCheckInput.select();
        e.preventDefault();
        return false;
    }
    if(!idRegex.test(idInput.value)) {
        idWarning.style.display = 'block';
        idWarning.innerText = '아아디의 값은 영문,숫자,_만 가능하며 첫 글자는 영문이어야 합니다.';
        idInput.focus();
        idInput.select();
        e.preventDefault();
        return false;
    }
    if(nickNameInput.value === '') {
        alert('닉네임 값은 필수입니다.');
        nickNameInput.focus();
        nickNameInput.select();
        e.preventDefault();
        return false;
    }
    if(!nickNameRegex.test(nickNameInput.value)) {
        document.documentElement.scrollTop = 0;
        hiddenWarning.classList.add('show');
        warning.innerText = '닉네임의 값이 한글(2-5), 영문 또는 숫자(4-10)에 맞지 않습니다.';
        nickNameInput.focus();
        nickNameInput.select();
        e.preventDefault();
        return false;
    }
};
