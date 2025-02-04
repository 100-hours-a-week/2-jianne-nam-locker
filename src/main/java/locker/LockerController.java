package locker;

import locker.view.LockerView;

public class LockerController {

    private final LockerView lockerView;
    private final LockerService lockerService;

    public LockerController(LockerView lockerView, LockerService lockerService) {
        this.lockerView = lockerView;
        this.lockerService = lockerService;
    }

    public void play() {
        while (true) {
            lockerView.writeGreeting();
            int choice = selectMenu();

            if (choice == 1) {
                lock();
            } else if (choice == 2) {
                unlock();
            } else if (choice == 3) {
                exit();
                break;
            }
        }
    }

    private int selectMenu() {
        try {
            lockerView.writeMenu();
            lockerView.writeMenuCommand();
            return lockerView.readMenuInput();
        } catch (RuntimeException e) {
            lockerView.show("\n" + e.getMessage() + "\n");
            return selectMenu();
        }
    }

    private void lock() {
        lockerView.show(lockerView.status(lockerService.getEmptyLockerIds()));
        readIdForLocking();
        lockerView.readEnter();
    }

    private void readIdForLocking() {
        try {
            lockerView.writeLockerNumberCommandForLocking();
            Long idInput = lockerView.readLockerIdInput();
            lockerService.getEmptyLocker(idInput);
            lockerView.writeLockerPassword(idInput, lockerService.lock(idInput));
        } catch (RuntimeException e) {
            lockerView.show("\n" + e.getMessage() + "\n");
            readIdForLocking();
        }
    }

    private void unlock() {
        lockerView.show(lockerView.status(lockerService.getEmptyLockerIds()));
        Long id = readIdForUnlocking();
        try {
            lockerView.writeLockerFee(id, unlockAndGetFee(id));
            lockerView.readEnter();
        } catch (RuntimeException e) {
            lockerView.show("\n" + e.getMessage() + "\n");
        }
    }

    private Long readIdForUnlocking() {
        try {
            lockerView.writeLockerNumberCommandForUnlocking();
            Long idInput = lockerView.readLockerIdInput();
            lockerService.getLockerInUse(idInput);
            return idInput;
        } catch (RuntimeException e) {
            lockerView.show("\n" + e.getMessage() + "\n");
            return readIdForUnlocking();
        }
    }

    private Long unlockAndGetFee(Long id) throws RuntimeException {
        lockerView.writePasswordInputCommand();
        return lockerService.unlock(id, lockerView.readPasswordInput());
    }

    private void exit() {
        lockerView.writeExit();
    }
}
