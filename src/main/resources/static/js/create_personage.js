const uniqueNameField = document.getElementById("uniqueName");
const uniqueNameErrorField = document.getElementById("uniqueNameError");

const nameField = document.getElementById("name");
const nameErrorField = document.getElementById("nameError");

const form = document.getElementById("personageForm");

function validateName() {
    nameField.value = nameField.value.trim();
    if (nameField.value == "") {
        nameErrorField.textContent = "Имя не может быть пустым";
    } else {
        nameErrorField.textContent = "";
    }
}

function validateUniqueName() {
    uniqueNameField.value = uniqueNameField.value.trim();
    const regex = RegExp("^[A-Za-z0-9_]{3,64}$");
    if (!regex.test(uniqueNameField.value)) {
        uniqueNameErrorField.textContent =
            "В коротком имени могут использоваться только латинские буквы, цифры и _. Длина от 3 до 64 символов.";
    } else {
        uniqueNameErrorField.textContent = "";
    }
}

function validateForm(event) {
    validateName();
    validateUniqueName();
    if (
        nameErrorField.textContent !== "" ||
        uniqueNameErrorField.textContent !== ""
    ) {
        event.preventDefault();
    }
}

nameField.addEventListener("input", validateName);
uniqueNameField.addEventListener("input", validateUniqueName);
form.addEventListener("submit", validateForm);