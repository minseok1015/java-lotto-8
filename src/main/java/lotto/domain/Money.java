package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money of(int amount) {
        return new Money(amount);
    }
    
}
