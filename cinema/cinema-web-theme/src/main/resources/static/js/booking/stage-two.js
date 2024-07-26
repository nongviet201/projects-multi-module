const seatContainer = document.getElementById('seat-container');
const rows = {};
let seatsData = {};
nextBtn.addEventListener('click', () => {
    currentStage++;
    barStageTwo.classList.add("active")
    barStageOne.classList.remove("active")
    barStageOne.classList.add("activated")
    divStage.innerHTML = "";
    getListTimeShowtimeByMovieIdAndAuditoriumId(movieId, nextBtn.value);
    $.ajax({
        url: '/api/v1/seat/get/' + nextBtn.value,
        type: 'GET',
        success: function (response) {
            seatsData = response;
            renderSeats(seatsData);
        }, error: function (xhr, status, error) {
            console.error('Đã xảy ra lỗi: ' + xhr);
        }
    });
});
    // Tạo hàm render ghế ngồi
    function renderSeats(seats) {
        seatContainer.classList.remove("d-none");
        // Nhóm ghế theo hàng
        seats.forEach(seat => {
            const row = seat.seatRow;
            if (!rows[row]) {
                rows[row] = [];
            }
            rows[row].push(seat);
        });

        // Tạo phần tử HTML cho từng hàng ghế
        for (let row in rows) {
            const rowDiv = document.createElement('div');
            rowDiv.className = 'seat-row';
            rows[row].forEach(seat => {
                const seatBtn = document.createElement('button');
                seatBtn.className = 'seat';
                seatBtn.value = seat.id;
                seatBtn.id = 'seat-'+ seat.id;
                seatBtn.innerText = seat.seatRow + seat.seatColumn;
                seatBtn.onclick = function() {
                    seatBtnFunc(seat.id);
                };
                rowDiv.appendChild(seatBtn);
            });
            seatContainer.appendChild(rowDiv);
        }
    }


let currentSeatsChose = new Set();
const numberSeatsChose = document.getElementById("number-seats-chose");
const seatName = document.getElementById("seat-name");

function seatBtnFunc(value) {
    let seat = seatsData.find(e => e.id.toString() === value.toString());

    const seatEl = document.getElementById("seat-" + seat.id);
    const isActive = seatEl.classList.toggle("active");

    if (isActive) {
        currentSeatsChose.add(seat.id);
    } else {
        currentSeatsChose.delete(seat.id);
    }

    updateSeatsDetail();
}

function updateSeatsDetail() {
    numberSeatsChose.innerText = currentSeatsChose.size;
    // Cập nhật chi tiết ghế
    if (currentSeatsChose.size > 0) {
        showtimeDetailSeat.classList.remove("d-none");
        seatName.innerHTML = "";
        currentSeatsChose.forEach(seatId => {
            let seat = seatsData.find(e => e.id === seatId);
            console.log(seat)
            const seatNameValue = document.createElement('span');
            seatNameValue.innerText = `${seat.seatRow}${seat.seatColumn}, `;
            seatNameValue.id = `${seat.seatRow}${seat.seatColumn}`;
            seatName.appendChild(seatNameValue);
        });
    } else {
        showtimeDetailSeat.classList.add("d-none");
        seatName.innerHTML = "";
    }
}






function getListTimeShowtimeByMovieIdAndAuditoriumId(movieId, auditoriumId) {
    $.ajax({
        url: '/booking/get/stage-two?movieId='+movieId+'&auditoriumId='+auditoriumId, type: 'GET', success: function (htmlResponse) {
            divStage.innerHTML = htmlResponse;
        }, error: function (xhr, status, error) {
            console.error('Đã xảy ra lỗi: ' + xhr);
        }
    });
}