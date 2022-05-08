package in.solve.problems.ctci.ch3;

import com.google.common.collect.Lists;

import java.util.List;

public class TowersOfHanoi {

    private final List<Tower> towers;
    private int size;

    private TowersOfHanoi(int size) {
        this.size = size;
        this.towers = Lists.newArrayList(new Tower("0"), new Tower("1"), new Tower("2"));
        for (int i = size; i > 0; i--) {
            towers.get(0).push(i);
        }
    }

    public void move() {
        getTower(0).move(size, getTower(1), getTower(2));
    }

    public Tower getTower(int index) {
        return towers.get(index);
    }

    public static TowersOfHanoi ofSize(int size) {
        return new TowersOfHanoi(size);
    }

    public class Tower extends LStack<Integer> {

        private final String name;

        public Tower(String name) {
            this.name = name;
        }

        @Override
        public void push(Integer item) {
            if ((!this.isEmpty()) && item >= this.peek()) {
                throw new IllegalArgumentException("Can not push " + item + " on rod with top being " + this.peek());
            }
            super.push(item);
        }

        @Override
        public String toString() {
            return "Tower " + name;
        }

        public void move(int noOfItems, Tower temp, Tower target) {
            if (noOfItems == 0) {
                return;
            }
            move(noOfItems - 1, target, temp);
            target.push(this.pop());
            temp.move(noOfItems -1, this, target);
        }
    }

}
