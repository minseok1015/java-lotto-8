package lotto;

import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.Winning;
import lotto.service.LottoGenerator;
import lotto.util.Retry;
import lotto.view.InputView;
import lotto.view.OutputView;


import java.util.HashMap;
import java.util.HashSet;


public class Application {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        
        Money buyAmount = Retry.readMoney(inputView);
        int lottoCount = buyAmount.calculateLottoCount();

        LottoGenerator generator = new LottoGenerator();
        Lotto[] lottos = generator.generateLottos(lottoCount);

        outputView.printPurchaseCount(lottoCount);
        outputView.printLottos(lottos);

        HashSet<Integer> winningNumber = inputView.readWinningNumbers();
        int bonus = inputView.readBonusNumber();
        Winning winning = Winning.of(winningNumber, bonus);

        HashMap<Rank, Integer> winningCountMap = new HashMap<>();

        for (int i = 0; i < lottoCount; i++) {
            Rank rank = winning.judge(lottos[i]);
            winningCountMap.put(rank, winningCountMap.getOrDefault(rank, 0) + 1);
        }

        double profitRate = calculateProfitRate(winningCountMap,buyAmount.value());

        outputView.printStatisticsHeader();
        outputView.printWinningCounts(winningCountMap);
        outputView.printProfitRate(profitRate);

    }

    private static double calculateProfitRate(HashMap<Rank, Integer> winningCountMap, int buyAmount){
        int totalPrize=0;
        for (Rank r : Rank.displayOrder()) {
            totalPrize += winningCountMap.getOrDefault(r, 0) * r.prize();
        }
        return Math.round((double) totalPrize / buyAmount * 100 * 100) / 100.0;
    }
}
