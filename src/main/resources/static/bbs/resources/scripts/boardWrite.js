let editorContent;
const content = window.document.getElementById('editor');
ClassicEditor
    .create(content, {
        simpleUpload: {
            uploadUrl: '/bbs/add/upload-image',
        },
    }).then(editor => {
    editorContent = editor;
    editorContent.getData();
    editor.editing.view.change(writer => {
        writer.setStyle('min-height', '70vh', editor.editing.view.document.getRoot());
        writer.setStyle('max-height', '70vh', editor.editing.view.document.getRoot());
    });
    // editorContent.getData().split('?').at(-1).split('"')[0];
    // editorContent.getData().split('download-image?').at(-1).split('"')[0];
    // editorContent.getData().split('download-image?').at(-1).split('">')[0];
    // editorContent.addEventListener('input', () => {
    //     if (editorContent.getData().includes('img') === true) {
    //         alert('ok');
    //     }
    // });
});




// if (ClassicEditor.create()) {
//     window.document.querySelector('.image-components').classList.add('visible');
// };

// editor.model.window.document.on('change:data', () => {
//    console.log("??");
// });
// editor.addEventListener('input', e => {
//     e.preventDefault();
//     window.document.querySelector('.image-components').classList.add('visible');
// });

// if (ClassicEditor.create.status === 'loaded') {
//     window.document.querySelector('.image-components').classList.add('visible');
// }

// if (ClassicEditor.create.simpleUpload.uploaded === 1) {
//     window.document.querySelector('.image-components').classList.add('visible');
// }

// if (ClassicEditor.create.simpleUpload.uploadUrl.request.url === '/bbs/add/upload-image') {
//     window.document.querySelector('.image-components').classList.add('visible');
// }
if (window.document.querySelector('.image-components') !== null) {
    const imageBox = window.document.querySelector('.list-images');
    const image = imageBox.querySelector(':scope > .list');
}

const fileForm = window.document.getElementById('fileUpload');


window.document.getElementById('imageUploadButton').addEventListener('click', () => {
    const ckUploadButton = window.document.querySelector('[type=file]');
    ckUploadButton.click();
});


