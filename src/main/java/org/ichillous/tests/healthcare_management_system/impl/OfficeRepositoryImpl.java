package org.ichillous.tests.healthcare_management_system.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.ichillous.tests.healthcare_management_system.model.Office;
import org.ichillous.tests.healthcare_management_system.repository.OfficeRepository;

import java.util.List;

public class OfficeRepositoryImpl implements OfficeRepository {

    private SessionFactory sessionFactory;

    public OfficeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createOffice(Office office) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(office);
            transaction.commit();
        }
    }

    @Override
    public Office getOfficeById(int officeId){
        try (Session session = sessionFactory.openSession()) {
            return session.get(Office.class, officeId);
        }
    }

    @Override
    public List<Office> getAllOffices() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Office", Office.class).list();
        }
    }

    @Override
    public void updateOffice(Office office) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(office);
            transaction.commit();
        }
    }

    @Override
    public void deleteOffice(int officeId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Office office = session.get(Office.class, officeId);
            if (office != null) {
                session.delete(office);
            }
            transaction.commit();
        }
    }
}
