const emailModifyForm = window.document.getElementById('emailModifyForm');
const emailModifyInput = window.document.getElementById('emailModifyInput');
const warning = window.document.getElementById('warning');
const emailRegex = new RegExp('^(?=.{10,100}$)([0-9a-z][0-9a-z_]*[0-9a-z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$');
const hiddenWarning = window.document.getElementById('hiddenWarning');

emailModifyInput.addEventListener('focusout', e => {
    if (!emailRegex.test(emailModifyInput.value)) {
        hiddenWarning.classList.remove('success-text');
        hiddenWarning.classList.add('failure-text');
        warning.innerText = '올바른 이메일을 입력해주세요.';
        emailModifyInput.focus();
        emailModifyInput.select();
    } else {
        const xhr = new XMLHttpRequest();
        const formDate = new FormData();
        formDate.append('email', emailModifyInput.value);
        xhr.open('POST', 'check-email');
        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const response = parseInt(xhr.responseText)
                    switch (response) {
                        case 0:
                            hiddenWarning.classList.remove('failure-text');
                            hiddenWarning.classList.add('success-text');
                            warning.innerText = '사용가능한 이메일 입니다.'
                            break;
                        case 1:
                            hiddenWarning.classList.remove('success-text');
                            hiddenWarning.classList.add('failure-text');
                            warning.innerText = '이미 존재하는 메일 주소입니다. 다른 메일 주소를 입력해주세요.';
                            emailModifyInput.focus();
                            emailModifyInput.select();
                            break;
                        default:
                            hiddenWarning.classList.remove('success-text');
                            hiddenWarning.classList.add('failure-text');
                            warning.innerText = '올바른 이메일을 입력해주세요.';
                            emailModifyInput.focus();
                            emailModifyInput.select();
                            break;
                    }
                }
                else {
                    alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
                }
            }
        };
        xhr.send(formDate);
    }
});

