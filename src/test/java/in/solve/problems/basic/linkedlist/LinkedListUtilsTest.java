package in.solve.problems.basic.linkedlist;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
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

    @Test
    public void shouldFindIntersectionPointOfEmptyLinkedLists() {
        Node<String> list1 = null;
        Node<String> list2 = null;

        String intersectionPoint = LinkedListUtils.intersectionPoint(list1, list2);

        assertNull(intersectionPoint);
    }

    @Test
    public void shouldFindIntersectionPointOfNonIntersectingLists() {
        Node<String> list1 = createLinedList("A", "B", "C");
        Node<String> list2 = createLinedList("P", "B", "C");

        String intersectionPoint = LinkedListUtils.intersectionPoint(list1, list2);

        assertNull(intersectionPoint);
    }

    @Test
    public void shouldFindIntersectionPointOfSameListsAsFirstNode() {
        final Node<String> list = createLinedList("A");

        final String intersectionPoint = LinkedListUtils.intersectionPoint(list, list);

        assertThat(intersectionPoint, is("A"));
    }

    @Test
    public void shouldFindIntersectionPointOfTwoSmallLists() {
        final Node<String> intersectionList = createLinedList("P", "Q");
        final Node<String> list1 = createLinedList("M", "N", "O");
        final Node<String> list2 = createLinedList("A", "B");
        append(list1, intersectionList);
        append(list2, intersectionList);

        final String intersectionPoint = LinkedListUtils.intersectionPoint(list1, list2);

        assertThat(intersectionPoint, is(intersectionList.value()));
    }

    @Test
    public void shouldFindIntersectionPointOfTwoListsSecondBeingLarger() {
        final Node<String> intersectionList = createLinedList("P", "Q");
        final Node<String> list1 = createLinedList("M", "N", "O");
        final Node<String> list2 = createLinedList("A", "B", "C", "D");
        append(list1, intersectionList);
        append(list2, intersectionList);

        final String intersectionPoint = LinkedListUtils.intersectionPoint(list1, list2);

        assertThat(intersectionPoint, is(intersectionList.value()));
    }

    @Test
    public void shouldFindIntersectionPointOfTwoListsOfSimilarSize() {
        final Node<String> intersectionList = createLinedList("P", "Q");
        final Node<String> list1 = createLinedList("M", "N", "O");
        final Node<String> list2 = createLinedList("A", "B", "C");
        append(list1, intersectionList);
        append(list2, intersectionList);

        final String intersectionPoint = LinkedListUtils.intersectionPoint(list1, list2);

        assertThat(intersectionPoint, is(intersectionList.value()));
    }

    @Test
    public void shouldFindNthLastElementForEmptyList() {
        final Node<String> list = null;
        String element = LinkedListUtils.lastNthElement(list, 1);

        assertNull(element);
    }

    @Test
    public void shouldFind1stLastElementForSingleList() {
        final Node<String> list = createLinedList("A");
        String element = LinkedListUtils.lastNthElement(list, 1);

        assertThat(element, is("A"));
    }

    @Test
    public void shouldFindNthLastElementForListOfSizeLesserThanN() {
        final Node<String> list = createLinedList("A");
        String element = LinkedListUtils.lastNthElement(list, 2);

        assertNull(element);
    }

    @Test
    public void shouldFind2ndLastElementIn3ElementList() {
        final Node<String> list = createLinedList("A", "B", "C");

        final String element = LinkedListUtils.lastNthElement(list, 2);

        assertThat(element, is("B"));
    }

    @Test
    public void shouldFind1ndLastElementIn3ElementList() {
        final Node<String> list = createLinedList("A", "B", "C");

        final String element = LinkedListUtils.lastNthElement(list, 1);

        assertThat(element, is("C"));
    }

    @Test
    public void shouldFind3rdLastElementIn3ElementList() {
        final Node<String> list = createLinedList("A", "B", "C");

        final String element = LinkedListUtils.lastNthElement(list, 3);

        assertThat(element, is("A"));
    }

    @Test
    public void shouldNotFind0thLastElementIn3ElementList() {
        final Node<String> list = createLinedList("A", "B", "C");

        final String element = LinkedListUtils.lastNthElement(list, 0);

        assertNull(element);
    }

    @Test
    public void shouldNotFind4thLastElementIn3ElementList() {
        final Node<String> list = createLinedList("A", "B", "C");

        final String element = LinkedListUtils.lastNthElement(list, 4);

        assertNull(element);
    }


    @Test
    public void shouldMergeEmptyLists() {
        Node<Integer> list1 = null;
        Node<Integer> list2 = null;

        Node<Integer> merged = LinkedListUtils.merge(list1, list2);

        assertNull(merged);
    }


    @Test
    public void shouldMergeOneEmptyAndOtherNonEmptyList() {
        final Node<Integer> list = createLinedList(1);

        Node<Integer> merged = LinkedListUtils.merge(list, null);
        assertThat(merged, is(linkedList(1)));

        merged = LinkedListUtils.merge(null, list);
        assertThat(merged, is(linkedList(1)));
    }

    @Test
    public void shouldMergeTwoListsOfOneNode() {
        final Node<Integer> list1 = createLinedList(2);
        final Node<Integer> list2 = createLinedList(3);

        final Node<Integer> merged = LinkedListUtils.merge(list1, list2);

        assertThat(merged, is(linkedList(2, 3)));
    }

    @Test
    public void shouldMergeTwoListsOfOneNodeInCorrectOrder() {
        final Node<Integer> list1 = createLinedList(12);
        final Node<Integer> list2 = createLinedList(3);

        final Node<Integer> merged = LinkedListUtils.merge(list1, list2);

        assertThat(merged, is(linkedList(3, 12)));
    }

    @Test
    public void shouldMergeTwoLists() {
        final Node<Integer> list1 = createLinedList(1, 2, 5, 7, 9, 12, 15);
        final Node<Integer> list2 = createLinedList(3, 5, 7, 10);

        final Node<Integer> merged = LinkedListUtils.merge(list1, list2);

        assertThat(merged, is(linkedList(1, 2, 3, 5, 5, 7, 7, 9, 10, 12, 15)));
    }

    @Test
    public void shouldMergeTwoListsSecondBeingLarger() {
        final Node<Integer> list1 = createLinedList(1, 2, 5, 7, 9, 12, 15);
        final Node<Integer> list2 = createLinedList(3, 5, 7, 10, 11, 12, 14, 16, 19);

        final Node<Integer> merged = LinkedListUtils.merge(list1, list2);

        assertThat(merged, is(linkedList(1, 2, 3, 5, 5, 7, 7, 9, 10, 11, 12, 12, 14, 15, 16, 19)));
    }

    private void append(final Node<String> list, final Node<String> tail) {
        Node pointer = list;
        while (pointer.next() != null) {
            pointer = pointer.next();
        }
        pointer.setNext(tail);
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
