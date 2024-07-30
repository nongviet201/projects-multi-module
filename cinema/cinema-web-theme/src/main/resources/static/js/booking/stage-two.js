let rows = {};
let seatsData = {};
const divStageTwo = document.getElementById("stage-two");
let currentSeatsChose = new Set();
const numberSeatsChose = document.getElementById("number-seats-chose");
const seatName = document.getElementById("seat-name");
let totalTicketPrice = 0;
let showtimeData;

function stageTwo() {
    fetchTimeShowtimesByMovieAndAuditorium(movieId, auditoriumId);
    fetchSeatsByAuditoriumId(auditoriumId);
    showtimeDetailShowAll(showtime);
    nextBtn.classList.add("disabled");
}

function fetchTimeShowtimesByMovieAndAuditorium(movieId, auditoriumId) {
    $.ajax({
        url: `/booking/get/stage-two?movieId=${movieId}&auditoriumId=${auditoriumId}`,
        type: 'GET',
        success: function (htmlResponse) {
            const stageTwoHeader = document.createElement('div');
            const seatContainer = document.getElementById("seat-container");
            stageTwoHeader.innerHTML = htmlResponse;
            divStageTwo.insertBefore(stageTwoHeader, seatContainer);
        },
        error: function (xhr, status, error) {
            console.error(`Đã xảy ra lỗi: ${xhr}`);
        }
    });
}

function fetchSeatsByAuditoriumId(auditoriumId) {
    $.ajax({
        url: `/api/v1/seat/get/${auditoriumId}`,
        type: 'GET',
        success: function (response) {
            seatsData = response;
            renderSeats(seatsData);
        },
        error: function (xhr, status, error) {
            console.error(`Đã xảy ra lỗi: ${xhr}`);
        }
    });
}

function renderSeats(seats) {
    const seatContainer = document.getElementById("seat-container");
    seatContainer.classList.remove("d-none");

    seats.forEach(seat => {
        const row = seat.seatRow;
        if (!rows[row]) {
            rows[row] = [];
        }
        rows[row].push(seat);
    });

    for (const row in rows) {
        const rowDiv = document.createElement('div');
        const rowNameS = document.createElement('div');
        const rowNameE = document.createElement('div');
        const rowList = document.createElement('div');

        rowDiv.className = 'seat-row';
        rowNameS.innerText = row;
        rowNameE.innerText = row;
        rowDiv.appendChild(rowNameS);

        rowList.className = 'seat-list';
        rows[row].forEach((seat, index) => {
            const seatBtn = document.createElement('button');
            seatBtn.className = 'seat';
            seatBtn.value = seat.id;
            seatBtn.id = `seat-${seat.id}`;
            seatBtn.innerText = index + 1;
            seatBtn.onclick = () => seatBtnFunc(seat.id);
            rowList.appendChild(seatBtn);
        });

        rowDiv.appendChild(rowList);
        rowDiv.appendChild(rowNameE);
        document.getElementById('seat-map').appendChild(rowDiv);
    }
}

function seatBtnFunc(seatId) {
    const seat = seatsData.find(e => e.id === seatId);
    const seatEl = document.getElementById(`seat-${seat.id}`);
    const isActive = seatEl.classList.toggle("active");

    if (isActive) {
        currentSeatsChose.add(seat.id);
        totalTicketPrice += seat.type.price;
    } else {
        currentSeatsChose.delete(seat.id);
        totalTicketPrice -= seat.type.price;
    }

    if (currentSeatsChose.size > 0) {
        nextBtn.classList.remove("disabled");
    } else {
        nextBtn.classList.add("disabled");
    }
    showtimeDetailSeatShow();
}

function hideStageTwo() {
    divStageTwo.style.display = "none";
}

function showStageTwo() {
    divStageTwo.style.display = "block";
}
