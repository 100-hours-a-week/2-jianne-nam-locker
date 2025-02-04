package locker.model;

public class Locker {

    private final Long id;
    private final Size size;

    public Locker(Long id, Size size) {
        validateId(id);
        validateSize(size);
        this.id = id;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public Size getSize() {
        return size;
    }

    private void validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Locker의 id는 null일 수 없습니다.");
        }
    }

    private void validateSize(Size size) {
        if (size == null) {
            throw new IllegalArgumentException("Locker의 size는 null일 수 없습니다.");
        }
    }
}
