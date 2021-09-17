package com.bank.dao;

import com.bank.HashPwd;


import java.sql.*;

public class BankAdminDAOImpl extends DaoImpl implements BankAdminDAO {

    @Override
    public boolean authenticateAdmin(String username, String password) {

        String sql ="SELECT * FROM bank_admin " +
                    "WHERE username=? AND password=?";

        password = HashPwd.hashPassword(password);

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
