package lotto.common;

public enum ErrorCode {
    INVALID_AMOUNT("[ERROR] 금액은 0보다 커야 합니다."),
    INVALID_UNIT("[ERROR] 금액은 1000원 단위여야 합니다."),
    NOT_NUMBER("[ERROR] 금액은 숫자여야 합니다."),
    INPUT_BLANK("[ERROR] 입력이 비어 있습니다."),
    WINNING_COUNT("[ERROR] 당첨 번호는 6개여야 합니다."),
    NUMBER_RANGE("[ERROR] 번호는 1~45 범위여야 합니다."),
    DUPLICATE_NUMBER("[ERROR] 중복 번호가 있습니다."),
    BONUS_RANGE("[ERROR] 보너스는 1~45 범위입니다."),
    BONUS_DUP("[ERROR] 보너스 번호는 당첨 번호와 달라야 합니다."),
    BONUS_NOT_NUMBER("[ERROR] 보너스 번호는 숫자여야 합니다."),
    INVALID_NUMBER_LIST_FORMAT("[ERROR] 숫자와 쉼표(,) 형식으로 입력해 주세요."),
    LOTTO_NUMBERS_MISSING("[ERROR] 로또 번호가 없습니다."),
    LOTTO_SIZE("[ERROR] 로또 번호는 6개여야 합니다.");


    private final String message;
    ErrorCode(String message) { this.message = message; }

    public String message() { return message; }

    public IllegalArgumentException asException() {
        return new IllegalArgumentException(message);
    }
}