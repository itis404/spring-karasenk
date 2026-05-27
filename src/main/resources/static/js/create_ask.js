const uniqueNameField = document.getElementById("uniqueName");
const uniqueNameErrorField = document.getElementById("uniqueNameError");

const nameField = document.getElementById("name");
const nameErrorField = document.getElementById("nameError");

const form = document.getElementById("registerForm");

function validateName(){
    if (nameField.value == ""){
        nameErrorField.textContent = "Название не может быть пустым";
    }
    else {
        nameErrorField.textContent = "";
    }
}

function validateUniqueName(){
    const regex = new RegExp("^[A-Za-z0-9_]{3,64}$");
    if (!regex.test(uniqueNameField.value)) {
        uniqueNameErrorField.textContent = "В коротком имени должны использоваться только латинские буквы, арабские цифры и _. Длина от 3 до 64 символов."
    }
}

function validateForm(){
    if (nameErrorField.textContent || uniqueNameErrorField.textContent){
        event.preventDefault();
    }
}

nameField.addEventListener("input", validateName());
uniqueNameField.addEventListener("input", validateUniqueName());
form.addEventListener("submit", validateForm);