import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class BMWEnquiryForm {

    // JDBC credentials
    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String USER = "pradyumna";
    private static final String PASS = "pradyumna123";
    private static final String DRIVER_CLASS = "oracle.jdbc.OracleDriver";


    public static void main(String[] args) {

        JFrame frame = new JFrame("BMW Bike Enquiry Form");

        // Labels
        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel phoneLabel = new JLabel("Phone:");
        JLabel modelLabel = new JLabel("Bike Model:");
        JLabel colorLabel = new JLabel("Preferred Color:");
        JLabel notesLabel = new JLabel("Additional Notes:");

        // Text Fields
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField colorField = new JTextField();
        JTextField notesField = new JTextField();

        JButton submitButton = new JButton("Submit Enquiry");

        // Set Bounds for Labels
        nameLabel.setBounds(50, 30, 120, 25);
        emailLabel.setBounds(50, 70, 120, 25);
        phoneLabel.setBounds(50, 110, 120, 25);
        modelLabel.setBounds(50, 150, 120, 25);
        colorLabel.setBounds(50, 190, 120, 25);
        notesLabel.setBounds(50, 230, 120, 25);

        // Set Bounds for Text Fields
        nameField.setBounds(180, 30, 180, 25);
        emailField.setBounds(180, 70, 180, 25);
        phoneField.setBounds(180, 110, 180, 25);
        modelField.setBounds(180, 150, 180, 25);
        colorField.setBounds(180, 190, 180, 25);
        notesField.setBounds(180, 230, 180, 25);

        // Button Bounds
        submitButton.setBounds(180, 270, 150, 30);

        // Add components to frame
        frame.add(nameLabel);
        frame.add(emailLabel);
        frame.add(phoneLabel);
        frame.add(modelLabel);
        frame.add(colorLabel);
        frame.add(notesLabel);

        frame.add(nameField);
        frame.add(emailField);
        frame.add(phoneField);
        frame.add(modelField);
        frame.add(colorField);
        frame.add(notesField);

        frame.add(submitButton);

        // Frame settings
        frame.setSize(450, 360);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Action Listener for Submit Button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String model = modelField.getText();
                String color = colorField.getText();
                String notes = notesField.getText();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || model.isEmpty() || color.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill all required fields!");
                    return;
                }

                try {
                    // Load JDBC Driver
                    Class.forName(DRIVER_CLASS);

                    // Connect to DB
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

                    // SQL Insert
                    String sql = "INSERT INTO BMW_ENQUIRY (NAME, EMAIL, PHONE, BIKE_MODEL, COLOR, NOTES) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, name);
                    pstmt.setString(2, email);
                    pstmt.setString(3, phone);
                    pstmt.setString(4, model);
                    pstmt.setString(5, color);
                    pstmt.setString(6, notes);

                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(frame, "Enquiry Submitted Successfully!\nDetails:\n" +
                            "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone +
                            "\nModel: " + model + "\nColor: " + color + "\nNotes: " + notes);

                    conn.close();

                    // Clear fields after submission
                    nameField.setText("");
                    emailField.setText("");
                    phoneField.setText("");
                    modelField.setText("");
                    colorField.setText("");
                    notesField.setText("");

                } catch (SQLIntegrityConstraintViolationException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Duplicate entry or constraint violation!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }
}
