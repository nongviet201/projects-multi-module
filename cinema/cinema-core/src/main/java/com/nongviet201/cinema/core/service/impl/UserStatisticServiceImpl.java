package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.entity.user.UserStatistic;
import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.enums.user.UserRank;
import com.nongviet201.cinema.core.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public UserStatistic getCurrentUserStatistic() {
        return userService.getCurrentUser().getUserStatistic()
        ;
    }

    @Override
    public Integer UpdateUserStatistic(
        Long totalSpending
    ) {
        User user = userService.getCurrentUser();

        int points;

        if (user.getUserStatistic() == null) {
            points = calculatePoints(UserRank.NORMAL, totalSpending);

            UserStatistic userStatistic =
                UserStatistic.builder()
                .userRank(UserRank.NORMAL)
                .points(points)
                .totalSpending(totalSpending)
                .build();

            userStatisticRepository.save(
                userStatistic
            );

            user.setUserStatistic(userStatistic);
            userRepository.save(user);

            return points;
        } else {
            points = calculatePoints(user.getUserStatistic().getUserRank(), totalSpending);

            user.getUserStatistic().setPoints(user.getUserStatistic().getPoints() + calculatePoints(user.getUserStatistic().getUserRank(), totalSpending));
            user.getUserStatistic().setTotalSpending(user.getUserStatistic().getTotalSpending() + totalSpending);
            user.getUserStatistic().setUserRank(setUserRank(user.getUserStatistic().getTotalSpending()));
            userStatisticRepository.save(user.getUserStatistic());
        }
        return points;
    }

    @Override
    public void createStatistic(
        User newUser
    ) {

        newUser.setUserStatistic(
            UserStatistic.builder()
            .userRank(UserRank.NORMAL)
            .points(0)
            .totalSpending(0L)
            .build()
        );
        userRepository.save(newUser);
        userStatisticRepository.save(
            newUser.getUserStatistic()
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
