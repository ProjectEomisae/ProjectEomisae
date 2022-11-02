const writerName = window.document.getElementById("writerName");
const main = window.document.getElementById("main");

const userInfo = window.document.getElementById("userInfo");

let clickFunction = function () {
    if(!userInfo.classList.contains('on')) {
        userInfo.classList.add('on');
    } else if(userInfo.classList.contains('on')) {
        userInfo.classList.remove('on');
    }
}
writerName.addEventListener('click', clickFunction);

main.addEventListener('click', () => {
    writerName.removeEventListener('click', clickFunction);
});












