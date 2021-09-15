package dao;

import com.bank.Customer;

import java.util.List;

public interface CustomerDAO extends Dao {

    static CustomerDAO getInstance() {
        return new CustomerDAOImpl();
    }


    List<Customer> getAllCustomers();
    void addCustomer(Customer customer);
    Customer authenticateCustomer(String email,String password);

}
