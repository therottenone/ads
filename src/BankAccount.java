import java.util.*;


class BankAccount {
    String accountNumber;
    String username;
    double balance;

    public BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username      = username;
        this.balance       = balance;
    }

    @Override
    public String toString() {
        return accountNumber + " | " + username + " – Balance: " + (long) balance;
    }
}


class Task1_BankAccountStorage {

    LinkedList<BankAccount> accounts = new LinkedList<>();

    public void addAccount(String accNumber, String username, double balance) {
        accounts.add(new BankAccount(accNumber, username, balance));
        System.out.println("Account added successfully");
    }

    public void displayAccounts() {
        if (accounts.isEmpty()) { System.out.println("No accounts found."); return; }
        System.out.println("Accounts List:");
        int i = 1;
        for (BankAccount a : accounts)
            System.out.println(i++ + ". " + a.username + " – Balance: " + (long) a.balance);
    }

    public BankAccount searchByUsername(String username) {
        for (BankAccount a : accounts)
            if (a.username.equalsIgnoreCase(username)) return a;
        return null;
    }

    public static void runDemo() {
        System.out.println("\nTask 1 – Bank Account Storage");
        Task1_BankAccountStorage bank = new Task1_BankAccountStorage();
        bank.addAccount("ACC001", "Ali",  150000);
        bank.addAccount("ACC002", "Sara", 220000);
        bank.displayAccounts();

        System.out.println("\nSearch for 'Ali':");
        BankAccount found = bank.searchByUsername("Ali");
        System.out.println(found != null ? "Found: " + found : "Not found");
    }
}


class Task2_DepositWithdraw extends Task1_BankAccountStorage {

    public boolean deposit(String username, double amount) {
        BankAccount acc = searchByUsername(username);
        if (acc == null) { System.out.println("Account not found."); return false; }
        if (amount <= 0) { System.out.println("Amount must be positive."); return false; }
        acc.balance += amount;
        System.out.println("Deposit: " + (long) amount);
        System.out.println("New balance: " + (long) acc.balance);
        return true;
    }

    public boolean withdraw(String username, double amount) {
        BankAccount acc = searchByUsername(username);
        if (acc == null) { System.out.println("Account not found."); return false; }
        if (amount <= 0) { System.out.println("Amount must be positive."); return false; }
        if (acc.balance < amount) { System.out.println("Insufficient balance."); return false; }
        acc.balance -= amount;
        System.out.println("Withdraw: " + (long) amount);
        System.out.println("New balance: " + (long) acc.balance);
        return true;
    }

    public static void runDemo() {
        System.out.println("\nTask 2 – Deposit & Withdraw");
        Task2_DepositWithdraw bank = new Task2_DepositWithdraw();
        bank.addAccount("ACC001", "Ali",  150000);
        bank.addAccount("ACC002", "Sara", 220000);

        System.out.println("\nEnter username: Ali");
        bank.deposit("Ali", 50000);

        System.out.println("\nEnter username: Sara");
        bank.withdraw("Sara", 70000);
    }
}


class Task3_TransactionHistory {

    Stack<String> transactionHistory = new Stack<>();

    public void addTransaction(String transaction) {
        transactionHistory.push(transaction);
        System.out.println("Transaction recorded: " + transaction);
    }

    public void undoLastTransaction() {
        if (transactionHistory.isEmpty()) { System.out.println("No transactions to undo."); return; }
        String removed = transactionHistory.pop();
        System.out.println("Undo → " + removed + " removed");
    }

    public void displayLastTransaction() {
        if (transactionHistory.isEmpty()) { System.out.println("No transactions recorded."); return; }
        System.out.println("Last transaction: " + transactionHistory.peek());
    }


    public void displayAllTransactions() {
        if (transactionHistory.isEmpty()) { System.out.println("No transactions."); return; }
        System.out.println("Transaction History (latest first):");
        ListIterator<String> it = transactionHistory.listIterator(transactionHistory.size());
        int i = 1;
        while (it.hasPrevious()) System.out.println(i++ + ". " + it.previous());
    }

