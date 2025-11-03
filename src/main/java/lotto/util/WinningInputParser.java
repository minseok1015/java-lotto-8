package lotto.util;

import java.util.*;
import java.util.stream.Collectors;

public final class WinningInputParser {
    private WinningInputParser() {}

    private static final int REQUIRED_COUNT = 6;

    public static Set<Integer> parseWinningNumbers(String line) {
        requireNonBlank(line);
        List<Integer> numbers = splitToNumber(line);
        validateCount(numbers);
        return new HashSet<>(numbers);
    }

    public static int validateBonus(String line) {
        requireNonBlank(line);
        return Integer.parseInt(line.trim());
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

}
