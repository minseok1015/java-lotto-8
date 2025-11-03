package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class WinningTest {

    @Test
    @DisplayName("Winning.of: 6개가 아니면 예외")
    void of_wrongSize_throws() {
        assertThatThrownBy(() -> Winning.of(Set.of(1,2,3,4,5), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Winning.of: 보너스가 당첨 번호와 겹치면 예외")
    void of_bonusDuplicate_throws() {
        assertThatThrownBy(() -> Winning.of(Set.of(1,2,3,4,5,6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Winning.of: 당첨 번호/보너스 범위 벗어나면 예외")
    void of_numbersOrBonusOutOfRange_throws() {
        assertThatThrownBy(() -> Winning.of(Set.of(0,2,3,4,5,6), 7))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Winning.of(Set.of(1,2,3,4,5,46), 7))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Winning.of(Set.of(1,2,3,4,5,6), 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Winning.of(Set.of(1,2,3,4,5,6), 46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("judge: 매칭 개수/보너스 여부에 따라 Rank 결정")
    void judge_rank_cases() {
        Winning winning = Winning.of(Set.of(1,2,3,4,5,6), 7);

        assertThat(winning.judge(new Lotto(java.util.List.of(1,2,3,10,11,12))))
                .isEqualTo(Rank.FIFTH);  // 3개

        assertThat(winning.judge(new Lotto(java.util.List.of(1,2,3,4,11,12))))
                .isEqualTo(Rank.FOURTH); // 4개

        assertThat(winning.judge(new Lotto(java.util.List.of(1,2,3,4,5,12))))
                .isEqualTo(Rank.THIRD);  // 5개 (보너스X)

        assertThat(winning.judge(new Lotto(java.util.List.of(1,2,3,4,5,7))))
                .isEqualTo(Rank.SECOND); // 5개 + 보너스

        assertThat(winning.judge(new Lotto(java.util.List.of(1,2,3,4,5,6))))
                .isEqualTo(Rank.FIRST);  // 6개

        assertThat(winning.judge(new Lotto(java.util.List.of(10,11,12,13,14,15))))
                .isEqualTo(Rank.MISS);   // 불일치
    }
}
