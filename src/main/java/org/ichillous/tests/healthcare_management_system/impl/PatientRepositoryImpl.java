package org.ichillous.tests.healthcare_management_system.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.ichillous.tests.healthcare_management_system.model.Patient;
import org.ichillous.tests.healthcare_management_system.repository.PatientRepository;

import java.util.List;

public class PatientRepositoryImpl implements PatientRepository {

    private SessionFactory sessionFactory;

    public PatientRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(patient);
            transaction.commit();
        }
    }

    @Override
    public Patient findById(int id) {  // Changed to int
        try (Session session = sessionFactory.openSession()) {
            return session.get(Patient.class, id);
        }
    }

    @Override
    public List<Patient> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Patient", Patient.class).list();
        }
    }

    @Override
    public void update(Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(patient);
            transaction.commit();
        }
    }

    @Override
    public void delete(int id) {  // Changed to int
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, id);
            if (patient != null) {
                session.delete(patient);
            }
            transaction.commit();
        }
    }
}