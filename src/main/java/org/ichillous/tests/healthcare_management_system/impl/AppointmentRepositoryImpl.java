package org.ichillous.tests.healthcare_management_system.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.ichillous.tests.healthcare_management_system.model.Appointment;
import org.ichillous.tests.healthcare_management_system.repository.AppointmentRepository;

import java.util.List;

public class AppointmentRepositoryImpl implements AppointmentRepository {

    private SessionFactory sessionFactory;

    public AppointmentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Appointment appointment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(appointment);
            transaction.commit();
        }
    }


    @Override
    public Appointment findById(int id) {  // Changed to int
        try (Session session = sessionFactory.openSession()) {
            return session.get(Appointment.class, id);
        }
    }

    @Override
    public List<Appointment> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Appointment", Appointment.class).list();
        }
    }

    @Override
    public void update(Appointment appointment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(appointment);
            transaction.commit();
        }
    }

    @Override
    public void delete(int id) {  // Changed to int
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, id);
            if (appointment != null) {
                session.delete(appointment);
            }
            transaction.commit();
        }
    }
}