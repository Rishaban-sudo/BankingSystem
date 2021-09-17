package com.bank.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BranchDAOImpl extends DaoImpl implements BranchDAO {

    @Override
    public void displayBranchInfo() {
        try (Statement st = con.createStatement()) {

            ResultSet rs =  st.executeQuery("SELECT * FROM branch");

            System.out.println("branch_id    " + "name    " + "address    " + "city    "+ "state    " + "zip    ");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "    " +
                                   rs.getString(2) + "    "+
                                   rs.getString(3) + "    " +
                                   rs.getString(4) + "    "+
                                   rs.getString(5) + "    "+
                                   rs.getString(6) + "    ");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
