package com.capgemini.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.util.DbUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.*;
import com.capgemini.banapp.exception.*;


public class BankAccountDaoImpl implements BankAccountDao {


	private JdbcTemplate jdbcTemplate;
	
	public BankAccountDaoImpl(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate=jdbcTemplate;
	}

	
	@Override
	public boolean addNewBankAccount(BankAccount account) {
		String query = "INSERT INTO bankaccounts (customer_name,account_type,account_balance) VALUES(?,?,?)";
		int result=jdbcTemplate.update(query,new Object[] {account.getAccountHolderName(),account.getAccountType(),account.getAccountBalance()});
		
			if (result == 1){
				return true;
			}
			return false;
	}

	@Override
	public boolean updateAccountDetails(long accountId, String accountHolderName, String accountType) {
		String query= "UPDATE bankaccounts SET customer_name=? ,account_type=? WHERE account_id=?";
		int result=jdbcTemplate.update(query,new Object[] {accountHolderName,accountType,accountId});		
		if(result==1){
				return true;
		}
		return false;
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		String query = "DELETE FROM bankaccounts WHERE account_id=?";
		int result;
		
		result = jdbcTemplate.update(query,new Object[] {accountId});
			if (result == 1)
				return true;
 
		return false;
	}

	@Override
	public List<BankAccount> findAllBankAccounts() {
		String query = "SELECT * FROM bankaccounts";
		List<BankAccount> accounts= jdbcTemplate.query(query,(rs,rowNum)->{
				long id= rs.getLong(1);
				String name=rs.getString(2);
				String type= rs.getString(3);
				double balance= rs.getDouble(4);
				BankAccount bankAcc= new BankAccount(id,name,type,balance);
				return bankAcc;
			});
		
		return accounts;
	}

	@Override
	public BankAccount searchAccount(long accountId) {
		BankAccount account=null;
		try{
			String query = "SELECT * FROM bankaccounts WHERE account_id="+accountId ;
			 account=jdbcTemplate.queryForObject(query,(rs,rowNum)->{
				long id= rs.getLong(1);
				String name=rs.getString(2);
				String type= rs.getString(3);
				double balance= rs.getDouble(4);
				BankAccount bankAcc= new BankAccount(id,name,type,balance);
				return bankAcc;
			});
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		catch(EmptyResultDataAccessException ex){
			System.out.println("accoount");
		}
		return account;
	}


	/*@Override
	public double getBalance(long accountId) {
		String query = "SELECT account_balance FROM bankaccounts WHERE account_id=" + accountId;
		double balance = -1;
		// Connection connection = DbUtil.getConnection();
		try  {
			if(result.next())
				balance = result.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

	@Override
	public void updateBalance(long accountId, double newBalance) {
		String query = "UPDATE bankaccounts SET account_balance=? WHERE account_id=?";
		// Connection connection = DbUtil.getConnection();
		try  {
			statement.setDouble(1, newBalance);
			statement.setLong(2, accountId);
			int result = statement.executeUpdate();
			System.out.println("No of rows affected:" + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}*/

	/*public static void commit() {
		try {
			if (connection != null) {
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback() {
		try {
			if (connection != null) {
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

	

}
