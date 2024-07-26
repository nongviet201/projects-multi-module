const divStage = document.getElementById("stage");
let currentStage = 1;
const showtimeDetailMovie = document.querySelector(".showtime-detail-movie");
const showtimeDetailCinema = document.querySelector(".showtime-detail-cinema");
const showtimeDetailSeat = document.querySelector(".showtime-detail-seat");
const seatContainer = document.getElementById("seat-container");
const bookingBar = document.querySelector(".booking-bar")
const barStageOne = document.querySelector(".stage-bar-one")
const barStageTwo = document.querySelector(".stage-bar-two")
const barStageThree = document.querySelector(".stage-bar-three")
const barStageFour = document.querySelector(".stage-bar-four")
const barStageFive = document.querySelector(".stage-bar-five")
const nextBtn = document.getElementById("next-page");
const prevBtn = document.getElementById("prev-page");
const seatMap = document.getElementById('seat-map');
let rows = {};
let seatsData = {};


let showtimeData;
let cityValue;

if (movieId === null && auditoriumId === null) {
    stageOne();
} else {
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
    console.log(currentStage);
    stageChange();
});

prevBtn.addEventListener('click', () => {
    divStage.innerHTML = "";
    currentStage--;
    console.log(currentStage);
    stageChange();
})

function stageChange() {
    divStage.innerHTML = "";
    updateBarStage();
    stageFunction()
}

function updateBarStage() {
    barStageOne.classList.remove("active", "activated");
    barStageTwo.classList.remove("active", "activated");
    barStageThree.classList.remove("active", "activated");
    barStageFour.classList.remove("active", "activated");
    barStageFive.classList.remove("active", "activated");

    switch (currentStage) {
        case 1:
            barStageOne.classList.add("active");
            break;
        case 2:
            barStageOne.classList.add("activated");
            barStageTwo.classList.add("active");
            break;
        case 3:
            barStageOne.classList.add("activated");
            barStageTwo.classList.remove("active");
            barStageThree.classList.add("active");
            break;
        case 4:
            barStageOne.classList.add("activated");
            barStageTwo.classList.remove("active");
            barStageThree.classList.remove("active");
            barStageFour.classList.add("active");
            break;
        case 5:
            barStageOne.classList.add("activated");
            barStageTwo.classList.remove("active");
            barStageThree.classList.remove("active");
            barStageFour.classList.remove("active");
            barStageFive.classList.add("active");
            break;
        default:
            break;
    }
}

function stageFunction() {
    switch (currentStage) {
        case 0:
            window.location.href = "/";
            break;
        case 1:
            seatContainer.classList.add("d-none");
            seatMap.innerHTML = ``;
            movieId = null;
            auditoriumId = null;
            rows = {};
            seatsData= {};
            stageOne()
            break;
        case 2:
            stageTwo()
            break;
        case 3:
            seatContainer.classList.add("d-none");
            stageThree()
            break
    }
}

function checkOut() {
    if (movieId && nextBtn.value != null) {}
}
