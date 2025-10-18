import java.util.Scanner;

class TicketCounter {
    private int availableTickets;

    public TicketCounter(int totalTickets) {
        this.availableTickets = totalTickets;
    }

    public synchronized void bookTicket(String user, String movie, int tickets) {
        try {
            System.out.println("\nProcessing booking for " + user + "...");
            Thread.sleep(1500);
            if (tickets <= availableTickets) {
                System.out.println("Generating ticket for " + user + "...");
                Thread.sleep(1000);
                System.out.println("Booking Confirmed | Name: " + user + " | Movie: " + movie + " | Tickets: " + tickets);
                availableTickets -= tickets;
            } else if (availableTickets > 0) {
                System.out.println("Booking Failed | " + user + ", only " + availableTickets + " ticket(s) left!");
            } else {
                System.out.println("Sorry " + user + ", tickets are sold out!");
            }
        } catch (InterruptedException e) {
            System.out.println("Booking interrupted for " + user);
        }
    }

    public synchronized int getAvailableTickets() {
        return availableTickets;
    }
}

class AutoBookingThread extends Thread {
    private TicketCounter counter;
    private String user;
    private String movie;
    private int tickets;

    public AutoBookingThread(TicketCounter counter, String user, String movie, int tickets) {
        this.counter = counter;
        this.user = user;
        this.movie = movie;
        this.tickets = tickets;
    }

    @Override
    public void run() {
        counter.bookTicket(user, movie, tickets);
    }
}

class AutoBookingRunnable implements Runnable {
    private TicketCounter counter;
    private String user;
    private String movie;
    private int tickets;

    public AutoBookingRunnable(TicketCounter counter, String user, String movie, int tickets) {
        this.counter = counter;
        this.user = user;
        this.movie = movie;
        this.tickets = tickets;
    }

    @Override
    public void run() {
        counter.bookTicket(user, movie, tickets);
    }
}

class InputThread extends Thread {
    private Scanner sc;
    private String[] username;
    private String[] selectedMovie;
    private int[] tickets;

    public InputThread(Scanner sc, String[] username, String[] selectedMovie, int[] tickets) {
        this.sc = sc;
        this.username = username;
        this.selectedMovie = selectedMovie;
        this.tickets = tickets;
    }

    @Override
    public void run() {
        try {
            System.out.print("Enter your name: ");
            username[0] = sc.next();

            System.out.println("\nAvailable Movies:");
            System.out.println("1. Avengers: Endgame");
            System.out.println("2. Pathaan");
            System.out.println("3. Animal");
            System.out.print("Choose your movie: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> selectedMovie[0] = "Avengers: Endgame";
                case 2 -> selectedMovie[0] = "Pathaan";
                case 3 -> selectedMovie[0] = "Animal";
                default -> selectedMovie[0] = "Unknown Movie";
            }

            System.out.print("Enter number of tickets to book: ");
            tickets[0] = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter valid data.");
        }
    }
}

public class MovieTicketBookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketCounter counter = new TicketCounter(10);

        String[] username = new String[1];
        String[] selectedMovie = new String[1];
        int[] tickets = new int[1];
        int choice;

        try {
            do {
                System.out.println("\n============ MOVIE TICKET BOOKING SYSTEM ============");
                System.out.println("1. Book Tickets");
                System.out.println("2. View Available Tickets");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        if (counter.getAvailableTickets() == 0) {
                            System.out.println("\nAll tickets are sold out!");
                            break;
                        }

                        InputThread inputThread = new InputThread(sc, username, selectedMovie, tickets);
                        inputThread.start();
                        inputThread.join();

                        Thread t1 = new AutoBookingThread(counter, "OnlineUser1", selectedMovie[0], 2);
                        Thread t2 = new Thread(new AutoBookingRunnable(counter, "OnlineUser2", selectedMovie[0], 3));
                        Thread t3 = new Thread(new AutoBookingRunnable(counter, "OnlineUser3", selectedMovie[0], 1));

                        t1.start();
                        t2.start();
                        t3.start();

                        counter.bookTicket(username[0], selectedMovie[0], tickets[0]);

                        t1.join();
                        t2.join();
                        t3.join();

                        System.out.println("\nBooking Process Completed!");
                        break;

                    case 2:
                        System.out.println("\nAvailable Tickets: " + counter.getAvailableTickets());
                        break;

                    case 3:
                        System.out.println("\nExiting... Thank you for using Movie Ticket Booking System!");
                        break;

                    default:
                        System.out.println("\nInvalid choice! Please try again.");
                }
            } while (choice != 3);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}