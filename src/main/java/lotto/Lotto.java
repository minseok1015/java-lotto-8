package lotto;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현

    @Override
    public String toString() {
        numbers.sort(Integer::compareTo);
        return numbers.toString();
    }

    public int calculateRank(HashSet<Integer> winningNumber,int bonus){
        int count=0;
        boolean isBonus=false;
        for (Integer number : numbers) {
            if(winningNumber.contains(number)){
                count++;
            }
            if(number==bonus){
                isBonus=true;
            }
        }

        if(count==3){
            return 5;
        }
        if(count==4){
            return 4;
        }
        if(count==5&&!isBonus){
            return 3;
        }
        if(count==5){
            return 2;
        }
        if(count==6){
            return 1;
        }
        return 6;
    }


}
