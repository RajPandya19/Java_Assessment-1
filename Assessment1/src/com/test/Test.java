package com.test;

import java.util.ArrayList;
import java.util.Scanner;

import com.controller.AppointmentController;
import com.controller.BillController;
import com.dao.BillDao;
import com.model.AppointmentModel;
import com.model.BillModel;
import com.model.PatientModel;

public class Test {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== SMILECARE SYSTEM =====");
            System.out.println("1. Book Appointment");
            System.out.println("2. Generate Bill");
            System.out.println("3. View Bill History");
            System.out.println("4. Exit");

            // ✅ ADDED LINE
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    PatientModel p = new PatientModel();
                    AppointmentModel a = new AppointmentModel();

                    System.out.print("Enter Name: ");
                    p.setName(sc.next());

                    System.out.print("Enter Phone: ");
                    p.setPhone(sc.next());

                    System.out.print("Service: ");
                    a.setServiceType(sc.next());

                    System.out.print("Date: ");
                    a.setDate(sc.next());

                    System.out.print("Cost: ");
                    a.setCost(sc.nextDouble());

                    AppointmentController.bookAppointment(p, a);
                    break;

                case 2:
                    BillModel b = new BillModel();

                    System.out.print("Enter Appointment ID: ");
                    b.setAppointmentId(sc.nextInt());

                    System.out.print("Medicine Cost: ");
                    b.setMedicineCost(sc.nextDouble());

                    System.out.print("Tax: ");
                    b.setTax(sc.nextDouble());

                    System.out.print("Discount: ");
                    b.setDiscount(sc.nextDouble());

                    BillController.generateBill(b);
                    break;

                case 3:
                    System.out.print("Enter Appointment ID: ");
                    int id = sc.nextInt();

                    BillModel bill = BillDao.getBillByAppointmentId(id);

                    if (bill == null) {
                        System.out.println("No Bill Found!");
                    } else {
                        System.out.println("Medicine Cost: " + bill.getMedicineCost());
                        System.out.println("Tax: " + bill.getTax());
                        System.out.println("Discount: " + bill.getDiscount());
                        System.out.println("Total Bill: " + bill.getTotal());
                        System.out.println("---------------------------");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}