package lotto.view;

import lotto.Lotto;
import lotto.domain.Rank;
import java.util.Map;


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

    public void printWinningCounts(Map<Rank, Integer> counts) {
        for (Rank r : Rank.displayOrder()) {
            System.out.println(r.label() + " - " + counts.getOrDefault(r, 0) + "개");
        }
    }

    public void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + profitRate + "%입니다.");
    }
}