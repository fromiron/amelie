const closeBtn = document.querySelector(".closeBtn");
const homeMsgBox = document.querySelector(".home__msg_box");
const slide = document.querySelector("#slider_wrap");

const mgsClose = () => {
    homeMsgBox.classList.add("disable");
    setTimeout(function () {
        homeMsgBox.style.display = "none";
        slide.style.display = "inline";
    }, 1000);

}


function init() {
    closeBtn.addEventListener("click", mgsClose);
}


init();