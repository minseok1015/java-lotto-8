package lotto.view;

import lotto.Lotto;
import java.util.HashMap;

public class OutputView {

    public void printPurchaseCount(int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다.");
    }

    public void printLottos(Lotto[] lottos) {
        for (int i = 0; i < lottos.length; i++) {
            System.out.println(lottos[i].toString());
        }
    }

    public void printStatisticsHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    public void printWinningCounts(HashMap<Integer, Integer> winningCountMap) {
        System.out.println("3개 일치 (5,000원) - " + winningCountMap.getOrDefault(5, 0) + "개");
        System.out.println("4개 일치 (50,000원) - " + winningCountMap.getOrDefault(4, 0) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + winningCountMap.getOrDefault(3, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + winningCountMap.getOrDefault(2, 0) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + winningCountMap.getOrDefault(1, 0) + "개");
    }

    public void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + profitRate + "%입니다.");
    }
}