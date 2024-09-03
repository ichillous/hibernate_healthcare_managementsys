package org.ichillous.tests.healthcare_management_system.service;

import org.ichillous.tests.healthcare_management_system.model.Doctor;
import org.ichillous.tests.healthcare_management_system.repository.DoctorRepository;

import java.util.List;

public class DoctorService {

    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void createDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public void updateDoctor(Doctor doctor) {
        doctorRepository.update(doctor);
    }

    public void deleteDoctor(int id) {
        doctorRepository.delete(id);
    }
}