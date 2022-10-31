const commentElement = window.document.querySelector('.comment-container');
const commentMenuButton = commentElement.querySelectorAll('.menu');

commentMenuButton.forEach(x => {
    x.addEventListener('click', () => {
        Array.from(commentMenuButton).filter(el => el !== x).forEach(e => e.parentNode.querySelector('.menu-list').classList.remove('on'));
        if (!x.parentNode.querySelector('.menu-list').classList.contains('on')) {
            x.parentNode.querySelector('.menu-list').classList.add('on');
        } else {
            x.parentNode.querySelector('.menu-list').classList.remove('on');
        }
        ;
        const main = window.document.getElementById('mainContainer');
//Hide modal
        window.addEventListener('mouseup', (e) => {
            e.target === main ? x.parentNode.querySelector('.menu-list').classList.remove('on') : false
        });
    });
});

const scrapElement = window.document.querySelector('.scrap-container');
const exportMenuButton = scrapElement.querySelector('.export');

exportMenuButton.addEventListener('click', () => {
    modifyMenuButton.parentNode.querySelector('.modify-info').classList.remove('on');
    if (!exportMenuButton.parentNode.querySelector('.export-info').classList.contains('on')) {
        exportMenuButton.parentNode.querySelector('.export-info').classList.add('on');
    } else {
        exportMenuButton.parentNode.querySelector('.export-info').classList.remove('on');
    }
    ;
    const main = window.document.getElementById('mainContainer');
//Hide modal
    window.addEventListener('mouseup', (e) => {
        e.target === main ? exportMenuButton.parentNode.querySelector('.export-info').classList.remove('on') : false
    });
});

const modifyMenuButton = scrapElement.querySelector('.modify');

modifyMenuButton.addEventListener('click', () => {
    exportMenuButton.parentNode.querySelector('.export-info').classList.remove('on');
    if (!modifyMenuButton.classList.contains('on')) {
        alert('로그인을 진행해 주세요.');
        return;
    }
    if (!modifyMenuButton.parentNode.querySelector('.modify-info').classList.contains('on')) {
        modifyMenuButton.parentNode.querySelector('.modify-info').classList.add('on');
    } else {
        modifyMenuButton.parentNode.querySelector('.modify-info').classList.remove('on');
    }
    ;
    const main = window.document.getElementById('mainContainer');
//Hide modal
    window.addEventListener('mouseup', (e) => {
        e.target === main ? modifyMenuButton.parentNode.querySelector('.modify-info').classList.remove('on') : false
    });
});

const commentBox = commentElement.querySelectorAll('.comment-box');
commentBox.forEach(x => {
    x.querySelectorAll('.link.answer').forEach(y => {
        y.addEventListener('click', e => {
            if (!x.querySelector('.write-comment').classList.contains('visible')) {
                if (x.querySelector('.write-comment').classList.contains('on')) {
                    alert('권한이 없습니다.');
                    return;
                }
                x.querySelector('.write-comment').classList.add('visible');
            } else {
                x.querySelector('.write-comment').classList.remove('visible');
            }
        });
    });
});

commentBox.forEach(x => {
    x.querySelector('.close-button').addEventListener('click', () => {
        if (!x.querySelector('.write-comment').classList.contains('visible')) {
            x.querySelector('.write-comment').classList.add('visible');
        } else {
            x.querySelector('.write-comment').classList.remove('visible');
        }
    });
});

const recommentElement = window.document.querySelectorAll('.recomment');
recommentElement.forEach(x => {
    x.querySelectorAll('.link.answer').forEach(y => {
        y.addEventListener('click', e => {
            if (!x.querySelector('.write-comment').classList.contains('visible')) {
                if (x.querySelector('.write-comment').classList.contains('on')) {
                    alert('권한이 없습니다.');
                    return;
                }
                x.querySelector('.write-comment').classList.add('visible');
            } else {
                x.querySelector('.write-comment').classList.remove('visible');
            }
        });
    });
});

