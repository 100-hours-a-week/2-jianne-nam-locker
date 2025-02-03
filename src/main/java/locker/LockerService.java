package locker;

import locker.model.Locker;
import locker.model.OccupiedLocker;
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

    public String lock(Long lockerId) {
        Locker existingLocker = lockerRepository.getLocker(lockerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 번호의 보관함이 존재하지 않습니다."));
        if (existingLocker instanceof OccupiedLocker) {
            throw new IllegalStateException("해당 보관함은 이미 사용 중입니다.");
        }
        lockerRepository.replaceLocker(new OccupiedLocker(
                lockerId, existingLocker.getSize(), dateTimeGenerator.generate(), passwordGenerator.generate()
        ));
        return passwordGenerator.generate();
    }

    public Long unlock(Long lockerId, String passwordInput) {
        Locker existingLocker = lockerRepository.getLocker(lockerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 번호의 보관함이 존재하지 않습니다."));
        if (!(existingLocker instanceof OccupiedLocker occupiedLocker)) {
            throw new IllegalStateException("해당 보관함은 비어 있습니다.");
        }
        if (!occupiedLocker.matchPassword(passwordInput)) {
            throw new IllegalStateException("틀린 암호입니다.");
        }
        LocalDateTime endDateTime = dateTimeGenerator.generate();
        lockerRepository.replaceLocker(new Locker(lockerId, occupiedLocker.getSize()));
        return occupiedLocker.calculateFee(endDateTime);
    }

    public List<Long> getEmptyLockerIds() {
        return lockerRepository.getOccupiedLockers().stream().map(Locker::getId).toList();
    }
}
