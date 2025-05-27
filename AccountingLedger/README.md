<h1 align="center">Brenda's Accounting Ledger App</h1>

This is a simple accounting ledger application built in Java. It allows users to add, view, and manage financial transactions via a text-based menu. Users can track deposits and payments, generate reports (month-to-date, year-to-date, etc.), and search by vendor. All transactions are saved to and loaded from a file.

### Home Screen

<table>
  <tr>
    <td align="center" width="1000">
      <img src="https://github.com/brendavvng/AccountingLedger/blob/main/images/AccountingLedger_HomeScreen.png?raw=true" width="380"/><br/>
      <sub><i>Displays home screen options</i></sub>
</table>

### Ledger Screen

<table>
  <tr>
    <td align="center" width="500">
      <img src="https://github.com/brendavvng/AccountingLedger/blob/main/images/AccountingLedger_LedgerScreen.png?raw=true" width="450" height=300"/><br/>
      <sub><i>Displays the Ledger options</i></sub>
    </td>
<td align="center" width="500">
      <img src="https://github.com/brendavvng/AccountingLedger/blob/main/images/AccountingLedger_LedgerScreen_AllOption.png?raw=true" width="450"/><br/>
      <sub><i>Displays ALL of the Ledger entries</i></sub>
   </td>
  </tr>
</table>


### Transactions Screens


<table>
  <tr>
    <td align="center" width="500">
      <img src="https://github.com/brendavvng/AccountingLedger/blob/main/images/AccountingLedger_LedgerScreen_DepositOption.png?raw=true" width="450"/><br/>
      <sub><i>Displays deposits only</i></sub>
    </td>
<td align="center" width="500">
      <img src="https://github.com/brendavvng/AccountingLedger/blob/main/images/AccountingLedger_LedgerScreen_PaymentOption.png?raw=true" width="450" height="455"/><br/>
      <sub><i>Displays payments only</i></sub>
   </td>
  </tr>
</table>


### Report Screen

<table>
  <tr>
    <td align="center" width="1000">
      <img src="https://github.com/brendavvng/AccountingLedger/blob/main/images/AccountingLedger_ReportScreen.png?raw=true" width="380"/><br/>
      <sub><i>Displays report menu options</i></sub>
</table>

### Exit System

<table>
  <tr>
    <td align="center" width="1000">
      <img src="https://github.com/brendavvng/AccountingLedger/blob/main/images/AccountingLedger_ExitSystem.png?raw=true" width="380"/><br/>
      <sub><i>Displays the exit screen when user decides to exit the system</i></sub>
</table>


## Interesting Code Snippet

Here’s one part of the project that I found interesting—how transactions are parsed from a line in a CSV file:

```java
private static void generateMonthToDateReport() {
    System.out.println("\nMonth To Date Report:");
    LocalDate now = LocalDate.now();

    List<Transactions> mtdTransactions = loadTransactions().stream()
        .filter(transaction -> transaction.getDate().getYear() == now.getYear()
            && transaction.getDate().getMonth() == now.getMonth())
        .collect(Collectors.toList());

    displayTransactions(mtdTransactions);
}
```


<b><i>This line of code is interesting because it uses the Stream API in Java, which is a powerful way to process data in a very efficient and readable way.</b></i>

The Java Stream API lets you process collections of data in a clean, functional way—like filtering, transforming, and summarizing—without writing explicit loops.

### *What Each Line of Code is Doing:*

- loadTransactions() loads all the transactions from a file. loadTransactions() simplifies your code by abstracting the data-fetching process, making it easier to maintain, update, and work with transaction data without worrying about where it comes from.

- .stream() turns the list into a stream for processing. Streams allow you to process a collection of data (like your transactions) without needing explicit loops, making the code cleaner and easier to understand.

- .filter(...) narrows the results to only the current month and year. The filter() method is particularly useful because it allows you to quickly pick out items that match certain criteria—in this case, transactions from the current month.

- .collect(Collectors.toList()) converts the stream back into a List for display. Using collect(Collectors.toList()) makes it easy to gather the filtered results back into a list that you can use later in your program.
