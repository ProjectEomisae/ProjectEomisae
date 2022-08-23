const commentElement = window.document.querySelector('.comment-container');
const commentMenuButton = commentElement.querySelectorAll('.menu');

commentMenuButton.forEach(x => {
    x.addEventListener('click', () => {
        Array.from(commentMenuButton).filter(el => el !== x).forEach(e => e.parentNode.querySelector(':scope > .menu-list').classList.remove('on'));
        if(!x.parentNode.querySelector('.menu-list').classList.contains('on')) {
            x.parentNode.querySelector('.menu-list').classList.add('on');
        } else {
            x.parentNode.querySelector('.menu-list').classList.remove('on');
        };
        const main = window.document.getElementById('mainContainer');
//Hide modal
        window.addEventListener('mouseup', (e) => {
            e.target === main ? x.parentNode.querySelector('.menu-list').classList.remove('on') : false
        });
    });
});
