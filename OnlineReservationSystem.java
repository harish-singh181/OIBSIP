package Java;
import java.util.*;

public class OnlineReservationSystem {

    // ----- Simple Database Simulation -----
    static class Reservation {
        int pnr;
        String name;
        int age;
        String trainNo;
        String trainName;
        String classType;
        String date;
        String from;
        String to;

        Reservation(int pnr, String name, int age, String trainNo, String trainName,
                    String classType, String date, String from, String to) {
            this.pnr = pnr;
            this.name = name;
            this.age = age;
            this.trainNo = trainNo;
            this.trainName = trainName;
            this.classType = classType;
            this.date = date;
            this.from = from;
            this.to = to;
        }
    }

    // Train database (train number → train name)
    static Map<String, String> trainDB = new HashMap<>();

    // Reservation storage
    static Map<Integer, Reservation> reservations = new HashMap<>();
    static int nextPNR = 1000;

    // Default login
    static final String USER = "admin";
    static final String PASS = "1234";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Setup some trains
        trainDB.put("12001", "Shatabdi Express");
        trainDB.put("22903", "Golden Temple Mail");
        trainDB.put("16591", "Hampi Express");
        trainDB.put("12711", "Pinakini Express");

        // ----- LOGIN -----
        if (!login(sc)) {
            System.out.println("Too many attempts. Exiting...");
            return;
        }

        // ----- MAIN MENU -----
        while (true) {
            System.out.println("\n===== ONLINE RESERVATION SYSTEM =====");
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    makeReservation(sc);
                    break;
                case "2":
                    cancelReservation(sc);
                    break;
                case "3":
                    System.out.println("Thank you for using the system!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ----- LOGIN FUNCTION -----
    static boolean login(Scanner sc) {
        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter Login ID: ");
            String id = sc.nextLine();

            System.out.print("Enter Password: ");
            String pw = sc.nextLine();

            if (id.equals(USER) && pw.equals(PASS)) {
                System.out.println("Login successful!\n");
                return true;
            } else {
                System.out.println("Incorrect credentials. Try again.");
            }
        }
        return false;
    }

    // ----- RESERVATION FUNCTION -----
    static void makeReservation(Scanner sc) {
        System.out.println("\n----- RESERVATION FORM -----");

        System.out.print("Passenger Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Train Number: ");
        String trainNo = sc.nextLine();

        // Auto-fill train name
        String trainName = trainDB.get(trainNo);
        if (trainName == null) {
            System.out.println("Invalid Train Number! Reservation cancelled.");
            return;
        }

        System.out.println("Train Name: " + trainName);

        System.out.print("Class Type (Sleeper/AC/General): ");
        String classType = sc.nextLine();

        System.out.print("Date of Journey (DD-MM-YYYY): ");
        String date = sc.nextLine();

        System.out.print("From: ");
        String from = sc.nextLine();

        System.out.print("To: ");
        String to = sc.nextLine();

        int pnr = nextPNR++;
        Reservation r = new Reservation(pnr, name, age, trainNo, trainName, classType, date, from, to);
        reservations.put(pnr, r);

        System.out.println("\nReservation Successful!");
        System.out.println("Your PNR Number: " + pnr);
    }

    // ----- CANCELLATION FUNCTION -----
    static void cancelReservation(Scanner sc) {
        System.out.println("\n----- CANCELLATION FORM -----");
        System.out.print("Enter PNR Number: ");
        int pnr = Integer.parseInt(sc.nextLine());

        Reservation r = reservations.get(pnr);

        if (r == null) {
            System.out.println("No reservation found for this PNR!");
            return;
        }

        // Show passenger details
        System.out.println("\nReservation Details:");
        System.out.println("Name: " + r.name);
        System.out.println("Train: " + r.trainNo + " - " + r.trainName);
        System.out.println("Date: " + r.date);
        System.out.println("From: " + r.from);
        System.out.println("To: " + r.to);

        System.out.print("\nConfirm cancellation? (yes/no): ");
        String conf = sc.nextLine();

        if (conf.equalsIgnoreCase("yes")) {
            reservations.remove(pnr);
            System.out.println("Ticket cancelled successfully!");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }
}
