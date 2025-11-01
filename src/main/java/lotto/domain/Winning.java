package lotto.domain;

import java.util.HashSet;

public class Winning {
    private final HashSet<Integer> numbers;
    private final int bonus;

    public Winning(HashSet<Integer> numbers, int bonus) {
        this.numbers = numbers;
        this.bonus = bonus;
    }

    
}
