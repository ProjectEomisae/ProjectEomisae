const moreInfoIcon = window.document.getElementById('moreInfoIcon');
const moreInfoText = window.document.getElementById('moreInfoText');

moreInfoIcon.addEventListener('mouseover', () => {
    if(!moreInfoText.classList.contains('visible')) {
        moreInfoText.classList.add('visible');
    }
});

moreInfoIcon.addEventListener('mouseout', () => {
    if(moreInfoText.classList.contains('visible')) {
        moreInfoText.classList.remove('visible');
    }
});