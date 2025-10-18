import java.sql.*;

public class HospitalManagementJDBC {

    // Database credentials
    private final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";  
    private final String USER = "pradyumna";       
    private final String PASS = "pradyumna123";    
    private final String DRIVER_CLASS = "oracle.jdbc.OracleDriver";

    public void runSystem() {
        System.out.println("--- Hospital Management JDBC System Started ---");

        try {
            
            Class.forName(DRIVER_CLASS);
            System.out.println("1. JDBC Driver Loaded Successfully.");

            
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {

                System.out.println("2. Database Connection Established.");

                
                String sql = "SELECT patient_id, patient_name, age, disease FROM Patients ORDER BY patient_id";
                System.out.println("3. Executing Query: " + sql);

                
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("\n--- Patient Records ---");
                System.out.println("---------------------------------------------------------------");
                System.out.printf("| %-10s | %-20s | %-5s | %-20s |\n", "Patient ID", "Name", "Age", "Disease");
                System.out.println("---------------------------------------------------------------");

                int recordCount = 0;
                while (rs.next()) {
                    int id = rs.getInt("patient_id");
                    String name = rs.getString("patient_name");
                    int age = rs.getInt("age");
                    String disease = rs.getString("disease");

                    System.out.printf("| %-10d | %-20s | %-5d | %-20s |\n", id, name, age, disease);
                    recordCount++;
                }
                System.out.println("---------------------------------------------------------------");

                if (recordCount == 0) {
                    System.out.println("No patient records found in the database.");
                }

            } catch (SQLException e) {
                System.err.println("\n--- ERROR DURING DATABASE OPERATION ---");
                System.err.println("Message: " + e.getMessage());
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver class not found. Check your classpath for " + DRIVER_CLASS);
        }

        System.out.println("--- System Execution Completed ---");
    }

    public static void main(String[] args) {
        HospitalManagementJDBC app = new HospitalManagementJDBC();
        app.runSystem();
    }
}