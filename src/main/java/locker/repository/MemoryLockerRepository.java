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

    private static final Map<Integer, Locker> lockers = new ConcurrentSkipListMap<>(Comparator.comparingInt(id -> id));

    static {
        for (Integer id = 1; id <= 9; id++) {
            lockers.put(id, new Locker(id, Size.SMALL));
        }

        for (Integer id = 10; id <= 14; id++) {
            lockers.put(id, new Locker(id, Size.MEDIUM));
        }

        for (Integer id = 15; id <= 16; id++) {
            lockers.put(id, new Locker(id, Size.LARGE));
        }
    }

    @Override
    public Optional<Locker> getLocker(Integer id) {
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
