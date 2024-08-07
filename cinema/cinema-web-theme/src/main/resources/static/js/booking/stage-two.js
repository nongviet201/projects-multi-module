const divStageTwo = document.getElementById("stage-two");
let currentSeatsChose = new Set();
const numberSeatsChose = document.getElementById("number-seats-chose");
let showtimeData;
let seatsData = {};

function timeBtnFunc(button) {
    const timeBtns = document.querySelectorAll(".time-btn");
    const id = btnFunc(button, timeBtns)
    $.ajax({
        url: `api/v1/showtime/get/showtimeId/${id}`,
        type: 'GET',
        success: function (response) {
            currentSeatsChose = new Set();
            totalTicketPrice = 0;
            showtimeDetailSeatShow();
            showtime = response;
            showtimeDetailCinemaShow(
                showtime.auditorium.cinema.name,
                showtime.auditorium.name,
                showtime.screeningDate,
                showtime.startTime
            );
            fetchSeatsByAuditoriumId(showtime.auditorium.id);
        },
        error: function (xhr, status, error) {
            console.error(`Đã xảy ra lỗi: ${xhr}`);
        }
    });
}

function stageTwo() {
    fetchTimeShowtimesByMovieAndAuditorium(showtime.id);
    showtimeDetailShowAll(showtime);
    nextBtn.classList.add("disabled");
}

function fetchTimeShowtimesByMovieAndAuditorium(showtimeId) {
    $.ajax({
        url: `/booking/get/stage-two?showtimeId=${showtimeId}`,
        type: 'GET',
        success: function (htmlResponse) {
            const stageTwoHeader = document.createElement('div');
            const seatContainer = document.getElementById("seat-container");
            stageTwoHeader.innerHTML = htmlResponse;
            divStageTwo.insertBefore(stageTwoHeader, seatContainer);
            fetchSeatsByAuditoriumId(showtime.auditorium.id);
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
            seatsData = {};
            seatsData = response;
            renderSeats(seatsData);
        },
        error: function (xhr, status, error) {
            console.error(`Đã xảy ra lỗi: ${xhr}`);
        }
    });
}

function renderSeats(seats) {
    document.getElementById("seat-container").classList.remove("d-none");
    const seatMap = document.getElementById('seat-map');
    seatMap.innerHTML = "";
    let rows = {};
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
        rows[row].forEach((seat) => {
            const seatBtn = document.createElement('button');
            seatBtn.className = 'seat';
            seatBtn.value = seat.id;
            seatBtn.id = `seat-${seat.id}`;
            seatBtn.innerText = `${seat.seatColumn}`;
            seatBtn.onclick = () => seatBtnFunc(seat.id);
            rowList.appendChild(seatBtn);
        });

        rowDiv.appendChild(rowList);
        rowDiv.appendChild(rowNameE);
        seatMap.appendChild(rowDiv);
    }
}

function seatBtnFunc(seatId) {
    const seat = seatsData.find(e => e.id === seatId);
    const seatEl = document.getElementById(`seat-${seat.id}`);
    const isActive = seatEl.classList.toggle("active");

    if (isActive) {
        currentSeatsChose.add(seat.id);
        totalTicketPrice += seat.price;
        upsertReservation(seat.id)
    } else {
        currentSeatsChose.delete(seat.id);
        totalTicketPrice -= seat.price
        cancelReservation(seat.id)
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

async function upsertReservation(seatId) {
    const data = {
        userId: 1,
        seatId: seatId,
        showtimeId: showtime.id,
    }

    try {
        let res = await axios.post("/api/v1/reservations/create", data);
        const seatEl = document.getElementById(`seat-${seatId}`);
        seatEl.dataset.reservationId = res.data.id;
    } catch (e) {
        console.log(e)
    }
}

async function cancelReservation(seatId) {
    const seatEl = document.getElementById(`seat-${seatId}`);
    const reservationId = seatEl.dataset.reservationId;
    try {
        let res = await axios.delete(`/api/v1/reservations/cancel/${reservationId}`);
        seatEl.dataset.reservationId = "";
        console.log('đã xóa');
    } catch (e) {
        console.log(e)
    }
}

