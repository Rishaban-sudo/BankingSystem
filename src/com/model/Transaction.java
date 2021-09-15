package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {
    private int txnId;
    private String txnDateTime;
    private int srcAccountId;
    private int destAccountId;
    private float amount;


    public Transaction(int txnId, String txnDateTime, int srcAccountId, int destAccountId, float amount) {
        this.txnId = txnId;
        this.txnDateTime = txnDateTime;
        this.srcAccountId = srcAccountId;
        this.destAccountId = destAccountId;
        this.amount = amount;
    }

    public static Transaction fromResultSet(ResultSet rs) throws SQLException {
        return new Transaction(rs.getInt(1),
                rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5));
    }


    @Override
    public String toString() {
        return
                "txnId = " + txnId + "\n" +
                "txnDateTime = " + txnDateTime + "\n" +
                "srcAccountId = " + srcAccountId + "\n" +
                "destAccountId = " + destAccountId + "\n" +
                "amount = " + amount;
    }
}
