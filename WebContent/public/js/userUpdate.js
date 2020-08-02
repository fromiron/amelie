const
pass1 = document.querySelector("#password");
const
pass2 = document.querySelector("#password2");
const
userId = document.querySelector("#userId");
const
userName = document.querySelector("#userName");
const
address = document.querySelector("#address");
const
mail = document.querySelector("#mail");
const
tel = document.querySelector("#tel");
const
form = document.querySelector(".join__submit");
const
formSumitForm = document.querySelector(".join_form__form");

function valueCheck(formDataId) {
	if (formDataId.value === "") {
		formDataId.focus();
		formDataId.style.backgroundColor = "#bab9bd";
		formSumitForm.preventDefault();

		return false;
	} else {
		formDataId.style.backgroundColor = "white";
		return true;
	}
}
function passCheck() {
	if (pass1.value !== pass2.value || pass1.value === "" || pass1.value === "") {
		pass1.focus()
		pass1.style.backgroundColor = "#bab9bd";
		pass2.style.backgroundColor = "#bab9bd";
		return false;
	} else {
		pass1.style.backgroundColor = "white";
		pass2.style.backgroundColor = "white";
		return true;
	}
}

function formCheckHandle() {

	if (passCheck() == true
			&& valueCheck(address) == true
			&& valueCheck(mail) == true && valueCheck(tel) == true) {

		formSumitForm.submit();

	} else {
		formSumitForm.preventDefault();
	}
}

window.onload = function() {
	form.onclick = function() {

		formCheckHandle();

	};

}
