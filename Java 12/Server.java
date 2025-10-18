import java.rmi.*;

public class Server {
    public static void main(String[] args) {
        try {
            Calculator stub = new CalculatorRemote();
            Naming.rebind("rmi://localhost:5000/calculator", stub);
            System.out.println("Calculator RMI Server is running...");
        } catch (Exception e) {
            System.out.println("Server Exception: " + e);
            e.printStackTrace();
        }
    }
}
