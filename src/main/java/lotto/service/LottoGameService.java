package lotto.service;

import lotto.Lotto;
import lotto.domain.Money;
import lotto.domain.Winning;
import lotto.util.Retry;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoGameService {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    // 기본 생성자
    public LottoGameService() {
        this(new InputView(), new OutputView(), new LottoGenerator());
    }

    // 주입 가능한 생성자 (테스트)
    public LottoGameService(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        Money money = readMoney();
        List<Lotto> lottos = issueLottos(money.calculateLottoCount());
        printPurchase(lottos);

        Winning winning = readWinning();
        LottoResultCounter counter = judgeAll(lottos, winning);

        printStats(counter, money.value());
    }

    private Money readMoney() {
        return Retry.readParsed(inputView::readPurchaseAmount, Money::from);
    }

    private List<Lotto> issueLottos(int count) {
        return lottoGenerator.generateLottos(count);
    }

    private void printPurchase(List<Lotto> lottos) {
        outputView.printPurchaseCount(lottos.size());
        outputView.printLottos(lottos);
    }

    private Winning readWinning() {
        var nums = Retry.readParsed(inputView::readWinningNumbersLine,
                lotto.util.WinningInputParser::parseWinningNumbers);
        int bonus = Retry.readParsed(inputView::readBonusNumberLine,
                lotto.util.WinningInputParser::validateBonus);
        return Winning.of(nums, bonus);
    }

    private LottoResultCounter judgeAll(List<Lotto> lottos, Winning winning) {
        LottoResultCounter counter = new LottoResultCounter();
        for (Lotto lotto : lottos) {
            counter.add(winning.judge(lotto));
        }
        return counter;
    }

    private void printStats(LottoResultCounter counter, int buyAmount) {
        outputView.printStatisticsHeader();
        outputView.printWinningCounts(counter.result());
        outputView.printProfitRate(counter.profitRate(buyAmount));
    }

}
