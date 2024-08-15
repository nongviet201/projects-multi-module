package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.entity.user.UserStatistic;
import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.enums.user.UserRank;
import com.nongviet201.cinema.core.repository.UserStatisticRepository;
import com.nongviet201.cinema.core.service.UserService;
import com.nongviet201.cinema.core.service.UserStatisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserStatisticServiceImpl implements UserStatisticService {

    private final UserStatisticRepository userStatisticRepository;
    private final UserService userService;

    @Override
    public UserStatistic getUserStatisticByUserId(int userId) {
        return userStatisticRepository.findByUserId(userId)
            .orElseThrow(() -> new BadRequestException("Không tìm thấy thông tin"));
    }

    @Override
    public UserStatistic getCurrentUserStatistic() {
        return getUserStatisticByUserId(
            userService.getCurrentUser().getId()
        );
    }

    @Override
    public Integer UpdateUserStatistic(
        Long totalSpending
    ) {
        User user = userService.getCurrentUser();

        UserStatistic userStatistic = userStatisticRepository.findByUserId(user.getId())
            .orElse(null);

        int points;

        if (userStatistic == null) {
            points = calculatePoints(UserRank.NORMAL, totalSpending);
            userStatisticRepository.save(
                UserStatistic.builder()
                    .userRank(UserRank.NORMAL)
                    .points(points)
                    .totalSpending(totalSpending)
                    .user(user)
                    .build()
            );
            return points;
        } else {
            points = calculatePoints(userStatistic.getUserRank(), totalSpending);

            userStatistic.setPoints(userStatistic.getPoints() + calculatePoints(userStatistic.getUserRank(), totalSpending));
            userStatistic.setTotalSpending(userStatistic.getTotalSpending() + totalSpending);
            userStatistic.setUserRank(setUserRank(userStatistic.getTotalSpending()));
            userStatisticRepository.save(userStatistic);
        }
        return points;
    }

    @Override
    public void createStatistic(
        User newUser
    ) {
        userStatisticRepository.save(
            UserStatistic.builder()
                .userRank(UserRank.NORMAL)
                .points(0)
                .totalSpending(0L)
                .build()
        );
    }

    private int calculatePoints(
        UserRank userRank,
        long transactionAmount
    ) {
        double rawPoints = ((double) transactionAmount / 1000) * userRank.getAccumulationRate();
        return roundPoints(rawPoints);
    }

    private int roundPoints(double rawPoints) {
        double fractionalPart = rawPoints - Math.floor(rawPoints);
        if (fractionalPart >= 0.5) {
            return (int) Math.ceil(rawPoints);
        } else {
            return (int) Math.floor(rawPoints);
        }
    }

    private UserRank setUserRank(
        long totalSpending
    ) {
        if (totalSpending == 0) {
            return UserRank.NORMAL;
        } if (totalSpending >= 2000000 && totalSpending <= 3999999) {
            return UserRank.VIP;
        }
        return UserRank.PREMIUM;
    }

}
