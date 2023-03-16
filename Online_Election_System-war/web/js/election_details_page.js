
document.querySelectorAll(".contestButton").forEach(button => {
    button.addEventListener("click", e => {
        document.querySelector("#contestSeatForm > input[name=seat_id]").value = e.target.closest(".seatCard").getAttribute("data-seatId");
        document.querySelector(".selectedSeatName").textContent = e.target.closest(".seatCard").getAttribute("data-seatName");
    })
})

document.querySelectorAll(".voteButton").forEach(button => {
    button.addEventListener("click", e => {
        document.querySelector("#voteForm > input[name=contester_id]").value = e.target.closest(".contesterCard").getAttribute("data-contesterId");
        document.querySelector(".selectedContesterTp").textContent = e.target.closest(".contesterCard").getAttribute("data-contesterTp");
    })
})

const contesterTps = [];
const contesterVotes = [];

const seatNames = [];
const seatContesterNumbers = [];

document.querySelectorAll(".contesterList").forEach(conList => {
    console.log(conList.children[0].textContent);
    contesterTps.push(conList.children[0].textContent);
    contesterVotes.push(conList.children[1].textContent);
})

document.querySelectorAll(".seatList").forEach(seatList => {
    seatNames.push(seatList.children[0].textContent);
    seatContesterNumbers.push(seatList.children[1].textContent);
})

const bar = {
    labels: contesterTps,
    datasets: [{
            label: "no. of votes",
            data: contesterVotes,
            hoverOffset: 4,
        }
    ],
};

const barConfig = {
    type: "bar",
    data: bar,
    options: {}
};

const doughnut = {
    labels: seatNames,
    datasets: [{
            label: "Seat",
            data: seatContesterNumbers,
            hoverOffset: 4,
        }
    ],
};

const doughnutConfig = {
    type: "doughnut",
    data: doughnut,
    options: {},
};


if(document.getElementById("contesterVotesBarChart") !== null) {
    const contesterVotesChart = new Chart(document.getElementById("contesterVotesBarChart"), barConfig);
}

if(document.getElementById("seatContesterNumberDoughnut") !== null) {
    const seatContesterChart = new Chart(document.getElementById("seatContesterNumberDoughnut"), doughnutConfig);
}




