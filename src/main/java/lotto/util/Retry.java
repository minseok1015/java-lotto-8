package lotto.util;

import lotto.domain.Money;
import lotto.view.InputView;

import java.util.function.Function;
import java.util.function.Supplier;

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

    public static <T> T readParsed(Supplier<String> reader, Function<String, T> parser) {
        while (true) {
            try {
                String input = reader.get();
                return parser.apply(input);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
