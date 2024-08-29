const divStageTwo = document.getElementById("stage-two");
let currentSeatsChose = new Set();
let showtimeData;
let seatsData = {};

function timeBtnFunc(button) {
    const timeBtns = document.querySelectorAll(".time-btn");
    const id = btnFunc(button, timeBtns)
    connectWS();
    $.ajax({
        url: `api/v1/showtime/get/showtimeId/${id}`,
        type: 'GET',
        success: function (response) {
            currentSeatsChose = new Set();
            totalTicketPrice = 0;
            showtimeDetailSeatShow();
            showtime = response;
            checkShowtimeHeader(showtime.id);
            showtimeDetailCinemaShow(
                showtime.cinemaName,
                showtime.auditoriumName,
                showtime.screeningDate,
                showtime.startTime,
                showtime.auditoriumType
            );
            fetchSeatsByShowtimeId(showtime.id);
        },
        error: function (xhr, status, error) {
            console.error(`Đã xảy ra lỗi: ${xhr}`);
        }
    });
}

function stageTwo() {
    getShowtimeById(showtime.id);
    showtimeDetailShowAll(showtime);
    nextBtn.classList.add("disabled");
    checkShowtimeHeader(showtime.id)
}

function checkShowtimeHeader(showtimeId) {
    const timeEl = document.querySelectorAll('.time-btn');
    timeEl.forEach(e => {
        if (e.value.toString() === showtimeId.toString()) {
            e.classList.add('active');
        }
    })
}

function getShowtimeById(showtimeId) {
    connectWS();
    $.ajax({
        url: `/booking/get/stage-two?showtimeId=${showtimeId}`,
        type: 'GET',
        success: function (htmlResponse) {
            const stageTwoHeader = document.createElement('div');
            const seatContainer = document.getElementById("seat-container");
            stageTwoHeader.innerHTML = htmlResponse;
            divStageTwo.insertBefore(stageTwoHeader, seatContainer);
            fetchSeatsByShowtimeId(showtime.id);
            checkShowtimeHeader(showtime.id);
        },
        error: function (xhr, status, error) {
            console.error(`Đã xảy ra lỗi: ${xhr}`);
            console.error('Status:', status);
            console.error('Error:', error);
        }
    });
}

function fetchSeatsByShowtimeId(showtimeId) {
    $.ajax({
        url: `/api/v1/seat/get/${showtimeId}`,
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
        let coupleDiv = null;
        let coupleCounter = 0;

        rows[row].forEach((seat) => {
            const seatBtn = document.createElement('button');

            if (seat.status === true) {
                seatBtn.innerText = `${seat.seatColumn}`;
                seatBtn.onclick = () => seatBtnFunc(seat.id);
            }

            if (seat.status === false) {
                seatBtn.innerHTML = `<i class="fa-solid fa-ban"></i>`;
                seatBtn.disabled = true;
            }

                seatBtn.className = 'seat';
                seatBtn.value = seat.id;
                seatBtn.id = `seat-${seat.id}`;

                if (seat.type === 'COUPLE') {
                    if (coupleCounter % 2 === 0) {
                        coupleDiv = document.createElement('div');
                        coupleDiv.className = 'div-seat-double';
                        rowList.appendChild(coupleDiv);

                        if (seat.status === true) {
                            coupleDiv.onclick = function () { seatCoupleBtnFunc(this); };
                        }
                    }
                    seatBtn.classList.remove('seat');
                    seatBtn.classList.add('seat-double');
                    seatBtn.onclick = null;
                    coupleDiv.appendChild(seatBtn);
                    coupleCounter++;
                } else {
                    rowList.appendChild(seatBtn);
                }

                if (seat.type === 'VIP') {
                    seatBtn.classList.add('seat-vip');
                }

        });

        rowDiv.appendChild(rowList);
        rowDiv.appendChild(rowNameE);
        seatMap.appendChild(rowDiv);
    }
    getReservation(showtime.id);
    getBlock(showtime.auditoriumId)
}

function seatCoupleBtnFunc(coupleDivEl) {
    const isActive = coupleDivEl.classList.toggle("active");
    coupleDivEl.querySelectorAll('.seat-double').forEach(seatEl => {
        seatUpdate(seatEl.value.toString(), isActive)
    })
}

function seatBtnFunc(seatId) {
    const seatEl = document.getElementById('seat-'+seatId);
    const isActive = seatEl.classList.toggle("active");

    seatUpdate(seatId, isActive);
}

function seatUpdate(value, isActive) {
    const seat = seatsData.find(e => e.id.toString() === value.toString());
    if (isActive) {
        currentSeatsChose.add(seat.id);
        upsertReservation(seat.id);
    } else {
        currentSeatsChose.delete(seat.id);
        cancelReservation(seat.id);
    }
    if (currentSeatsChose.size === 1) {
        startCountDown();
    } else if (currentSeatsChose.size === 0) {
        cancelCountDown();
    }

    checkNextBtn();
    showtimeDetailSeatShow();
}

function checkNextBtn() {
    if (currentSeatsChose.size >= 1) {
        nextBtn.classList.remove("disabled");
    } else if (currentSeatsChose.size === 0) {
        nextBtn.classList.add("disabled");
    }
}

function hideStageTwo() {
    divStageTwo.style.display = "none";
}

function showStageTwo() {
    divStageTwo.style.display = "block";
}

async function upsertReservation(seatId) {
    const data = {
        seatId: seatId,
        showtimeId: showtime.id,
    }
    try {
        let res = await axios.post("/api/v1/reservations/create", data);
    } catch (e) {
        console.error(e.message)
    }
}

async function getReservation(showtimeId) {
    try {
        let reservation = await $.ajax({
            url: `/api/v1/reservations/get/${showtimeId}`,
            type: 'GET',
        });
        console.log(reservation);

        reservation.forEach(e => {
            if (e.showtimeId === showtimeId) {
                const seat = seatsData.find(seat => seat.id.toString() === e.seatId.toString());
                const seatEl = document.getElementById(`seat-${e.seatId}`);

                if (e.status === "PENDING") {
                    if (seat.type === "COUPLE") {
                        seatEl.parentElement.classList.add('seat-hover');
                        if (e.isCurrentUser) {
                            seatEl.parentElement.classList.add('active');
                            currentSeatsChose.add(e.seatId);
                            startCountDown(e.timeRemaining);
                        }
                    } else {
                        seatEl.classList.add('seat-hover');
                        if (e.isCurrentUser) {
                            seatEl.classList.add('active');
                            currentSeatsChose.add(e.seatId);
                        }
                    }
                } else {
                    if (seat.type === "COUPLE") {
                        seatEl.parentElement.classList.add('seat-sold');
                    } else {
                        seatEl.classList.add('seat-sold');
                    }
                }
            }
        });

        checkNextBtn();
        showtimeDetailSeatShow();
    } catch (error) {
        console.error(`Đã xảy ra lỗi: ${error}`);
    }
}

async function getBlock(auditoriumId) {
    try {
        let block = await $.ajax({
            url: `/api/v1/block/get/${auditoriumId}`,
            type: 'GET',
        });

        checkNextBtn();
        showtimeDetailSeatShow();
    } catch (error) {
        console.error(`Đã xảy ra lỗi: ${error}`);
    }
}

async function cancelReservation(seatId) {
    let data = {
        seatId: seatId,
        showtimeId: showtime.id
    }
    try {
        let res = await axios.post(`/api/v1/reservations/cancel`, data);
    } catch (e) {
        console.error(e.message)
    }
}
