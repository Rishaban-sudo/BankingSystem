package dao;

import com.bank.Bank;
import com.model.Transaction;
import utils.MySQLConnection;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {

    private boolean hasSufficientBalance(Connection con, int srcAccountId, float amt, int custId) throws SQLException {

        AccountDAO accountDAO = AccountDAO.getInstance();
        accountDAO.connect();

        if (Bank.isValidAccountId(accountDAO, srcAccountId, custId)) {
            Statement st2 = con.createStatement();
            ResultSet rs = st2.executeQuery("SELECT avail_balance FROM account WHERE account_id= " + srcAccountId);

            float availBalance = 0.0f;
            if (rs.next()) {
                availBalance = rs.getFloat(1);
            }

            if (availBalance < amt) {
                System.out.println("Insufficient Balance !!!");
                return false;
            } else {
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean startTransaction(int srcAccountId, int destAccountId, float amt, int custId) {

        try (Connection con = MySQLConnection.getConnection()) {
            con.setAutoCommit(false);

            PreparedStatement wst = con.prepareStatement("""
                    UPDATE account
                    SET avail_balance = avail_balance - ?
                    WHERE account_id = ?""");

            PreparedStatement dst = con.prepareStatement("""
                    UPDATE account
                    SET avail_balance = avail_balance + ?
                    WHERE account_id = ?""");


            if (hasSufficientBalance(con, srcAccountId, amt, custId)) {
                wst.setFloat(1, amt);
                wst.setInt(2, srcAccountId);
                wst.executeUpdate();

                dst.setFloat(1, amt);
                dst.setInt(2, destAccountId);
                dst.executeUpdate();


                PreparedStatement st = con.prepareStatement("""
                        INSERT INTO transaction(txn_id, txn_date, src_account_id, dest_account_id, amount) 
                        VALUES (NULL,CURRENT_TIMESTAMP(),?,?,?)
                        """);

                st.setInt(1, srcAccountId);
                st.setInt(2, destAccountId);
                st.setFloat(3, amt);

                st.executeUpdate();

                PreparedStatement st2 = con.prepareStatement("""
                        UPDATE account
                        SET last_activity_date = CURRENT_DATE()
                        WHERE account_id = ?""");

                st2.setInt(1, srcAccountId);
                st2.executeUpdate();

                st2.setInt(1, destAccountId);
                st2.executeUpdate();

                con.commit();

                st2.close();
                st.close();
                dst.close();
                wst.close();

                return true;

            } else {
                con.rollback();
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return false;
    }

    @Override
    public List<Transaction> getTransactionLog(int accountId, int txnType) {

        String[] acc = {"src_account_id", "dest_account_id"};

        List<Transaction> transactions = new LinkedList<>();

        try (Connection con = MySQLConnection.getConnection()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * FROM transaction WHERE %s = %d", acc[txnType],accountId));


            while (rs.next()) {
                transactions.add(Transaction.fromResultSet(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactionLog() {

        List<Transaction> transactions = new LinkedList<>();

        try {
            Connection con = MySQLConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM transaction");

            while (rs.next()) {
                transactions.add(Transaction.fromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    @Override
    @Deprecated
    public void connect() {
    }

    @Override
    @Deprecated
    public void disconnect() {
    }
}
