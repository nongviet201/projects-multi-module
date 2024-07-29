function stageFour() {
    $.ajax({
        url: '/booking/get/stage-four',
        type: 'GET',
        success: function (htmlResponse) {
            divStage.innerHTML = htmlResponse;
            updateTicketBill();
        },
        error: function (xhr, status, error) {
            console.error(`Đã xảy ra lỗi: ${error}`);
            console.error('Status:', status);
            console.error('Error:', error);
        }
    });
}

function updateTicketBill() {
    const updateElement = (id, html) => document.getElementById(id).innerHTML = html;

    updateElement('bill-movie-name', `
        <p class="fs-16px fw-600 text-blue">${showtime.movie.name}</p>
        <p class="fs-14px">type</p>
    `);

    updateElement('bill-cinema-name', `
        <p class="fs-16px fw-600 text-blue">${showtime.auditorium.cinema.name}</p>
        <p class="fw-600 fs-14px">${showtime.startTime}, ${showtime.screeningDate}</p>
    `);

    const formatSeatList = () => Array.from(currentSeatsChose)
        .map(seatId => seatsData.find(e => e.id === seatId))
        .filter(Boolean)
        .map(seat => seat.seatRow + seat.seatColumn)
        .join(', ');

    const formatComboList = () => Array.from(comboData)
        .map(([comboId, quantity]) => {
            const combo = combosName.get(comboId);
            return combo ? `<span class="fs-14px"><strong>${quantity} <span> x </span></strong> ${combo}</span>` : '';
        })
        .filter(Boolean)
        .join(' ');

    updateElement('bill-auditorium', `
        <p class="fs-16px">${showtime.auditorium.name} <strong class="fs-14px ms-2">${formatSeatList()}</strong></p>
        ${formatComboList()}
    `);

    updateElement('bill-total-price', `${totalPrice} <span>đ</span>`);
}
