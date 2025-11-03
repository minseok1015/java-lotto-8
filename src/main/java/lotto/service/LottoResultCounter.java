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

    public int totalPrize() {
        int sum = 0;
        for (Rank r : Rank.values()) {
            sum += counts.get(r) * r.prize();
        }
        return sum;
    }

    public double profitRate(int buyAmount) {
        double rate = (double) totalPrize() / buyAmount * 100.0;
        return Math.round(rate * 100) / 100.0;
    }

}
