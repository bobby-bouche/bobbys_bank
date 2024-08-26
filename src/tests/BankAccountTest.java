/**
 * The BankAccountTest class contains unit tests for the BankAccount class.
 * 
 * This class leverages the JUnit framework to systematically test the behavior 
 * of the BankAccount class, ensuring that the methods function correctly under 
 * various scenarios, including both valid and invalid inputs.
 * 
 * Each test is designed to verify that specific conditions are met, and exceptions 
 * are thrown as expected when invalid data is provided.
 * 
 * Setup and teardown methods are included to initialize a BankAccount instance 
 * before each test and to clean up resources after each test. This ensures that 
 * each test is isolated and does not affect others.
 * 
 * Responsibilities:
 * - Test the construction of BankAccount objects.
 * - Validate account creation with both valid and invalid data.
 * - Test deposit, withdrawal, and transfer functionality.
 * - Ensure that appropriate exceptions are thrown for invalid operations.
 * 
 * Usage:
 * - Run this test class with a JUnit test runner to verify the correctness of 
 *   the BankAccount class.
 */

package tests;

import data_classes.Bank;
import data_classes.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exception_classes.IllegalWithdrawException;

/**
 * Unit tests for the BankAccount class.
 * This class tests the constructor, deposit, withdrawal, and transfer methods,
 * as well as the validation logic for invalid inputs.
 */
class BankAccountTest {

    private BankAccount account;
    Bank bank = new Bank("Bob's Bank");

    /**
     * Initializes resources required for the entire test class.
     * This method runs once before any test methods are executed.
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("Setting up test class");
    }

    /**
     * Cleans up resources after all test methods have been executed.
     * This method runs once after all tests in the class have completed.
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("Tearing down test class");
    }

    /**
     * Initializes a BankAccount object before each test method.
     * Ensures a fresh instance of BankAccount is available for each test.
     */
    @BeforeEach
    void setUp() throws Exception {
        account = new BankAccount(12345, "Savings", "John", "Doe", 36, "123 Street", 1000.0, bank);
        System.out.println("Test Starting...");
    }

    /**
     * Closes the logger and cleans up resources after each test method.
     * Ensures no test leaves open resources that could interfere with subsequent tests.
     */
    @AfterEach
    void tearDown() throws Exception {
        if (account != null) {
            account.close();
        }
        System.out.println("Test Over...");
    }

    /**
     * Tests that the BankAccount constructor correctly initializes all fields.
     * Verifies that the account number, account type, name, age, address, and balance
     * are set to the expected values.
     */
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

    /**
     * Tests that an IllegalArgumentException is thrown when an invalid account number is provided.
     * The account number should be a positive integer; a negative or zero value should trigger the exception.
     */
    @Test
    void testInvalidAccountNumber() {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new BankAccount(-1, "Savings", "John", "Doe", 36, "123 Street", 1000.0, bank);
        });
    }

    /**
     * Tests that an IllegalArgumentException is thrown when an invalid account type is provided.
     * The account type should be a non-empty string; an empty string should trigger the exception.
     */
    @Test
    void testInvalidAccountType() {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new BankAccount(12345, "", "John", "Doe", 36, "123 Street", 1000.0, bank);
        });
    }

    /**
     * Tests that an IllegalArgumentException is thrown when an invalid age is provided.
     * The age should be above the minimum allowed value; a value below this should trigger the exception.
     */
    @Test
    void testInvalidAge() {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new BankAccount(12345, "Savings", "John", "Doe", 15, "123 Street", 1000.0, bank);
        });
    }

    /**
     * Tests the depositAmount method to ensure that it correctly increases the account balance.
     * Verifies that the balance is updated by the correct amount after a deposit.
     */
    @Test
    void testDeposit() {
        account.depositAmount(100.0);
        assertEquals(1100.0, account.getBalance(), 0.001);
    }

    /**
     * Tests that an IllegalArgumentException is thrown when an invalid deposit amount is provided.
     * The deposit amount should be a positive double; a negative or zero value should trigger the exception.
     */
    @Test
    void testInvalidDeposit() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.depositAmount(-1.0);
        });
    }

    /**
     * Tests the withdrawAmount method to ensure that it correctly decreases the account balance.
     * Verifies that the balance is updated by the correct amount after a withdrawal.
     */
    @Test
    void testWithdraw() {
        account.withdrawAmount(100.0);
        assertEquals(900.0, account.getBalance(), 0.001);
    }

    /**
     * Tests that an IllegalWithdrawException is thrown when attempting to withdraw more than the account balance.
     * Verifies that insufficient funds are correctly detected and that the exception is thrown.
     */
    @Test
    void testInsufficientFunds() {
        assertThrows(IllegalWithdrawException.class, () ->
        {
            account.withdrawAmount(2000.0);
        });
    }

    /**
     * Tests the transferAmount method to ensure that it correctly transfers funds between accounts.
     * Verifies that the balance is correctly updated for both the sender and recipient accounts.
     */
    @Test
    void testTransfer() {
        BankAccount anotherAccount = new BankAccount(111009, "Savings", "Jane", "Doe", 23, "123 Street", 1000.0, bank);
        account.transferAmount(500.0, anotherAccount);
        assertEquals(500.0, account.getBalance(), 0.001);
        assertEquals(1500.0, anotherAccount.getBalance(), 0.001);
    }

    /**
     * Tests that an IllegalWithdrawException is thrown when attempting to transfer more than the account balance.
     * Verifies that insufficient funds are correctly detected and that the exception is thrown.
     */
    @Test
    void testInvalidTransfer() {
        BankAccount anotherAccount = new BankAccount(111008, "Savings", "Jane", "Doe", 23, "123 Street", 1000.0, bank);
        assertThrows(IllegalWithdrawException.class, () ->
        {
            account.transferAmount(5000.0, anotherAccount);
        });
    }
}
