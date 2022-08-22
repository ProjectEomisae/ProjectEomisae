ClassicEditor
    .create(window.document.getElementById('editor'), {
        simpleUpload: {
            uploadUrl: '/product/add/upload-image'
        }
    }).then(editor => {
    editor.editing.view.change(writer => {
        writer.setStyle('min-height', '30vh', editor.editing.view.document.getRoot());
        writer.setStyle('max-height', '30vh', editor.editing.view.document.getRoot());
    });
});

const fileUploadContainer = window.document.getElementById('fileUploadContainer');
const hoverChangeText = window.document.getElementById('hoverChangeText');

fileUploadContainer.addEventListener('mouseover', () => {
    hoverChangeText.innerHTML = "파일 크기 제한 : 2.00MB (허용 확장자 : *.*)";
});

fileUploadContainer.addEventListener('mouseout', () => {
    hoverChangeText.innerHTML = "여기에 파일을 끌어 놓거나 파일 첨부 버튼을 클릭하세요.";
});
