package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.model.entity.user.User;

public interface UserService {
    User findByEmail(String email);

    void createUser();

    User getCurrentUser();
}
