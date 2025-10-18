import java.rmi.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Calculator stub = (Calculator) Naming.lookup("rmi://localhost:5000/calculator");
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter first number: ");
            int a = sc.nextInt();

            System.out.print("Enter second number: ");
            int b = sc.nextInt();

            System.out.println("Sum: " + stub.add(a, b));
            System.out.println("Difference: " + stub.subtract(a, b));

        } catch (Exception e) {
            System.out.println("Client Exception: " + e);
            e.printStackTrace();
        }
    }
}
