// ui

// window.document.querySelector('#main').addEventListener('click', () => {
//     if(categoryElement.querySelector('.category-list').classList.contains('on')) {
//         categoryElement.querySelector('.category-list').classList.remove('on');
//     };
// });

const alignElement = window.document.querySelector('.align');
const alignButton = alignElement.querySelector('.link');
alignButton.addEventListener('click', () => {
    if (!alignElement.querySelector('.align-list').classList.contains('on')) {
        alignElement.querySelector('.align-list').classList.add('on');
    } else {
        alignElement.querySelector('.align-list').classList.remove('on');
    }
    ;
});

const userElement = window.document.querySelectorAll('.user.comment');
userElement.forEach(x => {
    x.querySelector('span').addEventListener('click', () => {
        Array.from(userElement).filter(el => el !== x).forEach(e => e.querySelector(':scope > .user-info').classList.remove('on'));
        if (!x.querySelector('.user-info').classList.contains('on')) {
            x.querySelector('.user-info').classList.add('on');
        } else {
            x.querySelector('.user-info').classList.remove('on');
        }
        ;
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
    if (!searchElement.classList.contains('on')) {
        searchElement.classList.add('on');
    } else {
        searchElement.classList.remove('on');
    }
    ;
});
//
// const writeBox = window.document.querySelector('.write-box');
// writeBox.querySelector('textarea').addEventListener('focusin', () => {
//     writeBox.querySelector('textarea').innerText = '';
// });

window.document.querySelector('[value=게시]').addEventListener('click', x => {
    if (!window.document.querySelector('[value=게시]').classList.contains('on')) {
        x.preventDefault();
        alert('권한이 없습니다.');
        return;
    }
    if (window.document.querySelector('.textarea.content').value === '') {
        x.preventDefault();
        alert('내용 값은 필수입니다.');
        window.document.querySelector('.textarea.content').focus();
        window.document.querySelector('.textarea.content').select();
    }
});

window.document.querySelectorAll('.submit-button').forEach(e => {
    e.addEventListener('click', x => {
        if (!window.document.querySelector('.submit-button').classList.contains('on')) {
            x.preventDefault();
            alert('권한이 없습니다.');
            return;
        }
        if (e.parentNode.querySelector('.comment-input').value === '') {
            x.preventDefault();
            alert('내용 값은 필수입니다.');
            e.parentNode.querySelector('.comment-input').focus();
            e.parentNode.querySelector('.comment-input').select();
        }
    });
});

window.document.querySelectorAll('.menu.button').forEach(x => {
    x.addEventListener('click', element => {
        if (!x.classList.contains('on')) {
            element.preventDefault();
            alert('권한이 없습니다.');
            return;
        }
        if (!x.parentNode.querySelector('.user-info').classList.contains('visible')) {
            x.parentNode.querySelector('.user-info').classList.add('visible');
        } else {
            x.parentNode.querySelector('.user-info').classList.remove('visible');
        }
        const main = window.document.getElementById('mainContainer');
//Hide modal
        window.addEventListener('mouseup', (e) => {
            e.target === main ? x.parentNode.querySelector('.user-info').classList.remove('visible') : false
        });
    });
});

window.document.querySelectorAll('.like').forEach(x => {
    x.addEventListener('click', y => {
        if (!x.classList.contains('on')) {
            alert('추천할 수 없습니다.');
            return false;
        }
        const xhr = new XMLHttpRequest();
        xhr.open('PUT', `./${x.querySelector('#urlName').value}/${x.querySelector('#commentIndex').value}/like`)
        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const responseJson = JSON.parse(xhr.responseText);
                    switch (responseJson['result']) {
                        case 'success':
                            x.querySelector('.icon').style.color = 'red';
                            x.querySelector('.like-count').innerText = parseInt(x.querySelector('.like-count').innerText) + 1;
                            break;
                        case 'not_found':
                            alert('찾을 수 없는 게시판입니다.');
                            break;
                        case 'not_allowed':
                            alert('더 이상 추천을 누를 수 없습니다.');
                            break;
                    }
                } else {
                    alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
                }
            }
        };
        xhr.send();
    });
});

window.document.body.querySelectorAll('.link.my-page').forEach(e => {
    e.addEventListener('click', x => {
        if (!e.classList.contains('on')) {
            x.preventDefault();
            alert('권한이 없습니다.');
        }
    });
});