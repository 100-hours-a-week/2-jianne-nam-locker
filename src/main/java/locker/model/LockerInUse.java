package locker.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class LockerInUse extends Locker {

    private final LocalDateTime createdAt;
    private final String password;

    public LockerInUse(Long id, Size size, LocalDateTime createdAt, String password) {
        super(id, size);
        this.createdAt = createdAt;
        this.password = password;
    }

    public Long calculateFee(LocalDateTime unlockedAt) {
        return this.getSize().calculateFee(getTotalMinutes(unlockedAt));
    }

    private Long getTotalMinutes(LocalDateTime unlockedAt) {
        return Duration.between(this.createdAt, unlockedAt).toMinutes();
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
