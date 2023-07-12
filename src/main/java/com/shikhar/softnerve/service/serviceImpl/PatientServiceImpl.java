package com.shikhar.softnerve.service.serviceImpl;

import com.shikhar.softnerve.entity.Patient;
import com.shikhar.softnerve.exceptions.ResourceNotFoundException;
import com.shikhar.softnerve.payload.PatientDto;
import com.shikhar.softnerve.repo.PatientRepo;
import com.shikhar.softnerve.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PatientDto createPatient(PatientDto patientDto) {

        Patient patient = this.modelMapper.map(patientDto, Patient.class);

        Patient createdPatent = this.patientRepo.save(patient);

        return this.modelMapper.map(createdPatent, PatientDto.class);
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto, Integer patientId) {

        Patient fetchedPatient = this.patientRepo.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "patient_id", patientId));

        fetchedPatient.setName(patientDto.getName());
        fetchedPatient.setContactDetails(patientDto.getContactDetails());
        fetchedPatient.setAddress(patientDto.getAddress());
        fetchedPatient.setPinCode(patientDto.getPinCode());

        Patient updatedPatient = this.patientRepo.save(fetchedPatient);

        return this.modelMapper.map(updatedPatient, PatientDto.class);
    }

    @Override
    public PatientDto getPatientById(Integer patientId) {

        Patient fetchedPatient = this.patientRepo.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "patient_id", patientId));


        return this.modelMapper.map(fetchedPatient, PatientDto.class);
    }

    @Override
    public List<PatientDto> getAllPatient() {

        List<Patient> patientList = this.patientRepo.findAll();

        List<PatientDto> patientDtoList = patientList.stream().map(patient -> this.modelMapper.map(patient, PatientDto.class)).collect(Collectors.toList());

        return patientDtoList;
    }

    @Override
    public void deletePatient(Integer patientId) {

        Patient fetchedPatient = this.patientRepo.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "patient_id", patientId));

        this.patientRepo.delete(fetchedPatient);
    }
}
