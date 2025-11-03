package lotto.service;

import lotto.domain.Rank;
import java.util.EnumMap;

public class LottoResultCounter {
    private final EnumMap<Rank, Integer> counts = new EnumMap<>(Rank.class);

    public LottoResultCounter() {
        for (Rank r : Rank.values()) {
            counts.put(r, 0);
        }
    }

    public void add(Rank rank) {
        counts.put(rank, counts.get(rank) + 1);
    }

}
