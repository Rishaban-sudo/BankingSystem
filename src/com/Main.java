package com;

import com.bank.Bank;
import flow.AdminFlow;
import flow.CustomerFlow;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Bank bank = Bank.getInstance();
        Scanner sc = new Scanner(System.in);
        int ch;



        while (true) {
            System.out.println("------- Banking System ------------");
            System.out.println("1.Login ");
            System.out.println("2.Login as Admin");
            System.out.println("3.Register");
            System.out.println("4.Exit");
            ch= sc.nextInt();

            switch (ch) {
                case 1 -> new CustomerFlow().start();
                case 2 -> new AdminFlow().start();
                case 3 -> bank.regCustomer();
                case 4 -> System.exit(0);
                default -> System.out.println("Enter valid num !!!");
            }

        }

        /*while (true) {
            System.out.println("------- Banking System ------------");
            System.out.println("1.Register customer");
            System.out.println("2.Display all customers");
            System.out.println("3.Create account");
            System.out.println("4.View all accounts");
            System.out.println("5.Deposit");
            System.out.println("6.Withdraw");
            System.out.println("7.Exit");

            System.out.println("Enter choice");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    bank.regCustomer();
                    break;
                case 2:
                    bank.displayAllCustomers();
                    break;
                case 3:
                    bank.createAccount();
                    break;
                case 4:
                    bank.viewAllAccounts();
                    break;
                case 5:
                    bank.deposit();
                    break;
                case 6:
                    bank.withDraw();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter valid number !!");
            }
        }*/

    }
}
