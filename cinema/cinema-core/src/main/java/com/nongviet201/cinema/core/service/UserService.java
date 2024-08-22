package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.user.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User findByEmail(String email);

    void createUser();

    User getCurrentUser();

    Boolean isCurrentUser(User user);

    List<User> getNewUsersByTimeRange(LocalDate startDate, LocalDate endDate);
}
