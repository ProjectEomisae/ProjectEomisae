const findByEmailForm = window.document.getElementById('findByEmailForm');
const findByQnaForm = window.document.getElementById('findByQnaForm');
const resendEmailForm = window.document.getElementById('resendEmailForm');

const hiddenWarningByEmail = {
    getElement: () => window.document.getElementById('hiddenWarningByEmail'),
    hide: () => {
        if (hiddenWarningByEmail.getElement().classList.contains('failure-text')) {
            hiddenWarningByEmail.getElement().classList.remove('failure-text');
        } else if (hiddenWarningByEmail.getElement().classList.contains('success-text')) {
            hiddenWarningByEmail.getElement().classList.remove('success-text');
        }
    },
    successShow: (text) => {
        hiddenWarningByEmail.hide();
        hiddenWarningByEmail.getElement().innerText = text;
        hiddenWarningByEmail.getElement().classList.add('success-text');
    },
    failureShow: (text) => {
        hiddenWarningByEmail.hide();
        hiddenWarningByEmail.getElement().innerText = text;
        hiddenWarningByEmail.getElement().classList.add('failure-text');
    }
};

const hiddenWarningByQna = {
    getElement: () => window.document.getElementById('hiddenWarningByQna'),
    hide: () => {
        if (hiddenWarningByQna.getElement().classList.contains('failure-text')) {
            hiddenWarningByQna.getElement().classList.remove('failure-text');
        } else if (hiddenWarningByQna.getElement().classList.contains('success-text')) {
            hiddenWarningByQna.getElement().classList.remove('success-text');
        }
    },
    successShow: (text) => {
        hiddenWarningByQna.hide();
        hiddenWarningByQna.getElement().innerText = text;
        hiddenWarningByQna.getElement().classList.add('success-text');
    },
    failureShow: (text) => {
        hiddenWarningByQna.hide();
        hiddenWarningByQna.getElement().innerText = text;
        hiddenWarningByQna.getElement().classList.add('failure-text');
    }
};

const hiddenWarningResendEmail = {
    getElement: () => window.document.getElementById('hiddenWarningResendEmail'),
    hide: () => {
        if (hiddenWarningResendEmail.getElement().classList.contains('failure-text')) {
            hiddenWarningResendEmail.getElement().classList.remove('failure-text');
        } else if (hiddenWarningResendEmail.getElement().classList.contains('success-text')) {
            hiddenWarningResendEmail.getElement().classList.remove('success-text');
        }
    },
    successShow: (text) => {
        hiddenWarningResendEmail.hide();
        hiddenWarningResendEmail.getElement().innerText = text;
        hiddenWarningResendEmail.getElement().classList.add('success-text');
    },
    failureShow: (text) => {
        hiddenWarningResendEmail.hide();
        hiddenWarningResendEmail.getElement().innerText = text;
        hiddenWarningResendEmail.getElement().classList.add('failure-text');
    }
};

findByEmailForm.onsubmit = e => {
    e.preventDefault();
    if (findByEmailForm['email'].value === '') {
        hiddenWarningByEmail.failureShow('이메일을 입력해 주세요.');
        findByEmailForm['email'].focus();
        return false;
    }
    if (!new RegExp('^(?=.{10,100}$)([0-9a-z][0-9a-z_]*[0-9a-z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$').test(findByEmailForm['email'].value)) {
        hiddenWarningByEmail.failureShow('올바른 이메일을 입력해 주세요.');
        findByEmailForm['email'].focus();
        findByEmailForm['email'].select();
        return false;
    }
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('email', findByEmailForm['email'].value);
    xhr.open('POST', './findAccountEmail');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'success' :
                        alert('회원님의 정보가 담긴 메일을 성공적으로 보냈습니다. 메일을 확인해 주세요.');
                        hiddenWarningByEmail.successShow('회원님의 정보가 담긴 메일을 성공적으로 보냈습니다. 메일을 확인해 주세요.');
                        break;
                    default :
                        hiddenWarningByEmail.failureShow('입력하신 이메일과 일치하는 회원이 없습니다.');
                        findByEmailForm['email'].select();
                }
            }
        } else {
            hiddenWarningByEmail.failureShow('서버와 통신하지 못하엿습니다. 잠시 후 다시 시도해 주세요.');
            findByEmailForm['email'].focus();
            findByEmailForm['email'].select();
        }
    };
    xhr.send(formData);
};

