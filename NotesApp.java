import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class NotesApp {
	static final String FILE_NAME = "notes.txt";
	static Scanner scanner = new Scanner(System.in);

	public static void addNote() {
		System.out.println("Enter your note (type 'END' on a new line to finish):");
		StringBuilder note = new StringBuilder();

		while (true) {
			String line = scanner.nextLine();
			if (line.equalsIgnoreCase("END")) {
				break;
			}
			note.append(line).append(System.lineSeparator());
		}

		try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
			writer.write(note.toString());
			writer.write("-----\n");
			System.out.println("Note saved successfully.");
		} catch (IOException e) {
			System.out.println("Error writing note to file.");
			e.printStackTrace();
		}
	}

	public static void viewNotes() {
		System.out.println("\n--- Saved Notes ---");
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			boolean empty = true;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				empty = false;
			}

			if (empty) {
				System.out.println("[No notes found]");
			}
		} catch (IOException e) {
			System.out.println("[No notes file found or error reading it]");
		}
	}

	public static void main(String[] args) {
		boolean running = true;

		while (running) {
			System.out.println("\n--- Notes App Menu ---");
			System.out.println("1. Add Note");
			System.out.println("2. View Notes");
			System.out.println("3. Exit");

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addNote();
				break;
			case 2:
				viewNotes();
				break;
			case 3:
				running = false;
				System.out.println("Exiting Notes App.");
				break;
			default:
				System.out.println("Invalid choice. Try again.");
			}
		}
	}
}
