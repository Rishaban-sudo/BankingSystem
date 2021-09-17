package com.bank.dao;

import com.bank.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DaoImpl implements Dao {

    protected Connection con = null;

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
}
