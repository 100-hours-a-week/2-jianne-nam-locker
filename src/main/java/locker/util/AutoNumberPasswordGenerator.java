package locker.util;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoNumberPasswordGenerator implements PasswordGenerator {

    @Override
    public String generate() {
        Random random = new Random();
        return IntStream.range(0, 8)
                .mapToObj(integer -> String.valueOf(random.nextInt(10)))
                .collect(Collectors.joining());
    }
}
