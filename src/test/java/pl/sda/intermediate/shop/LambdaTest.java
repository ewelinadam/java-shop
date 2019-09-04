package pl.sda.intermediate.shop;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaTest {

    private List<String> animals = Arrays.asList("cat", "dog", "", " ", null, "mouse");

    @Test
    public void lambdasOne() {

        // sortujemy po 3 literze
        Comparator<String> thirdLetterComparator = (a, b) -> String.valueOf(a.charAt(2)).compareTo(String.valueOf(b.charAt(2)));
        // sortujemy naturalnie
        Comparator<String> naturalFirstLetter = (a, b) -> a.compareTo(b);
        // sortowanie naturalne - zapis z klasą anonimowa
        Comparator<String> naturalFirstLetterAnonymus = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        // musi zwrocic true lub false
        Predicate<String> notBlankPredicate = n -> StringUtils.isNotBlank(n);

        System.out.println(animals.stream()
                .filter(notBlankPredicate)
                .sorted(thirdLetterComparator)
                .collect(Collectors.toList()));

    }

    @FunctionalInterface
    public interface SuperChecker<T> {

        // musi byc jedna metoda abstrakcyjna - wymog funtionan interface
        boolean check(T value);

        default SuperChecker<T> reversedValue() {
            return previousValue -> !this.check((previousValue));
        }
    }

    @Test
    public void lambdasTwo() {

        SuperChecker<Integer> checkIfOdd = a -> a % 2 != 0;
        // lun
        SuperChecker<Integer> checkIfOddAnonymus = new SuperChecker<Integer>() {
            @Override
            public boolean check(Integer value) {
                return value % 2 != 0;
            }
        };

        System.out.println(checkIfOdd.check(4));
        System.out.println(checkIfOdd.check(5));
        System.out.println(checkIfOdd.reversedValue().check(5));

    }

    @FunctionalInterface
    public interface SuperCalculator<T, E> {
        int calculate(T value1, E value2);
    }

    @Test
    public void lambdaThree() {

        SuperCalculator<Integer, String> adder = (a, b) -> a + Integer.parseInt(b);
        System.out.println(adder.calculate(3, "5"));

        BiFunction<Integer, String, Integer> biAdder = (a, b) -> a + Integer.parseInt(b);
        System.out.println(biAdder.apply(3, "5"));
    }

}
