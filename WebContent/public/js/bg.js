const bgImage = new Array("./public/image/home1.jpg", "public/image/home2.jpg", "public/image/home3.jpg", "public/image/home4.jpg");
let randomNum = Math.floor(Math.random() * bgImage.length);

function init() {
    document.getElementById("home__bg").style.backgroundRepeat = "no-repeat";
    document.getElementById("home__bg").style.backgroundImage = "url('" + bgImage[randomNum] + "')";
}


init();