recommentElement.forEach(x => {
    x.querySelector('.close-button').addEventListener('click', () => {
        if (!x.querySelector('.write-comment').classList.contains('visible')) {
            x.querySelector('.write-comment').classList.add('visible');
        } else {
            x.querySelector('.write-comment').classList.remove('visible');
        }
    });
});


const rerecommentElement = window.document.querySelectorAll('.re-recomment');
rerecommentElement.forEach(x => {
    x.querySelectorAll('.link.answer').forEach(y => {
        y.addEventListener('click', e => {
            if (!x.querySelector('.write-comment').classList.contains('visible')) {
                if (x.querySelector('.write-comment').classList.contains('on')) {
                    alert('권한이 없습니다.');
                    return;
                }
                x.querySelector('.write-comment').classList.add('visible');
            } else {
                x.querySelector('.write-comment').classList.remove('visible');
            }
        });
    });
});

rerecommentElement.forEach(x => {
    x.querySelector('.close-button').addEventListener('click', () => {
        if (!x.querySelector('.write-comment').classList.contains('visible')) {
            x.querySelector('.write-comment').classList.add('visible');
        } else {
            x.querySelector('.write-comment').classList.remove('visible');
        }
    });
});


window.document.querySelector('.input.comment').addEventListener('click', x => {
    if (!window.document.querySelector('.input.comment').classList.contains('on')) {
        x.preventDefault();
        alert('권한이 없습니다.');
    }
    if (window.document.querySelector('.textarea.content').value === '') {
        x.preventDefault();
        alert('내용 값은 필수입니다.');
        window.document.querySelector('.textarea.content').focus();
        window.document.querySelector('.textarea.content').select();
    }
});

window.document.querySelector('.textarea.comment').addEventListener('click', x => {
    if (!window.document.querySelector('.textarea.comment').classList.contains('on')) {
        x.preventDefault();
        alert('권한이 없습니다.');
    }
});


const ArticleBuy = window.document.querySelector('.buy-article');
ArticleBuy?.addEventListener('click', x => {
    if (!ArticleBuy.classList.contains('on')) {
        alert('본인 게시글 혹은 한 게시글에는 중복으로 할 수 없습니다.');
        return false;
    }
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `./${ArticleBuy.querySelector('.boardIndex').value}/${ArticleBuy.querySelector('.articleIndex').value}/article-buy`)
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'success':
                        ArticleBuy.querySelector('.buy-count').innerText = parseInt(ArticleBuy.querySelector('.buy-count').innerText) + 1;
                        break;
                    case 'not_found':
                        alert('찾을 수 없는 게시판입니다.');
                        break;
                    case 'not_allowed':
                        alert('한 게시글에 한 번만 가능합니다.');
                        break;
                }
            } else {
                alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
            }
        }
    };
    xhr.send();
});

const ArticleLike = window.document.querySelector('.like-article');
ArticleLike.addEventListener('click', x => {
    if (!ArticleLike.classList.contains('on')) {
        alert('추천할 수 없습니다.');
        return false;
    }
    const xhr = new XMLHttpRequest();
    xhr.open('PUT', `./${ArticleLike.querySelector('.boardIndex').value}/${ArticleLike.querySelector('.articleIndex').value}/article-like`)
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'success':
                        ArticleLike.querySelector('.like-count').innerText = parseInt(ArticleLike.querySelector('.like-count').innerText) + 1;
                        const userSection = window.document.querySelector('.user-section');
                        userSection.querySelector(':scope > .like-count > b').innerText = parseInt(userSection.querySelector(':scope > .like-count > b').innerText) + 1;
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

const ArticleReport = window.document.querySelector('.report-article');
ArticleReport?.addEventListener('click', x => {
    if (!ArticleReport.classList.contains('on')) {
        alert('비회원이거나 혹은 한 게시글에 대한 중복 신고는 할 수 없습니다.');
        return false;
    }
    if (confirm('신고하시겠습니까? 신고된 글은 관리자가 검토 후 조치합니다.')) {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', `./${ArticleReport.querySelector('.boardIndex').value}/${ArticleReport.querySelector('.articleIndex').value}/article-report`)
        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const responseJson = JSON.parse(xhr.responseText);
                    switch (responseJson['result']) {
                        case 'success':
                            alert('신고가 접수되었습니다.');
                            break;
                        case 'not_allowed':
                            alert('비회원이거나 혹은 한 게시글에 대한 중복 신고는 할 수 없습니다.');
                            break;
                    }
                } else {
                    alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
                }
            }
        };
        xhr.send();
    }
});


