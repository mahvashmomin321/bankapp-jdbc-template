package com.capgemini.bankapp.dao;

import java.util.List;

import com.capgemini.bankapp.model.BankAccount;

public interface BankAccountDao {
	
	public boolean addNewBankAccount(BankAccount account);
	public boolean updateAccountDetails(long accountId,String accountHolderName, String accountType);
	public boolean deleteBankAccount(long accountId);
	public List<BankAccount> findAllBankAccounts();
	public BankAccount searchAccount(long accountId);
	/*public double getBalance(long accountId);
	public void updateBalance(long accountId,double newBalance);
	public static void commit();
	public static void rollback();*/
}
