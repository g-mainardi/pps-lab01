import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final int INITIAL_BALANCE = 0;
    private static final int FIRST_DEPOSIT_AMOUNT = 100;
    private static final int SECOND_DEPOSIT_AMOUNT = 50;
    private static final int FIRS_WITHDRAW_AMOUNT = 70;
    private static final int WRONG_USER_ID = 2;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT_AMOUNT);
        bankAccount.deposit(WRONG_USER_ID, SECOND_DEPOSIT_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        int expectedBalance = INITIAL_BALANCE;
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT_AMOUNT);
        expectedBalance += FIRST_DEPOSIT_AMOUNT;
        bankAccount.withdraw(accountHolder.getId(), FIRS_WITHDRAW_AMOUNT);
        expectedBalance -= FIRS_WITHDRAW_AMOUNT;
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT_AMOUNT);
        bankAccount.withdraw(WRONG_USER_ID, FIRS_WITHDRAW_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
