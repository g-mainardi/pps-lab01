package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tdd.SmartDoorLockImplementation.INITIAL_FAILED_ATTEMPTS;

public class SmartDoorLockTest {

    public static final int PIN = 0711;
    public static final int WRONG_PIN = 3012;
    public static final int MAX_ATTEMPTS = 3;
    private SmartDoorLock doorLock;

    @BeforeEach
    void beforeEach() {
        doorLock = new SmartDoorLockImplementation(MAX_ATTEMPTS);
    }

    @Test
    public void testDoorInitialState() {
        assertFalse(doorLock.isLocked());
        assertFalse(doorLock.isBlocked());
        assertEquals(MAX_ATTEMPTS, doorLock.getMaxAttempts());
        assertEquals(INITIAL_FAILED_ATTEMPTS, doorLock.getFailedAttempts());
    }

    @Test
    public void testIllegalLock() {
        assertThrows(
                IllegalStateException.class,
                () -> doorLock.lock()
        );
    }

    @Test
    public void testPinSetAndUnlock() {
        setPinAndLock();
        assertTrue(doorLock.isLocked());
        doorLock.unlock(PIN);
        assertFalse(doorLock.isLocked());
    }

    private void setPinAndLock() {
        doorLock.setPin(PIN);
        doorLock.lock();
    }

    @Test
    public void testWrongPin() {
        setPinAndLock();
        doorLock.unlock(WRONG_PIN);
        assertTrue(doorLock.isLocked());
    }

    @Test
    public void testOverAttemptsAndBlock(){
        setPinAndLock();
        goOverAttempts();
        assertTrue(doorLock.isBlocked());
    }

    private void goOverAttempts() {
        for(int i=INITIAL_FAILED_ATTEMPTS; i<MAX_ATTEMPTS;i++){
            assertEquals(doorLock.getFailedAttempts(), i);
            doorLock.unlock(WRONG_PIN);
        }
    }

    @Test
    public void testReset(){
        testOverAttemptsAndBlock();
        doorLock.reset();
        testDoorInitialState();
    }
}
