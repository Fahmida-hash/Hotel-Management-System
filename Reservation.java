/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagementsystem;
import java.util.*;
/**
 *
 * @author Digital Outlet
 */
public class Reservation {
    private Customer customer;
    private Room room;
    private String checkInDate;
    private String checkOutDate;

    public Reservation(Customer customer, Room room, String checkInDate, String checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void displayReservation() {
        System.out.println("\n===== RESERVATION DETAILS =====");
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Room Type: " + room.getRoomType());
        System.out.println("Check-in Date: " + checkInDate);
        System.out.println("Check-out Date: " + checkOutDate);
    }
}
