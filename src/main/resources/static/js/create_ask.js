const uniqueNameField = document.getElementById("uniqueName");
const uniqueNameErrorField = document.getElementById("uniqueNameError");

const nameField = document.getElementById("name");
const nameErrorField = document.getElementById("nameError");

const form = document.getElementById("askForm");

function validateName(){
    nameField.value = nameField.value.trim();
    if (nameField.value == ""){
        nameErrorField.textContent = "Название не может быть пустым";
    }
    else {
        nameErrorField.textContent = "";
    }
}

function validateUniqueName(){
    uniqueNameField.value = uniqueNameField.value.trim();
    const regex = new RegExp("^[A-Za-z0-9_]{3,64}$");
    if (!regex.test(uniqueNameField.value)) {
        uniqueNameErrorField.textContent = "В коротком имени должны использоваться только латинские буквы, арабские цифры и _. Длина от 3 до 64 символов."
    }
    else {
        uniqueNameErrorField.textContent = "";
    }
}

function validateForm(){
    validateName();
    validateUniqueName();
    if (nameErrorField.textContent || uniqueNameErrorField.textContent){
        event.preventDefault();
    }
}

nameField.addEventListener("blur", validateName);
uniqueNameField.addEventListener("blur", validateUniqueName);
form.addEventListener("submit", validateForm);