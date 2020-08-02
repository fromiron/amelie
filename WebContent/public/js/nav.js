const navList = document.querySelector(".nav__list");
const navBtn = document.querySelector(".menuDropBtn");


const menuDrop = () => {
    navList.classList.toggle("displayed")
}

function init(){
    navBtn.addEventListener("click", menuDrop);
}


init();