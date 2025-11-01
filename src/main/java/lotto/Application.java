package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import view.InputView;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Application {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        int buyAmount = inputView.readPurchaseAmount();

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

        HashSet<Integer> winningNumber = inputView.readWinningNumbers();
        int bonus = inputView.readBonusNumber();

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
