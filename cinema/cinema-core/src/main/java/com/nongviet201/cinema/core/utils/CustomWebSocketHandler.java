package com.nongviet201.cinema.core.utils;

import com.nongviet201.cinema.core.schedule.ReservationScheduleTask;
import com.nongviet201.cinema.core.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Transactional
@AllArgsConstructor
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @Lazy
    private final ReservationScheduleTask reservationScheduleTask;
    @Lazy
    private final UserService userService;

    private final Map<String, String> userConnectWS = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String username = (String) session.getAttributes().get("username");
        if (username != null) {
            userConnectWS.put(session.getId(), username);
        }
        System.out.println("User connected: " + username);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String email = userConnectWS.get(session.getId());
        Integer userId = userService.findByEmail(email).getId();

        System.out.println("User connected: " + email);
        if (userId != null) {
            scheduler.schedule(() -> reservationScheduleTask.releaseExpiredUserDisconnectBookingWS(userId), 1, TimeUnit.MINUTES);
        }
    }
}
