package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.entity.user.UserStatistic;
import com.nongviet201.cinema.core.model.enums.UserRank;

public interface UserStatisticService {
    UserStatistic getUserStatisticByUserId(int userId);
    UserStatistic getCurrentUserStatistic();
    Integer UpdateUserStatistic(Long totalSpending);

    void createStatistic(User newUser);
}
