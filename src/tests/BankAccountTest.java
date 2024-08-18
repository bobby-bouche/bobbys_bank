package tests;

import data_classes.Bank;
import data_classes.BankAccount;
import internal_validation_classes.IllegalWithdrawException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountTest {
	
	private BankAccount account;
	Bank bank = new Bank("Bob's Bank");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Setting up test class");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("tearing down test class");
	}

	@BeforeEach
	void setUp() throws Exception {
		// Initialize a BankAccount object before each test
		account = new BankAccount(12345, "Savings", "John", "Doe", 36, "123 Street", 1000.0, bank);
		System.out.println("Test Starting...");
	}

	@AfterEach
	void tearDown() throws Exception {
        // Close the logger after each test
        if (account != null) {
            account.close();
        }
		System.out.println("Test Over...");
	}

	@Test
	void testConstructor() {
		assertEquals(12345, account.getAccNumber());
		assertEquals("Savings", account.getAccType());
		assertEquals("John", account.getFirstName());
		assertEquals("Doe", account.getLastName());
		assertEquals(36, account.getAge());
		assertEquals("123 Street", account.getAddress());
		assertEquals(1000.0, account.getBalance(), 0.001);
	}
	
	@Test
	void testInvalidAccountNumber() {
		assertThrows(IllegalArgumentException.class, () ->
		{
			new BankAccount(-1, "Savings", "John", "Doe", 36, "123 Street", 1000.0, bank);
		});
	}
	
	@Test
	void testInvalidAccountType() {
		assertThrows(IllegalArgumentException.class, () -> 
		{
			new BankAccount(12345, "", "John", "Doe", 36, "123 Street", 1000.0, bank);
		});
	}
	
	@Test
	void testInvalidAge() {
		assertThrows(IllegalArgumentException.class, () -> 
		{
			new BankAccount(12345, "Savings", "John", "Doe", 15, "123 Street", 1000.0, bank);
		});
	}
	
	@Test
	void testDeposit() {
		account.depositAmount(100.0);
		assertEquals(1100.0, account.getBalance(), 0.001);
	}
	
	@Test
	void testInvalidDeposit() {
		assertThrows(IllegalArgumentException.class, () -> {
			account.depositAmount(-1.0);
		});
	}
	
	@Test
	void testWithdraw() {
		account.withdrawAmount(100.0);
		assertEquals(900.0, account.getBalance(), 0.001);
	}
	
	@Test
	void testinsufficientFunds() {
		assertThrows(IllegalWithdrawException.class, () -> 
		{
			account.withdrawAmount(2000.0);
		});
	}
	
	@Test
	void testTransfer() {
		BankAccount anotherAccount = new BankAccount(111009, "Savings", "Jane", "Doe", 23, "123 Street", 1000.0, bank);
		account.transferAmount(500.0, anotherAccount);
		assertEquals(500.0, account.getBalance(), 0.001);
		assertEquals(1500.0, anotherAccount.getBalance(), 0.001);
		
	}
	
	@Test
	void testInvalidTransfer() {
		BankAccount anotherAccount = new BankAccount(111008, "Savings", "Jane", "Doe", 23, "123 Street", 1000.0, bank);
		assertThrows(IllegalWithdrawException.class, () -> 
		{
			account.transferAmount(5000.0, anotherAccount);
		});
	}

}
