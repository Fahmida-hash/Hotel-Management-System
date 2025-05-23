
package hotelmanagementsystem;
import java.util.*;
// Main class implementing the HotelInterface
public class HotelManagementSystem implements HotelInterface {
    // Scanner for user input
    static Scanner sc = new Scanner(System.in);
    
    // Lists to store rooms, customers, and reservations
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        // Creating object of HotelManagementSystem
        HotelManagementSystem hms = new HotelManagementSystem();

        // Initializing available rooms
        hms.initializeRooms();

        // Flag to control the main menu loop
        boolean running = true;

        System.out.println("\n===== WELCOME TO SKY VIEW HOTEL  =====");

        // Main menu loop
        while (running) {
            try {
                // Displaying menu options
                System.out.println("\nMain Menu:");
                System.out.println("1. View Room Details");
                System.out.println("2. Check Room Availability");
                System.out.println("3. Book a Room");
                System.out.println("4. Make Payment");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                // Taking user input
                int choice = getIntInput();

                // Handling menu options using switch-case
                switch (choice) {
                    case 1 -> hms.displayRoomDetails();   // Show all room details
                    case 2 -> hms.checkAvailability();    // Check available rooms
                    case 3 -> hms.bookRoom();             // Start booking process
                    case 4 -> hms.makePayment();          // Proceed with payment
                    case 5 -> {
                        // Exit program
                        System.out.println("\nThank you for choosing Sky View Hotel. Have a pleasant day!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                // Catch unexpected exceptions in main menu
                System.out.println("An unexpected error occurred. Please try again. (" + e.getMessage() + ")");
                sc.nextLine(); 
            }
        }
    }

    // Method to initialize a list of rooms
    void initializeRooms() {
        rooms.add(new Room(101, "Deluxe", 2000, true));
        rooms.add(new Room(102, "Executive", 3000, true));
        rooms.add(new Room(103, "Suite", 5000, true));
        rooms.add(new Room(104, "Standard", 1500, true));
        rooms.add(new Room(105, "Presidential", 7000, true));
    }

    // Display details of all rooms
    @Override
    public void displayRoomDetails() {
        System.out.println("\n===== ROOM DETAILS =====");
        for (Room r : rooms) {
            r.displayInfo();
        }
    }

    // Check which rooms are currently available
    @Override
    public void checkAvailability() {
        System.out.println("\n===== AVAILABLE ROOMS =====");
        for (Room r : rooms) {
            if (r.isAvailable()) {
                System.out.println("Room " + r.getRoomNumber() + " (" + r.getRoomType() + ") - BDT " + r.getPrice());
            }
        }
    }

    // Handle room booking
    @Override
    public void bookRoom() {
        try {
            // Get user details and desired room number
            System.out.print("\nEnter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter contact: ");
            String contact = sc.nextLine();
            System.out.print("Enter check-in date (dd-mm-yyyy): ");
            String checkIn = sc.nextLine();
            System.out.print("Enter check-out date (dd-mm-yyyy): ");
            String checkOut = sc.nextLine();
            System.out.print("Enter room number to book: ");
            int roomNum = getIntInput();

            // Find room and book if available
            Room room = findRoom(roomNum);
            if (room != null && room.isAvailable()) {
                room.setAvailable(false); // Set room as unavailable
                Customer c = new Customer(name, contact);
                Reservation r = new Reservation(c, room, checkIn, checkOut);
                customers.add(c);
                reservations.add(r);
                System.out.println("Room allotted successfully! Please proceed to payment to confirm your booking.");
            } else {
                System.out.println("Room not available or invalid room number.");
            }
        } catch (Exception e) {
            System.out.println("Error during booking: " + e.getMessage());
        }
    }

    // Handle payment process
    @Override
    public void makePayment() {
        try {
            System.out.print("\nEnter room number for payment: ");
            int roomNum = getIntInput();

            Room room = findRoom(roomNum);
            if (room != null && !room.isAvailable()) {
                // Show room and payment details
                System.out.println("Room " + room.getRoomNumber() + " (" + room.getRoomType() + ")");
                System.out.println("Total Payment: BDT " + room.getPrice());

                double amount = -1;
                boolean validPayment = false;

                // Loop until valid payment is made
                while (!validPayment) {
                    try {
                        System.out.print("Enter amount to pay: ");
                        amount = Double.parseDouble(sc.nextLine());

                        if (amount < room.getPrice()) {
                            System.out.println("Insufficient payment. Please pay the full amount.");
                        } else if (amount > room.getPrice()) {
                            System.out.println("Overpayment detected. Please pay the exact amount: BDT " + room.getPrice());
                        } else {
                            validPayment = true; // Payment is exactly equal
                        }

                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Please enter a numeric amount.");
                    }
                }

                // Final confirmation
                System.out.println("Payment successful! Your room is booked. Thank you.");
                room.setAvailable(true); // Reset room to available after simulated stay

            } else {
                System.out.println("Invalid room or room not booked.");
            }
        } catch (Exception e) {
            System.out.println("Payment error: " + e.getMessage());
        }
    }

    // Utility method to get integer input safely
    static int getIntInput() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    // Find room by room number
    Room findRoom(int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) return r;
        }
        return null;
    }
}

