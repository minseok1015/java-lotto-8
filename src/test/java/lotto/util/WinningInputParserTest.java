package lotto.util;

import lotto.common.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class WinningInputParserTest {

    @Test
    @DisplayName("당첨 번호: 쉼표로 6개 숫자 파싱 성공")
    void parseWinningNumbers_success() {
        Set<Integer> parsed = WinningInputParser.parseWinningNumbers("1, 2, 3, 4, 5, 6");
        assertThat(parsed).containsExactlyInAnyOrder(1,2,3,4,5,6);
    }

    @Test
    @DisplayName("당첨 번호: 숫자 외 문자가 섞이면 예외")
    void parseWinningNumbers_nonNumeric_throws() {
        assertThatThrownBy(() -> WinningInputParser.parseWinningNumbers("1, 2, a, 4, 5, 6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INVALID_NUMBER_LIST_FORMAT.message());
    }

    @Test
    @DisplayName("당첨 번호: 6개가 아니면 예외")
    void parseWinningNumbers_wrongCount_throws() {
        assertThatThrownBy(() -> WinningInputParser.parseWinningNumbers("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.WINNING_COUNT.message());

        assertThatThrownBy(() -> WinningInputParser.parseWinningNumbers("1,2,3,4,5,6,7"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.WINNING_COUNT.message());
    }

    @Test
    @DisplayName("당첨 번호: 중복이 있으면 예외")
    void parseWinningNumbers_duplicate_throws() {
        assertThatThrownBy(() -> WinningInputParser.parseWinningNumbers("1,2,3,4,5,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.DUPLICATE_NUMBER.message());
    }

    @Test
    @DisplayName("당첨 번호: 범위를 벗어나면 예외(1~45)")
    void parseWinningNumbers_outOfRange_throws() {
        assertThatThrownBy(() -> WinningInputParser.parseWinningNumbers("0,2,3,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NUMBER_RANGE.message());

        assertThatThrownBy(() -> WinningInputParser.parseWinningNumbers("1,2,3,4,5,46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NUMBER_RANGE.message());
    }

    @Test
    @DisplayName("보너스 번호: 정상 숫자면 통과")
    void validateBonus_success() {
        int bonus = WinningInputParser.validateBonus(" 45 ");
        assertThat(bonus).isEqualTo(45);
    }

    @Test
    @DisplayName("보너스 번호: 숫자가 아니면 예외")
    void validateBonus_nonNumeric_throws() {
        assertThatThrownBy(() -> WinningInputParser.validateBonus("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.BONUS_NOT_NUMBER.message());
    }

    @Test
    @DisplayName("보너스 번호: 범위를 벗어나면 예외(1~45)")
    void validateBonus_outOfRange_throws() {
        assertThatThrownBy(() -> WinningInputParser.validateBonus("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NUMBER_RANGE.message());

        assertThatThrownBy(() -> WinningInputParser.validateBonus("46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NUMBER_RANGE.message());
    }

    @Test
    @DisplayName("보너스 번호: 빈 입력이면 예외")
    void validateBonus_blank_throws() {
        assertThatThrownBy(() -> WinningInputParser.validateBonus(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INPUT_BLANK.message());
    }
}
