package com.pluralsight;

import java.io.*; // for input and output operations (e.g., file reading/writing)
import java.time.LocalDate; // for working with dates
import java.time.LocalTime; // for working with times
import java.util.ArrayList; // A flexible container for storing a list of items
import java.util.Comparator; // for defining how to compare objects (for sorting)
import java.util.List; // a collection of objects in a list form
import java.util.Scanner; // for reading user input
import java.time.format.DateTimeFormatter; // for formatting dates and times into strings
import java.util.stream.Collectors; // for collecting results from streams, like filtering data

public class AccountingLedgerApp {

    // declare the scanner and datetime formatter as a static to be able to use it throughout the class.
    private static final Scanner theScanner = new Scanner(System.in);
    // file to store transaction data, defining the path to reach csv file
    private static final String ledgerFile = "src/main/resources/transactions.csv";
    // importing date and time formatters, setting formats for time and date
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        // main method to run the application, printing welcome message to user, this line will run first
        System.out.println("\nWelcome to the Account Ledger!");
        // boolean variable to control the application loop
        // this is the main loop to keep the application running
        boolean willExit = false;

        // loop that keeps running until user exits
        while (!willExit) {
            // shows menu options
            displayHomeScreen();
            System.out.print("Enter choice: ");
            // scanner reads and process user input
            // .trim() method removes any extra spaces before and after the text (string)
            // .toUpperCase() method converts all the letters in a string to uppercase.
            String userInput = theScanner.nextLine().trim().toUpperCase();
            System.out.println("-------------------------------------------");
            try {
                // switch statement checks the users input and executes appropriate function
                // for this - method is addTransaction(D); / (P); and displayLedgerScreen();
                switch (userInput) {
                    case "D":
                        // this adds a deposit
                        addTransaction("D");
                        break;
                    case "P":
                        // this adds the payments
                        addTransaction("P");
                        break;
                    case "L":
                        // this views the ledger
                        displayLedgerScreen();
                        break;
                    case "X":
                        // this ends the loop and exits program
                        willExit = true;
                        System.out.println("Exiting the system now...");
                        break;
                    default:
                        // this handles invalid inputs
                        System.out.println("Invalid input. Please try again.");
                }
            } catch (Exception e) {
                // catch and show any unexpected errors (gracefully)
                // e.getMessage = helps with debugging errors, providing the error info
                System.out.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
        // this message shows when the program ends
        System.out.println("Goodbye!");
    }

    // displaying home menu options
    private static void displayHomeScreen() {
        System.out.println("Please choose one of the following options:");
        System.out.println("-------------------------------------------");
        System.out.println("(D) - Add Deposit");
        System.out.println("(P) - Make Payment (Debit)");
        System.out.println("(L) - Ledger");
        System.out.println("(X) - Exit");
    }

    // method to handle both deposits and payments depending on user input
    private static void addTransaction(String transactionType) {
        // adds a transaction (deposit or payment) to the ledger file
        System.out.println("Enter information below:");
        System.out.print("Description: ");
        // scanner reads the transaction description then moves onto next line
        String description = theScanner.nextLine();
        System.out.print("Vendor: ");
        // scanner reads the vendor / person name, then moves onto next line
        String vendor = theScanner.nextLine();
        //  creating variable to hold the transaction amount
        double amount;
        try {
            System.out.print("Amount: ");
            // convert user input into a double (number)
            amount = Double.parseDouble(theScanner.nextLine());
            if (transactionType.equals("P")) {
                // if it's a payment, store the amount as a negative value
                amount = -Math.abs(amount);
            }
        } catch (NumberFormatException e) {
            // NumberFormatException e - catches specific error, if user enters in something that can't be ...
            // ... converted into a number i.e: letters/symbols. if user submits invalid info, line below will run
            System.out.println("Invalid amount format. Transaction not added.");
            // exit method early so we don't continue with bad input
            return;
        }

        // getting current date and time
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        // create a new transaction variable with the data we collected from user input
        Transactions transaction = new Transactions(date, time, description, vendor, amount);


        try {
            boolean fileExists = new File(ledgerFile).exists(); // Check if the ledger file already exists

            PrintWriter pw = new PrintWriter(new FileWriter(ledgerFile, true)); // Create a PrintWriter to write to the file (append mode enabled)

            if (!fileExists || new File(ledgerFile).length() == 0) {
                // write this header line if the file is new or empty
                pw.println("date|time|description|vendor|amount"); // Write a header line if the file is new or empty
            }

            // write the transaction in pipe-delimited format to the file
            pw.printf("%s|%s|%s|%s|%.2f%n",
                    transaction.getDate().format(dateFormatter),
                    transaction.getTime().format(timeFormatter),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
            // saves and close the file
            pw.close();

            System.out.print("Added to ledger. What would you like to do next? ");
        } catch (IOException e) {
            System.out.print("Error writing to file: " + e.getMessage());
        }
    }

    // loads and returns transactions from the ledger file or an empty list if the file doesn't exist
    private static List<Transactions> loadTransactions() {
        // create a list to hold transactions
        List<Transactions> transactions = new ArrayList<>();
        // create a File object using the path to the ledger file
        File file = new File(ledgerFile);
        // checking if the file does not exist
        if (!file.exists()) {
            // return an empty list if file does not exist yet (no transactions yet)
            return transactions;
        }

        //  tries to open the ledger file for reading using the buffered reader
        try (BufferedReader buffReader = new BufferedReader(new FileReader(ledgerFile))) {
            String line;
            // used to skip the header line
            boolean isFirstLine = true;
            // reads file line by line
            while ((line = buffReader.readLine()) != null) {
                if (isFirstLine) {
                    // skips header line
                    isFirstLine = false;
                    continue;
                }

                // skips empty lines
                if (line.trim().isEmpty()) continue;

                // split the line into parts using the pipe character
                String[] parts = line.split("\\|");
                // checking if the line doesnt have exactly 5 parts - date, time, description, vendor, amount
                if (parts.length != 5) {
                    // if the line doesn't have exactly 5 parts, it's not a valid transaction
                    System.out.println("Error: Invalid format in line - " + line);
                    // skips line and moves onto next if the  line doesnt have exactly 5 parts
                    continue;
                }

                // try / catch method used for handling errors
                try {
                    // parse date - converting string to a date object
                    LocalDate date = LocalDate.parse(parts[0].trim(), dateFormatter);
                    // parse time - converting string to time object
                    LocalTime time = LocalTime.parse(parts[1].trim(), timeFormatter);
                    // get description
                    String description = parts[2].trim();
                    // get vendor / person's name
                    String vendor = parts[3].trim();
                    // parse the amount - converting string into a double value
                    double amount = Double.parseDouble(parts[4].trim());

                    // add the transaction to the list
                    transactions.add(new Transactions(date, time, description, vendor, amount));
                } catch (Exception e) {
                    // if something goes wrong while parsing, show an error for that line
                    System.out.println("Error parsing line: " + line);
                }
            }
        } catch (IOException e) {
            // if there was an issue reading the file, show an error message
            System.out.print("Error reading the ledger file: " + e.getMessage());
        }

        // sort the transactions by date in descending order (most recent first)
        transactions.sort(Comparator.comparing(Transactions::getDate).reversed());
        //  line returns the list of transactions, whether it is empty or contains data
        return transactions;
    }

    // displays a list of transactions
    private static void displayTransactions(List<Transactions> transactions) {
        if (transactions.isEmpty()) {
            // if there are no transactions, display this message
            System.out.println("No transactions to display.");
            // this ends the method here if the transactions list is empty
            return;
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Date  |  Time  |  Description  |  Vendor  |  Amount  ");
        System.out.println("----------------------------------------------------");
        // loop through each transaction in the 'transactions' list
        for (Transactions transaction : transactions) {
            // printing out the transaction data in the format above
            System.out.printf("%s|%s|%s|%s|%.2f%n",
                    transaction.getDate().format(dateFormatter),
                    transaction.getTime().format(timeFormatter),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
        }
        System.out.println("------------------------------------------------------------------");
        System.out.print("What would you like to do next? ");
    }
    // displays all the transactions in the ledger
    private static void displayAllEntries() {
        System.out.println("All Ledger Entries:");
        // load all transactions
        List<Transactions> allTransactions = loadTransactions();
        // display the transactions
        displayTransactions(allTransactions);
    }

    // displays only deposit transactions
    private static void displayDeposits() {
        System.out.println("\nDeposits:");
        // load the list of transactions & convert list into a stream, allowing functional operations to be performed on the data
        List<Transactions> deposits = loadTransactions().stream()
                // only deposits have positive amounts
                .filter(transaction -> transaction.getAmount() > 0)
                // collect results into a list
                .collect(Collectors.toList());
        // display the deposits
        displayTransactions(deposits);
    }

    // displays payment (debit) transactions
    private static void displayPayments() {
        System.out.println("\nPayments (Debits):");
        // load the list of transactions & convert list into a stream
        List<Transactions> payments = loadTransactions().stream()
                // only payments have negative amounts
                .filter(transaction -> transaction.getAmount() < 0)
                // collect results into a list
                .collect(Collectors.toList());
        // display the payments
        displayTransactions(payments);
    }

    // displaying ledger screen and it's options that user will choose from
    private static void displayLedgerScreen() {
        String ledgerChoice;
        boolean backToHome = false;
        while (!backToHome) {
            System.out.println("\nLedger Options:");
            System.out.println("-------------------------------------------");
            System.out.println("(A) - All");
            System.out.println("(D) - Deposits");
            System.out.println("(P) - Payments");
            System.out.println("(R) - Reports");
            System.out.println("(H) - Back to Home");
            System.out.print("Enter ledger option: ");
            // get user input
            ledgerChoice = theScanner.nextLine().trim().toUpperCase();
            System.out.println("-------------------------------------------");
            switch (ledgerChoice) {
                case "A": // will list all transactions
                    displayAllEntries();
                    break;
                case "D": // shows deposits only
                    displayDeposits();
                    break;
                case "P": // shows payments only
                    displayPayments();
                    break;
                case "R": // go to report menu
                    displayReportsMenu();
                    break;
                case "H": // go back to the home screen
                    backToHome = true;
                    break;
                default:
                    // handles invalid input
                    System.out.println("Invalid ledger option. Please try again.");
            }
        }
    }


    private static void displayReportsMenu() {
        // displays the reports menu options
        String reportChoice;
        // keeps track of whether user wants to go back to ledger screen
        boolean backToLedger = false;
        // continue showing the menu until the user chooses to go back to the ledger
        while (!backToLedger) {
            System.out.println("\nReports Menu:");
            System.out.println("-------------------------------------------");
            System.out.println("(1) - Month To Date");
            System.out.println("(2) - Previous Month");
            System.out.println("(3) - Year To Date");
            System.out.println("(4) - Previous Year");
            System.out.println("(5) - Search by Vendor");
            System.out.println("(0) - Back to Ledger page");
            System.out.print("Enter report option: ");
            // get user input
            reportChoice = theScanner.nextLine().trim();
            System.out.println("-------------------------------------------");
            try { // starts the try block to attempt executing the following code
                switch (reportChoice) {
                    case "1": // Month to Date Report
                        generateMonthToDateReport();
                        break;
                    case "2": // Previous Month Report
                        generatePreviousMonthReport();
                        break;
                    case "3": // Year to Date Report
                        generateYearToDateReport();
                        break;
                    case "4": // Previous Year Report
                        generatePreviousYearReport();
                        break;
                    case "5": // Search by Vendor
                        searchByVendor();
                        break;
                    case "0": // Back to Ledger
                        backToLedger = true;
                        break;
                    default:
                        // handle invalid report option
                        System.out.println("Invalid report option. Please try again.");
                }
            } catch (Exception e) { // catches any exception that occurs during code block, e = error that occurs in code block
                System.out.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }

    // generates and displays the month-to-date report
    private static void generateMonthToDateReport() {
        System.out.println("\nMonth To Date Report:");
        // getting today's date
        LocalDate now = LocalDate.now();
        // load all the saved transactions and filter them to only keep the ones from the current month and year
        List<Transactions> mtdTransactions = loadTransactions().stream()
                // check if the transaction's year and month match today's year and month
                .filter(transaction -> transaction.getDate().getYear() == now.getYear() &&
                        // filter transactions for the current month
                        transaction.getDate().getMonth() == now.getMonth())
                // collect the filtered results into a new list
                .collect(Collectors.toList());
        // display the Month-to-Date transactions
        displayTransactions(mtdTransactions);
    }

    // generates and displays the previous month report
    private static void generatePreviousMonthReport() {
        System.out.println("\nPrevious Month Report:");
        LocalDate now = LocalDate.now();
        // calculate the previous month
        LocalDate previousMonth = now.minusMonths(1);
        // filter transactions to only those from the previous month and same year
        List<Transactions> previousMonthTransactions = loadTransactions().stream()
                .filter(transaction -> transaction.getDate().getYear() == previousMonth.getYear() &&
                        // filter transactions for the previous month
                        transaction.getDate().getMonth() == previousMonth.getMonth())
                .collect(Collectors.toList());
        // display the previous month's transactions
        displayTransactions(previousMonthTransactions);
    }

    // generates and displays the year-to-date report
    private static void generateYearToDateReport() {
        System.out.println("\nYear To Date Report:");
        LocalDate now = LocalDate.now();
        // filter transactions that happened this year
        List<Transactions> ytdTransactions = loadTransactions().stream()
                // filter transactions for the current year
                .filter(transaction -> transaction.getDate().getYear() == now.getYear())
                .collect(Collectors.toList());
        // display the Year-to-Date transactions
        displayTransactions(ytdTransactions);
    }

    // generates and displays the previous year report
    private static void generatePreviousYearReport() {
        System.out.println("\nPrevious Year Report:");
        LocalDate now = LocalDate.now();
        List<Transactions> previousYearTransactions = loadTransactions().stream()
                // filter transactions for the previous year
                .filter(transaction -> transaction.getDate().getYear() == now.minusYears(1).getYear())
                .collect(Collectors.toList());
        // display the previous year's transactions
        displayTransactions(previousYearTransactions);
    }

    // searches for transactions by vendor and displays the results
    private static void searchByVendor() {
        System.out.print("Enter vendor name to search: ");
        // get vendor name input from user
        String vendorName = theScanner.nextLine().trim().toLowerCase();
        System.out.println("\nTransactions for Vendor: " + vendorName);
        List<Transactions> vendorTransactions = loadTransactions().stream()
                // filter by vendor name
                .filter(transaction -> transaction.getVendor().toLowerCase().trim().contains(vendorName))
                .collect(Collectors.toList());
        // display the transactions for the vendor
        displayTransactions(vendorTransactions);
    }
}
