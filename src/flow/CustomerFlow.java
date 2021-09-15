package flow;

import com.bank.Bank;
import com.model.Customer;
import dao.CustomerDAO;

import java.util.Scanner;

public class CustomerFlow implements Flow {
    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        Bank bank = Bank.getInstance();

        int ch = 0;
        CustomerDAO customerDAO = CustomerDAO.getInstance();
        customerDAO.connect();

        System.out.println("Enter email :");
        String email = sc.next();

        System.out.println("Enter password : ");
        String password = sc.next();

        Customer customer =  customerDAO.authenticateCustomer(email,password);
        customerDAO.disconnect();

        if(customer != null) {
            System.out.println("Welcome  " + customer.getFname() + " "+ customer.getLname());
            while (true) {

                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                System.out.println("1.Create account");
                System.out.println("2.Deposit");
                System.out.println("3.Withdraw");
                System.out.println("4.View my accounts ");
                System.out.println("5.Money Transfer (transaction)");
                System.out.println("6.View my transactions ");
                System.out.println("7.LogOut");
                ch = sc.nextInt();

                switch (ch) {
                    case 1:
                        bank.createAccount(customer.getId());
                        break;
                    case 2:
                        bank.deposit(customer.getId());
                        break;
                    case 3:
                        bank.withDraw(customer.getId());
                        break;
                    case 4:
                        bank.viewCustomerAccounts(customer.getId());
                        break;
                    case 5:
                        bank.transaction(customer.getId());
                        break;
                    case 6:
                        bank.viewMyAccountTransactions(customer.getId());
                        break;
                    case 7:
                        return;
                }
            }
        }
        else {
            System.out.println("Invalid credentials !!");
        }

    }
}
