package org.ichillous.tests.healthcare_management_system.repository;

import org.ichillous.tests.healthcare_management_system.model.Office;

import java.util.List;

public interface OfficeRepository {
    void createOffice(Office office);
    Office getOfficeById(int officeId);
    List<Office> getAllOffices();
    void updateOffice(Office office);
    void deleteOffice(int officeId);
}
