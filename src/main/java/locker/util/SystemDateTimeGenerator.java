package locker.util;

import java.time.LocalDateTime;

public class SystemDateTimeGenerator implements DateTimeGenerator {

    @Override
    public LocalDateTime generate() {
        return LocalDateTime.now();
    }
}
