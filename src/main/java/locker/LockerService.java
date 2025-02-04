package locker;

import locker.model.Locker;
import locker.model.LockerInUse;
import locker.repository.LockerRepository;
import locker.util.DateTimeGenerator;
import locker.util.PasswordGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class LockerService {

    private final PasswordGenerator passwordGenerator;
    private final DateTimeGenerator dateTimeGenerator;
    private final LockerRepository lockerRepository;

    public LockerService(PasswordGenerator passwordGenerator, DateTimeGenerator dateTimeGenerator, LockerRepository lockerRepository) {
        this.passwordGenerator = passwordGenerator;
        this.dateTimeGenerator = dateTimeGenerator;
        this.lockerRepository = lockerRepository;
    }

    public String lock(Integer lockerId) {
        Locker emptyLocker = getEmptyLocker(lockerId);
        String password = passwordGenerator.generate();
        lockerRepository.replaceLocker(new LockerInUse(
                lockerId, emptyLocker.getSize(), dateTimeGenerator.generate(), password));
        return password;
    }

    public Locker getEmptyLocker(Integer lockerId) {
        Locker emptyLocker = lockerRepository.getLocker(lockerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 번호의 보관함이 존재하지 않습니다."));
        if (emptyLocker instanceof LockerInUse) {
            throw new IllegalStateException("해당 보관함은 이미 사용 중입니다.");
        }
        return emptyLocker;
    }

    public Long unlock(Integer lockerId, String passwordInput) {
        LockerInUse lockerInUse = getLockerInUse(lockerId);
        if (!lockerInUse.matchPassword(passwordInput)) {
            throw new IllegalStateException("틀린 암호입니다.");
        }
        LocalDateTime endDateTime = dateTimeGenerator.generate();
        lockerRepository.replaceLocker(new Locker(lockerId, lockerInUse.getSize()));
        return lockerInUse.calculateFee(endDateTime);
    }

    public LockerInUse getLockerInUse(Integer lockerId) {
        Locker existingLocker = lockerRepository.getLocker(lockerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 번호의 보관함이 존재하지 않습니다."));
        if (!(existingLocker instanceof LockerInUse lockerInUse)) {
            throw new IllegalStateException("해당 보관함은 비어 있습니다.");
        }
        return lockerInUse;
    }

    public List<Integer> getEmptyLockerIds() {
        return lockerRepository.getLockersInUse().stream().map(Locker::getId).toList();
    }

    public boolean isAllInUse() {
        return lockerRepository.isAllInUse();
    }
}
