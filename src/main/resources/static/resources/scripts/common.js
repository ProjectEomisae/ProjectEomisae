const showInput = document.getElementById('showInput');
const selectOption = document.getElementById('selectBox');

const hiddenWarning = {
    getElement: () => window.document.getElementById('hiddenWarning'),
    hide: () => {
        if (hiddenWarning.getElement().classList.contains('failure-text')) {
            hiddenWarning.getElement().classList.remove('failure-text');
        } else if (hiddenWarning.getElement().classList.contains('success-text')) {
            hiddenWarning.getElement().classList.remove('success-text');
        }
    },
    successShow: (text) => {
        hiddenWarning.hide();
        hiddenWarning.getElement().innerText = text;
        hiddenWarning.getElement().classList.add('success-text');
    },
    failureShow: (text) => {
        hiddenWarning.hide();
        hiddenWarning.getElement().innerText = text;
        hiddenWarning.getElement().classList.add('failure-text');
    }
};



















