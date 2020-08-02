

function dateMinMaxHandle() {
	console.log("date js running");
	const dateFrom = document.querySelector("#dateForm");

	let today =new Date();
	let max =new Date();

	function todayfunc(){
	let yyyy = today.getFullYear();
	let mm = today.getMonth() + 1;
	let dd = today.getDate();
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}
	today = yyyy + '-' + mm + '-' + dd;
	}


	function maxfunc(){
	let yyyy = max.getFullYear();
	yyyy +=1;
	let mm = max.getMonth() + 1;
	let dd = max.getDate();
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}
	max = yyyy + '-' + mm + '-' + dd;
	}

	todayfunc();
	maxfunc();

	dateFrom.setAttribute("min", today);
	dateFrom.setAttribute("max", max);
	console.log(today);
	console.log(max);

}

	dateMinMaxHandle();

