package com.controller;

import com.dao.AppointmentDao;
import com.dao.PatientDao;
import com.model.AppointmentModel;
import com.model.PatientModel;

public class AppointmentController {

    public static void bookAppointment(PatientModel p, AppointmentModel a) {

        int patientId = PatientDao.addPatient(p);

        if (patientId > 0) {
            a.setPatientId(patientId);
            AppointmentDao.addAppointment(a);
            System.out.println("Appointment Booked!");
        }
    }
}