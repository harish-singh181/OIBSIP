package Java;

import java.util.*;

public class OnlineExam {
    static Scanner sc = new Scanner(System.in);
    static String username = "user", password = "1234", name = "Student";
    static int score = 0;

    public static void main(String[] args) {
        if (login()) menu();
        else System.out.println("Too many attempts. Exiting...");
    }

    static boolean login() {
        System.out.println("---- LOGIN ----");
        for (int i = 0; i < 3; i++) {
            System.out.print("Username: ");
            String u = sc.nextLine();
            System.out.print("Password: ");
            String p = sc.nextLine();
            if (u.equals(username) && p.equals(password)) {
                System.out.println("Welcome " + name + "!");
                return true;
            }
            System.out.println("Incorrect, try again.\n");
        }
        return false;
    }

    static void menu() {
        while (true) {
            System.out.println("\n---- MENU ----");
            System.out.println("1. Update Profile");
            System.out.println("2. Start Exam");
            System.out.println("3. Logout");
            System.out.print("Choose: ");
            String input = sc.nextLine();
            if (input.equals("1")) updateProfile();
            else if (input.equals("2")) startExam();
            else if (input.equals("3")) { System.out.println("Logged out."); return; }
            else System.out.println("Invalid choice.");
        }
    }

    static void updateProfile() {
        System.out.print("Enter new name: ");
        name = sc.nextLine();
        System.out.print("Enter new username: ");
        username = sc.nextLine();
        System.out.print("Enter new password: ");
        password = sc.nextLine();
        System.out.println("Profile updated!");
    }

    static void startExam() {
        score = 0; // <-- reset score for each attempt
        System.out.println("\nExam started! (10 seconds total)");
        long end = System.currentTimeMillis() + 10000;
        boolean timedOut = false;

        if (ask("1. Java is a ___ language?", "a) Low level", "b) High level", 'b', end)) { timedOut = true; }
        if (!timedOut && ask("2. OOP stands for?", "a) Object Oriented Programming", "b) Original Open Program", 'a', end)) { timedOut = true; }
        if (!timedOut && ask("3. JVM stands for?", "a) Java Visual Machine", "b) Java Virtual Machine", 'b', end)) { timedOut = true; }

        if (timedOut) {
            System.out.println("\nTime's up! Auto-submitting...");
        } else {
            System.out.println("\nYou finished before time. Submitting...");
        }
        submit();
    }

    // returns true when time is up (caller should stop asking further questions)
    static boolean ask(String q, String o1, String o2, char correct, long endTime) {
        if (System.currentTimeMillis() > endTime) return true;

        System.out.println(q);
        System.out.println(o1);
        System.out.println(o2);
        System.out.print("Answer (a/b): ");

        String ansLine = sc.nextLine().trim().toLowerCase();
        if (System.currentTimeMillis() > endTime) return true; // double-check after input
        if (ansLine.isEmpty()) return false; // treat empty as wrong but not timeout

        char ans = ansLine.charAt(0);
        if (ans == correct) score++;
        return false;
    }

    static void submit() {
        System.out.println("\n---- Exam Submitted ----");
        System.out.println("Score: " + score + "/3");
    }
}