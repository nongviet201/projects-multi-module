function connectWS() {
    let socketBooking = new SockJS('/booking/ws');
    let stompClientSeat = Stomp.over(socketBooking);

    stompClientSeat.connect({}, function (frame) {
        console.log('Connected to booking ws: ' + frame);

        stompClientSeat.subscribe('/topic/seatUpdate', function (message) {
            const response = JSON.parse(message.body);
            handleSeatUpdate(response);
        });
    });

    function handleSeatUpdate(response) {
        if (showtime.id === response.showtimeId) {
            const seat = seatsData.find(e => e.id.toString() === response.seatId.toString());
            const seatEl = document.getElementById('seat-'+response.seatId);
            if (response.status === "PENDING") {
                if (seat.type === "COUPLE") {
                    seatEl.parentElement.classList.add("seat-hover");
                } else {
                    seatEl.classList.add("seat-hover");
                }
            }
            if (response.status === "ORDERED") {
                if (seat.type === "COUPLE") {
                    seatEl.parentElement.classList.add("seat-sold");
                } else {
                    seatEl.classList.add("seat-sold");
                }
            }
            if (response.status == null) {
                currentSeatsChose.delete(response.seatId);
                showtimeDetailSeatShow();
                if (seat.type === "COUPLE") {
                    seatEl.parentElement.classList.remove("seat-hover", "seat-sold", "active");
                } else {
                    seatEl.classList.remove("seat-hover", "seat-sold", "active");
                }
            }
        }
    }
}
