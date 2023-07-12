package com.shikhar.softnerve.repo;

import com.shikhar.softnerve.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByName(String email);

}
