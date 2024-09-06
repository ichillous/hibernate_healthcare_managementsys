package org.ichillous.tests.healthcare_management_system.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.ichillous.tests.healthcare_management_system.model.Office;
import org.ichillous.tests.healthcare_management_system.repository.OfficeRepository;


import java.util.List;


public class OfficeRepositoryImpl implements OfficeRepository {

    private final SessionFactory sessionFactory;

    public OfficeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Office office) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(office);
            transaction.commit();
        }
    }

    @Override
    public Office findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Office.class, id);
        }
    }

    @Override
    public List<Office> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Office", Office.class).list();
        }
    }

    @Override
    public void update(Office office) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(office);
            transaction.commit();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Office office = session.get(Office.class, id);
            if (office != null) {
                session.remove(office);
            }
            transaction.commit();
        }
    }
}