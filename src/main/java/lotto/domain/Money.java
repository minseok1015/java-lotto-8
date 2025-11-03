package lotto.domain;

import lotto.common.ErrorCode;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    private Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static Money from(String input) {
        int amount = parseToInt(input);
        return new Money(amount);
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw ErrorCode.INVALID_AMOUNT.asException();
        }
        if (amount % LOTTO_PRICE != 0) {
            throw ErrorCode.INVALID_UNIT.asException();
        }
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw ErrorCode.NOT_NUMBER.asException();
        }
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }
    public int value() {
        return amount;
    }
}
