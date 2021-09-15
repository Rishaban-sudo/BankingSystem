package flow;

import com.bank.Bank;
import dao.BankAdminDAO;

import java.util.Scanner;

public class AdminFlow implements Flow {
    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        Bank bank = Bank.getInstance();
        BankAdminDAO bankAdminDAO = BankAdminDAO.getInstance();
        bankAdminDAO.connect();

        System.out.println("Enter username :");
        String username = sc.next();

        System.out.println("Enter password : ");
        String password = sc.next();

        if(bankAdminDAO.authenticateAdmin(username,password)) {
            int ch;
            while (true) {
                System.out.println("1.Display all customers");
                System.out.println("2.View all accounts");
                System.out.println("3.View All transactions");
                System.out.println("4.Exit");
                ch=sc.nextInt();

                switch (ch) {
                    case 1:
                        bank.displayAllCustomers();
                        break;
                    case 2:
                        bank.viewAllAccounts();
                        break;
                    case 3:
                        bank.viewAllTransactionLog();
                        break;
                    case 4:
                        bankAdminDAO.disconnect();
                        return;
                    default:
                        System.out.println("Enter valid number ");
                }

            }
        }
        else {
            bankAdminDAO.disconnect();
            System.out.println("Invalid Credentials!!!");
        }

    }
}
