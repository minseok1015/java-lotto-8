package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Money;
import lotto.service.LottoGenerator;
import lotto.util.Retry;
import lotto.view.InputView;
import lotto.view.OutputView;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


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

        HashMap<Integer,Integer> winningCountMap = new HashMap<>();


        for(int i=0;i<lottoCount;i++){
            int rank = lottos[i].calculateRank(winningNumber,bonus);
            winningCountMap.put(rank,winningCountMap.getOrDefault(rank,0)+1);
        }

        double profitRate = calculateProfitRate(winningCountMap,buyAmount.value());

        outputView.printStatisticsHeader();
        outputView.printWinningCounts(winningCountMap);
        outputView.printProfitRate(profitRate);

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
