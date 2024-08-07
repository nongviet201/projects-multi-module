package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.exception.ResourceNotFoundException;
import com.nongviet201.cinema.core.model.entity.user.User;
import com.nongviet201.cinema.core.repository.UserRepository;
import com.nongviet201.cinema.core.security.CustomUserDetail;
import com.nongviet201.cinema.core.security.IAuthenticationFacade;
import com.nongviet201.cinema.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final IAuthenticationFacade authenticationFacade;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user với email = " + email));
    }

    @Override
    public void createUser() {

    }

    @Override
    public User getCurrentUser() {
        if (authenticationFacade.getAuthentication() != null &&
            authenticationFacade.getAuthentication().getPrincipal() instanceof CustomUserDetail
        ) {
            CustomUserDetail userDetails = (CustomUserDetail) authenticationFacade.getAuthentication().getPrincipal();
            return findByEmail(userDetails.getUsername());
        }
        return null;
    }
}

