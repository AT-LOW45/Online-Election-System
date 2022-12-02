
const dropToggle = document.querySelector(".toggleDrop");
const tpForm = document.querySelector(".tpForm")

tpForm.addEventListener('click', (e) => {
    e.stopPropagation()
})

document.body.addEventListener("click", (e) => {
    if (!e.target.classList.contains("toggleDrop") && tpForm.style.opacity === "1") {
        tpForm.style.visibility = "hidden";
        tpForm.style.opacity = "0";
        tpForm.style.left = "105%"
    }
})

dropToggle.addEventListener("click", () => {
    if (tpForm.style.opacity === "1") {
        tpForm.style.visibility = "hidden";
        tpForm.style.opacity = "0";
        tpForm.style.left = "105%"
    } else {
        tpForm.style.visibility = "visible";
        tpForm.style.opacity = "1";
        tpForm.style.left = "110%"
    }
})

