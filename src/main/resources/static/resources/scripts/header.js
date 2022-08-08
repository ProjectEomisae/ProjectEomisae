const loginButton = document.querySelector('.top-login-button');

document.addEventListener('DOMContentLoaded', () => {
    loginButton.addEventListener('click', () => {
        document.querySelector('.shadow').classList.add('visible');
        document.querySelector('.login-form').classList.add('visible');
    });
});

window.document.querySelector('.shadow').addEventListener('click', () => {
    window.document.querySelector('.shadow').classList.remove('visible');
    window.document.querySelector('.login-form').classList.remove('visible');
});

