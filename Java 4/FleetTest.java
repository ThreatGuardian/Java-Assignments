import java.util.Scanner;

interface Vehicle {
    void start();
    void stop();
    int getSpeed();
    String getFuelType();
}

interface Maintenance {
    void performMaintenance();
}

class Car implements Vehicle, Maintenance {
    private int speed;
    private String fuelType;

    Car(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    public void start() {
        System.out.println("Car started.");
    }

    public void stop() {
        System.out.println("Car stopped.");
    }

    public int getSpeed() {
        return speed;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void performMaintenance() {
        System.out.println("Car maintenance completed.");
    }
}

class Bus implements Vehicle, Maintenance {
    private int speed;
    private String fuelType;

    Bus(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    public void start() {
        System.out.println("Bus started.");
    }

    public void stop() {
        System.out.println("Bus stopped.");
    }

    public int getSpeed() {
        return speed;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void performMaintenance() {
        System.out.println("Bus maintenance completed.");
    }
}

class Motorcycle implements Vehicle {
    private int speed;
    private String fuelType;

    Motorcycle(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    public void start() {
        System.out.println("Motorcycle started.");
    }

    public void stop() {
        System.out.println("Motorcycle stopped.");
    }

    public int getSpeed() {
        return speed;
    }

    public String getFuelType() {
        return fuelType;
    }
}

public class FleetTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vehicle[] fleet = new Vehicle[3];

        System.out.println("Enter Car speed:");
        int carSpeed = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Car fuel type:");
        String carFuel = sc.nextLine();
        fleet[0] = new Car(carSpeed, carFuel);

        System.out.println("Enter Bus speed:");
        int busSpeed = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Bus fuel type:");
        String busFuel = sc.nextLine();
        fleet[1] = new Bus(busSpeed, busFuel);

        System.out.println("Enter Motorcycle speed:");
        int motoSpeed = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Motorcycle fuel type:");
        String motoFuel = sc.nextLine();
        fleet[2] = new Motorcycle(motoSpeed, motoFuel);

        System.out.println("\n--- Fleet Details ---");
        for (Vehicle v : fleet) {
            v.start();
            System.out.println("Speed: " + v.getSpeed() + " km/h");
            System.out.println("Fuel Type: " + v.getFuelType());
            v.stop();
            if (v instanceof Maintenance) {
                ((Maintenance) v).performMaintenance();
            }
            System.out.println();
        }
        sc.close();
    }
}