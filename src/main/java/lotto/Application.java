package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

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
    }
}
