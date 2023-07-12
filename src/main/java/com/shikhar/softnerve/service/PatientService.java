package com.shikhar.softnerve.service;

import com.shikhar.softnerve.payload.PatientDto;

import java.util.List;

public interface PatientService {

    //create patient
    PatientDto createPatient(PatientDto patientDto);

    //update patient
    PatientDto updatePatient(PatientDto patientDto, Integer patientId);

    //get patient
    PatientDto getPatientById(Integer patientId);

    //get all patient
    List<PatientDto> getAllPatient();

    //delete patient
    void deletePatient(Integer patientId);
}
