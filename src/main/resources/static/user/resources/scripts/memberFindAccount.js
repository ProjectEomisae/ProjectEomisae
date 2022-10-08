const findByEmailForm = window.document.getElementById('findByEmailForm');
const hiddenWarning = {
    getElement: () => window.document.getElementById('hiddenWarning'),
    hide: () => {
        if (hiddenWarning.getElement().classList.contains('failure-text')) {
            hiddenWarning.getElement().classList.remove('failure-text');
        } else {
            hiddenWarning.getElement().classList.remove('success-text');
        }
    },
    successShow: (text) => {
        hiddenWarning.getElement().innerText = text;
        hiddenWarning.getElement().classList.add('success-text');
    },
    failureShow: (text) => {
        hiddenWarning.getElement().innerText = text;
        hiddenWarning.getElement().classList.add('failure-text');
    }
};

findByEmailForm.onsubmit = e => {
    e.preventDefault();
    if (findByEmailForm['email'].value === '') {
        hiddenWarning.failureShow('이메일을 입력해 주세요.');
        hiddenWarning['email'].focus();
        return;
    }
    if(!new RegExp('^(?=.{10,100}$)([0-9a-z][0-9a-z_]*[0-9a-z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$').test(findByEmailForm['email'].value)) {
        hiddenWarning.failureShow('올바른 이메일을 입력해 주세요.');
        hiddenWarning['email'].focus();
        hiddenWarning['email'].select();
        return;
    }
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `./memberFIndAccount?email=${findByEmailForm['email'].value}`);
    xhr.onreadystatechange = () => {
        if(xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'success' :
                        hiddenWarning.successShow('성공');
                        break;
                    default :
                        hiddenWarning.failureShow('해당 이메일과 일치하는 회원이 없습니다.');
                        hiddenWarning['email'].focus();
                        hiddenWarning['email'].select();
                }
            }
        } else {
            hiddenWarning.failureShow('서버와 통신하지 못하엿습니다. 잠시 후 다시 시도해주세요.');
            hiddenWarning['email'].focus();
            hiddenWarning['email'].select();
        }
    }
    xhr.send();
}





























