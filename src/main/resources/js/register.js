const nicknameField = document.getElementById("nickname");
const emailField = document.getElementById("email");
const passwordField = document.getElementById("password1");
const repeatedPasswordField = document.getElementById("password2");

fetch("/user/123")
    .then(response => {
        if (!response.ok) {
            return response.json().then(err => {
                throw err;
            });
        }
        return response.json();
    })
    .then(data => {
        console.log("User:", data);
    })
    .catch(error => {
        console.log("Ошибка:", error);
        alert(error.message);
    });

function validateNickname(){
    if (nicknameField.value == ""){
        alert("Имя пользователя не может быть пустым.")
    }
}

function validateEmail(){
    if (emailField.value == ""){
        alert("Введите почту.")
    }
}

function validatePassword(){
    const regex = "^(?=.*[0-9])" + // содержит цифры
                  "(?=.*[a-z])" + // содержит маленькие буквы
                  "(?=.*[A-Z])" + // содержит большие буквы
                  "(?=\\S+$)" + // не содержит пробел
                  ".{8,32}$"; // от 8 до 32 символов
    if (!regex.test(passwordField.value)){
        alert("Пароль должен содержать цифры, большие и маленькие латинские буквы, не должен содержать пробелов. Длина от 8 до 32 символов.")
        return false;
    }
}

function matchingPasswords(){
    if (repeatedPasswordField.value != passwordField.value){
        alert("Пароли не совпадают.")
    }
}

nicknameField.addEventListener("input", validateNickname);
passwordField.addEventListener("input", validatePassword);
repeatedPasswordField.addEventListener("input", matchingPasswords);
emailField.addEventListener("input", validateEmail);