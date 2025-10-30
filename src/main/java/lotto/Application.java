package lotto;

import camp.nextstep.edu.missionutils.Randoms;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");

        String buyAmountInput = readLine();
        int buyAmount = Integer.parseInt(buyAmountInput);

        int lottoCount = buyAmount/1000;

        Lotto[] lottos = new Lotto[lottoCount];

        for(int i=0;i<lottoCount;i++){
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lottos[i] = new Lotto(numbers);
        }

        System.out.println(lottoCount+"개를 구매했습니다.");
        for (int i = 0; i < lottoCount; i++) {
            System.out.println(lottos[i].toString());
        }

        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        String winningNumberInput = readLine();
        String[] winningNumbersInput = winningNumberInput.split(",");
        HashSet<Integer> winningNumber = new HashSet<>();
        for(int i=0;i<6;i++){
            winningNumber.add(Integer.parseInt(winningNumbersInput[i]));
        }

        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusInput = readLine();
        int bonus = Integer.parseInt(bonusInput);

        HashMap<Integer,Integer> winningCountMap = new HashMap<>();


        for(int i=0;i<lottoCount;i++){
            int rank = lottos[i].calculateRank(winningNumber,bonus);
            winningCountMap.put(rank,winningCountMap.getOrDefault(rank,0)+1);
        }

        double profitRate = calculateProfitRate(winningCountMap,buyAmount);

        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5, 000원) - "+winningCountMap.getOrDefault(5,0)+"개");
        System.out.println("4개 일치 (50,000원) - "+winningCountMap.getOrDefault(4,0)+"개");
        System.out.println("5개 일치 (1,500,000원) - "+winningCountMap.getOrDefault(3,0)+"개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - "+winningCountMap.getOrDefault(2,0)+"개");
        System.out.println("6개 일치 (2,000,000,000원) - "+winningCountMap.getOrDefault(1,0)+"개");
        System.out.println("총 수익률은 "+profitRate +"% 입니다");

    }

    private static double calculateProfitRate(HashMap<Integer, Integer> winningCountMap, int buyAmount){
        int totalPrize=0;
        totalPrize += winningCountMap.getOrDefault(5,0) * 5000;
        totalPrize += winningCountMap.getOrDefault(4,0) * 50000;
        totalPrize += winningCountMap.getOrDefault(3,0) * 1500000;
        totalPrize += winningCountMap.getOrDefault(2,0) * 30000000;
        totalPrize += winningCountMap.getOrDefault(1,0) * 2000000000;

        return Math.round((double) totalPrize / buyAmount * 100 * 100) / 100.0;
    }
}
