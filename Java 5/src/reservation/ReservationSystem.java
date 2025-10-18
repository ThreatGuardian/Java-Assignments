package reservation;

import java.util.*;

public abstract class ReservationSystem {
    protected final List<Ticket> bookedTickets = new ArrayList<>();
    protected final List<Train> trains = new ArrayList<>();
    protected int ticketCounter = 1000;

    public ReservationSystem() {
        trains.add(new Train("12001", "Shatabdi Express", "Pune", "Mumbai", 450.0));
        trains.add(new Train("22120", "Deccan Queen", "Pune", "Mumbai", 350.0));
        trains.add(new Train("22731", "Hyderabad Express", "Pune", "Hyderabad", 800.0));
    }

    public abstract Ticket bookTicket(Passenger passenger, String trainNo);

    public abstract boolean cancelTicket(int ticketId);

    public void viewBookedTickets() {
        if (bookedTickets.isEmpty()) {
            System.out.println("\nNo tickets booked yet.");
            return;
        }
        for (Ticket ticket : bookedTickets) {
            System.out.println(ticket);
        }
    }

    public void viewAvailableTrains() {
        System.out.println("\nAvailable Trains:");
        for (Train train : trains) {
            System.out.println(train);
        }
    }

    protected Train findTrainByNo(String trainNo) {
        for (Train train : trains) {
            if (train.getTrainNo().equals(trainNo)) {
                return train;
            }
        }
        return null;
    }
}