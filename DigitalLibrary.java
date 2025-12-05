package Java;

import java.util.*;

public class DigitalLibrary {

    // ----- DATA MODELS -----
    static class Book {
        int id;
        String title;
        String author;
        boolean issued;

        Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.issued = false;
        }
    }

    // ----- DATABASE SIMULATION -----
    static Map<Integer, Book> books = new HashMap<>();
    static int bookCounter = 1;

    static Scanner sc = new Scanner(System.in);

    // ----- MAIN -----
    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== DIGITAL LIBRARY MANAGEMENT =====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> adminLogin();
                case 2 -> userMenu();
                case 3 -> {
                    System.out.println("Thank you for using Digital Library!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ----- ADMIN LOGIN -----
    static void adminLogin() {
        System.out.print("Enter Admin ID: ");
        String id = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();

        if (id.equals("admin") && pass.equals("admin123")) {
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials!");
        }
    }

    // ----- ADMIN MENU -----
    static void adminMenu() {
        while (true) {
            System.out.println("\n===== ADMIN PANEL =====");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. View Books");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> addBook();
                case 2 -> deleteBook();
                case 3 -> viewBooks();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ----- ADD BOOK -----
    static void addBook() {
        sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        Book b = new Book(bookCounter, title, author);
        books.put(bookCounter, b);

        System.out.println("Book added with ID: " + bookCounter);
        bookCounter++;
    }

    // ----- DELETE BOOK -----
    static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();

        if (books.containsKey(id)) {
            books.remove(id);
            System.out.println("Book deleted!");
        } else {
            System.out.println("Invalid Book ID!");
        }
    }

    // ----- USER MENU -----
    static void userMenu() {
        while (true) {
            System.out.println("\n===== USER PANEL =====");
            System.out.println("1. View Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> viewBooks();
                case 2 -> issueBook();
                case 3 -> returnBook();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ----- VIEW BOOKS -----
    static void viewBooks() {
        System.out.println("\n----- BOOK LIST -----");

        if (books.isEmpty()) {
            System.out.println("No books available!");
            return;
        }

        for (Book b : books.values()) {
            System.out.println("ID: " + b.id + " | Title: " + b.title +
                    " | Author: " + b.author +
                    " | Status: " + (b.issued ? "Issued" : "Available"));
        }
    }

    // ----- ISSUE BOOK -----
    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        if (!books.containsKey(id)) {
            System.out.println("Invalid Book ID!");
            return;
        }

        Book b = books.get(id);

        if (b.issued) {
            System.out.println("Book is already issued!");
        } else {
            b.issued = true;
            System.out.println("Book issued successfully!");
        }
    }

    // ----- RETURN BOOK -----
    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        if (!books.containsKey(id)) {
            System.out.println("Invalid Book ID!");
            return;
        }

        Book b = books.get(id);

        if (!b.issued) {
            System.out.println("Book was not issued!");
        } else {
            b.issued = false;

            // simple fine rule for demo: fixed amount
            System.out.println("Book returned! Fine (if late): ₹10");
        }
    }
}
