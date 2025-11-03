package lotto.domain;

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
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
        if (numbers.contains(bonus)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 달라야 합니다.");
        }
        for (int n : numbers) {
            if (n < 1 || n > 45) throw new IllegalArgumentException("번호는 1~45 범위입니다.");
        }
        if (bonus < 1 || bonus > 45) throw new IllegalArgumentException("보너스는 1~45 범위입니다.");

        return new Winning(numbers, bonus);
    }
    
}
