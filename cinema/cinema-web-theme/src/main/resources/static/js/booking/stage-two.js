

function stageTwo() {
    getListTimeShowtimeByMovieIdAndAuditoriumId(movieId, auditoriumId);
    getListSeatByAuditoriumId(auditoriumId);
    showtimeDetailShowAll(showtime);
    nextBtn.classList.add("disabled");
}

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
        const rowNameS = document.createElement('div');
        const rowNameE = document.createElement('div');
        const rowList = document.createElement('div');
        rowDiv.className = 'seat-row';
        rowNameS.innerHTML= row;
        rowNameE.innerHTML= row;
        rowDiv.appendChild(rowNameS);
        rowList.className = 'seat-list';

        let i = 0;
        rows[row].forEach(seat => {
            const seatBtn = document.createElement('button');
            seatBtn.className = 'seat';
            seatBtn.value = seat.id;
            seatBtn.id = 'seat-'+ seat.id;
            seatBtn.innerText = ++i;
            seatBtn.onclick = function() {
                seatBtnFunc(seat.id);
            };
            rowList.appendChild(seatBtn);
        });
        rowDiv.appendChild(rowList);
        rowDiv.appendChild(rowNameE);
        seatMap.appendChild(rowDiv);
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

    if (currentSeatsChose.size > 0) {
        nextBtn.classList.remove("disabled");
    } else {
        nextBtn.classList.add("disabled");
    }

    showtimeDetailSeatShow();
}


function getListTimeShowtimeByMovieIdAndAuditoriumId(movieId, auditoriumId) {
    $.ajax({
        url: '/booking/get/stage-two?movieId='+movieId+'&auditoriumId='+auditoriumId,
        type: 'GET',
        success: function (htmlResponse) {
            divStage.innerHTML = htmlResponse;
        }, error: function (xhr, status, error) {
            console.error('Đã xảy ra lỗi: ' + xhr);
        }
    });
}

function getListSeatByAuditoriumId(auditoriumId) {
    $.ajax({
        url: '/api/v1/seat/get/' + auditoriumId,
        type: 'GET',
        success: function (response) {
            seatsData = response;
            renderSeats(seatsData);
        }, error: function (xhr, status, error) {
            console.error('Đã xảy ra lỗi: ' + xhr);
        }
    });
}