// const myPage = window.document.querySelector('.mypage-header');
// const listLi = myPage.querySelectorAll('.list');
//
// window.document.addEventListener("DOMContentLoaded", x => {
//     listLi.forEach(x => {
//         x.addEventListener('click', () => {
//             if (!x.classList.contains('selected')) {
//                 x.classList.add('selected');
//             } else {
//                 x.classList.remove('selected');
//             }
//         });
//     });
// });


// Array.prototype.forEach.call(listLi, function (x){
//     x.addEventListener("click", function (){
//         Array.prototype.forEach.call(listLi, function (x){
//             x.classList.remove("selected");
//         });
//         this.classList.add("selected");
//     });
// });

// window.document.addEventListener("DOMContentLoaded", x => {
//     listLi.forEach(x => {
//         x.addEventListener('click', () => {
//            const clicked = x.parentNode.parentNode.parentNode;
//             if (!x.classList.contains('selected')) {
//                 x.classList.add('selected');
//             } else {
//                 x.classList.remove('selected');
//             }
//             const index = x.index
//         });
//     });
// });