package lotto.util;

import java.util.function.Function;
import java.util.function.Supplier;

public class Retry {
    private Retry() {}

    public static <T> T readParsed(Supplier<String> reader, Function<String, T> parser) {
        while (true) {
            try {
                String input = reader.get();
                return parser.apply(input);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
