function connectWS() {
    let socketSeat = new SockJS('/booking/ws');
    let stompClientSeat = Stomp.over(socketSeat);
    stompClientSeat.connect({}, function (frame) {
        console.log('Connected to seat update: ' + frame);

        stompClientSeat.subscribe('/topic/seatUpdate', function (message) {
            var response = JSON.parse(message.body);
            handleSeatUpdate(response);
        });
    });

    function handleSeatUpdate(response) {
        var seatUpdatesDiv = document.getElementById('seatUpdates');
        var updateMessage = document.createElement('div');
        updateMessage.textContent = 'Seat ID: ' + response.seatId +
            ', Showtime ID: ' + response.showtimeId +
            ', Status: ' + response.status;
        seatUpdatesDiv.appendChild(updateMessage);
    }

    // Thiết lập kết nối đến WebSocket server cho cập nhật suất chiếu
    var socketShowtime = new SockJS('/ws/showtimeUpdate');
    var stompClientShowtime = Stomp.over(socketShowtime);

    stompClientShowtime.connect({}, function (frame) {
        console.log('Connected to showtime update: ' + frame);

        stompClientShowtime.subscribe('/topic/showtimeUpdate', function (message) {
            var response = JSON.parse(message.body);
            handleShowtimeUpdate(response);
        });
    });

    function handleShowtimeUpdate(response) {
        var showtimeUpdatesDiv = document.getElementById('showtimeUpdates');
        var updateMessage = document.createElement('div');
        updateMessage.textContent = 'Showtime ID: ' + response.showtimeId +
            ', Time: ' + response.time +
            ', Status: ' + response.status;
        showtimeUpdatesDiv.appendChild(updateMessage);
    }
}
