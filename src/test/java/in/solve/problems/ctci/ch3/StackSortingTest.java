package in.solve.problems.ctci.ch3;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class StackSortingTest {

    @Test
    public void testStackSorting() {
        for (int i = 0; i < 10; i++) {
            LStack<Integer> stack = new LStack<>();
            List<Integer> numbers = IntStream.range(0, i).mapToObj(Integer::valueOf).collect(Collectors.toList());
            randomize(numbers);
            for (int j = 0; j < i; j++) {
                stack.push(numbers.get(j));
            }

            StackSorting.sort(stack);

            if (!stack.isEmpty()) {
                Integer lastElement = stack.pop();
                while (!stack.isEmpty()) {
                    Integer currentElement = stack.pop();
                    assertThat(currentElement, is(lessThan(lastElement)));
                    lastElement = currentElement;
                }
            }

        }
    }

    private void randomize(List<Integer> numbers) {
        for (int i = 1; i < numbers.size(); i++) {
            int index1 = new Random().nextInt(numbers.size());
            int index2 = new Random().nextInt(numbers.size());
            Integer temp = numbers.get(index1);
            numbers.set(index1, numbers.get(index2));
            numbers.set(index2, temp);
        }
    }
}
