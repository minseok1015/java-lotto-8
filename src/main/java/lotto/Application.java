package lotto;

import lotto.service.LottoGameService;

public class Application {
    public static void main(String[] args) {
        new LottoGameService().run();
    }
}
