package dao;

import utils.MySQLConnection;

import java.sql.*;

public class BankAdminDAOImpl implements BankAdminDAO {

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
    public boolean authenticateAdmin(String username, String password) {

        String sql ="SELECT * FROM bank_admin " +
                    "WHERE username=? AND password=?";

        try (PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1,username);
            st.setString(2,password);

            ResultSet rs = st.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
