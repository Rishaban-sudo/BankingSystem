package dao;

import utils.MySQLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class AccountTypeDAOImpl implements AccountTypeDAO {

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
    public Map<String,String> getAccountTypes() {

        Map<String,String> accTypes = new HashMap<>();

        try (Statement st = con.createStatement()) {

            ResultSet rs =  st.executeQuery("SELECT name,acc_type_cd FROM account_type");


            while (rs.next()) {
                accTypes.put(rs.getString(1), rs.getString(2));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accTypes;
    }

}
