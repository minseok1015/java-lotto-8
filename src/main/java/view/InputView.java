package view;

import java.util.HashSet;
import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

    public HashSet<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        String winningNumberInput = Console.readLine();
        String[] winningNumbersInput = winningNumberInput.split(",");
        HashSet<Integer> winningNumber = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            winningNumber.add(Integer.parseInt(winningNumbersInput[i]));
        }
        return winningNumber;
    }

    public int readBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusInput = Console.readLine();
        return Integer.parseInt(bonusInput);
    }
}