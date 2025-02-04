package locker;

import locker.repository.LockerRepository;
import locker.repository.MemoryLockerRepository;
import locker.util.AutoNumberPasswordGenerator;
import locker.util.DateTimeGenerator;
import locker.util.PasswordGenerator;
import locker.util.SystemDateTimeGenerator;
import locker.view.LockerOutputView;
import locker.view.LockerView;

public class LockerApplication {

    public static void main(String[] args) {
        DateTimeGenerator dateTimeGenerator = new SystemDateTimeGenerator();
        PasswordGenerator passwordGenerator = new AutoNumberPasswordGenerator();

        LockerRepository lockerRepository = new MemoryLockerRepository();
        LockerService lockerService = new LockerService(passwordGenerator, dateTimeGenerator, lockerRepository);

        LockerView view = new LockerOutputView();

        LockerController controller = new LockerController(view, lockerService);
        controller.play();
    }
}
