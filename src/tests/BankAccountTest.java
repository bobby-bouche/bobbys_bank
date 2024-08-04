package tests;

import data_classes.BankAccount;
import validation_classes.AgeValdationException;
import validation_classes.DoubleValidationException;
import validation_classes.IllegalWithdrawException;
import validation_classes.IntegerValidationException;
import validation_classes.InvalidDepositException;
import validation_classes.StringValidationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountTest {
	
	private BankAccount account;

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
		account = new BankAccount(12345, "Savings", "John", "Doe", 36, "123 Street", 1000.0);
		System.out.println("Test Starting...");
	}

	@AfterEach
	void tearDown() throws Exception {
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
		assertThrows(IntegerValidationException.class, () ->
		{
			new BankAccount(-1, "Savings", "John", "Doe", 36, "123 Street", 1000.0);
		});
	}

}
