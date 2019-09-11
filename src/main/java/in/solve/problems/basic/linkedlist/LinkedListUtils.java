package in.solve.problems.basic.linkedlist;

import java.util.HashMap;

public class LinkedListUtils {

    public static <T> Node<T> reverse(final Node<T> list) {
        Node<T> pointer = list;
        Node lastNode = null;
        while (pointer != null) {
            final Node newNode = Node.of(pointer.value());
            newNode.setNext(lastNode);
            lastNode = newNode;
            pointer = pointer.next();
        }
        return lastNode;
    }

    public static <T> void deDupe(final Node<T> list) {
        final HashMap<T, Boolean> values = new HashMap<>();
        Node<T> pointer = list;
        Node<T> followerPointer = null;
        while (pointer != null) {
            if (values.containsKey(pointer.value())) {
                followerPointer.setNext(pointer.next());
            } else {
                followerPointer = pointer;
                values.put(pointer.value(), Boolean.TRUE);
            }

            pointer = pointer.next();
        }

    }

    public static <T> T intersectionPoint(final Node<T> list1, final Node<T> list2) {
        if (list1 == null || list2 == null) {
            return null;
        }

        int length1 = lengthOf(list1);
        int length2 = lengthOf(list2);

        if (length1 > length2) {
            return intersectionPoint(length1 - length2, list1, list2);
        }

        return intersectionPoint(length2 - length1, list2, list1);
    }

    public static <T> T lastNthElement(final Node<T> list, final int n) {
        if (list == null) {
            return null;
        }
        Node<T> follower = list;
        Node<T> leader = list;

        for (int i = 0; i < n; i++) {
            if (leader == null) {
                return null;
            }
            leader = leader.next();
        }

        while (leader != null) {
            leader = leader.next();
            follower = follower.next();
        }

        return follower == null? null : follower.value();
    }

    public static <T> T intersectionPoint(final int diff, final Node<T> largerList, final Node<T> smallerList) {
        Node<T> pointer = largerList;
        for (int i = 0; i < diff; i++) {
            pointer = pointer.next();
        }

        Node<T> anotherPointer = smallerList;
        while (anotherPointer != null) {
            if (anotherPointer == pointer) {
                return pointer.value();
            }

            anotherPointer = anotherPointer.next();
            pointer = pointer.next();
        }

        return null;
    }

    private static <T>int lengthOf(Node<T> node) {
        int length = 0;
        Node<T> pointer = node;

        while (pointer != null) {
            pointer = pointer.next();
            length++;
        }

        return length;
    }

    public static <T extends Comparable> Node<T> merge(final Node<T> list1, final Node<T> list2) {
        Node<T> merged = null;
        Node<T> mergedLast = merged;

        Node<T> pointer1 = list1;
        Node<T> pointer2 = list2;

        while (pointer1 != null && pointer2 != null) {
            while (pointer1 != null && pointer2 != null && pointer1.value().compareTo(pointer2.value()) <= 0) {
                Node<T> temp = Node.of(pointer1.value());
                if (merged == null) {
                    merged = temp;
                }
                if (mergedLast != null) {
                    mergedLast.setNext(temp);
                }
                mergedLast = temp;
                pointer1 = pointer1.next();
            }

            while (pointer2 != null && pointer1 != null && pointer2.value().compareTo(pointer1.value()) <= 0) {
                Node<T> temp = Node.of(pointer2.value());
                if (merged == null) {
                    merged = temp;
                }
                if (mergedLast != null) {
                    mergedLast.setNext(temp);
                }
                mergedLast = temp;
                pointer2 = pointer2.next();
            }
        }

        if (pointer1 == null) {
            while (pointer2 != null) {
                Node<T> temp = Node.of(pointer2.value());
                if (merged == null) {
                    merged = temp;
                }
                if (mergedLast != null) {
                    mergedLast.setNext(temp);
                }
                mergedLast = temp;
                pointer2 = pointer2.next();
            }
        }

        if (pointer2 == null) {
            while (pointer1 != null) {
                Node<T> temp = Node.of(pointer1.value());
                if (merged == null) {
                    merged = temp;
                }
                if (mergedLast != null) {
                    mergedLast.setNext(temp);
                }
                mergedLast = temp;
                pointer1 = pointer1.next();
            }
        }

        return merged;
    }

    public static <T> void reverseEvenNodesAtEnd(final Node<T> list) {
        if (list == null) {
            return;
        }

        Node<T> evenList = list.next();
        Node<T> oddList = list;

        Node<T> oddPointer = oddList;
        Node<T> evenPointer = evenList;


        while (evenPointer != null && oddPointer != null) {
            oddPointer.setNext(evenPointer.next());
            if (evenPointer.next() != null) {
                evenPointer.setNext(evenPointer.next().next());
            }
            oddPointer = oddPointer.next();
            evenPointer = evenPointer.next();
        }


        while (oddList.next() != null) {
            oddList = oddList.next();
        }

        oddList.setNext(reverse(evenList));

    }

    public static <T> Node<T> reverse(final Node<T> list, final int numberOfElements) {
        if (list == null || list.next() == null) {
            return list;
        }

        if (numberOfElements > lengthOf(list)) {
            return list;
        }

        Node<T> pointer = list.next();
        Node<T> follower = list;
        Node<T> beginning = list;

        int i = 1;

        while (pointer!= null && i < numberOfElements) {
            Node<T> temp = pointer.next();
            pointer.setNext(follower);
            follower = pointer;
            pointer = temp;
            i++;
        }

        beginning.setNext(pointer);

        return follower;
    }
}
