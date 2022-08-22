// ui

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

const userElement = window.document.querySelectorAll('.user');
userElement.forEach(x => {
    x.querySelector('span').addEventListener('click', () => {
        Array.from(userElement).filter(el => el !== x).forEach(e => e.querySelector(':scope > .user-info').classList.remove('on'));
        if(!x.querySelector('.user-info').classList.contains('on')) {
            x.querySelector('.user-info').classList.add('on');
        } else {
            x.querySelector('.user-info').classList.remove('on');
        };
        const main = window.document.getElementById('mainContainer');
//Hide modal
        window.addEventListener('mouseup', (e) => {
            e.target === main ? x.querySelector('.user-info').classList.remove('on') : false
        });
    });
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

const writeBox = window.document.querySelector('.write-box');
writeBox.querySelector('textarea').addEventListener('focusin', () => {
    writeBox.querySelector('textarea').innerText = '';
});

