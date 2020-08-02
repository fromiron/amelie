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
		console.log(parent);
		console.log(parent.firstElementChild.innerText);
		console.log(parent.firstElementChild.nextElementSibling.innerText);

		console.log("course "+parent.firstElementChild.nextElementSibling.textContent);
		console.log("courseId is "+parent.firstElementChild.innerText);

		courseName.value=parent.firstElementChild.nextElementSibling.textContent;
		courseId.value=parent.firstElementChild.innerText;

	});

	}


}
