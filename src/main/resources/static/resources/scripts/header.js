const menuWrap = window.document.querySelector('.menu-wrap');
const menuItem = menuWrap.querySelectorAll('.item');
menuItem.forEach(x => {
    if (x.ariaSelected === 'true') {
        x.classList.add('selected');
    }
});

if (menuWrap.querySelectorAll('.link').forEach(ex => {
    if (ex.ariaSelected === 'true') {
        ex.parentNode.parentNode.classList.add('selected');
    }
}));

// x.querySelectorAll(':scope > .list > li').forEach(ex => {
//     if (ex.ariaSelected === 'true') ;
//     x.querySelector('.item').classList.add('selected');

const loginButton = document.querySelector('.top-login-button');

loginButton.addEventListener('click', () => {
    window.document.querySelector('.shadow').classList.add('visible');
    window.document.querySelector('.login-form').classList.add('visible');
});

window.document.querySelector('.shadow').addEventListener('click', () => {
    window.document.querySelector('.shadow').classList.remove('visible');
    window.document.querySelector('.login-form').classList.remove('visible');
});

const loginButtonMobile = document.querySelector('.top-login-button-mobile');

loginButtonMobile.addEventListener('click', () => {
    window.document.querySelector('.shadow').classList.add('visible');
    window.document.querySelector('.login-form').classList.add('visible');
});

window.onscroll = () => {
    myFunction();
};

function myFunction() {
    const winScroll = document.body.scrollTop || document.documentElement.scrollTop;
    // (0 ~ 553)
    const height = document.documentElement.scrollHeight - document.documentElement.clientHeight; // 553
    const scrolled = (winScroll / height) * 100;
    document.getElementById("myBar").style.width = `${scrolled}%`;
}

// ((document.body.scrollTop || document.documentElement.scrollTop) / (document.documentElement.scrollHeight - document.documentElement.clientHeight)) * 100;

// window.document.documentElement.scrollTop 현재 top의 위치 (0 ~ 553)
// window.innerHeight 807 == document.documentElement.clientHeight 807
// document.documentElement.scrollHeight 1360
// ( 스크롤 된 높이+ 보고있는화면(viewport)높이 ) / 전체문서높이 * 100


// https://ko.javascript.info/size-and-scroll-window
// let scrollHeight = Math.max(
//     document.body.scrollHeight, document.documentElement.scrollHeight,
//     document.body.offsetHeight, document.documentElement.offsetHeight,
//     document.body.clientHeight, document.documentElement.clientHeight
// );

// alert('스크롤에 의해 가려진 분을 포함한 전체 문서 높이: ' + scrollHeight);
// alert('세로 스크롤에 의해 가려진 위쪽 영역 높이: ' + window.pageYOffset);
// alert('가로 스크롤에 의해 가려진 왼쪽 영역 너비: ' + window.pageXOffset);

const toggleButton = window.document.getElementById('toggle-container');

toggleButton.addEventListener('click', () => {
    window.document.getElementById('toggle-menu-container').classList.add('on');
});

const toggleBox = window.document.getElementById('toggle-menu-container');
const menuButton = toggleBox.querySelectorAll('.menu-button');

menuButton.forEach(x => {
    x.addEventListener('click', () => {
        if (!x.classList.contains('on')) {
            x.classList.add('on');
            x.parentNode.parentNode.querySelector('.list').classList.add('on');
        } else {
            x.classList.remove('on');
            x.parentNode.parentNode.querySelector('.list').classList.remove('on');
        }
    });
});

window.document.querySelector('#main').addEventListener('click', () => {
    toggleBox.classList.remove('on');
});


toggleBox.querySelector('.my-container').addEventListener('click', () => {
    toggleBox.classList.remove('on');
    document.querySelector('.shadow').classList.add('visible');
    document.querySelector('.login-form').classList.add('visible');
});

window.document.querySelector('.shadow').addEventListener('click', () => {
    window.document.querySelector('.shadow').classList.remove('visible');
    window.document.querySelector('.login-form').classList.remove('visible');
});

window.document.querySelector('.search-button').addEventListener('click', () => {
    window.document.querySelector('.keyword-input-container').classList.add('on');
});

window.document.querySelector('.exit-box').addEventListener('click', () => {
    window.document.querySelector('.keyword-input-container').classList.remove('on');
});

const messageContainer = window.document.querySelector('.message-container');

window.document.querySelector('.message-button').addEventListener('click', () => {
    userMenuButton.querySelector('.menu-box').classList.remove('on');
    if (!messageContainer.classList.contains('on')) {
        messageContainer.classList.add('on');
    } else {
        messageContainer.classList.remove('on');
    }
});

const userMenuButton = window.document.querySelector('.user-info-container');

userMenuButton.addEventListener('click', () => {
    messageContainer.classList.remove('on');
    if (!userMenuButton.querySelector('.menu-box').classList.contains('on')) {
        userMenuButton.querySelector('.menu-box').classList.add('on');
    } else {
        userMenuButton.querySelector('.menu-box').classList.remove('on');
    }
});

const scrollButton = window.document.querySelector('.scroll-button');
window.addEventListener('scroll', () => {
    if (document.documentElement.scrollTop > 500) {
        scrollButton.classList.add('on');
    } else {
        scrollButton.classList.remove('on');
    }
});

scrollButton.addEventListener('click', () => {
    scrollTo(document.documentElement, 0, 400);
    return false;
});

function scrollTo(element, to, duration) {
    let start = element.scrollTop,
        change = to - start,
        currentTime = 0,
        increment = 20;

    let animateScroll = function () {
        currentTime += increment;
        let val = Math.easeInOutQuad(currentTime, start, change, duration);
        element.scrollTop = val;
        if (currentTime < duration) {
            setTimeout(animateScroll, increment);
        }
    };
    animateScroll();
}

//t = current time
//b = start value
//c = change in value
//d = duration
Math.easeInOutQuad = function (t, b, c, d) {
    t /= d / 2;
    if (t < 1) return c / 2 * t * t + b;
    t--;
    return -c / 2 * (t * (t - 2) - 1) + b;
};

