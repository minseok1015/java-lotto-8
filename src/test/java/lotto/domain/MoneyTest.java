package lotto.domain;

import lotto.common.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    @DisplayName("정상 입력이면 Money를 생성하고 개수를 계산한다")
    @Test
    void from_valid_and_calculateCount() {
        Money money = Money.from("8000");
        assertThat(money.value()).isEqualTo(8000);
        assertThat(money.calculateLottoCount()).isEqualTo(8);
    }

    @DisplayName("숫자가 아닌 입력이면 예외가 발생한다")
    @Test
    void from_non_numeric_throws() {
        assertThatThrownBy(() -> Money.from("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NOT_NUMBER.message());
    }

    @DisplayName("금액이 0 이하이면 예외가 발생한다")
    @Test
    void from_non_positive_throws() {
        assertThatThrownBy(() -> Money.from("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INVALID_AMOUNT.message());

        assertThatThrownBy(() -> Money.from("-1000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INVALID_AMOUNT.message());
    }

    @DisplayName("금액이 1000원 단위가 아니면 예외가 발생한다")
    @Test
    void from_not_multiple_of_1000_throws() {
        assertThatThrownBy(() -> Money.from("1500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INVALID_UNIT.message());
    }
}