window.document.querySelector('.submit-button').addEventListener('click', e => {
    if (window.document.querySelector('[name=categoryIndex]').value === 'option') {
        e.preventDefault();
        alert('카테고리를 설정해 주세요.');
        window.document.querySelector('[name=categoryIndex]').focus();
        return;
    }

    if (window.document.querySelector('[name=title]').value === '') {
        e.preventDefault();
        alert('제목을 입력해 주세요.');
        window.document.querySelector('[name=title]').focus();
        return false;
    }

    if (editorContent.getData() === '') {
        e.preventDefault();
        alert('내용을 입력해 주세요.');
        window.document.querySelector('[name=content]').focus();
        window.document.querySelector('[name=content]').select();
        return false;
    }

    if (window.document.querySelector('[name=url]')?.value === '') {
        e.preventDefault();
        alert('링크는 필수 입력 값입니다.');
        window.document.querySelector('[name=url]').focus();
        return false;
    }

    if (window.document.querySelector('[name=orderDate]')?.value === '') {
        e.preventDefault();
        alert('주문날짜는 필수 입력 값입니다.');
        window.document.querySelector('[name=orderDate]').focus();
        return false;
    }

    if (window.document.querySelector('[name=shoppingMall]')?.value === '') {
        e.preventDefault();
        alert('쇼핑몰은 필수 입력 값입니다.');
        window.document.querySelector('[name=shoppingMall]').focus();
        return false;
    }

    if (window.document.querySelector('[name=brand]')?.value === '') {
        e.preventDefault();
        alert('브랜드명은 필수 입력 값입니다.');
        window.document.querySelector('[name=brand]').focus();
        return false;
    }

    if (window.document.querySelector('[name=productName]')?.value === '') {
        e.preventDefault();
        alert('상품명은 필수 입력 값입니다.');
        window.document.querySelector('[name=productName]').focus();
        return false;
    }

    if (window.document.querySelector('[name=productSize]')?.value === '') {
        e.preventDefault();
        alert('상품 사이즈는 필수 입력 값입니다.');
        window.document.querySelector('[name=productSize]').focus();
        return false;
    }

    if (window.document.querySelector('[name=productPrice]')?.value === '') {
        e.preventDefault();
        alert('상품 가격은 필수 입력 값입니다.');
        window.document.querySelector('[name=productPrice]').focus();
        return false;
    }

    if (window.document.querySelector('[name=shoes]')?.value === '') {
        e.preventDefault();
        alert('신발은 필수 입력 값입니다.');
        window.document.querySelector('[name=shoes]').focus();
        return false;
    }

    if (window.document.querySelector('[name=gender]')?.value === '') {
        e.preventDefault();
        alert('성별은 필수 입력 값입니다.');
        window.document.querySelector('[name=gender]').focus();
        return false;
    }

    if (window.document.querySelector('[name=productStatus]')?.value === '') {
        e.preventDefault();
        alert('제품 상태는 필수 입력 값입니다.');
        window.document.querySelector('[name=productStatus]').focus();
        return false;
    }

    if (window.document.querySelector('[name=tradeMethod]')?.value === '') {
        e.preventDefault();
        alert('거래 방법은 필수 입력 값입니다.');
        window.document.querySelector('[name=tradeMethod]').focus();
        return false;
    }

    if (window.document.querySelector('[name=purchaseProductPrice]')?.value === '') {
        e.preventDefault();
        alert('구매 가격은 필수 입력 값입니다.');
        window.document.querySelector('[name=purchaseProductPrice]').focus();
        return false;
    }

    if (window.document.querySelector('[name=saleProductPrice]')?.value === '') {
        e.preventDefault();
        alert('판매 가격은 필수 입력 값입니다.');
        window.document.querySelector('[name=saleProductPrice]').focus();
        return false;
    }

    if (window.document.querySelector('[name=mySize]')?.value === '') {
        e.preventDefault();
        alert('본인 사이즈는 필수 입력 값입니다.');
        window.document.querySelector('[name=mySize]').focus();
        return false;
    }

    if (window.document.querySelector('[name=exchangeSize]')?.value === '') {
        e.preventDefault();
        alert('교환 사이즈는 필수 입력 값입니다.');
        window.document.querySelector('[name=exchangeSize]').focus();
        return false;
    }

    if (window.document.querySelector('[name=contact]')?.value === '') {
        e.preventDefault();
        alert('연락처는 필수 입력 값입니다.');
        window.document.querySelector('[name=contact]').focus();
        return false;
    }

    if (editorContent.getData().includes('img')) {
        window.document.querySelector('[name=thumbnailId]').value = editorContent.getData().split('id=').at(-1).split('"')[0];
    }
});


