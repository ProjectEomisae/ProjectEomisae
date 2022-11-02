const showInput = document.getElementById('showInput');
const selectOption = document.getElementById('selectBox');

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


function showText() {
    const text = selectOption.options[selectOption.selectedIndex].text;
    showInput.value = `${text}`;
}

function inputNumberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}

// const nowDate = new Date();
// // const timeOff = new Date().getTimezoneOffset()*60000;
// const today = new Date(nowDate).toISOString().split("T")[0]; // -32400000
// // new Date(nowDate-timeOff).toISOString() : '2022-08-16T15:12:43.351Z' "T"를 기준으로 나누고 0번째 문자열 뽑아냄.
// document.getElementById("date").setAttribute("max", today);

// UTC : 세계시 toISOString 함수는 UTC 시간을 기준으로 반환한다.
// 한국 : UTC+9 이라서 UTC 와 9시간차이의 오프셋을 가지고 있다.
// UTC 는 YYYY-MM-DD 형식의 문자열으로 반환을 하기 때문에 오프셋을 변경할 필요가 있다.
// 현재 시간과의 차이를 분 단위로 반환하는 getTimezoneOffset() 함수를 사용한다.
// 분 단위로 반환하기 때문에 기존 밀리초 단위로 인자를 받는 new Date() 함수에 넣기 위해서 1000(밀리초)*60(초) 를 곱해 밀리초 단위로 만든다.
// 이후 현재 시간과의 차이만큼 빼어 시간을 설정하면 된다.

// const limitDay = new Date(nowDate.setDate(nowDate.getDate() -3)); // Tue Aug 16 2022 15:12:43 GMT+0900 (한국 표준시) {}
// const fistDay = new Date(limitDay).toISOString().split("T")[0]; // '2022-08-16'
// document.getElementById("date").setAttribute("min", fistDay);




















