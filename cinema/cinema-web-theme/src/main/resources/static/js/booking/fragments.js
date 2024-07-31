const stages = document.querySelectorAll('.booking-bar ul li');
const showtimeDetailCinema = document.querySelector(".ticket-cinema");

function updateBarStage() {
    stages.forEach(stage => {
        stage.classList.remove('active', 'activated');
        const processLine = stage.querySelector('.process-line');
        if (processLine) {
            processLine.classList.remove('active', 'activated');
        }
    });

    for (let i = 0; i < stages.length; i++) {
        const stage = stages[i];
        const processLine = stage.querySelector('.process-line');

        if (i < currentStage - 1) {
            stage.classList.add('activated');
            if (processLine) {
                processLine.classList.add('activated');
            }
        } else if (i === currentStage - 1) {
            stage.classList.add('active');
            if (processLine) {
                processLine.classList.add('active');
            }
        }
    }
}

function showtimeDetailMovieShow(poster, name) {
    const showtimeDetailMovie = document.querySelector(".ticket-movie");
    showtimeDetailMovie.innerHTML = `
        <div class="col-lg-4">
            <img src="${poster}"
                 alt="${name}">
        </div>
        <div class="col-lg-8 ps-3">
            <strong>${name}</strong>
        </div>
    `
}

function showtimeDetailCinemaShow(cinemaName, auditoriumName, startDate, startTime) {
    showtimeDetailCinema.innerHTML = `
         <div>
            <strong>${cinemaName}</strong>
            <span> - </span>
            <span>${auditoriumName}</span>
        </div>
        <div class="fs-14px mt-2">
            <span>Suất: </span>
            <strong>${startTime}</strong>
            <span> - </span>
            <strong>${startDate}</strong>
        </div>
    `
}

function showtimeDetailSeatShow() {
    const seatName = document.getElementById("seat-name");
    const showtimeDetailSeat = document.querySelector(".ticket-seat");
    numberSeatsChose.innerText = currentSeatsChose.size;
    if (currentSeatsChose.size > 0) {
        showtimeDetailSeat.classList.remove("d-none");
        seatName.innerHTML = "";
        currentSeatsChose.forEach(seatId => {
            let seat = seatsData.find(e => e.id === seatId);
            const seatNameValue = document.createElement('span');
            seatNameValue.innerText = `${seat.seatRow}${seat.seatColumn}, `;
            seatNameValue.id = `${seat.seatRow}${seat.seatColumn}`;
            seatName.appendChild(seatNameValue);

            const seatPrice = document.getElementById("seat-price");
            seatPrice.innerText = `${totalTicketPrice}đ`;
            ticketTotalPriceShow();
        });
    } else {
        showtimeDetailSeat.classList.add("d-none");
        seatName.innerHTML = "";
        ticketTotalPriceShow();
    }
}

function ticketTotalPriceShow() {
    totalPrice = totalComboPrice + totalTicketPrice;
    if (ticketTotalPrice) {
        ticketTotalPrice.innerText = `${totalPrice}`;
    }
}

function ticketDetailComboHide() {
    const comboDetails = document.querySelector('.combo-detail');
    const ticketCombo = document.getElementById('ticket-combo');
    comboDetails.innerHTML = "";
    ticketCombo.classList.add('d-none');
    totalComboPrice = 0;
    ticketTotalPriceShow();
}

function showtimeDetailShowAll(showtime) {
    showtimeDetailMovieShow(
        showtime.movie.poster,
        showtime.movie.name
    );
    showtimeDetailCinemaShow(
        showtime.auditorium.cinema.name,
        showtime.auditorium.name,
        showtime.screeningDate,
        showtime.startTime
    );
}

function showtimeDetailCinemaHide() {
    showtimeDetailCinema.innerHTML = "";
}