    public static void runDemo() {
        System.out.println("\nTask 3 – Transaction History (Stack)");
        Task3_TransactionHistory th = new Task3_TransactionHistory();
        th.addTransaction("Deposit 50000 to Ali");
        th.addTransaction("Withdraw 20000 from Ali");
        th.addTransaction("Bill Payment 5000 by Ali");
        th.displayLastTransaction();
        th.undoLastTransaction();
        th.displayLastTransaction();
        System.out.println();
        th.displayAllTransactions();
    }
}


class Task4_BillPaymentQueue {

    Queue<String> billQueue = new LinkedList<>();

    public void addBill(String bill) {
        billQueue.offer(bill);
        System.out.println("Added: " + bill);
    }

    public void processNextBill() {
        if (billQueue.isEmpty()) { System.out.println("No bills to process."); return; }
        System.out.println("Processing: " + billQueue.poll());
    }

    public void displayQueue() {
        if (billQueue.isEmpty()) { System.out.println("Bill queue is empty."); return; }
        System.out.print("Remaining: ");
        System.out.println(String.join(", ", billQueue));
    }

    public static void runDemo() {
        System.out.println("\nTask 4 – Bill Payment Queue (Queue)");
        Task4_BillPaymentQueue bpq = new Task4_BillPaymentQueue();
        bpq.addBill("Electricity Bill");
        bpq.addBill("Internet Bill");
        bpq.addBill("Water Bill");
        bpq.processNextBill();
        bpq.displayQueue();
    }
}


class Task5_AccountOpeningQueue {

    Queue<BankAccount>    accountRequests = new LinkedList<>();
    LinkedList<BankAccount> approvedAccounts = new LinkedList<>();
    private int accCounter = 100;

    public void submitRequest(String username, double initialDeposit) {
        String accNumber = "ACC" + (accCounter++);
        accountRequests.offer(new BankAccount(accNumber, username, initialDeposit));
        System.out.println("Request submitted for: " + username + " (pending admin approval)");
    }

    public void processNextRequest() {
        if (accountRequests.isEmpty()) { System.out.println("No pending requests."); return; }
        BankAccount acc = accountRequests.poll();
        approvedAccounts.add(acc);
        System.out.println("Approved & added: " + acc.username + " [" + acc.accountNumber + "]");
    }

    public void displayPendingRequests() {
        if (accountRequests.isEmpty()) { System.out.println("No pending requests."); return; }
        System.out.println("Pending Account Requests:");
        int i = 1;
        for (BankAccount a : accountRequests)
            System.out.println(i++ + ". " + a.username + " – Initial Deposit: " + (long) a.balance);
    }

    public void displayApprovedAccounts() {
        if (approvedAccounts.isEmpty()) { System.out.println("No approved accounts yet."); return; }
        System.out.println("Approved Accounts:");
        int i = 1;
        for (BankAccount a : approvedAccounts)
            System.out.println(i++ + ". " + a);
    }

    public static void runDemo() {
        System.out.println("\nTask 5 – Account Opening Queue (Admin)");
        Task5_AccountOpeningQueue admin = new Task5_AccountOpeningQueue();
        admin.submitRequest("Aisha", 100000);
        admin.submitRequest("Omar",  50000);
        admin.submitRequest("Layla", 75000);
        admin.displayPendingRequests();
        System.out.println();
        admin.processNextRequest();
        admin.processNextRequest();
        System.out.println();
        admin.displayPendingRequests();
        admin.displayApprovedAccounts();
    }
}


class Task6_BankAccountArray {

    public static void runDemo() {
        System.out.println("\nTask 6 – BankAccount Array (Physical)");

        BankAccount[] accounts = new BankAccount[3];
        accounts[0] = new BankAccount("ACC001", "Ali",   150000);
        accounts[1] = new BankAccount("ACC002", "Sara",  220000);
        accounts[2] = new BankAccount("ACC003", "Karim", 90000);

        System.out.println("Stored Accounts (Array):");
        for (int i = 0; i < accounts.length; i++)
            System.out.println((i + 1) + ". " + accounts[i]);
    }
}


class MiniBankingMenu {

    private final Task2_DepositWithdraw       bank    = new Task2_DepositWithdraw();
    private final Task3_TransactionHistory    history = new Task3_TransactionHistory();
    private final Task4_BillPaymentQueue      bills   = new Task4_BillPaymentQueue();
    private final Task5_AccountOpeningQueue   admin   = new Task5_AccountOpeningQueue();
    private final Scanner sc = new Scanner(System.in);


