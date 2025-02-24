import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;

import static example.model.SimpleBankAccount.WITHDRAW_FEE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final int INITIAL_BALANCE = 0;
    private static final int FIRST_DEPOSIT_AMOUNT = 100;
    private static final int SECOND_DEPOSIT_AMOUNT = 50;
    private static final int FIRST_WITHDRAW_AMOUNT = 70;
    private static final int WRONG_USER_ID = 2;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
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
    void testWrongUserID() {
        assertThrows(
                IllegalArgumentException.class,
                () -> bankAccount.deposit(WRONG_USER_ID, FIRST_DEPOSIT_AMOUNT)
        );
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT_AMOUNT);
        try {
            bankAccount.deposit(WRONG_USER_ID, SECOND_DEPOSIT_AMOUNT);
        } catch (IllegalArgumentException e) {
            assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
        }
    }

    @Test
    void testWithdraw() {
        int expectedBalance = INITIAL_BALANCE;
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT_AMOUNT);
        expectedBalance += FIRST_DEPOSIT_AMOUNT;
        bankAccount.withdraw(accountHolder.getId(), FIRST_WITHDRAW_AMOUNT);
        expectedBalance -= FIRST_WITHDRAW_AMOUNT;
        expectedBalance -= WITHDRAW_FEE;
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT_AMOUNT);
        try {
            bankAccount.withdraw(WRONG_USER_ID, FIRST_WITHDRAW_AMOUNT);
        } catch (IllegalArgumentException e) {
            assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
        }
    }

    @Test
    void testWithdrawTooHigh() {
        assertThrows(
            IllegalArgumentException.class,
            () -> bankAccount.withdraw(accountHolder.getId(), FIRST_WITHDRAW_AMOUNT)
        );
    }
}
