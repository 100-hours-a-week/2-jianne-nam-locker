package locker.repository;

import locker.model.Locker;
import locker.model.LockerInUse;
import locker.model.Size;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;

public class MemoryLockerRepository implements LockerRepository {

    private static final Map<Long, Locker> lockers = new ConcurrentSkipListMap<>(Comparator.comparingLong(id -> id));

    static {
        for (Long id = 1L; id <= 9L; id++) {
            lockers.put(id, new Locker(id, Size.SMALL));
        }

        for (Long id = 10L; id <= 14L; id++) {
            lockers.put(id, new Locker(id, Size.MEDIUM));
        }

        for (Long id = 15L; id <= 16L; id++) {
            lockers.put(id, new Locker(id, Size.LARGE));
        }
    }

    @Override
    public Optional<Locker> getLocker(Long id) {
        return Optional.ofNullable(lockers.get(id));
    }

    @Override
    public void replaceLocker(Locker locker) {
        lockers.replace(locker.getId(), locker);
    }

    @Override
    public List<Locker> getLockersInUse() {
        return lockers.values().stream().filter(locker -> locker instanceof LockerInUse).toList();
    }

    @Override
    public boolean isAllInUse() {
        return getLockersInUse().size() == 16;
    }
}
