package org.ichillous.tests.healthcare_management_system;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.ichillous.tests.healthcare_management_system.impl.AppointmentRepositoryImpl;
import org.ichillous.tests.healthcare_management_system.impl.DoctorRepositoryImpl;
import org.ichillous.tests.healthcare_management_system.impl.PatientRepositoryImpl;
import org.ichillous.tests.healthcare_management_system.model.Appointment;
import org.ichillous.tests.healthcare_management_system.model.Doctor;
import org.ichillous.tests.healthcare_management_system.impl.*;
import org.ichillous.tests.healthcare_management_system.model.Office;
import org.ichillous.tests.healthcare_management_system.model.Patient;
import org.ichillous.tests.healthcare_management_system.service.AppointmentService;
import org.ichillous.tests.healthcare_management_system.service.DoctorService;
import org.ichillous.tests.healthcare_management_system.service.PatientService;
import org.ichillous.tests.healthcare_management_system.service.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.print.Doc;
import java.util.Scanner;
import java.util.Stack;

@SpringBootApplication
public class HealthcareManagementSystemApplication {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        // Repositories
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl(sessionFactory);
        OfficeRepositoryImpl officeRepository = new OfficeRepositoryImpl(sessionFactory);

        // Services
        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);
        OfficeService officeService = new OfficeService(officeRepository);

        // Main Menu
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nHealthcare Management System");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Manage Offices");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    managePatients(patientService, scanner);
                    break;
                case 2:
                    manageDoctors(doctorService, scanner);
                    break;
                case 3:
                    manageAppointments(appointmentService, scanner);
                    break;
                case 4:
                    manageOffices(officeService, scanner);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting Healthcare Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        sessionFactory.close();
        scanner.close();
    }

    private static void managePatients(PatientService patientService, Scanner scanner) {
        System.out.println("\nManage Patients");
        System.out.println("1. Create Patient");
        System.out.println("2. Read Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        System.out.println("5. List All Patients");

        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        switch (choice) {
            case 1:
                Patient newPatient = new Patient();
                System.out.print("Enter first name: ");
                newPatient.setFirstName(scanner.nextLine());
                System.out.print("Enter last name: ");
                newPatient.setLastName(scanner.nextLine());
                System.out.print("Enter date of birth (YYYY-MM-DD): ");
                newPatient.setDateOfBirth(scanner.nextLine());
                System.out.print("Enter email: ");
                newPatient.setEmail(scanner.nextLine());
                System.out.print("Enter phone number: ");
                newPatient.setPhoneNumber(scanner.nextLine());
                patientService.createPatient(newPatient);
                System.out.println("Patient created successfully.");
                break;
            case 2:
                System.out.print("Enter Patient ID: ");
                int patientId = scanner.nextInt();
                Patient patient = patientService.getPatientById(patientId);
                if (patient != null) {
                    System.out.println("Patient ID: " + patient.getId());
                    System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
                    System.out.println("Date of Birth: " + patient.getDateOfBirth());
                    System.out.println("Email: " + patient.getEmail());
                    System.out.println("Phone: " + patient.getPhoneNumber());
                } else {
                    System.out.println("Patient not found.");
                }
                break;
            case 3:
                System.out.print("Enter Patient ID: ");
                patientId = scanner.nextInt();
                scanner.nextLine();  // consume newline
                patient = patientService.getPatientById(patientId);
                if (patient != null) {
                    System.out.print("Enter new first name: ");
                    patient.setFirstName(scanner.nextLine());
                    System.out.print("Enter new last name: ");
                    patient.setLastName(scanner.nextLine());
                    System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                    patient.setDateOfBirth(scanner.nextLine());
                    System.out.print("Enter new email: ");
                    patient.setEmail(scanner.nextLine());
                    System.out.print("Enter new phone number: ");
                    patient.setPhoneNumber(scanner.nextLine());
                    patientService.updatePatient(patient);
                    System.out.println("Patient updated successfully.");
                } else {
                    System.out.println("Patient not found.");
                }
                break;
            case 4:
                System.out.print("Enter Patient ID: ");
                patientId = scanner.nextInt();
                patientService.deletePatient(patientId);
                System.out.println("Patient deleted successfully.");
                break;
            case 5:
                System.out.println("Listing All Patients:");
                for (Patient p : patientService.getAllPatients()) {
                    System.out.println(p);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageDoctors(DoctorService doctorService, Scanner scanner) {
        System.out.println("\nManage Doctors");
        System.out.println("1. Create Doctor");
        System.out.println("2. Read Doctor");
        System.out.println("3. Update Doctor");
        System.out.println("4. Delete Doctor");
        System.out.println("5. List All Doctors");

        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        switch (choice) {
            case 1:
                Doctor newDoctor = new Doctor();
                System.out.print("Enter first name: ");
                newDoctor.setFirstName(scanner.nextLine());
                System.out.print("Enter last name: ");
                newDoctor.setLastName(scanner.nextLine());
                System.out.print("Enter specialty: ");
                newDoctor.setSpecialty(scanner.nextLine());
                System.out.print("Enter email: ");
                newDoctor.setEmail(scanner.nextLine());
                doctorService.createDoctor(newDoctor);
                System.out.println("Doctor created successfully.");
                break;
            case 2:
                System.out.print("Enter Doctor ID: ");
                int doctorId = scanner.nextInt();
                Doctor doctor = doctorService.getDoctorById(doctorId);
                if (doctor != null) {
                    System.out.println("Doctor ID: " + doctor.getId());
                    System.out.println("Name: " + doctor.getFirstName() + " " + doctor.getLastName());
                    System.out.println("Specialty: " + doctor.getSpecialty());
                    System.out.println("Email: " + doctor.getEmail());
                } else {
                    System.out.println("Doctor not found.");
                }
                break;
            case 3:
                System.out.print("Enter Doctor ID: ");
                doctorId = scanner.nextInt();
                scanner.nextLine();  // consume newline
                doctor = doctorService.getDoctorById(doctorId);
                if (doctor != null) {
                    System.out.print("Enter new first name: ");
                    doctor.setFirstName(scanner.nextLine());
                    System.out.print("Enter new last name: ");
                    doctor.setLastName(scanner.nextLine());
                    System.out.print("Enter new specialty: ");
                    doctor.setSpecialty(scanner.nextLine());
                    System.out.print("Enter new email: ");
                    doctor.setEmail(scanner.nextLine());
                    doctorService.updateDoctor(doctor);
                    System.out.println("Doctor updated successfully.");
                } else {
                    System.out.println("Doctor not found.");
                }
                break;
            case 4:
                System.out.print("Enter Doctor ID: ");
                doctorId = scanner.nextInt();
                doctorService.deleteDoctor(doctorId);
                System.out.println("Doctor deleted successfully.");
                break;
            case 5:
                System.out.println("Listing All Doctors:");
                for (Doctor d : doctorService.getAllDoctors()) {
                    System.out.println(d);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageAppointments(AppointmentService appointmentService, Scanner scanner) {
        System.out.println("\nManage Appointments");
        System.out.println("1. Create Appointment");
        System.out.println("2. Read Appointment");
        System.out.println("3. Update Appointment");
        System.out.println("4. Delete Appointment");
        System.out.println("5. List All Appointments");

        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        switch (choice) {
            case 1:
                Appointment newAppointment = new Appointment();
                System.out.print("Enter patient ID: ");
                Patient patient = new Patient();
                patient.setId(scanner.nextInt());
                newAppointment.setPatient(patient);
                System.out.print("Enter doctor ID: ");
                Doctor doctor = new Doctor();
                doctor.setId(scanner.nextInt());
                newAppointment.setDoctor(doctor);
                scanner.nextLine();  // consume newline
                System.out.print("Enter appointment date (YYYY-MM-DD): ");
                newAppointment.setAppointmentDate(scanner.nextLine());
                System.out.print("Enter notes: ");
                newAppointment.setNotes(scanner.nextLine());
                appointmentService.createAppointment(newAppointment);
                System.out.println("Appointment created successfully.");
                break;
            case 2:
                System.out.print("Enter Appointment ID: ");
                int appointmentId = scanner.nextInt();
                Appointment appointment = appointmentService.getAppointmentById(appointmentId);
                if (appointment != null) {
                    System.out.println("Appointment ID: " + appointment.getId());
                    System.out.println("Patient ID: " + appointment.getPatient().getId());
                    System.out.println("Doctor ID: " + appointment.getDoctor().getId());
                    System.out.println("Appointment Date: " + appointment.getAppointmentDate());
                    System.out.println("Notes: " + appointment.getNotes());
                } else {
                    System.out.println("Appointment not found.");
                }
                break;
            case 3:
                System.out.print("Enter Appointment ID: ");
                appointmentId = scanner.nextInt();
                scanner.nextLine();  // consume newline
                appointment = appointmentService.getAppointmentById(appointmentId);
                if (appointment != null) {
                    System.out.print("Enter new patient ID: ");
                    patient = new Patient();
                    patient.setId(scanner.nextInt());
                    appointment.setPatient(patient);
                    System.out.print("Enter new doctor ID: ");
                    doctor = new Doctor();
                    doctor.setId(scanner.nextInt());
                    appointment.setDoctor(doctor);
                    scanner.nextLine();  // consume newline
                    System.out.print("Enter new appointment date (YYYY-MM-DD): ");
                    appointment.setAppointmentDate(scanner.nextLine());
                    System.out.print("Enter new notes: ");
                    appointment.setNotes(scanner.nextLine());
                    appointmentService.updateAppointment(appointment);
                    System.out.println("Appointment updated successfully.");
                } else {
                    System.out.println("Appointment not found.");
                }
                break;
            case 4:
                System.out.print("Enter Appointment ID: ");
                appointmentId = scanner.nextInt();
                appointmentService.deleteAppointment(appointmentId);
                System.out.println("Appointment deleted successfully.");
                break;
            case 5:
                System.out.println("Listing All Appointments:");
                for (Appointment a : appointmentService.getAllAppointments()) {
                    System.out.println(a);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageOffices(OfficeService officeService, Scanner scanner) {
        System.out.println("\nManage Offices");
        System.out.println("1. Create Office");
        System.out.println("2. Read Office");
        System.out.println("3. Update Office");
        System.out.println("4. Delete Office");
        System.out.println("5. List All Offices");

        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        switch (choice) {
            case 1:
                Office newOffice = new Office();
                System.out.print("Enter location: ");
                newOffice.setLocation(scanner.nextLine());
                System.out.print("Enter phone: ");
                newOffice.setPhone(scanner.nextLine());
                System.out.print("Enter doctor ID: ");
                Doctor doctor = new Doctor();
                doctor.setId(scanner.nextInt());
                newOffice.setDoctor(doctor);
                officeService.createOffice(newOffice);
                System.out.println("Office created successfully.");
                break;
            case 2:
                System.out.print("Enter Office ID: ");
                int officeId = scanner.nextInt();
                Office office = officeService.getOfficeById(officeId);
                if (office != null) {
                    System.out.println("Office ID: " + office.getOfficeId());
                    System.out.println("Location: " + office.getLocation());
                    System.out.println("Phone: " + office.getPhone());
                    System.out.println("Doctor ID: " + office.getDoctor().getId());
                } else {
                    System.out.println("Office not found.");
                }
                break;
            case 3:
                System.out.print("Enter Office ID: ");
                officeId = scanner.nextInt();
                scanner.nextLine();  // consume newline
                office = officeService.getOfficeById(officeId);
                if (office != null) {
                    System.out.print("Enter new location: ");
                    office.setLocation(scanner.nextLine());
                    System.out.print("Enter new phone: ");
                    office.setPhone(scanner.nextLine());
                    officeService.updateOffice(office);
                    System.out.println("Office updated successfully.");
                } else {
                    System.out.println("Office not found.");
                }
                break;
            case 4:
                System.out.print("Enter Office ID: ");
                officeId = scanner.nextInt();
                officeService.deleteOffice(officeId);
                System.out.println("Office deleted successfully.");
                break;
            case 5:
                System.out.println("Listing All Offices:");
                for (Office o : officeService.getAllOffices()) {
                    System.out.println(o);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