findByQnaForm.onsubmit = e => {
    e.preventDefault();
    if (findByQnaForm['email'].value === '') {
        hiddenWarningByQna.failureShow('이메일을 입력해 주세요.');
        findByQnaForm['email'].focus();
        return false;
    }
    if (!new RegExp('^(?=.{10,100}$)([0-9a-z][0-9a-z_]*[0-9a-z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$').test(findByQnaForm['email'].value)) {
        hiddenWarningByQna.failureShow('올바른 이메일을 입력해 주세요.');
        findByQnaForm['email'].focus();
        findByQnaForm['email'].select();
        return false;
    }
    if (findByQnaForm['findAccountAnswer'].value === '') {
        hiddenWarningByQna.failureShow('비밀번호 찾기 답변을 입력해 주세요.');
        findByQnaForm['findAccountAnswer'].focus();
        return false;
    }
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('email', findByQnaForm['email'].value);
    formData.append('findAccountIndex', findByQnaForm['findAccountIndex'].value);
    formData.append('findAccountAnswer', findByQnaForm['findAccountAnswer'].value);
    xhr.open('POST', './findAccountQna');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'success' :
                        alert('메일을 성공적으로 보냈습니다. 메일을 확인해 주세요.');
                        hiddenWarningByQna.successShow('메일을 성공적으로 보냈습니다. 메일을 확인해 주세요.');
                        break;
                    case 'not_match_question_index' :
                        hiddenWarningByQna.failureShow('회원가입 시 선택한 질문이 아닙니다. 회원가입 시 선택한 질문을 선택해 주세요.');
                        findByQnaForm['findAccountIndex'].focus();
                        break;
                    case 'not_match_answer' :
                        hiddenWarningByQna.failureShow('비밀번호 찾기 답변이 올바르지 않습니다. 정확한 답변을 입력해 주세요.');
                        findByQnaForm['findAccountAnswer'].focus();
                        findByQnaForm['findAccountAnswer'].select();
                        break;
                    default :
                        hiddenWarningByQna.failureShow('입력하신 이메일과 일치하는 회원이 없습니다.');
                        findByQnaForm['email'].select();
                }
            } else {
                hiddenWarningByQna.failureShow('서버와 통신하지 못하엿습니다. 잠시 후 다시 시도해 주세요.');
                findByQnaForm['email'].focus();
                findByQnaForm['email'].select();
            }
        }
    };
    xhr.send(formData);
};

resendEmailForm.onsubmit = e => {
    e.preventDefault();
    if (resendEmailForm['email'].value === '') {
        hiddenWarningResendEmail.failureShow('이메일을 입력해 주세요.');
        resendEmailForm['email'].focus();
        return false;
    }
    if (!new RegExp('^(?=.{10,100}$)([0-9a-z][0-9a-z_]*[0-9a-z])@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$').test(resendEmailForm['email'].value)) {
        hiddenWarningResendEmail.failureShow('올바른 이메일을 입력해 주세요.');
        resendEmailForm['email'].focus();
        resendEmailForm['email'].select();
        return false;
    }
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('email', resendEmailForm['email'].value);
    xhr.open('POST', './resendVerificationEmail');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'success' :
                        alert('해당 메일로 가입 인증 메일이 다시 발송되었습니다. \n\n해당 메일을 통해 회원가입을 완료해주세요.');
                        hiddenWarningResendEmail.successShow(`[ ${resendEmailForm['email'].value} ] 메일로 가입 인증 메일이 다시 발송되었습니다. 해당 메일을 통해 회원가입을 완료해주세요.`);
                        break;
                    default :
                        alert('잘못된 요청입니다.');
                        resendEmailForm['email'].select();
                }
            }  else {
                alert('서버와 통신하지 못하엿습니다. 잠시 후 다시 시도해 주세요.');
                resendEmailForm['email'].focus();
                resendEmailForm['email'].select();
            }
        }
    };
    xhr.send(formData);
};


























