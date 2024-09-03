package org.ichillous.tests.healthcare_management_system.repository;

import org.ichillous.tests.healthcare_management_system.model.Appointment;

import java.util.List;

public interface AppointmentRepository {
    void save(Appointment appointment);
    Appointment findById(int id);
    List<Appointment> findAll();
    void update(Appointment appointment);
    void delete(int id);
}
