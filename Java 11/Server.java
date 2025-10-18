import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 5563;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chaitanya is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                String clientMessage = reader.readLine();
                System.out.println("Received from client: " + clientMessage);

                String response = "Hello, ! You sent: " + clientMessage;
                writer.println(response);

                socket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
