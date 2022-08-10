const loginButton = document.querySelector('.top-login-button');

document.addEventListener('DOMContentLoaded', () => {
    loginButton.addEventListener('click', () => {
        document.querySelector('.shadow').classList.add('visible');
        document.querySelector('.login-form').classList.add('visible');
    });
});

window.document.querySelector('.shadow').addEventListener('click', () => {
    window.document.querySelector('.shadow').classList.remove('visible');
    window.document.querySelector('.login-form').classList.remove('visible');
});

window.onscroll = () => {
    myFunction();
};

function myFunction() {
    const winScroll = document.body.scrollTop || document.documentElement.scrollTop;
    // (0 ~ 553)
    const height = document.documentElement.scrollHeight - document.documentElement.clientHeight; // 553
    const scrolled = (winScroll / height) * 100;
    document.getElementById("myBar").style.width = `${scrolled}%`;
}

// ((document.body.scrollTop || document.documentElement.scrollTop) / (document.documentElement.scrollHeight - document.documentElement.clientHeight)) * 100;

// window.document.documentElement.scrollTop 현재 top의 위치 (0 ~ 553)
// window.innerHeight 807 == document.documentElement.clientHeight 807
// document.documentElement.scrollHeight 1360
// ( 스크롤 된 높이+ 보고있는화면(viewport)높이 ) / 전체문서높이 * 100


// https://ko.javascript.info/size-and-scroll-window
// let scrollHeight = Math.max(
//     document.body.scrollHeight, document.documentElement.scrollHeight,
//     document.body.offsetHeight, document.documentElement.offsetHeight,
//     document.body.clientHeight, document.documentElement.clientHeight
// );

// alert('스크롤에 의해 가려진 분을 포함한 전체 문서 높이: ' + scrollHeight);
// alert('세로 스크롤에 의해 가려진 위쪽 영역 높이: ' + window.pageYOffset);
// alert('가로 스크롤에 의해 가려진 왼쪽 영역 너비: ' + window.pageXOffset);