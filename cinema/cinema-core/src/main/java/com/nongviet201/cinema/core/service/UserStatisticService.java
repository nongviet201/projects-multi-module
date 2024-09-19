package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.entity.user.UserStatistic;

public interface UserStatisticService {
    UserStatistic getCurrentUserStatistic();
    Integer UpdateUserStatistic(Long totalSpending);

    void createStatistic(User newUser);
}
