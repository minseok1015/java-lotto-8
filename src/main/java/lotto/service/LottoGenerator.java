package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.List;

public class LottoGenerator {

    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }

    public Lotto[] generateLottos(int count) {
        Lotto[] lottos = new Lotto[count];
        for (int i = 0; i < count; i++) {
            lottos[i] = generate();
        }
        return lottos;
    }
}

