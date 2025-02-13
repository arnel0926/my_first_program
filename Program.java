import java.util.HashMap;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;
    private boolean loggedIn;

    public Account(String accountNumber, String pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0.0;
        this.loggedIn = false;
    }

    public void checkBalance() {
        System.out.printf("Current balance: $%.2f%n", balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("$%.2f deposited successfully.%n", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("$%.2f withdrawn successfully.%n", amount);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    public boolean login(String pin) {
        if (this.pin.equals(pin)) {
            loggedIn = true;
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid PIN.");
            return false;
        }
    }

    public void logout() {
        loggedIn = false;
        System.out.println("Logged out.");
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}

public class Main {
    private static HashMap<String, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                registerAccount();
            } else if (choice.equals("2")) {
                login();
            } else if (choice.equals("3")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account number already exists.");
        } else {
            accounts.put(accountNumber, new Account(accountNumber, pin));
            System.out.println("Account successfully registered.");
        }
    }

    private static void login() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        Account account = accounts.get(accountNumber);
        if (account != null && account.login(pin)) {
            performActions(account);
        } else {
            System.out.println("Account not found or invalid login.");
        }
    }

    private static void performActions(Account account) {
        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            String action = scanner.nextLine();

            if (action.equals("1")) {
                account.checkBalance();
            } else if (action.equals("2")) {
                System.out.print("Enter amount to deposit: ");
                double amount = Double.parseDouble(scanner.nextLine());
                account.deposit(amount);
            } else if (action.equals("3")) {
                System.out.print("Enter amount to withdraw: ");
                double amount = Double.parseDouble(scanner.nextLine());
                account.withdraw(amount);
            } else if (action.equals("4")) {
                account.logout();
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
