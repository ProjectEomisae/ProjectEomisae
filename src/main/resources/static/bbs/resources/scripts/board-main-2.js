// dr, ds, gm, jo2, jo3, os, rt

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

const searchElement = window.document.querySelector('.search-container');
const searchButton = window.document.querySelector('.link.search');
searchButton.addEventListener('click', () => {
    if(!searchElement.classList.contains('on')) {
        searchElement.classList.add('on');
    } else {
        searchElement.classList.remove('on');
    };
});