package com.chs.service.impl;

import com.chs.module.UserRegistration;
import com.chs.repository.UserRegRepo;
import com.chs.service.UserRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegServiceImpl implements UserRegService {
    @Autowired
    private UserRegRepo registrationRepo;

    @Override
    public UserRegistration addDetails(UserRegistration registration) {
        return this.registrationRepo.save(registration);
    }

    @Override
    public UserRegistration updateDetails(UserRegistration registration) {
        Optional<UserRegistration> vendReg = this.registrationRepo.findById(registration.getUserId());
        if(vendReg.isPresent()){
            UserRegistration userUpdate = vendReg.get();
            userUpdate.setUserName(registration.getUserName());
            userUpdate.setEmailId(registration.getEmailId());
            userUpdate.setMobileNo(registration.getMobileNo());
            userUpdate.setCountry(registration.getCountry());
            userUpdate.setState(registration.getState());

            registrationRepo.save(userUpdate);
            return userUpdate;
        }
        else
            throw new IllegalStateException("Record not found with id : " + registration.getUserId());
    }

    @Override
    public List<UserRegistration> getAllDetails() {
        return this.registrationRepo.findAll();
    }

    @Override
    public UserRegistration getUserById(String userId) {
        Optional<UserRegistration> product = this.registrationRepo.findById(userId);
        if(product.isPresent())
            return product.get();
        else
            throw new IllegalStateException("Record not found with id : " + userId);
    }
}
