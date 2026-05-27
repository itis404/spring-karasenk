const nicknameField = document.getElementById("nickname");
const emailField = document.getElementById("email");
const passwordField = document.getElementById("password1");
const repeatedPasswordField = document.getElementById("password2");

const nicknameError = document.getElementById("nicknameError");
const emailError = document.getElementById("emailError");
const passwordError = document.getElementById("password1Error");
const repeatedPasswordError = document.getElementById("password2Error");
const errorField = document.getElementById("errorField");
const form = document.getElementById("registerForm");

function validateNickname(){
    if (nicknameField.value == ""){
        nicknameError.textContent = "Имя пользователя не может быть пустым.";
    }
    else {
        nicknameError.textContent = "";
    }
}

function validateEmail(){
    if (emailField.value == ""){
        emailError.textContent = "Введите почту.";
    }
    else {
        emailError.textContent = "";
    }
}

function validatePassword(){
    const regex = new RegExp("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,32}$");
    if (!regex.test(passwordField.value)){
        passwordError.textContent = "Пароль должен содержать цифры, большие и маленькие латинские буквы, не должен содержать пробелов. Длина от 8 до 32 символов.";
    }
    else {
        passwordError.style.backgroundColor = "blue"
        passwordError.textContent = "";
    }
}

function matchingPasswords(){
    if (repeatedPasswordField.value != passwordField.value){
        repeatedPasswordError.textContent = "Пароли не совпадают.";
    }
    else {
        repeatedPasswordError.textContent = "";
    }
}

function validateForm(event) {
    validateNickname();
    validateEmail();
    validatePassword();
    matchingPasswords();

    if (
        nicknameError.textContent ||
        emailError.textContent ||
        passwordError.textContent ||
        repeatedPasswordError.textContent
    ) {
        event.preventDefault();
    }
}

nicknameField.addEventListener("input", validateNickname);
passwordField.addEventListener("input", validatePassword);
repeatedPasswordField.addEventListener("input", matchingPasswords);
emailField.addEventListener("input", validateEmail);
form.addEventListener("submit", validateForm);