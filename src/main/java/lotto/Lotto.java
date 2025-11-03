package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        requireNonNull(numbers);
        requireSize(numbers);
        requireNoDup(numbers);
        requireInRange(numbers);
    }

    private void requireNonNull(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("[ERROR] 로또 번호가 없습니다.");
        }
    }

    private void requireSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void requireNoDup(List<Integer> numbers) {
        long unique = numbers.stream().distinct().count();
        if (unique != 6) {
            throw new IllegalArgumentException("[ERROR] 중복 번호가 있습니다.");
        }
    }

    private void requireInRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 번호는 1~45 범위여야 합니다.");
            }
        }
    }


    @Override
    public String toString() {
        List<Integer> copy = new ArrayList<>(numbers);
        Collections.sort(copy);
        return copy.toString();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
