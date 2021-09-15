package com.bank;


import com.model.Account;
import com.model.Customer;
import com.model.Transaction;
import dao.*;

import java.util.*;

public class Bank {

    private static Bank bank;
    private Bank() {

    }

    public static Bank getInstance() {
        return (bank ==null)? bank = new Bank(): bank;
    }

    public void regCustomer() {
        Scanner sc = new Scanner(System.in);
        CustomerDAO customerDAO = CustomerDAO.getInstance();
        customerDAO.connect();

        System.out.println("Enter details : ");

        System.out.println("Enter first name :");
        String fname = sc.next();

        System.out.println("Enter last name : ");
        String lname = sc.next();

        System.out.println("Enter city : ");
        String city = sc.next();

        System.out.println("Enter zipcode :");
        String zipcode = sc.next();

        System.out.println("Enter state code: ");
        String state = sc.next();

        System.out.println("Enter contact no :");
        String contactNo = sc.next();

        System.out.println("Enter email : ");
        String email = sc.next();

        System.out.println("Enter password :");
        String password = sc.next();

        customerDAO.addCustomer(new Customer(0,fname,lname,city,zipcode,state,contactNo,email,password));
        customerDAO.disconnect();
    }

    public void displayAllCustomers() {
        CustomerDAO customerDAO = CustomerDAO.getInstance();
        customerDAO.connect();

        List<Customer> customers = customerDAO.getAllCustomers();

        for (Customer customer : customers) {
            System.out.println("##############################");
            System.out.println(customer);
            System.out.println("##############################");
        }

        customerDAO.disconnect();
    }

    public void createAccount(int custId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the account details : ");


        AccountTypeDAO accountTypeDAO = AccountTypeDAO.getInstance();
        accountTypeDAO.connect();

        System.out.println("##### Available type of accounts :  #######");
        Map<String,String> accTypes =  accountTypeDAO.getAccountTypes();

        for (Map.Entry<String,String> entry : accTypes.entrySet()) {
            System.out.println(entry.getKey() + " " +entry.getValue());
        }


        accountTypeDAO.disconnect();
        System.out.println();

        System.out.println("Enter account type : (enter choice in short form ex:SAV)");
        String accType = sc.next();

        BranchDAO branchDAO = BranchDAO.getInstance();
        branchDAO.connect();

        branchDAO.displayBranchInfo();

        branchDAO.disconnect();

        System.out.println("Enter opened branchId : ");
        int openBranchId = sc.nextInt();

        AccountDAO accountDAO = AccountDAO.getInstance();
        accountDAO.connect();
        accountDAO.addAccount(new Account(0,accType,custId,null,null,null,null,openBranchId,0.0f));
        accountDAO.disconnect();

        System.out.println("Account Created Successfully");
    }

    public void viewAllAccounts() {
        AccountDAO accountDAO = AccountDAO.getInstance();
        accountDAO.connect();

        for(Account account : accountDAO.getAllAccounts()) {
            System.out.println("##############################");
            System.out.println(account);
            System.out.println("##############################");
        }

        accountDAO.disconnect();

    }

    public void viewCustomerAccounts(int custId) {
        AccountDAO accountDAO = AccountDAO.getInstance();
        accountDAO.connect();

        for(Account account : accountDAO.getCustomerAccounts(custId)) {
            System.out.println(account);
            System.out.println("##############################");
        }

        accountDAO.disconnect();
    }

    //checks if the account belongs to the customer
    public static boolean isValidAccountId(AccountDAO accountDAO,int accountId,int custId) {
        for (Account account : accountDAO.getCustomerAccounts(custId)) {
            if(account.getAccountId() ==accountId) {
                return true;
            }
        }
        return false;
    }


    public void deposit(int custId) {
        Scanner sc = new Scanner(System.in);
        AccountDAO accountDAO = AccountDAO.getInstance();
        accountDAO.connect();

        System.out.println("Enter the amount to be deposited : ");
        float amt = sc.nextFloat();

        System.out.println("Enter account_id : ");
        int accountId = sc.nextInt();

        if(isValidAccountId(accountDAO,accountId,custId)) {
            accountDAO.deposit(amt,accountId);
            System.out.println("Deposited Successfully !!");
        }
        else {
            System.out.println("Invalid account_id !!!!");
        }
        accountDAO.disconnect();
    }

    public void withDraw(int custId) {
        Scanner sc = new Scanner(System.in);
        AccountDAO accountDAO = AccountDAO.getInstance();
        accountDAO.connect();

        System.out.println("Enter the amount to be withdrawn : ");
        float amt = sc.nextFloat();

        System.out.println("Enter account_id : ");
        int accountId = sc.nextInt();

        if(isValidAccountId(accountDAO,accountId,custId)) {
            accountDAO.withdraw(amt,accountId);
        }
        else {
            System.out.println("Invalid account_id !!!!");
        }

        accountDAO.disconnect();
    }

    public void transaction(int custId) {
        Scanner sc = new Scanner(System.in);
        TransactionDAO transactionDAO = TransactionDAO.getInstance();

        System.out.println("Enter source account_id : ");
        int srcAccountId = sc.nextInt();
        System.out.println("Enter destination account_id : ");
        int destAccountId = sc.nextInt();

        System.out.println("Enter the amount : ");
        float amt = sc.nextFloat();

        if ((transactionDAO.startTransaction(srcAccountId, destAccountId, amt,custId))) {
            System.out.println("Transaction Success");
        }
        else {
            System.out.println("Transaction Failed !!!");
        }

    }

    public void viewMyAccountTransactions(int custId) {
        Scanner sc = new Scanner(System.in);
        TransactionDAO transactionDAO = TransactionDAO.getInstance();

        System.out.println("Enter account id :");
        int accountId = sc.nextInt();

        AccountDAO accDao = AccountDAO.getInstance();
        accDao.connect();

        System.out.println("~~~~~~~ Credit ~~~~~~~~~");
        if (isValidAccountId(accDao,accountId,custId)) {
            for(Transaction transaction : transactionDAO.getTransactionLog(accountId,TransactionDAO.CDT)) {
                System.out.println("##########################");
                System.out.println(transaction);
                System.out.println("##########################");
            }
        }

        System.out.println("~~~~~~~ DBT ~~~~~~~~~");
        for(Transaction transaction : transactionDAO.getTransactionLog(accountId,TransactionDAO.DBT)) {
            System.out.println("##########################");
            System.out.println(transaction);
            System.out.println("##########################");
        }


        accDao.disconnect();
    }

    public void viewAllTransactionLog() {
        TransactionDAO transactionDAO = TransactionDAO.getInstance();

        for(Transaction transaction : transactionDAO.getAllTransactionLog()) {
            System.out.println("##########################");
            System.out.println(transaction);
            System.out.println("##########################");
        }
    }

    public void deleteAccount(int custId) {
        Scanner sc = new Scanner(System.in);
        AccountDAO accountDAO = AccountDAO.getInstance();
        accountDAO.connect();

        System.out.println("Enter the accountId : ");
        int accountId = sc.nextInt();

        if(isValidAccountId(accountDAO,accountId,custId)) {
            if (accountDAO.deleteAccount(accountId)) {
                System.out.println("Account Deleted !");
            } else {
                System.out.println("Account not Deleted !!");
            }
        } else {
            System.out.println("Invalid account !!!");
        }

    }

}
