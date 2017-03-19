
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
public class WireWorldJUnitTest {
    
    @Test
    public void HeadIntoTail() throws Exception {
        Automaton a = new WireWorld(null,null,4,4);
        Assert.assertEquals(WireElectronState.ELECTRON_TAIL, a.nextCellState(new Cell(WireElectronState.ELECTRON_HEAD,new Coords2D(3,3)),null));
    }
    @Test
    public void TailIntoWire() throws Exception {
        Automaton a = new WireWorld(null,null,4,4);
        Assert.assertEquals(WireElectronState.WIRE, a.nextCellState(new Cell(WireElectronState.ELECTRON_TAIL,new Coords2D(3,3)),null));
    }
    @Test
    public void WireIntoHead() throws Exception {
        Automaton a = new WireWorld(null,null,4,4);
        Set<Cell> set = new HashSet<>();
        set.add(new Cell(WireElectronState.ELECTRON_HEAD,new Coords2D(0,0)));
        set.add(new Cell(WireElectronState.ELECTRON_TAIL,new Coords2D(1,0)));
        set.add(new Cell(WireElectronState.WIRE,new Coords2D(0,1)));
        set.add(new Cell(WireElectronState.ELECTRON_HEAD,new Coords2D(2,0)));
        set.add(new Cell(WireElectronState.VOID,new Coords2D(1,2)));
        Assert.assertEquals(WireElectronState.ELECTRON_HEAD, a.nextCellState(new Cell(WireElectronState.WIRE,new Coords2D(3,3)),set));
    }
}
