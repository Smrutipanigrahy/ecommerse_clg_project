package com.chs.service;

import com.chs.model.VendorRegistration;

import java.util.List;

public interface RegistrationService {

    VendorRegistration addDetails(VendorRegistration registration);
    VendorRegistration updateDetails(VendorRegistration registration);
    List<VendorRegistration> getAllDetails();
}
