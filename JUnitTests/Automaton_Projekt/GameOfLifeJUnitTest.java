
package Automaton_Projekt;

import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class GameOfLifeJUnitTest {
    @Test
    public void NextCellStateBirthShouldBeAlive() throws Exception {
        Set<Integer> born = new HashSet<>();
        born.add(2);
        Set<Integer> surv = new HashSet<>();
        surv.add(2);
        surv.add(3);
        Automaton a = new GameOfLife(new UniformStateFactory(BinaryState.DEAD), new MoorNeighborhood(4,4,true),4,4,born,surv);
        Set<Cell> set = new HashSet<>();
        set.add(new Cell(BinaryState.DEAD,new Coords2D(0,0)));
        set.add(new Cell(BinaryState.ALIVE,new Coords2D(1,0)));
        set.add(new Cell(BinaryState.DEAD,new Coords2D(0,1)));
        set.add(new Cell(BinaryState.ALIVE,new Coords2D(2,0)));
        set.add(new Cell(BinaryState.DEAD,new Coords2D(1,2)));
        Assert.assertEquals(BinaryState.ALIVE, a.nextCellState(new Cell(BinaryState.DEAD,new Coords2D(3,3)),set));
    }
    @Test
    public void NextCellStateSurvivalShouldBeDead() throws Exception {
        Set<Integer> born = new HashSet<>();
        born.add(2);
        Set<Integer> surv = new HashSet<>();
        surv.add(2);
        surv.add(3);
        Automaton a = new GameOfLife(new UniformStateFactory(BinaryState.DEAD), new MoorNeighborhood(4,4,true),4,4,born,surv);
        Set<Cell> set = new HashSet<>();
        set.add(new Cell(BinaryState.DEAD,new Coords2D(0,0)));
        set.add(new Cell(BinaryState.DEAD,new Coords2D(1,0)));
        set.add(new Cell(BinaryState.DEAD,new Coords2D(0,1)));
        set.add(new Cell(BinaryState.ALIVE,new Coords2D(2,0)));
        set.add(new Cell(BinaryState.DEAD,new Coords2D(1,2)));
        Assert.assertEquals(BinaryState.DEAD, a.nextCellState(new Cell(BinaryState.DEAD,new Coords2D(3,3)),set));

    }
    
    @Test
    public void NextCoordinatesJUnitTest() throws Exception {
        Automaton aut = new GameOfLife(null, null,4,4,null,null);
        Assert.assertEquals(new Coords2D(0,3),aut.nextCoordinates(new Coords2D(3,2)));

    }
}
