package lotto.domain;

import lotto.common.ErrorCode;

import java.util.Set;

public class Winning {
    private static final int WINNING_NUMBER_COUNT = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final Set<Integer> numbers;
    private final int bonus;

    public Winning(Set<Integer> numbers, int bonus) {
        this.numbers = numbers;
        this.bonus = bonus;
    }

    public static Winning of(Set<Integer> numbers, int bonus) {
        if (numbers.size() != WINNING_NUMBER_COUNT) {
            throw ErrorCode.WINNING_COUNT.asException();
        }
        if (numbers.contains(bonus)) {
            throw ErrorCode.BONUS_DUP.asException();
        }
        for (int number : numbers) {
            if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER){
                throw ErrorCode.NUMBER_RANGE.asException();
            }
        }
        if (bonus < MIN_LOTTO_NUMBER || bonus > MAX_LOTTO_NUMBER){
            throw ErrorCode.BONUS_RANGE.asException();
        }
        return new Winning(numbers, bonus);
    }

    public Rank judge(Lotto lotto) {
        int match = 0;
        boolean bonusMatched = false;
        for (int n : lotto.getNumbers()) {
            if (numbers.contains(n)){
                match++;
            }
            if (n == bonus){
                bonusMatched = true;
            }
        }
        return Rank.of(match, bonusMatched);
    }
    
}
