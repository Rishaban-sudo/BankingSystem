package dao;

import utils.MySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BranchDAOImpl implements BranchDAO {

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
    public void displayBranchInfo() {
        try {
            Statement st = con.createStatement();
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

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
