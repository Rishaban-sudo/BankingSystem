package dao;

import com.model.Customer;
import utils.MySQLConnection;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private Connection con = null;


    @Override
    public void connect() {
        try {
            con = MySQLConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> customerList = new LinkedList<>();

        try (Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT * FROM customer");

            while (rs.next()) {
                customerList.add(Customer.fromResultSet(rs));
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    @Override
    public void addCustomer(Customer customer) {

        String sql = "INSERT INTO customer(cust_id, fname, lname, city, zipcode, state, contact_no,email,password) " +
                     "VALUES(NULL,?,?,?,?,?,?,?,?)";

        try (PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, customer.getFname());
            st.setString(2, customer.getLname());
            st.setString(3, customer.getCity());
            st.setString(4, customer.getZipCode());
            st.setString(5, customer.getState());
            st.setString(6, customer.getContactNo());
            st.setString(7,customer.getEmail());
            st.setString(8,customer.getPassword());

            st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer authenticateCustomer(String email, String password) {

        try (PreparedStatement st = con.prepareStatement("SELECT * FROM customer " +
                                                         "WHERE email=? AND password=?")) {

            st.setString(1,email);
            st.setString(2,password);

            ResultSet rs = st.executeQuery();

            if(rs.next()) {
                return Customer.fromResultSet(rs);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
