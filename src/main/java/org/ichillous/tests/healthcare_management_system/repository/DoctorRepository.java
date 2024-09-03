package org.ichillous.tests.healthcare_management_system.repository;

import org.ichillous.tests.healthcare_management_system.model.Doctor;

import java.util.List;

public interface DoctorRepository {
    void save(Doctor doctor);
    Doctor findById(int id);
    List<Doctor> findAll();
    void update(Doctor doctor);
    void delete(int id);
}
