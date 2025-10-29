package lotto;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");

        String buyAmountInput = readLine();
        int buyAmount = Integer.parseInt(buyAmountInput);

    }
}
