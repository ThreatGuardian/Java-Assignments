package reservation;

public class Ticket {
    private final int ticketId;
    private final Passenger passenger;
    private final Train train;

    public Ticket(int ticketId, Passenger passenger, Train train) {
        this.ticketId = ticketId;
        this.passenger = passenger;
        this.train = train;
    }

    public int getTicketId() { return ticketId; }
    public Passenger getPassenger() { return passenger; }
    public Train getTrain() { return train; }

    @Override
    public String toString() {
        return "\n------ IRCTC TICKET ------" +
               "\nTicket ID   : " + ticketId +
               "\nPassenger   : " + passenger.getName() +
               "\nAge/Gender  : " + passenger.getAge() + " / " + passenger.getGender() +
               "\nTrain       : " + train.getTrainNo() + " - " + train.getTrainName() +
               "\nRoute       : " + train.getSource() + " → " + train.getDestination() +
               "\nFare Paid   : ₹" + train.getFare() +
               "\n--------------------------";
    }
}