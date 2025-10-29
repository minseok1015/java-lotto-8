package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");

        String buyAmountInput = readLine();
        int buyAmount = Integer.parseInt(buyAmountInput);

        int lottoCount = buyAmount/1000;

        Lotto[] Lottos = new Lotto[lottoCount];

        for(int i=0;i<lottoCount;i++){
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lottos[i] = new Lotto(numbers);
        }

        System.out.println(lottoCount+"개를 구매했습니다.");
        for (int i = 0; i < lottoCount; i++) {
            System.out.println(Lottos[i].toString());
        }

        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        String winningNumberInput = readLine();
        String[] winningNumbersInput = winningNumberInput.split(",");
        Set<Integer> winningNumber = new HashSet<>();
        for(int i=0;i<6;i++){
            winningNumber.add(Integer.parseInt(winningNumbersInput[i]));
        }
        

    }
}
