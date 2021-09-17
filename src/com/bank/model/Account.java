package com.bank.model;

import com.bank.Bank;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private int accountId;
    private Bank.AccountType accountType;
    private int custId;
    private String openDate;
    private String closeDate;
    private String lastActivityDate;
    private String status;
    private int openBranchId;
    private float availBalance;

    public Account(int accountId,
                   Bank.AccountType accountType,
                   int custId,
                   String openDate,
                   String closeDate,
                   String lastActivityDate,
                   String status,
                   int openBranchId,
                   float availBalance) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.custId = custId;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.lastActivityDate = lastActivityDate;
        this.status = status;
        this.openBranchId = openBranchId;
        this.availBalance = availBalance;
    }

    public static Account fromResultSet(ResultSet rs) throws SQLException {

        return new Account(rs.getInt(1), Bank.getAccountType(rs.getInt(2)),rs.getInt(3),
                rs.getString(4),rs.getString(5),rs.getString(6),
                rs.getString(7),rs.getInt(8),rs.getFloat(9));
    }


    public int getAccountId() {
        return accountId;
    }

    public Bank.AccountType getAccountType() {
        return accountType;
    }

    public int getCustId() {
        return custId;
    }

    public int getOpenBranchId() {
        return openBranchId;
    }

    @Override
    public String toString() {
        return
                "accountId = " + accountId + "\n" +
                "accountType = " + accountType + "\n" +
                "custId = " + custId + "\n" +
                "openDate = " + openDate + "\n" +
                "closeDate = " + closeDate + "\n" +
                "lastActivityDate = " + lastActivityDate + "\n" +
                "status = " + status + "\n" +
                "openBranchId = " + openBranchId + "\n" +
                "availBalance = " + availBalance;
    }
}