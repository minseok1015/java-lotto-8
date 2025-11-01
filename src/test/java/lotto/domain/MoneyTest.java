package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class MoneyTest {

    @DisplayName("2.1 정상 입력이면 Money를 생성하고 개수를 계산한다")
    @Test
    void from_valid_and_calculateCount() {
        Money money = Money.from("8000");
        assertThat(money.value()).isEqualTo(8000);
        assertThat(money.calculateLottoCount()).isEqualTo(8);
    }

}