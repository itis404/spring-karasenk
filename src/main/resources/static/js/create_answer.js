let count = 1;
const maxFiles = 20;

function addFileInput() {

    if (count >= maxFiles) {
        alert("Лимит " + maxFiles + " картинок");
        return;
    }
    count++;
    const div = document.createElement("div");
    div.innerHTML =
        '<input type="file" name="images" accept="image/*"/>' +
        '<button type="button" onclick="removeInput(this)">Удалить</button>';
    document.getElementById("file-container").appendChild(div);
}

function removeInput(button) {
    button.parentElement.remove();
    count--;
}