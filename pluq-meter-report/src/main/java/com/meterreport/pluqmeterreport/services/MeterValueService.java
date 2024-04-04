package com.meterreport.pluqmeterreport.services;


import com.meterreport.pluqmeterreport.errors.customErrors.location.LocationNotFoundException;
import com.meterreport.pluqmeterreport.errors.customErrors.meterValue.MeterValueAlreadyExistsException;
import com.meterreport.pluqmeterreport.errors.customErrors.meterValue.MeterValueNotFoundException;
import com.meterreport.pluqmeterreport.models.MeterValue;
import com.meterreport.pluqmeterreport.repos.MeterValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeterValueService {

    private final MeterValueRepository meterValueRepository;

    @Autowired
    public MeterValueService(MeterValueRepository meterValueRepository) {
        this.meterValueRepository = meterValueRepository;
    }


    public Optional<MeterValue> getMeterValueById(String meterValueId) {
        Optional<MeterValue> meterValue = meterValueRepository.findById(meterValueId);

        if(meterValue.isEmpty()){
            throw new MeterValueNotFoundException("MeterValue not found by meterValueId: " + meterValueId);
        }

        return meterValue;
    }

    public List<MeterValue> getAllMeterValues() {
        List<MeterValue> meterValueList = meterValueRepository.findAll();

        if(meterValueList.isEmpty()) {
            throw new MeterValueNotFoundException("Could not find any saved meterValues.");
        }
        return meterValueList;
    }

    public MeterValue saveMeterValue(MeterValue meterValue) {
        if(meterValueRepository.findById(meterValue.getTransactionId()).isPresent()) {
            throw new MeterValueAlreadyExistsException("MeterValue already exists with id: " + meterValue.getTransactionId());
        }

        return meterValueRepository.save(meterValue);
    }

    public List<MeterValue> saveMeterValueList(List<MeterValue> meterValueList) {
        for(MeterValue meterValue : meterValueList) {
            if(meterValueRepository.findById(meterValue.getTransactionId()).isPresent()) {
                throw new MeterValueAlreadyExistsException("MeterValue already exists with id: " + meterValue.getTransactionId());
            }
        }
        return meterValueRepository.saveAll(meterValueList);
    }

    public void deleteMeterValue(String meterValueId) {
        if (meterValueRepository.findById(meterValueId).isEmpty()) {
            throw new MeterValueNotFoundException("MeterValue not found by meterValueId: " + meterValueId);
        }

        meterValueRepository.deleteById(meterValueId);
    }

    public void deleteAllMeterValues() {
        if (meterValueRepository.findAll().isEmpty()) {
            throw new LocationNotFoundException("Could not find any saved meterValues.");
        }
        meterValueRepository.deleteAll();
    }

    public List<MeterValue> getMeterValuesByPhysicalReference(String physicalReference) {
        return meterValueRepository.findAllByPhysicalReference(physicalReference);
    }
}
