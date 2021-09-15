package dao;

import java.util.Map;

public interface AccountTypeDAO extends Dao {
    static AccountTypeDAO getInstance() {
        return new AccountTypeDAOImpl();
    }

    Map<String,String> getAccountTypes();

}
