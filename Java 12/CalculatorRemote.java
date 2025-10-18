import java.rmi.*;
import java.rmi.server.*;

public class CalculatorRemote extends UnicastRemoteObject implements Calculator {
    
    protected CalculatorRemote() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }
}
