package lotto;

import lotto.common.ErrorCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private static final int REQUIRED_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;


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
            throw ErrorCode.LOTTO_NUMBERS_MISSING.asException();
        }
    }

    private void requireSize(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_SIZE) {
            throw ErrorCode.LOTTO_SIZE.asException();
        }
    }

    private void requireNoDup(List<Integer> numbers) {
        long unique = numbers.stream().distinct().count();
        if (unique != REQUIRED_SIZE) {
            throw ErrorCode.DUPLICATE_NUMBER.asException();
        }
    }

    private void requireInRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw ErrorCode.NUMBER_RANGE.asException();
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
