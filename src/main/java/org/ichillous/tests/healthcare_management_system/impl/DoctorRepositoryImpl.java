package org.ichillous.tests.healthcare_management_system.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.ichillous.tests.healthcare_management_system.model.Doctor;
import org.ichillous.tests.healthcare_management_system.repository.DoctorRepository;

import java.util.List;

public class DoctorRepositoryImpl implements DoctorRepository {

    private SessionFactory sessionFactory;

    public DoctorRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(doctor);
            transaction.commit();
        }
    }

    @Override
    public Doctor findById(int id) {  // Changed to int
        try (Session session = sessionFactory.openSession()) {
            return session.get(Doctor.class, id);
        }
    }

    @Override
    public List<Doctor> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Doctor", Doctor.class).list();
        }
    }

    @Override
    public void update(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(doctor);
            transaction.commit();
        }
    }

    @Override
    public void delete(int id) {  // Changed to int
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, id);
            if (doctor != null) {
                session.delete(doctor);
            }
            transaction.commit();
        }
    }

}