package com.bank.dao;

public interface BankAdminDAO extends Dao {

     static BankAdminDAO getInstance() {
         return new BankAdminDAOImpl();
     }

     boolean authenticateAdmin(String username,String password);

}
