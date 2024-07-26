const divStage = document.getElementById("stage");
let currentStage = 1;
const showtimeDetailMovie = document.querySelector(".ticket-movie");
const showtimeDetailCinema = document.querySelector(".ticket-cinema");
const showtimeDetailSeat = document.querySelector(".ticket-seat");
const ticketTotalPrice = document.getElementById("total-price");
const seatContainer = document.getElementById("seat-container");
const nextBtn = document.getElementById("next-page");
const prevBtn = document.getElementById("prev-page");
const seatMap = document.getElementById('seat-map');
let rows = {};
let seatsData = {};
let movieId
let auditoriumId;
let totalPrice = 0;
let totalComboPrice = 0;
let totalTicketPrice = 0;
let showtimeData;
let cityValue;

if (showtime === null) {
        stageOne();
    } else {
    movieId = showtime.movie.id;
    auditoriumId = showtime.auditorium.id;
    currentStage = 2;
    stageChange();
}

nextBtn.addEventListener('click', () => {
    if (currentStage > 5) {
        nextBtn.classList.add("disabled");
    } else {
        nextBtn.classList.remove("disabled");
    }
    currentStage++;
    stageChange();
});

prevBtn.addEventListener('click', () => {
    currentStage--;
    stageChange();
})

function stageChange() {
    divStage.innerHTML = "";
    updateBarStage();
    stageFunction()
}


function stageFunction() {
    switch (currentStage) {
        case 0:
            window.location.href = "/";
            break;
        case 1:
            clearStageTwo()
            clearStageOne()
            stageOne()
            break;
        case 2:
            stageTwo()
            break;
        case 3:
            clearStageTwo()
            stageThree()
            break
    }
}

function clearStageTwo() {
    seatContainer.classList.add("d-none");
    seatMap.innerHTML = ``;
    rows = {};
    seatsData= {};
}
function clearStageOne() {
    movieId = null;
    auditoriumId = null;
}