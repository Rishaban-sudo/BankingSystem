package dao;

import com.bank.Transaction;

import java.util.List;

public interface TransactionDAO extends Dao{

    /* txn type */
    int DBT = 0;
    int CDT = 1;

    static TransactionDAO getInstance() {
        return new TransactionDAOImpl();
    }

    boolean startTransaction(int srcAccountId,int destAccountId,float amt,int custId);
    List<Transaction> getTransactionLog(int accountId,int txnType);
    List<Transaction> getAllTransactionLog();

}
