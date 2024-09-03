package org.ichillous.tests.healthcare_management_system.repository;

import org.ichillous.tests.healthcare_management_system.model.Patient;

import java.util.List;

public interface PatientRepository {
    void save(Patient patient);
    Patient findById(int id);
    List<Patient> findAll();
    void update(Patient patient);
    void delete(int id);
}
