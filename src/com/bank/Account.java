package com.bank;

public class Account {
    private int accountId;
    private String accountType;
    private int custId;
    private String openDate;
    private String closeDate;
    private String lastActivityDate;
    private String status;
    private int openBranchId;
    private float availBalance;

    public Account(int accountId,
                   String accountType,
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

    public int getAccountId() {
        return accountId;
    }

    public String getAccountType() {
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