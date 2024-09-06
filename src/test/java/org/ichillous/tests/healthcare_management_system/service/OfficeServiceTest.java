package org.ichillous.tests.healthcare_management_system.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.ichillous.tests.healthcare_management_system.impl.*;
import org.ichillous.tests.healthcare_management_system.impl.DoctorRepositoryImpl;
import org.ichillous.tests.healthcare_management_system.model.Doctor;
import org.ichillous.tests.healthcare_management_system.model.Office;
import org.ichillous.tests.healthcare_management_system.repository.DoctorRepository;
import org.ichillous.tests.healthcare_management_system.repository.OfficeRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockitoAnnotations;

import java.util.Objects;

class OfficeServiceTest {
    private OfficeService officeService;
    private SessionFactory sessionFactory;
    private Office office;
    private Doctor doctor;
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sessionFactory = new Configuration().configure("hibernate-test.cfg.xml").buildSessionFactory();
        OfficeRepository officeRepository = new OfficeRepositoryImpl(sessionFactory);
        DoctorRepository doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        officeService = new OfficeService(officeRepository);
        doctorService = new DoctorService(doctorRepository);
    }
    @AfterEach
    void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    void createOffice() {
        Office office = new Office();
        office.setLocation("123 Broken Dreams Blvd Columbus Ohio");
        office.setPhone("614-222-3333");
        office.setDoctor(doctorService.getDoctorById(1));
        officeService.createOffice(office);
        System.out.println(office);
    }

    @Test
    void getOfficeById() {
        officeService.getOfficeById(1);
        assertNull(office);
    }


    @Test
    void updateOffice() {
        Office office = new Office();
        office.setLocation("123 Broken Dreams Blvd Columbus Ohio");
        office.setPhone("614-222-3333");
        office.setDoctor(doctorService.getDoctorById(1));
        officeService.createOffice(office);
        office = officeService.getOfficeById(1);
        if (office != null ) {
            office.setLocation("1234 Forest Hills Blvd Columbus Ohio");
            office.setPhone("614-333-4444");
            office.setDoctor(doctorService.getDoctorById(2));
            officeService.updateOffice(office);
        } else {
            System.out.println("Office not found");
        }

        assertEquals("1234 Forest Hills Blvd Columbus Ohio", office.getLocation());
    }

    @Test
    void deleteOffice() {
        Office office = new Office();
        office.setLocation("123 Broken Dreams Blvd Columbus Ohio");
        office.setPhone("614-222-3333");
        office.setDoctor(doctorService.getDoctorById(1));
        officeService.createOffice(office);

        int id = office.getOfficeId();
        officeService.deleteOffice(id);
        assertNull(officeService.getOfficeById(id));
    }

    @ParameterizedTest
    @CsvSource({"Main Office", "Branch Office"})
    public void testCreateOfficeWithDifferentNames(String name) {
        Office office = new Office();
        office.setLocation(name);
        office.setPhone("614-222-3333");
        office.setDoctor(doctorService.getDoctorById(1));

        officeService.createOffice(office);
        assertNotNull(office);
        assertEquals(name, office.getLocation());
    }

    @Test
    void testInvalidInput() {
        Office office = new Office();
        office.setLocation("");
        office.setPhone("");
        office.setDoctor(doctorService.getDoctorById(1));
        officeService.createOffice(office);
        int id = office.getOfficeId();
        int id2 = 0;

        assertThrows(IllegalArgumentException.class, () -> {
            if (Objects.equals(office.getLocation(), "")) {
                throw new AssertionError("Office location is null");
            } else if (Objects.equals(office.getPhone(), "")) {
                throw new AssertionError("Office phone is null");
            } else if (Objects.equals(office.getDoctor(), doctorService.getDoctorById(id2))) {
                throw new AssertionError("Office doctor is null");
            }
        });

    }

}