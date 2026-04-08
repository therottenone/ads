public class BankingSystem {

    public static void main(String[] args) {

        Task1_BankAccountStorage.runDemo();
        Task2_DepositWithdraw   .runDemo();
        Task3_TransactionHistory.runDemo();
        Task4_BillPaymentQueue  .runDemo();
        Task5_AccountOpeningQueue.runDemo();
        Task6_BankAccountArray  .runDemo();

        System.out.println("Mini Banking Menu (Interactive)");
        new MiniBankingMenu().run();
    }
}