// const messageType = { // object
//     FILE: 'FILE',
//     TEXT: 'TEXT'
// };
//
// const fileDownloadFrame = window.document.getElementById('fileDownloadFrame');
//
//
// const addImg = (response) => {
//     const figure = window.document.createElement('figure');
//     let imgToWait = null;
//     figure.classList.add('image-box');
//     switch (response['type']) {
//         case messageType.TEXT:
//             figure.innerText = response['image-box'];
//             break;
//         case messageType.FILE:
//             if (response['fileType'].startsWith('image/')) {
//                 const img = window.document.createElement('img');
//                 img.classList.add('image');
//                 img.setAttribute('alt', '사진');
//                 img.setAttribute('src', `/bbs/download-image?id=${response['id']}`);
//                 figure.classList.add('image');
//                 figure.append(img);
//                 figure.addEventListener('click', () => {
//                     window.open(`/bbs/download-image?id=${response['id']}`, '_blank').focus();
//                 });
//                 imgToWait = img;
//             } else {
//                 const icon = window.document.createElement('i');
//                 const wrapper = window.document.createElement('div');
//                 const name = window.document.createElement('span');
//                 const size = window.document.createElement('span');
//                 icon.classList.add('icon', 'fa-solid', 'fa-download');
//                 name.classList.add('name');
//                 name.innerText = response['fileName'];
//                 size.classList.add('size');
//                 size.innerText = `${(response['size'] / 1048576).toLocaleString()}MB`;
//                 wrapper.classList.add('wrapper');
//                 wrapper.append(name, size);
//                 figure.classList.add('file');
//                 figure.append(icon, wrapper);
//                 figure.addEventListener('click', () => {
//                     fileDownloadFrame.setAttribute('src', `/bbs/download-image?id=${response['id']}`);
//                 });
//             }
//             break;
//     }
// }
//     uploadButton.addEventListener('click', () => {
//         imageUpload.click();
//     });
//     imageUpload.addEventListener('change', () => {
//         if (imageUpload.value === '') {
//             return;
//         }
//         const file = imageUpload.files[0];
//         if (file.size > 10 * (1024 ** 2)) {
//             alert('파일의 크기는 10.00MB를 초과할 수 없습니다.');
//             imageUpload.value = '';
//             return;
//         }
//         const xhr = new XMLHttpRequest();
//         const formData = new FormData();
//         formData.append('file', file);
//         xhr.open('POST', '/bbs/add/upload-image-button');
//         xhr.onreadystatechange = () => {
//             if (xhr.readyState === XMLHttpRequest.DONE) {
//                 if (xhr.status >= 200 && xhr.status < 300) {
//                     const response = JSON.parse(xhr.responseText);
//                     const toSend = {
//                         type: messageType.FILE,
//                         id: response['id'],
//                         fileType: response['fileType'],
//                         fileName: response['fileName'],
//                         size: response['size']
//                     };
//                     addImg(toSend);
//                 } else {
//                     alert('파일 업로드에 실패하였습니다.\n\n잠시 후 다시 시도해 주세요.');
//                 }
//             }
//         };
//         xhr.send(formData);
//     });

// const url = new URL(window.location.href);
// const urlName = url.pathname; // -> media.  url 의 쿼리 파라미터들을 읽는다. action의 값
// alert(urlName);
//
//     const content = window.document.querySelector('.ck-content');
//     const chc = window.document.createElement('p');
//     chc.innerText = 'check';
//     content.append(chc);
// ClassicEditor.editing.innerText = '            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>\n' +
//     '            <p>&bull; 사례를 약속한 회원은 사례 내용을 명확히 명시해주세요</p>\n' +
//     '            <p>> 사례 하겠습니다. &#10060</p>\n' +
//     '            <p>> 기프티콘으로 사례합니다. &#10060</p>\n' +
//     '            <p>> 스타벅스 카페라떼 Tall 기프티콘으로 사례합니다. &#11093</p>';


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

const nowDate = new Date();
// const timeOff = new Date().getTimezoneOffset()*60000;
const today = new Date(nowDate).toISOString().split("T")[0]; // -32400000
// new Date(nowDate-timeOff).toISOString() : '2022-08-16T15:12:43.351Z' "T"를 기준으로 나누고 0번째 문자열 뽑아냄.
document.getElementById("date").setAttribute("max", today);
// input date의 max 범위 값을 오늘 날짜로 제한

// UTC : 세계시 toISOString 함수는 UTC 시간을 기준으로 반환한다.
// 한국 : UTC+9 이라서 UTC 와 9시간차이의 오프셋을 가지고 있다.
// UTC 는 YYYY-MM-DD 형식의 문자열으로 반환을 하기 때문에 오프셋을 변경할 필요가 있다.
// 현재 시간과의 차이를 분 단위로 반환하는 getTimezoneOffset() 함수를 사용한다.
// 분 단위로 반환하기 때문에 기존 밀리초 단위로 인자를 받는 new Date() 함수에 넣기 위해서 1000(밀리초)*60(초) 를 곱해 밀리초 단위로 만든다.
// 이후 현재 시간과의 차이만큼 빼어 시간을 설정하면 된다.

const limitDay = new Date(nowDate.setDate(nowDate.getDate() -3));
// Wed Oct 26 2022 19:05:41 GMT+0900 (한국 표준시)
// 오늘 날짜 -3일

const firstDay = new Date(limitDay).toISOString().split("T")[0];
// '2022-10-26'
document.getElementById("date").setAttribute("min", firstDay);
// input date의 min 범위 값을 오늘 날짜 기준 3일 전으로 제한











