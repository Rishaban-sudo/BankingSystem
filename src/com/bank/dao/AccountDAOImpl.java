package com.bank.dao;

import com.bank.Bank;
import com.bank.model.Account;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AccountDAOImpl extends DaoImpl implements AccountDAO {

    @Override
    public void addAccount(Account account) {

        String sql = "INSERT INTO account(account_id,acc_type_cd, cust_id, open_date, close_date, last_activity_date, status, open_branch_id, avail_balance) \n" +
                     "VALUES (NULL,?,?,CURRENT_DATE(),NULL,CURRENT_DATE(),'ACTIVE',?,0.0);";

        try (PreparedStatement st = con.prepareStatement(sql)) {

            Bank.AccountType accountType = account.getAccountType();

            st.setInt(1,accountType.ordinal());
            st.setInt(2,account.getCustId());
            st.setInt(3,account.getOpenBranchId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> getAllAccounts() {

        List<Account> accounts = new LinkedList<>();

        try (Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT * FROM account");

            while (rs.next()) {
                accounts.add(Account.fromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public List<Account> getCustomerAccounts(int custId) {
        List<Account> accounts = new LinkedList<>();

        try (Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT * FROM account WHERE cust_id = "+custId);

            while (rs.next()) {
                accounts.add(Account.fromResultSet(rs));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public void deposit(float amount,int accountId) {

        String sql = "UPDATE account " +
                     "SET avail_balance = avail_balance + ? " +
                     "WHERE account_id = ?";

        try (PreparedStatement st = con.prepareStatement(sql)) {

            st.setFloat(1,amount);
            st.setInt(2,accountId);

            st.executeUpdate();
            updateLastActivityDate(accountId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void withdraw(float amount, int accountId) {

        try (Statement st2 = con.createStatement()) {


            ResultSet rs = st2.executeQuery("SELECT avail_balance FROM account WHERE account_id="+accountId);

            float availBalance = 0.0f;
            if(rs.next()) {
                 availBalance = rs.getFloat(1);
            }
            else {
                System.out.println("Invalid account !!");
                return;
            }

            if(availBalance<amount){
                System.out.println("Insufficient balance !!!");
                return;
            }

            PreparedStatement st = con.prepareStatement("UPDATE account " +
                    "SET avail_balance = avail_balance - ? " +
                    "WHERE account_id = ?");

            st.setFloat(1,amount);
            st.setInt(2,accountId);

            st.executeUpdate();

            updateLastActivityDate(accountId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("WithDrawn Successfully !!");
    }

    @Override
    public void updateLastActivityDate(int accountId) {
        try (Statement st = con.createStatement()) {

            st.executeUpdate("""
                    UPDATE account
                    SET last_activity_date = CURRENT_DATE()
                    WHERE account_id ="""+accountId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteAccount(int accountId) {
        try(Statement st = con.createStatement()) {

            String sql = String.format("DELETE FROM account WHERE account_id = %d",accountId);
            st.executeUpdate(sql);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
