package com.chs.service;

import com.chs.module.UserRegistration;

import java.util.List;

public interface UserRegService {
    UserRegistration addDetails(UserRegistration registration);
    UserRegistration updateDetails(UserRegistration registration);
    List<UserRegistration> getAllDetails();
    UserRegistration getUserById(String userId);
}
