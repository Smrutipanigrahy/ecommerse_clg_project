package com.chs.service.impl;

import com.chs.model.VendorRegistration;
import com.chs.repo.VendorRegistrationRepo;
import com.chs.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private VendorRegistrationRepo registrationRepo;

    @Override
    public VendorRegistration addDetails(VendorRegistration registration) {
        return this.registrationRepo.save(registration);
    }

    @Override
    public VendorRegistration updateDetails(VendorRegistration registration) {
        Optional<VendorRegistration> vendReg = this.registrationRepo.findById(registration.getVendorId());
        if(vendReg.isPresent()){
            VendorRegistration vendorUpdate = vendReg.get();
            vendorUpdate.setVendorName(registration.getVendorName());
            vendorUpdate.setEmailId(registration.getEmailId());
            vendorUpdate.setProduct(registration.getProduct());
            registrationRepo.save(vendorUpdate);
            return vendorUpdate;
        }
        else
            throw new IllegalStateException("Record not found with id : " + registration.getVendorId());

    }

    @Override
    public List<VendorRegistration> getAllDetails() {
        return this.registrationRepo.findAll();
    }
}
