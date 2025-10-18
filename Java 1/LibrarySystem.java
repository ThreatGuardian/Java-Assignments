import java.util.Scanner;

class Book {
    String isbn;
    String title;
    String author;
    boolean issued;

    Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.issued = false;
    }
}

class LibraryManager {
    Book[] books = new Book[10];
    int bookCount = 0;

    void addBook(String isbn, String title, String author) {
        if (bookCount < books.length) {
            books[bookCount++] = new Book(isbn, title, author);
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Library is full.");
        }
    }

    void viewBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("Books in Library:");
        for (int i = 0; i < bookCount; i++) {
            Book b = books[i];
            System.out.println((i + 1) + ". " + b.title + " by " + b.author +
                " [ISBN: " + b.isbn + "] - " + (b.issued ? "Issued" : "Available"));
        }
    }

    void issueBook(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isbn.equals(isbn)) {
                if (!books[i].issued) {
                    books[i].issued = true;
                    System.out.println("Book issued: " + books[i].title);
                } else {
                    System.out.println("Book is already issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    void returnBook(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isbn.equals(isbn)) {
                if (books[i].issued) {
                    books[i].issued = false;
                    System.out.println("Book returned: " + books[i].title);
                } else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    void searchByTitle(String title) {
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].title.equalsIgnoreCase(title)) {
                found = true;
                System.out.print("Found: " + books[i].title + " by " + books[i].author +
                    " [ISBN: " + books[i].isbn + "]");
                System.out.println(books[i].issued ? " - Currently issued." : " - Available.");
            }
        }
        if (!found) {
            System.out.println("No book found with that title.");
        }
    }

    void checkAvailability(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isbn.equals(isbn)) {
                if (!books[i].issued) {
                    System.out.println("Notification: Book '" + books[i].title + "' is available.");
                } else {
                    System.out.println("Book '" + books[i].title + "' is currently issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManager library = new LibraryManager();
        int choice = 0;

        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search by Title");
            System.out.println("6. Check Availability by ISBN");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            String choiceStr = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    library.addBook(isbn, title, author);
                    break;
                case 2:
                    library.viewBooks();
                    break;
                case 3:
                    System.out.print("Enter ISBN to issue: ");
                    String issueIsbn = scanner.nextLine();
                    library.issueBook(issueIsbn);
                    break;
                case 4:
                    System.out.print("Enter ISBN to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnIsbn);
                    break;
                case 5:
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();
                    library.searchByTitle(searchTitle);
                    break;
                case 6:
                    System.out.print("Enter ISBN to check availability: ");
                    String checkIsbn = scanner.nextLine();
                    library.checkAvailability(checkIsbn);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 7);

        scanner.close();
    }
}