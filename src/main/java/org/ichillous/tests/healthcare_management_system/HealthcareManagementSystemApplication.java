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

import java.util.Scanner;


public class HealthcareManagementSystemApplication {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        // Instantiate the repositories

        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl(sessionFactory);
        OfficeRepositoryImpl officeRepository = new OfficeRepositoryImpl(sessionFactory);
        // Instantiate the services using the repositories
        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);
        OfficeService officeService = new OfficeService(officeRepository);
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Manage Patients");
        System.out.println("2. Manage Doctors");
        System.out.println("3. Manage Appointments");
        System.out.println("4. Manage Offices");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                managePatients(patientService, scanner);
                break;
            case 2:
                manageDoctors(doctorService, scanner);
                break;
            case 3:
                manageAppointments(appointmentService, doctorService, patientService, scanner);
                break;
            case 4:
                manageOffices(officeService, scanner);
            default:
                System.out.println("Invalid choice.");
        }

        sessionFactory.close();
        scanner.close();
    }
    private static void manageOffices(OfficeService officeService, Scanner scanner){
        System.out.println("1. Create Office");
        System.out.println("2. Read Office");
        System.out.println("3. Update Office");
        System.out.println("4. Delete Office");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                Office newOffice = new Office();
                System.out.println("Enter office location:");
                newOffice.setLocation(scanner.nextLine());
                System.out.println("Enter office phone number:");
                newOffice.setPhone(scanner.nextLine());
                officeService.createOffice(newOffice);
                System.out.println("Office created.");
                break;
            case 2:
                System.out.println("Enter office ID: ");
                int officeId = scanner.nextInt();
                Office office = officeService.getOfficeById(officeId);
                if (office != null) {
                    System.out.println("Office ID: " + office.getOfficeId());
                    System.out.println("Office Location: " + office.getLocation());
                    System.out.println("Office Phone Number: " + office.getPhone());

                } else {
                    System.out.println("Office not found.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    private static void managePatients(PatientService patientService, Scanner scanner) {
        System.out.println("1. Create Patient");
        System.out.println("2. Read Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");

        int choice = scanner.nextInt();
        scanner.nextLine();

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
                scanner.nextLine();
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
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageDoctors(DoctorService doctorService, Scanner scanner) {
        System.out.println("1. Create Doctor");
        System.out.println("2. Read Doctor");
        System.out.println("3. Update Doctor");
        System.out.println("4. Delete Doctor");

        int choice = scanner.nextInt();
        scanner.nextLine();

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
                scanner.nextLine();
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
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageAppointments(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService, Scanner scanner) {
        System.out.println("1. Create Appointment");
        System.out.println("2. Read Appointment");
        System.out.println("3. Update Appointment");
        System.out.println("4. Delete Appointment");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                Appointment newAppointment = new Appointment();
                System.out.print("Enter Patient ID: ");
                int patientId = scanner.nextInt();
                Patient patient = patientService.getPatientById(patientId);
                newAppointment.setPatient(patient);

                System.out.print("Enter Doctor ID: ");
                int doctorId = scanner.nextInt();
                Doctor doctor = doctorService.getDoctorById(doctorId);
                newAppointment.setDoctor(doctor);

                // Add the patient to the doctor's patient set and vice versa
                doctor.getPatients().add(patient);
                patient.getDoctors().add(doctor);

                scanner.nextLine();  // consume newline
                System.out.print("Enter appointment date (YYYY-MM-DD): ");
                newAppointment.setAppointmentDate(scanner.nextLine());
                System.out.print("Enter notes: ");
                newAppointment.setNotes(scanner.nextLine());
                appointmentService.createAppointment(newAppointment);

                // Update the doctor and patient in the database
                doctorService.updateDoctor(doctor);
                patientService.updatePatient(patient);

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
                    System.out.print("Enter new Patient ID: ");
                    patientId = scanner.nextInt();
                    patient = patientService.getPatientById(patientId);
                    appointment.setPatient(patient);

                    System.out.print("Enter new Doctor ID: ");
                    doctorId = scanner.nextInt();
                    doctor = doctorService.getDoctorById(doctorId);
                    appointment.setDoctor(doctor);

                    // Add the patient to the doctor's patient set and vice versa
                    doctor.getPatients().add(patient);
                    patient.getDoctors().add(doctor);

                    scanner.nextLine();
                    System.out.print("Enter new appointment date (YYYY-MM-DD): ");
                    appointment.setAppointmentDate(scanner.nextLine());
                    System.out.print("Enter new notes: ");
                    appointment.setNotes(scanner.nextLine());
                    appointmentService.updateAppointment(appointment);

                    // Update the doctor and patient in the database
                    doctorService.updateDoctor(doctor);
                    patientService.updatePatient(patient);

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

            default:
                System.out.println("Invalid choice.");
        }
    }
}
