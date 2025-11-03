package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {

    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }

    public List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            lottos.add(generate());
        }
        return lottos;
    }
}

