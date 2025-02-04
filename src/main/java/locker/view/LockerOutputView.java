package locker.view;

import java.util.List;

public final class LockerOutputView extends LockerView {

    private static final int COL = 5;
    private static final Long[] ids = { 1L, 2L, 3L, 10L, 11L, 4L, 5L, 6L, 7L, 8L, 9L, 15L, 16L, 12L, 13L, 14L };

    private StringBuilder message;
    private int idx;

    public String status(List<Long> usingLockerIds) {
        this.idx = 0;
        this.message = new StringBuilder("\n보관함의 현재 상태는 다음과 같습니다.\n\n");

        addHorizontalLine(COL);
        message.append("\n|");

        addIdIfEmpty(COL, usingLockerIds);
        message.append("\n");

        addHorizontalLine(COL-2);
        message.append("|");
        addVerticalLines(2);
        message.append("\n|");

        addIdIfEmpty(COL-2, usingLockerIds);
        addVerticalLines(2);
        message.append("\n");

        addHorizontalLine(COL);
        message.append("\n|");

        addIdIfEmpty(COL, usingLockerIds);
        message.append("\n");

        addHorizontalLine(COL-2);
        message.append("|");
        addVerticalLines(2);
        message.append("\n|");

        addIdIfEmpty(COL-2, usingLockerIds);
        addVerticalLines(2);
        message.append("\n|");

        addVerticalLines(COL);
        message.append("\n|");

        addVerticalLines(COL);
        message.append("\n");

        addHorizontalLine(COL);
        message.append("\n");

        return message.toString();
    }

    private void addIdIfEmpty(int count, List<Long> usingLockerIds) {
        for (int i = 0; i < count; i++) {
            if (usingLockerIds.contains(ids[idx])) {
                message.append("  ").append(String.format("%2s", "X")).append("   |");
            } else {
                message.append("  ").append(String.format("%2d", ids[idx])).append("   |");
            }
            idx++;
        }
    }

    private void addVerticalLines(int count) {
        message.append("       |".repeat(Math.max(0, count)));
    }

    private void addHorizontalLine(int count) {
        message.append(" ------ ".repeat(Math.max(0, count)));
    }
}
