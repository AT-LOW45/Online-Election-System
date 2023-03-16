
const approveButton = document.querySelectorAll(".approveUserButton")
const updateButton = document.querySelectorAll(".updateUserButton")
const deleteButton = document.querySelectorAll(".deleteUserButton")

approveButton.forEach(button => {
    button.addEventListener("click", (e) => {
        const modalIdInput = document.querySelector("#approveUserForm > input[name=user_id]");
        modalIdInput.value = e.target.parentElement.firstElementChild.value;
        document.querySelector(".approveTpNumber").textContent = e.target.parentElement.children[1].value;
    })
})

deleteButton.forEach(button => {
    button.addEventListener("click", (e) => {
        const modalIdInput = document.querySelector("#deleteUserForm > input[name=user_id]");
        const modalRoleInput = document.querySelector("#deleteUserForm > input[name=role]");
        modalIdInput.value = e.target.parentElement.firstElementChild.value;
        modalRoleInput.value = e.target.parentElement.children[2].value;
        document.querySelector(".deleteTpNumber").textContent = e.target.parentElement.children[1].value;
    })
})

updateButton.forEach(button => {
    button.addEventListener("click", (e) => {
        const modalIdInput = document.querySelector("#updateUserForm input[name=user_id]");
        modalIdInput.value = e.target.parentElement.firstElementChild.value;
        document.querySelector(".updateTpNumber").textContent = e.target.parentElement.children[1].value;
    })
})

