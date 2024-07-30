package data_classes;

public class BankAccount {
	
	// bankAccount fields
	private int 	accNumber;
	private String 	accType;
	private double 	accBalance;
	private String 	Fname;
	private String 	LName;
	private int 	age;
	private String 	address;
	
	
	// constructor
	public BankAccount(final int accNum, final String accType, final String firstName, final String lastName, final  int age, String address, double balance) {
		this.accNumber 	= accNum;
		this.accType 	= accType;
		this.Fname 		= firstName;
		this.LName 		= lastName;
		this.age 		= age;
		this.address 	= address;
		this.accBalance = balance;
	}

	
	// getters
	public int getAccNumber() {
		return this.accNumber;
	}
	
	public String getAccTyp() {
		return this.accType;
	}
	
	public String getFirstName() {
		return this.Fname;
	}
	
	public String getLastName() {
		return this.LName;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public double getBalane() {
		return this.accBalance;
	}
	
	
	
	// method to deposit amount
	public void depositAmount(double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Invalid amount to deposit: " + amount);
		}
		this.accBalance += amount;
	}
	
	
	// method to withraw amount
	public void withdrawAmount(double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Invalid amount to withdraw: " + amount);
		}
		if(this.accBalance - amount < 0) {
			throw new IllegalArgumentException("Insufficient funds: " + this.accBalance);
		}
		this.accBalance -= amount;
	}
	
	
	// method to transfer amount
	public void transferAmount(double amount, BankAccount account) {
		
		try {
			this.withdrawAmount(amount);
			account.depositAmount(amount);
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	@Override
	public String toString() {
		return "BankAccount [accNumber=" + accNumber + ", accType=" + accType + ", accBalance=" + accBalance
				+ ", Fname=" + Fname + ", LName=" + LName + ", age=" + age + ", address=" + address + "]";
	}
	
}
