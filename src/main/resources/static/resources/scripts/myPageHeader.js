const listLi = document.querySelectorAll('.list');

listLi.forEach(x => {
    x.addEventListener('click', () => {
        x.classList.toggle('selected');
    });
});


// document.addEventListener("DOMContentLoaded", x => {
//
// });
