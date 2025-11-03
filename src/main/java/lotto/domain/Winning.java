package lotto.domain;

import lotto.common.ErrorCode;

import java.util.Set;

public class Winning {
    private final Set<Integer> numbers;
    private final int bonus;

    public Winning(Set<Integer> numbers, int bonus) {
        this.numbers = numbers;
        this.bonus = bonus;
    }

    public static Winning of(Set<Integer> numbers, int bonus) {
        if (numbers.size() != 6) {
            throw ErrorCode.WINNING_COUNT.asException();
        }
        if (numbers.contains(bonus)) {
            throw ErrorCode.BONUS_DUP.asException();
        }
        for (int number : numbers) {
            if (number < 1 || number > 45){
                throw ErrorCode.NUMBER_RANGE.asException();
            }
        }
        if (bonus < 1 || bonus > 45){
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
