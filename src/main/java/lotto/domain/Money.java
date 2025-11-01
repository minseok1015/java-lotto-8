package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    private Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static Money of(int amount) {
        return new Money(amount);
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 0보다 커야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위여야 합니다.");
        }
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }
    public int value() {
        return amount;
    }
}
