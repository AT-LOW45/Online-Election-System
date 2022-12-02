
const toast = document.querySelector(".toast");
const hideToastButton = document.querySelector(".hideToastButton");

hideToastButton !== null && hideToastButton.addEventListener("click", () => {
    toast.style.top = "-50%"
})

setTimeout(() => {
    if(toast !== null && toast.style.top === "5%") {
        toast.style.top = "-30%"
    }
}, 5000)