package lotto.util;

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
        return new HashSet<>(numbers);
    }

    public static int validateBonus(String line) {
        requireNonBlank(line);
        int bonus = Integer.parseInt(line.trim());
        validateInRange(bonus);
        return bonus;
    }

    private static void requireNonBlank(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어 있습니다.");
        }
    }

    private static List<Integer> splitToNumber(String line) {
        try {
            return Arrays.stream(line.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자와 쉼표(,) 형식으로 입력해 주세요.");
        }
    }

    private static void validateCount(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_COUNT) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 " + REQUIRED_COUNT + "개여야 합니다.");
        }
    }

    private static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            validateInRange(number);
        }
    }

    private static void validateInRange(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException("[ERROR] 번호는 " + MIN + "~" + MAX + " 범위여야 합니다.");
        }
    }

}
