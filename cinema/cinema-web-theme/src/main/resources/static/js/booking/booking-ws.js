function connectWS() {
    const wsUrl = 'ws://localhost:8080/ws';

    const socket = new WebSocket(wsUrl);

    socket.onopen = function(event) {
        console.log('WebSocket connection opened:', event);
        // Gửi một tin nhắn đến server khi kết nối mở
        socket.send('connect ws success');
    };

// Xử lý sự kiện khi nhận được tin nhắn từ server
    socket.onmessage = function(event) {
        console.log('Message from server:', event.data);
    };

// Xử lý sự kiện khi kết nối WebSocket bị đóng
    socket.onclose = function(event) {
        console.log('WebSocket connection closed:', event);
    };

// Xử lý sự kiện khi có lỗi xảy ra
    socket.onerror = function(error) {
        console.error('WebSocket error:', error);
    };

// Gửi tin nhắn đến server
    function sendMessage(message) {
        if (socket.readyState === WebSocket.OPEN) {
            socket.send(message);
        } else {
            console.error('WebSocket is not open. Cannot send message.');
        }
    }

}
