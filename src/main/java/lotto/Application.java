package lotto;

import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.Winning;
import lotto.service.LottoGenerator;
import lotto.service.LottoResultCounter;
import lotto.util.Retry;
import lotto.util.WinningInputParser;
import lotto.view.InputView;
import lotto.view.OutputView;
import java.util.List;
import java.util.Set;


public class Application {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Money buyAmount = Retry.readParsed(inputView::readPurchaseAmount, Money::from);
        int lottoCount = buyAmount.calculateLottoCount();

        LottoGenerator generator = new LottoGenerator();
        List<Lotto> lottos = generator.generateLottos(lottoCount);

        outputView.printPurchaseCount(lottoCount);
        outputView.printLottos(lottos);

        Set<Integer> winningNumbers = Retry.readParsed(
                inputView::readWinningNumbersLine,
                lotto.util.WinningInputParser::parseWinningNumbers
        );

        int bonus = Retry.readParsed(
                inputView::readBonusNumberLine,
                lotto.util.WinningInputParser::validateBonus
        );

        Winning winning = Winning.of(winningNumbers, bonus);

        LottoResultCounter counter = new LottoResultCounter();

        for (Lotto lotto : lottos) {
            Rank rank = winning.judge(lotto);
            counter.add(rank);
        }

        double profitRate = counter.profitRate(buyAmount.value());

        outputView.printStatisticsHeader();
        outputView.printWinningCounts(counter.result());
        outputView.printProfitRate(profitRate);

    }

}
