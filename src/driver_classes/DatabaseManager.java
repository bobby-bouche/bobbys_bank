package driver_classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import data_classes.Bank;
import data_classes.BankAccount;
import keyboard_class.Keyboard;

public class DatabaseManager {
	
	// DatabaseManager fields
	private static List<Bank> banks;
	private static Map<Integer, BankAccount> bankAccounts;
	
	private static Keyboard kb;
	Connection connection;
	
	
	// singleton instance variable
	private static DatabaseManager instance;
	
	
	// constructor
	private DatabaseManager() {
		super();
		kb 	  = new Keyboard();
		banks = new ArrayList<>();
	}

	
	// singleton method to ensure only one instance of DatabaseManager is created
	public static DatabaseManager getInstance() {
		if(instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	
	
	// getter methods
	public List<Bank> getBanks(){
		return banks;
	}
	
	public Map<Integer, BankAccount> getAccounts(){
		return bankAccounts;
	}
	
	
	
	// method to create database connection
	Connection connectDB() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bobby's_bank", "root", "Ronaldo");
		}
		catch(ClassNotFoundException | SQLException e){
			e.getMessage();
		}
		return con;
	}
	
	
	// TODO method to read all data from database
	void readAllDataFromDatabase() {
		
		connection = connectDB();
		
		
		
	}
	
	// TODO Smethod to retrieve account data
	public static void readAccountData(Connection con) throws SQLException {
		
		List<BankAccount> accounts = new ArrayList<>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT");
		
		while(rs.next()) {
			
			BankAccount account = new BankAccount();
		}
		
	}
	
	
	
	
	// TODO method to retieve bank data
	public static List<Bank> readBankData(Connection con, List<BankAccount> accounts) throws SQLException {
		
		List<Bank> banks = new ArrayList<>();
		Statement stmt 	 = con.createStatement();
		ResultSet rs     = stmt.executeQuery("SELECT * FROM BANK");
		
		while(rs.next()) {
			
			Bank bank = new Bank();
			bank.setBankID(rs.getInt("bankID"));
			bank.setName( rs.getString("name"));
			
			
			banks.add(bank);
		}
		rs.close();
		stmt.close();
		
		return banks;
	}
	
	
	
	
}
