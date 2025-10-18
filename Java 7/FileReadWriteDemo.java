import java.io.*;

public class FileReadWriteDemo {

    private static final String INPUT_FILE_PATH = "user_input.txt";
    private static final String OUTPUT_FILE_PATH = "processed_result.txt";

    private static void readAndProcessData() {
        System.out.println("--- Step 1: Attempting to Read Input File: " + INPUT_FILE_PATH + " ---");
        
        String line;
        
        try (FileReader fr = new FileReader(INPUT_FILE_PATH);
             BufferedReader br = new BufferedReader(fr);
             FileWriter fw = new FileWriter(OUTPUT_FILE_PATH);
             BufferedWriter bw = new BufferedWriter(fw)) {

            while ((line = br.readLine()) != null) {
                String processedLine = line.toUpperCase();
                
                bw.write(processedLine);
                bw.newLine();
                
                System.out.println("Processed Line: " + processedLine);
            }

            System.out.println("\n--- Step 2: Data processing complete. Output written to: " + OUTPUT_FILE_PATH + " ---");

        } catch (FileNotFoundException e) {
            System.err.println("Error: Input file not found at " + INPUT_FILE_PATH);
            System.err.println("Please manually create this file with text content and run the program again.");
        } catch (IOException e) {
            System.err.println("An I/O error occurred during read/write: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("!!! IMPORTANT: This program requires you to manually create a file named 'user_input.txt' in the same directory before running. !!!");
        readAndProcessData();
        
        System.out.println("\n--- Demonstration Complete ---");
    }
}
