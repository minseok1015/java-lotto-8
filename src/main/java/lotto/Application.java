package lotto;

import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.Winning;
import lotto.service.LottoGenerator;
import lotto.service.LottoResultCounter;
import lotto.util.Retry;
import lotto.view.InputView;
import lotto.view.OutputView;
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

        LottoResultCounter counter = new LottoResultCounter();

        for (int i = 0; i < lottoCount; i++) {
            Rank rank = winning.judge(lottos[i]);
            counter.add(rank);
        }

        double profitRate = counter.profitRate(buyAmount.value());

        outputView.printStatisticsHeader();
        outputView.printWinningCounts(counter.result());
        outputView.printProfitRate(profitRate);

    }

}
