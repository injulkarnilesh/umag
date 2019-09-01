package in.solve.problems.basic.linkedlist;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class LinkedListUtilsTest {

    @Test
    public void shouldReverseEmptyLinedList() {
        final Node<String> list = null;

        final Node<String> reversed = LinkedListUtils.reverse(list);

        assertNull(reversed);
    }

    @Test
    public void shouldReverseSingleElementList() {
        final Node<String> list = Node.of("A");
        final Node<String> reverse = LinkedListUtils.reverse(list);

        assertThat(reverse, is(linkedList("A")));
    }

    @Test
    public void shouldReverseTwoElementList() {
        final Node<String> list = Node.of("A");
        list.setNext(Node.of("B"));
        final Node<String> reverse = LinkedListUtils.reverse(list);

        assertThat(reverse, is(linkedList("B", "A")));
    }

    @Test
    public void shouldReverseMultiElementList() {
        final Node<String> list = createLinedList("A", "B", "C", "D", "E");
        final Node<String> reverse = LinkedListUtils.reverse(list);

        assertThat(reverse, is(linkedList("E", "D", "C", "B", "A")));
    }


    @Test
    public void shouldRemoveFromDuplicatesFromEmptyList() {
        Node<String> node = null;
        LinkedListUtils.deDupe(node);
        assertNull(node);
    }

    @Test
    public void shouldRemoveDuplicatesFromSingleElementList() {
        Node<String> node = Node.of("A");
        LinkedListUtils.deDupe(node);
        assertThat(node, is(linkedList("A")));
    }

    @Test
    public void shouldRemoveDuplicatesFromNonDuplicateList() {
        Node<String> list = createLinedList("A", "B", "C");
        LinkedListUtils.deDupe(list);
        assertThat(list, is(linkedList("A", "B", "C")));
    }

    @Test
    public void shouldRemoveDuplicateFromTwoDupNodeList() {
        Node<String> list = createLinedList("A", "A");

        LinkedListUtils.deDupe(list);

        assertThat(list, is(linkedList("A")));
    }

    @Test
    public void shouldRemoveDuplicateFromList() {
        Node<String> list = createLinedList("A", "B", "A", "C", "A", "A", "D", "E", "F", "D", "C", "C");

        LinkedListUtils.deDupe(list);

        assertThat(list, is(linkedList("A", "B", "C", "D", "E", "F")));
    }

    @Test
    public void shouldRemoveDuplicateFromListOfSameElement() {
        Node<String> list = createLinedList("A", "A", "A", "A", "A");

        LinkedListUtils.deDupe(list);

        assertThat(list, is(linkedList("A")));
    }

    private <T> Node<T> createLinedList(final T ...values) {
        Node<T> lastPointer = null;
        Node<T> node = null;
        for (int i = values.length - 1; i >= 0; i--) {
            node = Node.of(values[i]);
            node.setNext(lastPointer);
            lastPointer = node;
        }
        return node;
    }

    private <T> Matcher<Node<T>> linkedList(final T ...values) {

        return new TypeSafeMatcher<Node<T>>() {
            @Override
            protected boolean matchesSafely(final Node<T> tNode) {
                boolean matches = true;
                int i = 0;
                Node<T> node = tNode;
                while (node != null && i < values.length) {
                    if (!values[i].equals(node.value())) {
                        matches = false;
                        break;
                    }
                    i++;
                    node = node.next();
                }
                return matches && node == null && i == values.length;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendValue(values);
            }
        };
    }

}
