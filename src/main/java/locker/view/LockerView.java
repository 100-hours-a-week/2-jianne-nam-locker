package locker.view;

import java.util.List;
import java.util.Scanner;

public abstract class LockerView extends View {

    private static final String DIVIDER = "=====================";

    public int readMenuInput() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        validateMenuInput(answer);
        return Integer.parseInt(answer);
    }

    private void validateMenuInput(String input) {
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("올바른 입력이 아닙니다.");
        }
        if (number < 1 || number > 3) {
            throw new IllegalArgumentException("올바른 선택지가 아닙니다.");
        }
    }

    public String readPasswordInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void readEnter() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public Integer readLockerIdInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("올바른 입력이 아닙니다.");
        }
    }

    public void writeGreeting() {
        this.show("\n안녕하세요.\n물품 보관함 서비스입니다.\n");
    }

    public void writeMenu() {
        this.show(DIVIDER + "\n1. 물품 보관\n2. 물품 회수\n3. 종료\n" + DIVIDER + "\n");
    }

    public void writeNoEmptyLockers() {
        this.show("물품을 넣을 보관함이 없습니다.\n");
    }

    public void writeNoLockersInUse() {
        this.show("물품을 회수할 보관함이 없습니다.\n");
    }

    public void writeExit() {
        this.show(DIVIDER + "\n서비스를 종료합니다.\n감사합니다.\n");
    }

    public void writeMenuCommand() {
        this.show("원하는 동작을 선택하세요 >> ");
    }

    public void writeLockerNumberCommandForLocking() {
        this.show("물품을 보관할 보관함의 번호를 입력하세요 >> ");
    }

    public void writeLockerNumberCommandForUnlocking() {
        this.show("물품을 꺼낼 보관함의 번호를 입력하세요 >> ");
    }

    public void writeLockerPassword(Integer id, String password) {
        this.show(String.format("%d번 보관함이 열렸습니다. 물건을 넣고 문을 닫아 주세요.\n비밀번호는 %s 입니다.\n처음으로 돌아가려면 Enter를 눌러 주세요.\n", id, password));
    }

    public void writePasswordInputCommand() {
        this.show("암호를 입력하세요 >> ");
    }

    public void writeLockerFee(Integer id, Long fee) {
        this.show(String.format("%d번 보관함이 열렸습니다. 물건을 꺼내고 문을 닫아 주세요.\n요금은 %s원입니다.\n결제 후 Enter를 눌러 주세요.\n", id, fee));
    }

    public abstract String status(List<Integer> usingLockerIds);
}
