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
        return false;
    }
    if (editorContent.getData().includes('img')) {
        // const input = window.document.createElement('input');
        // input.setAttribute('type', 'hidden');
        // input.classList.add('inputImage');
        // input.value = editorContent.getData();
        // input.innerText = editorContent.getData();
        // form.append(input);
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


