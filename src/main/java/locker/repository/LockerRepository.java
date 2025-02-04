package locker.repository;

import locker.model.Locker;

import java.util.List;
import java.util.Optional;

public interface LockerRepository {

    Optional<Locker> getLocker(Long id);
    void replaceLocker(Locker locker);
    List<Locker> getLockersInUse();
}
