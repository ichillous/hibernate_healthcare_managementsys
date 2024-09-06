package org.ichillous.tests.healthcare_management_system.repository;

import org.ichillous.tests.healthcare_management_system.model.Office;

import java.util.List;

public interface OfficeRepository {
    void create(Office office);
    Office findById(int id);
    List<Office> findAll();
    void update(Office office);
    void delete(int id);
}