    private String prompt(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private double promptDouble(String msg) {
        while (true) {
            try   { return Double.parseDouble(prompt(msg)); }
            catch (NumberFormatException e) { System.out.println("  Invalid number, try again."); }
        }
    }

    private int promptInt(String msg) {
        while (true) {
            try   { return Integer.parseInt(prompt(msg)); }
            catch (NumberFormatException e) { System.out.println("  Invalid option, try again."); }
        }
    }


    private void bankMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n            Bank Menu           ");
            System.out.println("1 – Submit Account Opening Request");
            System.out.println("2 – Deposit Money");
            System.out.println("3 – Withdraw Money");
            System.out.println("4 – Pay a Bill");
            System.out.println("5 – View Transaction History");
            System.out.println("6 – Back");

            switch (promptInt("Select: ")) {

                case 1: {
                    String user = prompt("Enter desired username: ");
                    double dep  = promptDouble("Initial deposit amount: ");
                    admin.submitRequest(user, dep);
                    break;
                }
                case 2: {
                    String user   = prompt("Enter username: ");
                    double amount = promptDouble("Deposit amount: ");
                    if (bank.deposit(user, amount))
                        history.addTransaction("Deposit " + (long) amount + " to " + user);
                    break;
                }
                case 3: {
                    String user   = prompt("Enter username: ");
                    double amount = promptDouble("Withdraw amount: ");
                    if (bank.withdraw(user, amount))
                        history.addTransaction("Withdraw " + (long) amount + " from " + user);
                    break;
                }
                case 4: {
                    String billName = prompt("Enter bill description: ");
                    bills.addBill(billName);
                    history.addTransaction("Bill Payment queued: " + billName);
                    break;
                }
                case 5:
                    history.displayAllTransactions();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }


    private void atmMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1 – Balance Enquiry");
            System.out.println("2 – Withdraw");
            System.out.println("3 – Back");

            switch (promptInt("Select: ")) {

                case 1: {
                    String user = prompt("Enter username: ");
                    BankAccount acc = bank.searchByUsername(user);
                    if (acc != null)
                        System.out.println("Current Balance: " + (long) acc.balance);
                    else
                        System.out.println("Account not found.");
                    break;
                }
                case 2: {
                    String user   = prompt("Enter username: ");
                    double amount = promptDouble("Withdraw amount: ");
                    if (bank.withdraw(user, amount))
                        history.addTransaction("ATM Withdraw " + (long) amount + " from " + user);
                    break;
                }
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }


    private void adminMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n         Admin Menu            ");
            System.out.println("1 – View Pending Account Requests");
            System.out.println("2 – Process Next Account Request");
            System.out.println("3 – View Bill Payment Queue");
            System.out.println("4 – Process Next Bill Payment");
            System.out.println("5 – View All Approved Accounts");
            System.out.println("6 – Back");

            switch (promptInt("Select: ")) {

                case 1:
                    admin.displayPendingRequests();
                    break;
                case 2:
                    if (!admin.accountRequests.isEmpty()) {
                        BankAccount pending = admin.accountRequests.peek();
                        admin.processNextRequest();
                        bank.accounts.add(new BankAccount(
                                pending.accountNumber, pending.username, pending.balance));
                        System.out.println("Account is now active in the banking system.");
                    } else {
                        admin.processNextRequest();
                    }
                    break;
                case 3:
                    bills.displayQueue();
                    break;
                case 4:
                    bills.processNextBill();
                    break;
                case 5:
                    admin.displayApprovedAccounts();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void run() {
        bank.addAccount("ACC001", "Ali",  150000);
        bank.addAccount("ACC002", "Sara", 220000);

        boolean running = true;
        while (running) {
            System.out.println("      MINI BANKING SYSTEM      ");
            System.out.println("  1 – Enter Bank               ");
            System.out.println("  2 – Enter ATM                ");
            System.out.println("  3 – Admin Area               ");
            System.out.println("  4 – Exit                     ");

            switch (promptInt("Select: ")) {
                case 1: bankMenu();  break;
                case 2: atmMenu();   break;
                case 3: adminMenu(); break;
                case 4: running = false; System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid option, try 1-4.");
            }
        }
    }
}

