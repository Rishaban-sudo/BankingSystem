package dao;

import com.model.Account;

import java.util.List;

public interface AccountDAO extends Dao {

    static AccountDAO getInstance() {
        return new AccountDAOImpl();
    }

    void addAccount(Account account);
    List<Account> getAllAccounts();
    List<Account> getCustomerAccounts(int custId);
    void deposit(float amount,int accountId);
    void withdraw(float amount,int accountId);
    void updateLastActivityDate(int accountId);
    boolean deleteAccount(int accountId);
}
