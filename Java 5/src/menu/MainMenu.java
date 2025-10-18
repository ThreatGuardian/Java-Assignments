package menu;

import reservation.*;
import java.util.*;

public class MainMenu extends ReservationSystem {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public Ticket bookTicket(Passenger passenger, String trainNo) {
        Train selectedTrain = findTrainByNo(trainNo);
        if (selectedTrain == null) {
            System.out.println("\nInvalid train number. Please try again.");
            return null;
        }
        Ticket ticket = new Ticket(ticketCounter++, passenger, selectedTrain);
        bookedTickets.add(ticket);
        System.out.println("\nTicket booked successfully!");
        System.out.println(ticket);
        return ticket;
    }

    @Override
    public boolean cancelTicket(int ticketId) {
        for (Ticket ticket : bookedTickets) {
            if (ticket.getTicketId() == ticketId) {
                bookedTickets.remove(ticket);
                System.out.println("\nTicket ID " + ticketId + " canceled successfully.");
                return true;
            }
        }
        System.out.println("\nTicket not found.");
        return false;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n===== IRCTC RAILWAY RESERVATION =====");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Booked Tickets");
            System.out.println("4. View Available Trains");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    viewAvailableTrains();
                    System.out.print("\nEnter Train Number: ");
                    String trainNo = sc.nextLine();
                    System.out.print("Enter Passenger Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Gender (M/F/O): ");
                    String gender = sc.nextLine();
                    Passenger passenger = new Passenger(name, age, gender);
                    bookTicket(passenger, trainNo);
                }
                case 2 -> {
                    System.out.print("\nEnter Ticket ID to cancel: ");
                    int ticketId = Integer.parseInt(sc.nextLine());
                    cancelTicket(ticketId);
                }
                case 3 -> viewBookedTickets();
                case 4 -> viewAvailableTrains();
                case 5 -> {
                    System.out.println("\nThank you for using IRCTC Reservation System!");
                    return;
                }
                default -> System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
}
