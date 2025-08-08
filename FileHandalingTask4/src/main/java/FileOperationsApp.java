import java.io.*;
import java.util.Scanner;

public class   FileOperationsApp{
    private static final String file = "notes.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Notes Manager ===");
            System.out.println("1. Write Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    writeNote();
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.println("Exiting Notes Manager...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);
    }

    // Method to write a note to the file (append mode)
    private static void writeNote() {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(note + System.lineSeparator());
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read notes from the file
    private static void readNotes() {
        System.out.println("\n=== Your Notes ===");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean hasContent = false;

            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                hasContent = true;
            }

            if (!hasContent) {
                System.out.println("No notes found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes file found. Add a note first.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
