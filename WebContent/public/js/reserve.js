function fillWhite(){
	const otherTarget =  document.querySelectorAll(".reserveCourse");

	for(i=0; i<otherTarget.length; i++){
		otherTarget[i].classList.remove("selected");
	}
}

window.onload = function() {


	const reserveCourse =  document.querySelectorAll(".menu_list__menu_title");

	const courseId =  document.getElementById("courseId");
	const courseName =  document.getElementById("courseName");
	const msgBox = document.querySelector("#reserve__msg");

for(i =0; i<reserveCourse.length;i++){
	reserveCourse[i].addEventListener('click', e => {
	    fillWhite();
	    parent = e.currentTarget;

        parent.classList.add("selected");
		courseName.value=parent.firstElementChild.nextElementSibling.textContent;
		courseId.value=parent.firstElementChild.innerText;

	});

	}


}
