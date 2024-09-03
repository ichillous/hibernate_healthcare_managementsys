package org.ichillous.tests.healthcare_management_system.service;

import org.ichillous.tests.healthcare_management_system.model.Office;
import org.ichillous.tests.healthcare_management_system.repository.OfficeRepository;

import java.util.List;

public class OfficeService {

    private OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public void createOffice(Office office) {
        officeRepository.createOffice(office);
    }

    public Office getOfficeById(int officeId){
        return officeRepository.getOfficeById(officeId);
    }

    public List<Office> getAllOffices() {
        return officeRepository.getAllOffices();
    }

    public void updateOffice(Office office) {
        officeRepository.updateOffice(office);
    }

    public void deleteOffice(int officeId) {
        officeRepository.deleteOffice(officeId);
    }
}
