import java.util.Random;
import java.util.Scanner;

class BankAccount {
    String accountName;
    double balance;

    public BankAccount(String accName, double initialDeposit){
        accountName = accName;
        balance = initialDeposit;
    }

    public String getAccountName(){
        return accountName;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        if(amount <= 0){
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        balance += amount;
        System.out.println("Successfully deposited R" + amount);
        System.out.println("New balance: R" + balance);
    }

    public void withdraw(double amount){
        if(amount <= 0){
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }
        if(amount > balance){
            System.out.println("Insufficient funds. Your balance is R" + balance);
            return;
        }
        balance -= amount;
        System.out.println("Successfully withdrew R" + amount);
        System.out.println("New balance: R" + balance);
    }

    public void checkBalance(){
        System.out.println("Account: " + accountName);
        System.out.println("Balance: R" + balance);
    }
}

class Customer {
    String nameOfUser;
    String userSurname;
    char genderOf;
    String dateOfBirth;
    BankAccount account;

    public Customer(String name, String surname, char gender, String dob, double initialDeposit){
        nameOfUser = name;
        userSurname = surname;
        genderOf = gender;
        dateOfBirth = dob;
        account = new BankAccount(generateAccountName(), initialDeposit);
    }

    public String generateAccountName(){
        Random random = new Random();
        int addOnRandom = random.nextInt(9000) + 1000;

        char initialName = Character.toUpperCase(nameOfUser.charAt(0));
        char initialSurname = Character.toUpperCase(userSurname.charAt(0));

        String dobDigits = "";
        for(int i = 0; i < dateOfBirth.length(); i++){
            char ch = dateOfBirth.charAt(i);
            if(Character.isDigit(ch)){
                dobDigits = dobDigits + ch;
            }
        }

        return "" + initialName + initialSurname + dobDigits + addOnRandom;
    }

    public String getTitle(){
        if(genderOf == 'M' || genderOf == 'm'){
            return "Mr";
        } else {
            return "Ms";
        }
    }

    public BankAccount getAccount(){
        return account;
    }

    public String getFullName(){
        return nameOfUser + " " + userSurname;
    }
}

public class SimpleBankingSystem {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to Simple Banking System ===");
        System.out.println("Enter your name: ");
        String nameOfUser = scanner.nextLine();

        System.out.println("Enter your surname: ");
        String userSurname = scanner.nextLine();

        System.out.println("Enter your gender(M/F): ");
        char genderOf = scanner.next().charAt(0);
        scanner.nextLine();

        System.out.println("Enter your date of birth(dd/mm/yyyy): ");
        String dateOfBirth = scanner.nextLine();

        System.out.println("Enter your initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine();

        Customer customer = new Customer(nameOfUser, userSurname, genderOf, dateOfBirth, initialDeposit);
        BankAccount account = customer.getAccount();

        System.out.println("\nAccount created successfully!");
        System.out.println("Welcome, " + customer.getTitle() + " " + customer.getFullName());
        System.out.println("Your account name: " + account.getAccountName());

        int choice = 0;
        while(choice != 4){
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.println("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1){
                System.out.println("Enter deposit amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                account.deposit(amount);
            } else if(choice == 2){
                System.out.println("Enter withdrawal amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                account.withdraw(amount);
            } else if(choice == 3){
                account.checkBalance();
            } else if(choice == 4){
                System.out.println("Thank you for banking with us, " + customer.getTitle() + " " + customer.getFullName() + ". Goodbye!");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}