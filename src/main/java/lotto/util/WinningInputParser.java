package lotto.util;

import lotto.common.ErrorCode;

import java.util.*;
import java.util.stream.Collectors;

public final class WinningInputParser {
    private WinningInputParser() {}

    private static final int REQUIRED_COUNT = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    public static Set<Integer> parseWinningNumbers(String line) {
        requireNonBlank(line);
        List<Integer> numbers = splitToNumber(line);
        validateCount(numbers);
        validateRange(numbers);
        validateNoDuplicates(numbers);
        return new HashSet<>(numbers);
    }

    public static int validateBonus(String line) {
        requireNonBlank(line);
        try {
            int bonus = Integer.parseInt(line.trim());
            validateInRange(bonus);
            return bonus;
        } catch (NumberFormatException e) {
            throw ErrorCode.BONUS_NOT_NUMBER.asException();
        }
    }

    public static int validateBonus(String line, Set<Integer> winningNumbers) {
        requireNonBlank(line);
        try {
            int bonus = Integer.parseInt(line.trim());
            validateInRange(bonus);
            if (winningNumbers != null && winningNumbers.contains(bonus)) {
                throw ErrorCode.BONUS_DUP.asException();
            }
            return bonus;
        } catch (NumberFormatException e) {
            throw ErrorCode.BONUS_NOT_NUMBER.asException();
        }
    }

    private static void requireNonBlank(String line) {
        if (line == null || line.isBlank()) {
            throw ErrorCode.INPUT_BLANK.asException();
        }
    }

    private static List<Integer> splitToNumber(String line) {
        try {
            return Arrays.stream(line.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw ErrorCode.INVALID_NUMBER_LIST_FORMAT.asException();
        }
    }

    private static void validateCount(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_COUNT) {
            throw ErrorCode.WINNING_COUNT.asException();
        }
    }

    private static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            validateInRange(number);
        }
    }

    private static void validateInRange(int number) {
        if (number < MIN || number > MAX) {
            throw ErrorCode.NUMBER_RANGE.asException();
        }
    }

    private static void validateNoDuplicates(List<Integer> numbers) {
        long unique = numbers.stream().distinct().count();
        if (unique != numbers.size()) {
            throw ErrorCode.DUPLICATE_NUMBER.asException();
        }
    }
}
