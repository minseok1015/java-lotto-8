package lotto.service;

import lotto.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoResultCounterTest {

    @Test
    @DisplayName("add/totalPrize: 누적 상금 합산")
    void totalPrize_sum() {
        LottoResultCounter c = new LottoResultCounter();
        c.add(Rank.FIFTH);   // 5,000
        c.add(Rank.THIRD);   // 1,500,000
        c.add(Rank.THIRD);   // 1,500,000
        c.add(Rank.MISS);    // 0

        assertThat(c.totalPrize()).isEqualTo(5000 + 1500_000 + 1500000);
    }

    @Test
    @DisplayName("profitRate: 소수 둘째 자리 반올림")
    void profitRate_rounding() {
        LottoResultCounter c = new LottoResultCounter();
        c.add(Rank.FIFTH); // 5,000

        double rate = c.profitRate(8000); // 5000/8000*100 = 62.5
        assertThat(rate).isEqualTo(62.5);
    }
}
