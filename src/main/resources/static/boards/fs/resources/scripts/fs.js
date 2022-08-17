const categoryElement = window.document.querySelector('.category');
const categoryButton = categoryElement.querySelector('.link');
categoryButton.addEventListener('click', () => {
    if(!categoryElement.querySelector('.category-list').classList.contains('on')) {
        categoryElement.querySelector('.category-list').classList.add('on');
    } else {
        categoryElement.querySelector('.category-list').classList.remove('on');
    };
});

// window.document.querySelector('#main').addEventListener('click', () => {
//     if(categoryElement.querySelector('.category-list').classList.contains('on')) {
//         categoryElement.querySelector('.category-list').classList.remove('on');
//     };
// });

const alignElement = window.document.querySelector('.align');
const alignButton = alignElement.querySelector('.link');
alignButton.addEventListener('click', () => {
    if(!alignElement.querySelector('.align-list').classList.contains('on')) {
        alignElement.querySelector('.align-list').classList.add('on');
    } else {
        alignElement.querySelector('.align-list').classList.remove('on');
    };
});

const userElement = window.document.querySelector('.user');
const userButton = userElement.querySelector('span');
userButton.addEventListener('click', () => {
    if(!userElement.querySelector('.user-info').classList.contains('on')) {
        userElement.querySelector('.user-info').classList.add('on');
    } else {
        userElement.querySelector('.user-info').classList.remove('on');
    };
});
