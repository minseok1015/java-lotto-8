package lotto.util;

import lotto.domain.Money;
import lotto.view.InputView;

public class Retry {
    private Retry() {}

    public static Money readMoney(InputView inputView) {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return Money.from(input);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
