package in.solve.problems.ctci.ch3;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TowersOfHanoiTest {

    @Test
    public void testTowersOfHanoi() {

        for (int i = 0; i < 10; i++) {
            TowersOfHanoi tower = TowersOfHanoi.ofSize(i);
            TowersOfHanoi.Tower initialTower = tower.getTower(0);
            assertThat("init failure i = " + i, initialTower.getSize(), is(i));

            tower.move();

            assertTrue("init tower not empty i = " + i, initialTower.isEmpty());
            TowersOfHanoi.Tower targetTower = tower.getTower(2);
            assertThat("target tower size mismatch i = " + i, targetTower.getSize(), is(i));
            assertTrue("temp tower not empty i = " + i, tower.getTower(1).isEmpty());
            for (int j = 0; j < i; j++) {
                assertThat("i = " + i, targetTower.pop(), is(j+1));
            }
        }

    }
}
