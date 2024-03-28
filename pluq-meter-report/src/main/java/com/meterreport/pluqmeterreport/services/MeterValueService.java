package com.meterreport.pluqmeterreport.services;

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
        return meterValueRepository.findById(meterValueId);
    }

    public List<MeterValue> getAllMeterValues() {
        return meterValueRepository.findAll();
    }

    public MeterValue saveMeterValue(MeterValue meterValue) {
        return meterValueRepository.save(meterValue);
    }

    public List<MeterValue> saveMeterValueList(List<MeterValue> meterValueList) {
        return meterValueRepository.saveAll(meterValueList);
    }

    public void deleteMeterValue(String meterValueId) {
        meterValueRepository.deleteById(meterValueId);
    }

    public void deleteAllMeterValues() {
        meterValueRepository.deleteAll();
    }
}
