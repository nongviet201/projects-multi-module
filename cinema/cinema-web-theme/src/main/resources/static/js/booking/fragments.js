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

function showtimeDetailMovieShow(poster, name, movieAgeRequirement) {
    const showtimeDetailMovie = document.querySelector(".ticket-movie");
    showtimeDetailMovie.innerHTML = `
        <div class="col-lg-4">
            <img src="${poster}"
                 alt="${name}">
        </div>
        <div class="col-lg-8 ps-3">
            <strong>${name}</strong>
            <div class="mt-2">
            <span class="ticket-movie-auditorium-type fs-14px"></span>
            <span class="fw-700"> - </span>
            <span class="bg-orange fw-700 fs-14px text-white py-1 px-2" style="border-radius: 3px">T${movieAgeRequirement}</span>
            </div> 
        </div>
    `
}

function showtimeDetailCinemaShow(cinemaName, auditoriumName, startDate, startTime, auditoriumType) {
    function getCurrentYear() {
        const now = new Date();  // Tạo một đối tượng Date mới với thời gian hiện tại
        return now.getFullYear(); // Lấy năm từ đối tượng Date
    }
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
            <strong>${startDate}/${getCurrentYear()}</strong>
        </div>
    `
    document.querySelector(".ticket-movie-auditorium-type").innerHTML = `${auditoriumType} `;
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

            const seatType = document.getElementById("seat-type");
            seatType.innerText = `${seat.type}`
            const seatPrice = document.getElementById("seat-price");
            seatPrice.innerText = formatPrice(totalTicketPrice) + `đ`;
            ticketTotalPriceShow();
        });
    } else {
        showtimeDetailSeat.classList.add("d-none");
        seatName.innerHTML = "";
        ticketTotalPriceShow();
    }
}

function ticketTotalPriceShow() {
    const ticketTotalPrice = document.getElementById("total-price");
    totalPrice = totalComboPrice + totalTicketPrice;
    if (ticketTotalPrice) {
        ticketTotalPrice.innerText = formatPrice(totalPrice);
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
        showtime.moviePoster,
        showtime.movieName,
        showtime.ageRequirement
    );
    showtimeDetailCinemaShow(
        showtime.cinemaName,
        showtime.auditoriumName,
        showtime.screeningDate,
        showtime.startTime,
        showtime.auditoriumType
    );
}

function showtimeDetailCinemaHide() {
    showtimeDetailCinema.innerHTML = "";
    document.querySelector(".ticket-movie-auditorium-type").innerHTML = ``;
}

function formatPrice(number) {
    return number.toLocaleString('vi-VN');
}
