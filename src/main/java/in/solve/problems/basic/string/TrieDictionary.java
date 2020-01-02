package in.solve.problems.basic.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

final class TrieDictionary {

    private final Node root = Node.empty();

    private TrieDictionary() {

    }

    static TrieDictionary createNew() {
        return new TrieDictionary();
    }

    public void add(final String ...words) {
        Arrays.stream(words)
                .filter(Objects::nonNull)
                .filter(s -> s.trim().length() != 0)
                .forEach(this::addWord);
    }

    private void addWord(final String word) {
        Node pointer = root;
        for (int i = 0; i < word.length(); i++) {
            final char character = word.charAt(i);
            pointer = pointer.addMapping(character);
        }
        pointer.markEndNode();
    }

    public boolean has(final String word) {
        if (isEmpty(word)) {
            return false;
        }
        Node pointer = root;
        for (int i = 0; i < word.length(); i++) {
            final char character = word.charAt(i);
            if (pointer.hasCharacter(character)) {
                pointer = pointer.geChild(character);
            } else {
                return false;
            }
        }

        return pointer.isEndNode();
    }

    public boolean hasWordStartingWith(final String str) {
        if (isEmpty(str)) {
            return false;
        }
        Node pointer = root;
        for (int i = 0; i < str.length(); i++) {
            final char character = str.charAt(i);
            if (pointer.hasCharacter(character)) {
                pointer = pointer.geChild(character);
            } else {
                return false;
            }
        }

        return true;
    }

    public void delete(final String word) {
        if (isEmpty(word)) {
            return;
        }
        final Map<Character, Node> nodes = new LinkedHashMap<>();
        Node pointer = root;
        for (int i = 0; i < word.length(); i++) {
            final char character = word.charAt(i);
            if (pointer.hasCharacter(character)) {
                nodes.put(character, pointer);
                pointer = pointer.geChild(character);
            } else {
                return;
            }
        }
        if (pointer.isEndNode()) {
            pointer.unMarkEndNode();
        } else {
            return;
        }
        Node childNode = pointer;
        for (int i = word.length()-1; i >= 0; i--) {
            final char parentCharacter = word.charAt(i);
            Node parentNode = nodes.get(parentCharacter);
            deleteNode(childNode, parentNode, parentCharacter);
            childNode = parentNode;
        }
    }

    private void deleteNode(final Node childNode, final Node parentNode, final Character parentChar) {
        if (childNode.isEmpty() && !childNode.isEndNode()) {
            parentNode.removeCharacter(parentChar);
        }
    }


    private boolean isEmpty(final String str) {
        return str == null || str.length() == 0;
    }

    private static class Node {
        private Map<Character, Node> map = new HashMap<>();
        private boolean isEndNode = false;

        private static Node empty() {
            return new Node();
        }

        private Node addMapping(final Character character) {
            if (map.containsKey(character)) {
                return map.get(character);
            }
            else {
                final Node node = Node.empty();
                map.put(character, node);
                return node;
            }
        }

        private Node markEndNode() {
            this.isEndNode = true;
            return this;
        }

        private Node unMarkEndNode() {
            this.isEndNode = false;
            return this;
        }

        private boolean hasCharacter(final Character character) {
            return this.map.containsKey(character);
        }

        private void removeCharacter(final Character character) {
            this.map.remove(character);
        }

        private boolean isEmpty() {
            return map.isEmpty();
        }

        private Node geChild(final Character character) {
            return this.map.get(character);
        }

        private boolean isEndNode() {
            return isEndNode;
        }
    }
}
