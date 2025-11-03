package lotto.domain;

import java.util.Set;

public class Winning {
    private final Set<Integer> numbers;
    private final int bonus;

    public Winning(Set<Integer> numbers, int bonus) {
        this.numbers = numbers;
        this.bonus = bonus;
    }

    
}
