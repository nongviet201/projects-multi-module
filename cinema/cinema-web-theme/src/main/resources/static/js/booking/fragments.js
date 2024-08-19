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
            <span class="bg-orange fw-700 fs-14px text-white py-1 px-2" style="border-radius: 3px">${movieAgeRequirement}</span>
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
    const ticketSeat = document.querySelector(".ticket-seat");
    const seatContainer = ticketSeat.querySelector(".seat-container");
    const dashLine = ticketSeat.querySelector(".dash-line");

    seatContainer.innerHTML = "";

    totalTicketPrice = 0;

    if (currentSeatsChose.size > 0) {
        ticketSeat.classList.remove("d-none");

        const seatGroups = {
            "VIP": [],
            "NORMAL": [],
            "COUPLE": []
        };

        currentSeatsChose.forEach(seatId => {
            let seat = seatsData.find(e => e.id === seatId);
            seatGroups[seat.type].push(seat);
        });

        Object.keys(seatGroups).forEach(type => {
            if (seatGroups[type].length > 0) {
                let typeSeats = seatGroups[type];
                let seatNames = typeSeats.map(seat => `${seat.seatRow}${seat.seatColumn}`).join(', ');
                let seatPrice;

                seatPrice = typeSeats.reduce((acc, seat) => acc + seat.price, 0);

                totalTicketPrice += seatPrice;

                let typeContainer = document.createElement('div');
                typeContainer.classList.add('d-flex', 'justify-content-between');

                // Hiển thị số lượng ghế đã chọn
                let seatTypeText = document.createElement('span');
                seatTypeText.classList.add('fs-14px', 'fw-700');
                seatTypeText.innerHTML = `${typeSeats.length} <span class="fw-600"> x ${getSeatTypeText(type)}</span>`;

                let seatPriceText = document.createElement('span');
                seatPriceText.classList.add('text-end', 'fw-700');
                seatPriceText.innerText = `${formatPrice(seatPrice)} đ`;

                // Tạo phần danh sách ghế
                let seatListRow = document.createElement('div');
                seatListRow.classList.add('mb-3', 'fs-14px'); // Bootstrap class for muted text
                seatListRow.innerHTML = `Ghế: <span class="fw-bold">${seatNames}</span>`;

                // Thêm các phần tử vào container
                typeContainer.appendChild(seatTypeText);
                typeContainer.appendChild(seatPriceText);
                seatContainer.appendChild(typeContainer);
                seatContainer.appendChild(seatListRow);
                ticketTotalPriceShow();
            }
        });

        dashLine.classList.remove("d-none");
        ticketTotalPriceShow();
    } else {
        ticketSeat.classList.add("d-none");
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

function ticketDiscountPrice() {
    if (points > 0) {
        const discount = points * 1000;
        oldTotalPrice = totalTicketPrice + totalComboPrice;
        
        const oldPriceEl = document.getElementById('old-price');
        oldPriceEl.innerText = formatPrice(oldTotalPrice);
        oldPriceEl.parentElement.classList.remove('d-none');

        const discountPriceEl = document.getElementById('discount-price');
        discountPriceEl.innerHTML = `- ${formatPrice(discount)}` ;
        discountPriceEl.parentElement.classList.remove('d-none')
        
        totalPrice = (totalComboPrice + totalTicketPrice) - discount;
        document.getElementById("total-price").innerText = formatPrice(totalPrice);
    }
}

function getSeatTypeText(type) {
    switch (type) {
        case 'VIP':
            return 'Ghế VIP';
        case 'NORMAL':
            return 'Ghế Đơn';
        case 'COUPLE':
            return 'Ghế Đôi';
        default:
            return 'lỗi';
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


let timerInterval;
let targetTime;

function startCountDown(timeRemaining) {
    if (timeRemaining != null) {
        targetTime = new Date().getTime() + timeRemaining;
    } else {
        targetTime = new Date().getTime() + 600000; // Default 10 minutes
    }

    document.getElementById('countdown').classList.remove('d-none');

    if (timerInterval) {
        clearInterval(timerInterval);
    }
    timerInterval = setInterval(updateTimer, 1000);
}

function cancelCountDown() {
    const countdownElement = document.getElementById('countdown');
    if (countdownElement) {
        countdownElement.classList.add('d-none');
    }
    document.getElementById('timer').innerHTML = "00:00";
    clearInterval(timerInterval);
}

function updateTimer() {
    const now = new Date().getTime();
    const timeLeft = targetTime - now;

    if (timeLeft <= 0) {
        cancelCountDown();
        return;
    }

    const minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
    const seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);

    const formattedMinutes = minutes < 10 ? '0' + minutes : minutes;
    const formattedSeconds = seconds < 10 ? '0' + seconds : seconds;

    document.getElementById('timer').innerHTML = `${formattedMinutes}:${formattedSeconds}`;
}