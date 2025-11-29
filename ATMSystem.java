package Java;
import java.util.*;

public class ATMSystem {

    // -------- User Class --------
    class User {
        private String userId;
        private String userPin;
        private double balance;

        public User(String userId, String userPin, double balance) {
            this.userId = userId;
            this.userPin = userPin;
            this.balance = balance;
        }

        public String getUserId() {
            return userId;
        }

        public String getUserPin() {
            return userPin;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public boolean withdraw(double amount) {
            if (amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }

        public boolean transfer(User receiver, double amount) {
            if (amount <= balance) {
                balance -= amount;
                receiver.deposit(amount);
                return true;
            }
            return false;
        }
    }

    // -------- Transaction Class --------
    class Transaction {
        private String type;
        private double amount;

        public Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        public String toString() {
            return type + " : " + amount;
        }
    }

    // -------- Transaction History Class --------
    class TransactionHistory {
        private List<Transaction> history = new ArrayList<>();

        public void addTransaction(String type, double amount) {
            history.add(new Transaction(type, amount));
        }

        public void printHistory() {
            if (history.isEmpty()) {
                System.out.println("No transactions yet.");
            } else {
                System.out.println("----- Transaction History -----");
                for (Transaction t : history) {
                    System.out.println(t);
                }
            }
        }
    }

    // -------- ATM Logic --------
    private Scanner sc = new Scanner(System.in);
    private TransactionHistory history = new TransactionHistory();
    private User user = new User("1234", "5678", 10000); // default demo user

    public void start() {
        System.out.println("===== ATM SYSTEM =====");

        System.out.print("Enter User ID: ");
        String id = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        if (id.equals(user.getUserId()) && pin.equals(user.getUserPin())) {
            System.out.println("\nLogin Successful!");
            menu();
        } else {
            System.out.println("Invalid User ID or PIN!");
        }
    }

    // -------- Menu --------
    private void menu() {
        while (true) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    history.printHistory();
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double wd = sc.nextDouble();
                    if (user.withdraw(wd)) {
                        System.out.println("Withdrawal Successful!");
                        history.addTransaction("Withdraw", wd);
                    } else {
                        System.out.println("Insufficient Balance!");
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double dp = sc.nextDouble();
                    user.deposit(dp);
                    System.out.println("Deposit Successful!");
                    history.addTransaction("Deposit", dp);
                    break;

                case 4:
                    System.out.print("Enter receiver account ID (demo only): ");
                    String receiverId = sc.next();

                    System.out.print("Enter amount to transfer: ");
                    double amt = sc.nextDouble();

                    User dummyReceiver = new User(receiverId, "0000", 0);

                    if (user.transfer(dummyReceiver, amt)) {
                        System.out.println("Transfer Successful!");
                        history.addTransaction("Transfer", amt);
                    } else {
                        System.out.println("Insufficient Balance!");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using ATM. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // -------- Main Method --------
    public static void main(String[] args) {
        ATMSystem atm = new ATMSystem();
        atm.start();
    }
}
