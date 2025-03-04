package tdd;

import java.util.Optional;

public class SmartDoorLockImplementation implements SmartDoorLock {

    public static final int INITIAL_FAILED_ATTEMPTS = 0;
    public static final int PIN_DIGIT_NUMBER = 4;
    public static final int MAX_VALUE = (int) Math.pow(10, PIN_DIGIT_NUMBER);
    private boolean locked;
    private boolean blocked;
    private Optional<Integer> pin;
    private int failedAttempts;
    private final int maxAttempts;

    public SmartDoorLockImplementation(final int maxAttempts) {
        this.locked = false;
        this.blocked = false;
        this.failedAttempts = INITIAL_FAILED_ATTEMPTS;
        this.pin = Optional.empty();
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void setPin(int pin) {
        if(!this.isOpen()){
            throw new IllegalStateException("Door not open, cannot set PIN");
        }
        this.checkValidPin(pin);
        this.pin = Optional.of(pin);
    }

    private void checkValidPin(int pin) {
        if (pin < 0 || pin >= MAX_VALUE){
            throw new IllegalArgumentException("Not valid PIN (" + pin + ") provided");
        }
    }

    private boolean isOpen() {
        return !(this.isBlocked() || this.isLocked());
    }

    @Override
    public void unlock(int pin) {
        if(this.isBlocked()){
            return;
        }
        this.checkValidPin(pin);
        if(this.isPinCorrect(pin)){
            this.locked = false;
        } else {
            this.failedAttempt();
        }
    }

    private boolean isPinCorrect(int pin) {
        return this.isPinSet() && this.pin.get() == pin;
    }

    private void failedAttempt() {
        this.failedAttempts++;
        if(this.overAttempts()){
            this.blocked = true;
        }
    }

    private boolean overAttempts() {
        return this.getFailedAttempts() >= this.getMaxAttempts();
    }

    @Override
    public void lock() {
        if(!this.isPinSet()){
            throw new IllegalStateException("PIN not set, cannot lock the door.");
        }
        this.locked = true;
    }

    private boolean isPinSet() {
        return this.pin.isPresent();
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public boolean isBlocked() {
        return this.blocked;
    }

    @Override
    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    @Override
    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    @Override
    public void reset() {
        this.pin = Optional.empty();
        this.failedAttempts = INITIAL_FAILED_ATTEMPTS;
        this.blocked = false;
        this.locked = false;
    }
}