window.document.querySelector('.modify-article-report')?.addEventListener('click', () => {
    ArticleReport.click();
});

window.document.querySelectorAll('.like').forEach(x => {
    x.addEventListener('click', y => {
        if (!x.classList.contains('on')) {
            alert('추천할 수 없습니다.');
            return false;
        }
        const xhr = new XMLHttpRequest();
        xhr.open('PUT', `./${x.querySelector('.articleIndex').value}/${x.querySelector('.commentIndex').value}/like`)
        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const responseJson = JSON.parse(xhr.responseText);
                    switch (responseJson['result']) {
                        case 'success':
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

window.document.querySelectorAll('.comment-report').forEach(x => {
    x.addEventListener('click', y => {
        if (!x.classList.contains('on')) {
            alert('비회원이거나 혹은 한 댓글에 대한 중복 신고는 할 수 없습니다.');
            return false;
        }
        if (confirm('신고하시겠습니까? 신고된 글은 관리자가 검토 후 조치합니다.')) {
            const xhr = new XMLHttpRequest();
            xhr.open('GET', `./${x.querySelector('.articleIndex').value}/${x.querySelector('.commentIndex').value}/report`)
            xhr.onreadystatechange = () => {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status >= 200 && xhr.status < 300) {
                        const responseJson = JSON.parse(xhr.responseText);
                        switch (responseJson['result']) {
                            case 'success':
                                alert('신고가 접수되었습니다.');
                                break;
                            case 'not_allowed':
                                alert('비회원이거나 혹은 한 게시글에 대한 중복 신고는 할 수 없습니다.');
                                break;
                        }
                    } else {
                        alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
                    }
                }
            };
            xhr.send();
        }
    });
});

//
// const commentRowPerPage = commentElement.querySelectorAll('.user-profile');
// const more = window.document.getElementById('more');
// if (commentRowPerPage.length % 10 === 0) {
//     more.classList.add('visible');
//     more.addEventListener('click', () => {
//     });
// } else {
//     more.classList.remove('visible');
// }




// const commentDeleteForm = window.document.querySelectorAll('.commentDeleteForm');
// const commentDelete = window.document.querySelectorAll('.link.delete');
//
// commentDelete.forEach(x => {
//     x.addEventListener('click', () => {
//         const urlName = x.parentNode.querySelector('.urlName').value;
//         const cid = x.parentNode.querySelector('.commentIndex').value;
//         const method = window.document.querySelector("[name=_method]").value;
//         if (confirm('정말로 삭제할까요? 삭제한 댓글은 다시 복구할 수 없습니다.')) {
//             const xhr = new XMLHttpRequest();
//             xhr.open('POST', `./${cid}/comment-delete`, true)
//             xhr.onreadystatechange = () => {
//                 if (xhr.readyState === XMLHttpRequest.DONE) {
//                     if (xhr.status >= 200 && xhr.status < 300) {
//                         const responseJson = JSON.parse(xhr.responseText);
//                         switch (responseJson['result']) {
//                             case 'success':
//                                 window.location.href = '';
//                                 break;
//                             default:
//                                 alert('실패하였습니다.');
//                                 break;
//                         }
//                     } else {
//                         alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요.');
//                     }
//                 }
//             };
//             xhr.send();
//         }
//     });
// });


