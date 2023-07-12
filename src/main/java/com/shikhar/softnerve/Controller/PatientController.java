package com.shikhar.softnerve.Controller;

import com.shikhar.softnerve.payload.ApiResponse;
import com.shikhar.softnerve.payload.PatientDto;
import com.shikhar.softnerve.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {

        PatientDto createdPatient = this.patientService.createPatient(patientDto);

        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @PutMapping("/updatePatient")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto, @RequestParam Integer patientId) {

        PatientDto updatedPatient = this.patientService.updatePatient(patientDto, patientId);

        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("patientId") Integer patientId) {

        PatientDto patientDto = this.patientService.getPatientById(patientId);

        return new ResponseEntity<>(patientDto, HttpStatus.FOUND);
    }

    @GetMapping("/getAllPatient")
    public ResponseEntity<List<PatientDto>> getAllPatient() {

        List<PatientDto> patientDtoList = this.patientService.getAllPatient();

        return new ResponseEntity<>(patientDtoList, HttpStatus.FOUND);
    }

    @DeleteMapping("/")
    public ResponseEntity<ApiResponse> deletePatient(@RequestParam Integer patientId) {

        this.patientService.deletePatient(patientId);

        return new ResponseEntity<>(new ApiResponse("Patient was deleted successfully", true), HttpStatus.OK);
    }

}
