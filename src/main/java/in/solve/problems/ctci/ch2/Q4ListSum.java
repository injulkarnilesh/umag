package in.solve.problems.ctci.ch2;

import java.util.List;
import java.util.Stack;

public class Q4ListSum {

    public static ListNode<Integer> sum(ListNode<Integer> number1, ListNode<Integer> number2) {
        if (number1 == null || number2 == null) {
            throw new IllegalArgumentException("Null number");
        }
        //return sumWithRecursion(number1, number2);
        return sumWithStack(number1, number2);
    }

    private static ListNode<Integer> sumWithStack(ListNode<Integer> number1, ListNode<Integer> number2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode<Integer> pointer1 = number1;
        ListNode<Integer> pointer2 = number2;
        while (pointer1 != null) {
            stack1.push(pointer1.getValue());
            pointer1 = pointer1.getNext();
        }
        while (pointer2 != null) {
            stack2.push(pointer2.getValue());
            pointer2 = pointer2.getNext();
        }

        ListNode<Integer> result = null;
        int carry = 0;
        while (true) {
            if (stack1.empty() && stack2.empty()) {
                break;
            }
            int sum = 0;
            if(!stack1.empty()) {
                sum += stack1.pop();
            }
            if (!stack2.empty()) {
                sum += stack2.pop();
            }
            sum += carry;
            int digit = sum % 10;
            carry = sum > 9 ? 1 : 0;
            result = new ListNode<>(digit, result);
        }
        if (carry > 0) {
            result = new ListNode<>(carry, result);
        }
        return result;
    }

    private static ListNode sumWithRecursion(ListNode<Integer> number1, ListNode<Integer> number2) {
        ListNode<Integer> pointer1 = number1;
        ListNode<Integer> pointer2 = number2;
        int length1 = length(number1);
        int length2 = length(number2);
        if (length1 > length2) {
            for (int i = 0; i < length1 - length2; i++) {
                pointer2 = new ListNode<>(0, pointer2);
            }
        } else if (length2 > length1) {
            for (int i = 0; i < length2 - length1; i++) {
                pointer1 = new ListNode<>(0, pointer1);
            }
        }

        TempResult tempResult = sumRecursively(pointer1, pointer2);
        if (tempResult.carry != 0) {
            ListNode result = new ListNode<Integer>(tempResult.carry, tempResult.result);
            return result;
        }
        return tempResult.result;
    }

    private static TempResult sumRecursively(ListNode<Integer> number1,
                                             ListNode<Integer> number2) {
        if (number1 == null || number2 == null) {
            return new TempResult(null ,0);
        }
        TempResult tempResult = sumRecursively(number1.getNext(), number2.getNext());
        int currentDigitSum = number1.getValue() + number2.getValue() + tempResult.carry;
        int sumDigit = currentDigitSum % 10;
        int newCarry = currentDigitSum > 9 ? 1 : 0;
        ListNode<Integer> result = new ListNode<>(sumDigit, tempResult.result);
        return new TempResult(result, newCarry);
    }

    private static class TempResult {
        ListNode<Integer> result;
        int carry;

        public TempResult(ListNode<Integer> result, int carry) {
            this.result = result;
            this.carry = carry;
        }
    }

    private static int length(ListNode<Integer> list) {
        int count = 0;
        ListNode<Integer> pointer = list;
        while (pointer != null) {
            count++;
            pointer = pointer.getNext();
        }
        return count;
    }

}
