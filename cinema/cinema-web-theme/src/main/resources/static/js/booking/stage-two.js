const container = document.getElementById('seat-container');
const rows = {};
let seatsData = {};
nextBtn.addEventListener('click', () => {
    divStage.innerHTML = "";

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
                seatBtn.innerText = seat.seatRow + seat.seatColumn;
                seatBtn.addEventListener('click', () => seatBtnFunc(seat.id));
                rowDiv.appendChild(seatBtn);
            });
            container.appendChild(rowDiv);
        }
    }
    // Gọi hàm render
    renderSeats(seats);


function seatBtnFunc(value) {
    showtimeDetailSeat.innerHTML = `
        <div class="d-flex justify-content-between">
            <div class="fs-14px"><strong>1</strong><strong> x </strong><span>Ghế đơn</span></div>
            <strong class="fs-14px">70.000Đ</strong>
        </div>
        <div>
            <span class="fs-14px">Ghế: </span>
            <strong>K7</strong>
        </div>
        <div class="dash-line"></div>
    `;
}
