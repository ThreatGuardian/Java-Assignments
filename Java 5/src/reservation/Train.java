package reservation;

public class Train {
    private final String trainNo;
    private final String trainName;
    private final String source;
    private final String destination;
    private final double fare;

    public Train(String trainNo, String trainName, String source, String destination, double fare) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.fare = fare;
    }

    public String getTrainNo() { return trainNo; }
    public String getTrainName() { return trainName; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public double getFare() { return fare; }

    @Override
    public String toString() {
        return trainNo + " - " + trainName + " (" + source + " → " + destination + "), Fare: ₹" + fare;
    }
}