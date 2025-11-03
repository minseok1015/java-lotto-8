package lotto;

import lotto.common.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호가 6개보다 적으면 예외")
    void size_must_be_6() {
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.LOTTO_SIZE.message());
    }


    @DisplayName("로또 번호가 범위를 벗어나면 예외(1~45)")
    @Test
    void numbers_out_of_range_throws() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NUMBER_RANGE.message());

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NUMBER_RANGE.message());
    }

    @DisplayName("toString은 정렬된 문자열을 출력한다")
    @Test
    void toString_sorted() {
        Lotto lotto = new Lotto(List.of(45, 1, 30, 5, 10, 2));
        assertThat(lotto.toString()).isEqualTo("[1, 2, 5, 10, 30, 45]");
    }
}
