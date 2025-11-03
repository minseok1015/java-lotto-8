package lotto.service;

import lotto.Lotto;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.Winning;
import lotto.util.Retry;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Set;

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
        // 1) 구매 금액 입력/검증
        Money buyAmount = Retry.readParsed(inputView::readPurchaseAmount, Money::from);
        int lottoCount = buyAmount.calculateLottoCount();

        // 2) 로또 발행
        List<Lotto> lottos = lottoGenerator.generateLottos(lottoCount);

        // 3) 구매 결과 출력
        outputView.printPurchaseCount(lottoCount);
        outputView.printLottos(lottos);

        // 4) 당첨/보너스 입력 + 검증
        Set<Integer> winningNumbers = Retry.readParsed(
                inputView::readWinningNumbersLine,
                lotto.util.WinningInputParser::parseWinningNumbers
        );

        int bonus = Retry.readParsed(
                inputView::readBonusNumberLine,
                lotto.util.WinningInputParser::validateBonus
        );

        Winning winning = Winning.of(winningNumbers, bonus);

        // 5) 결과 집계
        LottoResultCounter counter = new LottoResultCounter();
        for (Lotto lotto : lottos) {
            Rank rank = winning.judge(lotto);
            counter.add(rank);
        }

        // 6) 통계/수익률 출력
        double profitRate = counter.profitRate(buyAmount.value());
        outputView.printStatisticsHeader();
        outputView.printWinningCounts(counter.result());
        outputView.printProfitRate(profitRate);
    }
}
